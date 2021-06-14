package com.gb.fineos.screencapture;

import com.gb.fineos.extent.ExtentTestManager;
import com.gb.fineos.factory.PropertiesFactory;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class ScreenCapture {
    private static final Logger LOG = Logger.getLogger(ScreenCapture.class);

    private ScreenCapture() {
        // Do nothing
    }

    public static void logScreenshot(final String pageName, final String action, final LogStatus captureType) {
        ExtentTestManager.getTest().log(captureType, pageName, captureScreenshot(action, captureType.toString()));
    }

    public static void logScreenshot(final String pageName, final LogStatus captureType) {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        String log = stackTraceElement.getFileName() + "_" + stackTraceElement.getMethodName();
        ExtentTestManager.getTest().log(captureType, pageName, captureScreenshot(log, captureType.toString()));
    }

    private static String captureScreenshot(final String action, final String captureType) {

        String captureTypeLower = captureType.toLowerCase();
        String captureTypeCamel = captureTypeLower.substring(0, 1).toUpperCase().concat(captureTypeLower.substring(1));
        String captureTypeUpper = captureType.toUpperCase();
        try {
            if (Boolean.parseBoolean(PropertiesFactory.getInstance().getProperties().getProperty("capture" + captureTypeCamel + "Screenshot"))) {
                String snapshotPath = PropertiesFactory.getInstance().getProperties().getProperty(captureTypeLower + "SnapshotPath");
                LOG.debug("Taking screenshot");
                final File scrFile = ((TakesScreenshot) ExtentTestManager.getWebDriver()).getScreenshotAs(OutputType.FILE);
                final File destFile = new File(PropertiesFactory.getInstance().getProperties().getProperty("extentReportPath")
                    + snapshotPath
                    + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
                    + "_"
                    + captureTypeUpper
                    + "_"
                    + action
                    + ".png");
                FileUtils.copyFile(scrFile, destFile);

                return ExtentTestManager.getTest().addScreenCapture("./" + snapshotPath + destFile.getName());
            } else {
                LOG.info("TAKING SCREENSHOT FOR " + captureTypeUpper + " IS NOT ENABLED");

                return null;
            }
        } catch (Exception e) {
            throw new AssertionError("***EXCEPTION OCCURRED WHILE TAKING SCREENSHOT...***", e);
        }
    }
}
