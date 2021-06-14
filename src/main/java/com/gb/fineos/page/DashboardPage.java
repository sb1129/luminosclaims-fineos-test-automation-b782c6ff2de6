package com.gb.fineos.page;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.factory.PageFactory;
import com.gb.fineos.page.component.LeavingPagePopup;
import com.gb.fineos.page.component.MainHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends BasePage {
    private MainHeader mainHeader;

    @FindBy(css = "a[id$='_MENUBAR.SidebarMenu_MENUITEM.Home_MENUITEM.Homelink']")
    private WebElement sidebarHomeLink;
    @FindBy(css = "a[id$='_MENUBAR.SidebarMenu_MENUITEM.SearchCases_MENUITEM.SearchCaseslink']")
    private WebElement sidebarCasesLink;
    @FindBy(xpath = "//input[contains(@id,'_MENUBAR.UtilitiesMenu_DROPDOWNMENU.UserOptions_DROPDOWNMENU.UserOptionslink')]")
    private WebElement userOptionsLink;
    @FindBy(css = "input[id$='_Logout_yes']")
    private WebElement confirmLogoutYesBtn;
    @FindBy(css = "input[id$='_Logout_no']")
    private WebElement confirmLogoutNoBtn;
    @FindBy(xpath = "//a[contains(@id,'_MENUBAR.SidebarMenu_MENUITEM.Tasks_MENUITEM.Taskslink')]")
    private WebElement sidebarTasksLink;
    @FindBy(css = " a[id$='_MENUBAR.SidebarMenu_MENUITEM.SearchParties_MENUITEM.SearchPartieslink']")
    private static WebElement sidebarPartiesLink;
    @FindBy(xpath = "//a[contains(@id,'_MENUITEM.SearchPolicieslink')]")
    private static WebElement sidebarPoliciesLink;

    public DashboardPage() {
        super("DASHBOARD PAGE");
        this.mainHeader = PageFactory.initElements(MainHeader.class);
        this.mainHeader.addPageNamePrefix(getPageName());
    }

    public MainHeader getMainHeader() {
        return mainHeader;
    }

    public void doLogout(final DashboardPageRequest pageRequest) {
        clickSidebarHomeLink(pageRequest);

        if (isLeavingPagePopupDisplayed()) {
            pageRequest.getContext().getPage(LeavingPagePopup.class).clickYesButton(pageRequest);
        }

        mainHeader.clickUserOptionsDropdown(pageRequest);
        mainHeader.clickLogout(pageRequest);
        clickConfirmLogoutYes(pageRequest);
    }

    public boolean isLeavingPagePopupDisplayed() {
        return !getDriver().findElements(By.xpath("//input[contains(@id,'_warningsLeavingPagePopup_yes')]")).isEmpty();
    }

    public boolean verifyCurrentUser(final DashboardPageRequest pageRequest, final String userFullName) {
        clickSidebarHomeLink(pageRequest);
        mainHeader.clickUserOptionsDropdown(pageRequest);
        if (mainHeader.getUserName(pageRequest).trim().equals(userFullName)) {
            return false;
        } else {
            mainHeader.clickLogout(pageRequest);
            clickConfirmLogoutYes(pageRequest);
            return true;
        }
    }

    // TODO move this to a separate component for sidebar links
    public void doNavigateToCases(final DashboardPageRequest pageRequest) {
        clickSidebarCasesLink(pageRequest);
    }

    public void clickSidebarHomeLink(final DashboardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on home page sidebar link");
        click(sidebarHomeLink);
    }

    public void clickSidebarTasksLink(final DashboardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Tasks sidebar link");
        click(sidebarTasksLink);
    }

    public void clickSidebarPartiesLink(final DashboardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking sidebar Parties Link");
        click(sidebarPartiesLink);
    }

    public void clickSidebarCasesLink(final DashboardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on cases sidebar link");
        click(sidebarCasesLink);
    }

    public void clickSidebarPoliciesLink(final DashboardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on policies sidebar link");
        click(sidebarPoliciesLink);
    }

    private void clickConfirmLogoutYes(final DashboardPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Confirm Logout Yes Button");
        clickModalBox(confirmLogoutYesBtn);
    }

    public static class DashboardPageRequest extends AbstractPageRequest {

        public DashboardPageRequest(final TestCaseContext context) {
            super(context);
        }
    }
}
