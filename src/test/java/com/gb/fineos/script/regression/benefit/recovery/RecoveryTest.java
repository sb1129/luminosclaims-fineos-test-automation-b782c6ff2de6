package com.gb.fineos.script.regression.benefit.recovery;

import com.gb.fineos.domain.CaseType;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.claims.recovery.ChooseRecoveryTypePage;
import com.gb.fineos.page.claims.recovery.EditActualRecoveryPage;
import com.gb.fineos.page.claims.recovery.EditAnticipatedRecoveryPage;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageBenefitBase;
import com.gb.fineos.page.sharedpages.partymanager.partysearch.PartySearchPage;
import com.gb.fineos.page.utils.FineosDSL;
import com.gb.fineos.page.utils.SearchUtils;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.script.utils.BaseTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.Map;

public class RecoveryTest extends BaseTest {

    //Anticipated Recovery

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.icare", "regression.nz"})
    public void addAnticipatedRecoveryTest(final Map<String, String> testData) {
        doTest("addAnticipatedRecoveryTest", "Add Antipated Recovery scenario...", testData, tc -> {
            FineosDSL.getInstance(tc).getBenefit().withTestStep(benefitTestCaseContext -> {
                addAnticipatedRecovery(benefitTestCaseContext);
            }).build().test();
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.icare", "regression.nz"})
    public void editAnticipatedRecoveryTest(final Map<String, String> testData) {
        doTest("editAnticipatedRecoveryTest", "Edit Antipated Recovery scenario...", testData, tc -> {
            FineosDSL.getInstance(tc).getBenefit().withTestStep(benefitTestCaseContext -> {
                addAnticipatedRecovery(benefitTestCaseContext);
                editAnticipatedRecovery(benefitTestCaseContext);
            }).build().test();
        });
    }

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.icare", "regression.nz"})
    public void viewAnticipatedRecoveryTest(final Map<String, String> testData) {
        doTest("viewAnticipatedRecoveryTest", "View Antipated Recovery scenario...", testData, tc -> {
            FineosDSL.getInstance(tc).getBenefit().withTestStep(benefitTestCaseContext -> {
                addAnticipatedRecovery(benefitTestCaseContext);
                viewAnticipatedRecovery(benefitTestCaseContext);
            }).build().test();

        });
    }

    //Actual Recovery

    //TODO
    //Revisit actual recovery when cash receipt added.

    @Ignore
    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.icare", "regression.nz"})
    public void addActualRecoveryTest(final Map<String, String> testData) {
        doTest("addActualRecoveryTest", "Add Actual Recovery scenario...", testData, tc -> {
            FineosDSL.getInstance(tc).getBenefit().withTestStep(benefitTestCaseContext -> {
                addAnticipatedRecovery(benefitTestCaseContext);
                addActualRecovery(benefitTestCaseContext);
            }).build().test();
        });
    }

    @Ignore
    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.icare", "regression.nz"})
    public void editActualRecoveryTest(final Map<String, String> testData) {
        doTest("editActualRecoveryTest", "Edit Actual Recovery scenario...", testData, tc -> {
            FineosDSL.getInstance(tc).getBenefit().withTestStep(benefitTestCaseContext -> {
                addAnticipatedRecovery(benefitTestCaseContext);
                addActualRecovery(benefitTestCaseContext);
                editActualRecovery(benefitTestCaseContext);
            }).build().test();
        });
    }

    @Ignore
    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.icare", "regression.nz"})
    public void viewActualRecoveryTest(final Map<String, String> testData) {
        doTest("viewActualRecoveryTest", "View Actual Recovery scenario...", testData, tc -> {
            FineosDSL.getInstance(tc).getBenefit().withTestStep(benefitTestCaseContext -> {
                addAnticipatedRecovery(benefitTestCaseContext);
                addActualRecovery(benefitTestCaseContext);
                viewActualRecovery(benefitTestCaseContext);
            }).build().test();
        });
    }

    //Stat Recovery

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = {"regression.au", "regression.icare", "regression.nz"})
    public void addStatRecoveryTest(final Map<String, String> testData) {
        doTest("addStatRecoveryTest", "Add Stat Recovery scenario...", testData, tc -> {
            FineosDSL.getInstance(tc).getBenefit().withTestStep(benefitTestCaseContext -> {
                addAnticipatedRecovery(benefitTestCaseContext);
                addStatRecovery(benefitTestCaseContext);
            }).build().test();
        });
    }

    /**
     * Add Anticipated Recovery
     * @param benefitTestCaseContext
     */
    public void addAnticipatedRecovery(TestCaseContext benefitTestCaseContext) {

        SearchUtils.searchCase(benefitTestCaseContext, CaseType.BENEFIT);

        //Display Benefit
        final DisplayCasePageBenefitBase displayCasePageBenefit = benefitTestCaseContext.getPage(DisplayCasePageBenefitBase.class);
        final DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest displayCasePageBenefitRequest = new DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest(benefitTestCaseContext);

        displayCasePageBenefit.clickRecoveriesTab(displayCasePageBenefitRequest);
        displayCasePageBenefit.clickAddAnticipatedButton(displayCasePageBenefitRequest);

        //Choose Recovery
        final ChooseRecoveryTypePage chooseRecoveryPage = benefitTestCaseContext.getPage(ChooseRecoveryTypePage.class);
        final ChooseRecoveryTypePage.ChooseRecoveryPageRequest choosePageRequest = new ChooseRecoveryTypePage.ChooseRecoveryPageRequest(benefitTestCaseContext);

        chooseRecoveryPage.chooseRecoveryType(choosePageRequest);
        chooseRecoveryPage.clickOkToProceed(choosePageRequest);

        final EditAnticipatedRecoveryPage addAnticipatedPage = benefitTestCaseContext.getPage(EditAnticipatedRecoveryPage.class);
        final EditAnticipatedRecoveryPage.EditAnticipatedRecoveryRequest addAnticipatedPageRequest = new EditAnticipatedRecoveryPage.EditAnticipatedRecoveryRequest(benefitTestCaseContext);

        addAnticipatedPage.enterRecoveryDate(addAnticipatedPageRequest);
        addAnticipatedPage.enterAnticipatedAmount(addAnticipatedPageRequest);
        addAnticipatedPage.enterReserveAmount(addAnticipatedPageRequest);
        addAnticipatedPage.enterRecoveryDescription(addAnticipatedPageRequest);

        //Choose Party
        final PartySearchPage partySearchPage = benefitTestCaseContext.getPage(PartySearchPage.class);
        final PartySearchPage.PartySearchPageRequest partySearchPageRequest = new PartySearchPage.PartySearchPageRequest(benefitTestCaseContext);

        addAnticipatedPage.clickToSearchParty(addAnticipatedPageRequest);
        partySearchPage.enterPartyDetails(partySearchPageRequest);
        addAnticipatedPage.clickToSaveRecovery(addAnticipatedPageRequest);
    }

    /**
     * Edit Anticipated Recovery
     * @param benefitTestCaseContext
     */
    public void editAnticipatedRecovery(TestCaseContext benefitTestCaseContext) {

        SearchUtils.searchCase(benefitTestCaseContext, CaseType.BENEFIT);

        //Display Benefit
        final DisplayCasePageBenefitBase displayCasePageBenefit = benefitTestCaseContext.getPage(DisplayCasePageBenefitBase.class);
        final DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest displayCasePageBenefitRequest = new DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest(benefitTestCaseContext);

        displayCasePageBenefit.clickRecoveriesTab(displayCasePageBenefitRequest);
        displayCasePageBenefit.clickEditRecovery(displayCasePageBenefitRequest);

        final EditAnticipatedRecoveryPage editAnticipatedPage = benefitTestCaseContext.getPage(EditAnticipatedRecoveryPage.class);
        final EditAnticipatedRecoveryPage.EditAnticipatedRecoveryRequest editAnticipatedPageRequest = new EditAnticipatedRecoveryPage.EditAnticipatedRecoveryRequest(benefitTestCaseContext);

        editAnticipatedPage.selectUpdateReason(editAnticipatedPageRequest);
        editAnticipatedPage.enterAnticipatedAmount(editAnticipatedPageRequest);
        editAnticipatedPage.enterUpdateRecoveryDescription(editAnticipatedPageRequest);
        editAnticipatedPage.enterRecoveryPercent(editAnticipatedPageRequest);
        editAnticipatedPage.clickToSaveRecovery(editAnticipatedPageRequest);
    }

    /**
     * View Anticipated Recovery
     * @param benefitTestCaseContext
     */
    public void viewAnticipatedRecovery(TestCaseContext benefitTestCaseContext) {
        SearchUtils.searchCase(benefitTestCaseContext, CaseType.BENEFIT);

        //Display Benefit
        final DisplayCasePageBenefitBase displayCasePageBenefit = benefitTestCaseContext.getPage(DisplayCasePageBenefitBase.class);
        final DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest displayCasePageBenefitRequest = new DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest(benefitTestCaseContext);

        displayCasePageBenefit.clickRecoveriesTab(displayCasePageBenefitRequest);
        displayCasePageBenefit.clickViewRecovery(displayCasePageBenefitRequest);
    }

    /**
     * Add Actual Recovery
     * @param benefitTestCaseContext
     */
    public void addActualRecovery(TestCaseContext benefitTestCaseContext) {

        SearchUtils.searchCase(benefitTestCaseContext, CaseType.BENEFIT);

        //Display Benefit
        final DisplayCasePageBenefitBase displayCasePageBenefit = benefitTestCaseContext.getPage(DisplayCasePageBenefitBase.class);
        final DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest displayCasePageBenefitRequest = new DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest(benefitTestCaseContext);

        displayCasePageBenefit.clickRecoveriesTab(displayCasePageBenefitRequest);
        displayCasePageBenefit.clickAddActualButton(displayCasePageBenefitRequest);

        //Choose Recovery
        final ChooseRecoveryTypePage chooseRecoveryPage = benefitTestCaseContext.getPage(ChooseRecoveryTypePage.class);
        final ChooseRecoveryTypePage.ChooseRecoveryPageRequest choosePageRequest = new ChooseRecoveryTypePage.ChooseRecoveryPageRequest(benefitTestCaseContext);

        chooseRecoveryPage.chooseRecoveryType(choosePageRequest);
        chooseRecoveryPage.clickOkToProceed(choosePageRequest);

        final EditActualRecoveryPage addActualPage = benefitTestCaseContext.getPage(EditActualRecoveryPage.class);
        final EditActualRecoveryPage.EditActualRecoveryRequest addActualPageRequest = new EditActualRecoveryPage.EditActualRecoveryRequest(benefitTestCaseContext);

        addActualPage.enterRecoveryDate(addActualPageRequest);
        addActualPage.enterCashReceiptNumber(addActualPageRequest);
        addActualPage.enterTotalRecoveredAmount(addActualPageRequest);
        addActualPage.enterRecoveredAmountForReserve(addActualPageRequest);
        addActualPage.selectPaymentMethod(addActualPageRequest);
        addActualPage.enterPaymentReferenceName(addActualPageRequest);
        addActualPage.selectAnticipated(addActualPageRequest);

        //Choose Party
        final PartySearchPage partySearchPage = benefitTestCaseContext.getPage(PartySearchPage.class);
        final PartySearchPage.PartySearchPageRequest partySearchPageRequest = new PartySearchPage.PartySearchPageRequest(benefitTestCaseContext);

        addActualPage.clickToSearchParty(addActualPageRequest);
        partySearchPage.enterPartyDetails(partySearchPageRequest);
        addActualPage.clickToSaveRecovery(addActualPageRequest);
    }

    /**
     * Edit Actual Recovery
     * @param benefitTestCaseContext
     */
    public void editActualRecovery(TestCaseContext benefitTestCaseContext) {

        SearchUtils.searchCase(benefitTestCaseContext, CaseType.BENEFIT);

        //Display Benefit
        final DisplayCasePageBenefitBase displayCasePageBenefit = benefitTestCaseContext.getPage(DisplayCasePageBenefitBase.class);
        final DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest displayCasePageBenefitRequest = new DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest(benefitTestCaseContext);

        displayCasePageBenefit.clickRecoveriesTab(displayCasePageBenefitRequest);
        displayCasePageBenefit.clickActualRecovery(displayCasePageBenefitRequest);
        displayCasePageBenefit.clickEditRecovery(displayCasePageBenefitRequest);

        final EditActualRecoveryPage addActualPage = benefitTestCaseContext.getPage(EditActualRecoveryPage.class);
        final EditActualRecoveryPage.EditActualRecoveryRequest addActualPageRequest = new EditActualRecoveryPage.EditActualRecoveryRequest(benefitTestCaseContext);

        addActualPage.enterPaymentReferenceName(addActualPageRequest);
        addActualPage.selectPaymentMethod(addActualPageRequest);
        addActualPage.clickToSaveRecovery(addActualPageRequest);
    }

    /**
     * View Actual Recovery
     * @param benefitTestCaseContext
     */
    public void viewActualRecovery(TestCaseContext benefitTestCaseContext) {

        SearchUtils.searchCase(benefitTestCaseContext, CaseType.BENEFIT);

        //Display Benefit
        final DisplayCasePageBenefitBase displayCasePageBenefit = benefitTestCaseContext.getPage(DisplayCasePageBenefitBase.class);
        final DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest displayCasePageBenefitRequest = new DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest(benefitTestCaseContext);

        displayCasePageBenefit.clickRecoveriesTab(displayCasePageBenefitRequest);
        displayCasePageBenefit.clickActualRecovery(displayCasePageBenefitRequest);
        displayCasePageBenefit.clickViewRecovery(displayCasePageBenefitRequest);
    }

    /**
     * Add Stat Recovery
     * @param benefitTestCaseContext
     */
    public void addStatRecovery(TestCaseContext benefitTestCaseContext) {

        SearchUtils.searchCase(benefitTestCaseContext, CaseType.BENEFIT);

        //Display Benefit
        final DisplayCasePageBenefitBase displayCasePageBenefit = benefitTestCaseContext.getPage(DisplayCasePageBenefitBase.class);
        final DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest displayCasePageBenefitRequest = new DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest(benefitTestCaseContext);

        displayCasePageBenefit.clickRecoveriesTab(displayCasePageBenefitRequest);
        displayCasePageBenefit.clickAddActualButton(displayCasePageBenefitRequest);

        //Choose Recovery
        final ChooseRecoveryTypePage chooseRecoveryPage = benefitTestCaseContext.getPage(ChooseRecoveryTypePage.class);
        final ChooseRecoveryTypePage.ChooseRecoveryPageRequest choosePageRequest = new ChooseRecoveryTypePage.ChooseRecoveryPageRequest(benefitTestCaseContext);

        chooseRecoveryPage.chooseRecoveryType(choosePageRequest);
        chooseRecoveryPage.clickOkToProceed(choosePageRequest);

        final EditActualRecoveryPage addActualPage = benefitTestCaseContext.getPage(EditActualRecoveryPage.class);
        final EditActualRecoveryPage.EditActualRecoveryRequest addActualPageRequest = new EditActualRecoveryPage.EditActualRecoveryRequest(benefitTestCaseContext);

        addActualPage.enterRecoveryDate(addActualPageRequest);
        addActualPage.enterTotalRecoveredAmount(addActualPageRequest);
        addActualPage.enterRecoveredAmountForReserve(addActualPageRequest);
        addActualPage.selectPaymentMethod(addActualPageRequest);
        addActualPage.selectAnticipated(addActualPageRequest);

        //Choose Party
        final PartySearchPage partySearchPage = benefitTestCaseContext.getPage(PartySearchPage.class);
        final PartySearchPage.PartySearchPageRequest partySearchPageRequest = new PartySearchPage.PartySearchPageRequest(benefitTestCaseContext);

        addActualPage.clickToSearchParty(addActualPageRequest);
        partySearchPage.enterPartyDetails(partySearchPageRequest);
        addActualPage.clickToSaveRecovery(addActualPageRequest);
    }
}
