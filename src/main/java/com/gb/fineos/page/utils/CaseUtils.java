package com.gb.fineos.page.utils;

import com.gb.fineos.domain.ClaimType;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.domain.TestInstance;
import com.gb.fineos.page.sharedpages.casemanager.casealias.AddCaseAliasPage;
import com.gb.fineos.page.sharedpages.casemanager.casealias.AddCaseAliasPage.AddCaseAliasPageRequest;
import com.gb.fineos.page.sharedpages.casemanager.casecreation.CaseCreationWizardPage;
import com.gb.fineos.page.sharedpages.casemanager.casecreation.CaseCreationWizardPage.CaseCreationWizardPageRequest;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageBenefitBase;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageClaimBase;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageClaimBase.DisplayCasePageClaimBaseRequest;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageNotificationBase;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageNotificationBase.DisplayCasePageNotificationBaseRequest;
import com.gb.fineos.page.sharedpages.casemanager.partycaseroles.AddPartyCaseRolePage;
import com.gb.fineos.page.sharedpages.casemanager.partycaseroles.AddPartyCaseRolePage.AddPartyCaseRolePageRequest;
import com.gb.fineos.page.sharedpages.documentmanager.AddExtraDataPage;
import com.gb.fineos.page.sharedpages.documentmanager.AddExtraDataPage.AddExtraDataPageRequest;
import com.gb.fineos.page.sharedpages.location.GBAddLocationAddressPage;
import com.gb.fineos.page.sharedpages.partymanager.partymaintenance.AddOrganisationPartyPage;
import com.gb.fineos.page.sharedpages.partymanager.partymaintenance.AddPersonPage;
import com.gb.fineos.page.sharedpages.partymanager.partysearch.PartySearchPage;
import com.gb.fineos.page.sharedpages.partymanager.partysearch.PartySearchPage.PartySearchPageRequest;
import com.gb.fineos.page.sharedpages.workmanager.ChooseNextProcessStepPage;
import com.gb.fineos.page.sharedpages.workmanager.ChooseNextProcessStepPage.ChooseNextProcessStepPageRequest;
import com.gb.fineos.page.sharedpages.workmanager.TransferToDeptPage;
import com.gb.fineos.page.sharedpages.workmanager.scripting.ProcessStatusPageBase;
import com.gb.fineos.page.sharedpages.workmanager.scripting.ProcessStatusPageBase.ProcessStatusPageBaseRequest;
import com.gb.fineos.page.sharedpages.workmanager.scripting.ProcessStatusPageClaimBase;
import com.gb.fineos.page.sharedpages.workmanager.scripting.ProcessStatusPageClaimBase.ProcessStatusPageClaimBaseRequest;
import com.gb.fineos.page.sharedpages.workmanager.scripting.au.ClaimIntakeProcessStatusPage.ClaimIntakeProcessStatusPageRequest;
import org.apache.log4j.Logger;

import java.util.Optional;

