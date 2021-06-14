package com.gb.fineos.integration.db.lookup;

import com.gb.fineos.factory.PropertiesFactory;
import com.gb.fineos.integration.db.DbCall;

/**
 * Base class for calling DA SQL.
 */
public class SettlementApprovalDA extends DelegateAuthority {
    private static final String SETTLEMENT_APPROVAL_DA_SQL = "SettlementApprovalDA.SQL";

    public SettlementApprovalDA(final DbCall.Database database) {
        super(database);
    }

    @Override
    public String getFile() {
        return PropertiesFactory.getInstance().getProperties().getProperty("sqlPath").concat(SETTLEMENT_APPROVAL_DA_SQL);
    }

}
