package com.gb.fineos.page.payment.benefitspaymentmanager.payments.dueevents;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.codehaus.plexus.util.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ViewUnscheduledDuesPage extends BasePage {

    @FindBy(xpath = "//span[contains(@id,'_Payee')]")
    private WebElement payee;

    @FindBy(xpath = "//span[contains(@id,'_DescriptionEnum')]")
    private WebElement paymentType;

    @FindBy(xpath = "//span[contains(@id,'_BasicPayeeAmount')]")
    private WebElement amount;

    @FindBy(xpath = "//span[contains(@id,'_DetailsBean')]")
    private WebElement payeeReference;

    @FindBy(xpath = "//span[contains(@id,'_amtAppliedToReserve')]")
    private WebElement amountAppliedToReserve;

    @FindBy(xpath = "//input[contains(@id,'_cmdPageBack_cloned')]")
    private WebElement closeButton;

    @FindBy(xpath = "//td[contains(@id,'_GBAdditionalDetails_cell')]")
    private WebElement additionalDetailsTab;

    @FindBy(xpath = "//div[@class='reserveContainer']//input[contains(@id, '_OffsetsAndDeductions_cmdView')]")
    private WebElement viewAdjustmentButton;


    public ViewUnscheduledDuesPage() {
        super("PAYMENT - PAYMENT TAB");
    }

    public boolean isPayeeValid(final ViewUnscheduledDuesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Verifying Payee: " + pageRequest.getPayee());
        return StringUtils.contains(payee.getText(), pageRequest.getPayee());
    }

    public boolean isPaymentTypeValid(final ViewUnscheduledDuesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Verifying Payment Type: " + pageRequest.getPaymentType());
        return StringUtils.contains(paymentType.getText(), pageRequest.getPaymentType());
    }

    public boolean isAmountValid(final ViewUnscheduledDuesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Verifying Amount: " + pageRequest.getBasicAmount());
        return StringUtils.contains(amount.getText(), pageRequest.getBasicAmount());
    }

    public boolean isAmountAppliedToReserveValid(final ViewUnscheduledDuesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Verifying Amount Applied to Reserve: " + pageRequest.getAmountAppliedToReserve());
        return StringUtils.contains(amountAppliedToReserve.getText(), pageRequest.getAmountAppliedToReserve());
    }

    public boolean isPayeeReferenceValid(final ViewUnscheduledDuesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Verifying Payee Reference: " + pageRequest.getPayeeReference());
        return StringUtils.contains(payeeReference.getText(), pageRequest.getPayeeReference());
    }

    public boolean isAdjustmentsValid(final ViewUnscheduledDuesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Verifying Adjustments. Type: " + pageRequest.getAdjustmentType() + " Amount: " + pageRequest.getValidAdjustmentAmount());

        return getDriver().findElements(By.xpath("//table[contains(@id, '_OffsetsAndDeductions')]/tbody/tr")).stream()
            .findFirst()
            .map(table -> !table.findElements(By.xpath("td[.='" + pageRequest.getAdjustmentType() + "']")).isEmpty()
                && !table.findElements(By.xpath("td[.='" + pageRequest.getValidAdjustmentAmount() + "']")).isEmpty())
            .orElse(false);
    }

    public void clickAdditionalDetailsTab(final ViewUnscheduledDuesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Additional Details tab");
        click(additionalDetailsTab);
    }

    public void expandAdjustments(final ViewUnscheduledDuesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Expanding Adjustments section");

        final WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        final WebElement expandCollapseImgElement = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[@class='reserveContainer']//td[@class='WidgetTitle' and span[@class='widgetTitleInner' and text()='Adjustments']]/div/img[@class='expandCollapseWidgetImage']")
                )
        );

        click(expandCollapseImgElement);

        wait.until(ExpectedConditions.elementToBeClickable(viewAdjustmentButton));
    }

    public static class ViewUnscheduledDuesPageRequest extends AbstractPageRequest {

        public ViewUnscheduledDuesPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getPayee() {
            if (get("PartiesType").equalsIgnoreCase("Person")) {
                return get("firstName") + " " + get("lastName");
            } else {
                return get("firstName");
            }
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

        public String getAdjustmentType() {
            return get("Adjustment_Type");
        }

        public String getAdjustmentAmount() {
            return get("Adjustment_Amount");
        }

        public String getValidAdjustmentAmount() {
            return get("ValidAdjustmentAmount");
        }

        public String getAmountAppliedToReserve() {
            return get("ValidAmountAppliedToReserve");
        }
    }
}
