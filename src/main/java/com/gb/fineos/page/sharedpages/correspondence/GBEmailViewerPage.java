package com.gb.fineos.page.sharedpages.correspondence;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.extent.ExtentTestManager;
import com.gb.fineos.factory.PageFactory;
import com.gb.fineos.page.BasePage;
import com.relevantcodes.extentreports.LogStatus;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

import static com.gb.fineos.screencapture.ScreenCapture.logScreenshot;
import static com.gb.fineos.selenium.SeleniumHelper.hasWindowWithUrl;
import static com.gb.fineos.selenium.SeleniumHelper.switchToWindow;
import static junit.framework.Assert.fail;


public class GBEmailViewerPage extends BasePage {
    private static final int WEB_DRIVER_WAIT_TIME_OUT_IN_SECONDS = 120;
    private static final int ATTACHMENTS_COLUMN_COUNT = 2;
    private static final int ATTACHMENTS_ROW_INDEX = 3;
    private static final String INLINE_ATTACHMENT_PREFIX = "(Inline)";
    private static final String UPLOAD_SUCCESS_MESSAGE = "Attachment(s) successfully uploaded.";
    private static final int RETRY_LIMIT = 3;
    private static final int DOCUMENT_DEFAULT_COUNT = 1;
    private static final String UPLOAD_FAILURE_MESSAGE = "has failed to upload, please attempt the upload again.";

    @FindBy(xpath = "//input[contains(@value,'Upload')]")
    private WebElement uploadButton;
    @FindBy(xpath = "//input[contains(@id,'viewSubmit')]")
    private WebElement viewButton;
    @FindBy(xpath = "//input[contains(@type,'checkbox') and contains(@id,'ATTACHMENT_')]")
    private List<WebElement> allAttachments;
    @FindBy(xpath = "//li[contains(@id,'viewMessage')]")
    private WebElement viewMessage;
    @FindBy(xpath = "//input[contains(@type,'checkbox')][1]")
    private WebElement selectAll;
    @FindBy(xpath = "//div[@class=\"container\"]")
    private WebElement tableData;
    @FindBy(xpath = "//input[contains(@type,'submit')]")
    private List<WebElement> buttons;
    @FindBy(xpath = "//div[contains(@class,'messages')]/ul/li")
    private List<WebElement> uploadConfirmMessage;
    @FindBy(xpath = "//div[contains(@class,'container')]//table")
    private WebElement attachmentsTable;
    @FindBy(xpath = "//li[contains(@id,'uploadClicked')]")
    private WebElement uploadingMessage;

    public GBEmailViewerPage() {
        super("GB EMAIL VIEWER");
    }

    public WebElement getUploadButton() {
        return uploadButton;
    }

    public WebElement getViewButton() {
        return viewButton;
    }

    public WebElement getViewMessage() {
        return viewMessage;
    }

    public void clickViewButton(final GBEmailViewerPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking view document");
        pageRequest.log(getPageName(), "Clicking on view button");
        click(viewButton);
    }

    public void clickUploadButton(final GBEmailViewerPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Upload button");
        click(uploadButton);
    }

    public void selectMoreThanOneAttachments(final GBEmailViewerPageRequest pageRequest, final int requiredAttachments) {
        int countOfAllAttachments = allAttachments.size();
        if (requiredAttachments <= countOfAllAttachments) {
            for (int i = 0; i < requiredAttachments; i++) {
                clickModalBox(allAttachments.get(i));
            }
        } else {
            pageRequest.log(getPageName(), "The number of attachments is less");
        }
    }

    public void verifyOrderOfAttachments(final GBEmailViewerPageRequest pageRequest) {
        String[] fileNamesList = pageRequest.getAttachedFileNames().split(";");

        final List<String> addedFilesList =
                getDriver().findElements(By.xpath("//div[contains(@class,'container')]/table/tbody/tr/td"))
                .stream().map(WebElement::getText).collect(Collectors.toList());

        for (int i = 1; i < fileNamesList.length; i++) {
            if (addedFilesList.get(i).contains(INLINE_ATTACHMENT_PREFIX)) {
                pageRequest.log(getPageName(), "The Regular and Inline attachments are not in Order..");
                fail("The Regular and Inline attachments are not in Order..");
            }
        }
        pageRequest.log(getPageName(), "The Regular and Inline attachments are in Order..");
        logScreenshot("Verify the attachments", LogStatus.INFO);
    }

