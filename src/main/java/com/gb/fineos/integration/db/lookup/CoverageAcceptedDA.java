package com.gb.fineos.integration.db.lookup;

import com.gb.fineos.factory.PropertiesFactory;
import com.gb.fineos.integration.db.DbCall;

/**
 * Base class for calling DA SQL.
 */
public class CoverageAcceptedDA extends DelegateAuthority {
    private static final String COVERAGE_ACCEPTED_DA_SQL = "CoverageAcceptedDA.SQL";

    public CoverageAcceptedDA(final DbCall.Database database) {
        super(database);
    }

    @Override
    public String getFile() {
        return PropertiesFactory.getInstance().getProperties().getProperty("sqlPath").concat(COVERAGE_ACCEPTED_DA_SQL);
    }

}
