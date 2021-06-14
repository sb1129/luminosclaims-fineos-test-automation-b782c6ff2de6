package com.gb.fineos.page.sharedpages.workmanager.scripting.nz;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.sharedpages.workmanager.scripting.ProcessStatusPageBase;

public class NotificationIntakeProcessStatusPage extends ProcessStatusPageBase {
    private static final String PAGE_NAME = "CREATE NOTIFICATION INTAKE NZ PAGE";

    public NotificationIntakeProcessStatusPage() {
        super();
    }



    public static class NotificationIntakeProcessStatusPageRequest extends ProcessStatusPageBaseRequest {
        public NotificationIntakeProcessStatusPageRequest(final TestCaseContext context) {
            super(context);
        }

    }

}
