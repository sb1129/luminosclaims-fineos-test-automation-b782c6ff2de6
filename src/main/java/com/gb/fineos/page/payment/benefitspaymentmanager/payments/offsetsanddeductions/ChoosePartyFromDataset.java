package com.gb.fineos.page.payment.benefitspaymentmanager.payments.offsetsanddeductions;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChoosePartyFromDataset extends BasePage {

    @FindBy(xpath = "//input[contains(@id,'_searchPageOk_cloned')]")
    private WebElement payeeOkButton;

    public ChoosePartyFromDataset() {
        super("ADD PAYMENT PAYEE PAGE");
    }

    public void clickOkToChoosePayee(final AddPaymentPayeePageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking OK button");
        click(payeeOkButton);
    }

    public static class AddPaymentPayeePageRequest extends AbstractPageRequest {
        public AddPaymentPayeePageRequest(final TestCaseContext context) {
            super(context);
        }
    }
}
