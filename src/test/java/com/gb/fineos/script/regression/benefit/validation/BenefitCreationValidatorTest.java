package com.gb.fineos.script.regression.benefit.validation;

import com.gb.fineos.domain.CaseType;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageClaimBase;
import com.gb.fineos.page.utils.FineosDSL;
import com.gb.fineos.page.utils.SearchUtils;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.script.utils.BaseTest;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class BenefitCreationValidatorTest extends BaseTest {

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.nz", "regression.icare"})
    public void createBenefitButtonNotEnabledWhenBenefitTypeIsNotAvailableTest(final Map<String, String> testData) {
        doTest("CreateBenefitButtonNotEnabledWhenBenefitTypeIsNotAvailableTest", "CreateBenefit button not enabled when BenefitType is not available test", testData, tc ->
            FineosDSL.getInstance(tc)
                .getClaim().withTestStep(this::verifyCreateBenefitButtonWhenBenefitTypeNotAvailable).test());
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.uk", "regression.nz", "regression.icare"})
    public void createBenefitButtonNotEnabledWhenCoverageIsNotAvailableTest(final Map<String, String> testData) {
        doTest("CreateBenefitButtonNotEnabledWhenCoverageIsNotAvailableTest", "CreateBenefit button not enabled when BenefitType is not available test", testData, tc ->
            FineosDSL.getInstance(tc)
                .getClaim().withTestStep(this::verifyCreateBenefitButtonWhenCoverageIsNotAvailable).test());
    }

    private void verifyCreateBenefitButtonWhenBenefitTypeNotAvailable(final TestCaseContext tc) {
        SearchUtils.searchCase(tc, CaseType.CLAIM);

        //Navigate to coverages tab for the claim
        final DisplayCasePageClaimBase displayCasePageClaim = tc.getPage(DisplayCasePageClaimBase.class);
        final DisplayCasePageClaimBase.DisplayCasePageClaimBaseRequest displayCasePageClaimBaseRequest = new DisplayCasePageClaimBase.DisplayCasePageClaimBaseRequest(tc);
        displayCasePageClaim.clickCoveragesTab(displayCasePageClaimBaseRequest);
        displayCasePageClaim.clickCoveragesAddButton(displayCasePageClaimBaseRequest);
        displayCasePageClaim.clickOkButton(displayCasePageClaimBaseRequest);

        // Coverages for Selected Policy is available but the Benefit Type is not available
        Assert.assertFalse(displayCasePageClaim.isCreateBenefitButtonEnabled(displayCasePageClaimBaseRequest));
    }

    private void verifyCreateBenefitButtonWhenCoverageIsNotAvailable(final TestCaseContext tc) {
        SearchUtils.searchCase(tc, CaseType.CLAIM);

        //Navigate to coverages tab for the claim
        final DisplayCasePageClaimBase displayCasePageClaim = tc.getPage(DisplayCasePageClaimBase.class);
        final DisplayCasePageClaimBase.DisplayCasePageClaimBaseRequest displayCasePageClaimBaseRequest = new DisplayCasePageClaimBase.DisplayCasePageClaimBaseRequest(tc);
        displayCasePageClaim.clickCoveragesTab(displayCasePageClaimBaseRequest);

        // Coverages for Selected Policy is not available
        Assert.assertFalse(displayCasePageClaim.isCreateBenefitButtonEnabled(displayCasePageClaimBaseRequest));
    }

}
