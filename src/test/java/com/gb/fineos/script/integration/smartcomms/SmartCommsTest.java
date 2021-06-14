package com.gb.fineos.script.integration.smartcomms;

import com.gb.fineos.extent.ExtentTestManager;
import com.gb.fineos.factory.PropertiesFactory;
import com.gb.fineos.listener.TestListener;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.smartcomms.domain.Appliance;
import com.gb.smartcomms.domain.ComplexGetRequest;
import com.gb.smartcomms.domain.ComplexGetRequest.Address;
import com.gb.smartcomms.domain.ComplexGetRequest.OrganisationRecipient;
import com.gb.smartcomms.domain.ComplexGetRequest.PersonRecipient;
import com.gb.smartcomms.service.SmartCommsService;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.Logger;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static com.gb.smartcomms.service.SmartCommsAssert.assertHasNode;
import static com.gb.smartcomms.service.SmartCommsAssert.assertMatches;

@Listeners(TestListener.class)
public class SmartCommsTest {
    private static final Logger LOG = Logger.getLogger(SmartCommsTest.class);

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"integration.smartcomms.au","integration.smartcomms.icare","integration.smartcomms.uk", "integration.smartcomms.nz"})
    public void correspondencePersonTest(final Map<String, String> testData) {
        doTest("correspondencePersonTest", "Generate correspondence API scenario for a person recipient.", testData, td -> {
            final ComplexGetRequest request = getComplexRequest(testData);
            final Document document = SmartCommsService.getDocument(request);

            assertDocument(testData, request, document);
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"integration.smartcomms.au","integration.smartcomms.icare","integration.smartcomms.uk", "integration.smartcomms.nz"})
    public void correspondenceOrganisationTest(final Map<String, String> testData) {
        doTest("correspondenceOrganisationTest", "Generate correspondence API scenario for an organisation recipient.", testData, td -> {
            final ComplexGetRequest request = getComplexRequest(testData);
            final Document document = SmartCommsService.getDocument(request);

            assertDocument(testData, request, document);
        });
    }

    private ComplexGetRequest getComplexRequest(final Map<String, String> testData) {
        final List<PersonRecipient> personRecipients = new ArrayList<>();
        if (testData.containsKey("RecipientPersonTitle")) {
            personRecipients.add(new PersonRecipient(testData.get("RecipientCustomerNumber"), testData.get("RecipientPersonTitle"), testData.get("RecipientPersonFirstName"), testData.get("RecipientPersonLastName"), testData.get("RecipientPhoneNumber"), testData.get("RecipientMobileNumber"), testData.get("RecipientEmail"), new Address(testData.get("RecipientAddressStreet"), testData.get("RecipientAddressSuburb"), testData.get("RecipientAddressState"), testData.get("RecipientAddressPostcode"), testData.get("RecipientAddressCountry"))));
        }

        final List<OrganisationRecipient> organisationRecipients = new ArrayList<>();
        if (testData.containsKey("RecipientOrgShortName")) {
            organisationRecipients.add(new OrganisationRecipient(testData.get("RecipientCustomerNumber"), testData.get("RecipientOrgShortName"), testData.get("RecipientOrgName"), testData.get("RecipientOrgABN"), testData.get("RecipientOrgBankAccount"), testData.get("RecipientPhoneNumber"), testData.get("RecipientMobileNumber"), testData.get("RecipientEmail"), new Address(testData.get("RecipientAddressStreet"), testData.get("RecipientAddressSuburb"), testData.get("RecipientAddressState"), testData.get("RecipientAddressPostcode"), testData.get("RecipientAddressCountry"))));
        }

        return new ComplexGetRequest(Appliance.valueOf(PropertiesFactory.getInstance().getProperties().getProperty("smartcomms.appliance")), testData.get("SmartCommsTemplateId"), personRecipients, organisationRecipients, testData.get("Subject"), testData.get("SenderEmail"), testData.get("SignatureTemplateId"), testData.get("NotificationNumber"), testData.get("CaseNumber"), testData.get("PolicyRef"), testData.get("AttachmentSource"));
    }

    private void assertDocument(final Map<String, String> testData, final ComplexGetRequest request, final Document document) {
        assertMatches(request, document);

        if (testData.getOrDefault("SignatureTemplateHasHeader", "N").equalsIgnoreCase("Y")) {
            assertHasNode("/review-case/review-document/review-channel/content/region/section/section/frag/lcij/p/var[@name='AddresseeTitle']/text()='" + testData.get("RecipientPersonTitle") + "'", document);
            assertHasNode("/review-case/review-document/review-channel/content/region/section/section/frag/lcij/p/var[@name='AddresseeLastName']/text()='" + testData.get("RecipientPersonLastName") + "'", document);
        }

        assertHasNode("//section[@name='Footer']/frag/lcij/p/text()='" + testData.get("FooterText") + "'", document);
        assertHasNode("//section[@name='Footer']/frag/lcij/p/style/text()='" + testData.get("FooterEmail") + "'", document);

        assertHasNode("//section[@name='Signature']/frag/lcij/p/b/var[@full-path='Correspondence.Case.CurrentUser.Name']/text()='" + testData.get("SignatureCurrentUserName") + "'", document);
        assertHasNode("//section[@name='Signature']/frag/lcij/p/b/var[@full-path='Correspondence.Case.CurrentUser.Role']/text()='" + testData.get("SignatureCurrentUserRole") + "'", document);
        assertHasNode("//section[@name='Signature']/frag/lcij/p/text()='" + testData.get("SignatureCompany") + "'", document);
        assertHasNode("//section[@name='Signature']/frag/lcij/p/text()='" + testData.get("SignaturePhoneNumber") + "'", document);
        assertHasNode("//section[@name='Signature']/frag/lcij/p/frag/@id='" + testData.get("SignatureImageId") + "'", document);

        assertHasNode("//section[@name='DefaultAttachment']/frag[@name='Auto Attachment Name']/lcij/p/text()='" + testData.get("DefaultAttachmentFileName") + "'", document);
    }

    protected void doTest(final String name, final String description, final Map<String, String> testData, final Consumer<Map<String, String>> testCaseConsumer) {
        if (testData.getOrDefault("Runmode", "N").equalsIgnoreCase("Y")) {
            try {
                ExtentTestManager.getTest().setDescription(description);
                ExtentTestManager.getTest().log(LogStatus.INFO, "LOB : " + testData.get("LOB"));
                ExtentTestManager.getTest().log(LogStatus.INFO, "TemplateID : " + testData.get("SmartCommsTemplateId"));
                ExtentTestManager.getTest().log(LogStatus.INFO, "SignatureID : " + testData.get("SignatureTemplateId"));
                testCaseConsumer.accept(testData);

            } catch (Exception e) {
                LOG.error("Test " + name + "failed.", e);
                throw e;
            }
        } else {
            LOG.info("Test is not enabled.");
            ExtentTestManager.getTest().log(LogStatus.SKIP, name, "Test is not enabled.");
        }
    }
}
