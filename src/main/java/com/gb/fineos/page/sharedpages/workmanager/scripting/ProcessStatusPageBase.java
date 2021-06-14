package com.gb.fineos.page.sharedpages.workmanager.scripting;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.factory.PageFactory;
import com.gb.fineos.page.BasePage;
import com.gb.fineos.page.component.GBClientFieldSearchPage;
import com.gb.fineos.page.component.LeavingPagePopup;
import com.gb.fineos.page.utils.RandomData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

public abstract class ProcessStatusPageBase extends BasePage {

    protected static final String REPORTING_UNIT_DEFAULT_FIRST_INDEX = "2";
    protected static final String CLIENT_SPECIFIC_DATA_LABEL = "Client Specific Data";
    private LeavingPagePopup leavingPagePopup;
    private GBClientFieldSearchPage gbClientFieldSearchPage;
    private static final int VERTICAL_LENGTH = 400;
    private static final int DEFAULT_INCIDENT_DAYS = -3;
    private static final int DEFAULT_NOTIFIER_DAYS = -2;
    private static final int DEFAULT_REPORT_TO_GB_DAYS = -1;

    public ProcessStatusPageBase() {
        super("PROCESS STATUS PAGE BASE - NOTIFICATION INTAKE PAGE");
        this.leavingPagePopup = PageFactory.initElements(LeavingPagePopup.class);
        this.leavingPagePopup.addPageNamePrefix(getPageName());
        this.gbClientFieldSearchPage = PageFactory.initElements(GBClientFieldSearchPage.class);
        this.gbClientFieldSearchPage.addPageNamePrefix(getPageName());
    }

    public LeavingPagePopup getLeavingPagePopup() {
        return leavingPagePopup;
    }

    public GBClientFieldSearchPage getGBClientFieldSearchPage() {
        return gbClientFieldSearchPage;
    }

    @FindBy(css = "select[id$='_ClaimSource']")
    private WebElement claimSource;
    @FindBy(css = "input[id$='_IncurredDate']")
    private WebElement incidentDate;
    @FindBy(css = "select[id$='_IncidentTimeZone']")
    private WebElement incidentTimeZone;
    @FindBy(css = "input[id$='_IncurredDateClaimant']")
    private WebElement notifierDate;
    @FindBy(css = "select[id$='_NotifierTimeZone']")
    private WebElement notifierTimeZone;
    @FindBy(css = "input[id$='_NotificationDate']")
    private WebElement gbDate;
    @FindBy(css = "textarea[id$='_caseDesc']")
    private WebElement caseDesc;
    @FindBy(css = "input[id$='_nextButton']")
    private List<WebElement> nextBtn;
    @FindBy(css = "input[id$='_editPageSave_cloned']")
    private WebElement save;
    @FindBy(css = "input[id$='_caseAlias_cmdAdd']")
    private WebElement caseAliasAddBtn;
    @FindBy(css = "select[id$='_TypeofIncident']")
    private WebElement natureOfLoss;
    @FindBy(css = "select[id$='_CauseOfLoss']")
    private WebElement causeOfLoss;
    @FindBy(css = "select[id$='_IncidentCode']")
    private WebElement incidentCode;
    @FindBy(css = "select[id$='_AccidentCauseCode']")
    private WebElement accidentCauseCode;
    @FindBy(css = "div[class='key-info-bar']")
    private WebElement infobar;

    @FindBy(xpath = "//input[contains(@id,'_InsuredStartDate')]")
    private WebElement insuredPolicyStartDate;
    @FindBy(xpath = "//input[contains(@id,'_InsuredEndDate')]")
    private WebElement insuredPolicyEndDate;
    @FindBy(xpath = "//select[contains(@id,'_Insured_Policy_Time_Zone')]")
    private WebElement insuredPolicyTimezone;
    @FindBy(xpath = "//input[contains(@id,'_Policy_Reference')]")
    private WebElement policyNumber;
    @FindBy(xpath = "//select[contains(@id,'_Excess_Method')]")
    private WebElement excessMethod;
    @FindBy(xpath = "//select[contains(@id,'_Excess_Basis')]")
    private WebElement excessBasis;
    @FindBy(css = "input[id$='_incidentAddress_rcbSearchButton_incidentAddress']")
    private WebElement addressSearch;
    @FindBy(xpath = "//input[contains(@id,'_okButton')]")
    private WebElement closeBtn;
    @FindBy(xpath = "//input[contains(@id,'_searchPageOk')]")
    private WebElement searchPageOkButton;

