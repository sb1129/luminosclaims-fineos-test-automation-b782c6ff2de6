package com.gb.fineos.script.smoke;

import com.gb.fineos.page.utils.FineosDSL;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.script.utils.BaseTest;
import org.testng.annotations.Test;

import java.util.Map;

public class SmokeTest extends BaseTest {

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"smoke.au", "smoke.nz", "smoke.icare", "smoke.uk", "smoke.cc"})
    public void smokeTest(final Map<String, String> testData) {
        doTest("Smoke", "End to end smoke test scenario for Case Alias " + testData.get("NOT_CaseAliasValue"), testData, tc ->
            FineosDSL.getInstance(tc)
                .getBenefit()
                    .withMakeRecommendation()
                    .withDocumentUpload()
                    .withCoverageAccepted()
                    .withReserve()
                    .and()
                    .withApproveReserves()
                    .withPayment()
                    .and()
                    .withApprovePayments()
                    //.withApproveSettlementOffer()
                .test());
    }

}
