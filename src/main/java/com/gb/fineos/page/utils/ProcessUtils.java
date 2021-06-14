package com.gb.fineos.page.utils;

import com.gb.fineos.domain.CaseType;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageNotificationBase.DisplayCasePageNotificationBaseRequest;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.icare.DisplayCasePageClaim;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.icare.DisplayCasePageClaim.DisplayCasePageClaimRequest;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.icare.DisplayCasePageNotification;
import com.gb.fineos.page.sharedpages.workmanager.ChooseNextProcessStepPage;
import com.gb.fineos.page.sharedpages.workmanager.ChooseNextProcessStepPage.ChooseNextProcessStepPageRequest;
import com.gb.fineos.screencapture.ScreenCapture;
import com.relevantcodes.extentreports.LogStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;

public final class ProcessUtils {
    public static final String CASE_STATUS_LODGEMENT = "Lodgement";
    public static final String CASE_STATUS_CLOSED = "Closed";
    public static final String CASE_STATUS_REOPEN = "Re-Open";
    public static final String CASE_STATUS_PRE_CLOSURE_CHECK = "PRE-CLOSURE CHECK";

    private ProcessUtils() {
        // do nothing
    }

    public static String getCurrentStatus(final TestCaseContext tc) {
        final DisplayCasePageClaim page = tc.getPage(DisplayCasePageClaim.class);
        final DisplayCasePageClaimRequest request = new DisplayCasePageClaimRequest(tc);

        ScreenCapture.logScreenshot("getCurrentStatus", LogStatus.INFO);

        return page.getCurrentStatus(request);
    }

    public static String getCurrentStatus(final TestCaseContext tc, final CaseType caseType) {
        SearchUtils.searchCase(tc, caseType);

        return getCurrentStatus(tc);
    }

    public static void progressFromLodgementToCoverageDecision(final TestCaseContext tc) {
        SearchUtils.searchCase(tc, CaseType.CLAIM);
        assertThat(getCurrentStatus(tc), equalToIgnoringCase(CASE_STATUS_LODGEMENT));

        final DisplayCasePageClaim displayCasePageClaim = tc.getPage(DisplayCasePageClaim.class);
        final DisplayCasePageClaimRequest displayCasePageClaimRequest = new DisplayCasePageClaimRequest(tc);

        displayCasePageClaim.clickTasksTab(displayCasePageClaimRequest);
        displayCasePageClaim.clickOnAllTasksRadioButton(displayCasePageClaimRequest);
        displayCasePageClaim.clickOnOpenTasksRadioButton(displayCasePageClaimRequest);

        //Lodge Claim first
        final DisplayCasePageNotification displayCasePageNotification = tc.getPage(DisplayCasePageNotification.class);
        final DisplayCasePageNotificationBaseRequest displayCasePageNotificationBaseRequest = new DisplayCasePageNotificationBaseRequest(tc);
        displayCasePageNotification.selectTaskFromTable(displayCasePageNotificationBaseRequest, "Lodge Claim");
        displayCasePageNotification.clickCloseOnTasks(displayCasePageNotificationBaseRequest);

        final ChooseNextProcessStepPage chooseNextProcessStepPage = tc.getPage(ChooseNextProcessStepPage.class);
        final ChooseNextProcessStepPageRequest chooseNextProcessStepPageRequest = new ChooseNextProcessStepPageRequest(tc);
        chooseNextProcessStepPage.clickOnCoverageDecisionRequiredCell(chooseNextProcessStepPageRequest);
        displayCasePageClaim.clickSaveButton(displayCasePageClaimRequest);
    }
}
