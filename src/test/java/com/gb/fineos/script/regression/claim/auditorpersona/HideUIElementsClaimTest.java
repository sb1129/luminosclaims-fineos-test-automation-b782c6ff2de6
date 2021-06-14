package com.gb.fineos.script.regression.claim.auditorpersona;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.domain.TestInstance;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageClaimBase;
import com.gb.fineos.page.utils.AuthenticationUtils;
import com.gb.fineos.page.utils.CaseUtils;
import com.gb.fineos.page.utils.FineosDSL;
import com.gb.fineos.page.utils.SearchUtils;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.script.utils.BaseTest;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.Map;


public class HideUIElementsClaimTest extends BaseTest {
    private static final String AUDITOR = "Auditor";
    private static final String AUDITOR_PERSONA_CLAIM_TEST = "Auditor Persona Claim Test";

    //GEN-1091 AC3 and AC4
    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.nz"})
    public void claimAuditorPersonaValidationTest(final Map<String, String> testData) {
        doTest("HIDE UI ELEMENTS TEST", "Auditor Persona Claim test", testData, tc ->
            FineosDSL.getInstance(tc).getClaim().withTestStep(this::transferDeptForAuditReview).withTestStep(this::claimAssociatedCasesAndLinkPartyValidation).test()
        );
    }

    private void claimAssociatedCasesAndLinkPartyValidation(final TestCaseContext tc) {
        AuthenticationUtils.loginAsUser(tc, AUDITOR);
        SearchUtils.searchCase(tc, tc.getClaimCaseNumber());

        final DisplayCasePageClaimBase displayCasePageClaimBase = tc.getPage(DisplayCasePageClaimBase.class);
        final DisplayCasePageClaimBase.DisplayCasePageClaimBaseRequest displayCasePageClaimBaseRequest = new DisplayCasePageClaimBase.DisplayCasePageClaimBaseRequest(tc);
        //verify the associated Cases widget is not found
        if (tc.isTestInstance(TestInstance.ICARE)) {
            displayCasePageClaimBase.clickClaimSummaryTab(displayCasePageClaimBaseRequest);
        } else {
            displayCasePageClaimBase.clickSummaryTab(displayCasePageClaimBaseRequest);
        }
        Assert.assertFalse("Summary Tab -> Associated Cases Widget is available ", displayCasePageClaimBase.isElementFound(displayCasePageClaimBase.getAssociatedCases()));
        displayCasePageClaimBaseRequest.log(AUDITOR_PERSONA_CLAIM_TEST, "Summary Tab -> Associated Cases Widget not found");
        //verify the link party search
        displayCasePageClaimBase.clickLinkParty(displayCasePageClaimBaseRequest);
        displayCasePageClaimBase.clickBrokerParty(displayCasePageClaimBaseRequest);
        displayCasePageClaimBaseRequest.log(AUDITOR_PERSONA_CLAIM_TEST, "Looking for Recent Tab");
        Assert.assertFalse("Recent Tab is found", displayCasePageClaimBase.isElementFound(displayCasePageClaimBase.getRecentTab()));
    }

    private void transferDeptForAuditReview(final TestCaseContext tc) {
        CaseUtils.assignDepartmentToNotification(tc);
    }
}
