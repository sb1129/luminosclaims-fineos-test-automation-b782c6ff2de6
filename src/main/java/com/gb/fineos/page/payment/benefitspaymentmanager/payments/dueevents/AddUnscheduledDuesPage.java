package com.gb.fineos.page.payment.benefitspaymentmanager.payments.dueevents;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddUnscheduledDuesPage extends BasePage {

    @FindBy(xpath = "//select[contains(@id,'AdhocDueAmountWidget') and contains(@id,'_DescriptionEnumPageLevel')]")
    private WebElement paymentTypeDropdown;

    @FindBy(xpath = "//input[contains(@id,'_BasicPayeeAmount')]")
    private WebElement amountMoneybox;

    @FindBy(xpath = "//input[contains(@id,'_BasicAmount')]")
    private WebElement distributionAmountMoneybox;

    @FindBy(xpath = "//textarea[contains(@id,'_DetailsBean')]")
    private WebElement payeeReferenceTextArea;

    @FindBy(xpath = "//input[contains(@id,'_SaveUnscheduledDue_cloned')]")
    private WebElement paymentSaveButton;

    @FindBy(xpath = "//input[contains(@id,'_rcbPayee_rcbSearchButton_rcbPayee')]")
    private WebElement payeeSearchButton;

    @FindBy(xpath = "//td[contains(@id,'_FINEOS.BenefitsPaymentManager.Payments.UnscheduledDues.Adhoc_Dues.Payment_Benefit_Due_Amount_cell')]")
    private WebElement paymentTab;

    @FindBy(xpath = "//td[contains(@id,'_GBAdditionalDetails_cell')]")
    private WebElement additionalDetailsTab;

    @FindBy(xpath = "//div[@class='reserveContainer']//input[contains(@id, '_addBenefitAmountButton')]")
    private WebElement addAdjustmentButton;


    public AddUnscheduledDuesPage() {
        super("PAYMENT - PAYMENT TAB");
    }

    public void selectPaymentType(final AddPaymentPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting payment type as: " + pageRequest.getPaymentType());
        selectValue(pageRequest.getPaymentType(), paymentTypeDropdown);
    }

    public void enterBasicAmount(final AddPaymentPageRequest pageRequest) {
        try {
            pageRequest.log(getPageName(), "Entering basic amount as: " + pageRequest.getBasicAmount());
            clearAndInput(pageRequest.getBasicAmount(), amountMoneybox, getIFrameDisableLayer().orElse(null));
        } catch (Exception e) {
            pageRequest.warning(getPageName(), "There was an issue when attempting to enter the basic amount", true);
            throw e;
        }
    }

    public void enterDistributionBasicAmount(final AddPaymentPageRequest pageRequest) {
        try {
            pageRequest.log(getPageName(), "Entering Distribution Basic Amount as: " + pageRequest.getDistributionAmount());
            clearAndInput(pageRequest.getDistributionAmount(), distributionAmountMoneybox, getIFrameDisableLayer().orElse(null));
        } catch (Exception e) {
            pageRequest.warning(getPageName(), "There was an issue when attempting to enter the distribution amount. Is distribution amount a valid field for the chosen type of payment?", true);
            throw e;
        }
    }

    public void enterPayeeReference(final AddPaymentPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Payee Reference as: " + pageRequest.getPayeeReference());
        clearAndInput(pageRequest.getPayeeReference(), payeeReferenceTextArea, getIFrameDisableLayer().orElse(null));
    }

    public void clickToSavePayment(final AddPaymentPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking save button");
        click(paymentSaveButton);
    }

    public void clickToSearchPayee(final AddPaymentPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking search payee button");
        click(payeeSearchButton);
    }

    public void clickAdditionalDetailsTab(final AddPaymentPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Additional Details tab");
        click(additionalDetailsTab);
    }

        public void clickPaymentTab(final AddPaymentPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Payment tab");
        click(paymentTab);
    }

    public void expandAdjustments(final AddPaymentPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Expanding Adjustments section");

        final WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        final WebElement expandCollapseImgElement = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@class='reserveContainer']//td[@class='WidgetTitle' and span[@class='widgetTitleInner' and text()='Adjustments']]/div/img[@class='expandCollapseWidgetImage']")
                )
        );

        click(expandCollapseImgElement);

        wait.until(ExpectedConditions.elementToBeClickable(addAdjustmentButton));
    }

    public void clickAddAdjustmentButton(final AddPaymentPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Add Adjustment button");
        click(addAdjustmentButton);
    }


    public static class AddPaymentPageRequest extends AbstractPageRequest {

        public AddPaymentPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getPaymentType() {
            return get("Payment Type");
        }

        public String getBasicAmount() {
            return get("Basic Amount");
        }

        public String getPayeeReference() {
            return get("Payment Reference");
        }

        public String getDistributionAmount() {
            return get("Distribution Amount");
        }

        public boolean hasAdditionalDetails() {
            return getOrDefault("StatisticalPayment", "no").equalsIgnoreCase("yes")
                || getOrDefault("FinalPayment", "no").equalsIgnoreCase("yes")
                || getContext().getData().containsKey("Invoice Date");
        }
    }
}
