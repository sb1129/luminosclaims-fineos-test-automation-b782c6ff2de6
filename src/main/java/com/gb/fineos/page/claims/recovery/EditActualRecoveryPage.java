package com.gb.fineos.page.claims.recovery;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditActualRecoveryPage extends BasePage {

    @FindBy(xpath = "//input[contains(@id,'_actualRecoveryDateTimeControlBean')]")
    private WebElement recoveryDateTextBox;

    @FindBy(xpath = "//input[contains(@id,'_RecoveredAmount')]")
    private WebElement totalRecoveredAmountMoneybox;

    @FindBy(xpath = "//input[contains(@id,'_paymentReferenceCheckName')]")
    private WebElement paymentReferenceNameTextbox;

    @FindBy(xpath = "//input[contains(@id,'_TaxRecoveredAmount')]")
    private WebElement taxAmountTextbox;

    @FindBy(xpath = "//input[contains(@id,'_paymentReferenceCheckNumber')]")
    private WebElement cashReceiptTextBox;

    @FindBy(xpath = "//select[contains(@id,'_paymentMethodDropDown')]")
    private WebElement paymentMethodDropdown;

    @FindBy(xpath = "//select[contains(@id,'_anticipatedRecoveryDropDownBean')]")
    private WebElement anticipatedDropdown;

    @FindBy(xpath = "//input[contains(@id,'_editPageSave_cloned')]")
    private WebElement recoverySaveButton;

    @FindBy(xpath = "//input[contains(@id,'_relControlRecoveredFrom_rcbSearchButton_relControlRecoveredFrom')]")
    private WebElement partySearchButton;

    @FindBy(xpath = "//input[contains(@id,'_RecoveredAmountTE')]")
    private WebElement recoveredAmountForReserveMoneybox;

    @FindBy(xpath = "//select[contains(@id,'_AnticipatedReasonForUpdate')]")
    private WebElement updateReasonDropdown;

    @FindBy(xpath = "//select[contains(@id,'_anticipatedRecoveryDropDownBean')]")
    private WebElement updateAnticipated;

    @FindBy(xpath = "//input[contains(@id,'_DAMAmount')]")
    private WebElement damAmountMoneybox;

    public EditActualRecoveryPage() {
        super("EDIT ACTUAL RECOVERY PAGE");
    }

    public void enterRecoveredAmountForReserve(final EditActualRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Enter Recovered Amount for Reserve as: " + pageRequest.getRecoveredForReserveAmount());
        clearAndInput(pageRequest.getRecoveredForReserveAmount(), recoveredAmountForReserveMoneybox, getIFrameDisableLayer().orElse(null));
    }

    public void selectAnticipated(final EditActualRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Select Anticipated as: " + pageRequest.selectAnticipated());
        selectValue(pageRequest.selectAnticipated(), anticipatedDropdown);
    }

    public void selectPaymentMethod(final EditActualRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Select Payment Method as: " + pageRequest.selectPaymentMethod());
        selectValue(pageRequest.selectPaymentMethod(), paymentMethodDropdown);
    }

    public void enterCashReceiptNumber(final EditActualRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Cash Receipt as: " + pageRequest.getCashReceiptNumber());
        clearAndInput(pageRequest.getCashReceiptNumber(), cashReceiptTextBox, getIFrameDisableLayer().orElse(null));
    }

    public void enterPaymentReferenceName(final EditActualRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Payment Reference Name as: " + pageRequest.getPaymentReferenceName());
        clearAndInput(pageRequest.getPaymentReferenceName(), paymentReferenceNameTextbox, getIFrameDisableLayer().orElse(null));
    }

    public void enterTotalRecoveredAmount(final EditActualRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Total Recovered Amount as: " + pageRequest.getTotalRecoveredAmount());
        clearAndInput(pageRequest.getTotalRecoveredAmount(), totalRecoveredAmountMoneybox, getIFrameDisableLayer().orElse(null));
    }

    public void enterRecoveryDate(final EditActualRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Recovery Date: " + pageRequest.getRecoveryDate());
        clearAndInput(pageRequest.getRecoveryDate(), recoveryDateTextBox, getIFrameDisableLayer().orElse(null));
    }

    public void enterTaxAmount(final EditActualRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Tax Amount: " + pageRequest.getTaxAmount());
        clearAndInput(pageRequest.getTaxAmount(), taxAmountTextbox, getIFrameDisableLayer().orElse(null));
    }

    public void clickToSaveRecovery(final EditActualRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking save button");
        click(recoverySaveButton);
    }

    public void clickToSearchParty(final EditActualRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking search party button");
        click(partySearchButton);
    }

    public void selectUpdateReason(final EditActualRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Select update reason");
        selectValue(pageRequest.getUpdateReason(), updateReasonDropdown);
    }

    public void selectUpdateAnticipated(final EditActualRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Select update Anticipated");
        selectValue(pageRequest.getUpdateAnticipated(), updateAnticipated);
    }

    public void enterDamAmount(final EditActualRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Dam Amount: " + pageRequest.getTaxAmount());
        clearAndInput(pageRequest.getDamAmount(), damAmountMoneybox, getIFrameDisableLayer().orElse(null));
    }

    public static class EditActualRecoveryRequest extends AbstractPageRequest {

        public EditActualRecoveryRequest(final TestCaseContext context) {
            super(context);
        }

        public String getRecoveryDate() {
            return get("Actual Recovery Date");
        }

        public String getCashReceiptNumber() {
            return get("Cash Receipt Number");
        }

        public String selectAnticipated() {
            return get("Anticipated Recovery");
        }

        public String selectPaymentMethod() {
            return get("Payment Method");
        }

        public String getPaymentReferenceName() {
            return get("Payment Reference Name");
        }

        public String getRecoveredForReserveAmount() {
            return get("Recovered Amount For Reserve");
        }

        public String getTotalRecoveredAmount() {
            return get("Total Recovered Amount");
        }

        public String getTaxAmount() {
            return get("Tax Amount");
        }

        public String getUpdateReason() {
            return get("Update Reason");
        }

        public String getDamAmount() {
            return get("Dam Amount");
        }

        public String getUpdateAnticipated() {
            return get("Update Anticipated");
        }
    }
}


