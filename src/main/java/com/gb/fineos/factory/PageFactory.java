package com.gb.fineos.factory;

import com.gb.fineos.extent.ExtentTestManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class PageFactory {
    private static final Logger LOG = Logger.getLogger(PageFactory.class);
    private static final long WAIT_TIME = 1000L;
    private static final int MILLIS = 1000;

    private PageFactory() {
        // Do nothing
    }

    public static <T> T initElements(final Class<T> pageClassToProxy) {
        final T page = org.openqa.selenium.support.PageFactory.initElements(ExtentTestManager.getWebDriver(), pageClassToProxy);
        waitForPageToLoad(WAIT_TIME);

        return page;
    }

    public static void waitForPageToLoad(final long timeOut) {
        try {
            long startTime = System.currentTimeMillis();
            long waitTime = startTime;
            long endTime = startTime + timeOut;

            while (System.currentTimeMillis() < endTime) {
                if (String.valueOf(((JavascriptExecutor) ExtentTestManager.getWebDriver()).executeScript("return document.readyState")).equals("complete")) {
                    waitTime = System.currentTimeMillis();
                    break;
                }
            }

            if (LOG.isDebugEnabled()) {
                final DateFormat timeOnlyDateFormat = new SimpleDateFormat("hh:mm:ss");
                final Date start = timeOnlyDateFormat.parse(timeOnlyDateFormat.format(new Date(startTime)));
                final Date end = timeOnlyDateFormat.parse(timeOnlyDateFormat.format(new Date(waitTime)));

                LOG.debug("PAGE LOAD TIME is : [" + (end.getTime() - start.getTime()) / MILLIS + "] seconds");
            }
        } catch (Exception e) {
            throw new AssertionError("***EXCEPTION OCCURRED WHILE PAGE IS LOADING...***", e);
        }
    }
}
