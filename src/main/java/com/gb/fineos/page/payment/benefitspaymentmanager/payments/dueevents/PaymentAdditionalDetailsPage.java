package com.gb.fineos.page.payment.benefitspaymentmanager.payments.dueevents;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentAdditionalDetailsPage extends BasePage {

    @FindBy(xpath = "//input[contains(@id,'_Allow_Statistical_Transaction_CHECKBOX')]")
    private WebElement statisticalPaymentCheckBox;

    @FindBy(xpath = "//input[contains(@id,'_Is_Final_Payment_CHECKBOX')]")
    private WebElement finalPaymentCheckBox;

    @FindBy(xpath = "//input[contains(@id,'_InvoiceDate')]")
    private WebElement invoiceDate;

    @FindBy(xpath = "//td[contains(@id,'_Due_Amount_cell')]")
    private WebElement paymentTab;

    @FindBy(xpath = "//input[contains(@id,'GBFxAmountWidget') and contains(@id,'_fxamount')]")
    private WebElement fxAmountTextBox;

    @FindBy(xpath = "//select[contains(@id,'GBFxAmountWidget') and contains(@id,'_currencyEnumDropDownBean')]")
    private WebElement fxCurrencyDropDown;

    @FindBy(xpath = "//input[contains(@id,'GBFxAmountWidget') and contains(@id,'_fxRateDecimal')]")
    private WebElement fxRateTextBox;

     @FindBy(xpath = "//input[contains(@id,'GBFxAmountWidget') and contains(@id,'_editFXButtonBean')]")
    private WebElement calculateFXRateButton;

    @FindBy(xpath = "//input[contains(@id,'_cmdPageBack_cloned')]")
    private WebElement closeButton;

    public PaymentAdditionalDetailsPage() {
        super("PAYMENT - ADDITIONAL DETAILS TAB");
    }

    public boolean isStatisticalPaymentValid(final PaymentAdditionalDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Verifying Statistical payment checkbox is: " + pageRequest.isStatisticalPayment());
        return getDriver().findElements(By.xpath("//img[contains(@id, '_Allow_Statistical_Transaction') and contains(@src, 'unticked.gif')]")).isEmpty() == pageRequest.isStatisticalPayment();
    }

    public boolean isFinalPaymentValid(final PaymentAdditionalDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Verifying Final payment checkbox is: " + pageRequest.isFinalPayment());
        return finalPaymentCheckBox.isSelected() == pageRequest.isFinalPayment();
    }

    public boolean isNumberOfPaymentApprovalsValid(final PaymentAdditionalDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Verifying Number Of Payment Approvals is: " + pageRequest.getExpectedNumberOfApprovals());
        return getDriver().findElements(By.xpath("//span[contains(@id,'GBPaymentApproverWidget_') and @class='DataLabel']")).size() == pageRequest.getExpectedNumberOfApprovals();
    }

    public void setStatisticalPaymentCheckBox(final PaymentAdditionalDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Check Statistical payment checkbox");
        click(statisticalPaymentCheckBox);
    }

    public void setFinalPaymentCheckBox(final PaymentAdditionalDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Check Final payment checkbox");
        click(finalPaymentCheckBox);
    }

    public void clickPaymentTab(final PaymentAdditionalDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Payment tab");
        click(paymentTab);
    }

    public void enterInvoiceDate(final PaymentAdditionalDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Invoice Date as: " + pageRequest.getInvoiceDate());
        clearAndInput(pageRequest.getInvoiceDate(), invoiceDate);
    }

    public void enterFXAmount(final PaymentAdditionalDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering FX Amount as: " + pageRequest.getFXAmount());
        clearAndInput(pageRequest.getFXAmount(), fxAmountTextBox);
    }

      public void enterFXRate(final PaymentAdditionalDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering FX Rate: " + pageRequest.getFXRate());
          clearAndInput(pageRequest.getFXRate(), fxRateTextBox);
    }

    public void selectFXCurrency(final PaymentAdditionalDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering FX Currency type as : " + pageRequest.getFXCurrency());
        selectValue(pageRequest.getFXCurrency(), fxCurrencyDropDown);
    }

    public void clickCalculateFXRate(final PaymentAdditionalDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Calculate FX Rate ");
        click(calculateFXRateButton);
    }

    public void clickClose(final PaymentAdditionalDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Close button");
        click(closeButton);
    }

    public static class PaymentAdditionalDetailsPageRequest extends AbstractPageRequest {

        public PaymentAdditionalDetailsPageRequest(final TestCaseContext context) {
            super(context);
        }

        public boolean isStatisticalPayment() {
            return get("StatisticalPayment").equalsIgnoreCase("yes");
        }

        public boolean isFinalPayment() {
            return get("FinalPayment").equalsIgnoreCase("yes");
        }

        public String getInvoiceDate() {
            return get("Invoice Date");
        }

        public String getFXAmount() {
            return get("FX Amount");
        }

        public String getFXCurrency() {
            return get("FX Currency");
        }

        public String getFXRate() {
            return get("FX Rate");
        }
        public int getExpectedNumberOfApprovals() {
            return Integer.parseInt(get("ValidExpectedNumberOfApprovals"));
        }
    }
}
