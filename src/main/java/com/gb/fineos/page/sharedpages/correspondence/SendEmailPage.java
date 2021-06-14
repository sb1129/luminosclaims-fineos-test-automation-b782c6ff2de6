package com.gb.fineos.page.sharedpages.correspondence;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.extent.ExtentTestManager;
import com.gb.fineos.factory.PageFactory;
import com.gb.fineos.page.BasePage;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.gb.fineos.screencapture.ScreenCapture.logScreenshot;
import static com.gb.fineos.selenium.SeleniumHelper.hasWindowWithUrl;
import static com.gb.fineos.selenium.SeleniumHelper.switchToWindow;

public class SendEmailPage extends BasePage {

    private static final int WEB_DRIVER_WAIT_TIME_OUT_IN_SECONDS = 120;

    public SendEmailPage() {
        super("SEND EMAIL");
    }

    public static SendEmailPage switchTo(final SendEmailPageRequest pageRequest) {
        final WebDriver webDriver = ExtentTestManager.getWebDriver();
        final WebDriverWait wait = new WebDriverWait(webDriver, WEB_DRIVER_WAIT_TIME_OUT_IN_SECONDS);
        pageRequest.parentWindowHandle = switchToWindow(webDriver, wait.until(hasWindowWithUrl("gbsendemailpage.jsp")));

        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("draftEditorWindow")));
        wait.until(ExpectedConditions.attributeContains(By.xpath("//div[contains(@class, 'th-ui-loading-mask')]"), "class", "hide"));
        logScreenshot("SEND EMAIL", LogStatus.INFO);

        return PageFactory.initElements(SendEmailPage.class);
    }

    public void close(final SendEmailPageRequest pageRequest) {
        getDriver().close(); //closing child window
        getDriver().switchTo().window(pageRequest.parentWindowHandle);
    }

    public static class SendEmailPageRequest extends AbstractPageRequest {
        private String parentWindowHandle;

        public SendEmailPageRequest(final TestCaseContext context) {
            super(context);
        }
    }
}
