package com.gb.fineos.page.sharedpages.workmanager.scripting.au;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.sharedpages.workmanager.scripting.ProcessStatusPageClaimBase;

public class ClaimIntakeProcessStatusPage extends ProcessStatusPageClaimBase {
    private static final String PAGE_NAME = "CREATE CLAIM INTAKE AU PAGE";

    public ClaimIntakeProcessStatusPage() {
        super();
    }


    public static class ClaimIntakeProcessStatusPageRequest extends ProcessStatusPageClaimBaseRequest {
        public ClaimIntakeProcessStatusPageRequest(final TestCaseContext context) {
            super(context);
        }

    }
}
