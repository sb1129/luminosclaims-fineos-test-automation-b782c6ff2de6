package com.gb.fineos.page.sharedpages.casemanager.displaycase;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.screencapture.ScreenCapture;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class DisplayCasePageBenefitBase extends DisplayCasePageBase {

    @FindBy(xpath = "//input[contains(@id,'_com.fineos.claims.reserves.reservecontainer.ReserveContainerWidgetEDIT_LINKEDIT_BUTTONReserves')]")
    private WebElement editReserveButton;

    @FindBy(xpath = "//input[contains(@id, '_listviewBean_cmdAdd')]")
    private WebElement addPaymentButton;

    @FindBy(xpath = "//input[contains(@id,'_listviewBean_cmdEdit')]")
    private WebElement editPaymentsButton;

    @FindBy(xpath = "//input[contains(@id, '_listviewBean_cmdView')]")
    private WebElement viewPaymentButton;

    @FindBy(xpath = "//td[contains(text(),'Coverage Approval Required')]")
    private WebElement coverageApprovalRequired;
    @FindBy(xpath = "//table[contains(@id,'TasksForCaseListViewWidget_') and contains(@id,'_TasksForCaseWidget')]/tbody/tr")
    private List<WebElement> tasksTable;
    @FindBy(xpath = "//td[contains(text(),'Reserve Approval Required')]")
    private WebElement reserveApprovalRequired;

    @FindBy(xpath = "//td[contains(@id, '_CaseTabControlBean_AdhocDues_cell')]")
    private WebElement displayTabPayment;
    @FindBy(xpath = "//td[contains(@id, '_CaseTabControlBean_PaymentHistoryBenefitViewTab_cell')]")
    private WebElement displayTabPaymentHistory;
    @FindBy(xpath = "//td[contains(@id, '_CaseTabControlBean_BenefitGeneralTab_cell')]")
    private WebElement displayTabBenefit;
    @FindBy(xpath = "//input[contains(@id,'DocumentsForCaseListviewWidget_') and contains(@id,'_addDocumentButton')]")
    private WebElement documentAddButton;
    @FindBy(xpath = "//input[contains(@id,'Adhoc_Dues_') and contains(@id,'_ApproveRecover')]")
    private WebElement approveRecoverButton;
    // link party
    @FindBy(xpath = "//a[contains(@id, '_MENUBAR.CaseSubjectMenu.PaymentBenefit_DROPDOWN.AddParticipant_DROPDOWN.AddParticipantlink')]")
    private WebElement linkPartyDropdown;
    @FindBy(xpath = "//a[contains(@id, '_MENUBAR.CaseSubjectMenu.PaymentBenefit_DROPDOWN.AddParticipant_Payee_Payeelink')]")
    private WebElement payeeLink;
    @FindBy(xpath = "(//a[contains(@id,'_Outbound_Correspondence')])[1]")
    private WebElement outboundCorrespondenceDocumentLink;
    @FindBy(xpath = "//div[contains(@class,'pageheader_heading')]/h2")
    private WebElement benefitName;

    // Popup buttons on Final payment confirmation.
    @FindBy(xpath = "//input[contains(@id, 'NoFurtherAction')]")
    private WebElement noFurtherActionBtn;
    @FindBy(xpath = "//input[contains(@id, 'ClearLinkedReserves')]")
    private WebElement clearLinkedReservesBtn;

    //Recoveries
    @FindBy(xpath = "//td[contains(@id, '_BenefitRecoveriesSubtab_cell')]")
    private WebElement displayTabRecoveries;
    @FindBy(xpath = "//input[contains(@id, '_addAnticipatedButton')]")
    private WebElement addAnticipatedButton;
    @FindBy(xpath = "//input[contains(@id, '_recoveriesLVBean_cmdEdit')]")
    private WebElement editRecoveryButton;
    @FindBy(xpath = "//input[contains(@id, '_recoveriesLVBean_cmdView')]")
    private WebElement viewRecoveryButton;
    @FindBy(xpath = "//input[contains(@id, '_addActualButton')]")
    private WebElement addActualButton;

    //Select Actual Recovery
    @FindBy(xpath = "//td[contains(@title,'Actual')]")
    private WebElement actualRecovery;

    public DisplayCasePageBenefitBase() {
        super("BENEFIT DISPLAY CASE PAGE");
    }

    public WebElement getBenefitName() {
        return benefitName;
    }

    public WebElement getApproveRecoverButton() {
        return approveRecoverButton;
    }

    public void clickOnEditButton(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Edit Reserve button ");
        click(editReserveButton);
    }

    public void clickOnReserveApprovalRequired(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Task : " + pageRequest.getTaskName());
        clickRowInTable(pageRequest, tasksTable, pageRequest.getTaskName());
    }

    public void verifyReserveApprovalRequiredTask(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Verifying Reserve Approval Required task in Task Tab ");
        if (isElementPresent(reserveApprovalRequired)) {
            pageRequest.log(getPageName(), "Reserve Approval Required task present in Task Tab ");
        }
    }

    private WebElement getReserveTableRow(final DisplayCasePageBenefitBaseRequest pageRequest) {
        return getDriver().findElements(By.xpath("//table[contains(@id, '_ReservesListView')]//td[@title='" + pageRequest.getReserveType() + "']")).stream()
            .map(webElement -> webElement.findElement(By.xpath("parent::tr")))
            .findFirst().orElseThrow(() -> new AssertionError("Unable to find Reserve Type : " + pageRequest.getReserveType()));
    }

    public boolean isReservePaidToDateValid(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Verifying Reserve Paid To Date is " + pageRequest.getAmountAppliedToReserve());
        return !getReserveTableRow(pageRequest).findElements(By.xpath("td[@title='" + pageRequest.getAmountAppliedToReserve() + "']")).isEmpty();
    }

    public boolean isReserveApproved(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Verifying Reserve Approved");
        return !getReserveTableRow(pageRequest).findElements(By.xpath("td[contains(@title, 'Approved')]")).isEmpty();
    }

    public boolean isReserveOutstandingAmountValid(final DisplayCasePageBenefitBaseRequest pageRequest) {
        final WebElement reserveTableRow = getReserveTableRow(pageRequest);
        final BigDecimal reserveAmount = Optional.of(reserveTableRow.findElement(By.xpath("td[2]")))
            .map(WebElement::getText)
            .map(str -> str.replace(",", ""))
            .map(BigDecimal::new)
            .orElseThrow(() -> new AssertionError("Unable to find Reserve Amount for : " + pageRequest.getReserveType()));

        final BigDecimal expectedReserveOutstandingAmount = reserveAmount.subtract(new BigDecimal(pageRequest.getAmountAppliedToReserve()));

        pageRequest.log(getPageName(), "Verifying Reserve Outstanding Amount is " + expectedReserveOutstandingAmount);

        final BigDecimal actualReserveOutstandingAmount = Optional.of(reserveTableRow.findElement(By.xpath("td[4]")))
            .map(WebElement::getText)
            .map(str -> str.replace(",", ""))
            .map(BigDecimal::new)
            .orElseThrow(() -> new AssertionError("Unable to find Outstanding Reserve Amount for : " + pageRequest.getReserveType()));

        final boolean matches = actualReserveOutstandingAmount.compareTo(expectedReserveOutstandingAmount) == 0;

        if (!matches) {
            pageRequest.warning(getPageName(), "Actual Reserve Outstanding Amount: " + actualReserveOutstandingAmount + " does not match: " + expectedReserveOutstandingAmount, false);
        }

        return matches;
    }

    public boolean hasPaymentHistory(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Verifying Payment appears in Payment History. " + pageRequest.getAmountAppliedToReserve());
        return !getDriver().findElements(By.xpath("//table[contains(@id, '_PaymentHistoryDetailsListview')]//td[@title='" + pageRequest.getAmountAppliedToReserve() + "']")).isEmpty();
    }

    // Tabs Click
    public void clickOnTaskTab(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Task Tab");
        click(getDisplayTabTasks());
    }

    public void clickPaymentTab(final DisplayCasePageBenefitBaseRequest pageRequest) {
        final WebDriverWait wait = new WebDriverWait(getDriver(), 100);
        wait.until(ExpectedConditions.visibilityOf(displayTabPayment));

        pageRequest.log(getPageName(), "Clicking on the Payment Tab");
        click(displayTabPayment);
    }


    public void clickOnDocumentAddButton(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Document Add Button ");
        click(documentAddButton);
    }

    public void clickPaymentHistoryTab(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Payment History Tab");
        click(displayTabPaymentHistory);
    }

    private void clickBenefitTab(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Benefit Tab");
        click(displayTabBenefit);
    }

    public void clickAddPaymentButton(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Add Button");
        click(addPaymentButton);
    }

     public void clickEditPaymentsButton(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Edit Button");
        click(editPaymentsButton);
    }

    public void clickViewPaymentButton(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the View Button");
        click(viewPaymentButton);
    }

    public void clickApproveRecoverButton(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Approve/Recover Button");
        click(approveRecoverButton);
    }

    public void handlePopUpWidgetWrapper(final DisplayCasePageBenefitBaseRequest pageRequest) {
        clickOnContinueButton(pageRequest);
        getDriver().findElements(By.id("AdditionalAdhocDueApprovalActionsPopupWidget_PopupWidgetWrapper"))
            .stream()
            .filter(WebElement::isDisplayed)
            .findFirst()
            .ifPresent(webElement -> {
                ScreenCapture.logScreenshot("Additional Approval Actions Popup", LogStatus.INFO);

                if (pageRequest.isFinalPayment()) {
                    clickOnClearLinkedReserveButton(pageRequest);
                } else {
                    clickOnNoFurtherActionButton(pageRequest);
                }
            });
    }

    public void clickLinkParty(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Link Party Dropdown");
        click(linkPartyDropdown);
    }

    public void clickPayeeLink(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Payee");
        click(payeeLink);
    }

    //payments
    public void selectPaymentsFromTable(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on payments row: " + pageRequest.getPaymentType());
        selectTaskFromTaskTable(pageRequest, "//table[contains(@id, 'Adhoc_Dues_') and contains(@id,'_listviewBean')]", pageRequest.getPaymentType());
    }

    // Tabs Click
    public void clickCoverageApprovalRequired(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on coverage Approval Required ");
        click(coverageApprovalRequired);
    }

    public String currentBenefitName(final DisplayCasePageBenefitBaseRequest pageRequest) {
        return getText(getBenefitName()).toUpperCase().trim();
    }

    public void clickOnContinueButton(final DisplayCasePageBenefitBaseRequest pageRequest) {
        getDriver().findElements(By.xpath("//input[contains(@id, 'continueButton')]"))
            .stream()
            .findFirst()
            .ifPresent(continueButton -> click(continueButton));
    }

    public void clickOnNoFurtherActionButton(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on No further action");
        clickModalBox(noFurtherActionBtn);
    }

    public void clickOnClearLinkedReserveButton(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on All Clear Linked Reserves button");
        clickModalBox(clearLinkedReservesBtn);
    }

    public boolean verifyPaymentApprovalMessage(final DisplayCasePageBenefitBaseRequest pageRequest, final String expectedStatus) {
        String currentMessage = getText(getCurrentMessageText());
        pageRequest.log(getPageName(), "Payment approval message is : " + currentMessage);
        return expectedStatus.equalsIgnoreCase(currentMessage);
    }

    public void clickRecoveriesTab(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Recoveries Tab");
        click(displayTabRecoveries, getIFrameDisableLayer().orElse(null));
    }

    public void clickAddAnticipatedButton(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Add Anticipated Button");
        click(addAnticipatedButton);
    }

    public void clickEditRecovery(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Edit Recovery Button");
        click(editRecoveryButton);
    }

    public void clickViewRecovery(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the View Recovery Button");
        click(viewRecoveryButton);
    }

    public void clickAddActualButton(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Add Actual Button");
        click(addActualButton);
    }

    public void clickActualRecovery(final DisplayCasePageBenefitBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Actual Recovery");
        click(actualRecovery);
    }

    public static class DisplayCasePageBenefitBaseRequest extends DisplayCasePageBase.DisplayCasePageBaseRequest {
        public DisplayCasePageBenefitBaseRequest(final TestCaseContext context) {
            super(context);
        }

        public String getReserveType() {
            return get("ReserveType");
        }

        public String getReserveAmount() {
            return get("ReserveExpenseAmount");
        }

        public String getReserveStatus() {
            return get("ReserveStatus");
        }

        public String getTaskName() {
            return get("TaskName");
        }

        public String getPaymentType() {
            return get("Payment Type");
        }

        public boolean isFinalPayment() {
            return getOrDefault("FinalPayment", "No").equalsIgnoreCase("Yes");
        }

        public String getAmountAppliedToReserve() {
            return get("ValidAmountAppliedToReserve");
        }
    }
}
