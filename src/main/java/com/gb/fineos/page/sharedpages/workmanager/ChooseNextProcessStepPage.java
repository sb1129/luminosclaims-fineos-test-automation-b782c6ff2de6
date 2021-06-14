package com.gb.fineos.page.sharedpages.workmanager;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ChooseNextProcessStepPage extends BasePage {

    public ChooseNextProcessStepPage() {
        super("CHOOSE_NEXT_PROCESS_STEP_PAGE");
    }

    @FindBy(xpath = "//div[contains(@id,'CHOOSE_NEXT_STEP_WIDGET_')]//td[contains(text(),'Coverage Decision Required')]")
    private WebElement coverageDecisionRequiredCell;
    @FindBy(xpath = "//div[contains(@id,'CHOOSE_NEXT_STEP_WIDGET_')]//td[contains(text(),'Not Proceeding with Claim')]")
    private WebElement notProceedingWithClaimCell;
    @FindBy(xpath = "//div[contains(@id,'CHOOSE_NEXT_STEP_WIDGET_')]//td[contains(text(),'Re-Open for Additional Expense Payment')]")
    private WebElement reopenForAdditionalExpensePayment;
    @FindBy(xpath = "//div[contains(@id,'CHOOSE_NEXT_STEP_WIDGET_')]//td[contains(text(),'Recommend Coverage Accepted')]")
    private WebElement recommendCoverageAcceptedCell;
    @FindBy(xpath = "//div[contains(@id,'CHOOSE_NEXT_STEP_WIDGET_')]//td[contains(text(),'Recommend Coverage Declined')]")
    private WebElement recommendCoverageDeclinedCell;
    @FindBy(xpath = "//div[contains(@id,'CHOOSE_NEXT_STEP_WIDGET_')]//td[contains(text(),'Approve')]")
    private WebElement approveCell;
    @FindBy(xpath = "//div[contains(@id,'CHOOSE_NEXT_STEP_WIDGET_')]//td[contains(text(),'Review Settlement Offer')]")
    private WebElement reviewSettlementOfferCell;

    @FindBy(xpath = "//div[contains(@id,'CHOOSE_NEXT_STEP_WIDGET_')]//td[contains(text(),'Proceed With Payment')]")
    private WebElement proceedWithPaymentCell;
    @FindBy(xpath = "//input[contains(@id,'_editPageSave')]")
    private WebElement save;


    public void clickOnOK(final ChooseNextProcessStepPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Ok button ");
        click(save);

        if (!getDriver().findElements(By.xpath("//input[contains(@id,'_editPageSave')]")).isEmpty()) {
            click(save);
        }
    }

    public void clickOnCoverageDecisionRequiredCell(final ChooseNextProcessStepPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting on Coverage Decision Required Cell ");
        try {
            click(coverageDecisionRequiredCell);
        } catch (Exception e) {
            pageRequest.warning(getPageName(), "There was an issue when trying to select 'Coverage Decision Required'", true);
            throw e;
        }
    }

    public void clickOnNotProceedingWithClaimCell(final ChooseNextProcessStepPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting On Not Proceeding With Claim Cell ");
        click(notProceedingWithClaimCell);
    }

    public void clickOnReopenForAdditionalExpensePaymentCell(final ChooseNextProcessStepPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting On Reopen for Additional expense payment Cell ");
        click(reopenForAdditionalExpensePayment);
    }

    public void clickOnRecommendCoverageAcceptedCell(final ChooseNextProcessStepPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting On Recommend Coverage Accepted Cell");
        click(recommendCoverageAcceptedCell);
    }

    public void clickOnRecommendCoverageDeclinedCell(final ChooseNextProcessStepPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting On Recommend Coverage Declined Cell");
        click(recommendCoverageDeclinedCell);
    }

    public void clickOnApproveCell(final ChooseNextProcessStepPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting On Approve Cell");
        click(approveCell);
    }
    public void clickOnProceedWithPaymentCell(final ChooseNextProcessStepPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting On Proceed With Payment Cell");
        click(proceedWithPaymentCell);
    }

    public void clickOnReviewSettlementOfferCell(final ChooseNextProcessStepPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting On Review Settlement Offer Cell");
        click(reviewSettlementOfferCell);
    }
    public static class ChooseNextProcessStepPageRequest extends AbstractPageRequest {

        public ChooseNextProcessStepPageRequest(final TestCaseContext context) {
            super(context);
        }
    }

}
