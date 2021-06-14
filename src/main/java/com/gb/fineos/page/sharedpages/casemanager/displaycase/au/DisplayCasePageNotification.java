package com.gb.fineos.page.sharedpages.casemanager.displaycase.au;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageNotificationBase;

public class DisplayCasePageNotification extends DisplayCasePageNotificationBase {

    public DisplayCasePageNotification() {
        super();
    }


    //override or add instance specific methods in here


    public static class DisplayCasePageNotificationRequest extends DisplayCasePageNotificationBaseRequest {

        public DisplayCasePageNotificationRequest(final TestCaseContext context) {
            super(context);
        }

    }
}