    @FindBy(xpath = "//select[contains(@id,'_ReportingUnit0')]")
    private WebElement reportingUnit;
    @FindBy(xpath = "//select[contains(@id,'_ReportingUnit')]")
    private List<WebElement> reportingUnitsList;
    @FindBy(xpath = "//div[@class='pageheader_heading']/h2/span")
    private WebElement caseNumberElement;
    @FindBy(xpath = "//span[contains(@id,'PageMessage1')]")
    private WebElement pageMessage;
    @FindBy(xpath = "//td[@class='raiseMessageText']/ul/li/span")
    private List<WebElement> errorValidation;
    @FindBy(xpath = "//img[contains(@id,'catastropheDetails_')]")
    private WebElement catastropheDetailsPanelWidget;
    @FindBy(xpath = "//a[contains(@id,'btn_close_popup_msg')]")
    private WebElement closeValidationMessage;

    public WebElement getClaimSource() {
        return claimSource;
    }

    public WebElement getIncidentDate() {
        return incidentDate;
    }

    public WebElement getIncidentTimeZone() {
        return incidentTimeZone;
    }

    public WebElement getNotifierDate() {
        return notifierDate;
    }

    public WebElement getNotifierTimeZone() {
        return notifierTimeZone;
    }

    public WebElement getGBDate() {
        return gbDate;
    }

    public WebElement getCaseDesc() {
        return caseDesc;
    }

    public List<WebElement> getNextBtn() {
        return nextBtn;
    }

    public WebElement getCaseAliasAddBtn() {
        return caseAliasAddBtn;
    }

    public WebElement getInfobar() {
        return infobar;
    }

    public WebElement getCauseOfLoss() {
        return causeOfLoss;
    }

    public WebElement getNatureOfLoss() {
        return natureOfLoss;
    }

    public WebElement getIncidentCode() {
        return incidentCode;
    }

    public WebElement getAccidentCauseCode() {
        return accidentCauseCode;
    }

    public WebElement getInsuredPolicyTimezone() {
        return insuredPolicyTimezone;
    }

    public WebElement getSave() {
        return save;
    }

    public WebElement getInsuredPolicyStartDate() {
        return insuredPolicyStartDate;
    }

    public WebElement getInsuredPolicyEndDate() {
        return insuredPolicyEndDate;
    }

    public WebElement getPolicyNumber() {
        return policyNumber;
    }

    public WebElement getExcessMethod() {
        return excessMethod;
    }

    public WebElement getExcessBasis() {
        return excessBasis;
    }

    public WebElement getReportingUnit() {
        return reportingUnit;
    }

    public List<WebElement> getReportingUnitsList() {
        return reportingUnitsList;
    }

    public WebElement getAddressSearch() {
        return addressSearch;
    }

    public WebElement getCloseBtn() {
        return closeBtn;
    }

    public String getCaseNumber() {
        return caseNumberElement.getText();
    }

    public WebElement getSearchPageOkButton() {
        return searchPageOkButton;
    }

    public WebElement getCatastropheDetailsPanelWidget() {
        return catastropheDetailsPanelWidget;
    }


    public void clickCaseAliasAddButton(final ProcessStatusPageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking the Add Button for Case Alias");
        click(getCaseAliasAddBtn());
    }

    public void selectNotificationMethod(final ProcessStatusPageBaseRequest pageRequest) {
        waitForElementPresent(claimSource);
        pageRequest.log(getPageName(), "Selecting Notification Method as " + pageRequest.getClaimSource());
        selectValue(pageRequest.getClaimSource(), claimSource);
        getAssertionHelper().assertIsDisplayed(pageRequest.getClaimSource(), claimSource);
    }

    public void enterInsuredPolicyStartDate(final ProcessStatusPageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Insured Policy Start date : " + pageRequest.getInsuredPolicyStartDate());
        clearAndInput(pageRequest.getInsuredPolicyStartDate(), insuredPolicyStartDate);
    }

    public void enterInsuredPolicyEndDate(final ProcessStatusPageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Insured Policy End date : " + pageRequest.getInsuredPolicyEndDate());
        clearAndInput(pageRequest.getInsuredPolicyEndDate(), insuredPolicyEndDate);
    }

    public void selectInsuredPolicyTimezone(final ProcessStatusPageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting Insured Policy Timezone : " + pageRequest.getInsuredPolicyTimezone());
        selectValue(pageRequest.getInsuredPolicyTimezone(), insuredPolicyTimezone);
    }

    public void enterPolicyNumber(final ProcessStatusPageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Policy Number : " + pageRequest.getPolicyNumber());
        clearAndInput(pageRequest.getPolicyNumber(), policyNumber);
    }

    public void selectExcessMethod(final ProcessStatusPageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting Excess Method : " + pageRequest.getExcessMethod());
        selectValue(pageRequest.getExcessMethod(), excessMethod);
    }

