package com.gb.fineos.page;

import com.gb.fineos.domain.PageRequest;
import com.gb.fineos.extent.ExtentTestManager;
import com.gb.fineos.factory.PropertiesFactory;
import com.gb.fineos.screencapture.ScreenCapture;
import com.gb.fineos.selenium.SeleniumHelper;
import com.gb.fineos.validation.AssertionHelper;
import com.google.common.base.Function;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.fineos.ta.util.StringUtils.isNullOrEmptyTrimmedString;
import static java.util.Arrays.stream;

public abstract class BasePage {
    private static final Logger LOG = Logger.getLogger(BasePage.class);
    private static final long MAX_WAIT_TIME_SECONDS = 10L;
    private static final int FINEOS_HEADER_SCROLL_BUFFER = 150;
    private static final long WAIT_TIME = 300;

    private final String pageName;
    private final Properties config;
    private final WebDriver driver;
    private final AssertionHelper assertionHelper;

    public BasePage(final String pageName) {
        this.pageName = pageName;
        this.config = PropertiesFactory.getInstance().getProperties();
        this.assertionHelper = new AssertionHelper();
        this.driver = ExtentTestManager.getWebDriver();
    }

    public String getPageName() {
        return pageName;
    }

    protected WebDriver getDriver() {
        return driver;
    }

    protected String getProperty(final String key) {
        return config.getProperty(key);
    }

    public AssertionHelper getAssertionHelper() {
        return assertionHelper;
    }

    public Optional<WebElement> getIFrameDisableLayer() {
        return getDriver().findElements(By.id("disablingLayerIframe")).stream().findFirst();
    }

    protected void clearInput(final WebElement webElement) {
        try {
            webElement.clear();
        } catch (NoSuchElementException e) {
            assertionHelper.handleNoSuchElementExceptionAndThrow(webElement, e);
        }
    }

    protected void clearAndInput(final String value, final WebElement webElement, final WebElement... elementToBeInvisible) {
        try {
            if (StringUtils.isNotBlank(value)) {
                highlightWebElement(webElement);
                webElement.clear();
                webElement.sendKeys(value);
            }
        } catch (NoSuchElementException e) {
            assertionHelper.handleNoSuchElementExceptionAndThrow(webElement, e);
        }
        waitForWebElementsToBeInvisible(elementToBeInvisible);
    }

    protected void input(final String value, final WebElement webElement, final WebElement... elementToBeInvisible) {
        try {
            if (StringUtils.isNotBlank(value)) {
                highlightWebElement(webElement);
                webElement.sendKeys(value);
            }
        } catch (NoSuchElementException e) {
            assertionHelper.handleNoSuchElementExceptionAndThrow(webElement, e);
        }
        waitForWebElementsToBeInvisible(elementToBeInvisible);
    }

    protected void inputDate(final WebElement webElement) {
        try {
            webElement.sendKeys(getDate());
        } catch (NoSuchElementException e) {
            assertionHelper.handleNoSuchElementExceptionAndThrow(webElement, e);
        }
    }

