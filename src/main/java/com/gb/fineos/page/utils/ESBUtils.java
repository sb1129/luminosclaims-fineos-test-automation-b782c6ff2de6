package com.gb.fineos.page.utils;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.domain.TestInstance;
import com.gb.fineos.integration.rest.ResponseValidator;
import com.gb.fineos.integration.services.CashReceipts;
import com.gb.fineos.integration.services.ProvideAP;
import com.gb.fineos.integration.services.ProvidePRC;
import com.gb.fineos.integration.services.SanctionCheck;

import java.util.concurrent.TimeUnit;

public final class ESBUtils {

    private static final int SECS_TO_WAIT_FOR_PAYMENT_CLEARANCE_NO_SANCTION_CHECK = 360;
    private static final int SECS_TO_WAIT_FOR_SANCTION_CHECK_BEFORE_PAYMENT_CLEARANCE = 180;

    private ESBUtils() {
        // Do nothing.
    }

    /**
     * This method will call ProvideAP job, waits for the specified time for the dynamics to process the payments
     * internally and perform the reconciliation back to Fineos.
     */
    public static void generatePaymentsForDynamicsAndPerformReconciliation(final TestCaseContext tc) {

        if (!tc.isTestInstance(TestInstance.UK)) {
            // Call ProvideAP
            ResponseValidator.assertSuccess(new ProvideAP().callService());
            // Wait for specified seconds for the ProvideAP file to be picked by Dynamics application and process the payments
            pauseProcessForSpecifiedSeconds(SECS_TO_WAIT_FOR_PAYMENT_CLEARANCE_NO_SANCTION_CHECK);
            // Call Provide PRC
            ResponseValidator.assertSuccess(new ProvidePRC().callService());
        } else {
            // For UK instance, Payments need to go through the sanction check process.

            // Call ProvideAP
            ResponseValidator.assertSuccess(new ProvideAP().callService());
            // Wait for specified seconds for the ProvideAP file to be picked by Dynamics application and process the payments
            pauseProcessForSpecifiedSeconds(SECS_TO_WAIT_FOR_SANCTION_CHECK_BEFORE_PAYMENT_CLEARANCE);
            // Call Sanctions Check
            ResponseValidator.assertHTTPStatus200(new SanctionCheck().callService());
            // Wait for specified seconds for the SanctionsCheck payments file to progress to the next stage
            pauseProcessForSpecifiedSeconds(SECS_TO_WAIT_FOR_SANCTION_CHECK_BEFORE_PAYMENT_CLEARANCE);
            // Call Provide PRC
            ResponseValidator.assertSuccess(new ProvidePRC().callService());

        }
    }

    public static void executeCashReceiptsJob() {
        ResponseValidator.assertSuccess(new CashReceipts().callService());
    }

    public static void pauseProcessForSpecifiedSeconds(final int secondsToSleep) {
        try {
            TimeUnit.SECONDS.sleep(secondsToSleep);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
