package com.gb.fineos.page.sharedpages.documentmanager;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DocumentPropertiesPage extends BasePage {
    @FindBy(xpath = "//input[contains(@id, '_archiveButton')]")
    private WebElement deleteDocument;
    @FindBy(xpath = "//a[contains(@id, '_linkedDocumentLinkBean')]")
    private WebElement linkedDocument;
    @FindBy(xpath = "//input[contains(@id, '_Confirm_Archive_yes')]")
    private WebElement yesButton;

    @FindBy(xpath = "//a[contains(@id, 'DocumentPropertiesWidget_') and contains(@id,'_clientLinkBean')]")
    private WebElement clientLinkBeanParty;
    @FindBy(xpath = "//a[contains(@id, 'DocumentPropertiesWidget_') and contains(@id, '_caseLinkBean')]")
    private WebElement caseLinkBeanCase;
    @FindBy(xpath = "//input[contains(@id, '_editPageCancel_cloned')]")
    private WebElement cancelButton;

    //Document properties
    @FindBy(xpath = "//span[contains(@id,'DocumentPropertiesWidget_') and contains(@id,'_documentType')]")
    private WebElement documentType;
    @FindBy(xpath = "//a[contains(@id, '_clientLinkBean')]")
    private WebElement party;
    @FindBy(xpath = "//a[contains(@id, '_caseLinkBean')]")
    private WebElement caseNum;
    @FindBy(xpath = "//a[contains(@id,'DocumentPropertiesWidget_') and contains(@id,'_linkedDocumentLinkBean')]")
    private WebElement linkedDoc;
    @FindBy(xpath = "//span/select[contains(@id,'DocumentPropertiesWidget_') and contains(@id,'_privacyTagEnumDropDownBean')]")
    private WebElement privacyTag;
    @FindBy(xpath = "//select[contains(@id,'DocumentPropertiesWidget_') and contains(@id,'_status')]")
    private WebElement status;
    @FindBy(xpath = "//input[contains(@id,'DocumentPropertiesWidget_') and contains(@id,'_title')]")
    private WebElement title;
    @FindBy(xpath = "//span[contains(@id,'DocumentPropertiesWidget_') and contains(@id,'_createdBy')]")
    private WebElement creator;
    @FindBy(xpath = "//span[contains(@id,'DocumentPropertiesWidget_') and contains(@id,'_lastUpdatedBy')]")
    private WebElement lastUpdatedBy;
    @FindBy(xpath = "//span[contains(@id,'DocumentPropertiesWidget_') and contains(@id,'_creationDate')]")
    private WebElement creationDate;
    @FindBy(xpath = "//span[contains(@id,'DocumentPropertiesWidget_') and contains(@id,'_lastUpdateDate')]")
    private WebElement lastUpdatedDate;
    @FindBy(xpath = "//input[contains(@id,'_editPageCancel_cloned')]")
    private WebElement pageCancel;
    @FindBy(xpath = "//input[contains(@id,'_editPageSave_') and contains(@class,'Button')]")
    private WebElement okButton;

    public WebElement getClientLinkBeanParty() {
        return clientLinkBeanParty;
    }

    public WebElement getCaseLinkBeanCase() {
        return caseLinkBeanCase;
    }

    public DocumentPropertiesPage() {
        super("DOCUMENT PROPERTIES PAGE");
    }

    public void deleteAndConfirmDocument(final DocumentPropertiesPageRequest pageRequest) {
        clickDeleteBtn(pageRequest);
        clickYesButton(pageRequest);
    }

    public void clickDeleteBtn(final DocumentPropertiesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Mark the document for deletion");
        click(deleteDocument);
    }

    public void clickCancelButton(final DocumentPropertiesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Cancel Button");
        click(cancelButton);
    }

    public void clickLinkedDocument(final DocumentPropertiesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Click Linked Document");
        click(linkedDocument);
    }

    public void clickYesButton(final DocumentPropertiesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking the confirm delete pop-up Yes Button");
        click(yesButton);
    }

    public boolean isPartyOrCaseFieldEmpty(final DocumentPropertiesPageRequest pageRequest) {
        if (getText(clientLinkBeanParty).isEmpty() && getText(caseLinkBeanCase).isEmpty()) {
            return false;
        } else {
            pageRequest.log(getPageName(), "Party or Case added to Document : " + getText(clientLinkBeanParty) + "/ " + getText(caseLinkBeanCase));
            return true;
        }
    }


    public void isTitleTextDisplayed(final DocumentPropertiesPageRequest pageRequest, final WebElement webElement, final String propertyVal) {

        if (getWebElementAttribute(webElement, "id", "value").equals(propertyVal)) {
            pageRequest.log(getPageName(), "property value " + propertyVal + " is displayed as expected");
        } else {
            getAssertionHelper().assertIsNotDisplayed(propertyVal, webElement);
        }
    }

    public void isPartialTextDisplayed(final DocumentPropertiesPageRequest pageRequest, final WebElement webElement, final String propertyVal) {

        if (getText(webElement).contains(propertyVal)) {
            pageRequest.log(getPageName(), "property value " + propertyVal + " is displayed as expected");
        } else {
            getAssertionHelper().assertIsNotDisplayed(propertyVal, webElement);
        }
    }


    public void isDocumentTypeDisplayed(final DocumentPropertiesPageRequest pageRequest, final String propertyVal) {

        if (getText(documentType).equals(propertyVal)) {
            pageRequest.log(getPageName(), "property value " + propertyVal + " is displayed as expected");
        } else {
            getAssertionHelper().assertIsNotDisplayed(propertyVal, documentType);
        }
    }

    public void isMetaDataDisplayed(final DocumentPropertiesPageRequest pageRequest, final WebElement webElement, final String propertyVal) {

        if (getText(webElement).equals(propertyVal)) {
            pageRequest.log(getPageName(), "property value " + propertyVal + " is displayed as expected");
        } else {
            getAssertionHelper().assertIsNotDisplayed(propertyVal, webElement);
        }
    }
    public  void isDateDisplayed(final DocumentPropertiesPageRequest pageRequest, final WebElement webElement) {

        if (getText(webElement).contains(getDateWithoutTime())) {
            pageRequest.log(getPageName(), "property value " + getDate() + " is displayed as expected");
        } else {
            getAssertionHelper().assertIsNotDisplayed(getDate(), webElement);
        }
    }

    public  void isDropDownValueDisplayed(final DocumentPropertiesPageRequest pageRequest, final WebElement webElement, final String propertyVal) {

        if (getTextOfDefaultSelectedItem(webElement).equals(propertyVal)) {
            pageRequest.log(getPageName(), "property value " + getTextOfDefaultSelectedItem(webElement) + " is displayed as expected");
        } else {
            getAssertionHelper().assertIsNotDisplayed(getTextOfDefaultSelectedItem(webElement), webElement);
        }
    }

    public void clickCancel(final DocumentPropertiesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Closing properties page");
        clickModalBox(pageCancel);
    }

    public void clickOk(final DocumentPropertiesPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking Ok button");
        click(okButton);
    }

    public WebElement getDocumentType() {
        return documentType;
    }
    public WebElement getParty() {
        return party;
    }
    public WebElement getCaseNum() {
        return caseNum;
    }

    public WebElement getLinkedDoc() {
        return linkedDoc;
    }
    public WebElement getPrivacyTag() {
        return privacyTag;
    }

    public WebElement getStatus() {
        return status;
    }
    public WebElement getTitle() {
        return title;
    }

    public WebElement getCreator() {
        return creator;
    }

    public WebElement getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public WebElement getCreationDate() {
        return creationDate;
    }
    public WebElement getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public static class DocumentPropertiesPageRequest extends AbstractPageRequest {

        public DocumentPropertiesPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getDocumentType() {
            return get("BusinessType");
        }

        public String getNotifier() {
            return get("notifier");
        }

        public String getLinkedDocument() {
            return get("linkedDocument");
        }

        public String getPrivacyTag() {
            return get("PrivacyTag");
        }

        public String getStatus() {
            return get("Status");
        }

        public String getTitle() {
            return get("title");
        }

        public String getFullName() {
            return get("fullName");
        }
    }
}
