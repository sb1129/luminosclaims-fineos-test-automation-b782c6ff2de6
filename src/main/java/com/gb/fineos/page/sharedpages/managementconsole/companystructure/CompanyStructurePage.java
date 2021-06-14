package com.gb.fineos.page.sharedpages.managementconsole.companystructure;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CompanyStructurePage extends BasePage {
    @FindBy(xpath = "//td[contains(@id,'CompanyStructureTab_') and contains(@id,'_FINEOS.ManagementConsole.CompanyStructureTabControl_Departments_cell')]")
    private WebElement departmentsTab;

    @FindBy(xpath = "//td[contains(@id,'CompanyStructureTab_') and contains(@id,'_FINEOS.ManagementConsole.CompanyStructureTabControl_Users_cell')]")
    private WebElement usersTab;

    @FindBy(xpath = "//td[contains(@id,'CompanyStructureTab_') and contains(@id,'_FINEOS.ManagementConsole.CompanyStructureTabControl_SecuredActions_cell')]")
    private WebElement securedActionsTab;

    public CompanyStructurePage() {
        super("COMPANY STRUCTURE PAGE");
    }

    public void clickDepartmentsTab(final CompanyStructurePageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Departments tab");
        click(departmentsTab);
    }

    public void clickUsersTab(final CompanyStructurePageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Users tab");
        click(usersTab);
    }

    public void clickSecuredActionsTab(final CompanyStructurePageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Secured Actions tab");
        click(securedActionsTab);
    }

    public static class CompanyStructurePageRequest extends AbstractPageRequest {
        public CompanyStructurePageRequest(final TestCaseContext context) {
            super(context);
        }
    }
}
