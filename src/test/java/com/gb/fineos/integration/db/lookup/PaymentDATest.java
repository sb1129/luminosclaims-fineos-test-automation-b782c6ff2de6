package com.gb.fineos.integration.db.lookup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gb.fineos.integration.db.DbCall;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

public class PaymentDATest {
    private static final String PAYMENT_BENEFIT = "LU001887-01-01";
    private static final String DA_TYPE = "Payment";

    private PaymentDA paymentDA;

    @BeforeMethod
    public void setUp() {
        paymentDA = new PaymentDA(DbCall.Database.FINEOSAPP) {
        };
    }

    @Test
    public void test_getSQLStringFromFile() throws IOException {
        String SQLString = paymentDA.getSQLStringFromFile();
        assertEquals(SQLString.startsWith("with CTE_PolicyLookUp"), true);
        assertEquals(SQLString.contains("order by Level"), true);
    }

    @Test
    public void test_formatSql() throws IOException {
        String formattedSQL = paymentDA.formatSql(PAYMENT_BENEFIT, DA_TYPE);
        String[] valuesInQuotes = StringUtils.substringsBetween(formattedSQL, "\'", "\'");
        assertTrue(Arrays.asList(valuesInQuotes).contains(PAYMENT_BENEFIT));
        assertTrue(Arrays.asList(valuesInQuotes).contains(DA_TYPE));
    }

    @Test
    public void test_getRow() throws IOException {
        String paymentDARow = String.valueOf(paymentDA.getRow(PAYMENT_BENEFIT, DA_TYPE));
        assertNotNull(paymentDARow);
        assertEquals(paymentDARow.contains(PAYMENT_BENEFIT), true);
        assertEquals(paymentDARow.contains(DA_TYPE), true);
    }

    @Test
    public void test_getObject() throws IOException {
        Object paymentDARow = paymentDA.getRow(PAYMENT_BENEFIT, DA_TYPE);
        ObjectMapper objectMapper = new ObjectMapper();
        final PaymentBean paymentBean = objectMapper.convertValue(paymentDARow, PaymentBean.class);
        assertNotNull(paymentBean);

    }
}
