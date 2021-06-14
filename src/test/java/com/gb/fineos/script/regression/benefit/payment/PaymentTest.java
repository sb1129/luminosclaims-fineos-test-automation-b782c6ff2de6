package com.gb.fineos.script.regression.benefit.payment;

import com.gb.fineos.domain.CaseType;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.dueevents.AddUnscheduledDuesPage;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.dueevents.AddUnscheduledDuesPage.AddPaymentPageRequest;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.dueevents.EditUnscheduledDuesPage;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.dueevents.EditUnscheduledDuesPage.EditUnscheduledDuesPageRequest;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.dueevents.PaymentAdditionalDetailsPage;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.dueevents.PaymentAdditionalDetailsPage.PaymentAdditionalDetailsPageRequest;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.dueevents.ViewUnscheduledDuesPage;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.dueevents.ViewUnscheduledDuesPage.ViewUnscheduledDuesPageRequest;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.offsetsanddeductions.ChoosePartyFromDataset;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.offsetsanddeductions.ChoosePartyFromDataset.AddPaymentPayeePageRequest;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.offsetsanddeductions.QuickAddOffsetDeductionPage;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.offsetsanddeductions.QuickAddOffsetDeductionPage.QuickAddOffsetDeductionPageRequest;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageBenefitBase;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest;
import com.gb.fineos.page.utils.BenefitUtils;
import com.gb.fineos.page.utils.ESBUtils;
import com.gb.fineos.page.utils.FineosDSL;
import com.gb.fineos.page.utils.SearchUtils;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.screencapture.ScreenCapture;
import com.gb.fineos.script.utils.BaseTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.Map;

