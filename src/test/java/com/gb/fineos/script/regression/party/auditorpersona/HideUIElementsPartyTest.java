package com.gb.fineos.script.regression.party.auditorpersona;

import com.gb.fineos.page.sharedpages.partymanager.partydetails.PartyDetailsPage;
import com.gb.fineos.page.utils.SearchUtils;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.script.utils.BaseTest;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.Map;


public class HideUIElementsPartyTest extends BaseTest {
    private static final int EXPECTED_WIDGET_SIZE = 0;
    private static final String AUDITOR_PERSONA_PARTY_TEST = "Auditor Persona Party Test";

    //GEN-1091 AC1
    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.nz"})
    public void partyPageValidationsTest(final Map<String, String> testData) {
        doTest("HIDE UI ELEMENTS TEST", "Auditor Persona Party Page Tab test", testData, tc -> {
            SearchUtils.searchParty(tc);

            final PartyDetailsPage partyDetailsPage = tc.getPage(PartyDetailsPage.class);
            final PartyDetailsPage.PartyDetailsPageRequest partyDetailsPageRequest = new PartyDetailsPage.PartyDetailsPageRequest(tc);

            partyDetailsPageRequest.log(AUDITOR_PERSONA_PARTY_TEST, "Looking for Contacts Tab");
            Assert.assertFalse("Contacts Tab is available", partyDetailsPage.isElementFound(partyDetailsPage.getContactsTab()));
            partyDetailsPageRequest.log(AUDITOR_PERSONA_PARTY_TEST, "Looking for Documents Tab");
            Assert.assertFalse("Documents Tab is available", partyDetailsPage.isElementFound(partyDetailsPage.getDocumentsTab()));
            partyDetailsPageRequest.log(AUDITOR_PERSONA_PARTY_TEST, "Looking for Tasks Tab");
            Assert.assertFalse("Tasks Tab is available", partyDetailsPage.isElementFound(partyDetailsPage.getTasksTab()));
            partyDetailsPageRequest.log(AUDITOR_PERSONA_PARTY_TEST, "Looking for Portfolio Tab");
            Assert.assertFalse("Portfolio Tab is available", partyDetailsPage.isElementFound(partyDetailsPage.getPortfolioTab()));
            partyDetailsPageRequest.log(AUDITOR_PERSONA_PARTY_TEST, "Looking for Relationship Tab");
            Assert.assertFalse("Relationship Tab is available", partyDetailsPage.isElementFound(partyDetailsPage.getRelationshipTab()));
            partyDetailsPageRequest.log(AUDITOR_PERSONA_PARTY_TEST, "Looking for Cases Tab");
            Assert.assertFalse("Cases Tab is available", partyDetailsPage.isElementFound(partyDetailsPage.getCasesTab()));
            partyDetailsPageRequest.log(AUDITOR_PERSONA_PARTY_TEST, "Looking for Payment History Tab");
            Assert.assertFalse("Payment History Tab is available", partyDetailsPage.isElementFound(partyDetailsPage.getPaymentHistoryTab()));

            Assert.assertTrue("Customer Tab is not found", partyDetailsPage.isElementFound(partyDetailsPage.getCustomerTab()));
            partyDetailsPageRequest.log(AUDITOR_PERSONA_PARTY_TEST, "Customer Tab found");
            Assert.assertTrue("Extra Information Tab is not found", partyDetailsPage.isElementFound(partyDetailsPage.getExtraInfoTab()));
            partyDetailsPageRequest.log(AUDITOR_PERSONA_PARTY_TEST, "Extra information Tab found");
            Assert.assertTrue("Party Roles Tab is not found", partyDetailsPage.isElementFound(partyDetailsPage.getPartyRolesTab()));
            partyDetailsPageRequest.log(AUDITOR_PERSONA_PARTY_TEST, "Party Roles Tab found");
            Assert.assertTrue("Tax Tab is not found", partyDetailsPage.isElementFound(partyDetailsPage.getTaxTab()));
            partyDetailsPageRequest.log(AUDITOR_PERSONA_PARTY_TEST, "Tax Tab found");
            Assert.assertTrue("Payment Preferences Tab is not found", partyDetailsPage.isElementFound(partyDetailsPage.getPaymentPreferencesTab()));
            partyDetailsPageRequest.log(AUDITOR_PERSONA_PARTY_TEST, "Payment Preferences Tab found");
            Assert.assertTrue("Certification Tab is not found", partyDetailsPage.isElementFound(partyDetailsPage.getCertificationsTab()));
            partyDetailsPageRequest.log(AUDITOR_PERSONA_PARTY_TEST, "Certification Tab found");
        });
    }

    //GEN-1091 AC2
    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.nz"})
    public void partyPageSummaryTabValidationTest(final Map<String, String> testData) {
        doTest("HIDE UI ELEMENTS TEST", "Auditor Persona Party Page Summary Tab test", testData, tc -> {
            SearchUtils.searchParty(tc);

            final PartyDetailsPage partyDetailsPage = tc.getPage(PartyDetailsPage.class);
            final PartyDetailsPage.PartyDetailsPageRequest partyDetailsPageRequest = new PartyDetailsPage.PartyDetailsPageRequest(tc);
            partyDetailsPage.clickCustomerTab(partyDetailsPageRequest);

            Assert.assertFalse("Summary Tab -> portfolio section is available ", partyDetailsPage.getPortfolioFor().size() > EXPECTED_WIDGET_SIZE);
            partyDetailsPageRequest.log(AUDITOR_PERSONA_PARTY_TEST, "Summary Tab -> portfolio section not found");

            Assert.assertFalse("Summary Tab -> Last Contact Details is available ", partyDetailsPage.getLastContactDetails().size() > EXPECTED_WIDGET_SIZE);
            partyDetailsPageRequest.log(AUDITOR_PERSONA_PARTY_TEST, "Summary Tab -> last contact details section not found");

            Assert.assertFalse("Summary Tab -> Points of Contacts is available ", partyDetailsPage.getPointsOfContact().size() > EXPECTED_WIDGET_SIZE);
            partyDetailsPageRequest.log(AUDITOR_PERSONA_PARTY_TEST, "Summary Tab -> points of contact section not found");
        });
    }
}
