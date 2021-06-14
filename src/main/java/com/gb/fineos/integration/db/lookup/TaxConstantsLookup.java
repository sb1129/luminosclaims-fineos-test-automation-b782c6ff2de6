package com.gb.fineos.integration.db.lookup;

import com.gb.fineos.integration.db.DbCall;

/**
 * Retrieves tax constants from lookup for controlling test data.
 */
public class TaxConstantsLookup extends DbLookup {
    public TaxConstantsLookup(final String username, final String password) {
        super(DbCall.Database.FINEOSAPP,  "GB.Payments.Tax.TaxConstantsLookup");
    }

    /** e.g. "20" */
    public String getGstRate() {
        return (String) getLookupValue("GSTRate");
    }

    /** e.g. "VAT must not exceed 20% of the line item recovered amount" */
    public String getRecoveryTaxValidationMessage() {
        return (String) getLookupValue("RecoveryTaxValidationMessage");
    }

    /** e.g. "TaxAppliedRecoveries" */
    public String getTaxApplicableEnumSubset() {
        return (String) getLookupValue("TaxApplicableEnumSubset");
    }
}
