package com.gb.fineos.script.regression.claim;

import com.gb.fineos.domain.CaseType;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageClaimBase;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageClaimBase.DisplayCasePageClaimBaseRequest;
import com.gb.fineos.page.utils.ESBUtils;
import com.gb.fineos.page.utils.FineosDSL;
import com.gb.fineos.page.utils.ProcessUtils;
import com.gb.fineos.page.utils.SearchUtils;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.script.utils.BaseTest;
import org.testng.annotations.Test;

import java.util.Map;

import static com.gb.fineos.page.utils.ProcessUtils.CASE_STATUS_CLOSED;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;


public class AutoCloseTest extends BaseTest {

    private final static String AUTO_CLOSE_UNSUCCESSFUL_TASK = "Auto Close Unsuccessful";
    private static final String SUCCESSFUL_AUTOCLOSE = "Successful";
    private static final int SECS_TO_WAIT_FOR_WORK_PERFORMER = 60;

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"dynamics.integration.au", "dynamics.integration.nz", "dynamics.integration.icare", "dynamics.integration.uk"})
    public void AutoCloseWorkPerformerTest(final Map<String, String> testData) {
        doTest("AutoCloseWorkPerformerTest", "Test auto close scenario for Case Alias " + testData.get("NOT_CaseAliasValue"), testData, tc ->
            FineosDSL.getInstance(tc)
                .getBenefit()
                    .withTestStep(testCaseContext -> {
                        final DisplayCasePageClaimBaseRequest request = new DisplayCasePageClaimBaseRequest(tc);

                        ESBUtils.generatePaymentsForDynamicsAndPerformReconciliation(testCaseContext);
                        ESBUtils.pauseProcessForSpecifiedSeconds(SECS_TO_WAIT_FOR_WORK_PERFORMER);

                        if (request.getAutoCloseType().equals(SUCCESSFUL_AUTOCLOSE)) {
                            assertThat(ProcessUtils.getCurrentStatus(testCaseContext, CaseType.BENEFIT), equalToIgnoringCase(CASE_STATUS_CLOSED));
                            assertThat(ProcessUtils.getCurrentStatus(testCaseContext, CaseType.CLAIM), equalToIgnoringCase(CASE_STATUS_CLOSED));
                            assertThat(ProcessUtils.getCurrentStatus(testCaseContext, CaseType.NOTIFICATION), equalToIgnoringCase(CASE_STATUS_CLOSED));
                        } else {
                            assertThat(ProcessUtils.getCurrentStatus(testCaseContext, CaseType.BENEFIT), equalToIgnoringCase(request.getBenefitStatus()));

                            SearchUtils.searchCase(testCaseContext, CaseType.CLAIM);
                            final DisplayCasePageClaimBase claimPage = testCaseContext.getPage(DisplayCasePageClaimBase.class);
                            assertThat(ProcessUtils.getCurrentStatus(testCaseContext), equalToIgnoringCase(request.getClaimStatus()));

                            // Verify auto close unsuccessful task
                            claimPage.clickTasksTab(request);
                            claimPage.isTaskFound(request, AUTO_CLOSE_UNSUCCESSFUL_TASK);
                            claimPage.selectTaskFromTable(request, AUTO_CLOSE_UNSUCCESSFUL_TASK);
                            claimPage.clickCloseOnTasks(request);

                            SearchUtils.searchCase(testCaseContext, CaseType.NOTIFICATION);
                            assertThat(ProcessUtils.getCurrentStatus(testCaseContext, CaseType.NOTIFICATION), equalToIgnoringCase(request.getNotificationStatus()));
                        }
                    })
                .test());
    }
}
