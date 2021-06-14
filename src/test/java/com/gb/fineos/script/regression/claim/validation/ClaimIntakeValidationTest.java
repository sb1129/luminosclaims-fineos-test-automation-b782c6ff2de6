package com.gb.fineos.script.regression.claim.validation;

import com.gb.fineos.domain.ClaimType;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.domain.TestInstance;
import com.gb.fineos.page.claims.motoraccident.GbAddInsuredVehiclePage;
import com.gb.fineos.page.sharedpages.casemanager.casealias.AddCaseAliasPage;
import com.gb.fineos.page.sharedpages.casemanager.casecreation.CaseCreationWizardPage;
import com.gb.fineos.page.sharedpages.casemanager.casecreation.CaseCreationWizardPage.CaseCreationWizardPageRequest;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageNotificationBase;
import com.gb.fineos.page.sharedpages.casemanager.partycaseroles.AddPartyCaseRolePage;
import com.gb.fineos.page.sharedpages.workmanager.scripting.ProcessStatusPageClaimBase;
import com.gb.fineos.page.sharedpages.workmanager.scripting.uk.ClaimIntakeProcessStatusPage;
import com.gb.fineos.page.utils.CaseUtils;
import com.gb.fineos.page.utils.FineosDSL;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.screencapture.ScreenCapture;
import com.gb.fineos.script.utils.BaseTest;
import com.gb.fineos.script.utils.TestUtils;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ClaimIntakeValidationTest extends BaseTest {

    private static final int POLICE_INCIDENT_NUMBER = 2;
    private static final String LINKED_PARTIES_VALIDATION = "LINKED PARTIES - VALIDATION";
    private static final String CLAIM_INTAKE_VALIDATION = "CLAIM INTAKE - VALIDATION";
    private static final String FIRST_EXPECTED_VALIDATION_MESSAGE = "ClaimType_FirstExpectedValidationMessage";
    private static final String SECOND_EXPECTED_VALIDATION_MESSAGE = "ClaimType_SecondExpectedValidationMessage";
    private static final String THIRD_EXPECTED_VALIDATION_MESSAGE = "ClaimType_ThirdExpectedValidationMessage";
    private static final String LINKED_PARTIES_VALIDATION_MESSAGE = "LinkedPartiesExpectedValidationMessage";
    private static final String CLAIM_INTAKE_EXPECTED_VALIDATION_MESSAGE = "ClaimIntakeStepOneExpectedValidationMessage";


    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.nz", "regression.icare", "validation.uk"})
    public void claimIntakeValidation(final Map<String, String> testData) {
        doTest("Claim Validation", "Validate claim intake data against claim " + testData.get("CLM_CaseAliasValue"), testData, tc ->
            FineosDSL.getInstance(tc)
                .getClaim()
                .withImplementation(this::performClaimCreationValidationTest)
                .test());
    }

    private void performClaimCreationValidationTest(final TestCaseContext tc) {
        stepToClaimIntakePage(tc);
        addClaimAndValidateMessages(tc);
    }

    private void stepToClaimIntakePage(final TestCaseContext tc) {
        // Create on create claim
        final DisplayCasePageNotificationBase displayCasePageNotificationBase = CaseUtils.getDisplayCasePageNotification(tc);
        displayCasePageNotificationBase.doClickOnCreateClaim(CaseUtils.getDisplayCasePageNotificationRequest(tc));

        // Click on add button from case creation page
        final CaseCreationWizardPage caseCreationWizardPage = tc.getPage(CaseCreationWizardPage.class);
        final CaseCreationWizardPageRequest caseCreationWizardPageRequest = new CaseCreationWizardPageRequest(tc);

        // Validate required party for those requiring it
        List<String> expectedLinkedPartyValidationMessages = caseCreationWizardPageRequest.getExpectedValidationMessages(LINKED_PARTIES_VALIDATION_MESSAGE, ";");
        ClaimType claimType = caseCreationWizardPageRequest.getClaimType();

        if (claimType.equals(ClaimType.MOTOR_CLAIM)) {
            caseCreationWizardPage.clickOkButton(caseCreationWizardPageRequest);
            List<String> actualLinkedPartyValidationMessages = caseCreationWizardPage.getValidationMessages(caseCreationWizardPageRequest, caseCreationWizardPage.getErrorValidationText(caseCreationWizardPageRequest));
            TestUtils.validateErrorMessages(tc, expectedLinkedPartyValidationMessages, actualLinkedPartyValidationMessages, LogStatus.FAIL);
            ScreenCapture.logScreenshot(LINKED_PARTIES_VALIDATION, LogStatus.INFO);
        }


        // Adding a claimant
        caseCreationWizardPage.clickAddLinkedParty(caseCreationWizardPageRequest);
        tc.getPage(AddPartyCaseRolePage.class).doLinkAClaimantParty(new AddPartyCaseRolePage.AddPartyCaseRolePageRequest(tc));
        caseCreationWizardPage.clickOkButton(caseCreationWizardPageRequest);

    }

    private void addClaimAndValidateMessages(final TestCaseContext tc) {
        // Adding case and claim details
        final ProcessStatusPageClaimBase claimIntakeProcessStatusPage = CaseUtils.getProcessStatusPageClaim(tc);
        final ProcessStatusPageClaimBase.ProcessStatusPageClaimBaseRequest claimIntakeProcessStatusPageRequest = new ProcessStatusPageClaimBase.ProcessStatusPageClaimBaseRequest(tc);


        List<String> expectedValidationMessages;
        List<String> actualValidationMessages;

        // Validate Step One Claim Intake
        expectedValidationMessages = claimIntakeProcessStatusPageRequest.getExpectedValidationMessages(CLAIM_INTAKE_EXPECTED_VALIDATION_MESSAGE, ";");
        ClaimType claimType = claimIntakeProcessStatusPageRequest.getClaimType();

        claimIntakeProcessStatusPage.clickNextButton(claimIntakeProcessStatusPageRequest);
        actualValidationMessages = claimIntakeProcessStatusPage.getValidationMessages(claimIntakeProcessStatusPageRequest, claimIntakeProcessStatusPage.getErrorValidationText(claimIntakeProcessStatusPageRequest));
        TestUtils.validateErrorMessages(tc, expectedValidationMessages, actualValidationMessages, LogStatus.FAIL);
        ScreenCapture.logScreenshot(CLAIM_INTAKE_VALIDATION, LogStatus.INFO);


        claimIntakeProcessStatusPage.enterClaimCaseDetailsDescription(claimIntakeProcessStatusPageRequest);
        claimIntakeProcessStatusPage.enterClaimReceiveDate(claimIntakeProcessStatusPageRequest);

        if (tc.isTestInstance(TestInstance.UK)) {
            ((ClaimIntakeProcessStatusPage) claimIntakeProcessStatusPage)
                .selectClaimReceiveDateTimeZoneWithIndex(claimIntakeProcessStatusPageRequest);
        } else {
            claimIntakeProcessStatusPage.selectClaimReceiveDateTimeZone(claimIntakeProcessStatusPageRequest);
        }

        claimIntakeProcessStatusPage.selectingClientSpecificData(claimIntakeProcessStatusPageRequest);

        final AddCaseAliasPage addCaseAliasPage = tc.getPage(AddCaseAliasPage.class);
        final AddCaseAliasPage.AddCaseAliasPageRequest addCaseAliasPageRequest = new AddCaseAliasPage.AddCaseAliasPageRequest(tc);

        // Entering details of motor, personal injury and property damage claims

        switch (claimType) {
            case MOTOR_CLAIM:
                claimIntakeProcessStatusPage.doAddGeneralIncidentDetails(claimIntakeProcessStatusPageRequest);

                if (tc.isTestInstance(TestInstance.ICARE)) {
                    Optional.of(claimIntakeProcessStatusPageRequest)
                        .filter(req -> tc.isTestInstance(TestInstance.ICARE))
                        .ifPresent(claimIntakeProcessStatusPage::doAddCostCentre);

                    claimIntakeProcessStatusPage.clickCaseAliasAddButton(claimIntakeProcessStatusPageRequest);
                    addCaseAliasPage.selectAliasTypePoliceIncidentNumber(addCaseAliasPageRequest, POLICE_INCIDENT_NUMBER);
                    addCaseAliasPage.enterPoliceIncidentNumber(addCaseAliasPageRequest);
                    addCaseAliasPage.clickOkButton(addCaseAliasPageRequest);
                }

                claimIntakeProcessStatusPage.clickOnNextButton(claimIntakeProcessStatusPageRequest);

                claimIntakeProcessStatusPage.clickOnNextButton(claimIntakeProcessStatusPageRequest);
                expectedValidationMessages = claimIntakeProcessStatusPageRequest.getExpectedValidationMessages(FIRST_EXPECTED_VALIDATION_MESSAGE, ";");
                actualValidationMessages = claimIntakeProcessStatusPage.getValidationMessages(claimIntakeProcessStatusPageRequest, claimIntakeProcessStatusPage.getErrorValidationText(claimIntakeProcessStatusPageRequest));
                TestUtils.validateErrorMessages(tc, expectedValidationMessages, actualValidationMessages, LogStatus.FAIL);
                ScreenCapture.logScreenshot(CLAIM_INTAKE_VALIDATION, LogStatus.INFO);

                // Perform validation
                claimIntakeProcessStatusPage.clickOnVehiclesInvolvedAddButton(claimIntakeProcessStatusPageRequest);
                if (claimIntakeProcessStatusPageRequest.getInsuredThirdPartyVehicle().equalsIgnoreCase("Insured Vehicle")) {
                    claimIntakeProcessStatusPage.clickOnInsuredVehicleTypeRadioButton(claimIntakeProcessStatusPageRequest);
                } else {
                    claimIntakeProcessStatusPage.clickOnThirdPartyVehicleRadioButton(claimIntakeProcessStatusPageRequest);
                }
                claimIntakeProcessStatusPage.clickOnVehicleTypePopupWidgetOKButton(claimIntakeProcessStatusPageRequest);
                (tc.getPage(GbAddInsuredVehiclePage.class)).clickOnOKButton(new GbAddInsuredVehiclePage.GbAddInsuredVehiclePageRequest(tc));

                // Perform validation on vehicle
                expectedValidationMessages = claimIntakeProcessStatusPageRequest.getExpectedValidationMessages(SECOND_EXPECTED_VALIDATION_MESSAGE, ";");
                actualValidationMessages = claimIntakeProcessStatusPage.getValidationMessages(claimIntakeProcessStatusPageRequest, claimIntakeProcessStatusPage.getErrorValidationText(claimIntakeProcessStatusPageRequest));
                TestUtils.validateErrorMessages(tc, expectedValidationMessages, actualValidationMessages, LogStatus.FAIL);
                ScreenCapture.logScreenshot(CLAIM_INTAKE_VALIDATION, LogStatus.INFO);

                claimIntakeProcessStatusPage.doAddInsuredVehicleDetails(claimIntakeProcessStatusPageRequest);

                claimIntakeProcessStatusPage.clickOnNextButton(claimIntakeProcessStatusPageRequest);
                break;
            case PERSONAL_INJURY_CLAIM:
            case MISC_INJURY_CLAIM:
                if (claimIntakeProcessStatusPage.isElementPresent(claimIntakeProcessStatusPage.getInjuryCodeSearch())) {
                    claimIntakeProcessStatusPage.clickOnInjuryCodeSearch(claimIntakeProcessStatusPageRequest);

                    // Perform injury code search validations
                    claimIntakeProcessStatusPage.clickSearchPageOkButton(claimIntakeProcessStatusPageRequest);
                    expectedValidationMessages = claimIntakeProcessStatusPageRequest.getExpectedValidationMessages(FIRST_EXPECTED_VALIDATION_MESSAGE, ";");
                    actualValidationMessages = claimIntakeProcessStatusPage.getValidationMessages(claimIntakeProcessStatusPageRequest, claimIntakeProcessStatusPage.getErrorValidationText(claimIntakeProcessStatusPageRequest));
                    TestUtils.validateErrorMessages(tc, expectedValidationMessages, actualValidationMessages, LogStatus.ERROR);
                    ScreenCapture.logScreenshot(CLAIM_INTAKE_VALIDATION, LogStatus.INFO);

                    claimIntakeProcessStatusPage.clickOnInjuryCodeSearchButton(claimIntakeProcessStatusPageRequest);
                    expectedValidationMessages = claimIntakeProcessStatusPageRequest.getExpectedValidationMessages(SECOND_EXPECTED_VALIDATION_MESSAGE, ";");
                    actualValidationMessages = claimIntakeProcessStatusPage.getValidationMessages(claimIntakeProcessStatusPageRequest, claimIntakeProcessStatusPage.getErrorValidationText(claimIntakeProcessStatusPageRequest));
                    TestUtils.validateErrorMessages(tc, expectedValidationMessages, actualValidationMessages, LogStatus.ERROR);
                    ScreenCapture.logScreenshot(CLAIM_INTAKE_VALIDATION, LogStatus.INFO);
                }
                claimIntakeProcessStatusPage.enterInjuryCodeText(claimIntakeProcessStatusPageRequest);

                claimIntakeProcessStatusPage.clickOnInjuryCodeSearchButton(claimIntakeProcessStatusPageRequest);
                Optional.of(claimIntakeProcessStatusPageRequest)
                    .filter(req -> tc.isTestInstance(TestInstance.ICARE))
                    .ifPresent(claimIntakeProcessStatusPage::doAddCostCentre);
                claimIntakeProcessStatusPage.clickOnNextButton(claimIntakeProcessStatusPageRequest);

                // Perform Injury validation
                claimIntakeProcessStatusPage.clickOnNextButton(claimIntakeProcessStatusPageRequest);
                expectedValidationMessages = claimIntakeProcessStatusPageRequest.getExpectedValidationMessages(THIRD_EXPECTED_VALIDATION_MESSAGE, ";");
                actualValidationMessages = claimIntakeProcessStatusPage.getValidationMessages(claimIntakeProcessStatusPageRequest, claimIntakeProcessStatusPage.getErrorValidationText(claimIntakeProcessStatusPageRequest));
                TestUtils.validateErrorMessages(tc, expectedValidationMessages, actualValidationMessages, LogStatus.ERROR);
                ScreenCapture.logScreenshot(CLAIM_INTAKE_VALIDATION, LogStatus.INFO);

                claimIntakeProcessStatusPage.doAddInjuryCodes(claimIntakeProcessStatusPageRequest);

                claimIntakeProcessStatusPage.clickOnNextButton(claimIntakeProcessStatusPageRequest);
                break;
            case PROPERTY_DAMAGE_CLAIM:
            case PROPERTY_CLAIM:
            case MISC_PROPERTY_CLAIM:
                Optional.of(claimIntakeProcessStatusPageRequest)
                    .filter(req -> tc.isTestInstance(TestInstance.ICARE))
                    .ifPresent(claimIntakeProcessStatusPage::doAddCostCentre);
                claimIntakeProcessStatusPage.clickOnNextButton(claimIntakeProcessStatusPageRequest);

                // Perform Validations
                claimIntakeProcessStatusPage.clickOnNextButton(claimIntakeProcessStatusPageRequest);
                expectedValidationMessages = claimIntakeProcessStatusPageRequest.getExpectedValidationMessages(FIRST_EXPECTED_VALIDATION_MESSAGE, ";");
                actualValidationMessages = claimIntakeProcessStatusPage.getValidationMessages(claimIntakeProcessStatusPageRequest, claimIntakeProcessStatusPage.getErrorValidationText(claimIntakeProcessStatusPageRequest));
                TestUtils.validateErrorMessages(tc, expectedValidationMessages, actualValidationMessages, LogStatus.ERROR);
                ScreenCapture.logScreenshot(CLAIM_INTAKE_VALIDATION, LogStatus.INFO);

                claimIntakeProcessStatusPage.doAddPropertyDamageGeneralDetails(claimIntakeProcessStatusPageRequest);

                claimIntakeProcessStatusPage.clickOnItemDetailsAddButton(claimIntakeProcessStatusPageRequest);

                // Perform item detail validations
                claimIntakeProcessStatusPage.clickOnOKButton(claimIntakeProcessStatusPageRequest);
                expectedValidationMessages = claimIntakeProcessStatusPageRequest.getExpectedValidationMessages(SECOND_EXPECTED_VALIDATION_MESSAGE, ";");
                actualValidationMessages = claimIntakeProcessStatusPage.getValidationMessages(claimIntakeProcessStatusPageRequest, claimIntakeProcessStatusPage.getErrorValidationText(claimIntakeProcessStatusPageRequest));
                TestUtils.validateErrorMessages(tc, expectedValidationMessages, actualValidationMessages, LogStatus.ERROR);
                ScreenCapture.logScreenshot(CLAIM_INTAKE_VALIDATION, LogStatus.INFO);

                claimIntakeProcessStatusPage.enterPropertyItemDescription(claimIntakeProcessStatusPageRequest);
                claimIntakeProcessStatusPage.selectPropertyItemType(claimIntakeProcessStatusPageRequest);
                claimIntakeProcessStatusPage.selectPropertyCategory(claimIntakeProcessStatusPageRequest);
                claimIntakeProcessStatusPage.clickOnOKButton(claimIntakeProcessStatusPageRequest);

                claimIntakeProcessStatusPage.clickOnNextButton(claimIntakeProcessStatusPageRequest);
                break;
            case HEALTH_CLAIM:
                // Perform injury claim validations
                claimIntakeProcessStatusPage.clickOnNextButton(claimIntakeProcessStatusPageRequest);
                expectedValidationMessages = claimIntakeProcessStatusPageRequest.getExpectedValidationMessages(FIRST_EXPECTED_VALIDATION_MESSAGE, ";");
                actualValidationMessages = claimIntakeProcessStatusPage.getValidationMessages(claimIntakeProcessStatusPageRequest, claimIntakeProcessStatusPage.getErrorValidationText(claimIntakeProcessStatusPageRequest));
                TestUtils.validateErrorMessages(tc, expectedValidationMessages, actualValidationMessages, LogStatus.ERROR);
                ScreenCapture.logScreenshot(CLAIM_INTAKE_VALIDATION, LogStatus.INFO);

                claimIntakeProcessStatusPage.doAddingHealthInjuryCaseDetails(claimIntakeProcessStatusPageRequest);
                Optional.of(claimIntakeProcessStatusPageRequest)
                    .filter(req -> tc.isTestInstance(TestInstance.ICARE))
                    .ifPresent(claimIntakeProcessStatusPage::doAddCostCentre);
                claimIntakeProcessStatusPage.clickOnNextButton(claimIntakeProcessStatusPageRequest);

                // Perform additional injury claim validations (injury)
                claimIntakeProcessStatusPage.clickOnNextButton(claimIntakeProcessStatusPageRequest);
                expectedValidationMessages = claimIntakeProcessStatusPageRequest.getExpectedValidationMessages(SECOND_EXPECTED_VALIDATION_MESSAGE, ";");
                actualValidationMessages = claimIntakeProcessStatusPage.getValidationMessages(claimIntakeProcessStatusPageRequest, claimIntakeProcessStatusPage.getErrorValidationText(claimIntakeProcessStatusPageRequest));
                TestUtils.validateErrorMessages(tc, expectedValidationMessages, actualValidationMessages, LogStatus.ERROR);
                ScreenCapture.logScreenshot(CLAIM_INTAKE_VALIDATION, LogStatus.INFO);

                claimIntakeProcessStatusPage.doAddCauseOfInjuryCode(claimIntakeProcessStatusPageRequest);
                claimIntakeProcessStatusPage.clickOnNextButton(claimIntakeProcessStatusPageRequest);

                // Perform additional injury claim validations (injury)
                claimIntakeProcessStatusPage.clickOnNextButton(claimIntakeProcessStatusPageRequest);
                expectedValidationMessages = claimIntakeProcessStatusPageRequest.getExpectedValidationMessages(THIRD_EXPECTED_VALIDATION_MESSAGE, ";");
                actualValidationMessages = claimIntakeProcessStatusPage.getValidationMessages(claimIntakeProcessStatusPageRequest, claimIntakeProcessStatusPage.getErrorValidationText(claimIntakeProcessStatusPageRequest));
                TestUtils.validateErrorMessages(tc, expectedValidationMessages, actualValidationMessages, LogStatus.ERROR);
                ScreenCapture.logScreenshot(CLAIM_INTAKE_VALIDATION, LogStatus.INFO);

                claimIntakeProcessStatusPage.doAddInjuryCodes(claimIntakeProcessStatusPageRequest);

                claimIntakeProcessStatusPage.clickOnNextButton(claimIntakeProcessStatusPageRequest);
                break;
            default:
                tc.error("ERROR", "Claim type is not present in Test data.");
        }
    }

}
