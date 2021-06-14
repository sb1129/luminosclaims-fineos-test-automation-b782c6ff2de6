package com.gb.fineos.page.sharedpages.casemanager.casealias;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class AddCaseAliasPage extends BasePage {

    private static final String ALIAS_TYPE = "1";

    @FindBy(xpath = "//select[contains(@id,'_aliasTypeDropDownBean')]")
    private WebElement aliasTypeDropDownBean;
    @FindBy(xpath = "//input[contains(@id,'_Alias_Value')]")
    private WebElement caseAliasValueTextBox;
    @FindBy(xpath = "//input[contains(@id,'_editPageSave')]")
    private WebElement okButton;


    public AddCaseAliasPage() {
        super("ADD CASE ALIAS PAGE");
    }

    public void enterCaseAliasValue(final AddCaseAliasPageRequest pageRequest) {
        String alias = pageRequest.getCaseAliasValue().replace('-', '_') + "-" + pageRequest.getUserName() + "-" + pageRequest.getTestRunIdentifier();
        pageRequest.log(getPageName(), "Entering Alias value as: " + alias);
        clearAndInput(alias, caseAliasValueTextBox);
    }


    public void selectAliasTypeDropDown(final AddCaseAliasPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting Alias Type DropDown Bean Type as :  Other Claim Reference");
        selectByIndex(ALIAS_TYPE, aliasTypeDropDownBean);
    }

    public void clickOkButton(final AddCaseAliasPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking OK");
        click(okButton);
    }
    public void selectAliasTypePoliceIncidentNumber(final AddCaseAliasPageRequest pageRequest, final int indexValue) {
        pageRequest.log(getPageName(), "Selecting Alias Type DropDown Bean Type as :  Police Incident Number");
        selectByIndex(String.valueOf(indexValue), aliasTypeDropDownBean);
    }

    public void enterPoliceIncidentNumber(final AddCaseAliasPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Police Incident Number as : " + getDayAndTime());
        clearAndInput(getDayAndTime(), caseAliasValueTextBox);
    }

    public static class AddCaseAliasPageRequest extends AbstractPageRequest {

        public AddCaseAliasPageRequest(final TestCaseContext context) {
            super(context);
        }


        public String getCaseAliasValue() {
            return get("CaseAliasValue");
        }

        public String getCaseAlias(final String caseType) {
            return get(caseType);
        }
        public String getUserName() {
            return get("UserName");
        }
    }
}
