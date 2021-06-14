package com.gb.fineos.domain;

import org.apache.http.client.utils.DateUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DynamicsRequestTest {
    private static final Date RECEIPT_DATE = DateUtils.parseDate("02-06-2020", new String[] { "dd-MM-yyyy"});

    @Test
    public void testGetters() {
        final DynamicsRequest request = DynamicsRequest.builder()
            .date(RECEIPT_DATE)
            .accountNumber("034001297278")
            .accountName("Mr John Smith")
            .caseNumber("UK001588-01")
            .amount(new BigDecimal("850.55"))
            .caseNumberIncludedInReference(true)
            .build();

        assertEquals(RECEIPT_DATE, request.getDate());
        assertEquals("200602", request.getFormattedDate());
        assertEquals("034001297278", request.getAccountNumber());
        assertEquals("Mr John Smith", request.getAccountName());
        assertEquals("UK001588-01", request.getCaseNumber());
        assertEquals("85055", request.getAmount());
        assertTrue(request.isCaseNumberIncludedInReference());
    }
}