import static com.gb.fineos.page.utils.BenefitUtils.APPROVAL_MESSAGE;
import static com.gb.fineos.page.utils.BenefitUtils.LINK_DOCUMENT_TO_PAYMENT;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class PaymentTest extends BaseTest {

    private final static String FOREIGN_EXCHANGE_ADJUSTMENT_REQUIRED = "Foreign Exchange Adjustment Required";
    private final static String NOT_ASSIGNED = StringUtils.SPACE.concat("Not Assigned");
    private final static String NOT_PRESENT = StringUtils.SPACE.concat("Not Present");
    private final static String PRESENT = StringUtils.SPACE.concat("Present");

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.nz", "regression.icare"})
    public void expenseStatisticalPaymentTest(final Map<String, String> testData) {
        doTest("expenseStatisticalPaymentTest", "Test successful expense Statistical payment scenario for " + testData.get("BEN_CaseAliasValue"), testData, tc ->
            FineosDSL.getInstance(tc).getBenefit().withPaymentApprovalImplementation(this::paymentApproval).test());
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.nz", "regression.icare", "regression.uk"})
    public void expenseCashAmountSubjectToGSTPaymentTest(final Map<String, String> testData) {
        doTest("expenseCashAmountSubjectToGSTPaymentTest", "Test successful expense Cash Amount Subject to GST payment scenario for " + testData.get("BEN_CaseAliasValue"), testData, tc ->
            FineosDSL.getInstance(tc)
                .getBenefit()
                    .getPayment()
                        .withTestStep(this::verify)
                        .and()
                    .withPaymentApprovalImplementation(this::paymentApproval)
                .test());
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.nz", "regression.icare"})
    public void settlementCashAmountSubjectToGSTPaymentTest(final Map<String, String> testData) {
        doTest("settlementCashAmountSubjectToGSTPaymentTest", "Test successful settlement Cash Amount Subject to GST payment scenario for " + testData.get("BEN_CaseAliasValue"), testData, tc ->
            FineosDSL.getInstance(tc)
                .getBenefit()
                    .getPayment()
                        .withTestStep(this::verify)
                        .and()
                    .withPaymentApprovalImplementation(this::paymentApproval)
                .test());
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.nz", "regression.icare"})
    public void expenseDirectInvoicePaymentTest(final Map<String, String> testData) {
        doTest("expenseDirectInvoicePaymentTest", "Test successful expense Direct Invoice payment scenario for " + testData.get("BEN_CaseAliasValue"), testData, tc ->
            FineosDSL.getInstance(tc)
                .getBenefit()
                    .getPayment()
                        .withTestStep(this::verify)
                        .and()
                    .withPaymentApprovalImplementation(this::paymentApproval)
            .test());
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.nz", "regression.icare", "regression.uk"})
    public void settlementDirectInvoicePaymentTest(final Map<String, String> testData) {
        doTest("settlementDirectInvoicePaymentTest", "Test successful settlement Direct Invoice payment scenario for " + testData.get("BEN_CaseAliasValue"), testData, tc ->
            FineosDSL.getInstance(tc)
                .getBenefit()
                    .getPayment()
                        .withTestStep(this::verify)
                        .and()
                    .withPaymentApprovalImplementation(this::paymentApproval)
                .test());
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.nz", "regression.icare"})
    public void internationalPaymentExpensesTest(final Map<String, String> testData) {
        doTest("InternationalPayment", "Test successful international payment Expenses scenario for " + testData.get("BEN_CaseAliasValue"), testData, tc ->
                FineosDSL.getInstance(tc).getBenefit().getPayment().withImplementation(this::internationalPayment).and().withPaymentApprovalImplementation(this::paymentApproval).test());
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.nz", "regression.icare"})
    public void internationalPaymentSettlementTest(final Map<String, String> testData) {
        doTest("InternationalPayment", "Test successful international payment Settlement scenario for " + testData.get("BEN_CaseAliasValue"), testData, tc ->
                FineosDSL.getInstance(tc).getBenefit().getPayment().withImplementation(this::internationalPayment).and().withPaymentApprovalImplementation(this::paymentApproval).test());
    }

    @Ignore
    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"dynamics.integration.au", "dynamics.integration.uk", "dynamics.integration.nz", "dynamics.integration.icare"})
    public void adjustmentTest(final Map<String, String> testData) {
        doTest("adjustmentTest", "Verify Adjustment for " + testData.get("BEN_CaseAliasValue"), testData, tc ->
                FineosDSL.getInstance(tc)
                        .getBenefit()
                        .withTestStep(benefitTestCaseContext -> {
                            ESBUtils.generatePaymentsForDynamicsAndPerformReconciliation(benefitTestCaseContext);

                            //TODO STEP 1 AND 2, dependency on https://gbtrial.atlassian.net/browse/LSA-212 , this has to be completed and than the claim# to be Glued with DSL.Once completed remove @Ignore.
                            //The Steps from 5 to 16 have been tested on old frameWork and work as expected.
                            //STEP 3 Open ActiveBatch job and run "CashReceipts" job.(Use the webservice call to achieve this particular step)
                            ESBUtils.executeCashReceiptsJob();

                            addFXAdjustment(benefitTestCaseContext);
                        })
                        .test());
    }

    private void paymentApproval(TestCaseContext testCaseContext) {
        SearchUtils.searchCase(testCaseContext, CaseType.BENEFIT);

        final DisplayCasePageBenefitBase displayCasePageBenefitBase = testCaseContext.getPage(DisplayCasePageBenefitBase.class);
        final DisplayCasePageBenefitBaseRequest displayCasePageBenefitRequest = new DisplayCasePageBenefitBaseRequest(testCaseContext);

        BenefitUtils.submitPaymentsForApproval(displayCasePageBenefitBase, testCaseContext);

        assertTrue(displayCasePageBenefitBase.verifyPaymentApprovalMessage(displayCasePageBenefitRequest, APPROVAL_MESSAGE), "Expected message displayed as " + APPROVAL_MESSAGE);

        displayCasePageBenefitBase.clickTasksTab(new DisplayCasePageBenefitBaseRequest(testCaseContext));

        if (displayCasePageBenefitBase.isTaskFound(displayCasePageBenefitRequest, LINK_DOCUMENT_TO_PAYMENT)) {
            BenefitUtils.linkDocumentToPayment(displayCasePageBenefitBase, testCaseContext);
        } else {
            testCaseContext.error("Payment Approval", "Unable to find Task: " + LINK_DOCUMENT_TO_PAYMENT + " on Benefit: " + testCaseContext.getBenefitCaseNumber());
        }

        BenefitUtils.performDAPaymentApproval(displayCasePageBenefitBase, testCaseContext);
    }

    private void internationalPayment(TestCaseContext benefitTestCaseContext) {
        SearchUtils.searchCase(benefitTestCaseContext, CaseType.BENEFIT);
        final DisplayCasePageBenefitBase displayCasePageBenefitBase = benefitTestCaseContext.getPage(DisplayCasePageBenefitBase.class);
        final DisplayCasePageBenefitBaseRequest displayCasePageBenefitBaseRequest = new DisplayCasePageBenefitBaseRequest(benefitTestCaseContext);

        //LSA-280 4 Add new payment to Payee from "Payments" tab against approved reserve.
        //a.) Go to Payment Tab-> Click on Add button
        displayCasePageBenefitBase.clickPaymentTab(displayCasePageBenefitBaseRequest);
        displayCasePageBenefitBase.clickAddPaymentButton(displayCasePageBenefitBaseRequest);

        //-> Search  the payee and Add Payment Type: International Expenses OR International Settlement
        final AddUnscheduledDuesPage gbUnscheduledDuesPage = benefitTestCaseContext.getPage(AddUnscheduledDuesPage.class);
        final AddPaymentPageRequest addPaymentPageRequest = new AddPaymentPageRequest(benefitTestCaseContext);
        gbUnscheduledDuesPage.clickToSearchPayee(addPaymentPageRequest);
        final ChoosePartyFromDataset payeePage = benefitTestCaseContext.getPage(ChoosePartyFromDataset.class);
        final AddPaymentPayeePageRequest addPaymentPayeePageRequest = new AddPaymentPayeePageRequest(benefitTestCaseContext);

        //choose payee
        payeePage.clickOkToChoosePayee(addPaymentPayeePageRequest);
        //add payment Type
        gbUnscheduledDuesPage.selectPaymentType(addPaymentPageRequest);

        //b.) Go to "Additional Details" tab.
        gbUnscheduledDuesPage.clickAdditionalDetailsTab(addPaymentPageRequest);
        final PaymentAdditionalDetailsPage paymentAdditionalDetailsPage = benefitTestCaseContext.getPage(PaymentAdditionalDetailsPage.class);
        final PaymentAdditionalDetailsPageRequest paymentAdditionalDetailsPageRequest = new PaymentAdditionalDetailsPageRequest(benefitTestCaseContext);
        if (paymentAdditionalDetailsPageRequest.isStatisticalPayment()) {
            paymentAdditionalDetailsPage.setStatisticalPaymentCheckBox(paymentAdditionalDetailsPageRequest);
        }
        //c.) Enter FX Amount and FX Currency and Click On "Get FX Rate" button.
        paymentAdditionalDetailsPage.enterFXAmount(paymentAdditionalDetailsPageRequest);
        paymentAdditionalDetailsPage.selectFXCurrency(paymentAdditionalDetailsPageRequest);
        if (paymentAdditionalDetailsPageRequest.isStatisticalPayment()) {
            paymentAdditionalDetailsPage.enterFXRate(paymentAdditionalDetailsPageRequest);
        }
        paymentAdditionalDetailsPage.clickCalculateFXRate(paymentAdditionalDetailsPageRequest);

        //d.) Go to Payments section-> Add Amount in Amount Distribution
        gbUnscheduledDuesPage.clickPaymentTab(addPaymentPageRequest);
        gbUnscheduledDuesPage.enterPayeeReference(addPaymentPageRequest);
        gbUnscheduledDuesPage.enterDistributionBasicAmount(addPaymentPageRequest);


        //e.) Click on Save button
        gbUnscheduledDuesPage.clickToSavePayment(addPaymentPageRequest);
    }

    private void addFXAdjustment(final TestCaseContext benefitTestCaseContext) {
        final DisplayCasePageBenefitBase displayCasePageBenefitBase = benefitTestCaseContext.getPage(DisplayCasePageBenefitBase.class);
        final DisplayCasePageBenefitBaseRequest displayCasePageBenefitBaseRequest = new DisplayCasePageBenefitBaseRequest(benefitTestCaseContext);
        //5,6.Go to Tasks tab and verify "Foreign Exchange Adjustment Required" task is available.
        displayCasePageBenefitBase.clickTasksTab(displayCasePageBenefitBaseRequest);
        if (!displayCasePageBenefitBase.isTaskFound(displayCasePageBenefitBaseRequest, FOREIGN_EXCHANGE_ADJUSTMENT_REQUIRED)) {
            assertFalse(false, FOREIGN_EXCHANGE_ADJUSTMENT_REQUIRED + NOT_PRESENT);
            ScreenCapture.logScreenshot(FOREIGN_EXCHANGE_ADJUSTMENT_REQUIRED + NOT_PRESENT, LogStatus.FAIL);
            Assert.fail();
        } else if ((displayCasePageBenefitBase.isTaskFound(displayCasePageBenefitBaseRequest, FOREIGN_EXCHANGE_ADJUSTMENT_REQUIRED))
                && (displayCasePageBenefitBase.isTaskFound(displayCasePageBenefitBaseRequest, NOT_ASSIGNED))) {
            assertFalse(false, FOREIGN_EXCHANGE_ADJUSTMENT_REQUIRED + NOT_ASSIGNED);
            ScreenCapture.logScreenshot(FOREIGN_EXCHANGE_ADJUSTMENT_REQUIRED + NOT_ASSIGNED, LogStatus.FAIL);
            Assert.fail();
        } else {
            assertTrue(true, FOREIGN_EXCHANGE_ADJUSTMENT_REQUIRED + PRESENT);
        }

        //7.  Go to Payments Tab,
        displayCasePageBenefitBase.clickPaymentTab(displayCasePageBenefitBaseRequest);


        //7. Select payment where description says international payment
        displayCasePageBenefitBase.selectPaymentsFromTable(displayCasePageBenefitBaseRequest);

        //8.click Edit Button
        displayCasePageBenefitBase.clickEditPaymentsButton(displayCasePageBenefitBaseRequest);

        //8 opens GBEditUnscheduledDuesPageRequest
        final EditUnscheduledDuesPage gbEditUnscheduledDuesPage = benefitTestCaseContext.getPage(EditUnscheduledDuesPage.class);
        final EditUnscheduledDuesPageRequest gbEditUnscheduledDuesEditPageRequest = new EditUnscheduledDuesPageRequest(benefitTestCaseContext);


        //9.Locate the correct AdhocReserveContainerWidget  and click adjustment to expand
        ScreenCapture.logScreenshot("clickToExpandAdjustment", LogStatus.INFO);
        gbEditUnscheduledDuesPage.clickToExpandAdjustment(gbEditUnscheduledDuesEditPageRequest);
        ScreenCapture.logScreenshot("clickToExpandAdjustment", LogStatus.INFO);


        //10 Click Add Adjustment Button
        gbEditUnscheduledDuesPage.addAdjustment(gbEditUnscheduledDuesEditPageRequest);


        //10 opens  QuickAddOffsetDeductionPage
        final QuickAddOffsetDeductionPage quickAddOffsetDeductionPage = benefitTestCaseContext.getPage(QuickAddOffsetDeductionPage.class);
        final QuickAddOffsetDeductionPageRequest quickAddOffsetDeductionPageRequest = new QuickAddOffsetDeductionPageRequest(benefitTestCaseContext);


        //11. Select  FX Adjustment
        ScreenCapture.logScreenshot("BEFORE CLICK ON ROW", LogStatus.INFO);
        quickAddOffsetDeductionPage.clickFXAdjustmentRow(quickAddOffsetDeductionPageRequest);
        ScreenCapture.logScreenshot("AFTER CLICK ON ROW", LogStatus.INFO);

        //11. click +ve FX Adjustment
        if (("positive").equals(quickAddOffsetDeductionPageRequest.getFXAdjustmentType())) {
            quickAddOffsetDeductionPage.selectPositiveAdjustment(quickAddOffsetDeductionPageRequest);
        } else {
            quickAddOffsetDeductionPage.selectNegativeAdjustment(quickAddOffsetDeductionPageRequest);
        }
        quickAddOffsetDeductionPage.enterAdjustmentAmount(quickAddOffsetDeductionPageRequest);

        //12 click Add
        quickAddOffsetDeductionPage.add(quickAddOffsetDeductionPageRequest);

        //13 click ok
        quickAddOffsetDeductionPage.clickOkButton(quickAddOffsetDeductionPageRequest);

        //13 opens gbEditUnscheduledDuesPage

        //14 click to save
        gbEditUnscheduledDuesPage.clickToSavePayment(gbEditUnscheduledDuesEditPageRequest);


        //14 opens displaycasepage

        //15 select payment record
        displayCasePageBenefitBase.selectPaymentsFromTable(displayCasePageBenefitBaseRequest);


        //click to approve
        displayCasePageBenefitBase.clickApproveRecoverButton(displayCasePageBenefitBaseRequest);
        BenefitUtils.handleAutoGeneratedAdjustmentPage(benefitTestCaseContext);
        displayCasePageBenefitBase.handlePopUpWidgetWrapper(displayCasePageBenefitBaseRequest);


        //STEP 16 Go to Tasks tab and close "Foreign Exchange Adjustment Required" task
        displayCasePageBenefitBase.clickTasksTab(displayCasePageBenefitBaseRequest);
        displayCasePageBenefitBase.selectTaskFromTable(displayCasePageBenefitBaseRequest, FOREIGN_EXCHANGE_ADJUSTMENT_REQUIRED);
        displayCasePageBenefitBase.clickCloseOnTasks(displayCasePageBenefitBaseRequest);
    }

    public void verify(TestCaseContext testCaseContext) {
        SearchUtils.searchCase(testCaseContext, CaseType.BENEFIT);

        final DisplayCasePageBenefitBase displayCasePageBenefit = testCaseContext.getPage(DisplayCasePageBenefitBase.class);
        final DisplayCasePageBenefitBaseRequest displayCasePageBenefitRequest = new DisplayCasePageBenefitBaseRequest(testCaseContext);

        ScreenCapture.logScreenshot(displayCasePageBenefit.getPageName(), LogStatus.INFO);
        assertTrue(displayCasePageBenefit.isReservePaidToDateValid(displayCasePageBenefitRequest));
        assertTrue(displayCasePageBenefit.isReserveApproved(displayCasePageBenefitRequest));
        assertTrue(displayCasePageBenefit.isReserveOutstandingAmountValid(displayCasePageBenefitRequest));

        displayCasePageBenefit.clickPaymentTab(displayCasePageBenefitRequest);
        displayCasePageBenefit.selectPaymentsFromTable(displayCasePageBenefitRequest);
        displayCasePageBenefit.clickViewPaymentButton(displayCasePageBenefitRequest);

        final ViewUnscheduledDuesPage viewUnscheduledDuesPage = testCaseContext.getPage(ViewUnscheduledDuesPage.class);
        final ViewUnscheduledDuesPageRequest viewUnscheduledDuesPageRequest = new ViewUnscheduledDuesPageRequest(testCaseContext);

        ScreenCapture.logScreenshot(viewUnscheduledDuesPage.getPageName(), LogStatus.INFO);
        assertTrue(viewUnscheduledDuesPage.isPayeeValid(viewUnscheduledDuesPageRequest));
        assertTrue(viewUnscheduledDuesPage.isPaymentTypeValid(viewUnscheduledDuesPageRequest));
        assertTrue(viewUnscheduledDuesPage.isAmountValid(viewUnscheduledDuesPageRequest));
        assertTrue(viewUnscheduledDuesPage.isAmountAppliedToReserveValid(viewUnscheduledDuesPageRequest));
        assertTrue(viewUnscheduledDuesPage.isPayeeReferenceValid(viewUnscheduledDuesPageRequest));

        if (testCaseContext.getData().containsKey("Adjustment_Type")) {
            assertTrue(viewUnscheduledDuesPage.isAdjustmentsValid(viewUnscheduledDuesPageRequest));
        }

        viewUnscheduledDuesPage.clickAdditionalDetailsTab(viewUnscheduledDuesPageRequest);

        final PaymentAdditionalDetailsPage paymentAdditionalDetailsPage = testCaseContext.getPage(PaymentAdditionalDetailsPage.class);
        final PaymentAdditionalDetailsPageRequest paymentAdditionalDetailsPageRequest = new PaymentAdditionalDetailsPageRequest(testCaseContext);
        assertTrue(paymentAdditionalDetailsPage.isStatisticalPaymentValid(paymentAdditionalDetailsPageRequest));
        assertTrue(paymentAdditionalDetailsPage.isFinalPaymentValid(paymentAdditionalDetailsPageRequest));
        assertTrue(paymentAdditionalDetailsPage.isNumberOfPaymentApprovalsValid(paymentAdditionalDetailsPageRequest));
        paymentAdditionalDetailsPage.clickClose(paymentAdditionalDetailsPageRequest);

        displayCasePageBenefit.clickPaymentHistoryTab(displayCasePageBenefitRequest);
        assertTrue(displayCasePageBenefit.hasPaymentHistory(displayCasePageBenefitRequest));
    }
}
