package com.gb.fineos.page.utils;

import com.gb.fineos.domain.CaseType;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageBenefitBase;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageBenefitBase.DisplayCasePageBenefitBaseRequest;
import com.gb.fineos.page.sharedpages.casemanager.partycaseroles.PartyCaseRolePage;
import com.gb.fineos.page.sharedpages.casemanager.partycaseroles.PartyCaseRolePage.PartyCaseRolePageRequest;
import com.gb.fineos.page.sharedpages.partymanager.partysearch.PartySearchPage;
import com.gb.fineos.page.sharedpages.partymanager.partysearch.PartySearchPage.PartySearchPageRequest;

public final class PartyUtils {

    private PartyUtils() {
        // Do nothing.
    }

    public static void linkPayee(final TestCaseContext tc) {
        SearchUtils.searchCase(tc, CaseType.BENEFIT);

        final DisplayCasePageBenefitBase displayCasePageBenefit = tc.getPage(DisplayCasePageBenefitBase.class);
        final DisplayCasePageBenefitBaseRequest request = new DisplayCasePageBenefitBaseRequest(tc);
        displayCasePageBenefit.clickLinkParty(request);
        displayCasePageBenefit.clickPayeeLink(request);

        final PartySearchPage partySearchPage = tc.getPage(PartySearchPage.class);
        final PartyCaseRolePage partyCaseRolePage = tc.getPage(PartyCaseRolePage.class);
        partySearchPage.enterPartyDetails(new PartySearchPageRequest(tc));
        partyCaseRolePage.clickOnOkButton(new PartyCaseRolePageRequest(tc));
    }

}
