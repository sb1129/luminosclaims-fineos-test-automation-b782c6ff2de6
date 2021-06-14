package com.gb.fineos.page.sharedpages.workmanager;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ActivityPage extends BasePage {

    //Tabs
    @FindBy(xpath = "//td[contains(@id,'_FINEOS.WorkManager.Activities.ActivityDialogue.Task_ActivityDetails_cell')]")
    private WebElement activityDetailsTab;
    @FindBy(xpath = "//td[contains(@id,'activitytabs_') and contains(@id,'_DocumentView_cell')]")
    private WebElement activityDocumentsTab;
    @FindBy(xpath = "//td[contains(@id,'_FINEOS.WorkManager.Activities.ActivityDialogue.Task_OtherDetails_cell')]")
    private WebElement activityHistoryTab;
    @FindBy(xpath = "//input[contains(@id,'DocumentsForActivitiesListviewWidget_') and contains(@id,'_docLinkButton')]")
    private WebElement linkButton;
    @FindBy(xpath = "//a[contains(@id,'_Correspondence')]")
    private List<WebElement> correspondenceLink;
    @FindBy(xpath = "//input[contains(@id,'_cmdPageBack')]")
    private WebElement cancelScreenButton;


    public ActivityPage() {
        super("ACTIVITY PAGE");
    }

    public void clickOnActivityDocumentsTab(final ActivityPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Activity Documents Tab ");
        clickModalBox(activityDocumentsTab);
    }

    public void openCorrespondenceDocument(final ActivityPageRequest pageRequest) {
        pageRequest.log(getPageName(), "click on the documents Link to open the document");
        clickModalBox(correspondenceLink.get(0));
    }

    public void clickOnLinkButton(final ActivityPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Link Button ");
        click(linkButton);
    }

    public void clickOnCloseScreenButton(final ActivityPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clicking on Close Button ");
        click(cancelScreenButton);

        if (!getDriver().findElements(By.xpath("//input[contains(@id,'_cmdPageBack')]")).isEmpty()) {
            click(cancelScreenButton);
        }
    }

    public static class ActivityPageRequest extends AbstractPageRequest {

        public ActivityPageRequest(final TestCaseContext context) {
            super(context);
        }

    }
}
