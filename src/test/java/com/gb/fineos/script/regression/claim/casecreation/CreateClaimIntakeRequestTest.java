package com.gb.fineos.script.regression.claim.casecreation;

import com.gb.fineos.page.utils.FineosDSL;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.script.utils.BaseTest;
import org.testng.annotations.Test;

import java.util.Map;


public class CreateClaimIntakeRequestTest extends BaseTest {

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.nz", "regression.uk", "regression.icare"})
    public void addNewPersonPartyAsClaimantTest(final Map<String, String> testData) {
        doTest("Adding New Person Party as Claimant..", "Adding New Person party as A Claimant While Creating A Claim", testData, tc -> {
            FineosDSL.getInstance(tc).getClaim().build().test();
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.nz", "regression.uk","regression.icare"})
    public void addNewOrgPartyAsClaimantTest(final Map<String, String> testData) {
        doTest("Adding New Org Party as Claimant..", "Adding New Org party as A Claimant While Creating A Claim", testData, tc -> {
            FineosDSL.getInstance(tc).getClaim().build().test();
        });
    }
}
