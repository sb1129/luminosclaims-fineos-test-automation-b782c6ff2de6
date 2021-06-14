package com.gb.fineos.page.sharedpages.workmanager.activities.documents;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import com.gb.fineos.screencapture.ScreenCapture;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LinkADocumentPage extends BasePage {

    @FindBy(xpath = "//input[contains(@id,'DocumentsForCaseListviewWidget_') and contains(@id,'_Checkbox_RowId_0_CHECKBOX')]")
    private WebElement associatedDocumentsCheckBox;
    @FindBy(xpath = "//td[contains(@class,'ListCell wrap')]/a[contains(@id,'_Insurer_Approval')]")
    private WebElement insurerApprovalRow;
    @FindBy(xpath = "//input[contains(@id,'_searchPageOk_cloned')]")
    private WebElement okButton;
    @FindBy(xpath = "//input[contains(@name,'_searchButton')]")
    private WebElement caseSearchButton;

    public LinkADocumentPage() {
        super("LINK A DOCUMENT PAGE");
    }

    public void clickOnCheckBox(final LinkADocumentPageRequest pageRequest) {
        if (getDriver().findElements(By.xpath("//span[contains(text(),'Associated Documents')]")).isEmpty()) {
            clickCaseSearchButton(pageRequest);
        }
        pageRequest.log(getPageName(), "Clicking on CheckBox ");
        click(associatedDocumentsCheckBox);
    }

    public void clickCaseSearchButton(final LinkADocumentPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Search Button ");
        try {
            click(caseSearchButton);
        } catch (Exception e) {
            pageRequest.warning(getPageName(), "There was an issue when attempting to search for the case within the Link Documents page", true);
            throw e;
        }
    }

    public boolean associatedDocumentsAreVisible(final LinkADocumentPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Looking for 'Associated Documents' Widget");
        try {
            boolean widgetFound = !getDriver().findElements(By.xpath("//span[contains(text(),'Associated Documents')]")).isEmpty();
            if (!widgetFound) {
                pageRequest.log(getPageName(), "Could not locate 'Associated Documents' widget");
                return false;
            }
            return true;
        } catch (Exception e) {
            pageRequest.warning(getPageName(), "There was an error when attempting to determine if the 'Associated Documents' widget exists on the page", true);
            throw e;
        }
    }

    private void selectAssociatedDocument(final LinkADocumentPageRequest pageRequest, final String documentName) {
        pageRequest.log(getPageName(), "Selecting " + documentName + " row");
        selectTaskFromTaskTable(pageRequest, "//table[contains(@id, 'DocumentsForCaseListviewWidget_') and contains(@id,'_DocumentsViewControl')]", documentName);
    }

    public void selectInsurerApprovalRow(final LinkADocumentPageRequest pageRequest) {
        selectAssociatedDocument(pageRequest, "Insurer Approval");
    }

    public void selectDelegatedAuthorityApprovalRow(final LinkADocumentPageRequest pageRequest) {
        selectAssociatedDocument(pageRequest, "Delegated Authority Approval");
    }

    public void clickOnOKBox(final LinkADocumentPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Ok Button ");
        click(okButton);
        ScreenCapture.logScreenshot(getPageName(), LogStatus.INFO);
    }

    public static class LinkADocumentPageRequest extends AbstractPageRequest {

        public LinkADocumentPageRequest(final TestCaseContext context) {
            super(context);
        }

    }
}
