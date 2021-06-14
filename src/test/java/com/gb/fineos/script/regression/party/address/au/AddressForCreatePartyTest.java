package com.gb.fineos.script.regression.party.address.au;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.sharedpages.partymanager.partymaintenance.AddAddressPage;
import com.gb.fineos.page.sharedpages.partymanager.partymaintenance.AddPersonPage;
import com.gb.fineos.page.sharedpages.partymanager.partysearch.PartySearchPage;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.screencapture.ScreenCapture;
import com.gb.fineos.script.utils.BaseTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class AddressForCreatePartyTest extends BaseTest {
    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = "regression.au")
    public void inputAddressMandatoryFieldsOnly(final Map<String, String> testData) {
        doTest("inputDefaultIncidentAddressMandatoryFieldsOnly", "Verify Incident Address Fields on AU create Party", testData, tc -> {
            navigateToAddressForCreateParty(tc);

            final AddAddressPage addAddressPage = tc.getPage(AddAddressPage.class);
            final AddAddressPage.AddAddressPageRequest addAddressPageRequest = new AddAddressPage.AddAddressPageRequest(tc);
            addAddressPage.closeTheAddressWindow();
            addAddressPage.clickOnOverrideQASCheckBox(addAddressPageRequest);
            addAddressPage.selectCountry(addAddressPageRequest);
            addAddressPage.enterPostcode(addAddressPageRequest);
            addAddressPage.enterStreetText(addAddressPageRequest);
            addAddressPage.enterSuburb(addAddressPageRequest);
            addAddressPage.selectState(addAddressPageRequest);
            addAddressPage.selectStreetType(addAddressPageRequest);
            addAddressPage.clickOkOnAddressPage(addAddressPageRequest);

            final AddPersonPage addPersonPage = tc.getPage(AddPersonPage.class);
            final AddPersonPage.AddPersonPageRequest addPersonPageRequest = new AddPersonPage.AddPersonPageRequest(tc);
            Assert.assertEquals(addPersonPageRequest.getAddressLabel(), addPersonPage.getAddressLabel(addPersonPageRequest));
            addPersonPage.clickCancel(addPersonPageRequest);
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = "regression.au")
    public void inputAllAddressFields(final Map<String, String> testData) {
        doTest("inputDefaultAllAddressFields", "Verify Incident Address All Fields on AU", testData, tc -> {
            navigateToAddressForCreateParty(tc);

            final AddAddressPage addAddressPage = tc.getPage(AddAddressPage.class);
            final AddAddressPage.AddAddressPageRequest addAddressPageRequest = new AddAddressPage.AddAddressPageRequest(tc);
            addAddressPage.closeTheAddressWindow();
            addAddressPage.clickOnOverrideQASCheckBox(addAddressPageRequest);
            addAddressPage.selectCountry(addAddressPageRequest);
            addAddressPage.selectState(addAddressPageRequest);
            addAddressPage.selectStreetType(addAddressPageRequest);
            addAddressPage.selectUnitType(addAddressPageRequest);
            addAddressPage.selectFloorType(addAddressPageRequest);
            addAddressPage.enterSuburb(addAddressPageRequest);
            addAddressPage.enterStreetText(addAddressPageRequest);
            addAddressPage.enterPostcode(addAddressPageRequest);
            addAddressPage.inputUnitNumber(addAddressPageRequest);
            addAddressPage.inputFloorNumber(addAddressPageRequest);
            addAddressPage.enterPremiseNumberText(addAddressPageRequest);
            addAddressPage.clickOkOnAddressPage(addAddressPageRequest);

            final AddPersonPage addPersonPage = tc.getPage(AddPersonPage.class);
            final AddPersonPage.AddPersonPageRequest addPersonPageRequest = new AddPersonPage.AddPersonPageRequest(tc);
            Assert.assertEquals(addPersonPageRequest.getAddressLabel(), addPersonPage.getAddressLabel(addPersonPageRequest));
            addPersonPage.clickCancel(addPersonPageRequest);
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = "regression.au")
    public void verifyAddressValidationError(final Map<String, String> testData) {
        doTest("inputDefaultAllAddressFields", "Verify Incident Address All Fields on AU", testData, tc -> {
            navigateToAddressForCreateParty(tc);

            final AddAddressPage addAddressPage = tc.getPage(AddAddressPage.class);
            final AddAddressPage.AddAddressPageRequest addAddressPageRequest = new AddAddressPage.AddAddressPageRequest(tc);
            addAddressPage.closeTheAddressWindow();
            addAddressPage.clickOnOverrideQASCheckBox(addAddressPageRequest);
            addAddressPage.clickOkOnAddressPage(addAddressPageRequest);
            Assert.assertEquals(addAddressPage.getValidationError(addAddressPageRequest), addAddressPageRequest.getValError());
            ScreenCapture.logScreenshot("ADDRESS VALIDATION ERROR", LogStatus.INFO);
            addAddressPage.closeValidationError(addAddressPageRequest);
            addAddressPage.clickCancel(addAddressPageRequest);

            final AddPersonPage addPersonPage = tc.getPage(AddPersonPage.class);
            final AddPersonPage.AddPersonPageRequest addPersonPageRequest = new AddPersonPage.AddPersonPageRequest(tc);
            addPersonPage.clickCancel(addPersonPageRequest);
        });
    }

    private void navigateToAddressForCreateParty(final TestCaseContext tc) {
        final PartySearchPage partySearchPage = tc.getPage(PartySearchPage.class);
        final PartySearchPage.PartySearchPageRequest partySearchPageRequest = new PartySearchPage.PartySearchPageRequest(tc);
        partySearchPage.clickSidebarPartiesLink(partySearchPageRequest);
        partySearchPage.clickSearch(partySearchPageRequest);
        partySearchPage.clickAddNewPartyButton(partySearchPageRequest);

        final AddPersonPage addPersonPage = tc.getPage(AddPersonPage.class);
        final AddPersonPage.AddPersonPageRequest addPersonPageRequest = new AddPersonPage.AddPersonPageRequest(tc);
        addPersonPage.clickOnNewButtonToAddAddress(addPersonPageRequest);
        addPersonPage.setAlertSelectAddressType(addPersonPageRequest);
        addPersonPage.clickOkToConfirmAddAddress(addPersonPageRequest);

    }
}
