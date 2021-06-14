package com.gb.fineos.page.payments.paymentpreferences;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewPaymentPreferencePage extends BasePage {

    public ViewPaymentPreferencePage() {
        super("Payment Preference");
    }

    @FindBy(xpath = "//input[contains(@id,'_cmdPageBack_cloned')]")
    private WebElement closeBtn;

    @FindBy(xpath = "//span[contains(@id,'_associatedParty')]")
    private WebElement associatedParty;

    @FindBy(xpath = "//span[contains(@id,'_accountName')]")
    private WebElement accountName;

    @FindBy(xpath = "//span[contains(@id,'_routingNumber')]")
    private WebElement routingNumber;

    @FindBy(xpath = "//span[contains(@id,'_bankAccountNo')]")
    private WebElement accountNumber;

    public String getAssociatedParty() {
        return associatedParty.getText();
    }

    public String getAccountName() {
        return accountName.getText();
    }

    public String getRoutingNumber() {
        return routingNumber.getText();
    }

    public String getAccountNumber() {
        return accountNumber.getText();
    }

    public void clickOnCloseButton(final ViewPaymentPreferencePageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Close Button");
        clickModalBox(closeBtn);
    }

    public static class ViewPaymentPreferencePageRequest extends AbstractPageRequest {
        public ViewPaymentPreferencePageRequest(final TestCaseContext context) {
            super(context);
        }
    }
}
