package com.gb.fineos.page.claims.recovery;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.Map;

public class ChooseRecoveryTypePage extends BasePage {

    private static final String EXCESS_COLLECTION = "Excess Collection";
    private static final String EXCESS_WRITE_OFF = "Excess Write-Off";
    private static final String RECEIVED_IN_ERROR = "Received in Error";
    private static final String SALVAGE_RECOVERY_WHOLE = "Salvage Recovery - Whole";
    private static final String SALVAGE_RECOVERY_PARTS = "Salvage Recovery - Parts";
    private static final String STATISTICAL_EXCESS_COLLECTION = "Statistical Excess Collection";
    private static final String STATISTICAL_SUBROGATION = "Statistical Subrogation Recovery";
    private static final String SUBROGATION_EXPENSES = "Subrogation - Expenses Recovery";
    private static final String SUBROGATION_SETTLEMENT = "Subrogation - Settlement Recovery";
    private static final String ADDITIONAL_FUNDS_RECEIVED = "Additional Funds Received";
    private static final String FACULTATIVE_RECOVERY = "Facultative Recovery";
    private static final String SMASH_REPAIR_RECOVERY = "Smash Repair Recovery";
    private static final String FEDERAL_GOVERNMENT_RECOVERY = "Federal Government Recovery";
    private static final String TREATY_REOOVERY = "Treaty Recovery";

    @FindBy(xpath = "//input[contains(@id,'_LocalSaveButton_cloned')]")
    private WebElement okButton;

    //Recovery Type Additional Funds Received
    @FindBy(xpath = "//td[contains(@title,'Additional Funds Received')]")
    private WebElement recoveryTypeAdditionalFundsReceived;

    //Recovery Type Facultative Recovery
    @FindBy(xpath = "//td[contains(@title,'Facultative Recovery')]")
    private WebElement recoveryTypeFacultativeRecovery;

    //Recovery Type Smash Repair Recovery
    @FindBy(xpath = "//td[contains(@title,'Smash Repair Recovery')]")
    private WebElement recoveryTypeSmashRepairRecovery;

    //Recovery Type Federal Government Recovery
    @FindBy(xpath = "//td[contains(@title,'Federal Government Recovery')]")
    private WebElement recoveryTypeFederalGovernmentRecovery;

    //Recovery Type Treaty Recovery
    @FindBy(xpath = "//td[contains(@title,'Treaty Recovery')]")
    private WebElement recoveryTypeTreatyRecovery;

    //Recovery Type Excess Collection
    @FindBy(xpath = "//td[contains(@title,'Excess Collection')]")
    private WebElement recoveryTypeExcessCollection;

    //Recovery Type Write off
    @FindBy(xpath = "//td[contains(@title,'Excess Write-Off')]")
    private WebElement recoveryTypeWriteOff;

    //Recovery Type Received in Error
    @FindBy(xpath = "//td[contains(@title,'Received in Error')]")
    private WebElement recoveryTypeReceivedInError;

    //Recovery Type Salvage Recovery - Parts
    @FindBy(xpath = "//td[contains(@title,'Salvage Recovery - Parts')]")
    private WebElement recoveryTypeSalvageParts;

    //Recovery Type Salvage Recovery - Whole
    @FindBy(xpath = "//td[contains(@title,'Salvage Recovery - Whole')]")
    private WebElement recoveryTypeSalvageWhole;

    //Recovery Type Statistical Excess Collection
    @FindBy(xpath = "//td[contains(@title,'Statistical Excess Collection')]")
    private WebElement recoveryTypeStatisticalExcessColletion;

    //Recovery Type Statistical Subrogation
    @FindBy(xpath = "//td[contains(@title,'Statistical Subrogation Recovery')]")
    private WebElement recoveryTypeStatisticalSubrogation;

    //Recovery Type Subrogation - Expense
    @FindBy(xpath = "//td[contains(@title,'Subrogation - Expenses Recovery')]")
    private WebElement recoveryTypeSubrogationExpense;

    //Recovery Type Subrogation - Settlement
    @FindBy(xpath = "//td[contains(@title,'Subrogation - Settlement Recovery')]")
    private WebElement recoveryTypeSubrogationSettlement;

    public ChooseRecoveryTypePage() {
        super("CHOOSE RECOVERY PAGE");
    }

    public void clickRecoveryTypeAdditionalFundsReceived(final ChooseRecoveryTypePage.ChooseRecoveryPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Recovery Type Write Off");
        click(recoveryTypeAdditionalFundsReceived);
    }

    public void clickRecoveryTypeFacultativeRecovery(final ChooseRecoveryTypePage.ChooseRecoveryPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Recovery Type Facultative Recovery");
        click(recoveryTypeFacultativeRecovery);
    }

