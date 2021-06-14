package com.gb.fineos.page.sharedpages.casemanager.displaycase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DisplayCasePageOverpaymentBase extends DisplayCasePageBase {
    public static final String OP_TYPE_OVERPAYMENT = "Overpayment";

    @FindBy(xpath = "//div[contains(@class,'pageheader_heading')]/h2/span")
    private WebElement overpaymentNameElement;

    @FindBy(xpath = "//span[contains(@id,'_AgreedRecoveryAmount')]")
    private WebElement agreedRecoveryAmount;

    @FindBy(xpath = "//span[contains(@id,'_OverpaymentType')]")
    private WebElement overpaymentType;

    @FindBy(xpath = "//span[@id='RecoveryPlanListviewWidget']//input[contains(@id,'_RecoveryPlanListView_cmdAdd')]")
    private WebElement addRecoveryPlanButton;

    @FindBy(xpath = "//span[@id='ActualRecoveriesListviewWidget']//input[contains(@id,'_RecoveryPlanListView_cmdAdd')]")
    private WebElement addActualRecoveryButton;

    public DisplayCasePageOverpaymentBase() {
        super("OVERPAYMENT DISPLAY CASE PAGE");
    }

    public String getOverpaymentName() {
        return overpaymentNameElement.getText();
    }

    public void verifyOverpaymentNumber(final DisplayCasePageBaseRequest pageRequest) {
        final String overPaymentNumber = getText(overpaymentNameElement);
        pageRequest.log(getPageName(), "Created Overpayment Number  is : " + overPaymentNumber);
    }

    public WebElement getAgreedRecoveryAmount() {
        return agreedRecoveryAmount;
    }

    public WebElement getOverpaymentType() {
        return overpaymentType;
    }

    public void clickAddRecoveryPlanButton(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Add Recovery Plan button");
        click(addRecoveryPlanButton);
    }

    public void clickAddActualRecoveryButton(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Add Actual Recovery button");
        click(addActualRecoveryButton);
    }

    public boolean hasRecoveryPlan(final DisplayCasePageBaseRequest pageRequest) {
        final String agreementDate = pageRequest.getContext().getValue("Agreement_Date");
        final String type = pageRequest.getContext().getValue("Type");
        final String amountToSubmit = pageRequest.getContext().getValue("Amount_To_Submit");

        return getDriver().findElements(By.xpath("span[@id='RecoveryPlanListviewWidget']//div[contains(@id,'_RecoveryPlanListView_ScrollPane')]//tr")).stream()
            .anyMatch(row -> !row.findElements(By.xpath("/td[@title='" + agreementDate + "'")).isEmpty()
                && !row.findElements(By.xpath("/td[@title='" + type + "'")).isEmpty()
                && !row.findElements(By.xpath("/td[@title='" + amountToSubmit + "'")).isEmpty());
    }

    public boolean hasActualRecovery(final DisplayCasePageBaseRequest pageRequest) {
        final String dateOfRecovery = pageRequest.getContext().getValue("Date_Of_Recovery");
        final String recoveryAmount = pageRequest.getContext().getValue("Recovery_Amount");
        final String cashReceiptNumber = pageRequest.getContext().getValue("Cash_Receipt_Number");

        return getDriver().findElements(By.xpath("span[@id='RecoveryPlanListviewWidget']//div[contains(@id,'_RecoveryPlanListView_ScrollPane')]//tr")).stream()
            .anyMatch(row -> !row.findElements(By.xpath("/td[@title='" + dateOfRecovery + "'")).isEmpty()
                && !row.findElements(By.xpath("/td[@title='" + recoveryAmount + "'")).isEmpty()
                && !row.findElements(By.xpath("/td[@title='" + cashReceiptNumber + "'")).isEmpty());
    }
}
