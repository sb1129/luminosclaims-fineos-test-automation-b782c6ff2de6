package com.gb.fineos.script.regression.party.address.icare;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.sharedpages.partymanager.partydetails.PartyDetailsPage;
import com.gb.fineos.page.sharedpages.partymanager.partymaintenance.AddAddressPage;
import com.gb.fineos.page.sharedpages.partymanager.partysearch.PartySearchPage;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.screencapture.ScreenCapture;
import com.gb.fineos.script.utils.BaseTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class AddressForEditPartyTest extends BaseTest {
    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = "regression.icare")
    public void inputAddressMandatoryFieldsOnly(final Map<String, String> testData) {
        doTest("inputDefaultIncidentAddressMandatoryFieldsOnly", "Verify Incident Address Fields on ICARE create Party", testData, tc -> {
            navigateToAddressForCreateParty(tc);

            final AddAddressPage addAddressPage = tc.getPage(AddAddressPage.class);
            final AddAddressPage.AddAddressPageRequest addAddressPageRequest = new AddAddressPage.AddAddressPageRequest(tc);
            addAddressPage.closeTheAddressWindow();
            addAddressPage.clickOnOverrideQASCheckBox(addAddressPageRequest);
            addAddressPage.clearUnitNumber(addAddressPageRequest);
            addAddressPage.clearFloorNumber(addAddressPageRequest);
            addAddressPage.selectUnitType(addAddressPageRequest);
            addAddressPage.selectFloorType(addAddressPageRequest);
            addAddressPage.clearBuildingName1(addAddressPageRequest);
            addAddressPage.clearBuildingName2(addAddressPageRequest);
            addAddressPage.clearDeliveryPointID(addAddressPageRequest);
            addAddressPage.clearLotNumber(addAddressPageRequest);
            addAddressPage.clearPostalNumber(addAddressPageRequest);
            addAddressPage.selectPostalType(addAddressPageRequest);
            addAddressPage.clearPremiseNumber(addAddressPageRequest);
            addAddressPage.selectCountry(addAddressPageRequest);
            addAddressPage.enterPostcode(addAddressPageRequest);
            addAddressPage.enterStreetText(addAddressPageRequest);
            addAddressPage.enterSuburb(addAddressPageRequest);
            addAddressPage.selectState(addAddressPageRequest);
            addAddressPage.selectStreetType(addAddressPageRequest);
            addAddressPage.clickOkOnAddressPage(addAddressPageRequest);

            final PartyDetailsPage partyDetailsPage = tc.getPage(PartyDetailsPage.class);
            final PartyDetailsPage.PartyDetailsPageRequest partyDetailsPageRequest = new PartyDetailsPage.PartyDetailsPageRequest(tc);
            Assert.assertEquals(partyDetailsPageRequest.getPartyAddress(), partyDetailsPage.getPartyAddress(partyDetailsPageRequest));
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = "regression.icare")
    public void inputAllAddressFields(final Map<String, String> testData) {
        doTest("inputDefaultAllAddressFields", "Verify Incident Address All Fields on ICARE", testData, tc -> {
            navigateToAddressForCreateParty(tc);

            final AddAddressPage addAddressPage = tc.getPage(AddAddressPage.class);
            final AddAddressPage.AddAddressPageRequest addAddressPageRequest = new AddAddressPage.AddAddressPageRequest(tc);
            addAddressPage.closeTheAddressWindow();
            addAddressPage.clickOnOverrideQASCheckBox(addAddressPageRequest);
            addAddressPage.clearBuildingName1(addAddressPageRequest);
            addAddressPage.clearBuildingName2(addAddressPageRequest);
            addAddressPage.clearDeliveryPointID(addAddressPageRequest);
            addAddressPage.clearLotNumber(addAddressPageRequest);
            addAddressPage.clearPostalNumber(addAddressPageRequest);
            addAddressPage.selectPostalType(addAddressPageRequest);
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

            final PartyDetailsPage partyDetailsPage = tc.getPage(PartyDetailsPage.class);
            final PartyDetailsPage.PartyDetailsPageRequest partyDetailsPageRequest = new PartyDetailsPage.PartyDetailsPageRequest(tc);
            Assert.assertEquals(partyDetailsPageRequest.getPartyAddress(), partyDetailsPage.getPartyAddress(partyDetailsPageRequest));
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = "regression.icare")
    public void verifyAddressValidationError(final Map<String, String> testData) {
        doTest("inputDefaultAllAddressFields", "Verify Incident Address All Fields on ICARE", testData, tc -> {
            navigateToAddressForCreateParty(tc);

            final AddAddressPage addAddressPage = tc.getPage(AddAddressPage.class);
            final AddAddressPage.AddAddressPageRequest addAddressPageRequest = new AddAddressPage.AddAddressPageRequest(tc);
            addAddressPage.closeTheAddressWindow();
            addAddressPage.clickOnOverrideQASCheckBox(addAddressPageRequest);
            addAddressPage.clearFloorNumber(addAddressPageRequest);
            addAddressPage.selectFloorType(addAddressPageRequest);
            addAddressPage.selectUnitType(addAddressPageRequest);
            addAddressPage.selectStreetType(addAddressPageRequest);
            addAddressPage.selectState(addAddressPageRequest);
            addAddressPage.clearBuildingName1(addAddressPageRequest);
            addAddressPage.clearBuildingName2(addAddressPageRequest);
            addAddressPage.clearDeliveryPointID(addAddressPageRequest);
            addAddressPage.clearLotNumber(addAddressPageRequest);
            addAddressPage.clearPostalNumber(addAddressPageRequest);
            addAddressPage.selectPostalType(addAddressPageRequest);
            addAddressPage.clearUnitNumber(addAddressPageRequest);
            addAddressPage.clearSuburb(addAddressPageRequest);
            addAddressPage.clearPostcode(addAddressPageRequest);
            addAddressPage.clearPremiseNumber(addAddressPageRequest);
            addAddressPage.clickOkOnAddressPage(addAddressPageRequest);
            Assert.assertEquals(addAddressPage.getValidationError(addAddressPageRequest), addAddressPageRequest.getValError());
            ScreenCapture.logScreenshot("ADDRESS VALIDATION ERROR", LogStatus.INFO);
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
