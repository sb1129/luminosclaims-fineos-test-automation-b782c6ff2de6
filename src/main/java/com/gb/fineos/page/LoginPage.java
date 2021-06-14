package com.gb.fineos.page;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.extent.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Class Name : LoginPage
 * Description : Contains Initializing WebElements, Methods to perform actions on Web Page
 */
public class LoginPage extends BasePage {

    private static final String LOGIN = "login";
    private static final String USER_NAME_KEY = "UserName";
    private static final String PASSWORD_KEY = "Password";

    /**
     * Method Name : LoginPage()
     * Description : Parameterized Constructor - To initialize object
     */
    public LoginPage() {
        super("LOGIN PAGE");
    }

    /**
     * Method Name : doLogin()
     * Description : To login into application
     */
    public void doLogin(final LoginPageRequest pageRequest) {
        try {
            openApplication(pageRequest);
            doLogin(pageRequest, USER_NAME_KEY, PASSWORD_KEY);
        } catch (Exception e) {
            pageRequest.getContext().error(getPageName(), e);
            throw new AssertionError("Login failed.", e);
        }
    }

    @Deprecated
    public void doLogin(final LoginPageRequest pageRequest, final String usernameKey) {
        doLogin(pageRequest, usernameKey, PASSWORD_KEY);
    }

    public void doLogin(final LoginPageRequest pageRequest, final String usernameKey, final String passwordKey) {
        try {
            getDriver().switchTo().frame(getDriver().findElement(By.id(LOGIN)));
            enterUsername(pageRequest, usernameKey);
            enterPassword(pageRequest, passwordKey);
            clickLogin(pageRequest);
            ExtentTestManager.getTest().log(LogStatus.INFO, getPageName(), "Session Id: " + ((RemoteWebDriver) getDriver()).getSessionId());
            getDriver().switchTo().defaultContent();
            getDriver().switchTo().frame(getDriver().findElement(By.id("logininfo")));
            clickContinue(pageRequest);
        } catch (Exception e) {
            pageRequest.getContext().error(getPageName(), e);
            throw new AssertionError(e.getMessage(), e);
        }
    }

    private void enterUsername(final LoginPageRequest pageRequest, final String usernameKey) {
        final String username = pageRequest.getUsername(usernameKey);

        pageRequest.log(getPageName(), "Entering User Name : " + username);
        clearAndInput(username, getDriver().findElement(By.id("fineos_logindialog_nameinput_cell")));
    }

    private void enterPassword(final LoginPageRequest pageRequest, final String passwordKey) {
        final String password = pageRequest.getPassword(passwordKey);

        pageRequest.log(getPageName(), "Entering Password : " + password);
        clearAndInput(password, getDriver().findElement(By.id("fineos_logindialog_password_input_cell")));
    }

    private void clickLogin(final LoginPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Login Button");
        getDriver().findElement(By.id(LOGIN)).click();
    }

    private void clickContinue(final LoginPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Continue Button");
        getDriver().findElement(By.xpath("//input[@name='ok']")).click();
    }

    public void clickOnReLoginButton(final LoginPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Login Button");
        getDriver().findElement(By.id("button")).click();
    }

    private void openApplication(final LoginPageRequest pageRequest) {
        try {
            getDriver().get(getProperty("Application_URL"));
            pageRequest.log("LAUNCH APPLICATION", "Navigating to Application: " + getProperty("Application_URL"));
        } catch (Exception e) {
            throw new AssertionError("***EXCEPTION OCCURRED WHILE LAUNCHING URL INTO BROWSER***");
        }
    }

    public static class LoginPageRequest extends AbstractPageRequest {

        public LoginPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getUsername(final String usernameKey) {
            return get(usernameKey);
        }

        public String getPassword(final String passwordKey) {
            return getOrDefault(passwordKey, "");
        }
    }
}
