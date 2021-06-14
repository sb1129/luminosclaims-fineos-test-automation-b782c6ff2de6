package com.gb.fineos.integration.db.lookup;

import com.gb.fineos.factory.PropertiesFactory;
import com.gb.fineos.integration.db.DbCall;

/**
 * Base class for calling DA SQL.
 */
public class CoverageDeclinedDA extends DelegateAuthority {
    private static final String COVERAGE_DECLINED_DA_SQL = "CoverageDeclinedDA.SQL";

    public CoverageDeclinedDA(final DbCall.Database database) {
        super(database);
    }

    @Override
    public String getFile() {
        return PropertiesFactory.getInstance().getProperties().getProperty("sqlPath").concat(COVERAGE_DECLINED_DA_SQL);
    }

}
