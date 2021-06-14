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

public class AddRecoveryPlanPage extends BasePage {
    @FindBy(xpath = "//input[contains(@id,'_Agreement_Date')]")
    private WebElement agreementDate;

    @FindBy(xpath = "//select[contains(@id,'_Type')]")
    private WebElement typeDropdown;

    @FindBy(xpath = "//input[contains(@id,'_Amount_to_Submit')]")
    private WebElement amountToSubmit;

    @FindBy(xpath = "//input[contains(@id,'_Submit_By_Date')]")
    private WebElement submitByDate;

    @FindBy(xpath = "//input[contains(@id,'_Payment_Frequency')]")
    private WebElement paymentFrequencyDropdown;

    @FindBy(xpath = "//input[contains(@id,'_No_of_Payments')]")
    private WebElement numberOfPayments;

    @FindBy(xpath = "//input[contains(@id,'_editPageSave_cloned')]")
    private WebElement saveButton;

    @FindBy(xpath = "//input[contains(@id,'_editPageCancel_cloned')]")
    private WebElement cancelButton;

    public AddRecoveryPlanPage() {
        super("ADD RECOVERY PLAN PAGE");
    }

    public boolean hasValidationMessages(final AddRecoveryPlanPageRequest pageRequest, final String key) {
        ScreenCapture.logScreenshot(getPageName(), "hasValidationMessages", LogStatus.INFO);

        return getDriver().findElements(By.xpath("//table[@id='val_message_table']/tbody/tr/td[@class='raiseMessageText']/ul/li/span"))
            .stream()
            .map(WebElement::getText)
            .collect(toList())
            .containsAll(pageRequest.getExpectedValidationMessages(key));
    }

    public void enterAgreementDate(final AddRecoveryPlanPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Inputting Agreement date " + pageRequest.getAgreementDate());
        clearAndInput(pageRequest.getAgreementDate(), agreementDate);
    }

    public void selectType(final AddRecoveryPlanPageRequest pageRequest) {
        pageRequest.log(getPageName(), " Entering Type as: " + pageRequest.getTypeSelectIndex());
        selectByIndex(pageRequest.getTypeSelectIndex(), typeDropdown);
    }

    public void enterAmountToSubmit(final AddRecoveryPlanPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Amount to Submit " + pageRequest.getAmountToSubmit());
        clearAndInput(pageRequest.getAmountToSubmit(), amountToSubmit);
    }

    public void clickSaveButton(final AddRecoveryPlanPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Save button");
        click(saveButton);
    }

    public void clickCancelButton(final AddRecoveryPlanPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Cancel button");
        click(cancelButton);
    }

    public static class AddRecoveryPlanPageRequest extends AbstractPageRequest {

        public AddRecoveryPlanPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getAgreementDate() {
            return get("Agreement_Date");
        }

        public String getTypeSelectIndex() {
            return get("Type_Select_Index");
        }

        public String getAmountToSubmit() {
            return get("Amount_To_Submit");
        }

        public List<String> getExpectedValidationMessages(final String key) {
            return Arrays.asList(get(key).split(","));
        }
    }
}
