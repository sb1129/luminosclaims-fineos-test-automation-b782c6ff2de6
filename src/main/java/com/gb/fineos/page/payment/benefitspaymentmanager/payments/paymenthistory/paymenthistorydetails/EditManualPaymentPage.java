package com.gb.fineos.page.payment.benefitspaymentmanager.payments.paymenthistory.paymenthistorydetails;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.WebElement;

public class EditManualPaymentPage extends BasePage {

    private WebElement basicAmount;
    private WebElement netBenefitAmount;
    private WebElement balancingAmount;

    public EditManualPaymentPage() {
        super("EDIT MANUAL PAYMENT");
    }


    public static class EditManualPaymentPageRequest extends AbstractPageRequest {

        public EditManualPaymentPageRequest(final TestCaseContext context) {
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
            return get("StatisticalPayment").equalsIgnoreCase("yes") || get("FinalPayment").equalsIgnoreCase("yes");
        }
    }
}
