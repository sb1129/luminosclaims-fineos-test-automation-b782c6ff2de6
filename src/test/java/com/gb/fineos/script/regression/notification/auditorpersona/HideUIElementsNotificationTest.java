package com.gb.fineos.script.regression.notification.auditorpersona;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageNotificationBase;
import com.gb.fineos.page.utils.AuthenticationUtils;
import com.gb.fineos.page.utils.CaseUtils;
import com.gb.fineos.page.utils.FineosDSL;
import com.gb.fineos.page.utils.SearchUtils;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.script.utils.BaseTest;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.Map;


public class HideUIElementsNotificationTest extends BaseTest {
    private static final String AUDITOR = "Auditor";
    private static final String AUDITOR_PERSONA_NOTIFICATION_TEST = "Auditor Persona Notification Test";


    //GEN-1091 AC3 and AC4
    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.nz"})
    public void notificationAuditorPersonaValidationTest(final Map<String, String> testData) {
        doTest("HIDE UI ELEMENTS TEST", "Auditor Persona Notification test", testData, tc ->
            FineosDSL.getInstance(tc).getNotification().withTestStep(this::transferDeptForAuditReview).withTestStep(this::notificationAssociatedCasesAndLinkPartyValidation).build().test()
        );
    }

    private void notificationAssociatedCasesAndLinkPartyValidation(final TestCaseContext tc) {
        AuthenticationUtils.loginAsUser(tc, AUDITOR);
        SearchUtils.searchCase(tc, tc.getNotificationCaseNumber());

        final DisplayCasePageNotificationBase displayCasePageNotificationBase = CaseUtils.getDisplayCasePageNotification(tc);
        final DisplayCasePageNotificationBase.DisplayCasePageNotificationBaseRequest displayCasePageNotificationRequest = CaseUtils.getDisplayCasePageNotificationRequest(tc);

        displayCasePageNotificationBase.clickSummaryTab(displayCasePageNotificationRequest);
        //verify the associated Cases widget is not found
        Assert.assertFalse("Summary Tab -> portfolio section is available ", displayCasePageNotificationBase.isElementFound(displayCasePageNotificationBase.getAssociatedCases()));
        displayCasePageNotificationRequest.log(AUDITOR_PERSONA_NOTIFICATION_TEST, "Summary Tab -> portfolio section not found");
        //verify the link party search
        displayCasePageNotificationBase.clickLinkParty(displayCasePageNotificationRequest);
        displayCasePageNotificationBase.clickBrokerParty(displayCasePageNotificationRequest);
        displayCasePageNotificationRequest.log(AUDITOR_PERSONA_NOTIFICATION_TEST, "Looking for Recent Tab");
        Assert.assertFalse("Recent Tab is found", displayCasePageNotificationBase.isElementFound(displayCasePageNotificationBase.getRecentTab()));
    }

    private void transferDeptForAuditReview(final TestCaseContext tc) {
        CaseUtils.assignDepartmentToNotification(tc);
    }
}
