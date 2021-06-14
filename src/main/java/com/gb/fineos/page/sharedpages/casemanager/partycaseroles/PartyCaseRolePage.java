package com.gb.fineos.page.sharedpages.casemanager.partycaseroles;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PartyCaseRolePage extends BasePage {

    @FindBy(xpath = "//input[contains(@id,'_editPageSave')]")
    private WebElement okButton;


    public PartyCaseRolePage() {
        super("PARTY CASE ROLE PAGE");
    }



    public void clickOnOkButton(final PartyCaseRolePageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on OK Button");
        click(okButton);
    }



    public static class PartyCaseRolePageRequest extends AbstractPageRequest {

        public PartyCaseRolePageRequest(final TestCaseContext context) {
            super(context);
        }

    }
}
