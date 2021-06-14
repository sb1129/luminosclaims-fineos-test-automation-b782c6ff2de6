package com.gb.fineos.page.utils;

import com.gb.fineos.domain.DynamicsRequest;
import com.gb.fineos.domain.DynamicsRequest.DynamicsRequestBuilder;
import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.domain.TestInstance;
import com.gb.fineos.factory.PropertiesFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertNotNull;

public final class DynamicsUtils {
    private static final Logger LOG = Logger.getLogger(DynamicsUtils.class);

    private static final String BINDER_MAPPING_CSV = "com/gb/fineos/script/BinderMapping.csv";

    private static final String VERSION_REF = "versionRef";

    private static final String BINDER_NAME = "Binder_Name";
    private static final String BINDER_BSB = "Binder_BSB";
    private static final String BINDER_BANK_ACCOUNT = "Binder_BankAccount";

    private static final String CASH_RECEIPT_AMOUNT = "Cash_Receipt_Amount";
    private static final String FX_ADJ_AMOUNT = "FX_Adj_Amount";

    private static final Properties PROPS = PropertiesFactory.getInstance().getProperties();

    private DynamicsUtils() {
        // Do nothing.
    }

    /**
     * Sends a Cash Receipt file to Dynamics.
     *
     * This method accepts the following data from the Test Case Context:
     *  <ul>
     *      <li>Claim Case Number - <b>GeneratedClaimCaseAlias</b></li>
     *      <li>Policy Version Ref - <b>versionRef</b> (Refers to column of the same name in NotificationDSL unless overridden)</li>
     *      <li>Cash Receipt Payment amount - <b>Cash_Receipt_Amount</b></li>
     *      <li>Include Case Number in Reference flag - CaseNumber_Included_In_Reference (Defaults to TRUE)</li>
     *  </ul>
     *
     *  Keys in <b>bold</b> are mandatory.
     *
     * @param tc Test Case Context
     */
    public static void sendCashReceiptFile(final TestCaseContext tc) {
        assertNotNull("Cash Receipt Payment amount is required.", tc.getValue(CASH_RECEIPT_AMOUNT));

        final DynamicsRequestBuilder dynamicsRequestBuilder = getDynamicsRequestBuilder(tc);
        dynamicsRequestBuilder.amount(new BigDecimal(tc.getValue(CASH_RECEIPT_AMOUNT)));

        tc.log("DynamicsUtils", "Transferring Cash Receipt file to Dynamics.");
        sendCashReceiptFile(dynamicsRequestBuilder.build());
    }

    public static void sendCashReceiptFile(final DynamicsRequest dynamicsRequest) {
        final DateFormat filenameDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        final String dir = PROPS.getProperty("dynamics.cash_receipt.dir");

        final String filename = "Cash_Receipt_data_"
                                    + dynamicsRequest.getCaseNumber()
                                    + "_"
                                    + filenameDateFormat.format(dynamicsRequest.getDate())
                                    + (dynamicsRequest.getTestInstance() == TestInstance.UK ? ".txt" : "");

        final String data = generateCashReceiptData(dynamicsRequest);

        final String dynamicsUploadDestination =
            Optional.of(dir).filter(loc -> loc.endsWith("/")).orElse(dir + "/") + filename;

        try {
            LOG.info("Sending transfer Cash Receipt file to Dynamics upload location: " + dynamicsUploadDestination);
            LOG.debug("Cash Receipt file: " + filename + " data:\n" + data);
            FileUtils.copyInputStreamToFile(new ByteArrayInputStream(data.getBytes()), new File(dynamicsUploadDestination));
        } catch (IOException e) {
            throw new AssertionError("Unable to transfer Cash Receipt file to : " + dynamicsUploadDestination, e);
        }
    }

    /**
     * Sends a FX Adjustment file to Dynamics.
     *
     * This method accepts the following data from the Test Case Context:
     *  <ul>
     *      <li>Claim Case Number - <b>GeneratedClaimCaseAlias</b></li>
     *      <li>Policy Version Ref - <b>versionRef</b> (Refers to column of the same name in NotificationDSL unless overridden)</li>
     *      <li>FX Adjustment amount - <b>FX_Adj_Amount</b></li>
     *      <li>Include Case Number in Reference flag - CaseNumber_Included_In_Reference (Defaults to TRUE)</li>
     *  </ul>
     *
     *  Keys in <b>bold</b> are mandatory.
     *
     * @param tc Test Case Context
     */
    public static void sendFxAdjustmentFile(final TestCaseContext tc) {
        assertNotNull("FX Adjustment amount is required.", tc.getValue(FX_ADJ_AMOUNT));

        final DynamicsRequestBuilder dynamicsRequestBuilder = getDynamicsRequestBuilder(tc);
        dynamicsRequestBuilder.amount(new BigDecimal(tc.getValue(FX_ADJ_AMOUNT)));

        tc.log("DynamicsUtils", "Transferring FX Adjustment file to Dynamics.");
        sendFxAdjustmentFile(dynamicsRequestBuilder.build());
    }