    public void selectExcessBasis(final ProcessStatusPageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting Excess Basis : " + pageRequest.getExcessBasis());
        selectValue(pageRequest.getExcessBasis(), excessBasis);
    }

    public void inputIncidentDate(final ProcessStatusPageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Incident Date as:" + pageRequest.getIncidentDate());
        clearAndInput(pageRequest.getIncidentDate(), getIncidentDate());
    }

    public void inputIncidentDate(final ProcessStatusPageBaseRequest pageRequest, final String incidentDate) {
        pageRequest.log(getPageName(), "Inputting Incident date " + incidentDate);
        clearAndInput(incidentDate, getIncidentDate());
    }

    public void selectIncidentTimezone(final ProcessStatusPageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Incident Timezone " + pageRequest.getIncidentTimeZone());
        selectByIndex(pageRequest.getIncidentTimeZoneIndex(), incidentTimeZone);
    }

    public void inputNotifierDate(final ProcessStatusPageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Notifier Date as:" + pageRequest.getNotifierDate());
        clearAndInput(pageRequest.getNotifierDate(), getNotifierDate());
    }

    public void inputNotifierDate(final ProcessStatusPageBaseRequest pageRequest, final String notifierDate) {
        pageRequest.log(getPageName(), "Inputting Notifier date " + notifierDate);
        clearAndInput(notifierDate, getNotifierDate());
    }

    public void selectNotifierTimezone(final ProcessStatusPageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Notifier Timezone " + pageRequest.getNotifierTimeZone());
        selectByIndex(pageRequest.getNotifierTimeZoneIndex(), notifierTimeZone);
    }

    public void inputGBDate(final ProcessStatusPageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Report To GB Date as:" + pageRequest.getGBDate());
        clearAndInput(pageRequest.getGBDate(), getGBDate());
    }

    public void inputGBDate(final ProcessStatusPageBaseRequest pageRequest, final String gbDate) {
        pageRequest.log(getPageName(), "Inputting GB date " + gbDate);
        clearAndInput(gbDate, getNotifierDate());
    }

    public void selectingClientSpecificData(final ProcessStatusPageBaseRequest pageRequest) {
        addingClientSpecificData(pageRequest, "//span[contains(@id,'gbLiabilityNotificationGeneralWidget')]/table[contains(@class,'WidgetPanel')]");
    }

    public void addingClientSpecificData(final ProcessStatusPageBaseRequest pageRequest, final String xPathPrefix) {
        if (!getDriver().findElements(By.xpath(xPathPrefix + "/tbody/tr/td/span[text()='" + CLIENT_SPECIFIC_DATA_LABEL + "']")).isEmpty()) {
            for (int i = 0; i < reportingUnitsList.size(); i++) {
                WebElement element = getElement("//select[contains(@id,'_ReportingUnit" + i + "')]");
                if (isElementPresent(element)) {
                    pageRequest.log(getPageName(), "Selecting  Reporting Unit..");
                    selectByIndex(REPORTING_UNIT_DEFAULT_FIRST_INDEX, element);
                }
            }
            selectAllClientData(pageRequest);
        }
    }

    private void selectAllClientData(final ProcessStatusPageBaseRequest pageRequest) {
        if (!getDriver().findElements(By.xpath("//span[contains(@id,'gbClientDataWidget') and contains(@id,'_clientDataRelSearch')]")).isEmpty()) {
            final List<WebElement> clientDataRouteList = getDriver().findElements(By.xpath("//span[contains(@id,'gbClientDataWidget')]/table[contains(@class,'WidgetPanel')]"));

            for (int i = 0; i < clientDataRouteList.size(); i++) {
                WebElement element = getElement("//input[contains(@id,'gbClientDataWidget" + i + "_') and contains(@id,'_clientDataRelSearch')]");
                if (isElementPresent(element)) {
                    pageRequest.log(getPageName(), "Selecting Bus Route..");
                    click(element);
                    gbClientFieldSearchPage.searchClientData(pageRequest);
                }
            }
        }
    }

    public void inputCaseDesc(final ProcessStatusPageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Case Desc as: " + pageRequest.getCaseDesc());
        clearAndInput(pageRequest.getCaseDesc(), caseDesc);
    }

    public void doSubmitNotificationIntakeForm(final ProcessStatusPageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking next button");
        scrollIntoView(nextBtn.get(1));
        click(nextBtn.get(1));
    }

