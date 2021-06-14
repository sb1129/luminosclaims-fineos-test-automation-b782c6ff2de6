package com.gb.fineos.listener;

import com.gb.fineos.extent.ExtentTestManager;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Date;

public class TestListener implements ITestListener {
    private static final Logger LOG = Logger.getLogger(TestListener.class);

    public void onStart(final ITestContext context) {
        LOG.info("*** Test Suite " + context.getName() + " started ***");
    }

    public void onFinish(final ITestContext context) {
        LOG.info("*** Test Suite " + context.getName() + " ending ***");
        ExtentTestManager.finish();
    }

    public void onTestStart(final ITestResult result) {
        LOG.info("*** Running test method " + result.getMethod().getMethodName() + "...");
        ExtentTestManager.startTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(final ITestResult result) {
        LOG.info("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
        ExtentTestManager.getTest().log(LogStatus.PASS, "Test passed");
        ExtentTestManager.getTest().setEndedTime(new Date());
    }

    public void onTestFailure(final ITestResult result) {
        LOG.error("*** Test execution " + result.getMethod().getMethodName() + " failed...");
        ExtentTestManager.getTest().log(LogStatus.FAIL, "Test Failed", result.getThrowable());
        ExtentTestManager.getTest().setEndedTime(new Date());
    }

    public void onTestSkipped(final ITestResult result) {
        LOG.warn("*** Test " + result.getMethod().getMethodName() + " skipped...");
        ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Skipped", result.getThrowable().getMessage());
        ExtentTestManager.getTest().setEndedTime(new Date());
    }

    public void onTestFailedButWithinSuccessPercentage(final ITestResult result) {
        LOG.error("*** Test failed but within percentage % " + result.getMethod().getMethodName());
        ExtentTestManager.getTest().setEndedTime(new Date());
    }
}
