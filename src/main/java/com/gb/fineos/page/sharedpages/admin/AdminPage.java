package com.gb.fineos.page.sharedpages.admin;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.factory.PageFactory;
import com.gb.fineos.page.BasePage;
import com.gb.fineos.page.component.MainHeader;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminPage extends BasePage {

    private MainHeader mainHeader;

    @FindBy(xpath = "//input[contains(@id,'UserManagementWidget_') and contains(@id,'_UserManagement_Company_Structure_Company_Structureimage')]")
    private WebElement companyStructureIcon;
    @FindBy(xpath = "//div[contains(@onclick,'WorkflowWidget_') and contains(@onclick,'_Workflow_Roles_Rolesimage')]")
    private WebElement roles;

    @FindBy(xpath = "//input[contains(@id,'ServicesWidget_') and contains(@id,'_Services_Configuration_Manager_Configuration_Managerimage')]")
    private WebElement configurationManager;

    public AdminPage() {
        super("CONFIGURATION STUDIO PAGE");
        this.mainHeader = PageFactory.initElements(MainHeader.class);
        this.mainHeader.addPageNamePrefix(getPageName());
    }

    public void clickCompanyStructureIcon(final AdminPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Configuration Studio Icon");
        click(companyStructureIcon);
    }


    public void clickRoles(final AdminPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Roles Button");
        click(roles);
    }

    public void clickConfigurationManagerIcon(final AdminPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Configuration Manager Icon");
        click(configurationManager);
    }

    public static class AdminPageRequest extends AbstractPageRequest {

        public AdminPageRequest(final TestCaseContext context) {
            super(context);
        }
    }
}
