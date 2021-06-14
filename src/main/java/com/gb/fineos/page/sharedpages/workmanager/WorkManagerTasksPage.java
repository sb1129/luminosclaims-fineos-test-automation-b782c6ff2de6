package com.gb.fineos.page.sharedpages.workmanager;

import com.gb.fineos.domain.AbstractPageRequest;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.factory.PageFactory;
import com.gb.fineos.page.BasePage;
import com.gb.fineos.page.component.Filter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WorkManagerTasksPage extends BasePage {

    private Filter filter;

    public WorkManagerTasksPage() {
        super("WORKMANAGER TASKS PAGE");
        this.filter = PageFactory.initElements(Filter.class);
        this.filter.addPageNamePrefix(getPageName());
    }

    public Filter getFilter() {
        return filter;
    }

    @FindBy(xpath = "//a[contains(@id,'filter') and contains(@onclick,'_workqueuelistview_Name')]")
    private WebElement filterTaskName;
    @FindBy(xpath = "//a[contains(@id,'filter') and contains(@onclick,'UserWorkQueueSubjectReference_TextFilterUserWorkQueue_')]")
    private WebElement filterSubject;
    @FindBy(xpath = "(//input[contains(@id,'_CHECKBOX')])[1]")
    private WebElement selectFilteredResult;
    @FindBy(xpath = "//a[contains(@id,'_MENUBAR.WorkManagerTasksContextMenu_MENUITEM.UserOpenTask_MENUITEM.UserOpenTasklink')]")
    private WebElement openTaskLink;

     @FindBy(xpath = "//a[contains(@id,'_MENUBAR.WorkManagerTasksContextMenu_MENUITEM.UserCloseTask_MENUITEM.UserCloseTasklink')]")
    private WebElement closeTaskLink;


    public void clickTaskNameFilter(final WorkManagerTasksPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Click Task Name filter");
        clickModalBox(filterTaskName);
        filter.setFilter("UserWorkQueue", "workqueuelistview", "Name");
    }

    public void clickSubjectFilter(final WorkManagerTasksPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Click Subject filter");
        clickModalBox(filterSubject);
        filter.setFilter("UserWorkQueue", "workqueuelistview", "SubjectReference");
    }

    public void selectFilteredResultCheckbox(final WorkManagerTasksPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Select checkbox of the filtered result");
        check(selectFilteredResult);
    }

    public void clickOpenTaskLink(final WorkManagerTasksPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Click Open Task Link");
        click(openTaskLink);
    }

       public void clickCloseTaskLink(final WorkManagerTasksPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Click Close Task Link");
        click(closeTaskLink);
    }

    public static class WorkManagerTasksPageRequest extends AbstractPageRequest {

        public WorkManagerTasksPageRequest(final TestCaseContext context) {
            super(context);
        }

        public String getTaskName() {
            return get("TaskName");
        }

        public String getSubject() {
            return get("Subject");
        }
    }
}
