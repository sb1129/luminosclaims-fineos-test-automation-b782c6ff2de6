package com.gb.fineos.page.utils;

import com.gb.fineos.domain.CaseType;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.DashboardPage;
import com.gb.fineos.page.DashboardPage.DashboardPageRequest;
import com.gb.fineos.page.sharedpages.casemanager.CaseSearchPage;
import com.gb.fineos.page.sharedpages.casemanager.CaseSearchPage.CaseSearchPageRequest;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageBenefitBase;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageClaimBase;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageClaimBase.DisplayCasePageClaimBaseRequest;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageNotificationBase;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageNotificationBase.DisplayCasePageNotificationBaseRequest;
import com.gb.fineos.page.sharedpages.partymanager.partysearch.PartySearchPage;
import com.gb.fineos.page.sharedpages.partymanager.partysearch.PartySearchPage.PartySearchPageRequest;
import com.gb.fineos.page.sharedpages.workmanager.WorkManagerTasksPage;
import com.gb.fineos.page.sharedpages.workmanager.WorkManagerTasksPage.WorkManagerTasksPageRequest;

import static com.gb.fineos.domain.CaseType.BENEFIT;
import static com.gb.fineos.domain.CaseType.CLAIM;
import static com.gb.fineos.domain.CaseType.NOTIFICATION;

public final class SearchUtils {
    private SearchUtils() {
        // Do nothing
    }

    public static void searchCase(final TestCaseContext tc) {
        final DashboardPage dashboardPage = tc.getPage(DashboardPage.class);
        final DashboardPageRequest dashboardPageRequest = new DashboardPageRequest(tc);
        dashboardPage.clickSidebarCasesLink(dashboardPageRequest);
        final CaseSearchPage caseSearchPage = tc.getPage(CaseSearchPage.class);
        final CaseSearchPageRequest caseSearchPageRequest = new CaseSearchPageRequest(tc);
        caseSearchPage.enterCaseNumber(caseSearchPageRequest);
        caseSearchPage.clickSearch(caseSearchPageRequest);
    }

    public static void searchCase(final TestCaseContext tc, final CaseType caseType) {
        final DashboardPage dashboardPage = tc.getPage(DashboardPage.class);
        final DashboardPageRequest dashboardPageRequest = new DashboardPageRequest(tc);
        dashboardPage.clickSidebarCasesLink(dashboardPageRequest);
        final CaseSearchPage caseSearchPage = tc.getPage(CaseSearchPage.class);
        final CaseSearchPageRequest caseSearchPageRequest = new CaseSearchPageRequest(tc);
        caseSearchPage.enterCaseAliasValue(caseSearchPageRequest, caseType);
        caseSearchPage.clickSearch(caseSearchPageRequest);
    }

    public static void searchCase(final TestCaseContext tc, final String caseNumber) {
        final DashboardPage dashboardPage = tc.getPage(DashboardPage.class);
        final DashboardPageRequest dashboardPageRequest = new DashboardPageRequest(tc);
        dashboardPage.clickSidebarCasesLink(dashboardPageRequest);
        final CaseSearchPage caseSearchPage = tc.getPage(CaseSearchPage.class);
        final CaseSearchPageRequest caseSearchPageRequest = new CaseSearchPageRequest(tc);
        caseSearchPage.enterActualCaseNumber(caseSearchPageRequest, caseNumber);
        caseSearchPage.clickSearch(caseSearchPageRequest);
    }


    public static void searchParty(final TestCaseContext tc) {
        final DashboardPage dashboardPage = tc.getPage(DashboardPage.class);
        final DashboardPageRequest dashboardPageRequest = new DashboardPageRequest(tc);
        dashboardPage.clickSidebarPartiesLink(dashboardPageRequest);

        final PartySearchPage partySearchPage = tc.getPage(PartySearchPage.class);
        final PartySearchPageRequest partySearchPageRequest = new PartySearchPageRequest(tc);

        if (partySearchPageRequest.isPerson()) {
            partySearchPage.inputFirstName(partySearchPageRequest);
            partySearchPage.inputLastName(partySearchPageRequest);
        } else if (partySearchPageRequest.isOrganisation()) {
            partySearchPage.clickOnOrganisationRadioButton(partySearchPageRequest);
            partySearchPage.enterOrganisationName(partySearchPageRequest);
            partySearchPage.enterShortName(partySearchPageRequest);
        } else {
            throw new AssertionError("Invalid Party Type supplied.");
        }

        partySearchPage.clickSearch(partySearchPageRequest);
        partySearchPage.clickOkButton(partySearchPageRequest);
    }

