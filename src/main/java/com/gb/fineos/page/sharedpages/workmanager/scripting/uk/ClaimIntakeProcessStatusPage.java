package com.gb.fineos.page.sharedpages.workmanager.scripting.uk;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.sharedpages.workmanager.scripting.ProcessStatusPageClaimBase;


public class ClaimIntakeProcessStatusPage extends ProcessStatusPageClaimBase {
    public ClaimIntakeProcessStatusPage() {
        super();
    }



    public void selectClaimReceiveDateTimeZoneWithIndex(final ProcessStatusPageClaimBaseRequest pageRequest) {
        pageRequest.log(getPageName(), " Selecting Claim Receive Date TimeZone as : " + pageRequest.getClaimReceiveDateTimeZone());
        selectByIndex(pageRequest.getClaimReceivedTimeZoneIndex(), getClaimReceivedDateTimeZoneDropdown());
    }

    public static class ClaimIntakeProcessStatusPageRequest extends ProcessStatusPageClaimBaseRequest {
        public ClaimIntakeProcessStatusPageRequest(final TestCaseContext context) {
            super(context);
        }
    }

}
