package com.gb.fineos.script.regression.overpayment;

import com.gb.fineos.domain.CaseType;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.overpayments.recovery.AddActualRecoveryPage;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.overpayments.recovery.AddActualRecoveryPage.AddActualRecoveryPageRequest;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.overpayments.recovery.AddRecoveryPlanPage;
import com.gb.fineos.page.payment.benefitspaymentmanager.payments.overpayments.recovery.AddRecoveryPlanPage.AddRecoveryPlanPageRequest;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageBase.DisplayCasePageBaseRequest;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageOverpaymentBase;
import com.gb.fineos.page.utils.FineosDSL;
import com.gb.fineos.page.utils.SearchUtils;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.script.utils.BaseTest;
import org.testng.annotations.Test;

import java.util.Map;

import static org.junit.Assert.assertTrue;

public class OverpaymentRecoveryTest extends BaseTest {

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"dynamics.integration.au", "dynamics.integration.nz", "dynamics.integration.icare", "dynamics.integration.uk"})
    public void overpaymentAddRecoveryPlanTest(final Map<String, String> testData) {
        doTest("Overpayment Recovery - Add Recovery Plan for Case Alias " + testData.get("NOT_CaseAliasValue"), "", testData, tc ->
            FineosDSL.getInstance(tc)
                .getBenefit()
                    .getOverpayment()
                        .withTestStep(overpaymentTestCaseContext -> {
                            SearchUtils.searchCase(overpaymentTestCaseContext, CaseType.OVERPAYMENT);

                            final DisplayCasePageOverpaymentBase overpaymentPage = overpaymentTestCaseContext.getPage(DisplayCasePageOverpaymentBase.class);
                            final DisplayCasePageBaseRequest overpaymentPageRequest = new DisplayCasePageBaseRequest(overpaymentTestCaseContext);
                            overpaymentPage.clickAddRecoveryPlanButton(overpaymentPageRequest);

                            final AddRecoveryPlanPage addRecoveryPlanPage = overpaymentTestCaseContext.getPage(AddRecoveryPlanPage.class);
                            final AddRecoveryPlanPageRequest addRecoveryPlanPageRequest = new AddRecoveryPlanPageRequest(overpaymentTestCaseContext);
                            addRecoveryPlanPage.clickSaveButton(addRecoveryPlanPageRequest);

                            assertTrue("Expected validation messages : " + String.join(",", addRecoveryPlanPageRequest.getExpectedValidationMessages("ExpectedValidationMessages_1")),
                                addRecoveryPlanPage.hasValidationMessages(addRecoveryPlanPageRequest, "ExpectedValidationMessages_1"));

                            addRecoveryPlanPage.enterAgreementDate(addRecoveryPlanPageRequest);
                            addRecoveryPlanPage.selectType(addRecoveryPlanPageRequest);
                            addRecoveryPlanPage.clickSaveButton(addRecoveryPlanPageRequest);

                            assertTrue("Expected validation messages : " + String.join(",", addRecoveryPlanPageRequest.getExpectedValidationMessages("ExpectedValidationMessages_2")),
                                addRecoveryPlanPage.hasValidationMessages(addRecoveryPlanPageRequest, "ExpectedValidationMessages_2"));

                            addRecoveryPlanPage.enterAmountToSubmit(addRecoveryPlanPageRequest);

                            addRecoveryPlanPage.clickSaveButton(addRecoveryPlanPageRequest);

                            overpaymentPage.hasRecoveryPlan(overpaymentPageRequest);
                        })
                .test());
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"dynamics.integration.au", "dynamics.integration.nz", "dynamics.integration.icare", "dynamics.integration.uk"})
    public void overpaymentAddActualRecoveryTest(final Map<String, String> testData) {
        doTest("Overpayment Recovery - Add Actual Recovery for Case Alias " + testData.get("NOT_CaseAliasValue"), "", testData, tc ->
            FineosDSL.getInstance(tc)
                .getBenefit()
                    .getOverpayment()
                        .withTestStep(overpaymentTestCaseContext -> {
                            SearchUtils.searchCase(overpaymentTestCaseContext, CaseType.OVERPAYMENT);

                            final DisplayCasePageOverpaymentBase overpaymentPage = overpaymentTestCaseContext.getPage(DisplayCasePageOverpaymentBase.class);
                            final DisplayCasePageBaseRequest overpaymentPageRequest = new DisplayCasePageBaseRequest(overpaymentTestCaseContext);
                            overpaymentPage.clickAddActualRecoveryButton(overpaymentPageRequest);

                            final AddActualRecoveryPage addActualRecoveryPage = overpaymentTestCaseContext.getPage(AddActualRecoveryPage.class);
                            final AddActualRecoveryPageRequest addActualRecoveryPageRequest = new AddActualRecoveryPageRequest(overpaymentTestCaseContext);
                            addActualRecoveryPage.clickSaveButton(addActualRecoveryPageRequest);

                            assertTrue("Expected validation messages : " + String.join(",", addActualRecoveryPageRequest.getExpectedValidationMessages("ExpectedValidationMessages_1")),
                                addActualRecoveryPage.hasValidationMessages(addActualRecoveryPageRequest, "ExpectedValidationMessages_1"));

                            addActualRecoveryPage.enterDateOfRecovery(addActualRecoveryPageRequest);
                            addActualRecoveryPage.enterCashReceiptNumber(addActualRecoveryPageRequest);
                            addActualRecoveryPage.clickSaveButton(addActualRecoveryPageRequest);

                            assertTrue("Expected validation messages : " + String.join(",", addActualRecoveryPageRequest.getExpectedValidationMessages("ExpectedValidationMessages_2")),
                                addActualRecoveryPage.hasValidationMessages(addActualRecoveryPageRequest, "ExpectedValidationMessages_2"));

                            addActualRecoveryPage.clickCancelButton(addActualRecoveryPageRequest);
                        })
                .test());
    }
}