    public void selectOneAttachment(final GBEmailViewerPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting only one attachment");
        for (WebElement attachment : allAttachments) {
            if (!attachment.isSelected()) {
                clickModalBox(attachment);
                break;
            }
        }
    }

    public void isAttachmentSelected(final GBEmailViewerPageRequest pageRequest) {
        if (getCountOfSelectedAttachments(pageRequest) == 1) {
            pageRequest.log(getPageName(), "Only one attachment is Selected");
        } else {
            Assert.assertFalse("More than one Attachment is selected", false);
        }
    }

    public void isMultipleSelected(final GBEmailViewerPageRequest pageRequest) {
        if (getCountOfSelectedAttachments(pageRequest) == Integer.parseInt(pageRequest.getRequiredDocumentsCount())) {
            pageRequest.log(getPageName(), "Multiple attachments selected");
        } else {
            Assert.assertFalse("Only one attachment is Selected", false);
        }
    }

    public int getCountOfSelectedAttachments(final GBEmailViewerPageRequest pageRequest) {
        int selectedAttachments = 0;
        for (WebElement attachment : allAttachments) {
            if (attachment.isSelected()) {
                selectedAttachments++;
                pageRequest.log(getPageName(), "Selected Attachment is : " + getText(attachment));
            } else {
                break;
            }
        }
        return selectedAttachments;
    }

    public static GBEmailViewerPage switchTo(final GBEmailViewerPageRequest pageRequest) {
        final WebDriver webDriver = ExtentTestManager.getWebDriver();
        final WebDriverWait wait = new WebDriverWait(webDriver, WEB_DRIVER_WAIT_TIME_OUT_IN_SECONDS);
        pageRequest.parentWindowHandle = switchToWindow(webDriver, wait.until(hasWindowWithUrl("gbemailviewer.jsp")));

        logScreenshot("GB EMail Viewer", LogStatus.INFO);

        return PageFactory.initElements(GBEmailViewerPage.class);
    }

    public void assertViewButtonIsDisabled(final GBEmailViewerPageRequest pageRequest) {
        Assert.assertFalse("The view button is enabled ", viewButton.isEnabled());
        pageRequest.log(getPageName(), "view button is disabled");
    }

    public void assertViewButtonIsEnabled(final GBEmailViewerPageRequest pageRequest) {
        Assert.assertTrue("The view button is disabled", viewButton.isEnabled());
        pageRequest.log(getPageName(), "view button is enabled");
    }

    public boolean isUploadButtonEnabled(final GBEmailViewerPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Upload button is Status...");
        return uploadButton.isEnabled();
    }

    public void selectMultipleAttachments(final GBEmailViewerPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting multiple documents");
        selectMoreThanOneAttachments(pageRequest, Integer.parseInt(pageRequest.getRequiredDocumentsCount()));
        logScreenshot("multiple documents selection", LogStatus.INFO);
    }

    public void selectMultipleAttachments(final GBEmailViewerPageRequest pageRequest, final int count) {
        pageRequest.log(getPageName(), "Selecting multiple default documents");
        selectMoreThanOneAttachments(pageRequest, count);
    }

    public void selectAllAttachments(final GBEmailViewerPageRequest pageRequest) {
        pageRequest.log(getPageName(), "clicking SelectAll checkboxes");
        clickModalBox(selectAll);
        logScreenshot("select all checkboxes clicked", LogStatus.INFO);
    }

    public void assertMultipleAttachmentsSelectedToViewMessage(final GBEmailViewerPageRequest pageRequest) {
        getAssertionHelper().assertIsDisplayed("message after selecting more than one attachment to view", viewMessage);
        pageRequest.log(getPageName(), "The message is displayed as Please note that files may only be viewed one at a time.");
    }

