package com.gb.fineos.page.payment.benefitspaymentmanager.payments.overpayments.recovery;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import com.gb.fineos.screencapture.ScreenCapture;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class AddActualRecoveryPage extends BasePage {
    @FindBy(xpath = "//input[contains(@id,'_Date_of_Recovery')]")
    private WebElement dateOfRecovery;

    @FindBy(xpath = "//input[contains(@id,'_Check_Name')]")
    private WebElement checkName;

    @FindBy(xpath = "//input[contains(@id,'_Recovery_Amount')]")
    private WebElement recoveryAmount;

    @FindBy(xpath = "//input[contains(@id,'_Check_Number')]")
    private WebElement cashReceiptNumber;

    @FindBy(xpath = "//input[contains(@id,'_editPageSave_cloned')]")
    private WebElement saveButton;

    @FindBy(xpath = "//input[contains(@id,'_editPageCancel_cloned')]")
    private WebElement cancelButton;

    public AddActualRecoveryPage() {
        super("ADD ACTUAL RECOVERY PAGE");
    }

    public boolean hasValidationMessages(final AddActualRecoveryPageRequest pageRequest, final String key) {
        ScreenCapture.logScreenshot(getPageName(), "hasValidationMessages", LogStatus.INFO);

        return getDriver().findElements(By.xpath("//table[@id='val_message_table']/tbody/tr/td[@class='raiseMessageText']/ul/li/span"))
            .stream()
            .map(WebElement::getText)
            .collect(toList())
            .containsAll(pageRequest.getExpectedValidationMessages(key));
    }

    public void enterDateOfRecovery(final AddActualRecoveryPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Inputting Date of Recovery " + pageRequest.getDateOfRecovery());
        clearAndInput(pageRequest.getDateOfRecovery(), dateOfRecovery);
    }

    public void enterRecoveryAmount(final AddActualRecoveryPageRequest pageRequest) {
        pageRequest.log(getPageName(), " Entering Recovery Amount " + pageRequest.getRecoveryAmount());
        clearAndInput(pageRequest.getRecoveryAmount(), recoveryAmount);
    }

    public void enterCashReceiptNumber(final AddActualRecoveryPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Amount to Submit " + pageRequest.getCashReceiptNumber());
        clearAndInput(pageRequest.getCashReceiptNumber(), cashReceiptNumber);
    }

    public void clickSaveButton(final AddActualRecoveryPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Save button");
        click(saveButton);
    }

    public void clickCancelButton(final AddActualRecoveryPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Cancel button");
        click(cancelButton);
    }

    public static class AddActualRecoveryPageRequest extends AbstractPageRequest {

        public AddActualRecoveryPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getDateOfRecovery() {
            return get("Date_Of_Recovery");
        }

        public String getRecoveryAmount() {
            return get("Recovery_Amount");
        }

        public String getCashReceiptNumber() {
            return get("Cash_Receipt_Number");
        }

        public List<String> getExpectedValidationMessages(final String key) {
            return Arrays.asList(get(key).split(","));
        }
    }
}
