package com.gb.fineos.page.claims.recovery;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditAnticipatedRecoveryPage extends BasePage {

    @FindBy(xpath = "//input[contains(@id,'_AnticipatedRecoveryDate')]")
    private WebElement recoveryDateTextBox;

    @FindBy(xpath = "//input[contains(@id,'_TotalAnticipatedRecoveryAmount')]")
    private WebElement anticipatedAmountMoneybox;

    @FindBy(xpath = "//input[contains(@id,'_AnticipatedRecoveryPercentage')]")
    private WebElement recoveryPercentBox;

    @FindBy(xpath = "//textarea[contains(@id,'_RecoveryDescription')]")
    private WebElement recoveryDesciptionTextArea;

    @FindBy(xpath = "//textarea[contains(@id,'_AnticipatedUpdateReasonDescription')]")
    private WebElement recoveryUpdateDesciptionTextArea;

    @FindBy(xpath = "//input[contains(@id,'_editPageSave_cloned')]")
    private WebElement recoverySaveButton;

    @FindBy(xpath = "//input[contains(@id,'_relControlRecoveredFrom_rcbSearchButton_relControlRecoveredFrom')]")
    private WebElement partySearchButton;

    @FindBy(xpath = "//select[contains(@id,'_AnticipatedReasonForUpdate')]")
    private WebElement updateReasonDropdown;

    @FindBy(xpath = "//input[contains(@id,'_anticipatedRecoveryAmountBeanTL')]")
    private WebElement reserveAmountMoneybox;

    public EditAnticipatedRecoveryPage() {
        super("EDIT ANTICIPATED RECOVERY PAGE");
    }

    public void enterRecoveryDescription(final EditAnticipatedRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Recovery Description as: " + pageRequest.getRecoveryDescription());
        clearAndInput(pageRequest.getRecoveryDescription(), recoveryDesciptionTextArea, getIFrameDisableLayer().orElse(null));
    }

    public void enterReserveAmount(final EditAnticipatedRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Enter Reserve Amount as: " + pageRequest.getReserveAmount());
        clearAndInput(pageRequest.getReserveAmount(), reserveAmountMoneybox, getIFrameDisableLayer().orElse(null));
    }

    public void enterUpdateRecoveryDescription(final EditAnticipatedRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Update Recovery Description as: " + pageRequest.getRecoveryDescription());
        clearAndInput(pageRequest.getUpdateRecoveryDescription(), recoveryUpdateDesciptionTextArea, getIFrameDisableLayer().orElse(null));
    }

    public void enterAnticipatedAmount(final EditAnticipatedRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Anticipated Amount as: " + pageRequest.getAnticipatedAmount());
        clearAndInput(pageRequest.getAnticipatedAmount(), anticipatedAmountMoneybox, getIFrameDisableLayer().orElse(null));
    }

    public void enterRecoveryDate(final EditAnticipatedRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Recovery Date as: " + pageRequest.getRecoveryDate());
        clearAndInput(pageRequest.getRecoveryDate(), recoveryDateTextBox, getIFrameDisableLayer().orElse(null));
    }

    public void enterRecoveryPercent(final EditAnticipatedRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Recovery Percent as: " + pageRequest.getRecoveryPercent());
        clearAndInput(pageRequest.getRecoveryPercent(), recoveryPercentBox, getIFrameDisableLayer().orElse(null));
    }

    public void clickToSaveRecovery(final EditAnticipatedRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking save button");
        click(recoverySaveButton);
    }

    public void clickToSearchParty(final EditAnticipatedRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking search party button");
        click(partySearchButton);
    }

    public void selectUpdateReason(final EditAnticipatedRecoveryRequest pageRequest) {
        pageRequest.log(getPageName(), "Select update reason");
        selectValue(pageRequest.getUpdateReason(), updateReasonDropdown);
    }

    public static class EditAnticipatedRecoveryRequest extends AbstractPageRequest {

        public EditAnticipatedRecoveryRequest(final TestCaseContext context) {
            super(context);
        }

        public String getRecoveryDate() {
            return get("Recovery Date");
        }

        public String getAnticipatedAmount() {
            return get("Anticipated Amount");
        }

        public String getRecoveryDescription() {
            return get("Recovery Description");
        }

        public String getUpdateReason() {
            return get("Reason Update");
        }

        public String getUpdateRecoveryDescription() {
            return get("Update Recovery Description");
        }

        public String getRecoveryPercent() {
            return get("Recovery Percent");
        }

        public String getReserveAmount() {
            return get("Reserve Amount");
        }
    }
}