    public void clickRecoveryTypeSmashRepairRecovery(final ChooseRecoveryTypePage.ChooseRecoveryPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Recovery Type Smash Repair Recovery");
        click(recoveryTypeSmashRepairRecovery);
    }

    public void clickRecoveryTypeFederalGovernmentRecovery(final ChooseRecoveryTypePage.ChooseRecoveryPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Recovery Type Federal Government Recovery");
        click(recoveryTypeFederalGovernmentRecovery);
    }

    public void clickRecoveryTypeTreatyRecovery(final ChooseRecoveryTypePage.ChooseRecoveryPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Recovery Type Treaty Recovery");
        click(recoveryTypeTreatyRecovery);
    }

    public void clickOkToProceed(final ChooseRecoveryTypePage.ChooseRecoveryPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking save button");
        click(okButton);
    }

    public void clickRecoveryTypeWriteOff(final ChooseRecoveryTypePage.ChooseRecoveryPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Recovery Type Write Off");
        click(recoveryTypeWriteOff);
    }

    public void clickRecoveryTypeReceivedInError(final ChooseRecoveryTypePage.ChooseRecoveryPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking recovery Type Received in Error");
        click(recoveryTypeReceivedInError);
    }

    public void clickRecoveryTypeSalvageParts(final ChooseRecoveryTypePage.ChooseRecoveryPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Recovery Type Salvage Parts");
        click(recoveryTypeSalvageParts);
    }

    public void clickRecoveryTypeSalvageWhole(final ChooseRecoveryTypePage.ChooseRecoveryPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Recovery Type Salvage Whole");
        click(recoveryTypeSalvageWhole);
    }

    public void clickRecoveryTypeStatisticalExcessColletion(final ChooseRecoveryTypePage.ChooseRecoveryPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Recovery Type Statistical Excess Colletion");
        click(recoveryTypeStatisticalExcessColletion);
    }

    public void clickRecoveryTypeStatisticalSubrogation(final ChooseRecoveryTypePage.ChooseRecoveryPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Recovery Type Statistical Subrogation");
        click(recoveryTypeStatisticalSubrogation);
    }

    public void clickRecoveryTypeSubrogationExpenses(final ChooseRecoveryTypePage.ChooseRecoveryPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Recovery Type Subrogation Expense");
        click(recoveryTypeSubrogationExpense);
    }

    public void clickRecoveryTypeSubrogationSettlement(final ChooseRecoveryTypePage.ChooseRecoveryPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Recovery Type Subrogation Settlement");
        click(recoveryTypeSubrogationSettlement);
    }

    public void clickRecoveryTypeExcessCollection(final ChooseRecoveryTypePage.ChooseRecoveryPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Recovery Type Excess Collection");
        click(recoveryTypeExcessCollection);
    }

    public void chooseRecoveryType(final ChooseRecoveryTypePage.ChooseRecoveryPageRequest pageRequest) {

        Map<String, WebElement> mapping = new HashMap<>();

        mapping.put(EXCESS_COLLECTION, recoveryTypeExcessCollection);
        mapping.put(EXCESS_WRITE_OFF, recoveryTypeWriteOff);
        mapping.put(RECEIVED_IN_ERROR, recoveryTypeReceivedInError);
        mapping.put(SALVAGE_RECOVERY_WHOLE, recoveryTypeSalvageWhole);
        mapping.put(SALVAGE_RECOVERY_PARTS, recoveryTypeSalvageParts);
        mapping.put(STATISTICAL_EXCESS_COLLECTION, recoveryTypeStatisticalExcessColletion);
        mapping.put(STATISTICAL_SUBROGATION, recoveryTypeStatisticalSubrogation);
        mapping.put(SUBROGATION_EXPENSES, recoveryTypeSubrogationExpense);
        mapping.put(SUBROGATION_SETTLEMENT, recoveryTypeSubrogationSettlement);
        mapping.put(ADDITIONAL_FUNDS_RECEIVED, recoveryTypeAdditionalFundsReceived);
        mapping.put(FACULTATIVE_RECOVERY, recoveryTypeFacultativeRecovery);
        mapping.put(SMASH_REPAIR_RECOVERY, recoveryTypeSmashRepairRecovery);
        mapping.put(FEDERAL_GOVERNMENT_RECOVERY, recoveryTypeFederalGovernmentRecovery);
        mapping.put(TREATY_REOOVERY, recoveryTypeTreatyRecovery);

        String recoveryType = pageRequest.getRecoveryType();
        pageRequest.log(getPageName(), "Clicking Recovery Type " + recoveryType);
        click(mapping.get(recoveryType));
    }

    public static class ChooseRecoveryPageRequest extends AbstractPageRequest {

        public ChooseRecoveryPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getRecoveryType() {
            return get("Recovery Type");
        }
    }
}
