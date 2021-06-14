package com.gb.fineos.integration.db.lookup;

import com.gb.fineos.integration.db.DbCall;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class ReserveDATest {
    private static final String PAYMENT_BENEFIT = "LU013392-01-01";
    private static final String DA_TYPE = "Reserve";

    private ReserveDA reserveDA;

    @BeforeMethod
    public void setUp() {
        reserveDA = new ReserveDA(DbCall.Database.FINEOSAPP) {
        };

    }

    @Test
    public void test_getSQLStringFromFile() throws IOException {
        String SQLString = reserveDA.getSQLStringFromFile();
        assertEquals(SQLString.startsWith("with CTE_PolicyLookUp"), true);
        assertEquals(SQLString.contains("order by Level"), true);
    }

    @Test
    public void test_formatSql() throws IOException {
        String formattedSQL = reserveDA.formatSql(PAYMENT_BENEFIT, DA_TYPE);
        String[] valuesInQuotes = StringUtils.substringsBetween(formattedSQL, "\'", "\'");
        assertTrue(Arrays.asList(valuesInQuotes).contains(PAYMENT_BENEFIT));
        assertTrue(Arrays.asList(valuesInQuotes).contains(DA_TYPE));
    }

    @Test
    public void test_getRow() throws IOException {
        String reserveDARow = String.valueOf(reserveDA.getRow(PAYMENT_BENEFIT, DA_TYPE));
        assertNotNull(reserveDARow);
        assertEquals(reserveDARow.contains(PAYMENT_BENEFIT), true);
        assertEquals(reserveDARow.contains(DA_TYPE), true);
    }

}
