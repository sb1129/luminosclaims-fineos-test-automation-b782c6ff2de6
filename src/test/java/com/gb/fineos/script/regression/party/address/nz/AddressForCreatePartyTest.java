package com.gb.fineos.script.regression.party.address.nz;

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
    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = "regression.nz")
    public void inputAddressMandatoryFieldsOnly(final Map<String, String> testData) {
        doTest("inputDefaultIncidentAddressMandatoryFieldsOnly", "Verify Incident Address Fields on UK and NZ create Party", testData, tc -> {
            navigateToAddressForCreateParty(tc);

            final AddAddressPage addAddressPage = tc.getPage(AddAddressPage.class);
            final AddAddressPage.AddAddressPageRequest addAddressPageRequest = new AddAddressPage.AddAddressPageRequest(tc);
            addAddressPage.closeTheAddressWindow();
            addAddressPage.clickOnOverrideQASCheckBox(addAddressPageRequest);
            addAddressPage.selectCountry(addAddressPageRequest);
            addAddressPage.inputAddressLine1(addAddressPageRequest);
            addAddressPage.enterSuburbForDefaultWidget(addAddressPageRequest);
            addAddressPage.enterPostcodeForDefaultWidget(addAddressPageRequest);
            addAddressPage.clickOkOnAddressPage(addAddressPageRequest);

            final AddPersonPage addPersonPage = tc.getPage(AddPersonPage.class);
            final AddPersonPage.AddPersonPageRequest addPersonPageRequest = new AddPersonPage.AddPersonPageRequest(tc);
            Assert.assertEquals(addPersonPageRequest.getAddressLabel(), addPersonPage.getAddressLabel(addPersonPageRequest));
            addPersonPage.clickCancel(addPersonPageRequest);
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = "regression.nz")
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

            final AddPersonPage addPersonPage = tc.getPage(AddPersonPage.class);
            final AddPersonPage.AddPersonPageRequest addPersonPageRequest = new AddPersonPage.AddPersonPageRequest(tc);
            Assert.assertEquals(addPersonPageRequest.getAddressLabel(), addPersonPage.getAddressLabel(addPersonPageRequest));
            addPersonPage.clickCancel(addPersonPageRequest);
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = "regression.nz")
    public void verifyAddressValidationError(final Map<String, String> testData) {
        doTest("inputDefaultAllAddressFields", "Verify Incident Address All Fields on UK and NZ", testData, tc -> {
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
