package com.gb.fineos.script.seed;

import com.gb.fineos.page.DashboardPage;
import com.gb.fineos.page.sharedpages.admin.AdminPage;
import com.gb.fineos.page.sharedpages.orgstructure.DepartmentSearchPage;
import com.gb.fineos.page.sharedpages.workmanager.roleadministration.EditRolePage;
import com.gb.fineos.page.sharedpages.workmanager.roleadministration.RoleAdminPage;
import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.screencapture.ScreenCapture;
import com.gb.fineos.script.utils.BaseTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Description :
 */
public class AddRoleToUserTest extends BaseTest {

    @Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = "seed.common")
    public void addRoleToUserTest(final Map<String, String> testData) {

        doTest("addRoleToUser", "Successfully search for and add a role to a user", testData, tc -> {
            final DashboardPage dashboardPage = tc.getPage(DashboardPage.class);
            dashboardPage.getMainHeader().clickConfigStudio(new DashboardPage.DashboardPageRequest(tc));

            final AdminPage configurationStudio = tc.getPage(AdminPage.class);
            final AdminPage.AdminPageRequest configurationStudioPageRequest = new AdminPage.AdminPageRequest(tc);
            configurationStudio.clickRoles(configurationStudioPageRequest);

            final RoleAdminPage roleAdminPage = tc.getPage(RoleAdminPage.class);
            final RoleAdminPage.RoleAdminPageRequest roleAdminPageRequest = new RoleAdminPage.RoleAdminPageRequest(tc);

            final DepartmentSearchPage departmentSearch = tc.getPage(DepartmentSearchPage.class);
            final DepartmentSearchPage.DepartmentSearchPageRequest departmentSearchPageRequest = new DepartmentSearchPage.DepartmentSearchPageRequest(tc);
            for (String department : departmentSearchPageRequest.getDepartments()) {
                roleAdminPage.clickAddDeptButton(roleAdminPageRequest);
                departmentSearch.clickNameSearchTab(departmentSearchPageRequest);
                departmentSearch.inputDepartmentName(departmentSearchPageRequest, department);
                departmentSearch.clickSearchButton(departmentSearchPageRequest);
                departmentSearch.clickDepartment(departmentSearchPageRequest, department);
                departmentSearch.clickOkButton(departmentSearchPageRequest);

                ScreenCapture.logScreenshot("DEPARTMENT SEARCH", LogStatus.INFO);

                final EditRolePage editRolePage = tc.getPage(EditRolePage.class);
                final EditRolePage.EditRolePageRequest editRolePageRequest = new EditRolePage.EditRolePageRequest(tc);

                for (String role : roleAdminPageRequest.getRoles()) {
                    roleAdminPage.getTreeView().clickChildNodeElement(roleAdminPageRequest, department, role);
                    roleAdminPage.clickEditButton(roleAdminPageRequest);

                    editRolePage.clickAvailableUsersUserIdFilterButton(editRolePageRequest);

                    editRolePage.getFilter().inputFilterText(editRolePageRequest, editRolePageRequest.getUserToEdit());
                    editRolePage.getFilter().clickApplyButton(editRolePageRequest);

                    if (editRolePage.selectedAvailableUserIdsAreExpected(editRolePageRequest)) {
                        editRolePage.clickAllocateSelectedUser(editRolePageRequest);
                        editRolePage.clickOkButton(editRolePageRequest);
                        editRolePageRequest.getContext().log("EDIT ROLE PAGE", "Successfully given " + editRolePageRequest.getUserToEdit() + " the role " + role + " in the department " + department);
                    } else {
                        editRolePageRequest.getContext().log("EDIT ROLE PAGE", "Checking to see if user has already been allocated the role");
                        editRolePage.clickAvailableUsersUserIdFilterButton(editRolePageRequest);
                        editRolePage.getFilter().clickClearButton(editRolePageRequest);

                        editRolePage.clickAllocatedUsersUserIdFilterButton(editRolePageRequest);
                        editRolePage.getFilter().inputFilterText(editRolePageRequest, editRolePageRequest.getUserToEdit());
                        editRolePage.getFilter().clickApplyButton(editRolePageRequest);

                        if (editRolePage.selectedAllocatedUserIdsAreExpected(editRolePageRequest)) {
                            editRolePageRequest.getContext().warning("EDIT ROLE PAGE", editRolePageRequest.getUserToEdit() + " already has the role of " + role + " in " + department, true);
                            editRolePage.clickCancelButton(editRolePageRequest);

                        } else {
                            //exit to a non edit screen for safe completion of test
                            editRolePageRequest.getContext().log("EDIT ROLE PAGE", "Failing test due to unexpected results");
                            ScreenCapture.logScreenshot("EDIT ROLE PAGE", LogStatus.FAIL);
                            editRolePage.clickCancelButton(editRolePageRequest);
                            Assert.fail("Failing test due to unexpected results when searching for selected users. Check that the user exists in the department.");
                        }
                    }
                }
                roleAdminPage.clickCloseScreenButton(roleAdminPageRequest);
                configurationStudio.clickRoles(configurationStudioPageRequest);
            }

        });
    }
}
