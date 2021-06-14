package com.gb.fineos.validation;

import com.gb.fineos.extent.ExtentTestManager;
import com.gb.fineos.screencapture.ScreenCapture;
import com.relevantcodes.extentreports.LogStatus;
import junit.framework.AssertionFailedError;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class AssertionHelper {
    private static final Logger LOG = Logger.getLogger(AssertionHelper.class);
    private final WebDriver driver = ExtentTestManager.getWebDriver();
    private final SoftAssert softAssert = new SoftAssert();

    public AssertionHelper() {
        // Do nothing
    }

    public void assertTabIsSelected(final String tabName, final WebElement webElement) {
        assertEquals(tabName + " Tab is not the selected tab", "TabOn", getTabStatus(webElement));
    }

    public void assertTabIsNotSelected(final String tabName, final WebElement webElement) {
        assertEquals(tabName + " Tab is the selected tab", "TabOff", getTabStatus(webElement));
    }

    public void assertIsDisplayed(final String elementName, final WebElement webElement) {
        try {
            assertTrue(elementName + " Web Element is not visible", webElementIsDisplayed(webElement));
        } catch (AssertionFailedError e) {
            handleAssertionException(e);
        }
    }

    public void assertIsDisplayed(final String elementName, final String webElementID) {
        try {
            assertTrue(elementName + " Web Element is not visible", webElementIsDisplayed(webElementID));
        } catch (AssertionFailedError e) {
            handleAssertionException(e);
        }
    }

    public void assertIsNotDisplayed(final String elementName, final String webElementID) {
        try {
            assertFalse(elementName + " Web Element is visible", webElementIsDisplayed(webElementID));
        } catch (AssertionFailedError e) {
            handleAssertionException(e);
        }
    }

    public void assertIsNotDisplayed(final String elementName, final WebElement webElement) {
        try {
            assertFalse(elementName + " Web Element is visible", webElementIsDisplayed(webElement));
        } catch (AssertionFailedError e) {
            handleAssertionException(e);
        }
    }

    private String getTabStatus(final WebElement webElement) {
        String tabValue = "";
        try {
            tabValue = driver.findElement(By.id(webElement.getAttribute("id"))).getAttribute("class");
        } catch (NoSuchElementException e) {
            handleNoSuchElementExceptionAndThrow(webElement, e);
        }
        return tabValue;
    }

    private boolean webElementIsDisplayed(final String webElementID) {
        return !driver.findElements(By.id(webElementID)).isEmpty();
    }

    private boolean webElementIsDisplayed(final WebElement webElement) {
        boolean condition = false;
        try {
            condition = webElementIsDisplayed(webElement.getAttribute("id"));
        } catch (NoSuchElementException e) {
            handleNoSuchElementExceptionAndThrow(webElement, e);
        }
        return condition;
    }

    public void handleNoSuchElementExceptionAndThrow(final WebElement webElement, final NoSuchElementException exception) {
        if (widgetErrorFound()) {
            handleWidgetException();
        } else {
            LOG.error("Unable to identify web element: " + webElement, exception);
            throw exception;
        }
    }

    public void handleNoSuchElementException(final WebElement webElement) {
        LOG.error("Unable to identify web element: " + webElement);
    }

    private void handleAssertionException(final AssertionFailedError e) {
        if (widgetErrorFound()) {
            handleWidgetException();
        } else {
            throw e;
        }
    }

    private void handleWidgetException() {
        WidgetException widgetException = new WidgetException(driver.findElement(By.xpath("//div[contains(@class,'WidgetFailedPageMessage')]")).getText());
        LOG.error("Widget Failed Error Message: " + widgetException.getMessage(), widgetException);
        ScreenCapture.logScreenshot("WIDGET FAILED", LogStatus.ERROR);
        throw widgetException;
    }

    private boolean widgetErrorFound() {
        return !driver.findElements(By.xpath("//div[contains(@class,'WidgetFailed')]")).isEmpty();
    }

    public void assertVerify(final String message, final String expected, final String actual) {
        softAssert.assertEquals(message, expected, actual);
    }

}
