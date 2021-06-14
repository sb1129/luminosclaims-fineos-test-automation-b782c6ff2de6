package com.gb.fineos.page.payment.benefitspaymentmanager.payments.dueevents;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import com.gb.fineos.screencapture.ScreenCapture;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditUnscheduledDuesPage extends BasePage {

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

    @FindBy(xpath = "//input[contains(@id,'_addBenefitAmountButton')]")
    private WebElement addAdjustmentButton;

    public EditUnscheduledDuesPage() {
        super("EDIT PAYMENT PAGE");
    }


    public void selectPaymentType(final EditUnscheduledDuesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting payment type as: " + pageRequest.getPaymentType());
        selectValue(pageRequest.getPaymentType(), paymentTypeDropdown);
    }

    public void enterBasicAmount(final EditUnscheduledDuesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering basic amount as: " + pageRequest.getBasicAmount());
        clearAndInput(pageRequest.getBasicAmount(), amountMoneybox, getIFrameDisableLayer().orElse(null));
        final WebDriverWait wait = new WebDriverWait(getDriver(), 100);
    }

    public void enterDistributionBasicAmount(final EditUnscheduledDuesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Distribution Basic Amount as: " + pageRequest.getDistributionAmount());
        clearAndInput(pageRequest.getDistributionAmount(), distributionAmountMoneybox, getIFrameDisableLayer().orElse(null));
        final WebDriverWait wait = new WebDriverWait(getDriver(), 100);
    }

    public void enterPayeeReference(final EditUnscheduledDuesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Payee Reference as: " + pageRequest.getPayeeReference());
        clearAndInput(pageRequest.getPayeeReference(), payeeReferenceTextArea, getIFrameDisableLayer().orElse(null));
    }

    public void clickToSavePayment(final EditUnscheduledDuesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking save button");
        click(paymentSaveButton);
    }

    public void clickToSearchPayee(final EditUnscheduledDuesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking search payee button");
        click(payeeSearchButton);
    }


    public void clickToExpandAdjustment(final EditUnscheduledDuesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Expanding Adjustments section");

        final WebDriverWait wait = new WebDriverWait(getDriver(), 5);
        final WebElement expandCollapseImgElement = wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//div[@class='reserveContainer']//td[@class='WidgetTitle' and span[@class='widgetTitleInner' and text()='Adjustments']]/div/img[@class='expandCollapseWidgetImage']")
            )
        );

        click(expandCollapseImgElement);

        wait.until(ExpectedConditions.elementToBeClickable(addAdjustmentButton));

        ScreenCapture.logScreenshot("Click expanded ", LogStatus.INFO);
    }

    public void addAdjustment(final EditUnscheduledDuesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Add  Adjustment button");
        click(addAdjustmentButton);
    }

    public static class EditUnscheduledDuesPageRequest extends AbstractPageRequest {

        public EditUnscheduledDuesPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getPaymentType() {
            return get("Revised Payment Type");
        }

        public String getBasicAmount() {
            return get("Revised Basic Amount");
        }

        public String getPayeeReference() {
            return get("Revised Payment Reference");
        }

        public String getDistributionAmount() {
            return get("Revised Distribution Amount");
        }
    }
}