    public void selectAccidentCauseCode(final ProcessStatusPageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), " Entering Incident Code as: " + pageRequest.getAccidentCauseCode());
        selectByIndex(pageRequest.getAccidentCauseCodeIndex(), getAccidentCauseCode());
    }

    public void selectCauseOfLoss(final ProcessStatusPageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), " Entering Cause of Loss as: " + pageRequest.getCauseOfLoss());
        selectByIndex(pageRequest.getCauseOfLossIndex(), getCauseOfLoss());
    }

    public void selectNatureOfLoss(final ProcessStatusPageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), " Entering Nature of Loss as: " + pageRequest.getNatureOfLoss());
        selectByIndex(pageRequest.getNatureOfLossIndex(), getNatureOfLoss());
    }

    public void verifyCaseNumber(final ProcessStatusPageBaseRequest pageRequest) {
        final String caseNumber = getText(caseNumberElement);
        pageRequest.log(getPageName(), "Created " + pageRequest.getCaseType() + " Case Number  is : " + caseNumber);
    }

    public void doClickAddressSearch(final ProcessStatusPageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Address Search");
        click(addressSearch);
    }

    public void clickClose(final ProcessStatusPageBaseRequest pageRequest) {
        scrollPageVertically(VERTICAL_LENGTH);
        pageRequest.log(getPageName(), "Click Close");
        click(closeBtn);
    }

    public void closeValidationErrorMessage(final ProcessStatusPageBaseRequest pageBaseRequest) {
        pageBaseRequest.log(getPageName(), "Closing validation message box");
        clickModalBox(closeValidationMessage);
    }

    public void clickSearchPageOkButton(final ProcessStatusPageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking the OK button on the search page");
        click(getSearchPageOkButton());
    }

    public void doExpandCatastropheDetailsWidget(final ProcessStatusPageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Expand Image for the Catastrophe details Panel");
        if (getCatastropheDetailsPanelWidget().getAttribute("title").equals("Collapse this Section")) {
            pageRequest.log(getPageName(), "Catastrophe details Panel is already Expanded");
        } else {
            click(catastropheDetailsPanelWidget);
        }
    }


    public static class ProcessStatusPageBaseRequest extends AbstractPageRequest {

        public ProcessStatusPageBaseRequest(final TestCaseContext context) {
            super(context);
        }

        public String getCaseType() {
            return get("caseType");
        }

        public String getClaimSource() {
            return get("claimSource");
        }

        public String getIncidentDate() {
            getContext().getData().putIfAbsent("incidentDate", RandomData.getCalculatedDateAndTime(DEFAULT_INCIDENT_DAYS));
            return getContext().getData().get("incidentDate");
        }

        public String getNotifierDate() {
            getContext().getData().putIfAbsent("notifierDate", RandomData.getCalculatedDateAndTime(DEFAULT_NOTIFIER_DAYS));
            return getContext().getData().get("notifierDate");
        }

        public String getGBDate() {
            getContext().getData().putIfAbsent("gbDate", RandomData.getCalculatedDateAndTime(DEFAULT_REPORT_TO_GB_DAYS));
            return getContext().getData().get("gbDate");
        }

        public String getIncidentTimeZone() {
            return get("incidentTimeZone");
        }

        public String getIncidentTimeZoneIndex() {
            return get("incidentTimeZoneIndex");
        }

        public String getNotifierTimeZone() {
            return get("notifierTimeZone");
        }

        public String getNotifierTimeZoneIndex() {
            return get("notifierTimeZoneIndex");
        }

        public String getCaseDesc() {
            return get("caseDesc");
        }

        public String getNatureOfLoss() {
            return get("natureOfLoss");
        }

        public String getCauseOfLoss() {
            return get("causeOfLoss");
        }

        public String getNatureOfLossIndex() {
            return get("natureOfLossIndex");
        }

        public String getCauseOfLossIndex() {
            return get("causeOfLossIndex");
        }

        public String getIncidentCode() {
            return get("incidentCode");
        }

        public String getAccidentCauseCode() {
            return get("accidentCauseCode");
        }

        public String getIncidentCodeIndex() {
            return get("incidentCodeIndex");
        }

        public String getAccidentCauseCodeIndex() {
            return get("accidentCauseCodeIndex");
        }

        public String getInsuredPolicyStartDate() {
            return get("InsuredPolicyStartDate");
        }

        public String getInsuredPolicyTimezone() {
            return get("InsuredPolicyTimezone");
        }

        public String getInsuredPolicyEndDate() {
            return get("InsuredPolicyEndDate");
        }

        public String getPolicyNumber() {
            return get("PolicyNumber");
        }

        public String getExcessMethod() {
            return get("ExcessMethod");
        }

        public String getExcessBasis() {
            return get("ExcessBasis");
        }

        public List<String> getExpectedValidationMessages(final String key, final String delimiter) {
            return Arrays.asList(get(key).split(delimiter));
        }

        public List<String> getExpectedLabels(final String key, final String delimiter) {
            return Arrays.asList(get(key).split(delimiter));
        }
    }

}
