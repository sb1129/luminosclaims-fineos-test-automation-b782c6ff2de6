package com.gb.fineos.page.sharedpages.casemanager.displaycase.au;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageClaimBase;

public class DisplayCasePageClaim extends DisplayCasePageClaimBase {

    public DisplayCasePageClaim() {
        super();
    }


    //override or add instance specific methods in here


    public static class DisplayCasePageClaimRequest extends DisplayCasePageClaimBaseRequest {

        public DisplayCasePageClaimRequest(final TestCaseContext context) {
            super(context);
        }

    }

}

