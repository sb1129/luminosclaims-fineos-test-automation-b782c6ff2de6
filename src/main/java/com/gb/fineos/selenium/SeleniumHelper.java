package com.gb.fineos.selenium;

import com.gb.fineos.factory.PropertiesFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.io.File;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public final class SeleniumHelper {
    private static final Logger LOG = Logger.getLogger(SeleniumHelper.class);

    private static final MessageFormat WEB_DRIVER_PROPERTY_MESSAGE_FORMAT = new MessageFormat("Browser_{0}_{1}_Driver");
    private static final String NO_PROPERTY_EXISTS = "No property exists: ";

    private static final String BROWSER = "Browser";
    private static final String BROWSER_MOZILLA = "Firefox";
    private static final String BROWSER_IE = "IE";
    private static final String BROWSER_CHROME = "Chrome";

    private static final String OPERATING_SYSTEM = "Operating_System";
    private static final String WEBDRIVER_GECKO_DRIVER = "webdriver.gecko.driver";
    private static final String WEBDRIVER_IE_DRIVER = "webdriver.ie.driver";
    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";

    private static final String BROWSER_WIDTH = "Browser_Width";
    private static final String BROWSER_HEIGHT = "Browser_Height";
    private static final String DOWNLOAD_LOCATION_PATH = (System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "regression" + File.separator + "downloads");

    private SeleniumHelper() {
        // Do nothing.
    }

    public static String getDownloadLocationPath() {
        return DOWNLOAD_LOCATION_PATH;
    }

    public static WebDriver getWebDriver() {
        WebDriver driver;

        try {
            final String webDriverProperty;
            final String browserDriver;

            switch (getProperty(BROWSER)) {
                case BROWSER_MOZILLA:
                    webDriverProperty = WEB_DRIVER_PROPERTY_MESSAGE_FORMAT.format(new Object[] {getProperty(OPERATING_SYSTEM), BROWSER_MOZILLA});
                    browserDriver = getProperty(webDriverProperty);

                    if (StringUtils.isNotBlank(browserDriver)) {
                        System.setProperty(WEBDRIVER_GECKO_DRIVER, browserDriver);
                    } else {
                        LOG.error(NO_PROPERTY_EXISTS + webDriverProperty);
                        throw new IllegalArgumentException(NO_PROPERTY_EXISTS + webDriverProperty);
                    }

                    final DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                    capabilities.setBrowserName("firefox");
                    capabilities.setVersion("your firefox version");
                    capabilities.setPlatform(Platform.WINDOWS);
                    capabilities.setCapability("marionette", false);
                    driver = new FirefoxDriver(capabilities);
                    break;
                case BROWSER_IE:
                    webDriverProperty = WEB_DRIVER_PROPERTY_MESSAGE_FORMAT.format(new Object[] {getProperty(OPERATING_SYSTEM), BROWSER_IE});
                    browserDriver = getProperty(webDriverProperty);

                    if (StringUtils.isNotBlank(browserDriver)) {
                        System.setProperty(WEBDRIVER_IE_DRIVER, browserDriver);
                    } else {
                        LOG.error(NO_PROPERTY_EXISTS + webDriverProperty);
                        throw new IllegalArgumentException(NO_PROPERTY_EXISTS + webDriverProperty);
                    }

                    driver = new InternetExplorerDriver();
                    break;
                case BROWSER_CHROME:
                    webDriverProperty = WEB_DRIVER_PROPERTY_MESSAGE_FORMAT.format(new Object[] {getProperty(OPERATING_SYSTEM), BROWSER_CHROME});
                    browserDriver = getProperty(webDriverProperty);

                    if (StringUtils.isNotBlank(browserDriver)) {
                        System.setProperty(WEBDRIVER_CHROME_DRIVER, browserDriver);
                    } else {
                        LOG.error(NO_PROPERTY_EXISTS + webDriverProperty);
                        throw new IllegalArgumentException(NO_PROPERTY_EXISTS + webDriverProperty);
                    }

                    final ChromeOptions options = new ChromeOptions();
                    HashMap<String, Object> chromePref = new HashMap<>();
                    options.addArguments("--headless", "--incognito", "--disable-popup-blocking", "--test-type");

                    //to provide download location if any
                    chromePref.put("useAutomationExtension", false);
                    chromePref.put("download.default_directory", DOWNLOAD_LOCATION_PATH);
                    chromePref.put("download.prompt_for_download", false);
                    chromePref.put("profile.default_content_settings.popups", 0);
                    options.setExperimentalOption("prefs", chromePref);

                    driver = new ChromeDriver(options);
                    break;
                default:
                    throw new AssertionError("***UNSUPPORTED BROWSER TYPE***");
            }

            setWindowSize(driver);

            driver.manage().timeouts().implicitlyWait(Long.parseLong(getProperty("Default_WaitTime")), TimeUnit.SECONDS);

            LOG.info("Launching " + driver.manage().window().getSize() + " " + getProperty(BROWSER) + " browser.");

            return driver;
        } catch (Exception e) {
            throw new AssertionError("***EXCEPTION OCCURRED WHILE LAUNCHING THE BROWSER***", e);
        }
    }

    public static ExpectedCondition<String> hasWindowWithUrl(final String url) {
        return webDriver -> {
            String childWindowHandle = null;
            final String parentWindowHandle = Objects.requireNonNull(webDriver).getWindowHandle();

            for (final String handle : webDriver.getWindowHandles()) {
                webDriver.switchTo().window(handle);

                if (webDriver.getCurrentUrl().contains(url)) {
                    childWindowHandle = handle;
                }
            }

            webDriver.switchTo().window(parentWindowHandle);
            return childWindowHandle;
        };
    }

    public static String switchToWindow(final WebDriver webDriver, final String handle) {
        final String parentWindowHandle = webDriver.getWindowHandle(); // Save parent window

        webDriver.switchTo().window(handle);
        SeleniumHelper.setWindowSize(webDriver);
        return parentWindowHandle;
    }

    private static void setWindowSize(final WebDriver webDriver) {
        final Dimension browserSize = new Dimension(Integer.parseInt(getProperty(BROWSER_WIDTH)), Integer.parseInt(getProperty(BROWSER_HEIGHT)));
        LOG.info("Setting dimensions of browser to: " + browserSize);

        webDriver.manage().window().setSize(browserSize);
    }

    private static String getProperty(final String key) {
        return PropertiesFactory.getInstance().getProperties().getProperty(key);
    }
}