    public static void searchAndOpenTask(final TestCaseContext tc) {
        final DashboardPage dashboardPage = tc.getPage(DashboardPage.class);
        final DashboardPageRequest dashboardPageRequest = new DashboardPageRequest(tc);
        dashboardPage.clickSidebarTasksLink(dashboardPageRequest);

        //Filter and open Unindexed task
        final WorkManagerTasksPage workManagerTasksPage = tc.getPage(WorkManagerTasksPage.class);
        final WorkManagerTasksPageRequest workManagerTasksPageRequest = new WorkManagerTasksPageRequest(tc);
        workManagerTasksPage.clickTaskNameFilter(workManagerTasksPageRequest);
        workManagerTasksPage.getFilter().inputFilterText(workManagerTasksPageRequest, workManagerTasksPageRequest.getTaskName());
        workManagerTasksPage.getFilter().clickApplyButton(workManagerTasksPageRequest);
        workManagerTasksPage.clickSubjectFilter(workManagerTasksPageRequest);
        workManagerTasksPage.getFilter().inputFilterText(workManagerTasksPageRequest, workManagerTasksPageRequest.getSubject());
        workManagerTasksPage.getFilter().clickApplyButton(workManagerTasksPageRequest);
        workManagerTasksPage.selectFilteredResultCheckbox(workManagerTasksPageRequest);
        workManagerTasksPage.clickOpenTaskLink(workManagerTasksPageRequest);
    }

     public static void searchAndCloseTask(final TestCaseContext tc) {
        final DashboardPage dashboardPage = tc.getPage(DashboardPage.class);
        final DashboardPageRequest dashboardPageRequest = new DashboardPageRequest(tc);
        dashboardPage.clickSidebarTasksLink(dashboardPageRequest);

        //Filter and close Unindexed task
        final WorkManagerTasksPage workManagerTasksPage = tc.getPage(WorkManagerTasksPage.class);
        final WorkManagerTasksPageRequest workManagerTasksPageRequest = new WorkManagerTasksPageRequest(tc);
        workManagerTasksPage.clickTaskNameFilter(workManagerTasksPageRequest);
        workManagerTasksPage.getFilter().inputFilterText(workManagerTasksPageRequest, workManagerTasksPageRequest.getTaskName());
        workManagerTasksPage.getFilter().clickApplyButton(workManagerTasksPageRequest);
        workManagerTasksPage.clickSubjectFilter(workManagerTasksPageRequest);
        workManagerTasksPage.getFilter().inputFilterText(workManagerTasksPageRequest, workManagerTasksPageRequest.getSubject());
        workManagerTasksPage.getFilter().clickApplyButton(workManagerTasksPageRequest);
        workManagerTasksPage.selectFilteredResultCheckbox(workManagerTasksPageRequest);
        workManagerTasksPage.clickCloseTaskLink(workManagerTasksPageRequest);
    }

    public static void searchDocumentsAndOpenProperties(final TestCaseContext tc, final CaseType caseType) {
        switch (caseType) {
            case NOTIFICATION:
                searchCase(tc, NOTIFICATION);
                final DisplayCasePageNotificationBase displayCasePageNotificationBase = tc.getPage(DisplayCasePageNotificationBase.class);
                final DisplayCasePageNotificationBaseRequest displayCasePageNotificationBaseRequest = new DisplayCasePageNotificationBaseRequest(tc);
                displayCasePageNotificationBase.clickDocumentsTab(displayCasePageNotificationBaseRequest);
                displayCasePageNotificationBase.clickTitleFilter(displayCasePageNotificationBaseRequest);
                displayCasePageNotificationBase.getFilter().inputFilterText(displayCasePageNotificationBaseRequest, displayCasePageNotificationBaseRequest.getTitle());
                displayCasePageNotificationBase.getFilter().clickApplyButton(displayCasePageNotificationBaseRequest);
                displayCasePageNotificationBase.selectFirstCheckbox(displayCasePageNotificationBaseRequest);
                displayCasePageNotificationBase.clickOnDocumentPropertiesButton(displayCasePageNotificationBaseRequest);
            case CLAIM:
                searchCase(tc, CLAIM);
                final DisplayCasePageClaimBase displayCasePageClaimBase = tc.getPage(DisplayCasePageClaimBase.class);
                final DisplayCasePageClaimBaseRequest displayCasePageClaimBaseRequest = new DisplayCasePageClaimBaseRequest(tc);
                displayCasePageClaimBase.clickDocumentsTab(displayCasePageClaimBaseRequest);
                displayCasePageClaimBase.clickTitleFilter(displayCasePageClaimBaseRequest);
                displayCasePageClaimBase.getFilter().inputFilterText(displayCasePageClaimBaseRequest, displayCasePageClaimBaseRequest.getTitle());
                displayCasePageClaimBase.getFilter().clickApplyButton(displayCasePageClaimBaseRequest);
                displayCasePageClaimBase.selectFirstCheckbox(displayCasePageClaimBaseRequest);
                displayCasePageClaimBase.clickOnDocumentPropertiesButton(displayCasePageClaimBaseRequest);
            case BENEFIT:
                searchCase(tc, BENEFIT);
                final DisplayCasePageBenefitBase displayCasePageBenefitBase = tc.getPage(DisplayCasePageBenefitBase.class);
                final DisplayCasePageBenefitBaseRequest displayCasePageBenefitBaseRequest = new DisplayCasePageBenefitBaseRequest(tc);
                displayCasePageBenefitBase.clickDocumentsTab(displayCasePageBenefitBaseRequest);
                displayCasePageBenefitBase.clickTitleFilter(displayCasePageBenefitBaseRequest);
                displayCasePageBenefitBase.getFilter().inputFilterText(displayCasePageBenefitBaseRequest, displayCasePageBenefitBaseRequest.getTitle());
                displayCasePageBenefitBase.getFilter().clickApplyButton(displayCasePageBenefitBaseRequest);
                displayCasePageBenefitBase.selectFirstCheckbox(displayCasePageBenefitBaseRequest);
                displayCasePageBenefitBase.clickOnDocumentPropertiesButton(displayCasePageBenefitBaseRequest);
            default:
                throw new AssertionError("This Case Type do not exists: " + caseType);
        }
    }

