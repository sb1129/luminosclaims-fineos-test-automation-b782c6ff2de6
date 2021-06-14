package com.gb.fineos.page.sharedpages.workmanager.roleadministration;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.factory.PageFactory;
import com.gb.fineos.page.BasePage;
import com.gb.fineos.page.component.Filter;
import com.gb.fineos.page.component.LeavingPagePopup;
import com.gb.fineos.page.component.MainHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EditRolePage extends BasePage {
    private MainHeader mainHeader;
    private Filter filter;
    private LeavingPagePopup leavingPagePopup;

    @FindBy(xpath = "//a[contains(@id,'filter') and contains(@onclick,'UnAllocatedUsersWidgetuserID_TextFilterUnAllocatedUsersWidget_')]")
    private WebElement availableUsersUserIdFilterButton;
    @FindBy(xpath = "//a[contains(@id,'filter') and contains(@onclick,'UnAllocatedUsersWidgetuserName_TextFilterUnAllocatedUsersWidget_')]")
    private WebElement availableUsersUserNameFilterButton;
    @FindBy(xpath = "//a[contains(@id,'filter') and contains(@onclick,'AllocatedUsersWidgetuserID_TextFilterAllocatedUsersWidget_')]")
    private WebElement allocatedUsersUserIdFilterButton;
    @FindBy(xpath = "//a[contains(@id,'filter') and contains(@onclick,'AllocatedUsersWidgetuserName_TextFilterAllocatedUsersWidget_')]")
    private WebElement allocatedUsersUserNameFilterButton;
    @FindBy(xpath = "//input[contains(@id,'UsersForRolePickWidget_') and contains(@id,'_AllocateSelectedItemsButton')]")
    private WebElement allocateSelectedUsersButton;
    @FindBy(xpath = "//input[contains(@id,'UsersForRolePickWidget_') and contains(@id,'_DeAllocateSelectedItemsButton')]")
    private WebElement deallocateSelectedUsersButton;
    @FindBy(xpath = "//input[contains(@id,'_editPageSave')]")
    private WebElement okButton;
    @FindBy(xpath = "//input[contains(@id,'_editPageCancel')]")
    private WebElement cancelButton;


    public EditRolePage() {
        super("EDIT ROLE PAGE");
        this.mainHeader = PageFactory.initElements(MainHeader.class);
        this.mainHeader.addPageNamePrefix(getPageName());
        this.filter = PageFactory.initElements(Filter.class);
        this.filter.addPageNamePrefix(getPageName());
        this.leavingPagePopup = PageFactory.initElements(LeavingPagePopup.class);
        this.leavingPagePopup.addPageNamePrefix(getPageName());
    }

    public Filter getFilter() {
        return filter;
    }

    public void clickOkButton(final EditRolePageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the 'OK' button");
        click(okButton);
    }

    public void clickCancelButton(final EditRolePageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on the Cancel button");
        click(cancelButton);
    }

    public void clickAvailableUsersUserIdFilterButton(final EditRolePageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking the Filter Button for Available Users - User ID");
        click(availableUsersUserIdFilterButton);
        filter.setFilter("UnAllocatedUsersWidget", "ItemsLVW", "userID");
    }

    public void clickAvailableUsersUserNameFilterButton(final EditRolePageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking the Filter Button for Available Users - User Name");
        click(availableUsersUserNameFilterButton);
        filter.setFilter("UnAllocatedUsersWidget", "ItemsLVW", "userName");
    }

    public void clickAllocatedUsersUserIdFilterButton(final EditRolePageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking the Filter Button for Allocated Users - User ID");
        click(allocatedUsersUserIdFilterButton);
        filter.setFilter("AllocatedUsersWidget", "ItemsLVW", "userID");
    }

    public void clickAllocatedUsersUserNameFilterButton(final EditRolePageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking the Filter Button for Allocated Users - User Name");
        click(allocatedUsersUserNameFilterButton);
        filter.setFilter("AllocatedUsersWidget", "ItemsLVW", "userName");
    }

    public void clickDeallocateSelectedUsersButton(final EditRolePageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking the '<' button to deallocate selected users role");
        click(deallocateSelectedUsersButton);
    }

    public void clickAllocateSelectedUser(final EditRolePageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking the '>' button to allocate selected users role");
        click(allocateSelectedUsersButton);
    }

    public boolean selectedAvailableUserIdsAreExpected(final EditRolePageRequest pageRequest) {
        return selectedColumnsAreExpectedValue(pageRequest, Arrays.asList(pageRequest.getUserToEdit()), "UnAllocatedUsersWidget", "userID");
    }

    public boolean selectedAllocatedUserIdsAreExpected(final EditRolePageRequest pageRequest) {
        return selectedColumnsAreExpectedValue(pageRequest, Arrays.asList(pageRequest.getUserToEdit()), "AllocatedUsersWidget", "userID");
    }

    private boolean selectedColumnsAreExpectedValue(final EditRolePageRequest pageRequest, final List<String> expectedValues, final String widgetName, final String columnName) {
        List<String> selectedColumnValues = getSelectedRowsColumnValues(widgetName, columnName);
        if (selectedColumnValues.isEmpty()) {
            pageRequest.getContext().log(getPageName(), "Search did not yield any results");

            return false;
        } else {
            if (expectedValues.size() > 1) {
                Arrays.sort(selectedColumnValues.toArray());
                Arrays.sort(expectedValues.toArray());
            }
            pageRequest.getContext().log(getPageName(), "Expected selected values are " + expectedValues.toString() + " - found " + selectedColumnValues.toString());
            boolean valuesMatch = selectedColumnValues.equals(expectedValues);
            if (!valuesMatch) {
                pageRequest.getContext().log(getPageName(), "Expected selected values do not match actual selected values");
            }
            return valuesMatch;
        }
    }

    private List<String> getSelectedRowsColumnValues(final String widgetName, final String columnName) {
        List<WebElement> selectedRows = getSelectedRows(widgetName);
        if (selectedRows.isEmpty()) {
            return Collections.emptyList();
        } else {
            List<String> selectedRowColumnValues = new ArrayList<>();
            for (WebElement row : selectedRows) {
                selectedRowColumnValues.add(row.findElement(By.xpath("//td[starts-with(@id,'" + widgetName + "') and contains(@id,'" + columnName + "')]")).getText());
            }
            return selectedRowColumnValues;
        }
    }

    private List<WebElement> getSelectedRows(final String widgetName) {
        return getTable(widgetName).findElements(By.className("ListRowSelected"));
    }

    private WebElement getTable(final String widgetName) {
        By xpath = By.xpath("//table[starts-with(@id,'" + widgetName + "_') and contains(@class,'ListTable')]");
        waitForElementClickable(xpath);
        return getDriver().findElement(xpath);
    }


    public static class EditRolePageRequest extends AbstractPageRequest {

        public EditRolePageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getUserToEdit() {
            return get("New_User");
        }
    }

    public LeavingPagePopup getLeavingPagePopup() {
        return leavingPagePopup;
    }
}