    public String getUploadingMessage(final GBEmailViewerPageRequest pageRequest) {
        String uploadMessage = getCssValue(uploadingMessage, "innerText").trim();
        waitForTextToAppear(uploadingMessage, uploadMessage);
        return uploadMessage;
    }

    public String getUploadMessage(final GBEmailViewerPageRequest pageRequest) {
        String uploadMessage = getText(uploadConfirmMessage.get(0)).trim();
        return uploadMessage;
    }

    public void deleteDownloadedFiles(final GBEmailViewerPageRequest pageRequest) {
        pageRequest.log(getPageName(), "delete all downloaded files");
        deleteDownloadedFiles(pageRequest.getFileName(), pageRequest);
    }

    public void close(final GBEmailViewerPageRequest pageRequest) {
        getDriver().close();
        getDriver().switchTo().window(pageRequest.parentWindowHandle);
    }

    public boolean isScrollBarDisplayed(final GBEmailViewerPageRequest pageRequest) {

        if (getCssValue(tableData, "overflow-y").equals("scroll")) {
            pageRequest.log(getPageName(), "Scroll Bar is Exists");
            return true;
        }
        pageRequest.log(getPageName(), "Scroll Bar does not Exist");
        return false;
    }

    public boolean uploadButtonIsDisplayed(final GBEmailViewerPageRequest pageRequest) {
        for (WebElement button : buttons) {
            if (button.getText().equals("Upload")) {
                pageRequest.log(getPageName(), "Upload Button Exists");
                return true;
            }
        }
        pageRequest.log(getPageName(), "Upload Button does not Exist");
        return false;
    }

    public int getFirstColumnRowsCount(final GBEmailViewerPageRequest pageRequest) {
        final List<WebElement> elements = getDriver().findElements(By.xpath("//div[contains(@class,'container')]/table/tbody/tr"));
        pageRequest.log(getPageName(), "Attachments table rows count : " + (elements.size() - ATTACHMENTS_COLUMN_COUNT));
        return elements.size() - ATTACHMENTS_COLUMN_COUNT;
    }

    public int getColumnCount(final GBEmailViewerPageRequest pageRequest) {
        List<WebElement> elements = getDriver().findElements(By.xpath("//div[contains(@class,'container')]/table/tbody/tr[" + ATTACHMENTS_ROW_INDEX + "]"));
        pageRequest.log(getPageName(), "Attachments table columns count : " + elements.size());
        return elements.size();
    }

    public void clearAttachmentSelection(final GBEmailViewerPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clearing the attachments selection");
        for (WebElement attachment : allAttachments) {
            if (attachment.isSelected()) {
                clickModalBox(selectAll);
            }
        }
    }

    public void waitForUploadProcessToFinish(final GBEmailViewerPageRequest pageRequest) {
        String uploadConfirmationMessage = getText(uploadConfirmMessage.get(0)).trim();
        pageRequest.log(getPageName(), "Waiting for upload process to Finish");
        waitForTextToAppear(uploadConfirmMessage.get(0), uploadConfirmationMessage);
        int retry = 0;
        boolean success = false;
        while (!success && retry < RETRY_LIMIT) {
            if (getUploadMessage(pageRequest).contains(UPLOAD_FAILURE_MESSAGE)) {
                selectOneAttachment(pageRequest);
                clickUploadButton(pageRequest);
                waitForTextToAppear(uploadConfirmMessage.get(0), uploadConfirmationMessage);
            } else {
                success = true;
                break;
            }
            retry++;
        }
        if (success) {
            pageRequest.log(getPageName(), "Attachments uploaded");
        } else {
            pageRequest.log(getPageName(), "Attachments upload failed");
        }
    }

    public static class GBEmailViewerPageRequest extends AbstractPageRequest {
        private String parentWindowHandle;

        public GBEmailViewerPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getFileName() {
            return get("fileName");
        }

        public String getAttachedFileNames() {
            return get("Attached files");
        }

        public String getRequiredDocumentsCount() {
            return get("Count");
        }

        public String getUploadingMessage() {
            return get("UploadingMessage");
        }

        public String getConfirmationMessage() {
            return get("ConfirmationMessage");
        }
    }
}

