package com.gb.fineos.script.regression.dashboard.auditorpersona;


import com.gb.fineos.page.DashboardPage;
import com.gb.fineos.page.sharedpages.casemanager.CaseSearchPage;
import com.gb.fineos.page.sharedpages.contractmanager.ContractSearchPage;
import com.gb.fineos.page.sharedpages.partymanager.partysearch.PartySearchPage;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.script.utils.BaseTest;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.Map;


public class HideUIElementsDashboardTest extends BaseTest {
    private static final String AUDITOR_PERSONA_DASHBOARD_TEST = "Auditor Persona Dashboard Test";

    //GEN-1091 AC5, AC6, AC7, AC8
    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.nz"})
    public void dashboardAuditorPersonaValidationTest(final Map<String, String> testData) {
        doTest("HIDE UI ELEMENTS TEST", "Auditor Persona Dashboard test", testData, tc -> {
            final DashboardPage dashboardPage = tc.getPage(DashboardPage.class);
            final DashboardPage.DashboardPageRequest dashboardPageRequest = new DashboardPage.DashboardPageRequest(tc);

            //AC5
            dashboardPage.clickSidebarCasesLink(dashboardPageRequest);
            final CaseSearchPage caseSearchPage = tc.getPage(CaseSearchPage.class);
            final CaseSearchPage.CaseSearchPageRequest caseSearchPageRequest = new CaseSearchPage.CaseSearchPageRequest(tc);
            caseSearchPageRequest.log(AUDITOR_PERSONA_DASHBOARD_TEST, "Looking for Party Tab");
            Assert.assertFalse("Party Tab is Found", caseSearchPage.isElementFound(caseSearchPage.getPartyTab()));

            //AC6
            caseSearchPage.selectCaseType(caseSearchPageRequest);
            caseSearchPage.clickSearch(caseSearchPageRequest);
            caseSearchPageRequest.log(AUDITOR_PERSONA_DASHBOARD_TEST, "Looking for Case Search Widget");
            Assert.assertFalse("Case Search Widget is Found", caseSearchPage.isElementFound(caseSearchPage.getCaseSearchResult()));
            caseSearchPage.clickCancel(caseSearchPageRequest);

            //AC7
            dashboardPage.clickSidebarPartiesLink(dashboardPageRequest);
            final PartySearchPage partySearchPage = tc.getPage(PartySearchPage.class);
            final PartySearchPage.PartySearchPageRequest partySearchPageRequest = new PartySearchPage.PartySearchPageRequest(tc);
            partySearchPageRequest.log(AUDITOR_PERSONA_DASHBOARD_TEST, "Looking for Party Search Recent Tab");
            Assert.assertFalse("Party Search Recent Tab is Found", partySearchPage.isElementFound(partySearchPage.getRecentTab()));
            partySearchPage.clickCancel(partySearchPageRequest);

            //AC8
            dashboardPage.clickSidebarPoliciesLink(dashboardPageRequest);
            final ContractSearchPage contractSearchPage = tc.getPage(ContractSearchPage.class);
            final ContractSearchPage.ContractSearchPageRequest contractSearchPageRequest = new ContractSearchPage.ContractSearchPageRequest(tc);
            contractSearchPageRequest.log(AUDITOR_PERSONA_DASHBOARD_TEST, "Looking for Policy Search Recent Tab");
            Assert.assertFalse("Policy Search Recent Tab is Found", contractSearchPage.isElementFound(contractSearchPage.getRecentTab()));
            contractSearchPageRequest.log(AUDITOR_PERSONA_DASHBOARD_TEST, "Looking for Policy Search Tab");
            Assert.assertFalse("Policy Search Tab is Found", contractSearchPage.isElementFound(contractSearchPage.getPolicySearchTab()));
            contractSearchPageRequest.log(AUDITOR_PERSONA_DASHBOARD_TEST, "Looking for Policy Search Party Tab");
            Assert.assertFalse("Policy Search Party Tab is Found", contractSearchPage.isElementFound(contractSearchPage.getPartyTab()));
            contractSearchPage.clickCancel(contractSearchPageRequest);
        });
    }


}
