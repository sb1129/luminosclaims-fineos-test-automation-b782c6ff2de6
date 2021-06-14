package com.gb.fineos.page.sharedpages.workmanager.scripting.icare;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.sharedpages.workmanager.scripting.ProcessStatusPageBase;

public class NotificationIntakeProcessStatusPage extends ProcessStatusPageBase {
    private static final String PAGE_NAME = "CREATE NOTIFICATION INTAKE ICARE PAGE";

    public NotificationIntakeProcessStatusPage() {
        super();
    }


    public void selectIncidentCode(final NotificationIntakeProcessStatusPageRequest pageRequest) {
        pageRequest.log(PAGE_NAME, " Entering Incident Code as: " + pageRequest.getIncidentCode());
        selectByIndex(pageRequest.getIncidentCodeIndex(), getIncidentCode());
    }

    public static class NotificationIntakeProcessStatusPageRequest extends ProcessStatusPageBaseRequest {
        public NotificationIntakeProcessStatusPageRequest(final TestCaseContext context) {
            super(context);
        }

    }

}
