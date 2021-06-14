package com.gb.fineos.page.sharedpages.orgstructure;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DepartmentSearchPage extends BasePage {
    @FindBy(xpath = "//td[contains(@id,'_FINEOS.OrgStructure.DeptSearch_deptByNameSearch_cell')]")
    private WebElement nameSearchTab;
    @FindBy(xpath = "//td[contains(@id,'_FINEOS.OrgStructure.DeptSearch_deptSearch_cell')]")
    private WebElement departmentsTab;
    @FindBy(xpath = "//input[contains(@id,'DepartmentSearchWidget_') and contains(@id,'_departmentName')]")
    private WebElement departmentNameSearchInput;
    @FindBy(xpath = "//input[contains(@id,'DepartmentSearchWidget') and contains(@id,'_findButton')]")
    private WebElement searchButton;
    @FindBy(xpath = "//input[contains(@id,'_searchPageOk')]")
    private WebElement okButton;

    public DepartmentSearchPage() {
        super("DEPARTMENT SEARCH PAGE");
    }

    public void clickNameSearchTab(final DepartmentSearchPageRequest pageRequest) {
        clickTab(pageRequest, nameSearchTab, "Name Search");
    }

    public void clickDepartmentsTab(final DepartmentSearchPageRequest pageRequest) {
        clickTab(pageRequest, departmentsTab, "Departments");
    }

    public void inputDepartmentName(final DepartmentSearchPageRequest pageRequest, final String departmentName) {
        pageRequest.log(getPageName(), "Entering Department Name as: " + departmentName);
        clearAndInput(departmentName, departmentNameSearchInput);
    }

    public void clickSearchButton(final DepartmentSearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Search");
        click(searchButton);
    }

    public void clickDepartment(final DepartmentSearchPageRequest pageRequest, final String departmentName) {
        pageRequest.log(getPageName(), "Looking for " + departmentName + " in table");
        final List<WebElement> tableRows = getDriver().findElements(By.xpath("//table[contains(@id,'ChooseDepartmentSearchWidget_') and contains(@id,'_chooseDeptList')]//tr"));
        if (!tableRows.isEmpty()) {
            clickRowInTable(pageRequest, tableRows, departmentName);
        }
    }

    public void clickOkButton(final DepartmentSearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking the OK button");
        click(okButton);
    }


    public static class DepartmentSearchPageRequest extends AbstractPageRequest {

        public DepartmentSearchPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String[] getDepartments() {
            return get("Department").split(",");
        }

    }
}
