package com.gb.fineos.integration.db.lookup;

import com.gb.fineos.factory.PropertiesFactory;
import com.gb.fineos.integration.db.DbCall;

/**
 * Base class for calling DA SQL.
 */
public class PaymentDA extends DelegateAuthority {
    private static final String PAYMENT_DA_SQL = "PaymentDA.SQL";

    public PaymentDA(final DbCall.Database database) {
        super(database);
    }

    @Override
    public String getFile() {
        return PropertiesFactory.getInstance().getProperties().getProperty("sqlPath").concat(PAYMENT_DA_SQL);
    }


}
