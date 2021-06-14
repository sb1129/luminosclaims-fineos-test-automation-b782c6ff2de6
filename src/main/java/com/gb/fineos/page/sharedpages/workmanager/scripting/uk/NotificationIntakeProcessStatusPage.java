package com.gb.fineos.page.sharedpages.workmanager.scripting.uk;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.sharedpages.workmanager.scripting.ProcessStatusPageBase;

public class NotificationIntakeProcessStatusPage extends ProcessStatusPageBase {
    private static final String PAGE_NAME = "CREATE NOTIFICATION INTAKE UK PAGE";
    private static final String TIMEZONE_INDEX = "4";

    public NotificationIntakeProcessStatusPage() {
        super();
    }

    @Override
    public void selectInsuredPolicyTimezone(final ProcessStatusPageBaseRequest pageRequest) {
        pageRequest.log(PAGE_NAME, "Selecting Insured Policy Timezone : " + pageRequest.getInsuredPolicyTimezone());
        selectByIndex(TIMEZONE_INDEX, getInsuredPolicyTimezone());
    }

    public static class NotificationIntakeProcessStatusPageRequest extends ProcessStatusPageBaseRequest {
        public NotificationIntakeProcessStatusPageRequest(final TestCaseContext context) {
            super(context);
        }


    }

}
