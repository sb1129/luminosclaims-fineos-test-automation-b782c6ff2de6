package com.gb.fineos.page.sharedpages.location;

import com.gb.fineos.domain.TestCaseContext;

public class GBEditLocationAddressPage extends GBAddLocationAddressPage {
    public GBEditLocationAddressPage() {
        super();
    }

    public void clearSuburbField(final GBEditLocationAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clear Suburb field");
        getSuburb().clear();
    }

    public void clearStreetNameField(final GBEditLocationAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clear Street Field");
        getStreet().clear();
        selectValue(pageRequest.getStreetType(), getStreetType());
    }

    public void clearPostCodeField(final GBEditLocationAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clear Postcode Field");
        getPostcode().clear();
    }

    public void clearPremiseNo(final GBEditLocationAddressPageRequest pageRequest) {
        pageRequest.log(getPageName(), "Clear Premise No");
        getPremiseNo().clear();
    }

    public static class GBEditLocationAddressPageRequest extends GBAddLocationAddressPageRequest {

        public GBEditLocationAddressPageRequest(final TestCaseContext context) {
            super(context);
        }
    }
}
