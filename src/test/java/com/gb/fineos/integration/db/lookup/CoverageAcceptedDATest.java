package com.gb.fineos.integration.db.lookup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gb.fineos.integration.db.DbCall;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class CoverageAcceptedDATest {
    private static final String PAYMENT_BENEFIT = "LU001887-01-01";
    private static final String DA_TYPE = "Coverage Accepted";

    private CoverageAcceptedDA coverageAcceptedDA;

    @BeforeMethod
    public void setUp() {
        coverageAcceptedDA = new CoverageAcceptedDA(DbCall.Database.FINEOSAPP) {
        };
    }

    @Test
    public void test_getSQLStringFromFile() throws IOException {
        String SQLString = coverageAcceptedDA.getSQLStringFromFile();
        assertEquals(SQLString.startsWith("with CTE_PolicyLookUp"), true);
        assertEquals(SQLString.contains("order by Level"), true);
    }

    @Test
    public void test_formatSql() throws IOException {
        String formattedSQL = coverageAcceptedDA.formatSql(PAYMENT_BENEFIT, DA_TYPE);
        String[] valuesInQuotes = StringUtils.substringsBetween(formattedSQL, "\'", "\'");
        assertTrue(Arrays.asList(valuesInQuotes).contains(PAYMENT_BENEFIT));
        assertTrue(Arrays.asList(valuesInQuotes).contains(DA_TYPE));
    }

    @Test
    public void test_getRow() throws IOException {
        String coverageAcceptedDARow = String.valueOf(coverageAcceptedDA.getRow(PAYMENT_BENEFIT, DA_TYPE));
        assertNotNull(coverageAcceptedDARow);
        assertEquals(coverageAcceptedDARow.contains(PAYMENT_BENEFIT), true);
        assertEquals(coverageAcceptedDARow.contains(DA_TYPE), true);
    }

    @Test
    public void getRowValue() throws IOException {
        Object paymentDARow = coverageAcceptedDA.getRow(PAYMENT_BENEFIT, DA_TYPE);
        ObjectMapper objectMapper = new ObjectMapper();
        final PaymentBean paymentBean = objectMapper.convertValue(paymentDARow, PaymentBean.class);
        assertNotNull(paymentBean);
        assertNotNull(paymentBean.Level);
    }

    @Test
    public void getLevels() throws IOException {
        List<String> levels = coverageAcceptedDA.getLevels(PAYMENT_BENEFIT, DA_TYPE);
        assertNotNull(levels);
    }
}
