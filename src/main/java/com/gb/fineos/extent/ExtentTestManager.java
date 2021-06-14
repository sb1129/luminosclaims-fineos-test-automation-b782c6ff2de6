package com.gb.fineos.extent;

import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class ExtentTestManager {
    private static Map<String, ExtentTestWrapper> nameToTestMap = new HashMap<>();
    private static Map<Long, ExtentTestHolder> threadIdToTestMap = new HashMap<>();

    private ExtentTestManager() {
        // Do nothing
    }

    public static synchronized ExtentTest getTest() {
        return threadIdToTestMap.get(Thread.currentThread().getId()).getExtentTest();
    }

    public static synchronized WebDriver getWebDriver() {
        return threadIdToTestMap.get(Thread.currentThread().getId()).getWebDriver();
    }

    public static synchronized void setWebDriver(final WebDriver webDriver) {
        threadIdToTestMap.get(Thread.currentThread().getId()).setWebDriver(webDriver);
    }


    public static synchronized void startTest(final String testName) {
        // if this extentTest has already been created return
        if (!nameToTestMap.containsKey(testName)) {
            nameToTestMap.put(testName, new ExtentTestWrapper(testName));
        }

        final ExtentTest extentTest = nameToTestMap.get(testName).startTest();
        threadIdToTestMap.put(Thread.currentThread().getId(), new ExtentTestHolder(extentTest));
    }

    public static synchronized void endTest(final String testName) {
        nameToTestMap.get(testName).endTest();
        threadIdToTestMap.remove(Thread.currentThread().getId());
    }

    public static synchronized void finish() {
        nameToTestMap.values().forEach(ExtentTestWrapper::finish);
    }

    private static final class ExtentTestHolder {
        private final ExtentTest extentTest;
        private WebDriver webDriver;

        private ExtentTestHolder(final ExtentTest extentTest) {
            this.extentTest = extentTest;
        }

        private ExtentTest getExtentTest() {
            return extentTest;
        }

        private WebDriver getWebDriver() {
            return webDriver;
        }

        private void setWebDriver(final WebDriver webDriver) {
            this.webDriver = webDriver;
        }
    }

    private static class ExtentTestWrapper {
        private final String testName;
        private final ExtentTest parent;

        ExtentTestWrapper(final String testName) {
            this.testName = testName;
            this.parent = ExtentManager.getInstance().startTest(testName);

        }

        ExtentTest startTest() {
            final ExtentTest child = ExtentManager.getInstance().startTest(testName);
            parent.appendChild(child);
            child.setStartedTime(new Date());
            return child;
        }

        void endTest() {
            // Do nothing as this logic has been deferred to 'finish' to ensure the correct status is shown on the parent.
        }

        void finish() {
            ExtentManager.getInstance().endTest(parent);
            ExtentManager.getInstance().flush();
        }
    }

}