    protected String getDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHMM");
        return dateFormat.format(date);
    }

    protected String getDayAndTime() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("ddHHmmss");
        return dateFormat.format(date);
    }

    protected String getClaimFormatDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return dateFormat.format(date);
    }

    /**
     * Clicks on a non-modal screen element.
     *
     * @see #clickModalBox(WebElement, WebElement...)
     */
    protected void click(final WebElement webElement, final WebElement... webElementsToBeInvisible) {
        final WebElement[] elements = getIFrameDisableLayer()
            .filter(layer -> !ArrayUtils.contains(webElementsToBeInvisible, layer))
            .map(layer -> Stream.concat(stream(webElementsToBeInvisible), Stream.of(layer)).toArray(WebElement[]::new))
            .orElse(webElementsToBeInvisible);

        try {
            clickActions(webElement, elements);
        } catch (NoSuchElementException e) {
            assertionHelper.handleNoSuchElementExceptionAndThrow(webElement, e);
        } catch (StaleElementReferenceException e) {
            clickActions(webElement, elements);
        } catch (WebDriverException e) {
            ExtentTestManager.getTest().log(LogStatus.INFO, "SCROLLING PAGE", "Couldn't click the web element. Attempting to scroll.");
            scrollIntoView(webElement);
            clickActions(webElement, elements);
        }
    }

    /**
     * @see #click(WebElement, WebElement...)
     */
    protected void clickModalBox(final WebElement webElement, final WebElement... webElementsToBeInvisible) {
        try {
            clickActions(webElement, webElementsToBeInvisible);
        } catch (NoSuchElementException e) {
            assertionHelper.handleNoSuchElementExceptionAndThrow(webElement, e);
        } catch (WebDriverException e) {
            ExtentTestManager.getTest().log(LogStatus.INFO, "SCROLLING PAGE", "Couldn't click the web element. Attempting to scroll.");
            scrollIntoView(webElement);
            clickActions(webElement, webElementsToBeInvisible);
        }
    }

    protected void click(final By xpath, final WebElement... elementToBeInvisible) {
        final WebDriverWait wait = new WebDriverWait(getDriver(), Long.parseLong(getProperty("Default_WaitTime")));
        click(wait.until(ExpectedConditions.elementToBeClickable(xpath)), elementToBeInvisible);
    }

    protected void clickTab(final PageRequest pageRequest, final WebElement tab, final String tabName) {
        if (getWebElementAttribute(tab, "id", "class").equals("TabOn")) {
            pageRequest.log(getPageName(), tabName + " is already selected");
        } else {
            pageRequest.log(getPageName(), "Clicking on the " + tabName + " tab");
            click(tab);
        }
    }

    public void scrollIntoView(final WebElement webElement) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) getDriver();
            js.executeScript("window.scrollTo(0," + (webElement.getLocation().y - FINEOS_HEADER_SCROLL_BUFFER) + ")");
        } catch (NoSuchElementException e) {
            assertionHelper.handleNoSuchElementExceptionAndThrow(webElement, e);
        }
    }

    private void clickActions(final WebElement webElement, final WebElement... elementToBeInvisible) {
        highlightWebElement(webElement);

        final WebDriverWait wait = new WebDriverWait(getDriver(), 20);
        wait.until(ExpectedConditions.elementToBeClickable(webElement)).click();

        waitForWebElementsToBeInvisible(elementToBeInvisible);
    }

    protected void waitForElementPresent(final WebElement webElement) {
        final WebDriverWait wait = new WebDriverWait(getDriver(), 100);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitForElementClickable(final By xpath) {
        final WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
    }

    protected void waitForElementClickable(final WebElement webElement) {
        final WebDriverWait wait = new WebDriverWait(getDriver(), 30);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected void waitForTextToAppear(final WebElement webElement, final String text) {
        final WebDriverWait wait = new WebDriverWait(getDriver(), 60);
        wait.until(ExpectedConditions.textToBePresentInElement(webElement, text));
    }

    protected void highlightWebElement(final WebElement webElement) {
        if (getProperty("isHighlight").equalsIgnoreCase(String.valueOf(true))) {
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, "border: 4px solid yellow;");
            ((JavascriptExecutor) getDriver()).executeScript("arguments[0].setAttribute('style', arguments[1]);", webElement, "");
        }
    }

    protected void scrollPageVertically(final Integer scrollTo) {
        ((JavascriptExecutor) getDriver()).executeScript("window.scrollTo(" + scrollTo.toString() + ", -document.body.scrollHeight);");
    }

    protected void waitForWebElementsToBeInvisible(final WebElement... webElement) {
        List<WebElement> webElementAsArray = new ArrayList<>(Arrays.asList(webElement));
        if (!webElementAsArray.isEmpty()) {
            WebDriverWait wait = new WebDriverWait(getDriver(), MAX_WAIT_TIME_SECONDS);
            wait.until(ExpectedConditions.invisibilityOfAllElements(webElementAsArray));
        }
    }

    protected void selectValue(final String value, final WebElement webElement, final WebElement... elementToBeVisible) {
        try {
            waitForWebElementsToBeInvisible(elementToBeVisible);
            highlightWebElement(webElement);
            Select select = new Select(webElement);
            select.selectByVisibleText(value);
            webElement.sendKeys(Keys.TAB);
        } catch (StaleElementReferenceException e) {
            clickActions(webElement, elementToBeVisible);
        } catch (NoSuchElementException e) {
            assertionHelper.handleNoSuchElementExceptionAndThrow(webElement, e);
        }
    }

    protected void selectByIndex(final String index, final WebElement webElement) {
        try {
            highlightWebElement(webElement);
            scrollIntoView(webElement);
            Select select = new Select(webElement);
            select.selectByIndex(Integer.parseInt(index));
            webElement.sendKeys(Keys.TAB);
        } catch (NoSuchElementException e) {
            assertionHelper.handleNoSuchElementExceptionAndThrow(webElement, e);
        }
    }

    protected void clickTextFromList(final List<WebElement> items, final String value) {
        for (WebElement item : items) {
            if (item.getText().contains(value)) {
                item.click();
                break;
            }
        }
    }

    protected boolean isEditable(final WebElement webElement) {
        try {
            return webElement.isEnabled();
        } catch (NoSuchElementException e) {
            assertionHelper.handleNoSuchElementExceptionAndThrow(webElement, e);
        }

        return false;
    }

    protected String getText(final WebElement webElement) {
        try {
            return webElement.getText();
        } catch (NoSuchElementException e) {
            assertionHelper.handleNoSuchElementExceptionAndThrow(webElement, e);
        }
        return null;
    }

    protected String getCssValue(final WebElement webElement, final String value) {
        try {
            return webElement.getCssValue(value);
        } catch (NoSuchElementException e) {
            assertionHelper.handleNoSuchElementExceptionAndThrow(webElement, e);
        }
        return null;
    }

    public boolean isElementPresent(final WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            LOG.error(e);
        }
        return false;
    }

    public boolean isElementFound(final WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            LOG.warn("WARNING: ELEMENT NOT FOUND");
            //To verify that the element does not exist. No need to throw exception, just a message is enough.
        }
        return false;
    }

    public void switchToIframePopup(final String iFrameId) {
        getDriver().switchTo().frame(getDriver().findElement(By.id(iFrameId)));
    }

    public void switchWindow() {
        String initialWindow = getDriver().getWindowHandle();
        Set<String> handles = getDriver().getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(initialWindow)) {
                getDriver().switchTo().window(windowHandle);
            }
        }
    }

    public void closeTheAddressWindow() {
        String parentWindow = getDriver().getWindowHandle();
        Set<String> handles = getDriver().getWindowHandles();
        for (String windowHandle : handles) {
            if (!windowHandle.equals(parentWindow)) {
                getDriver().switchTo().window(windowHandle);

                getDriver().close(); //closing child window
                getDriver().switchTo().window(parentWindow); //ctrl to parent window
            }
        }
    }

    protected boolean webElementExists(final WebElement webElement) {
        try {
            return !getDriver().findElements(By.id(webElement.getAttribute("id"))).isEmpty();
        } catch (NoSuchElementException e) {
            assertionHelper.handleNoSuchElementExceptionAndThrow(webElement, e);
        }
        return false;
    }

    protected boolean webElementExistsSilently(final WebElement webElement) {
        try {
            return !getDriver().findElements(By.id(webElement.getAttribute("id"))).isEmpty();
        } catch (NoSuchElementException e) {
            // silent means don't throw
        }
        return false;
    }

    protected boolean webElementIsDisplayed(final WebElement webElement) {
        try {
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            assertionHelper.handleNoSuchElementException(webElement);
        }
        return false;
    }

    protected String getWebElementAttribute(final WebElement webElement, final String byAttribute, final String getAttribute) {
        try {
            return getDriver().findElement(By.id(webElement.getAttribute(byAttribute))).getAttribute(getAttribute);
        } catch (NoSuchElementException e) {
            assertionHelper.handleNoSuchElementExceptionAndThrow(webElement, e);
        }
        return null;
    }

    protected void check(final WebElement webElement) {
        if (StringUtils.equalsIgnoreCase("checkbox", webElement.getAttribute("type")) && !webElement.isSelected()) {
            click(webElement);
        }
    }

    protected void uncheck(final WebElement webElement) {
        if (StringUtils.equalsIgnoreCase("checkbox", webElement.getAttribute("type")) && webElement.isSelected()) {
            click(webElement);
        }
    }

    protected void selectTableValues(final String widgetName, final String tableXPath, final List<String> values) {
        final List<WebElement> trs = getDriver().findElements(
            By.xpath("//table[starts-with(@id, '" + widgetName + "') and contains(@id, '" + tableXPath + "')]/tbody/tr"));

        trs.stream()
            .filter(tr -> tr.findElements(By.xpath(".//td")).stream().anyMatch(td -> values.contains(td.getText())))
            .forEach(tr -> check(tr.findElement(By.xpath(".//input[@type='checkbox' and starts-with(@id, '" + widgetName + "') and contains(@id, '_Checkbox_RowId_')]"))));
    }

    protected void clickRowInTable(final PageRequest pageRequest, final List<WebElement> tableRows, final String rowTitle) {
        for (WebElement row : tableRows) {
            if (((RemoteWebElement) row).findElementByTagName("td").getAttribute("title").equalsIgnoreCase(rowTitle)) {
                if (row.getAttribute("class").equalsIgnoreCase("ListRowSelected")) {
                    pageRequest.log(getPageName(), rowTitle + " is already the selected row");
                } else {
                    try {
                        pageRequest.log(getPageName(), "Clicking " + rowTitle);
                        click(row);
                        break;
                    } catch (NoSuchElementException e) {
                        assertionHelper.handleNoSuchElementExceptionAndThrow(row, e);
                    }
                }
            }
        }
    }

    protected WebElement getElement(final String xpath) {
        try {
            return getDriver().findElement(By.xpath(xpath));
        } catch (NoSuchElementException e) {
            LOG.error(e);
            throw new NoSuchElementException("Cannot find element with xpath: " + xpath);
        }
    }

    protected void selectTaskFromTaskTable(final PageRequest pageRequest, final String tableXPath, final String rowTitle) {
        getDriver().findElements(By.xpath(tableXPath + "/tbody/tr/td[.='" + rowTitle + "']"))
            .stream()
            .findFirst()
            .ifPresent(cell -> {
                pageRequest.log(getPageName(), "Selecting on row of task : " + rowTitle);
                click(cell.findElement(By.xpath("./..")));
            });
    }

    protected void fileUpload(final WebElement webElement, final String fileName) {
        try {
            File myFile = new File(System.getProperty("user.dir") + getProperty("documentsLocation") + fileName);
            webElement.sendKeys(myFile.getAbsolutePath());
        } catch (final Exception e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }
    }

    public boolean fileDownloaded(final String fileName, final PageRequest pageRequest) {
        try {
            File file = new File(SeleniumHelper.getDownloadLocationPath(), fileName);
            WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
            wait.until((Function<? super WebDriver, Boolean>) (driver) -> file.exists());
            if (file.exists()) {
                // File has been found, it can now be deleted:
                pageRequest.log(getPageName(), "File has been found");
                return true;
            }
            return false;
        } catch (final Exception e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }
    }

    public void deleteDownloadedFiles(final String fileName, final PageRequest pageRequest) {
        try {
            File file = new File(SeleniumHelper.getDownloadLocationPath(), fileName);
            WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
            wait.until((Function<? super WebDriver, Boolean>) (driver) -> file.exists());
            if (file.exists()) {
                pageRequest.log(getPageName(), "Downloaded files have been deleted");
                file.delete();
            }
        } catch (final Exception e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }
    }

    public boolean isFileDownloadedExt(final PageRequest pageRequest, final String ext) {
        try {
            boolean flag = false;
            File dir = new File(SeleniumHelper.getDownloadLocationPath());
            WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
            wait.until((Function<? super WebDriver, Boolean>) (driver) -> dir.exists());

            File[] dirContents = dir.listFiles();

            for (int i = 0; i <= dirContents.length; i++) {
                if (dirContents[i].getName().contains(ext)) {
                    flag = true;
                    pageRequest.log(getPageName(), "Downloaded file Extension matched");
                }
            }
            return flag;
        } catch (final Exception e) {
            LOG.error(e);
            throw new RuntimeException(e);
        }
    }

    public void waitForFileDownload(final String fileName, final PageRequest pageRequest) {
        File targetFile = new File(SeleniumHelper.getDownloadLocationPath(), fileName);
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME);
        wait.until((Function<? super WebDriver, Boolean>) (driver) -> targetFile.exists());
        if (!targetFile.exists()) {
            pageRequest.log(getPageName(), "File is not available");
        } else {
            pageRequest.log(getPageName(), "File is available");
        }

    }

    public boolean isValidationMessageExists(final PageRequest pageRequest, final WebElement validationMessage) {
        pageRequest.log(getPageName(), "Check if the error message exists");
        try {
            validationMessage.isDisplayed();
            pageRequest.log(getPageName(), "error message is displayed ");
            ScreenCapture.logScreenshot(getPageName(), LogStatus.INFO);
            return true;

        } catch (NoSuchElementException e) {
            pageRequest.log(getPageName(), "error message is not displayed ");
            return false;
        }
    }

    public String getDateWithoutTime() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }

    public String getTextOfDefaultSelectedItem(final WebElement webElement) {
        try {
            Select item = new Select(webElement);
            String selectedValue = item.getFirstSelectedOption().getText();
            return selectedValue;

        } catch (NoSuchElementException e) {
            assertionHelper.handleNoSuchElementExceptionAndThrow(webElement, e);
        }
        return null;

    }

    public List<String> getValidationMessages(final PageRequest pageRequest, final String errorValidationText) {
        pageRequest.log(getPageName(), "Getting validation messages displayed on screen");
        try {
            if (!isNullOrEmptyTrimmedString(errorValidationText)) {
                List<String> errorMessages = new LinkedList<>(Arrays.asList(errorValidationText.split("\\r?\\n")));
                if (!errorMessages.isEmpty()) {
                    pageRequest.log(getPageName(), "Validation messages are: " + ArrayUtils.toString(errorMessages));
                } else {
                    pageRequest.log(getPageName(), "There are no validation messages displayed");
                }
                return errorMessages;
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            pageRequest.log(getPageName(), "There was an issue when attempting to retrieve the validation messages displayed");
            throw e;
        }
    }

    public String getErrorValidationText(final PageRequest pageRequest) {
        try {
            List<WebElement> messages = driver.findElements(By.xpath("//td[@class='raiseMessageText']/ul/li/span"));
            if (!messages.isEmpty()) {
                return messages.stream().map(WebElement::getText).collect(Collectors.joining("\n"));
            } else {
                pageRequest.getContext().log("MESSAGE VALIDATION", "There were no messages displayed on the screen");
                return StringUtils.EMPTY;
            }
        } catch (Exception e) {
            pageRequest.getContext().warning(getPageName(), "There was an error when attempting to retrieve the error validations displayed. Were any displayed on the screen?", true);
            pageRequest.getContext().warning("ERROR", e.getMessage(), false);
            return com.fineos.ta.util.StringUtils.EMPTY_STRING;
        }
    }

    public List<String> getLabelsDisplayedFromTheScreen(final PageRequest pageRequest, final String errorValidationText) {
        pageRequest.log(getPageName(), "Getting Labels displayed on screen");
        try {
            if (!isNullOrEmptyTrimmedString(errorValidationText)) {
                List<String> labels = new LinkedList<>(Arrays.asList(errorValidationText.split("\\r?\\n")));
                if (!labels.isEmpty()) {
                    pageRequest.log(getPageName(), "Labels  are: " + ArrayUtils.toString(labels));
                } else {
                    pageRequest.log(getPageName(), "There are no labels displayed");
                }
                return labels;
            } else {
                return Collections.emptyList();
            }
        } catch (Exception e) {
            pageRequest.log(getPageName(), "There was an issue when attempting to retrieve the labels displayed");
            throw e;
        }
    }


    public String getLabels(final PageRequest pageRequest, final String screenName) {
        try {
            List<WebElement> textLabels = null;
            if (screenName.equals("PolicySearch")) {
                textLabels = driver.findElements(By.xpath("//label[contains(@class,'TextLabel')]"));
            } else {
                textLabels = driver.findElements(By.xpath("//label[@class='TextLabel']"));
            }
            if (!textLabels.isEmpty()) {
                return textLabels.stream().map(WebElement::getText).collect(Collectors.joining("\n"));
            } else {
                pageRequest.getContext().log("MESSAGE VALIDATION", "There were no labels displayed on the " + screenName + "  screen");
                return StringUtils.EMPTY;
            }
        } catch (Exception e) {
            pageRequest.getContext().warning(getPageName(), "There was an error when attempting to retrieve the labels displayed. Were any displayed on the " + screenName + "   screen?", true);
            pageRequest.getContext().warning("ERROR", e.getMessage(), false);
            return com.fineos.ta.util.StringUtils.EMPTY_STRING;
        }
    }
}
