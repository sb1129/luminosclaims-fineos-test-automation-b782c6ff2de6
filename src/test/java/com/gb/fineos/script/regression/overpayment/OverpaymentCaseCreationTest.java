package com.gb.fineos.script.regression.overpayment;

import com.gb.fineos.domain.CaseType;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.dueevents.AddUnscheduledDuesPage;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.dueevents.PaymentAdditionalDetailsPage;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.dueevents.PaymentAdditionalDetailsPage.PaymentAdditionalDetailsPageRequest;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.offsetsanddeductions.ChoosePartyFromDataset;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.offsetsanddeductions.QuickAddOffsetDeductionPage;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.offsetsanddeductions.QuickAddOffsetDeductionPage.QuickAddOffsetDeductionPageRequest;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageBenefitBase;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageOverpaymentBase;
import com.gb.fineos.page.utils.FineosDSL;
import com.gb.fineos.page.utils.SearchUtils;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.script.utils.BaseTest;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class OverpaymentCaseCreationTest extends BaseTest {

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"dynamics.integration.au", "dynamics.integration.nz", "dynamics.integration.icare", "dynamics.integration.uk"})
    public void overpaymentCaseCreationForExpenseCashAmountSubjectToGSTPaymentTest(final Map<String, String> testData) {
        doTest("Overpayment Case creation for ExpenseCashAmountSubjectToGST Payment", "", testData, tc ->
            FineosDSL.getInstance(tc)
                .getBenefit()
                    .getOverpayment()
                        .withTestStep(this::verifyOverpayment)
                .test());
    }


    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"dynamics.integration.au", "dynamics.integration.nz", "dynamics.integration.icare"})
    public void overpaymentCaseCreationForSettlementCashAmountSubjectToGSTPaymentTest(final Map<String, String> testData) {
        doTest("Overpayment Case creation for SettlementCashAmountSubjectToGST Payment", "", testData, tc ->
            FineosDSL.getInstance(tc)
                .getBenefit()
                    .getOverpayment()
                        .withTestStep(this::verifyOverpayment)
                .test());
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"dynamics.integration.au", "dynamics.integration.nz", "dynamics.integration.icare", "dynamics.integration.uk"})
    public void overpaymentCaseCreationForExpenseDirectInvoicePaymentTest(final Map<String, String> testData) {
        doTest("Overpayment Case creation for ExpenseDirectInvoice Payment", "", testData, tc ->
            FineosDSL.getInstance(tc)
                .getBenefit()
                    .getPayment()
                        .withImplementation(this::alternativeAddPaymentOfInvoiceTypeImplementation)
                        .and()
                    .getOverpayment()
                        .withTestStep(this::verifyOverpayment)
                .test());
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"dynamics.integration.au", "dynamics.integration.nz", "dynamics.integration.icare", "dynamics.integration.uk"})
    public void overpaymentCaseCreationForSettlementDirectInvoicePaymentTest(final Map<String, String> testData) {
        doTest("Overpayment Case creation for SettlementDirectInvoice Payment", "", testData, tc ->
            FineosDSL.getInstance(tc)
                .getBenefit()
                    .getPayment()
                        .withImplementation(this::alternativeAddPaymentOfInvoiceTypeImplementation)
                        .and()
                    .getOverpayment()
                        .withTestStep(this::verifyOverpayment)
                .test());
    }

    public void alternativeAddPaymentOfInvoiceTypeImplementation(final TestCaseContext tc) {
        SearchUtils.searchCase(tc, CaseType.BENEFIT);

        final DisplayCasePageBenefitBase displayCasePageBenefit = tc.getPage(DisplayCasePageBenefitBase.class);
        final DisplayCasePageBenefitBaseRequest displayCasePageBenefitRequest = new DisplayCasePageBenefitBaseRequest(tc);
        displayCasePageBenefit.clickPaymentTab(displayCasePageBenefitRequest);
        displayCasePageBenefit.clickAddPaymentButton(displayCasePageBenefitRequest);

        // Select Payment type
        final AddUnscheduledDuesPage paymentPage = tc.getPage(AddUnscheduledDuesPage.class);
        final AddUnscheduledDuesPage.AddPaymentPageRequest pageRequest = new AddUnscheduledDuesPage.AddPaymentPageRequest(tc);
        paymentPage.selectPaymentType(pageRequest);
        paymentPage.enterBasicAmount(pageRequest);
        paymentPage.enterDistributionBasicAmount(pageRequest);
        paymentPage.enterPayeeReference(pageRequest);
        paymentPage.clickToSearchPayee(pageRequest);

        // Choose Payee
        final ChoosePartyFromDataset payeePage = tc.getPage(ChoosePartyFromDataset.class);
        payeePage.clickOkToChoosePayee(new ChoosePartyFromDataset.AddPaymentPayeePageRequest(tc));

        // Click to add Adjustments
        paymentPage.expandAdjustments(pageRequest);
        paymentPage.clickAddAdjustmentButton(pageRequest);

        final QuickAddOffsetDeductionPage quickAddOffsetDeductionPage = tc.getPage(QuickAddOffsetDeductionPage.class);
        final QuickAddOffsetDeductionPageRequest quickAddOffsetDeductionPageRequest = new QuickAddOffsetDeductionPageRequest(tc);

        // Add the GST Adjustment amount
        quickAddOffsetDeductionPage.selectAdjustmentType(quickAddOffsetDeductionPageRequest);
        quickAddOffsetDeductionPage.enterAdjustmentAmount(quickAddOffsetDeductionPageRequest);
        quickAddOffsetDeductionPage.clickAddButton(quickAddOffsetDeductionPageRequest);
        quickAddOffsetDeductionPage.clickOkButton(quickAddOffsetDeductionPageRequest);

        // Enter Additional Details
        paymentPage.clickAdditionalDetailsTab(pageRequest);

        final PaymentAdditionalDetailsPage paymentAdditionalDetailsPage = tc.getPage(PaymentAdditionalDetailsPage.class);
        final PaymentAdditionalDetailsPageRequest paymentAdditionalDetailsPageRequest = new PaymentAdditionalDetailsPageRequest(tc);
        paymentAdditionalDetailsPage.enterInvoiceDate(paymentAdditionalDetailsPageRequest);

        paymentAdditionalDetailsPage.clickPaymentTab(paymentAdditionalDetailsPageRequest);
        paymentPage.clickToSavePayment(pageRequest);
    }

    private void verifyOverpayment(final TestCaseContext tc) {
        SearchUtils.searchCase(tc, CaseType.OVERPAYMENT);

        // Verify Overpayment information
        final DisplayCasePageOverpaymentBase displayCasePageOverpayment = tc.getPage(DisplayCasePageOverpaymentBase.class);
        Assert.assertEquals(tc.getValue("Agreed Recovery Amount"), displayCasePageOverpayment.getAgreedRecoveryAmount().getText());
        Assert.assertEquals(DisplayCasePageOverpaymentBase.OP_TYPE_OVERPAYMENT, displayCasePageOverpayment.getOverpaymentType().getText());
    }
}
