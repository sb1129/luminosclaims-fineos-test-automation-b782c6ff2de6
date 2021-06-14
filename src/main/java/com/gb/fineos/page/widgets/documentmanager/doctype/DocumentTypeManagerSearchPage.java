package com.gb.fineos.page.widgets.documentmanager.doctype;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DocumentTypeManagerSearchPage  extends BasePage {

    public DocumentTypeManagerSearchPage() {
        super("DOCUMENTS PAGE");
    }

    //Tabs
    @FindBy(xpath = "//td[contains(@id,'_adminDocumentSearch_cell')]")
    private WebElement searchTab;
    @FindBy(xpath = "//td[contains(@id,'_Recent_cell')]")
    private WebElement recentTab;
    @FindBy(xpath = "//td[contains(@id,'_DocumentTypefolder_cell')]")
    private WebElement documentTypeFolderTab;


    // Choose a Document Type
    @FindBy(xpath = "//input[contains(@id,'SearchDocumentTypeWidget_') and contains(@id,'_businesstype')]")
    private WebElement searchBusinessType;
    @FindBy(xpath = "//select[contains(@id,'SearchDocumentTypeWidget_') and contains(@id,'_Category')]")
    private WebElement categoryDropdown;
    @FindBy(xpath = "//input[contains(@id,'SearchDocumentTypeWidget_') and contains(@id,'_Search')]")
    private WebElement searchButton;
    @FindBy(xpath = "//td[text()='Insurer Approval']")
    private WebElement insurerApproval;
    @FindBy(xpath = "//td[text()='Delegated Authority Approval']")
    private WebElement delegatedAuthorityApproval;
    @FindBy(xpath = "//td[text()='Outbound Correspondence']")
    private WebElement outboundCorrespondence;

    @FindBy(xpath = "//input[contains(@id,'_searchPageOk_cloned')]")
    private WebElement okButton;
    @FindBy(xpath = "//input[contains(@id,'_searchPageCancel_cloned')]")
    private WebElement cancelButton;

    public WebElement getInsurerApproval() {
        return insurerApproval;
    }

    public WebElement getDelegatedAuthorityApproval() {
        return delegatedAuthorityApproval;
    }

    public WebElement getOutboundCorrespondence() {
        return outboundCorrespondence;
    }

    public WebElement getOkButton() {
        return okButton;
    }

    public WebElement getCancelButton() {
        return cancelButton;
    }



    public void clickOnInsurerApproval(final DocumentTypeManagerSearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Insurer Approval value");
        click(getInsurerApproval());
    }

    public void clickOnDelegatedAuthorityApproval(final DocumentTypeManagerSearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Delegated Authority Approval value");
        click(getDelegatedAuthorityApproval());
    }

    public void clickOnOutboundCorrespondence(final DocumentTypeManagerSearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Outbound correspondence");
        click(getOutboundCorrespondence());
    }

    public void clickOnOkButton(final DocumentTypeManagerSearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Ok button");
        click(getOkButton());
    }

    public void clickOnSearchTab(final DocumentTypeManagerSearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Search Tab");
        click(searchTab);
    }
    public void enterBusinessTypeName(final DocumentTypeManagerSearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Entering Business Type Name : " + pageRequest.getBusinessType());
        clearAndInput(pageRequest.getBusinessType(), searchBusinessType);
    }
    public void selectCategoryAsDefault(final DocumentTypeManagerSearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Selecting Category Type : Default");
        selectValue(pageRequest.getCategory(), categoryDropdown);
    }

    public void clickOnSearchButton(final DocumentTypeManagerSearchPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Search Button");
        click(searchButton);
    }


    public static class DocumentTypeManagerSearchPageRequest extends AbstractPageRequest {

        public DocumentTypeManagerSearchPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getBusinessType() {
            return get("BusinessType");
        }

        public String getCategory() {
            return get("Category");
        }
    }
}
