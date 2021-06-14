package com.gb.fineos.page.sharedpages.casemanager.displaycase.icare;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageNotificationBase;

public class DisplayCasePageNotification extends DisplayCasePageNotificationBase {

    public DisplayCasePageNotification() {
        super();
    }


    //override or add instance specific methods in here

    @Override
    public void doClickOnCreateClaim(final DisplayCasePageNotificationBase.DisplayCasePageNotificationBaseRequest pageRequest) {
        switch (pageRequest.getClaimType()) {
            case MOTOR_CLAIM:
            case HEALTH_CLAIM:
                clickAddSubCase(pageRequest);
                break;
            case PERSONAL_INJURY_CLAIM:
                clickOnInjuryLiabilityClaim(pageRequest);
                break;
            case PROPERTY_DAMAGE_CLAIM:
                clickOnPropertyLiabilityClaim(pageRequest);
                break;
            case PROPERTY_CLAIM:
                clickOnCreatePropertyClaim(pageRequest);
                break;
            case MISC_INJURY_CLAIM:
                clickOnMiscInjuryClaim(pageRequest);
                break;
            case MISC_PROPERTY_CLAIM:
                clickOnMiscPropertyClaim(pageRequest);
                break;
            default:
                pageRequest.log(getPageName(), "Claim type is not present in Application." + pageRequest.getClaimType());
                break;
        }
    }

    public static class DisplayCasePageNotificationRequest extends DisplayCasePageNotificationBaseRequest {

        public DisplayCasePageNotificationRequest(final TestCaseContext context) {
            super(context);
        }

    }
}
