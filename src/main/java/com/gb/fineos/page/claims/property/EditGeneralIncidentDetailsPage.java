package com.gb.fineos.page.claims.property;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditGeneralIncidentDetailsPage extends BasePage {
    public EditGeneralIncidentDetailsPage() {
        super("Edit General Incident Details Page");
    }

    @FindBy(xpath = "//input[contains(@id,'_incidentAddress_rcbSearchButton_incidentAddress')]")
    private WebElement editIncidentLocationSearchBtn;
    @FindBy(xpath = "//input[contains(@id,'_editPageSave_cloned')]")
    private WebElement editSaveOkBtn;
    @FindBy(xpath = "//input[contains(@id,'_editPageCancel')]")
    private WebElement editCancelBtn;
    @FindBy(xpath = "//span[contains(@id,'EditPropertyIncidentLocationDetailsContainerWidget_') and contains(@id,'_Incident_address_Label')]")
    private WebElement addressLabel;

    public WebElement getAddressLabel() {
        return addressLabel;
    }

    public void clickEditIncidentLocationSearchBtn(final EditGeneralIncidentDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on edit incident location search button");
        clickModalBox(editIncidentLocationSearchBtn);
    }

    public void clickEditSave(final EditGeneralIncidentDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Saving the changes");
        clickModalBox(editSaveOkBtn);
    }

    public void clickCancel(final EditGeneralIncidentDetailsPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Cancel Edit");
        click(editCancelBtn);
    }

    public String getAddressLabel(final EditGeneralIncidentDetailsPageRequest pageRequest) {
        waitForElementPresent(getAddressLabel());
        pageRequest.log(getPageName(), "Getting Address Label from the " + getPageName());
        return getAddressLabel().getText();
    }

    public static class EditGeneralIncidentDetailsPageRequest extends AbstractPageRequest {
        public EditGeneralIncidentDetailsPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getAddressLabel() {
            return get("addressLabel");
        }
    }

}
