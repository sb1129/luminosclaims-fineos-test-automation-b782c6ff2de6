package com.gb.fineos.script.regression.party.address.nz;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.sharedpages.partymanager.partydetails.PartyDetailsPage;
import com.gb.fineos.page.sharedpages.partymanager.partymaintenance.AddAddressPage;
import com.gb.fineos.page.sharedpages.partymanager.partysearch.PartySearchPage;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.script.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class AddressForEditPartyTest extends BaseTest {
    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = "regression.uk")
    public void inputAddressMandatoryFieldsOnly(final Map<String, String> testData) {
        doTest("inputDefaultIncidentAddressMandatoryFieldsOnly", "Verify Incident Address Fields on UK and NZ create Party", testData, tc -> {
            navigateToAddressForCreateParty(tc);

            final AddAddressPage addAddressPage = tc.getPage(AddAddressPage.class);
            final AddAddressPage.AddAddressPageRequest addAddressPageRequest = new AddAddressPage.AddAddressPageRequest(tc);
            addAddressPage.closeTheAddressWindow();
            addAddressPage.clickOnOverrideQASCheckBox(addAddressPageRequest);
            addAddressPage.clearSuburbDefault(addAddressPageRequest);
            addAddressPage.clearAddressLine2(addAddressPageRequest);
            addAddressPage.clearState(addAddressPageRequest);
            addAddressPage.selectCountry(addAddressPageRequest);
            addAddressPage.inputAddressLine1(addAddressPageRequest);
            addAddressPage.enterSuburbForDefaultWidget(addAddressPageRequest);
            addAddressPage.enterPostcodeForDefaultWidget(addAddressPageRequest);
            addAddressPage.clickOkOnAddressPage(addAddressPageRequest);

            final PartyDetailsPage partyDetailsPage = tc.getPage(PartyDetailsPage.class);
            final PartyDetailsPage.PartyDetailsPageRequest partyDetailsPageRequest = new PartyDetailsPage.PartyDetailsPageRequest(tc);
            Assert.assertEquals(partyDetailsPageRequest.getPartyAddress(), partyDetailsPage.getPartyAddress(partyDetailsPageRequest));
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = "regression.uk")
    public void inputAllAddressFields(final Map<String, String> testData) {
        doTest("inputDefaultAllAddressFields", "Verify Incident Address All Fields on UK and NZ", testData, tc -> {
            navigateToAddressForCreateParty(tc);

            final AddAddressPage addAddressPage = tc.getPage(AddAddressPage.class);
            final AddAddressPage.AddAddressPageRequest addAddressPageRequest = new AddAddressPage.AddAddressPageRequest(tc);
            addAddressPage.closeTheAddressWindow();
            addAddressPage.clickOnOverrideQASCheckBox(addAddressPageRequest);
            addAddressPage.selectCountry(addAddressPageRequest);
            addAddressPage.inputAddressLine1(addAddressPageRequest);
            addAddressPage.inputAddressLine2(addAddressPageRequest);
            addAddressPage.enterStateForDefaultWidget(addAddressPageRequest);
            addAddressPage.enterSuburbForDefaultWidget(addAddressPageRequest);
            addAddressPage.enterPostcodeForDefaultWidget(addAddressPageRequest);
            addAddressPage.clickOkOnAddressPage(addAddressPageRequest);

            final PartyDetailsPage partyDetailsPage = tc.getPage(PartyDetailsPage.class);
            final PartyDetailsPage.PartyDetailsPageRequest partyDetailsPageRequest = new PartyDetailsPage.PartyDetailsPageRequest(tc);
            Assert.assertEquals(partyDetailsPageRequest.getPartyAddress(), partyDetailsPage.getPartyAddress(partyDetailsPageRequest));
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = "regression.uk")
    public void verifyAddressValidationError(final Map<String, String> testData) {
        doTest("inputDefaultAllAddressFields", "Verify Incident Address All Fields on UK and NZ", testData, tc -> {
            navigateToAddressForCreateParty(tc);

            final AddAddressPage addAddressPage = tc.getPage(AddAddressPage.class);
            final AddAddressPage.AddAddressPageRequest addAddressPageRequest = new AddAddressPage.AddAddressPageRequest(tc);
            addAddressPage.closeTheAddressWindow();
            addAddressPage.clickOnOverrideQASCheckBox(addAddressPageRequest);
            addAddressPage.clearAddressLine1(addAddressPageRequest);
            addAddressPage.clearAddressLine2(addAddressPageRequest);
            addAddressPage.clearPostcodeDefault(addAddressPageRequest);
            addAddressPage.clearState(addAddressPageRequest);
            addAddressPage.clearSuburbDefault(addAddressPageRequest);
            addAddressPage.clickOkOnAddressPage(addAddressPageRequest);
            Assert.assertEquals(addAddressPage.getValidationError(addAddressPageRequest), addAddressPageRequest.getValError());
            addAddressPage.closeValidationError(addAddressPageRequest);
            addAddressPage.clickCancel(addAddressPageRequest);
        });
    }

    private void navigateToAddressForCreateParty(final TestCaseContext tc) {
        final PartySearchPage partySearchPage = tc.getPage(PartySearchPage.class);
        final PartySearchPage.PartySearchPageRequest partySearchPageRequest = new PartySearchPage.PartySearchPageRequest(tc);
        partySearchPage.clickSidebarPartiesLink(partySearchPageRequest);
        partySearchPage.inputSearchString(partySearchPageRequest);
        partySearchPage.clickSearch(partySearchPageRequest);

        final PartyDetailsPage partyDetailsPage = tc.getPage(PartyDetailsPage.class);
        final PartyDetailsPage.PartyDetailsPageRequest partyDetailsPageRequest = new PartyDetailsPage.PartyDetailsPageRequest(tc);
        partyDetailsPage.clickEditAddressForPartyBtn(partyDetailsPageRequest);
        partyDetailsPage.clickEditAddressConfirmationYes(partyDetailsPageRequest);

    }
}