import static com.gb.fineos.domain.CaseType.BENEFIT;
import static com.gb.fineos.domain.CaseType.CLAIM;
import static com.gb.fineos.domain.CaseType.NOTIFICATION;
import static com.gb.fineos.page.utils.ProcessUtils.CASE_STATUS_CLOSED;
import static com.gb.fineos.page.utils.ProcessUtils.CASE_STATUS_PRE_CLOSURE_CHECK;
import static com.gb.fineos.page.utils.ProcessUtils.CASE_STATUS_REOPEN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public final class CaseUtils {
    private static final Logger LOG = Logger.getLogger(CaseUtils.class);

    private static final int POLICE_INCIDENT_NUMBER = 2;
    private static final String LODGE_NOTIFICATION = "Lodge Notification";
    private static final String LODGE_CLAIM = "Lodge Claim";
    private static final String COMPLETE_BENEFIT_DETAILS = "Complete Benefit Details";
    private static final String RECORD_AND_REOPEN_REASON = "Record Re-Open Reason";
    private static final String REOPEN_FOR_ADDITIONAL_PAYMENT = "Re-Open for Additional Expense Payment";


    private CaseUtils() {
        // Do nothing.
    }

    public static ProcessStatusPageBase getNotificationIntakeProcessStatusPage(final TestCaseContext testCase) {
        switch (testCase.getTestInstance()) {
            case AU:
                return testCase.getPage(com.gb.fineos.page.sharedpages.workmanager.scripting.au.NotificationIntakeProcessStatusPage.class);
            case ICARE:
                return testCase.getPage(com.gb.fineos.page.sharedpages.workmanager.scripting.icare.NotificationIntakeProcessStatusPage.class);
            case NZ:
                return testCase.getPage(com.gb.fineos.page.sharedpages.workmanager.scripting.nz.NotificationIntakeProcessStatusPage.class);
            case UK:
                return testCase.getPage(com.gb.fineos.page.sharedpages.workmanager.scripting.uk.NotificationIntakeProcessStatusPage.class);
            default:
                throw new AssertionError("No NotificationIntakeProcessStatusPage class exists for test instance: " + testCase.getTestInstance());
        }
    }

    public static ProcessStatusPageBaseRequest getNotificationIntakeProcessStatusPageRequest(final TestCaseContext testCase) {
        switch (testCase.getTestInstance()) {
            case AU:
                return new com.gb.fineos.page.sharedpages.workmanager.scripting.au.NotificationIntakeProcessStatusPage.NotificationIntakeProcessStatusPageRequest(testCase);
            case ICARE:
                return new com.gb.fineos.page.sharedpages.workmanager.scripting.icare.NotificationIntakeProcessStatusPage.NotificationIntakeProcessStatusPageRequest(testCase);
            case NZ:
                return new com.gb.fineos.page.sharedpages.workmanager.scripting.nz.NotificationIntakeProcessStatusPage.NotificationIntakeProcessStatusPageRequest(testCase);
            case UK:
                return new com.gb.fineos.page.sharedpages.workmanager.scripting.uk.NotificationIntakeProcessStatusPage.NotificationIntakeProcessStatusPageRequest(testCase);
            default:
                throw new AssertionError("No NotificationIntakeProcessStatusPageRequest class exists for test instance: " + testCase.getTestInstance());
        }
    }

    public static ProcessStatusPageClaimBase getProcessStatusPageClaim(final TestCaseContext testCase) {
        switch (testCase.getTestInstance()) {
            case AU:
                return testCase.getPage(com.gb.fineos.page.sharedpages.workmanager.scripting.au.ClaimIntakeProcessStatusPage.class);
            case ICARE:
                return testCase.getPage(com.gb.fineos.page.sharedpages.workmanager.scripting.icare.ClaimIntakeProcessStatusPage.class);
            case NZ:
                return testCase.getPage(com.gb.fineos.page.sharedpages.workmanager.scripting.nz.ClaimIntakeProcessStatusPage.class);
            case UK:
                return testCase.getPage(com.gb.fineos.page.sharedpages.workmanager.scripting.uk.ClaimIntakeProcessStatusPage.class);
            default:
                throw new AssertionError("No NotificationIntakeProcessStatusPage class exists for test instance: " + testCase.getTestInstance());
        }
    }

    public static ProcessStatusPageClaimBaseRequest getProcessStatusPageClaimRequest(final TestCaseContext testCase) {
        switch (testCase.getTestInstance()) {
            case AU:
                return new ClaimIntakeProcessStatusPageRequest(testCase);
            case ICARE:
                return new com.gb.fineos.page.sharedpages.workmanager.scripting.icare.ClaimIntakeProcessStatusPage.ClaimIntakeProcessStatusPageRequest(testCase);
            case NZ:
                return new com.gb.fineos.page.sharedpages.workmanager.scripting.nz.ClaimIntakeProcessStatusPage.ClaimIntakeProcessStatusPageRequest(testCase);
            case UK:
                return new com.gb.fineos.page.sharedpages.workmanager.scripting.uk.ClaimIntakeProcessStatusPage.ClaimIntakeProcessStatusPageRequest(testCase);
            default:
                throw new AssertionError("No ProcessStatusPageClaimBaseRequest class exists for test instance: " + testCase.getTestInstance());
        }
    }

    public static DisplayCasePageNotificationBaseRequest getDisplayCasePageNotificationRequest(final TestCaseContext testCase) {
        switch (testCase.getTestInstance()) {
            case AU:
                return new com.gb.fineos.page.sharedpages.casemanager.displaycase.au.DisplayCasePageNotification.DisplayCasePageNotificationRequest(testCase);
            case ICARE:
                return new com.gb.fineos.page.sharedpages.casemanager.displaycase.icare.DisplayCasePageNotification.DisplayCasePageNotificationRequest(testCase);
            case NZ:
                return new com.gb.fineos.page.sharedpages.casemanager.displaycase.nz.DisplayCasePageNotification.DisplayCasePageNotificationRequest(testCase);
            case UK:
                return new com.gb.fineos.page.sharedpages.casemanager.displaycase.uk.DisplayCasePageNotification.DisplayCasePageNotificationRequest(testCase);
            default:
                throw new AssertionError("No DisplayCasePageNotificationRequest class exists for test instance: " + testCase.getTestInstance());
        }
    }

    public static DisplayCasePageNotificationBase getDisplayCasePageNotification(final TestCaseContext testCase) {
        switch (testCase.getTestInstance()) {
            case AU:
                return testCase.getPage(com.gb.fineos.page.sharedpages.casemanager.displaycase.au.DisplayCasePageNotification.class);
            case ICARE:
                return testCase.getPage(com.gb.fineos.page.sharedpages.casemanager.displaycase.icare.DisplayCasePageNotification.class);
            case NZ:
                return testCase.getPage(com.gb.fineos.page.sharedpages.casemanager.displaycase.nz.DisplayCasePageNotification.class);
            case UK:
                return testCase.getPage(com.gb.fineos.page.sharedpages.casemanager.displaycase.uk.DisplayCasePageNotification.class);
            default:
                throw new AssertionError("No DisplayCasePageNotification class exists for test instance: " + testCase.getTestInstance());
        }
    }

    public static void createNotification(final TestCaseContext tc) {
        SearchUtils.searchParty(tc);
        navigateToCreateNotificationIntake(tc);
        completeCreateNotificationForm(tc, false);
        submitNotification(tc);
    }

    public static void navigateToCreateNotificationIntake(final TestCaseContext tc) {
        final PartySearchPage partySearchPage = tc.getPage(PartySearchPage.class);
        partySearchPage.clickAddCase(new PartySearchPageRequest(tc));

        final CaseCreationWizardPage caseCreationwizardPage = tc.getPage(CaseCreationWizardPage.class);
        final CaseCreationWizardPageRequest caseCreationWizardPageRequest = new CaseCreationWizardPageRequest(tc);

        // Select Case Type
        caseCreationwizardPage.selectCaseType(caseCreationWizardPageRequest);
        caseCreationwizardPage.clickOnNextButton(caseCreationWizardPageRequest);

        // Add policy to notification
        caseCreationwizardPage.clickOnAddContract(caseCreationWizardPageRequest);
        caseCreationwizardPage.enterPolicyVersionRef(caseCreationWizardPageRequest);
        caseCreationwizardPage.clickOnSearchButton(caseCreationWizardPageRequest);
        caseCreationwizardPage.clickOnSearchPageOk(caseCreationWizardPageRequest);

        // Add notifier party
        caseCreationwizardPage.clickAddLinkedParty(caseCreationWizardPageRequest);
        caseCreationwizardPage.clickAddNotifier(caseCreationWizardPageRequest);
        caseCreationwizardPage.clickOnNextAtLinkPartyPage(caseCreationWizardPageRequest);
        caseCreationwizardPage.inputSearchString(caseCreationWizardPageRequest);
        caseCreationwizardPage.clickSearch(caseCreationWizardPageRequest);

        // refresh page
        final CaseCreationWizardPage caseCreationWizardPage2 = tc.getPage(CaseCreationWizardPage.class);
        final CaseCreationWizardPageRequest caseCreationWizardPageRequest2 = new CaseCreationWizardPageRequest(tc);
        caseCreationWizardPage2.clickOnNextButton(caseCreationWizardPageRequest2);
    }

    public static void completeCreateNotificationForm(final TestCaseContext tc, final boolean addDeprecatedCaseAlias) {
        final ProcessStatusPageBase notificationIntakeProcessStatusPage = getNotificationIntakeProcessStatusPage(tc);
        final ProcessStatusPageBaseRequest notificationIntakeProcessPageRequest = getNotificationIntakeProcessStatusPageRequest(tc);
        notificationIntakeProcessStatusPage.selectNotificationMethod(notificationIntakeProcessPageRequest);

        // Input mandatory incident details
        notificationIntakeProcessStatusPage.inputIncidentDate(notificationIntakeProcessPageRequest);
        notificationIntakeProcessStatusPage.selectIncidentTimezone(notificationIntakeProcessPageRequest);
        notificationIntakeProcessStatusPage.inputNotifierDate(notificationIntakeProcessPageRequest);
        notificationIntakeProcessStatusPage.selectNotifierTimezone(notificationIntakeProcessPageRequest);
        notificationIntakeProcessStatusPage.inputGBDate(notificationIntakeProcessPageRequest);
        notificationIntakeProcessStatusPage.selectingClientSpecificData(notificationIntakeProcessPageRequest);
        notificationIntakeProcessStatusPage.inputCaseDesc(notificationIntakeProcessPageRequest);

        if (!tc.isTestInstance(TestInstance.ICARE)) {
            // Add policy details
            notificationIntakeProcessStatusPage.enterInsuredPolicyStartDate(notificationIntakeProcessPageRequest);
            notificationIntakeProcessStatusPage.enterInsuredPolicyEndDate(notificationIntakeProcessPageRequest);
            notificationIntakeProcessStatusPage.selectInsuredPolicyTimezone(notificationIntakeProcessPageRequest);
            notificationIntakeProcessStatusPage.enterPolicyNumber(notificationIntakeProcessPageRequest);
            notificationIntakeProcessStatusPage.selectExcessMethod(notificationIntakeProcessPageRequest);
            notificationIntakeProcessStatusPage.selectExcessBasis(notificationIntakeProcessPageRequest);
        }

        notificationIntakeProcessStatusPage.doClickAddressSearch(notificationIntakeProcessPageRequest);

        // Add address details
        final GBAddLocationAddressPage gbAddLocationAddressPage = tc.getPage(GBAddLocationAddressPage.class);
        final GBAddLocationAddressPage.GBAddLocationAddressPageRequest addLocationAddressPageRequest = new GBAddLocationAddressPage.GBAddLocationAddressPageRequest(tc);
        gbAddLocationAddressPage.addAddress(addLocationAddressPageRequest);

        // Add Nature and Cause of Loss
        notificationIntakeProcessStatusPage.selectNatureOfLoss(notificationIntakeProcessPageRequest);
        notificationIntakeProcessStatusPage.selectCauseOfLoss(notificationIntakeProcessPageRequest);

        if (tc.isTestInstance(TestInstance.ICARE)) {
            notificationIntakeProcessStatusPage.selectAccidentCauseCode(notificationIntakeProcessPageRequest);
            if (!notificationIntakeProcessPageRequest.getCaseType().equalsIgnoreCase("Motor")) {
                ((com.gb.fineos.page.sharedpages.workmanager.scripting.icare.NotificationIntakeProcessStatusPage) notificationIntakeProcessStatusPage)
                    .selectIncidentCode((com.gb.fineos.page.sharedpages.workmanager.scripting.icare.NotificationIntakeProcessStatusPage.NotificationIntakeProcessStatusPageRequest) notificationIntakeProcessPageRequest);
            }
        } else {
            if (notificationIntakeProcessPageRequest.getCaseType().equalsIgnoreCase("Motor")) {
                notificationIntakeProcessStatusPage.selectAccidentCauseCode(notificationIntakeProcessPageRequest);
            }
        }
        if (addDeprecatedCaseAlias) {
            // Adding case alias value
            notificationIntakeProcessStatusPage.clickCaseAliasAddButton(notificationIntakeProcessPageRequest);
            final AddCaseAliasPage addCaseAliasPage = tc.getPage(AddCaseAliasPage.class);
            final AddCaseAliasPage.AddCaseAliasPageRequest addCaseAliasPageRequest = new AddCaseAliasPage.AddCaseAliasPageRequest(tc);
            addCaseAliasPage.enterCaseAliasValue(addCaseAliasPageRequest);
            addCaseAliasPage.clickOkButton(addCaseAliasPageRequest);
        }
    }

    public static void submitNotification(final TestCaseContext tc) {
        getNotificationIntakeProcessStatusPage(tc).doSubmitNotificationIntakeForm(getNotificationIntakeProcessStatusPageRequest(tc));
        getNotificationIntakeProcessStatusPage(tc).verifyCaseNumber(getNotificationIntakeProcessStatusPageRequest(tc));

        // Adding GeneratedNotificationCaseAlias to testCaseContext
        tc.getData().put(NOTIFICATION.getGeneratedCaseAlias(), getNotificationIntakeProcessStatusPage(tc).getCaseNumber());
    }

    public static void createClaim(final TestCaseContext tc) {
        navigatingToClaimIntakeForm(tc);
        completeCreateClaimForm(tc, false);
        submitCreateClaimIntake(tc);
        isDuplicateClaimTaskExists(tc);
    }

    private static void navigatingToClaimIntakeForm(final TestCaseContext tc) {
        // Create on create claim
        final DisplayCasePageNotificationBase displayCasePageNotificationBase = getDisplayCasePageNotification(tc);
        displayCasePageNotificationBase.doClickOnCreateClaim(getDisplayCasePageNotificationRequest(tc));
        getDisplayCasePageNotificationRequest(tc).log(LODGE_CLAIM, "NAVIGATING TO CLAIM INTAKE FORM");
        // Click on add button from case creation page
        final CaseCreationWizardPage caseCreationWizardPage = tc.getPage(CaseCreationWizardPage.class);
        final CaseCreationWizardPageRequest caseCreationWizardPageRequest = new CaseCreationWizardPageRequest(tc);
        caseCreationWizardPage.clickAddLinkedParty(caseCreationWizardPageRequest);
        addingClaimant(tc, caseCreationWizardPage);
    }

    private static void addingClaimant(final TestCaseContext tc, final CaseCreationWizardPage caseCreationWizardPage) {
        // Adding a claimant

        final AddPartyCaseRolePage addPartyCaseRolePage = tc.getPage(AddPartyCaseRolePage.class);
        final AddPartyCaseRolePageRequest addPartyCaseRolePageRequest = new AddPartyCaseRolePageRequest(tc);
        final PartySearchPage partySearchPage = tc.getPage(PartySearchPage.class);
        final PartySearchPageRequest partySearchPageRequest = new PartySearchPageRequest(tc);
        final AddPersonPage addPersonPage = tc.getPage(AddPersonPage.class);
        final AddPersonPage.AddPersonPageRequest addPersonPageRequest = new AddPersonPage.AddPersonPageRequest(tc);
        final GBAddLocationAddressPage gbAddLocationAddressPage = tc.getPage(GBAddLocationAddressPage.class);
        final GBAddLocationAddressPage.GBAddLocationAddressPageRequest gbAddLocationAddressPageRequest = new GBAddLocationAddressPage.GBAddLocationAddressPageRequest(tc);
        final AddOrganisationPartyPage addOrganisationPartyPage = tc.getPage(AddOrganisationPartyPage.class);
        final AddOrganisationPartyPage.AddOrganisationPartyPageRequest addOrganisationPartyPageRequest = new AddOrganisationPartyPage.AddOrganisationPartyPageRequest(tc);
        partySearchPageRequest.log(LODGE_CLAIM, "ADDING CLAIMANT");
        if (addPartyCaseRolePageRequest.isUniqueClaimant()) {
            addPartyCaseRolePage.selectLinkPartyRole(addPartyCaseRolePageRequest);
            addPartyCaseRolePage.clickOnNextButton(addPartyCaseRolePageRequest);
            partySearchPage.searchAndAddNewParty(partySearchPageRequest);
            if (addPartyCaseRolePageRequest.getLinkPartyType().equals("Person")) {
                addPersonPage.addNewPersonParty(addPersonPageRequest);
                gbAddLocationAddressPage.addAddress(gbAddLocationAddressPageRequest);
                addPersonPage.doAddingPersonalContactInformation(addPersonPageRequest);
            } else {
                addOrganisationPartyPage.addNewOrgParty(addOrganisationPartyPageRequest);
                gbAddLocationAddressPage.addAddress(gbAddLocationAddressPageRequest);
                addOrganisationPartyPage.doAddingContactInformation(addOrganisationPartyPageRequest);
            }
            if (getDisplayCasePageNotificationRequest(tc).getClaimType() == ClaimType.MOTOR_CLAIM) {
                addPartyCaseRolePage.clickOnOKButton(addPartyCaseRolePageRequest);
            }
            addPartyCaseRolePageRequest.log("NEW PARTY AS CLAIMANT", "New Party as Claimant Added");
        } else {
            addPartyCaseRolePage.doLinkAClaimantParty(addPartyCaseRolePageRequest);
        }
        caseCreationWizardPage.clickOkButton(new CaseCreationWizardPageRequest(tc));
    }

    public static void completeCreateClaimForm(final TestCaseContext tc, final boolean addDeprecatedCaseAlias) {
        // Adding case and claim details
        final ProcessStatusPageClaimBase claimIntakeProcessStatusPage = getProcessStatusPageClaim(tc);
        final ProcessStatusPageClaimBaseRequest claimIntakeProcessStatusPageRequest = getProcessStatusPageClaimRequest(tc);
        claimIntakeProcessStatusPageRequest.log(LODGE_CLAIM, "COMPLETING CLAIM INTAKE FORM");
        claimIntakeProcessStatusPage.enterClaimCaseDetailsDescription(claimIntakeProcessStatusPageRequest);
        claimIntakeProcessStatusPage.enterClaimReceiveDate(claimIntakeProcessStatusPageRequest);
        //Adding Reporting Units
        claimIntakeProcessStatusPage.selectingClientSpecificData(claimIntakeProcessStatusPageRequest);

        if (tc.isTestInstance(TestInstance.UK)) {
            ((com.gb.fineos.page.sharedpages.workmanager.scripting.uk.ClaimIntakeProcessStatusPage) claimIntakeProcessStatusPage)
                .selectClaimReceiveDateTimeZoneWithIndex(claimIntakeProcessStatusPageRequest);
        } else {
            claimIntakeProcessStatusPage.selectClaimReceiveDateTimeZone(claimIntakeProcessStatusPageRequest);
        }
        final AddCaseAliasPage addCaseAliasPage = tc.getPage(AddCaseAliasPage.class);
        final AddCaseAliasPageRequest addCaseAliasPageRequest = new AddCaseAliasPageRequest(tc);
        // Entering details of motor, personal injury and property damage claims
        final ClaimType claimType = claimIntakeProcessStatusPageRequest.getClaimType();
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
                if (addDeprecatedCaseAlias) {
                    claimIntakeProcessStatusPage.clickCaseAliasAddButton(claimIntakeProcessStatusPageRequest);
                    addCaseAlias(tc);
                }
                claimIntakeProcessStatusPage.clickOnNextButton(claimIntakeProcessStatusPageRequest);
                claimIntakeProcessStatusPage.doAddVehicleDetails(claimIntakeProcessStatusPageRequest);
                break;
            case PERSONAL_INJURY_CLAIM:
            case MISC_INJURY_CLAIM:
                claimIntakeProcessStatusPage.doAddCauseOfInjuryCode(claimIntakeProcessStatusPageRequest);
                Optional.of(claimIntakeProcessStatusPageRequest)
                    .filter(req -> tc.isTestInstance(TestInstance.ICARE))
                    .ifPresent(claimIntakeProcessStatusPage::doAddCostCentre);
                claimIntakeProcessStatusPage.clickOnNextButton(claimIntakeProcessStatusPageRequest);
                claimIntakeProcessStatusPage.doAddInjuryCodes(claimIntakeProcessStatusPageRequest);
                if (addDeprecatedCaseAlias) {
                    claimIntakeProcessStatusPage.clickCaseAliasAddButton(claimIntakeProcessStatusPageRequest);
                    addCaseAlias(tc);
                }
                break;
            case PROPERTY_DAMAGE_CLAIM:
            case PROPERTY_CLAIM:
            case MISC_PROPERTY_CLAIM:
                Optional.of(claimIntakeProcessStatusPageRequest)
                    .filter(req -> tc.isTestInstance(TestInstance.ICARE))
                    .ifPresent(claimIntakeProcessStatusPage::doAddCostCentre);
                claimIntakeProcessStatusPage.clickOnNextButton(claimIntakeProcessStatusPageRequest);
                claimIntakeProcessStatusPage.doAddPropertyDamageGeneralDetails(claimIntakeProcessStatusPageRequest);
                claimIntakeProcessStatusPage.doAddPropertyItemDetails(claimIntakeProcessStatusPageRequest);
                if (addDeprecatedCaseAlias) {
                    claimIntakeProcessStatusPage.clickCaseAliasAddButton(claimIntakeProcessStatusPageRequest);
                    addCaseAlias(tc);
                }
                break;
            case HEALTH_CLAIM:
                claimIntakeProcessStatusPage.doAddingHealthInjuryCaseDetails(claimIntakeProcessStatusPageRequest);
                Optional.of(claimIntakeProcessStatusPageRequest)
                    .filter(req -> tc.isTestInstance(TestInstance.ICARE))
                    .ifPresent(claimIntakeProcessStatusPage::doAddCostCentre);
                claimIntakeProcessStatusPage.clickOnNextButton(claimIntakeProcessStatusPageRequest);
                claimIntakeProcessStatusPage.doAddCauseOfInjuryCode(claimIntakeProcessStatusPageRequest);
                claimIntakeProcessStatusPage.clickOnNextButton(claimIntakeProcessStatusPageRequest);
                claimIntakeProcessStatusPage.doAddInjuryCodes(claimIntakeProcessStatusPageRequest);
                if (addDeprecatedCaseAlias) {
                    claimIntakeProcessStatusPage.clickCaseAliasAddButton(claimIntakeProcessStatusPageRequest);
                    addCaseAlias(tc);
                }
                break;
            default:
                LOG.error("Claim type is not present in Test data.");
        }
    }

    private static void submitCreateClaimIntake(final TestCaseContext tc) {
        final ProcessStatusPageClaimBase claimIntakeProcessStatusPage = getProcessStatusPageClaim(tc);
        final ProcessStatusPageClaimBaseRequest claimIntakeProcessStatusPageRequest = getProcessStatusPageClaimRequest(tc);
        claimIntakeProcessStatusPageRequest.log(LODGE_CLAIM, "SUBMITTING CLAIM INTAKE FORM");
        claimIntakeProcessStatusPage.clickOnNextButton(claimIntakeProcessStatusPageRequest);
        try {
            claimIntakeProcessStatusPage.verifyClaimNumber(new ClaimIntakeProcessStatusPageRequest(tc));
        } catch (Exception e) {
            if (claimIntakeProcessStatusPage.getCloseBtn().isDisplayed()) {
                claimIntakeProcessStatusPageRequest.warning("CLAIM INTAKE - REDIRECT FAILED", "Completing the claim intake form did not redirect to the Claim Landing Page (Refer LSA-291)", true);
                claimIntakeProcessStatusPage.clickClose(claimIntakeProcessStatusPageRequest);
                claimIntakeProcessStatusPage.verifyClaimNumber(new ClaimIntakeProcessStatusPageRequest(tc));
            }
        }
        // Adding GeneratedClaimCaseAlias to testCaseContext
        tc.getData().put(CLAIM.getGeneratedCaseAlias(), claimIntakeProcessStatusPage.getCaseNumber());
    }

    private static void isDuplicateClaimTaskExists(final TestCaseContext tc) {
        if (tc.isTestInstance(TestInstance.ICARE)) {
            SearchUtils.searchCase(tc, CLAIM);
            DisplayCasePageClaimBase displayCasePageClaimBase = tc.getPage(DisplayCasePageClaimBase.class);
            final DisplayCasePageClaimBaseRequest displayCasePageClaimBaseRequest = new DisplayCasePageClaimBaseRequest(tc);
            displayCasePageClaimBaseRequest.log(LODGE_CLAIM, "CHECKING IF DUPLICATE CLAIM EXISTS");
            displayCasePageClaimBase.clickTasksTab(displayCasePageClaimBaseRequest);
            if (displayCasePageClaimBase.isTaskFound(displayCasePageClaimBaseRequest, "Duplicate Claim")) {
                displayCasePageClaimBase.selectTaskFromTable(displayCasePageClaimBaseRequest, "Duplicate Claim");
                displayCasePageClaimBase.clickCloseOnTasks(displayCasePageClaimBaseRequest);
                final AddExtraDataPage addExtraDataPage = tc.getPage(AddExtraDataPage.class);
                final AddExtraDataPageRequest addExtraDataPageRequest = new AddExtraDataPageRequest(tc);
                addExtraDataPage.selectClosureReasonOfNotDuplicate(addExtraDataPageRequest);
                addExtraDataPage.clickOkButton(addExtraDataPageRequest);
            }
        }
    }

    private static void addCaseAlias(final TestCaseContext testCase) {
        final AddCaseAliasPage addCaseAliasPage = testCase.getPage(AddCaseAliasPage.class);
        final AddCaseAliasPageRequest addCaseAliasPageRequest = new AddCaseAliasPageRequest(testCase);

        if (testCase.isTestInstance(TestInstance.ICARE)) {
            addCaseAliasPage.selectAliasTypeDropDown(addCaseAliasPageRequest);
        }

        addCaseAliasPage.enterCaseAliasValue(addCaseAliasPageRequest);
        addCaseAliasPage.clickOkButton(addCaseAliasPageRequest);
    }

    public static void moveCaseToCoverageDecision(final TestCaseContext tc) {
        final ChooseNextProcessStepPage chooseNextProcessStepPage = tc.getPage(ChooseNextProcessStepPage.class);
        final ChooseNextProcessStepPageRequest chooseNextProcessStepPageRequest = new ChooseNextProcessStepPageRequest(tc);
        final DisplayCasePageBenefitBase displayCasePageBenefit = tc.getPage(DisplayCasePageBenefitBase.class);
        final DisplayCasePageBenefitBaseRequest displayCasePageBenefitRequest = new DisplayCasePageBenefitBaseRequest(tc);
        final DisplayCasePageNotificationBase displayCasePageNotification = getDisplayCasePageNotification(tc);
        final DisplayCasePageNotificationBaseRequest displayCasePageNotificationRequest = getDisplayCasePageNotificationRequest(tc);
        final DisplayCasePageClaimBase displayCasePageClaim = tc.getPage(DisplayCasePageClaimBase.class);
        final DisplayCasePageClaimBaseRequest displayCasePageClaimRequest = new DisplayCasePageClaimBaseRequest(tc);

        //Searching for Notification
        SearchUtils.searchCase(tc, NOTIFICATION);
        //Lodge Notification task and Close the task.
        displayCasePageNotification.clickTasksTab(displayCasePageNotificationRequest);
        // Lodge Notification
        displayCasePageNotification.selectTaskFromTable(displayCasePageNotificationRequest, "Lodge Notification");
        displayCasePageNotification.clickCloseOnTasks(displayCasePageNotificationRequest);
        displayCasePageNotification.clickSaveButton(displayCasePageNotificationRequest);
        //Searching for Claim
        SearchUtils.searchCase(tc, CLAIM);
        //Claim task and Close the task.
        displayCasePageClaim.clickTasksTab(displayCasePageClaimRequest);
        displayCasePageClaim.clickOnAllTasksRadioButton(displayCasePageClaimRequest);
        //Lodge Claim
        displayCasePageNotification.selectTaskFromTable(displayCasePageNotificationRequest, "Lodge Claim");
        displayCasePageClaim.clickCloseOnTasks(displayCasePageClaimRequest);
        chooseNextProcessStepPage.clickOnCoverageDecisionRequiredCell(chooseNextProcessStepPageRequest);
        displayCasePageClaim.clickSaveButton(displayCasePageClaimRequest);
        //Searching for Benefit
        SearchUtils.searchCase(tc, BENEFIT);
        //Benefit task and Close the task.
        displayCasePageBenefit.clickProgressionArrowIcon(displayCasePageBenefitRequest);
        displayCasePageBenefit.clickSaveButton(displayCasePageBenefitRequest);
    }

    public static void moveClaimToPreClosureCheck(final TestCaseContext tc) {
        SearchUtils.searchCase(tc, CLAIM);

        tc.getPage(DisplayCasePageClaimBase.class).clickProgressionArrowIcon(new DisplayCasePageClaimBaseRequest(tc));

        assertThat(ProcessUtils.getCurrentStatus(tc), equalToIgnoringCase(CASE_STATUS_PRE_CLOSURE_CHECK));
    }

    public static void reopenCaseForAdditionalExpensePayment(final TestCaseContext tc) {
        final ChooseNextProcessStepPage chooseNextProcessStepPage = tc.getPage(ChooseNextProcessStepPage.class);
        final ChooseNextProcessStepPageRequest chooseNextProcessStepPageRequest = new ChooseNextProcessStepPageRequest(tc);
        final DisplayCasePageBenefitBase displayCasePageBenefit = tc.getPage(DisplayCasePageBenefitBase.class);
        final DisplayCasePageBenefitBaseRequest displayCasePageBenefitRequest = new DisplayCasePageBenefitBaseRequest(tc);
        final DisplayCasePageNotificationBase displayCasePageNotification = getDisplayCasePageNotification(tc);
        final DisplayCasePageNotificationBaseRequest displayCasePageNotificationRequest = getDisplayCasePageNotificationRequest(tc);
        final DisplayCasePageClaimBase displayCasePageClaim = tc.getPage(DisplayCasePageClaimBase.class);
        final DisplayCasePageClaimBaseRequest displayCasePageClaimRequest = new DisplayCasePageClaimBaseRequest(tc);
        final AddExtraDataPage addExtraDataPage = tc.getPage(AddExtraDataPage.class);
        final AddExtraDataPageRequest addExtraDataPageRequest = new AddExtraDataPageRequest(tc);

        SearchUtils.searchCase(tc, NOTIFICATION);

        // Lodge Notification
        displayCasePageNotification.clickTasksTab(displayCasePageNotificationRequest);
        displayCasePageNotification.selectTaskFromTable(displayCasePageNotificationRequest, LODGE_NOTIFICATION);
        displayCasePageNotification.clickCloseOnTasks(displayCasePageNotificationRequest);
        displayCasePageNotification.clickSaveButton(displayCasePageNotificationRequest);

        SearchUtils.searchCase(tc, CLAIM);

        // Lodge Claim
        displayCasePageClaim.clickTasksTab(displayCasePageClaimRequest);
        displayCasePageNotification.selectTaskFromTable(displayCasePageNotificationRequest, LODGE_CLAIM);
        displayCasePageClaim.clickCloseOnTasks(displayCasePageClaimRequest);
        chooseNextProcessStepPage.clickOnNotProceedingWithClaimCell(chooseNextProcessStepPageRequest);
        chooseNextProcessStepPage.clickOnOK(chooseNextProcessStepPageRequest);

        SearchUtils.searchCase(tc, BENEFIT);

        // Benefit task and Close the task.
        displayCasePageBenefit.clickProgressionArrowIcon(displayCasePageBenefitRequest);
        displayCasePageBenefit.clickSaveButton(displayCasePageBenefitRequest);

        SearchUtils.searchCase(tc, CLAIM);
        displayCasePageClaim.clickProgressionArrowIcon(displayCasePageClaimRequest);
        chooseNextProcessStepPage.clickOnOK(chooseNextProcessStepPageRequest);
        assertThat(ProcessUtils.getCurrentStatus(tc), equalToIgnoringCase(CASE_STATUS_CLOSED));

        displayCasePageClaim.clickProgressionArrowIcon(displayCasePageClaimRequest);
        chooseNextProcessStepPage.clickOnOK(chooseNextProcessStepPageRequest);
        assertThat(ProcessUtils.getCurrentStatus(tc), equalToIgnoringCase(CASE_STATUS_REOPEN));

        // Claim task and Close the task.
        displayCasePageClaim.clickTasksTab(displayCasePageClaimRequest);
        displayCasePageClaim.clickOnAllTasksRadioButton(displayCasePageClaimRequest);
        displayCasePageNotification.selectTaskFromTable(displayCasePageNotificationRequest, RECORD_AND_REOPEN_REASON);
        displayCasePageClaim.clickCloseOnTasks(displayCasePageClaimRequest);
        addExtraDataPage.clickOkButton(addExtraDataPageRequest);
        chooseNextProcessStepPage.clickOnReopenForAdditionalExpensePaymentCell(chooseNextProcessStepPageRequest);
        chooseNextProcessStepPage.clickOnOK(chooseNextProcessStepPageRequest);

        assertThat(ProcessUtils.getCurrentStatus(tc), equalToIgnoringCase(REOPEN_FOR_ADDITIONAL_PAYMENT));

        SearchUtils.searchCase(tc, BENEFIT);

        // Benefit task and Close the task.
        displayCasePageBenefit.clickProgressionArrowIcon(displayCasePageBenefitRequest);
        displayCasePageBenefit.clickSaveButton(displayCasePageBenefitRequest);

        // Benefit task and Close the task.
        displayCasePageBenefit.clickTasksTab(displayCasePageClaimRequest);
        displayCasePageBenefit.clickOnAllTasksRadioButton(displayCasePageClaimRequest);
        displayCasePageBenefit.selectTaskFromTable(displayCasePageNotificationRequest, RECORD_AND_REOPEN_REASON);
        displayCasePageBenefit.clickCloseOnTasks(displayCasePageClaimRequest);
        addExtraDataPage.clickOkButton(addExtraDataPageRequest);
        chooseNextProcessStepPage.clickOnOK(chooseNextProcessStepPageRequest);

        assertThat(ProcessUtils.getCurrentStatus(tc), equalToIgnoringCase(COMPLETE_BENEFIT_DETAILS));
    }

    public static void assignDepartmentToNotification(final TestCaseContext tc) {
        final DisplayCasePageNotificationBase displayCasePageNotificationBase = getDisplayCasePageNotification(tc);
        final DisplayCasePageNotificationBaseRequest displayCasePageNotificationBaseRequest = getDisplayCasePageNotificationRequest(tc);
        displayCasePageNotificationBase.clickTransferCase(displayCasePageNotificationBaseRequest);
        displayCasePageNotificationBase.clickAssignToDept(displayCasePageNotificationBaseRequest);

        final TransferToDeptPage transferToDeptPage = tc.getPage(TransferToDeptPage.class);
        final TransferToDeptPage.TransferToDeptPageRequest transferToDeptPageRequest = new TransferToDeptPage.TransferToDeptPageRequest(tc);
        transferToDeptPage.selectDeptWithNoRole(transferToDeptPageRequest);
        transferToDeptPage.clickOk(transferToDeptPageRequest);
        transferToDeptPage.caseTransferOk(transferToDeptPageRequest);
    }
}
