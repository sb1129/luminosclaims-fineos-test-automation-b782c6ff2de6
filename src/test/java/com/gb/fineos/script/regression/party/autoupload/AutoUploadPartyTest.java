package com.gb.fineos.script.regression.party.autoupload;

import com.gb.fineos.page.sharedpages.correspondence.GBEmailViewerPage;
import com.gb.fineos.page.sharedpages.documentmanager.DocumentPropertiesPage;
import com.gb.fineos.page.sharedpages.partymanager.partydetails.PartyDetailsPage;
import com.gb.fineos.page.widgets.documentmanager.ExtraDataPage;
import com.gb.fineos.page.widgets.documentmanager.doctype.DocumentTypeManagerSearchPage;
import com.gb.fineos.page.widgets.documentmanager.fileupload.UploadDocumentPage;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.screencapture.ScreenCapture;
import com.gb.fineos.script.utils.BaseTest;
import com.gb.fineos.page.utils.SearchUtils;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;


public class AutoUploadPartyTest extends BaseTest {


    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.nz", "regression.icare"})
    public void autoUploadDocumentsPartyTest(final Map<String, String> testData) {
        doTest("Auto Upload changes are visible for Notifications", "", testData, tc -> {
            final GBEmailViewerPage.GBEmailViewerPageRequest gbEmailViewerPageRequest = new GBEmailViewerPage.GBEmailViewerPageRequest(tc);
            final GBEmailViewerPage gbEmailViewerPage = navigateToGBEmailViewerPage(gbEmailViewerPageRequest);

            Assert.assertEquals(gbEmailViewerPage.getUploadButton().isEnabled(), false);
            Assert.assertEquals(gbEmailViewerPage.getViewButton().isEnabled(), true);
            gbEmailViewerPage.close(gbEmailViewerPageRequest);
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.nz", "regression.icare"})
    public void autoUploadMultipleAttachmentsSelectedPartyTest(final Map<String, String> testData) {
        doTest("Select Multiple Attachments and verify that warning is displayed and View button is disabled", "", testData, tc -> {
            final GBEmailViewerPage.GBEmailViewerPageRequest gbEmailViewerPageRequest = new GBEmailViewerPage.GBEmailViewerPageRequest(tc);
            final GBEmailViewerPage gbEmailViewerPage = navigateToGBEmailViewerPage(gbEmailViewerPageRequest);
            gbEmailViewerPage.selectMultipleAttachments(gbEmailViewerPageRequest);
            gbEmailViewerPage.assertViewButtonIsDisabled(gbEmailViewerPageRequest);
            gbEmailViewerPage.getAssertionHelper().assertIsDisplayed("message after selecting more than one attachment to view", gbEmailViewerPage.getViewMessage());
            gbEmailViewerPage.close(gbEmailViewerPageRequest);
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.nz", "regression.icare"})
    public void autoUploadViewOneAttachmentPartyTest(final Map<String, String> testData) {
        doTest("Select One Attachment from Email viewer and View the document", "", testData, tc -> {
            final GBEmailViewerPage.GBEmailViewerPageRequest gbEmailViewerPageRequest = new GBEmailViewerPage.GBEmailViewerPageRequest(tc);
            final GBEmailViewerPage gbEmailViewerPage = navigateToGBEmailViewerPage(gbEmailViewerPageRequest);
            gbEmailViewerPage.selectOneAttachment(gbEmailViewerPageRequest);
            gbEmailViewerPage.assertViewButtonIsEnabled(gbEmailViewerPageRequest);
            gbEmailViewerPage.clickViewButton(gbEmailViewerPageRequest);
            Assert.assertTrue(gbEmailViewerPage.fileDownloaded(gbEmailViewerPageRequest.getFileName(), gbEmailViewerPageRequest));
            gbEmailViewerPage.deleteDownloadedFiles(gbEmailViewerPageRequest.getFileName(), gbEmailViewerPageRequest);
            gbEmailViewerPage.close(gbEmailViewerPageRequest);
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.nz", "regression.icare"})
    public void autoUploadSelectAllViewPartyTest(final Map<String, String> testData) {
        doTest("Select All Attachments and verify that warning is displayed and View button is disabled", "", testData, tc -> {
            final GBEmailViewerPage.GBEmailViewerPageRequest gbEmailViewerPageRequest = new GBEmailViewerPage.GBEmailViewerPageRequest(tc);
            final GBEmailViewerPage gbEmailViewerPage = navigateToGBEmailViewerPage(gbEmailViewerPageRequest);
            gbEmailViewerPage.selectAllAttachments(gbEmailViewerPageRequest);
            gbEmailViewerPage.assertViewButtonIsDisabled(gbEmailViewerPageRequest);
            gbEmailViewerPage.assertMultipleAttachmentsSelectedToViewMessage(gbEmailViewerPageRequest);
            gbEmailViewerPage.close(gbEmailViewerPageRequest);
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.nz", "regression.icare"})
    public void verifyPrefixAndOrderOfAttachments(final Map<String, String> testData) {
        doTest("Auto Upload Documents Prefix and order", "AutoUpload Attachments - Identify footer images from attachment list", testData, tc -> {
            final GBEmailViewerPage.GBEmailViewerPageRequest gbEmailViewerPageRequest = new GBEmailViewerPage.GBEmailViewerPageRequest(tc);
            final GBEmailViewerPage gbEmailViewerPage = navigateToGBEmailViewerPage(gbEmailViewerPageRequest);
            gbEmailViewerPage.verifyOrderOfAttachments(gbEmailViewerPageRequest);
            gbEmailViewerPage.close(gbEmailViewerPageRequest);
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.nz", "regression.icare"}, dependsOnMethods = {})
    public void uploadDocumentFromEmailViewerPageTest(final Map<String, String> testData) {
        doTest("Auto upload Email Viewer Page", "Select an Attachment from Email viewer and upload as document", testData, tc -> {
            //open the attached document in GB email viewer
            final GBEmailViewerPage.GBEmailViewerPageRequest gbEmailViewerPageRequest = new GBEmailViewerPage.GBEmailViewerPageRequest(tc);
            final GBEmailViewerPage gbEmailViewerPage = navigateToGBEmailViewerPage(gbEmailViewerPageRequest);
            gbEmailViewerPage.selectMultipleAttachments(gbEmailViewerPageRequest);
            gbEmailViewerPage.clickUploadButton(gbEmailViewerPageRequest);
            if (gbEmailViewerPage.getUploadingMessage(gbEmailViewerPageRequest).isEmpty()) {
                gbEmailViewerPageRequest.log("GB Email Viewer Page Request", "Uploading document confirmation message is not displayed with in the execution time");
            } else {
                org.junit.Assert.assertEquals(gbEmailViewerPage.getUploadingMessage(gbEmailViewerPageRequest), gbEmailViewerPageRequest.getUploadingMessage(), "Uploading document confirmation message ");
            }
            ScreenCapture.logScreenshot("Document Upload", LogStatus.INFO);
            if (gbEmailViewerPage.getUploadMessage(gbEmailViewerPageRequest).equalsIgnoreCase(gbEmailViewerPageRequest.getConfirmationMessage())) {
                gbEmailViewerPageRequest.log("GB Email Viewer Page Request", "Uploaded document confirmation message : " + gbEmailViewerPage.getUploadMessage(gbEmailViewerPageRequest));
            } else  {
                gbEmailViewerPageRequest.log("GB Email Viewer Page Request", "Uploaded document confirmation message : " + gbEmailViewerPage.getUploadMessage(gbEmailViewerPageRequest));
                ScreenCapture.logScreenshot("GB Email Viewer Page", LogStatus.FAIL);
                org.junit.Assert.fail();
            }
            gbEmailViewerPage.close(gbEmailViewerPageRequest);
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.nz", "regression.icare"})
    public void verifyThePropertiesOfUploadDocument(final Map<String, String> testData) {
        doTest("Auto upload document properties page", "Verify the Attachment properties correspondence added has a “Party” or “Case” or BOTH linked to the source", testData, tc -> {

            //open the attached document in GB email viewer
            final GBEmailViewerPage.GBEmailViewerPageRequest gbEmailViewerPageRequest = new GBEmailViewerPage.GBEmailViewerPageRequest(tc);
            final GBEmailViewerPage gbEmailViewerPage = navigateToGBEmailViewerPage(gbEmailViewerPageRequest);
            gbEmailViewerPage.selectMultipleAttachments(gbEmailViewerPageRequest);
            gbEmailViewerPage.clickUploadButton(gbEmailViewerPageRequest);
            gbEmailViewerPage.close(gbEmailViewerPageRequest);
            SearchUtils.searchParty(tc);
            // documents tab
            final PartyDetailsPage partyDetailsPage = tc.getPage(PartyDetailsPage.class);
            final PartyDetailsPage.PartyDetailsPageRequest partyDetailsPageRequest = new PartyDetailsPage.PartyDetailsPageRequest(tc);
            partyDetailsPage.clickDocumentsTabAndNavigateToDocumentsForParty(partyDetailsPageRequest);
            partyDetailsPage.clickDocumentTypeFilter(partyDetailsPageRequest);
            partyDetailsPage.getFilter().inputFilterText(partyDetailsPageRequest, partyDetailsPageRequest.getDocumentType());
            partyDetailsPage.getFilter().clickApplyButton(partyDetailsPageRequest);
            //select the task
            partyDetailsPage.selectFirstCheckbox(partyDetailsPageRequest);

            //click on Properties button
            partyDetailsPage.clickOnPropertiesButton(partyDetailsPageRequest);
            final DocumentPropertiesPage documentPropertiesPage = tc.getPage(DocumentPropertiesPage.class);
            final  DocumentPropertiesPage.DocumentPropertiesPageRequest documentPropertiesPageRequest = new DocumentPropertiesPage.DocumentPropertiesPageRequest(tc);
            if (!documentPropertiesPage.isPartyOrCaseFieldEmpty(documentPropertiesPageRequest)) {
                Assert.fail("EXCEPTION CAUSED BY..Party/Case not linked to the Document");
            }
            documentPropertiesPage.clickCancelButton(documentPropertiesPageRequest);
        });
    }

    private GBEmailViewerPage navigateToGBEmailViewerPage(final GBEmailViewerPage.GBEmailViewerPageRequest pageRequest) {
        SearchUtils.searchParty(pageRequest.getContext());

        final PartyDetailsPage partyDetailsPage = pageRequest.getContext().getPage(PartyDetailsPage.class);
        final PartyDetailsPage.PartyDetailsPageRequest partyDetailsPageRequest = new PartyDetailsPage.PartyDetailsPageRequest(pageRequest.getContext());
        partyDetailsPage.clickDocumentsTabAndNavigateToDocumentsForParty(partyDetailsPageRequest);
        partyDetailsPage.clickDocumentTypeFilter(partyDetailsPageRequest);
        partyDetailsPage.getFilter().inputFilterText(partyDetailsPageRequest, partyDetailsPageRequest.getDocumentType());
        partyDetailsPage.getFilter().clickApplyButton(partyDetailsPageRequest);
        if (partyDetailsPage.getResultRows().size() == 0) {
            partyDetailsPageRequest.getContext().log("PARTY DETAILS  PAGE", "Document Not Found. Row count = " + String.valueOf(partyDetailsPage.getResultRows().size()) + ". Adding Document..");
            partyDetailsPage.clickAddDocumentButton(partyDetailsPageRequest);
            // search type of document and select outbound correspondence document
            final DocumentTypeManagerSearchPage documentTypeManagerSearchPage = pageRequest.getContext().getPage(DocumentTypeManagerSearchPage.class);
            final DocumentTypeManagerSearchPage.DocumentTypeManagerSearchPageRequest documentTypeManagerSearchPageRequest = new DocumentTypeManagerSearchPage.DocumentTypeManagerSearchPageRequest(pageRequest.getContext());
            documentTypeManagerSearchPage.clickOnSearchTab(documentTypeManagerSearchPageRequest);
            documentTypeManagerSearchPage.selectCategoryAsDefault(documentTypeManagerSearchPageRequest);
            documentTypeManagerSearchPage.enterBusinessTypeName(documentTypeManagerSearchPageRequest);
            documentTypeManagerSearchPage.clickOnSearchButton(documentTypeManagerSearchPageRequest);
            documentTypeManagerSearchPage.clickOnOutboundCorrespondence(documentTypeManagerSearchPageRequest);
            documentTypeManagerSearchPage.clickOnOkButton(documentTypeManagerSearchPageRequest);

            final UploadDocumentPage uploadDocumentPage = pageRequest.getContext().getPage(UploadDocumentPage.class);
            final UploadDocumentPage.UploadDocumentPageRequest uploadDocumentPageRequest = new UploadDocumentPage.UploadDocumentPageRequest(pageRequest.getContext());

            int retry = 0; //retrying the file upload 3 times.
            boolean success = false; //capturing result outcome True/ False.
            while (!success && retry < 3)  {
                uploadDocumentPage.uploadDocument(uploadDocumentPageRequest);
                uploadDocumentPage.selectPrivacyTag(uploadDocumentPageRequest);
                ScreenCapture.logScreenshot("Document Upload", LogStatus.INFO);
                uploadDocumentPage.clickOnOkButton(uploadDocumentPageRequest);
                final ExtraDataPage extraDataPage = pageRequest.getContext().getPage(ExtraDataPage.class);
                final ExtraDataPage.ExtraDataPageRequest extraDataPageRequest = new ExtraDataPage.ExtraDataPageRequest(pageRequest.getContext());
                extraDataPage.clickOKOrClose(extraDataPageRequest);
                if (uploadDocumentPage.isValidationMessageExists(uploadDocumentPageRequest, uploadDocumentPage.getWarningMessage())) {
                    uploadDocumentPage.clickCloseWarningMessage(uploadDocumentPageRequest);
                } else {
                    success = true;
                    break;
                }
                retry ++;
            }
            //checking if the file is uploaded
            if (success) {
                partyDetailsPageRequest.log("PARTY DETAILS  PAGE", "file uploaded");
            } else {
                uploadDocumentPageRequest.log("PARTY DETAILS  PAGE", "Upload Failed");
            }

        }
        partyDetailsPageRequest.getContext().log("PARTY DETAILS  PAGE", "Documents Found. Row count = " + String.valueOf(partyDetailsPage.getResultRows().size()));
        partyDetailsPage.clickOutboundCorrespondenceDocumentLinkToOpen(partyDetailsPageRequest);

        return GBEmailViewerPage.switchTo(pageRequest);
    }
}