    public static void sendFxAdjustmentFile(final DynamicsRequest dynamicsRequest) {
        final DateFormat filenameDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        final String dir = PROPS.getProperty("dynamics.fx_adjustment.dir");

        final String filename = "FX_Adjustment_data_"
                                    + dynamicsRequest.getCaseNumber()
                                    + "_"
                                    + filenameDateFormat.format(dynamicsRequest.getDate())
                                    + (dynamicsRequest.getTestInstance() == TestInstance.UK ? ".txt" : "");

        final String data = generateFxAdjustmentData(dynamicsRequest);

        final String dynamicsUploadDestination =
            Optional.of(dir).filter(loc -> loc.endsWith("/")).orElse(dir + "/") + filename;

        try {
            LOG.info("Sending transfer FX Adjustment file to Dynamics upload location: " + dynamicsUploadDestination);
            LOG.debug("FX Adjustment file: " + filename + " data:\n" + data);
            FileUtils.copyInputStreamToFile(new ByteArrayInputStream(data.getBytes()), new File(dynamicsUploadDestination));
        } catch (IOException e) {
            throw new AssertionError("Unable to transfer FX Adjustment file to : " + dynamicsUploadDestination, e);
        }
    }

    static DynamicsRequestBuilder getDynamicsRequestBuilder(final TestCaseContext tc) {
        assertNotNull("Claim case number is required.", tc.getClaimCaseNumber());
        assertNotNull("Policy Version Ref is required.", tc.getValue(VERSION_REF));

        final Map<String, String> binderDetails = getBinderDetails(tc.getValue(VERSION_REF));

        final DynamicsRequestBuilder dynamicsRequestBuilder = DynamicsRequest.builder();
        dynamicsRequestBuilder.date(new Date());
        dynamicsRequestBuilder.caseNumber(tc.getClaimCaseNumber());
        dynamicsRequestBuilder.accountName(binderDetails.get(BINDER_NAME));
        dynamicsRequestBuilder.accountNumber(binderDetails.get(BINDER_BSB) + binderDetails.get(BINDER_BANK_ACCOUNT));
        dynamicsRequestBuilder.caseNumberIncludedInReference(Boolean.parseBoolean(tc.getData().getOrDefault("CaseNumber_Included_In_Reference", "true")));
        dynamicsRequestBuilder.testInstance(tc.getTestInstance());

        return dynamicsRequestBuilder;
    }

    static String generateCashReceiptData(final DynamicsRequest request) {
        final String template = PROPS.getProperty("dynamics.cash_receipt.template");

        try {
            final String templateText = FileUtils.readFileToString(new File(Objects.requireNonNull(DynamicsUtils.class.getClassLoader().getResource(template)).toURI()));

            final String reference = request.isCaseNumberIncludedInReference() ? "Ref=" + request.getCaseNumber() : "";

            return String.format(templateText, request.getFormattedDate(), request.getFormattedDate(), request.getAccountNumber(), request.getAmount(), request.getAccountName(), reference);
        } catch (IOException | IllegalStateException | URISyntaxException e) {
            throw new AssertionError("Error generating data using template " + template, e);
        }
    }

    static String generateFxAdjustmentData(final DynamicsRequest request) {
        final String template = PROPS.getProperty("dynamics.fx_adjustment.template");

        try {
            final String templateText = FileUtils.readFileToString(new File(Objects.requireNonNull(DynamicsUtils.class.getClassLoader().getResource(template)).toURI()));

            final String reference = request.isCaseNumberIncludedInReference() ? request.getCaseNumber() : "";

            return String.format(templateText, request.getFormattedDate(), request.getFormattedDate(), request.getAccountNumber(), request.getAmount(), reference, reference);
        } catch (IOException | IllegalStateException | URISyntaxException e) {
            throw new AssertionError("Error generating data using template " + template, e);
        }
    }

    static Map<String, String> getBinderDetails(final String policyVersionRef) {
        try {
            final CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().withCommentMarker('#')
                .parse(new InputStreamReader(requireNonNull(DynamicsUtils.class.getClassLoader().getResourceAsStream(BINDER_MAPPING_CSV), "Cannot find " + BINDER_MAPPING_CSV)));

            return csvParser.getRecords().stream().map(CSVRecord::toMap)
                .filter(map -> map.getOrDefault(VERSION_REF, "").equals(policyVersionRef))
                .findFirst()
                .orElseThrow(() -> new AssertionError("Unable to find: " + policyVersionRef + " in " + BINDER_MAPPING_CSV));
        } catch (IOException | IllegalStateException e) {
            throw new AssertionError(BINDER_MAPPING_CSV + " encountered " + e.getMessage(), e);
        }
    }
}
