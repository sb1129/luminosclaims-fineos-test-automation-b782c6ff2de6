package com.gb.fineos.integration.db.lookup;

import com.gb.fineos.factory.PropertiesFactory;
import com.gb.fineos.integration.db.DbCall;

/**
 * Base class for calling DA SQL.
 */
public class ReserveDA extends DelegateAuthority {
    private static final String RESERVE_DA_SQL = "ReserveDA.SQL";

    public ReserveDA(final DbCall.Database database) {
        super(database);
    }

    @Override
    public String getFile() {
        return PropertiesFactory.getInstance().getProperties().getProperty("sqlPath").concat(RESERVE_DA_SQL);
    }


}
