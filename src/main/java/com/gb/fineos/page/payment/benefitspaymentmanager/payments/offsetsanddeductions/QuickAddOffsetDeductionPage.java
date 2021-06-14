package com.gb.fineos.page.payment.benefitspaymentmanager.payments.offsetsanddeductions;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class QuickAddOffsetDeductionPage extends BasePage {


    @FindBy(xpath = "//input[contains(@id,'QuickAddOffsetsAndDeductionsDetailsWrapperWidget') and contains(@id,'_HiddenButton_cloned')]")
    private WebElement okButton;


    @FindBy(xpath = "//input[contains(@id,'QuickAddOffsetsAndDeductionsDetailsWrapperWidget') and contains(@id,'_QuickAddButton')]")
    private WebElement addButton;

    @FindBy(xpath = "//select[contains(@id,'com.fineos.payments.benefitspaymentmanager.payments.offsetsanddeductions.FlatAmountIncreaseInstrEditDetailsWidget') and contains(@id,'_signDropDownBean')]")
    private WebElement addFXAdjustmentAmountDropDownBean;

    @FindBy(xpath = "//input[contains(@id,'FlatAmountIncreaseInstrEditDetailsWidget') and contains(@id,'_Adjustment_Amount')]")
    private WebElement adjustmentAmount;

    @FindBy(xpath = "//td[contains(@title, 'FX Adjustment')]")
    private WebElement fxAdjustmentRow;

    @FindBy(xpath = "//input[contains(@id,'_Reference')]")
    private WebElement adjustmentReference;

    public QuickAddOffsetDeductionPage() {
        super("OFFSET AND DEDUCTION PAGE");
    }


    public void clickOkButton(final QuickAddOffsetDeductionPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking OK button");
        click(okButton);
    }

    public void selectAdjustmentType(final QuickAddOffsetDeductionPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting Adjustment type: " + pageRequest.getAdjustmentType());
        click(getDriver().findElement(By.xpath("//td[contains(@title, '" + pageRequest.getAdjustmentType() + "')]")));
    }


    public void enterAdjustmentReference(final QuickAddOffsetDeductionPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Adjustment Reference: " + pageRequest.getAdjustmentReference());
        clearAndInput(pageRequest.getAdjustmentReference(), adjustmentReference, getIFrameDisableLayer().orElse(null));
    }

    public void clickAddButton(final QuickAddOffsetDeductionPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Add  button");
        click(addButton);
    }

    public void clickFXAdjustmentRow(final QuickAddOffsetDeductionPage.QuickAddOffsetDeductionPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on FX Adjustment  row: " + pageRequest.getPaymentReference());
        click(fxAdjustmentRow);
    }


    public void add(final QuickAddOffsetDeductionPage.QuickAddOffsetDeductionPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Add  button");
        click(addButton);
    }

    public void selectPositiveAdjustment(final QuickAddOffsetDeductionPage.QuickAddOffsetDeductionPageRequest pageRequest) {
        pageRequest.log(getPageName(), " Selecting Adjustment Type as: " + pageRequest.getFXAdjustmentType());
        selectValue("+", addFXAdjustmentAmountDropDownBean);
    }

    public void selectNegativeAdjustment(final QuickAddOffsetDeductionPage.QuickAddOffsetDeductionPageRequest pageRequest) {
        pageRequest.log(getPageName(), " Selecting Adjustment Type as: " + pageRequest.getFXAdjustmentType());
        selectValue("-", addFXAdjustmentAmountDropDownBean);
    }

    public void enterAdjustmentAmount(final QuickAddOffsetDeductionPage.QuickAddOffsetDeductionPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Adjustment amount as: " + pageRequest.getAdjustmentAmount());
        clearAndInput(pageRequest.getAdjustmentAmount(), adjustmentAmount, getIFrameDisableLayer().orElse(null));
    }

    public static class QuickAddOffsetDeductionPageRequest extends AbstractPageRequest {
        public QuickAddOffsetDeductionPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getAdjustmentType() {
            return get("Adjustment_Type");
        }

        public String getPaymentReference() {
            return get("PaymentReference");
        }

        public String getFXAdjustmentType() {
            return get("FXAdjustmentType");
        }

        public String getAdjustmentAmount() {
            return get("Adjustment_Amount");
        }

        public String getAdjustmentReference() {
            return get("Adjustment_Reference");
        }
    }
}
