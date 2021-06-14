package com.gb.fineos.page.sharedpages.casemanager.displaycase;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.utils.BenefitUtils;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;

public class DisplayCasePageClaimBase extends DisplayCasePageBase {

    //Claim level tabs
    @FindBy(xpath = "//td[contains(@id,'_CaseTabControlBean_') and contains(@id,'CoveragesTab_cell')]")
    private WebElement claimCoveragesTab;
    @FindBy(xpath = "//span[contains(@id,'ClaimGeneralWidget')]")
    private WebElement claimDetails;

    @FindBy(xpath = "//input[contains(@id,'_updateCoverages')]")
    private WebElement policyUpdateButton;

    //Coverages for Selected Policy
    @FindBy(xpath = "//input[contains(@id,'AssuranceComponentLVW_') and contains(@id,'_CoveragesLVW_cmdAdd')]")
    private WebElement claimCoveragesLVWAddButton;
    //Benefit Types
    @FindBy(xpath = "//input[contains(@id,'BenefitRightsListViewWidget_') and contains(@id,'_BenefitRights_cmdAdd')]")
    private WebElement benefitTypesAddButton;
    @FindBy(xpath = "//input[contains(@id,'BenefitRightsListViewWidget_') and contains(@id,'_CreateBenefit')]")
    private WebElement createBenefitButton;

    @FindBy(xpath = "//table[contains(@id,'ChooseCaseTypeLvw_') and contains(@id,'_chooseCaseTypelv')]/tbody/tr[2]/td")
    private WebElement liabilityBenefitRow;

    @FindBy(xpath = "//div[contains(@class,'pageheader_heading')]/h2")
    private WebElement claimName;
    // claim --Document tab elements

    @FindBy(xpath = "//table[contains(@id, 'DocumentsForCaseListviewWidget') and contains(@id,'DocumentsViewControl')]/tbody/tr")
    private List<WebElement> documentsTableRows;

    //Claim Status Progress with coverage accepted.
    @FindBy(xpath = "//td[contains(text(),'Settlement Approval Required')]")
    private WebElement settlementApprovalRequired;
    @FindBy(xpath = "(//a[contains(@id,'_Outbound_Correspondence')])[1]")
    private WebElement outboundCorrespondenceDocumentLink;
    @FindBy(xpath = "//input[contains(@id,'DocumentsForCaseListviewWidget_') and contains(@id,'_DocumentProperties')]")
    private WebElement documentPropertiesButton;
    @FindBy(xpath = "//td[contains(@id,'DisplayCaseTabbedDialogWidget_') and contains(@id,'_CaseTabControlBean_GeneralClaimTab_cell')]")
    private WebElement claimSummaryTab;


    public DisplayCasePageClaimBase() {
        super("CLAIM PAGE");
    }

    public WebElement getClaimName() {
        return claimName;
    }

    public WebElement getClaimDetails() {
        return claimDetails;
    }

    public WebElement getClaimSummaryTab() {
        return claimSummaryTab;
    }


    public void clickOnSettlementApprovalRequiredCell(final DisplayCasePageClaimBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Clicking on Settlement Approval Required Cell");
        click(settlementApprovalRequired);
    }

    public void clickClaimSummaryTab(final DisplayCasePageBaseRequest pageRequest) {
        try {
            if (getAlertPopUp().isDisplayed()) {
                clickModalBox(getClosebtn());
                pageRequest.log(getPageName(), "Clicking on the Summary Tab");
                clickModalBox(claimSummaryTab);
            }
        } catch (final Exception e) {
            pageRequest.log(getPageName(), "Clicking on the Summary Tab");
            clickModalBox(claimSummaryTab);
        }
    }


    public void clickCoveragesTab(final DisplayCasePageClaimBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Coverages Tab");
        click(claimCoveragesTab);
    }

    public void clickPolicyUpdateButton(final DisplayCasePageClaimBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Policy update button");
        click(policyUpdateButton);
    }

    public void clickCoveragesAddButton(final DisplayCasePageClaimBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Coverages Add Button");
        click(claimCoveragesLVWAddButton);
    }

    public void clickBenefitTypesAddButton(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Benefit Types Add Button");
        click(benefitTypesAddButton);
    }

    public void clickCreateBenefitButton(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Create Benefit Button");
        click(createBenefitButton);
    }
    public void clickLiabilityBenefitRow(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Liability Benefit Row");
        click(liabilityBenefitRow);
    }

    public void clickOnPropertiesButton(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Properties Button");
        click(documentPropertiesButton);
    }

    public void clickNextButton(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Next button");
        click(getNextButton());
    }

    public void clickOkButton(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking OK button");
        click(getSaveButton());
    }

    public void clickOkButtonCloned(final DisplayCasePageBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking OK button");
        click(getOkButton());
    }

    public void verifyBenefitNumber(final DisplayCasePageBaseRequest pageRequest) {
        final String benefitNumber = getText(getCaseNumber());
        if (StringUtils.countMatches(benefitNumber, "-") != 2) {
            pageRequest.error(getPageName(), "Benefit number did not match the correct pattern of '-##-##': " + benefitNumber);
        } else if (!StringUtils.right(benefitNumber, BenefitUtils.BENEFIT_NUMBER_SUFFIX.length()).equals(BenefitUtils.BENEFIT_NUMBER_SUFFIX)) {
            pageRequest.warning(getPageName(), "Created Benefit Number is: " + benefitNumber + "\nWas this the expected benefit number?", true);
        } else {
            pageRequest.log(getPageName(), "Created Benefit Number is : " + benefitNumber);
        }
    }

    public String currentClaimName(final DisplayCasePageClaimBaseRequest pageRequest) {
        return  getText(getClaimName()).toUpperCase().trim();
    }

    public static class DisplayCasePageClaimBaseRequest extends DisplayCasePageBaseRequest {

        public DisplayCasePageClaimBaseRequest(final TestCaseContext context) {
            super(context);
        }

    }

    public boolean isCreateBenefitButtonEnabled(final DisplayCasePageClaimBaseRequest pageRequest) {
        pageRequest.log(getPageName(), "Is Create Benefit button Enabled.");

        try {
            final WebDriverWait wait = new WebDriverWait(getDriver(), 5);
            return Optional.ofNullable(wait.until(ExpectedConditions.elementToBeClickable(createBenefitButton))).isPresent();
        } catch (TimeoutException e) {
            return false;
        }
    }
}

