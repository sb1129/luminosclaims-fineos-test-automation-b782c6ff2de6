package com.gb.fineos.page.utils;

import com.gb.fineos.domain.DynamicsRequest;
import org.apache.http.client.utils.DateUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DynamicsUtilsUnitTest {

    private static final Date RECEIPT_DATE = DateUtils.parseDate("02-06-2020", new String[] { "dd-MM-yyyy"});

    private static final String EXPECTED_CASH_RECEIPT =
        "01,WPACAU2DK,91211819,200602,0155,01,80,,2/\r\n" +
        "02,91211819,WPACAU2DK,1,200602,,,AUD,2/\r\n" +
        "03,034001297278,AUD,010,000,,,015,000,,,045,000,,,100,000,,/\r\n" +
        "16,142,85055,Z,,/\r\n" +
        "88,Remitter=Mr John Smith Ref=UK001588-01\r\n" +
        "49,000,2/\r\n" +
        "98,000,1,4/\r\n" +
        "99,4590754205,103,517/\r\n";

    private static final String EXPECTED_FX_NEG_ADJUSTMENT =
        "01,WPACAU2DK,91211819,200602,0155,01,80,,2/\r\n" +
        "02,91211819,WPACAU2DK,1,200602,,AUD,2/\r\n" +
        "03,034001297278,AUD,010,000,,,015,000,,,045,000,,,100,000,,,400,000,,/\r\n" +
        "16,699,85055,Z,,/\r\n" +
        "88,OVERSEAS TELEGRAPHIC TRANSFER REFERENCE 786948 WBC\r\n" +
        "88,PAYPLUS-UK001588-01///ROC/UK001588-01\r\n" +
        "49,000,2/\r\n" +
        "98,000,1,4/\r\n" +
        "99,4236054652,103,521/\r\n";

    @Test
    public void testGenerateCashReceiptData() {
        assertEquals(
            EXPECTED_CASH_RECEIPT,
            DynamicsUtils.generateCashReceiptData(DynamicsRequest.builder().date(RECEIPT_DATE).accountNumber("034001297278").accountName("Mr John Smith").caseNumber("UK001588-01").amount(new BigDecimal("850.55")).build())
        );
    }

    @Test
    public void testGenerateFxNegativeAdjustmentData() {
        assertEquals(
            EXPECTED_FX_NEG_ADJUSTMENT,
            DynamicsUtils.generateFxAdjustmentData(DynamicsRequest.builder().date(RECEIPT_DATE).accountNumber("034001297278").caseNumber("UK001588-01").amount(new BigDecimal("850.55")).build())
        );
    }
}
