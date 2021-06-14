package com.gb.fineos.script.utils;

import com.gb.fineos.domain.TestCase;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.extent.ExtentTestManager;
import com.gb.fineos.factory.PageFactory;
import com.gb.fineos.listener.TestListener;
import com.gb.fineos.page.DashboardPage;
import com.gb.fineos.page.DashboardPage.DashboardPageRequest;
import com.gb.fineos.page.LoginPage;
import com.gb.fineos.page.LoginPage.LoginPageRequest;
import com.gb.fineos.selenium.SeleniumHelper;
import org.testng.annotations.Listeners;

import java.util.Map;
import java.util.function.Consumer;

@Listeners(TestListener.class)
public abstract class BaseTest {

    protected final void doTest(final String name, final String description, final Map<String, String> testData, final Consumer<TestCaseContext> consumer) {
        final TestCase testCase = new TestCase(name, description, testData, testCaseLoginConsumer(), consumer, testCaseLogoutConsumer());

        if (testCase.isEnabled()) {
            try {
                ExtentTestManager.setWebDriver(SeleniumHelper.getWebDriver());
                testCase.start();
                testCase.execute();
                testCase.pass();
            } catch (Exception e) {
                testCase.fail(e);
            } finally {
                testCase.end();
            }
        } else {
            testCase.skip(name, "Test is not enabled.");
        }
    }

    private Consumer<TestCaseContext> testCaseLoginConsumer() {
        return tc -> {
            final LoginPage loginPage = tc.getPage(LoginPage.class);
            loginPage.doLogin(new LoginPageRequest(tc));
            PageFactory.waitForPageToLoad(5000L);
        };
    }

    private Consumer<TestCaseContext> testCaseLogoutConsumer() {
        return tc -> {
            final DashboardPage dashboardPage = tc.getPage(DashboardPage.class);
            dashboardPage.doLogout(new DashboardPageRequest(tc));
        };
    }
}
