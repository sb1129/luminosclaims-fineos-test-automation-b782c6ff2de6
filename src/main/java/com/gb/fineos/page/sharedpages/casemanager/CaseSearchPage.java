package com.gb.fineos.page.sharedpages.casemanager;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.CaseType;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import com.gb.fineos.screencapture.ScreenCapture;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Optional;

public class CaseSearchPage extends BasePage {

    @FindBy(id = "CaseSearchTabs_un11_FINEOS.CaseManager.CaseSearch.Payments_searchByCase_cell")
    private WebElement tabCase;
    @FindBy(id = "CaseSearchTabs_un11_FINEOS.CaseManager.CaseSearch.Payments_caseSearchByParty_cell")
    private WebElement partyTab;
    @FindBy(id = "CaseSearchTabs_un11_FINEOS.CaseManager.CaseSearch.Payments_searchByPayment_cell")
    private WebElement tabPayments;
    @FindBy(id = "CaseSearchTabs_un11_FINEOS.CaseManager.CaseSearch.Payments_recentCases_cell")
    private WebElement tabRecent;

    @FindBy(id = "CaseSearchByCaseWidget_un12_searchButton")
    private WebElement btnSearch;
    @FindBy(id = "CaseSearchByCaseWidget_un12_newSearchButton")
    private WebElement btnNewSearch;
    @FindBy(xpath = "//input[contains(@id, '_searchPageCancel_')]")
    private WebElement btnCancel;
    @FindBy(xpath = "//div[contains(@id,'_CaseSearchResults')]")
    private WebElement caseSearchResult;

    // case tab:
    @FindBy(id = "CaseSearchByCaseWidget_un12_caseNumber")
    private WebElement caseNumber;
    @FindBy(id = "CaseSearchByCaseWidget_un12_searchCaseAliasCheckBox_CHECKBOX")
    private WebElement searchCaseAlias;
    @FindBy(id = "CaseSearchByCaseWidget_un12_Effective_Date")
    private WebElement effectiveDate;
    @FindBy(id = "CaseSearchByCaseWidget_un12_policyNumber")
    private WebElement policyReference1;
    @FindBy(id = "CaseSearchByCaseWidget_un12_policyReference2")
    private WebElement policyReference2;
    @FindBy(id = "CaseSearchByCaseWidget_un12_policyReference3")
    private WebElement policyReference3;
    @FindBy(id = "CaseSearchByCaseWidget_un12_policyReference4")
    private WebElement policyReference4;
    @FindBy(id = "CaseSearchByCaseWidget_un12_policyReference5")
    private WebElement policyReference5;
    @FindBy(xpath = "//select[contains(@id,'_caseTypeDropDown')]")
    private WebElement caseType;
    @FindBy(id = "CaseSearchByCaseWidget_un12_searchCaseChildren_CHECKBOX")
    private WebElement searchCaseChildren;

    // party tab:

    // payments tab:

    //recent tab:

    public WebElement getCaseNumberElement() {
        return caseNumber;
    }

    public WebElement getPartyTab() {
        return partyTab;
    }

    public WebElement getCaseSearchResult() {
        return caseSearchResult;
    }

    public CaseSearchPage() {
        super("CASE SEARCH PAGE");
    }

    private void clickTabCase(final CaseSearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Case Tab");
        click(tabCase);
    }

    private void clickTabParty(final CaseSearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Party Tab");
        click(partyTab);
    }

    private void clickTabPayments(final CaseSearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Payments Tab");
        click(tabPayments);
    }

    private void clickTabRecent(final CaseSearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Recent Tab");
        click(tabRecent);
    }

    //if the Logged user is CC user then it will work for Notification, Claim and Benefit search
    public void enterCaseNumber(final CaseSearchPageRequest pageRequest) {
        final String caseAlias = pageRequest.getCaseNumber().replace('-', '_') + "-" + pageRequest.getUserName() + "-" + pageRequest.getTestRunIdentifier();
        pageRequest.log(getPageName(), "Entering Case Number/Case Alias: " + caseAlias);
        clearAndInput(caseAlias, getCaseNumberElement());
    }

    public void enterActualCaseNumber(final CaseSearchPageRequest pageRequest, final String caseNumber) {
        pageRequest.log(getPageName(), "Entering Case Number/Case Alias: " + caseNumber);
        clearAndInput(caseNumber, getCaseNumberElement());
    }

    public void enterCaseAliasValue(final CaseSearchPageRequest pageRequest, final CaseType caseType) {
        final String caseAlias = pageRequest.getGeneratedCaseAlias(caseType);

        pageRequest.log(getPageName(), "Entering Case Number/Case Alias: " + caseAlias);
        clearAndInput(caseAlias, getCaseNumberElement());
    }

    public String getCaseAliasValue(final CaseSearchPageRequest pageRequest, final CaseType caseType) {
        final String caseAlias = pageRequest.getGeneratedCaseAlias(caseType);
        pageRequest.log(getPageName(), "Case Number/Case Alias: " + caseAlias);
        return caseAlias;
    }

    public void clickSearch(final CaseSearchPageRequest pageRequest) {
        ScreenCapture.logScreenshot(getPageName(), LogStatus.INFO);
        pageRequest.log(getPageName(), "Clicking on Search Button");
        click(btnSearch);
    }

    public void enterHardCodedCaseNumber(final CaseSearchPage.CaseSearchPageRequest pageRequest, final String caseNumber) {
        pageRequest.log(getPageName(), "Entering Hard Coded Case Number/alias" + caseNumber);
        clearAndInput(caseNumber, getCaseNumberElement());
    }

    public void clickCancel(final CaseSearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Click Cancel Button");
        clickModalBox(btnCancel);
    }

    public void selectCaseType(final CaseSearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Select Case Type");
        selectValue(pageRequest.getCaseTypeForSearch(), caseType);
    }

    public static class CaseSearchPageRequest extends AbstractPageRequest {

        public CaseSearchPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getUserName() {
            return get("UserName");
        }

        public String getCaseNumber() {
            return get("CaseNumber");
        }

        public String getCaseAlias(final CaseType caseType) {
            return get(caseType.getCaseAlias());
        }

        public String getGeneratedCaseAlias(final CaseType caseType) {
            return Optional.ofNullable(getContext().getValue(caseType.getGeneratedCaseAlias()))
                        .orElseGet(() -> getCaseAlias(caseType) + "-" + getUserName() + "-" + getTestRunIdentifier());
        }

        public String getCaseTypeForSearch() {
            return get("caseType");
        }
    }
}
