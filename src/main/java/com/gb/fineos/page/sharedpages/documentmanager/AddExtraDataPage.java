package com.gb.fineos.page.sharedpages.documentmanager;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddExtraDataPage extends BasePage {


    @FindBy(xpath = "//textarea[contains(@id,'AddExtraDataWidget_') and contains(@id,'_ExclusionsConsidered')]")
    private WebElement exclusionsConsideredTextArea;
    @FindBy(xpath = "//textarea[contains(@id,'AddExtraDataWidget_') and contains(@id,'_EndorsementsConsidered')]")
    private WebElement endorsementsConsideredTextArea;
    @FindBy(xpath = "//textarea[contains(@id,'AddExtraDataWidget_') and contains(@id,'_ExcessStatus')]")
    private WebElement excessStatusTextArea;
    @FindBy(xpath = "//input[contains(@id,'AddExtraDataWidget_') and contains(@id,'_WithoutPrejudice_CHECKBOX')]")
    private WebElement withoutPrejudiceCheckBox;
    @FindBy(xpath = "//textarea[contains(@id,'AddExtraDataWidget_') and contains(@id,'_CoverageDecisionComment')]")
    private WebElement coverageDecisionCommentTextArea;

    @FindBy(xpath = "//textarea[contains(@id,'EditExtraDataWidget_') and contains(@id,'_OfferBreakdown')]")
    private WebElement offerBreakdownTextArea;
    @FindBy(xpath = "//textarea[contains(@id,'EditExtraDataWidget_') and contains(@id,'_OfferConditions')]")
    private WebElement offerConditionsTextArea;
    @FindBy(xpath = "//textarea[contains(@id,'EditExtraDataWidget_') and contains(@id,'_Notes')]")
    private WebElement notesTextArea;
    @FindBy(xpath = "//input[contains(@id,'EditExtraDataWidget_') and contains(@id,'_NextAction')]")
    private WebElement nextActionTextArea;
    @FindBy(xpath = "//textarea[contains(@id,'EditExtraDataWidget_') and contains(@id,'_InsurerApprovalObtained')]")
    private WebElement insurerApprovalObtainedDropdown;
    @FindBy(xpath = "//select[contains(@id,'ExtraDataWidget_') and contains(@id,'_closureReason')]")
    private WebElement closureReasonDropdown;


    @FindBy(xpath = "//input[contains(@id,'_editPageSave_cloned')]")
    private WebElement saveButton;


    public WebElement getExclusionsConsideredTextArea() {
        return exclusionsConsideredTextArea;
    }

    public WebElement getEndorsementsConsideredTextArea() {
        return endorsementsConsideredTextArea;
    }

    public WebElement getExcessStatusTextArea() {
        return excessStatusTextArea;
    }

    public WebElement getWithoutPrejudiceCheckBox() {
        return withoutPrejudiceCheckBox;
    }

    public WebElement getCoverageDecisionCommentTextArea() {
        return coverageDecisionCommentTextArea;
    }

    public AddExtraDataPage() {
        super("ADD EXTRA DATA PAGE");
    }


    public void enterExclusionsConsideredValue(final AddExtraDataPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Exclusions Considered Text : " + pageRequest.getExclusionsConsidered());
        clearAndInput(pageRequest.getExclusionsConsidered(), getExclusionsConsideredTextArea());
    }

    public void enterExcessStatusValue(final AddExtraDataPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Excess Status Text : " + pageRequest.getExcessStatus());
        clearAndInput(pageRequest.getExcessStatus(), getExcessStatusTextArea());
    }
    public void enterEndorsementsConsideredValue(final AddExtraDataPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Endorsements Considered Text : " + pageRequest.getEndorsementsConsidered());
        clearAndInput(pageRequest.getEndorsementsConsidered(), getEndorsementsConsideredTextArea());
    }
    public void enterCoverageDecisionCommentValue(final AddExtraDataPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Coverage Decision Comment Text : " + pageRequest.getCoverageDecisionComment());
        clearAndInput(pageRequest.getCoverageDecisionComment(), getCoverageDecisionCommentTextArea());
    }

    public void enterOfferBreakdown(final AddExtraDataPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Offer Breakdown Text : " + pageRequest.getExclusionsConsidered());
        clearAndInput(pageRequest.getExclusionsConsidered(), offerBreakdownTextArea);
    }

    public void enterOfferConditions(final AddExtraDataPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Offer Conditions Text : " + pageRequest.getExclusionsConsidered());
        clearAndInput(pageRequest.getExclusionsConsidered(), offerConditionsTextArea);
    }

    public  void selectClosureReasonOfNotDuplicate(final AddExtraDataPageRequest pageRequest) {
        String closureReason = "Not a duplicate";
        pageRequest.log(getPageName(), "Selecting closure reason as: " + closureReason);
        selectValue(closureReason, closureReasonDropdown);
    }

    public void clickOkButton(final AddExtraDataPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking OK button");
        click(saveButton);
    }




    public static class AddExtraDataPageRequest extends AbstractPageRequest {

        public AddExtraDataPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getExclusionsConsidered() {
            return get("ExclusionsConsidered");
        }

        public String getExcessStatus() {
            return get("ExcessStatus");
        }

        public String getEndorsementsConsidered() {
            return get("EndorsementsConsidered");
        }

        public String getCoverageDecisionComment() {
            return get("CoverageDecisionComment");
        }
    }


}
