package com.gb.fineos.page.sharedpages.workmanager.roleadministration;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.factory.PageFactory;
import com.gb.fineos.page.BasePage;
import com.gb.fineos.page.component.MainHeader;
import com.gb.fineos.page.component.TreeView;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RoleAdminPage extends BasePage {
    private MainHeader mainHeader;
    private TreeView treeView;

    @FindBy(xpath = "//input[contains(@id,'_cmdPageBack_')]")
    private WebElement closeScreenButton;
    @FindBy(xpath = "//input[contains(@id,'DeptRolesTVW') and contains(@id,'addDeptButton')]")
    private WebElement addDeptButton;
    @FindBy(xpath = "//input[contains(@id,'DeptRolesTVW') and contains(@id,'editRoleButton')]")
    private WebElement editButton;

    public RoleAdminPage() {
        super("ROLE ADMIN PAGE");
        this.mainHeader = PageFactory.initElements(MainHeader.class);
        this.mainHeader.addPageNamePrefix(getPageName());
        this.treeView = PageFactory.initElements(TreeView.class);
        this.treeView.addPageNamePrefix(getPageName());
    }

    public TreeView getTreeView() {
        return treeView;
    }

    public void clickCloseScreenButton(final RoleAdminPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Close Screen button");
        click(closeScreenButton);
    }

    public void clickAddDeptButton(final RoleAdminPageRequest pageRequest) {
        if (addDeptButton.getAttribute("class").equalsIgnoreCase("ButtonDisabled Button")) {
            pageRequest.getContext().error(getPageName(), "Add Dept button is disabled");
        } else {
            pageRequest.log(getPageName(), "Clicking on Add Dept button");
            click(addDeptButton);
        }
    }

    public void clickEditButton(final RoleAdminPageRequest pageRequest) {
        if (editButton.getAttribute("class").equalsIgnoreCase("ButtonDisabled Button")) {
            pageRequest.getContext().error(getPageName(), "Edit button is disabled");
        } else {
            pageRequest.log(getPageName(), "Clicking on Edit button");
            click(editButton);
        }
    }

    public static class RoleAdminPageRequest extends AbstractPageRequest {

        public RoleAdminPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String[] getRoles() {
            return get("Role").split(",");
        }

        public String[] getDepartments() {
            return get("Department").split(",");
        }

    }
}
