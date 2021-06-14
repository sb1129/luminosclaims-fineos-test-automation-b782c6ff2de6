package com.gb.fineos.page.utils;

import com.gb.fineos.domain.DynamicsRequest;
import com.gb.fineos.domain.DynamicsRequest.DynamicsRequestBuilder;
import com.gb.fineos.domain.TestInstance;
import com.gb.fineos.script.utils.BaseTest;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class DynamicsUtilsTest extends BaseTest {

    @Test
    public void sendCashReceiptFile() {
        final DynamicsRequestBuilder dynamicsRequestBuilder = DynamicsRequest.builder();
        dynamicsRequestBuilder.date(new Date());
        dynamicsRequestBuilder.caseNumber("LU017638-01");
        dynamicsRequestBuilder.amount(new BigDecimal("100.00"));
        dynamicsRequestBuilder.caseNumberIncludedInReference(true);
        dynamicsRequestBuilder.testInstance(TestInstance.AU);

        final Map<String, String> binderDetails = DynamicsUtils.getBinderDetails("SEL City Markets 2018/19");

        dynamicsRequestBuilder.accountName(binderDetails.get("Binder_Name"));
        dynamicsRequestBuilder.accountNumber(binderDetails.get("Binder_BSB") + binderDetails.get("Binder_BankAccount"));

        DynamicsUtils.sendCashReceiptFile(dynamicsRequestBuilder.build());
    }

    @Test
    public void sendFxAdjustmentFile() {
        final DynamicsRequestBuilder dynamicsRequestBuilder = DynamicsRequest.builder();
        dynamicsRequestBuilder.date(new Date());
        dynamicsRequestBuilder.caseNumber("LU017638-01");
        dynamicsRequestBuilder.amount(new BigDecimal("100.00"));
        dynamicsRequestBuilder.caseNumberIncludedInReference(true);
        dynamicsRequestBuilder.testInstance(TestInstance.AU);

        final Map<String, String> binderDetails = DynamicsUtils.getBinderDetails("SEL City Markets 2018/19");

        dynamicsRequestBuilder.accountName(binderDetails.get("Binder_Name"));
        dynamicsRequestBuilder.accountNumber(binderDetails.get("Binder_BSB") + binderDetails.get("Binder_BankAccount"));

        DynamicsUtils.sendFxAdjustmentFile(dynamicsRequestBuilder.build());
    }
}
