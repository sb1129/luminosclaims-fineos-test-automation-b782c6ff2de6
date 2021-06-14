package com.gb.fineos.integration.db.lookup;

import com.gb.fineos.factory.PropertiesFactory;
import com.gb.fineos.integration.db.DbCall;

public class CaseNumber extends DelegateAuthority {
    private static final String CASE_NUMBER_SQL = "CaseNumber.SQL";

    public CaseNumber(final DbCall.Database database) {
        super(database);
    }

    @Override
    public String getFile() {
        return PropertiesFactory.getInstance().getProperties().getProperty("sqlPath").concat(CASE_NUMBER_SQL);
    }

}
