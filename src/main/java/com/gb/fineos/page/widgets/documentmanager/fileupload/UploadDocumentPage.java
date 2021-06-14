package com.gb.fineos.page.widgets.documentmanager.fileupload;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UploadDocumentPage extends BasePage {

    public UploadDocumentPage() {
        super("UPLOAD DOCUMENTS PAGE");
    }
    // Choose a Document Type
    @FindBy(xpath = "//input[contains(@id,'uploadpath')]")
    private WebElement browseButton;
    @FindBy(css = "input[type=file]")
    private WebElement fileUploadElement;
    @FindBy(xpath = "//select[contains(@id,'_privacyTag')]")
    private WebElement privacyTag;
    @FindBy(xpath = "//span[contains(@id, 'PageMessage')]")
    private WebElement warningMessage;
    @FindBy(xpath = "//a[contains(@id,'btn_close_popup_msg')]")
    private WebElement closeWarningMessage;
    @FindBy(xpath = "//input[contains(@id,'UploadDocumentWidget_') and contains(@id,'_OKBtn_cloned')]")
    private WebElement okButton;
    @FindBy(xpath = "//input[contains(@id,'_searchPageCancel_cloned')]")
    private WebElement cancelButton;


    public WebElement getOkButton() {
        return okButton;
    }

    public WebElement getCancelButton() {
        return cancelButton;
    }

    public WebElement getPrivacyTag() {
        return privacyTag;
    }

    public WebElement getWarningMessage() {
        return warningMessage;
    }


    public void clickOnBrowseButton(final UploadDocumentPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Browse button ");
        click(browseButton);
    }

    public void uploadDocument(final UploadDocumentPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Uploading A document :" + pageRequest.getDocumentName());
        fileUpload(fileUploadElement, pageRequest.getDocumentName());
    }

    public void clickOnOkButton(final UploadDocumentPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Ok button ");
        click(getOkButton());
    }

    public void selectPrivacyTag(final UploadDocumentPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting  Privacy Tag : " + pageRequest.getPrivacyTag());
        selectValue(pageRequest.getPrivacyTag(), privacyTag);
    }

    public void clickCloseWarningMessage(final UploadDocumentPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Close the error message");
        clickModalBox(closeWarningMessage);
    }

    public static class UploadDocumentPageRequest extends AbstractPageRequest {

        public UploadDocumentPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getDocumentName() {
            return get("DocumentName");
        }

        public String getPrivacyTag() {
            return get("PrivacyTag");
        }
    }
}
