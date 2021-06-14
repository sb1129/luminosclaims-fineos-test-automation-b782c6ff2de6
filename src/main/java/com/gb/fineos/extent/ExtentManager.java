package com.gb.fineos.extent;

import com.gb.fineos.factory.PropertiesFactory;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public final class ExtentManager {
    private static final ExtentManager INSTANCE = new ExtentManager();

    private final ExtentReports extent;

    private ExtentManager() {
        final Properties config = PropertiesFactory.getInstance().getProperties();

        final String extentLocation =
            config.getProperty("ReportLocation") + "_" + new SimpleDateFormat("yyyy-MM-dd-HHmmss").format(new Date()) + ".html";

        extent = new ExtentReports(config.getProperty("extentReportPath") + extentLocation, true, DisplayOrder.OLDEST_FIRST);
        extent.loadConfig(ClassLoader.getSystemResource("extent-config.xml"));
        extent.addSystemInfo("User", "Gallagher Bassett Services Pty Ltd.");
        extent.addSystemInfo("OS", config.getProperty("Operating_System"));
    }

    public static ExtentReports getInstance() {
        return INSTANCE.extent;
    }
}
