package com.gb.fineos.domain;

import com.gb.fineos.extent.ExtentTestManager;
import com.gb.fineos.factory.PageFactory;
import com.gb.fineos.factory.PropertiesFactory;
import com.gb.fineos.page.BasePage;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import static com.gb.fineos.screencapture.ScreenCapture.logScreenshot;

public class TestCase implements TestCaseContext {
    private static final Logger LOG = Logger.getLogger(TestCase.class);

    private static final String TEST_INSTANCE = "Test_Instance";

    private final String name;
    private final String description;
    private final TestInstance testInstance;
    private final Map<String, String> data;
    private final Consumer<TestCaseContext> loginConsumer;
    private final Consumer<TestCaseContext> testCaseConsumer;
    private final Consumer<TestCaseContext> logoutConsumer;
    private boolean authenticated;

    public TestCase(
        final String name,
        final String description,
        final Map<String, String> data,
        final Consumer<TestCaseContext> loginConsumer,
        final Consumer<TestCaseContext> testCaseConsumer,
        final Consumer<TestCaseContext> logoutConsumer) {

        this.name = name;
        this.description = description;
        this.testInstance = TestInstance.valueOfProperty(PropertiesFactory.getInstance().getProperties().getProperty(TEST_INSTANCE));
        this.data = data;
        this.loginConsumer = loginConsumer;
        this.logoutConsumer = logoutConsumer;
        this.testCaseConsumer = testCaseConsumer;
    }

    @Override
    public TestInstance getTestInstance() {
        return testInstance;
    }

    @Override
    public boolean isTestInstance(final TestInstance testInstance) {
        return getTestInstance() == testInstance;
    }

    @Override
    public Map<String, String> getData() {
        return data;
    }

    @Override
    public String getValue(final String key) {
        return data.get(key);
    }

    @Override
    public String getNotificationCaseNumber() {
        return data.get("GeneratedNotificationCaseAlias");
    }

    @Override
    public String getClaimCaseNumber() {
        return data.get("GeneratedClaimCaseAlias");
    }

    @Override
    public String getBenefitCaseNumber() {
        return data.get("GeneratedBenefitCaseAlias");
    }

    @Override
    public <T extends BasePage> T getPage(final Class<T> pageClass) {
        return PageFactory.initElements(pageClass);
    }

    public void start() {
        try {
            ExtentTestManager.getTest().setDescription(description);

            if (isEnabled()) {
                loginConsumer.accept(this);
                authenticated = true;
            }
        } catch (Exception e) {
            fail(e);
        }
    }

    public void execute() {
        testCaseConsumer.accept(this);
    }

    public void pass() {
        logScreenshot("SUCCESS SCREENSHOT", name, LogStatus.PASS);
    }

    public void fail(final Exception exception) {
        logScreenshot("FAIL SCREENSHOT", name, LogStatus.FAIL);
        LOG.error("Test " + name + " failed.", exception);
        Assert.fail("EXCEPTION CAUSED BY..." + exception.getMessage());
    }

    public void end() {
        if (authenticated) {
            logoutConsumer.accept(this);

            authenticated = false;
        }

        Optional.ofNullable(ExtentTestManager.getWebDriver()).ifPresent(WebDriver::quit);
        ExtentTestManager.setWebDriver(null);
    }

    public boolean isEnabled() {
        return data.getOrDefault("Runmode", "N").equalsIgnoreCase("Y");
    }

    @Override
    public void log(final String action, final String message) {
        LOG.info(message);
        ExtentTestManager.getTest().log(LogStatus.INFO, action, message);
    }

    @Override
    public void skip(final String action, final String message) {
        LOG.info(message);
        ExtentTestManager.getTest().log(LogStatus.SKIP, action, message);
    }

    @Override
    public void error(final String action, final Exception exception) {
        LOG.error(exception);
        logScreenshot(action, LogStatus.ERROR);
    }

    public void error(final String action, final String message) {
        LOG.error(message);
        logScreenshot(action, LogStatus.ERROR);
        throw new AssertionError(message);
    }

    @Override
    public void warning(final String action, final String message, final boolean withScreenshot) {
        LOG.info(message);
        ExtentTestManager.getTest().log(LogStatus.WARNING, action, message);
        if (withScreenshot) {
            logScreenshot(action, LogStatus.WARNING);
        }
    }
}
