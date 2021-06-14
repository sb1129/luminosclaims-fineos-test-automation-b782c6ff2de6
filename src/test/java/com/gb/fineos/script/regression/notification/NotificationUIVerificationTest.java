package com.gb.fineos.script.regression.notification;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.domain.TestInstance;
import com.gb.fineos.page.sharedpages.casemanager.casecreation.CaseCreationWizardPage;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageNotificationBase;
import com.gb.fineos.page.sharedpages.partymanager.partysearch.PartySearchPage;
import com.gb.fineos.page.sharedpages.workmanager.scripting.ProcessStatusPageBase;
import com.gb.fineos.page.utils.CaseUtils;
import com.gb.fineos.page.utils.FineosDSL;
import com.gb.fineos.page.utils.SearchUtils;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.script.utils.BaseTest;
import com.gb.fineos.script.utils.TestUtils;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class NotificationUIVerificationTest extends BaseTest {

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.nz", "regression.icare", "validation.uk"})
    public void notificationLabelVerificationTest(final Map<String, String> testData) {
        doTest("Notification UI Verification Test", "Verify all text labels present at Notification level screens", testData, tc ->
            FineosDSL.getInstance(tc)
                .getNotification()
                .withImplementation(this::verifyUILabelsFromNotificationCase)
                .test());
    }


    public void verifyUILabelsFromNotificationCase(final TestCaseContext tc) {
        SearchUtils.searchParty(tc);
        verifyLabelsFromLinkPartyScreen(tc);
        verifyLabelsFromPolicySearchScreen(tc);
        verifyLabelsFromIntakeNotificationScreen(tc);
        CaseUtils.completeCreateNotificationForm(tc, false);
        CaseUtils.submitNotification(tc);
        verifyLabelsFromSummaryTabEditScreen(tc);
        verifyLabelsFromIncidentDetailsTabEditScreen(tc);
    }

    public static void verifyLabelsFromIntakeNotificationScreen(final TestCaseContext tc){
        final CaseCreationWizardPage caseCreationWizardPage2 = tc.getPage(CaseCreationWizardPage.class);
        final CaseCreationWizardPage.CaseCreationWizardPageRequest caseCreationWizardPageRequest2 = new CaseCreationWizardPage.CaseCreationWizardPageRequest(tc);
        final ProcessStatusPageBase notificationIntakeProcessStatusPage = CaseUtils.getNotificationIntakeProcessStatusPage(tc);
        final ProcessStatusPageBase.ProcessStatusPageBaseRequest notificationIntakeProcessPageRequest = CaseUtils.getNotificationIntakeProcessStatusPageRequest(tc);

        caseCreationWizardPage2.clickOnNextButton(caseCreationWizardPageRequest2);
        if (!tc.isTestInstance(TestInstance.ICARE)) {
            notificationIntakeProcessStatusPage.doExpandCatastropheDetailsWidget(notificationIntakeProcessPageRequest);
        }
        final List<String> expectedLabelsFromNotificationIntake = notificationIntakeProcessPageRequest.getExpectedLabels("NotificationIntake_Labels", ";");
        final List<String> actualLabelsFromNotificationIntake = notificationIntakeProcessStatusPage.getLabelsDisplayedFromTheScreen(notificationIntakeProcessPageRequest, notificationIntakeProcessStatusPage.getLabels(notificationIntakeProcessPageRequest, "Notificationintake"));
        TestUtils.compareLabels(tc, expectedLabelsFromNotificationIntake, actualLabelsFromNotificationIntake, LogStatus.ERROR);

    }

    public static void verifyLabelsFromLinkPartyScreen(final TestCaseContext tc) {
        final PartySearchPage partySearchPage = tc.getPage(PartySearchPage.class);
        partySearchPage.clickAddCase(new PartySearchPage.PartySearchPageRequest(tc));

        final CaseCreationWizardPage caseCreationwizardPage = tc.getPage(CaseCreationWizardPage.class);
        final CaseCreationWizardPage.CaseCreationWizardPageRequest caseCreationWizardPageRequest = new CaseCreationWizardPage.CaseCreationWizardPageRequest(tc);
        final ProcessStatusPageBase notificationIntakeProcessStatusPage = CaseUtils.getNotificationIntakeProcessStatusPage(tc);
        final ProcessStatusPageBase.ProcessStatusPageBaseRequest notificationIntakeProcessPageRequest = CaseUtils.getNotificationIntakeProcessStatusPageRequest(tc);

        // Select Case Type
        caseCreationwizardPage.selectCaseType(caseCreationWizardPageRequest);
        caseCreationwizardPage.clickOnNextButton(caseCreationWizardPageRequest);
        caseCreationwizardPage.clickAddLinkedParty(caseCreationWizardPageRequest);

        final List<String> expectedLabelsFromLinkPartyScreen = notificationIntakeProcessPageRequest.getExpectedLabels("LinkParty_Labels", ";");
        final List<String> actualLabelsFromLinkPartyScreen = notificationIntakeProcessStatusPage.getLabelsDisplayedFromTheScreen(notificationIntakeProcessPageRequest, notificationIntakeProcessStatusPage.getLabels(notificationIntakeProcessPageRequest,"LinkParty"));
        TestUtils.compareLabels(tc, expectedLabelsFromLinkPartyScreen, actualLabelsFromLinkPartyScreen, LogStatus.ERROR);

    }

    public static void verifyLabelsFromPolicySearchScreen(final TestCaseContext tc) {
        final CaseCreationWizardPage caseCreationwizardPage = tc.getPage(CaseCreationWizardPage.class);
        final CaseCreationWizardPage.CaseCreationWizardPageRequest caseCreationWizardPageRequest = new CaseCreationWizardPage.CaseCreationWizardPageRequest(tc);
        final ProcessStatusPageBase notificationIntakeProcessStatusPage = CaseUtils.getNotificationIntakeProcessStatusPage(tc);
        final ProcessStatusPageBase.ProcessStatusPageBaseRequest notificationIntakeProcessPageRequest = CaseUtils.getNotificationIntakeProcessStatusPageRequest(tc);

        // Add notifier party

        caseCreationwizardPage.clickAddNotifier(caseCreationWizardPageRequest);
        caseCreationwizardPage.clickOnNextAtLinkPartyPage(caseCreationWizardPageRequest);
        caseCreationwizardPage.inputSearchString(caseCreationWizardPageRequest);
        caseCreationwizardPage.clickSearch(caseCreationWizardPageRequest);

        // Add policy to notification
        caseCreationwizardPage.clickOnAddContract(caseCreationWizardPageRequest);
        caseCreationwizardPage.enterPolicyVersionRef(caseCreationWizardPageRequest);
        caseCreationwizardPage.clickOnSearchButton(caseCreationWizardPageRequest);

        final List<String> expectedLabelsFromPolicySearchScreen = notificationIntakeProcessPageRequest.getExpectedLabels("Search_Policy_Labels", ";");
        final List<String> actualLabelsFromPolicySearchScreen = notificationIntakeProcessStatusPage.getLabelsDisplayedFromTheScreen(notificationIntakeProcessPageRequest, notificationIntakeProcessStatusPage.getLabels(notificationIntakeProcessPageRequest, "PolicySearch"));
        TestUtils.compareLabels(tc, expectedLabelsFromPolicySearchScreen, actualLabelsFromPolicySearchScreen, LogStatus.ERROR);
        caseCreationwizardPage.clickOnSearchPageOk(caseCreationWizardPageRequest);


    }

    public static void verifyLabelsFromSummaryTabEditScreen(final TestCaseContext tc) {
        final DisplayCasePageNotificationBase displayCasePageNotification = CaseUtils.getDisplayCasePageNotification(tc);
        final DisplayCasePageNotificationBase.DisplayCasePageNotificationBaseRequest displayCasePageNotificationRequest = CaseUtils.getDisplayCasePageNotificationRequest(tc);
        final ProcessStatusPageBase notificationIntakeProcessStatusPage = CaseUtils.getNotificationIntakeProcessStatusPage(tc);
        final ProcessStatusPageBase.ProcessStatusPageBaseRequest notificationIntakeProcessPageRequest = CaseUtils.getNotificationIntakeProcessStatusPageRequest(tc);

        displayCasePageNotification.doOpenSummaryTab(displayCasePageNotificationRequest);
        displayCasePageNotification.clickEditSummaryCaseDetails(displayCasePageNotificationRequest);
        final List<String> expectedLabelsFromEditCaseSummaryScreen = notificationIntakeProcessPageRequest.getExpectedLabels("SummaryTab_EditCase_Labels", ";");
        final List<String> actualLabelsFromEditCaseSummaryScreen = notificationIntakeProcessStatusPage.getLabelsDisplayedFromTheScreen(notificationIntakeProcessPageRequest, notificationIntakeProcessStatusPage.getLabels(notificationIntakeProcessPageRequest,"summarydetails"));
        TestUtils.compareLabels(tc, expectedLabelsFromEditCaseSummaryScreen, actualLabelsFromEditCaseSummaryScreen, LogStatus.ERROR);
        displayCasePageNotification.clickOKButton(displayCasePageNotificationRequest);

        displayCasePageNotification.clickEditSummaryClaimDetails(displayCasePageNotificationRequest);
        displayCasePageNotification.doExpandClaimSourceDetailsPanelWidget(displayCasePageNotificationRequest);
        final List<String> expectedLabelsFromEditClaimSummaryScreen = notificationIntakeProcessPageRequest.getExpectedLabels("SummaryTab_EditClaimDetails_Labels", ";");
        final List<String> actualLabelsFromEditClaimSummaryScreen = notificationIntakeProcessStatusPage.getLabelsDisplayedFromTheScreen(notificationIntakeProcessPageRequest, notificationIntakeProcessStatusPage.getLabels(notificationIntakeProcessPageRequest, "summaryclaimsdetails"));
        TestUtils.compareLabels(tc, expectedLabelsFromEditClaimSummaryScreen, actualLabelsFromEditClaimSummaryScreen, LogStatus.ERROR);
        displayCasePageNotification.clickOKButton(displayCasePageNotificationRequest);
    }


    public static void verifyLabelsFromIncidentDetailsTabEditScreen(final TestCaseContext tc) {
        final DisplayCasePageNotificationBase displayCasePageNotification = CaseUtils.getDisplayCasePageNotification(tc);
        final DisplayCasePageNotificationBase.DisplayCasePageNotificationBaseRequest displayCasePageNotificationRequest = CaseUtils.getDisplayCasePageNotificationRequest(tc);
        final ProcessStatusPageBase notificationIntakeProcessStatusPage = CaseUtils.getNotificationIntakeProcessStatusPage(tc);
        final ProcessStatusPageBase.ProcessStatusPageBaseRequest notificationIntakeProcessPageRequest = CaseUtils.getNotificationIntakeProcessStatusPageRequest(tc);

        displayCasePageNotification.clickIncidentDetailsTab(displayCasePageNotificationRequest);
        displayCasePageNotification.clickEditIncidentDetails(displayCasePageNotificationRequest);
        final List<String> expectedLabelsFromEditIncidentDetailsScreen = notificationIntakeProcessPageRequest.getExpectedLabels("IncidentTab_GeneralEdit_Labels", ";");
        final List<String> actualLabelsFromEditIncidentDetailsScreen = notificationIntakeProcessStatusPage.getLabelsDisplayedFromTheScreen(notificationIntakeProcessPageRequest, notificationIntakeProcessStatusPage.getLabels(notificationIntakeProcessPageRequest, "Incidentdetails"));
        TestUtils.compareLabels(tc, expectedLabelsFromEditIncidentDetailsScreen, actualLabelsFromEditIncidentDetailsScreen, LogStatus.ERROR);
        displayCasePageNotification.clickOKButton(displayCasePageNotificationRequest);

        displayCasePageNotification.clickLossCauseTabDialogue(displayCasePageNotificationRequest);
        displayCasePageNotification.doExpandQuickAddItemPanelWidget(displayCasePageNotificationRequest);
        final List<String> expectedLabelsFromLossCauseScreen = notificationIntakeProcessPageRequest.getExpectedLabels("IncidentTab_CauseOfLoss_Labels", ";");
        final List<String> actualLabelsFromLossCauseScreen = notificationIntakeProcessStatusPage.getLabelsDisplayedFromTheScreen(notificationIntakeProcessPageRequest, notificationIntakeProcessStatusPage.getLabels(notificationIntakeProcessPageRequest, "Losscasuedetails"));
        TestUtils.compareLabels(tc, expectedLabelsFromLossCauseScreen, actualLabelsFromLossCauseScreen, LogStatus.ERROR);
    }

}