    public static void searchDocuments(final TestCaseContext tc, final CaseType caseType) {
        switch (caseType) {
            case NOTIFICATION:
                final DisplayCasePageNotificationBase displayCasePageNotificationBase = tc.getPage(DisplayCasePageNotificationBase.class);
                final DisplayCasePageNotificationBaseRequest displayCasePageNotificationBaseRequest = new DisplayCasePageNotificationBaseRequest(tc);
                displayCasePageNotificationBase.clickDocumentsTab(displayCasePageNotificationBaseRequest);
                displayCasePageNotificationBase.clickBusinessTypeFilter(displayCasePageNotificationBaseRequest);
                displayCasePageNotificationBase.getFilter().inputFilterText(displayCasePageNotificationBaseRequest, displayCasePageNotificationBaseRequest.getBusinessType());
                displayCasePageNotificationBase.getFilter().clickApplyButton(displayCasePageNotificationBaseRequest);
                displayCasePageNotificationBase.clickTitleFilter(displayCasePageNotificationBaseRequest);
                displayCasePageNotificationBase.getFilter().inputFilterText(displayCasePageNotificationBaseRequest, displayCasePageNotificationBaseRequest.getDocumentName());
                displayCasePageNotificationBase.getFilter().clickApplyButton(displayCasePageNotificationBaseRequest);
            case CLAIM:
                final DisplayCasePageClaimBase displayCasePageClaimBase = tc.getPage(DisplayCasePageClaimBase.class);
                final DisplayCasePageClaimBaseRequest displayCasePageClaimBaseRequest = new DisplayCasePageClaimBaseRequest(tc);
                displayCasePageClaimBase.clickDocumentsTab(displayCasePageClaimBaseRequest);
                displayCasePageClaimBase.clickBusinessTypeFilter(displayCasePageClaimBaseRequest);
                displayCasePageClaimBase.getFilter().inputFilterText(displayCasePageClaimBaseRequest, displayCasePageClaimBaseRequest.getBusinessType());
                displayCasePageClaimBase.getFilter().clickApplyButton(displayCasePageClaimBaseRequest);
                displayCasePageClaimBase.clickTitleFilter(displayCasePageClaimBaseRequest);
                displayCasePageClaimBase.getFilter().inputFilterText(displayCasePageClaimBaseRequest, displayCasePageClaimBaseRequest.getDocumentName());
                displayCasePageClaimBase.getFilter().clickApplyButton(displayCasePageClaimBaseRequest);
            case BENEFIT:
                final DisplayCasePageBenefitBase displayCasePageBenefitBase = tc.getPage(DisplayCasePageBenefitBase.class);
                final DisplayCasePageBenefitBaseRequest displayCasePageBenefitBaseRequest = new DisplayCasePageBenefitBaseRequest(tc);
                String currentBenefit = displayCasePageBenefitBase.currentBenefitName(displayCasePageBenefitBaseRequest);
                displayCasePageBenefitBase.clickDocumentsTab(displayCasePageBenefitBaseRequest);
                displayCasePageBenefitBase.clickBusinessTypeFilter(displayCasePageBenefitBaseRequest);
                displayCasePageBenefitBase.getFilter().inputFilterText(displayCasePageBenefitBaseRequest, displayCasePageBenefitBaseRequest.getBusinessType());
                displayCasePageBenefitBase.getFilter().clickApplyButton(displayCasePageBenefitBaseRequest);
                displayCasePageBenefitBase.clickTitleFilter(displayCasePageBenefitBaseRequest);
                displayCasePageBenefitBase.getFilter().inputFilterText(displayCasePageBenefitBaseRequest, displayCasePageBenefitBaseRequest.getDocumentName());
                displayCasePageBenefitBase.getFilter().clickApplyButton(displayCasePageBenefitBaseRequest);
            default:
                throw new AssertionError("This Case Type do not exists: " + caseType);
        }
    }

}
