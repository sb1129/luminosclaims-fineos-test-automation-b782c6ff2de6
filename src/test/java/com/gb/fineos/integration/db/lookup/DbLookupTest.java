package com.gb.fineos.integration.db.lookup;

import com.gb.fineos.integration.db.DbCall;
import org.powermock.reflect.Whitebox;
import org.testng.annotations.Test;

import java.math.BigDecimal;

import static org.testng.Assert.assertEquals;

@SuppressWarnings("squid:S00112")
public class DbLookupTest {
    private DbLookup dbLookup;

    @Test
    public void formatSqlSimpleStr() throws Exception {
        dbLookup = new DbLookup(DbCall.Database.FINEOSAPP,"GB.Payments.Tax.TaxConstantsLookup") {
        };
        Object[] params = new Object[]{"GSTRate"};
        String sql = Whitebox.invokeMethod(dbLookup, "formatSql", params);
        assertEquals(sql, "SELECT AnswerNum, AnswerStr FROM VGetLookupData " +
            "WHERE LookupName = 'GB.Payments.Tax.TaxConstantsLookup' " +
            "AND GETDATE() BETWEEN LookupEffectiveFromDate AND LookupEffectiveToDate " +
            "AND MinStr_1 = 'GSTRate'");
    }

    @Test
    public void formatSqlMixedStrAndNum() throws Exception {
        dbLookup = new DbLookup(DbCall.Database.FINEOSAPP, "Samples.rehabilitation.outcomemeasure.CIQProductivityVariableLookup") {
        };
        Object[] params = new Object[]{
            "13",
            new BigDecimal(77504023),
            null,
            new BigDecimal(77504025)};
        String sql = Whitebox.invokeMethod(dbLookup, "formatSql", params);
        assertEquals(sql, "SELECT AnswerNum, AnswerStr FROM VGetLookupData " +
            "WHERE LookupName = 'Samples.rehabilitation.outcomemeasure.CIQProductivityVariableLookup' " +
            "AND GETDATE() BETWEEN LookupEffectiveFromDate AND LookupEffectiveToDate " +
            "AND MinStr_1 = '13' " +
            "AND (MinNum_2 IS NOT NULL AND MinNum_2 >= 77504023 AND MaxNum_2 IS NOT NULL AND MaxNum_2 <= 77504023) " +
            "AND 1 = 1 " +
            "AND (MinNum_4 IS NOT NULL AND MinNum_4 >= 77504025 AND MaxNum_4 IS NOT NULL AND MaxNum_4 <= 77504025)");
    }
}
