package com.gb.fineos.page.sharedpages.casemanager.displaycase;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.factory.PageFactory;
import com.gb.fineos.page.BasePage;
import com.gb.fineos.page.component.Filter;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class DisplayCasePageBase extends BasePage {

    private final Filter filter;

    protected static final String PAGE_NAME = "DISPLAY CASE PAGE";
    protected static final String MENU_BAR = "Menu Bar";
    protected static final String HTML_CLASS = "class";
    protected static final String TAB_ON = "TabOn";
    protected static final String CASE_DETAILS_WEB_ELEMENT_ID = "CaseDetails";
    protected static final String TASKS_TAB_WEB_ELEMENT_ID = "TasksForCaseListViewWidget";
    protected static final String DOCUMENTS_FOR_CASE_WIDGET_ID = "DocumentsForCaseListviewWidget";
    protected static final String PAYMENT_HISTORY_TAB_WEB_ELEMENT_ID = "PaymentHistoryDetailsListviewWidget";
    protected static final String GENERAL_INCIDENT_DETAILS_WIDGET_WEB_ELEMENT_ID = "ViewGenIncidentDetailsContainerWidget";
    protected static final String DOCUMENTS_FOR_CASE_WIDGET = "Documents For Case Widget";
    protected static final String PAYMENTS_MADE_WIDGET = "Payments Made Widget";
    protected static final String CASE_SUMMARY = "Case Summary";
    protected static final String DOCUMENTS = "Documents";
    protected static final String TASKS = "Tasks";
    protected static final String TASKS_WIDGET = TASKS + " Widget";
    protected static final String PROCESSES = "Processes";


    protected static final String CLAIM_CONSULTANT = "UserName";
    protected static final String APPROVAL_LEVEL_1 = "TeamLead";
    protected static final String APPROVAL_LEVEL_2 = "BranchManger";
    protected static final String APPROVAL_LEVEL_3 = "OperationalManager";
    protected static final String APPROVAL_LEVEL_4 = "CFO";
    protected static final String APPROVAL_LEVEL_5 = "UserName";
    protected static final String LEVEL_1 = "1.000000";
    protected static final String LEVEL_2 = "2.000000";
    protected static final String LEVEL_3 = "3.000000";
    protected static final String LEVEL_4 = "4.000000";
    protected static final String LEVEL_5 = "5.000000";

    DisplayCasePageBase(final String pageName) {
        super(pageName);
        this.filter = PageFactory.initElements(Filter.class);
        this.filter.addPageNamePrefix(getPageName());
    }

    public Filter getFilter() {
        return filter;
    }

    // menubar buttons
    @FindBy(id = "DROPDOWN.AddSubCase")
    private WebElement menuBarCreateClaimDropdown;
    @FindBy(id = "DROPDOWN.Correspondence")
    private WebElement menuBarCreateCorrespondenceDropdown;
    @FindBy(id = "DROPDOWN.AddActivity")
    private WebElement menuBarAddTaskButton;
    @FindBy(id = "DROPDOWN.AddEform")
    private WebElement menuBarAddNoteDropdown;

    // casesummary panel
    @FindBy(id = "collapsible_panel_status")
    private WebElement caseSummaryPanelCollapse;
    @FindBy(xpath = "//span[contains(@id,'AssociatedClaimsListviewWidget')]")
    private WebElement associatedCases;
    @FindBy(xpath = "//a[contains(@id,'_Broker_Brokerlink')]")
    private WebElement brokerPartyLink;
    @FindBy(xpath = "//a[contains(@id,'_DROPDOWNMENU.CaseParticipantAddDropdownlink')]")
    private WebElement linkParty;
    @FindBy(xpath = "//td[contains(@id,'_recent_cell')]")
    private WebElement recentCell;


    //main buttons
    @FindBy(xpath = "//input[contains(@id,'_editPageSave_cloned')]")
    private WebElement saveButton;
    @FindBy(xpath = "//input[contains(@id,'_OK_cloned')]")
    private WebElement okButton;
    @FindBy(xpath = "//input[contains(@id,'_next_cloned')]")
    private WebElement nextButton;

    @FindBy(xpath = "//span[contains(@class,'menuIconSpan')]")
    private WebElement progressionArrowIcon;

    @FindBy(xpath = "//span[contains(@id, 'PageMessage1')]")
    private WebElement currentMessageText;

    // Display Tabs
    @FindBy(xpath = "//td[contains(@id, '_CaseTabControlBean_PropertyGeneralClaimTab_cell')]")
    private WebElement displayTabSummary;
    @FindBy(xpath = "//td[contains(@id, '_CaseTabControlBean_CaseReservesSummaryTab_cell')]")
    private WebElement displayTabReserves;
    @FindBy(xpath = "//td[contains(@id, '_CaseTabControlBean_Case_Actions_cell')]")
    private WebElement displayTabCaseActions;
    @FindBy(xpath = "//td[contains(@id, '_CaseTabControlBean_Case_Alias_cell')]")
    private WebElement displayTabCaseAlias;
    @FindBy(xpath = "//td[contains(@id, '_CaseTabControlBean_Validations_cell')]")
    private WebElement displayTabValidations;
    @FindBy(xpath = "//td[contains(@id, '_CaseTabControlBean_Documents_cell')]")
    private WebElement displayTabDocuments;
    @FindBy(xpath = "//td[contains(@id, '_CaseTabControlBean_Activity_Feed_cell')]")
    private WebElement displayTabActivities;
    @FindBy(xpath = "//td[contains(@id, '_CaseTabControlBean_Tasks_cell')]")
    private WebElement displayTabTasks;

    @FindBy(css = "input[id$='_caseAlias_cmdAdd']")
    private WebElement caseAliasAddBtn;
    //Tasks table

    // Task tab level buttons
    @FindBy(xpath = "//input[contains(@id,'TasksForCaseListViewWidget_') and contains(@id,'_allTasks_GROUP')]")
    private WebElement allTasksRadioButton;
    @FindBy(xpath = "//input[contains(@id,'TasksForCaseListViewWidget_') and contains(@id,'_openTasks_GROUP')]")
    private WebElement openTasksRadioButton;

    // Task tab level buttons
    @FindBy(xpath = "//input[contains(@id,'TasksForCaseListViewWidget_') and contains(@id,'_addTaskButton')]")
    private WebElement taskAddButton;
    @FindBy(xpath = "//input[contains(@id,'TasksForCaseListViewWidget_') and contains(@id,'_openTaskButton')]")
    private WebElement taskOpenButton;
    @FindBy(xpath = "//input[contains(@id,'TasksForCaseListViewWidget_') and contains(@id,'_move-button')]")
    private WebElement taskMoveButton;
    @FindBy(xpath = "//input[contains(@id,'_closeTask-button')]")
    private WebElement taskCloseButton;

    // Document tab level buttons
    @FindBy(xpath = "//input[contains(@id,'_addDocumentButton')]")
    private WebElement documentAddButton;

   //Document filter
    @FindBy(xpath = "//a[contains(@id,'filter') and contains(@onclick,'_DocumentsViewControl_BusinessType_textFilter')]")
    private WebElement filterDocumentType;
    @FindBy(xpath = "//a[contains(@id,'filter') and contains(@onclick,'DocumentsForCaseListviewWidgetTitle_TextFilterDocumentsForCaseListviewWidget_')]")
    private WebElement filterTitle;
    @FindBy(xpath = "//tr[contains(@class,'ListRow')]")
    private List<WebElement> resultRows;
    @FindBy(xpath = "//td[contains(@id,'_DocumentsViewControlMultiSelectCol0')]")
    private WebElement documentTableFirstCheckbox;
    @FindBy(xpath = "//input[contains(@id,'DocumentsForCaseListviewWidget_') and contains(@id,'_DocumentProperties')]")
    private WebElement propertiesButton;
    @FindBy(xpath = "//div[@class='pageheader_heading']/h2/span")
    private WebElement caseNumber;
    @FindBy(xpath = "//a[contains(@id,'filter') and contains(@onclick,'_DocumentsViewControl_BusinessType_textFilter')]")
    private WebElement filterBusinessType;
    @FindBy(xpath = "(//a[contains(@id,'_Outbound_Correspondence')]")
    private List<WebElement> outboundCorrespondenceDocumentLink;
    @FindBy(xpath = "//img[contains(@id,'QuickAddPropertyItemWidget_')]")
    private WebElement quickAddItemPanelWidget;
    @FindBy(xpath = "//img[contains(@id,'ClaimSource_')]")
    private WebElement claimSourceDetailsPanelWidget;
    @FindBy(xpath = "//div[contains(@class,'bottom-close')]")
    private WebElement closebtn;
    @FindBy(xpath = "//ul[contains(@class,'alertsList ps-container')]")
    private WebElement alertPopUp;

    public WebElement getCaseNumber() {
        return caseNumber;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getOkButton() {
        return okButton;
    }

    public WebElement getNextButton() {
        return nextButton;
    }

    public WebElement getRecentTab() {
        return recentCell;
    }

    public WebElement getDisplayTabSummary() {
        return displayTabSummary;
    }

    public WebElement getDisplayTabReserves() {
        return displayTabReserves;
    }

    public WebElement getDisplayTabCaseActions() {
        return displayTabCaseActions;
    }

    public WebElement getDisplayTabCaseAlias() {
        return displayTabCaseAlias;
    }


    public WebElement getMenuBarCreateClaimDropdown() {
        return menuBarCreateClaimDropdown;
    }

    public WebElement getMenuBarCreateCorrespondenceDropdown() {
        return menuBarCreateCorrespondenceDropdown;
    }

    public WebElement getMenuBarAddTaskButton() {
        return menuBarAddTaskButton;
    }

    public WebElement getMenuBarAddNoteDropdown() {
        return menuBarAddNoteDropdown;
    }

    public WebElement getCaseSummaryPanelCollapse() {
        return caseSummaryPanelCollapse;
    }

    public WebElement getQuickAddItemPanelWidget() {
        return quickAddItemPanelWidget;
    }

    public WebElement getClaimSourceDetailsPanelWidget() {
        return claimSourceDetailsPanelWidget;
    }


    public WebElement getProgressionArrowIcon() {
        return progressionArrowIcon;
    }

    public WebElement getCurrentMessageText() {
        return currentMessageText;
    }

    public WebElement getDisplayTabValidations() {
        return displayTabValidations;
    }

    public WebElement getDisplayTabDocuments() {
        return displayTabDocuments;
    }

    public WebElement getDisplayTabActivities() {
        return displayTabActivities;
    }

    public WebElement getDisplayTabTasks() {
        return displayTabTasks;
    }

    public WebElement getTaskAddButton() {
        return taskAddButton;
    }

    public WebElement getTaskOpenButton() {
        return taskOpenButton;
    }

    public WebElement getTaskMoveButton() {
        return taskMoveButton;
    }

    public WebElement getTaskCloseButton() {
        return taskCloseButton;
    }

    public List<WebElement> getResultRows() {
        return resultRows;
    }

    public WebElement getAssociatedCases() {
        return associatedCases;
    }

    public WebElement getAlertPopUp() {
        return alertPopUp;
    }

    public WebElement getClosebtn() {
        return closebtn;
    }

    public String getDALevel(final String level) {

        switch (level) {
            case LEVEL_1:
                return APPROVAL_LEVEL_1;
            case LEVEL_2:
                return APPROVAL_LEVEL_2;
            case LEVEL_3:
                return APPROVAL_LEVEL_3;
            case LEVEL_4:
                return APPROVAL_LEVEL_4;
            case LEVEL_5:
                return APPROVAL_LEVEL_5;
            default:
                return CLAIM_CONSULTANT;
        }
    }

    public void clickNextButton(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on Next button ");
        click(getNextButton());
    }

    public void clickProgressionArrowIcon(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on progression arrow Icon");
        click(getProgressionArrowIcon());
    }

    protected void clickAddSubCase(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Create/Add Claim Dropdown in the menu bar");
        click(menuBarCreateClaimDropdown);
    }

    public void clickCreateCorrespondence(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Create Correspondence Dropdown in the menu bar");
        click(menuBarCreateCorrespondenceDropdown);
        final WebElement correspondenceLink = getDriver().findElement(
            By.xpath("//span[text()='" + pageRequest.getCorrespondenceType() + "']//parent::a[contains(@id, 'DROPDOWN.Correspondence_')]"));
        click(correspondenceLink);
    }

    protected void clickAddTask(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicked on Add Task Button in the menu bar");
        click(menuBarAddTaskButton);
    }

    // Task tab buttons
    public void clickOnAllTasksRadioButton(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on All Tasks Radio Button ");
        click(allTasksRadioButton);
    }

    // Task tab buttons
    public void clickOnOpenTasksRadioButton(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on Open Tasks Radio Button ");
        click(openTasksRadioButton);
    }

    public void clickAddOnTaskButton(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on Tasks Add button ");
        click(getTaskAddButton());
    }

    public void clickOnOpenTaskButton(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on Tasks Open button ");
        click(getTaskOpenButton());
    }

    public void clickMoveOnTaskButton(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on Tasks Move button ");
        click(getTaskMoveButton());
    }

    // Use 'clickCloseOnTasks'
    @Deprecated
    public void clickCloseOnTaskTab(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on Tasks Close button ");
        click(getTaskCloseButton());
    }

    public void clickCloseOnTasks(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Tasks Close button");
        click(getTaskCloseButton());
    }

    public void clickOpenOnTasks(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Tasks Open button ");
        click(getTaskOpenButton());
    }

    protected void clickAddNote(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Add Note Dropdown in the menu bar");
        click(menuBarAddNoteDropdown);
    }

    public void clickAddButton(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking the Add button on Case Alias Widget");
        click(caseAliasAddBtn);
    }


    // MainPage buttons
    public void clickOkButton(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on OK button ");
        click(getOkButton());
    }

    public void clickSaveButton(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on Ok button ");
        click(getSaveButton(), getDriver().findElement(By.xpath("//div[@id='main']/div[@class='pageheader_heading']/span[.='Choose Next Step']")));
    }

    //display tabs
    public void clickSummaryTab(final DisplayCasePageBaseRequest pageRequest) {
        try {
            if (getAlertPopUp().isDisplayed()) {
                clickModalBox(getClosebtn());
                pageRequest.log(getPageName(), "Clicking on the Summary Tab");
                clickModalBox(displayTabSummary);
            }
        } catch (final Exception e) {
            pageRequest.log(getPageName(), "Clicking on the Summary Tab");
            click(displayTabSummary);
        }
    }

    public void clickReservesTab(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Reserves Tab");
        click(displayTabReserves);
    }

    public void clickCaseActionsTab(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Case Actions Tab");
        click(displayTabCaseActions);
    }

    public void clickCaseAliasTab(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Case Alias Tab");
        click(getDisplayTabCaseAlias());
    }

    public void clickDocumentsTab(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Documents Tab");
        clickModalBox(displayTabDocuments);
    }

    public void clickTasksTab(final DisplayCasePageBaseRequest pageRequest) {
        try {
            if (alertPopUp.isDisplayed()) {
                clickModalBox(closebtn);
                pageRequest.log(getPageName(), "Clicking on the Tasks Tab");
                click(getDisplayTabTasks());
            }
        } catch (final Exception e) {
            pageRequest.log(getPageName(), "Clicking on the Tasks Tab");
            click(getDisplayTabTasks());
        }
    }

    public void clickActivitiesTab(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Activities Tab");
        click(getDisplayTabActivities());
    }

    public void doClickCaseAliasTab(final DisplayCasePageBaseRequest pageRequest) {
        if (getWebElementAttribute(getDisplayTabCaseAlias(), "id", HTML_CLASS).equals(TAB_ON)) {
            pageRequest.log(getPageName(), "Case Alias Tab is already selected");
        } else {
            clickCaseAliasTab(pageRequest);
        }
        getAssertionHelper().assertTabIsSelected("Case Alias", displayTabCaseAlias);
        getAssertionHelper().assertTabIsSelected("Case Alias", getDisplayTabCaseAlias());
        getAssertionHelper().assertIsDisplayed("Case Alias Widget", "CaseAliasListviewWidget");
    }

    // this probably doesn't work yet, and will need some changes.
    protected void clickCaseSummaryPanelCollapse(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Collapse Button for the Case Summary Panel");
        click(caseSummaryPanelCollapse);
    }

    public void doExpandQuickAddItemPanelWidget(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Expand Image for the Quick Add Item Panel");
        if (getQuickAddItemPanelWidget().getAttribute("title").equals("Collapse this Section")) {
            pageRequest.log(getPageName(), "Quick Add Item Panel is already Expanded");
        } else {
            click(quickAddItemPanelWidget);
        }
    }

    public void doExpandClaimSourceDetailsPanelWidget(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Expand Image for the Claim Source Details Panel");
        if (getClaimSourceDetailsPanelWidget().getAttribute("title").equals("Collapse this Section")) {
            pageRequest.log(getPageName(), "Claim Source Details Panel is already Expanded");
        } else {
            click(claimSourceDetailsPanelWidget);
        }
    }

    public void clickValidationsTab(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Validations Tab");
        click(displayTabValidations);
    }

    public void selectTaskFromTable(final DisplayCasePageBaseRequest pageRequest, final String taskName) {
        pageRequest.log(getPageName(), "Clicking on Task row: " + taskName);
        selectTaskFromTaskTable(pageRequest, "//table[contains(@id,'TasksForCaseListViewWidget_') and contains(@id,'_TasksForCaseWidget')]", taskName);
    }

    public boolean isTaskFound(final DisplayCasePageBaseRequest pageRequest, final String taskName) {
        pageRequest.log(getPageName(), "Searching for Task : " + taskName);

        try {
            final WebDriverWait wait = new WebDriverWait(getDriver(), 5);
            return !wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//table[contains(@id,'TasksForCaseListViewWidget_') and contains(@id,'_TasksForCaseWidget')]/tbody/tr/td[contains(.,'" + taskName + "')]"))
            ).isEmpty();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickOnDocumentAddButton(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Document Add Button ");
        click(documentAddButton);
    }

    public void clickBusinessTypeFilter(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Business Type Filter");
        clickModalBox(filterBusinessType);
        filter.setFilter("DocumentsForCaseListviewWidget", "DocumentsViewControl", "BusinessType");
    }

    public void clickDocumentTypeFilter(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Document Type Filter");
        clickModalBox(filterDocumentType);
        filter.setFilter(DOCUMENTS_FOR_CASE_WIDGET_ID, "DocumentsViewControl", "BusinessType");
    }

    public void clickTitleFilter(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Title Filter");
        clickModalBox(filterTitle);
        filter.setFilter(DOCUMENTS_FOR_CASE_WIDGET_ID, "DocumentsViewControl", "Title");
    }

    public void selectFirstCheckbox(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on First Checkbox");
        clickModalBox(documentTableFirstCheckbox);
    }

    public String getCurrentStatus(final DisplayCasePageClaimBase.DisplayCasePageClaimBaseRequest pageRequest) {
        final String currentStatus = getDriver().findElements(By.xpath("//dl[contains(@class,'status')]/dd[1]")).stream()
            .findFirst()
            .map(WebElement::getText)
            .orElse("");

        pageRequest.log(getPageName(), "Stage progress Current Status : " + currentStatus);

        return currentStatus;
    }

    public void clickOnDocumentPropertiesButton(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Document Properties Button ");
        clickModalBox(propertiesButton);
    }

    public void clickOutboundCorrespondenceDocumentLinkToOpen(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Outbound Correspondence Document Link To Open");
        clickModalBox(outboundCorrespondenceDocumentLink.get(0));
    }

    public void clickLinkParty(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Click on Link Party");
        clickModalBox(linkParty);
    }

    public void clickBrokerParty(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Click on Broker Party");
        clickModalBox(brokerPartyLink);
    }

    public static class DisplayCasePageBaseRequest extends AbstractPageRequest {

        public DisplayCasePageBaseRequest(final TestCaseContext context) {
            super(context);
        }

        public String getCorrespondenceType() {
            return get("CorrespondenceType");
        }

        public String getTaskName() {
            return get("TaskName");
        }

        public String getBusinessType() {
            return get("BusinessType");
        }

        public String getDocumentType() {
            return get("DocumentType");
        }

        public String getDocumentName() {
            return get("DocumentName");
        }

        public String getTitle() {
            return get("title");
        }

        public String getNotificationStatus() {
            return get("NotificationStatus");
        }

        public String getClaimStatus() {
            return get("ClaimStatus");
        }

        public String getBenefitStatus() {
            return get("BenefitStatus");
        }

        public String getAutoCloseType() {
            return get("AutoCloseType");
        }
    }
}
