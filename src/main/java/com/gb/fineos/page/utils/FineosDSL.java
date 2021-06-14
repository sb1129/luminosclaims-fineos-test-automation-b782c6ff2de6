package com.gb.fineos.page.utils;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.domain.TestInstance;
import com.gb.fineos.page.BasePage;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageBase.DisplayCasePageBaseRequest;
import com.gb.fineos.page.sharedpages.casemanager.displaycase.DisplayCasePageBenefitBase;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang.StringUtils;
import org.junit.Assert;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.gb.fineos.domain.CaseType.BENEFIT;
import static com.gb.fineos.domain.CaseType.CLAIM;
import static com.gb.fineos.domain.CaseType.NOTIFICATION;
import static com.gb.fineos.domain.CaseType.OVERPAYMENT;
import static java.util.Objects.requireNonNull;
import static org.junit.Assert.assertTrue;

/**
 * <p>FINEOS DSL (Domain-specific language) used for rapidly creating test data for:</p>
 *
 * <ul>
 *     <li>Notification(s)</li>
 *     <li>Claim(s)</li>
 *     <li>Benefit(s)</li>
 *     <li>Payee(s)</li>
 *     <li>Reserve(s)</li>
 *     <li>Payment(s)</li>
 * </ul>
 *
 * <b>Important</b>
 * <p>Each test scenario CSV file must include the columns: Runmode, UserName, Password.</p>
 * <p>The UserNameFullName column is also mandatory if the test scenario requires approved Reserve(s) and/or Payment(s)</p>
 * <p>Once test data has been created, accessing it in your test case <b>MUST</b> use the <code>getTestCaseContext</code> method.</p><br>
 *
 * <h3>EXAMPLE USAGE</h3>
 * <p>Running a test with the CSV contents:</p>
 *
 * <pre>
 * Runmode,UserName,Password,UserNameFullName,NOT_CaseAliasValue,CLM_CaseAliasValue,BEN_CaseAliasValue,BEN_PAYEE_PartiesType,BEN_PAYEE_firstName,BEN_PAYEE_lastName,BEN_EXP_APP
 * "Y","QLD_LCC1",,QLD Liability CC1,"Liability_PD_Notification","Liability_PD_Claim","Liability_PD_Benefit","Organisation","Melbourne Stars","Mstars",true
 * </pre>
 *
 * <br><p>And executing the following method:</p>
 *
 * <pre>FineosDSL.getInstance(tc).build();</pre>
 *
 * <p>Is equivalent to (assuming the same CSV file is used)</p>
 *
 * <pre>
 * FineosDSL.getInstance(tc, false)
 *      .withNotification("NOT_")
 *          .withClaim("CLM_")
 *              .withBenefit("BEN_")
 *                  .withPayee()
 *                  .and()
 *                  .withExpenseReserve()
 *                  .and()
 *                  .withApproveExpenseReserves()
 *      .build();
 * </pre>
 *
 * <p>Is also equivalent to (assuming a valid test data CSV with 150+ columns exists):</p>
 *
 * <pre>
 *  CaseUtils.createNotification(tc);
 *  CaseUtils.createClaim(tc);
 *  BenefitUtils.createBenefit(tc);
 *  BenefitUtils.moveBenefitToProceedWithPayment(tc);
 *  PartyUtils.linkPayee(tc);
 *  BenefitUtils.addExpenseReserves(tc);
 *  BenefitUtils.approveReserves(tc);
 * </pre>
 */
public final class FineosDSL {

    private static final String CASE_ALIAS_VALUE = "CaseAliasValue";
    private static final String EXISTING_CASE_NUMBER = "ExistingCaseNumber";
    private static final String USER_NAME_FULL_NAME = "UserNameFullName";
    private static final String PARTIES_TYPE = "PartiesType";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String REOPEN_FOR_EXPENSE_PAYMENT = "ReopenForExpensePayment";
    private static final String MAKE_RECOMMENDATION = "MakeRecommendation";
    private static final String COVERAGE_ACCEPTED = "CoverageAccepted";
    private static final String COVERAGE_DECLINED = "CoverageDeclined";
    private static final String SETTLEMENT_OFFER_APPROVED = "SettlementOfferApproved";
    private static final String PROCEED_WITH_PAYMENT = "ProceedWithPayment";
    private static final String PRE_CLOSURE_CHECK = "PreclosureCheck";
    private static final String UPLOAD_DOCUMENT = "UploadDocument";


    private static final Pattern SPECIAL_CSV_HEADERS = Pattern.compile("^(NOT\\d*_|CLM_|BEN_).*");

    private static final String TEST_CASE_COLUMN_IS_MANDATORY = "Test case data column is mandatory: ";
    private static final String FINEOS_DSL_MUST_BE_BUILT_FIRST = "FineosDSL must be built first.";

    private final TestCaseContext testCase;
    private final List<NotificationDSL> notifications = new ArrayList<>();
    private final List<TestStep> testSteps = new ArrayList<>();

    private final Map<String, Map<String, String>> notificationDSLTestData;
    private final Map<String, Map<String, String>> claimDSLTestData;
    private final Map<String, Map<String, String>> benefitDSLTestData;
    private final Map<String, Map<String, String>> coverageAcceptedDSLTestData;
    private final Map<String, Map<String, String>> coverageDeniedDSLTestData;
    private final Map<String, Map<String, String>> settlementOfferForApproveTestData;
    private final Map<String, Map<String, String>> reserveDSLTestData;
    private final Map<String, Map<String, String>> reserveApproveDSLTestData;
    private final Map<String, Map<String, String>> paymentDSLTestData;
    private final Map<String, Map<String, String>> paymentApproveDSLTestData;
    private final Map<String, Map<String, String>> overpaymentDSLTestData;

    private boolean built;

    private FineosDSL(final TestCaseContext testCase) {
        this.testCase = testCase;

        this.notificationDSLTestData = getTestData("NotificationDSL.csv", CASE_ALIAS_VALUE);
        this.claimDSLTestData = getTestData("ClaimDSL.csv", CASE_ALIAS_VALUE);
        this.benefitDSLTestData = getTestData("BenefitDSL.csv", CASE_ALIAS_VALUE);
        this.coverageAcceptedDSLTestData = getTestData("CoverageAcceptedDSL.csv", BENEFIT.getCaseAlias());
        this.coverageDeniedDSLTestData = getTestData("CoverageDeniedDSL.csv", BENEFIT.getCaseAlias());
        this.settlementOfferForApproveTestData = getTestData("SettlementOfferApprovalDSL.csv", BENEFIT.getCaseAlias());
        this.reserveDSLTestData = getTestData("ExpenseReserveDSL.csv", BENEFIT.getCaseAlias());
        this.reserveApproveDSLTestData = getTestData("ExpenseReserveApproveDSL.csv", BENEFIT.getCaseAlias());
        this.paymentDSLTestData = getTestData("PaymentDSL.csv", BENEFIT.getCaseAlias());
        this.paymentApproveDSLTestData = getTestData("PaymentApproveDSL.csv", BENEFIT.getCaseAlias());
        this.overpaymentDSLTestData = getTestData("OverpaymentDSL.csv", BENEFIT.getCaseAlias());
    }

    public static FineosDSL getInstance(final TestCaseContext testCase) {
        return getInstance(testCase, true);
    }

    public static FineosDSL getInstance(final TestCaseContext testCase, final boolean preProcessCSV) {
        FineosDSL fineosDSL = new FineosDSL(testCase);

        if (preProcessCSV) {
            if (testCase.getData().keySet().stream().anyMatch(key -> key.startsWith("NOT_"))) {
                fineosDSL.processSimpleCSV();
            } else {
                fineosDSL.processComplexCSV();
            }
        }

        return fineosDSL;
    }

    /**
     * <p>Processes a 'simple' CSV file.</p><br>
     *
     * <p>Allows for only a single: Notification, Claim, Benefit, Payee, Reserve, Settlement Offer and Payment.</p><br>
     *
     * <h3>CSV Header Prefix structure (Suffixed columns override columns in CSV file)</h3>
     * <pre>
     * NOT_ (NotificationDSL.csv)
     *    CLM_ (ClaimDSL.csv)
     *       BEN_ (BenefitDSL.csv)
     *          BEN_PAYEE_ (Test case CSV file - Specify headers suffixed with: PartiesType, firstName, lastName.)
     *          BEN_EXP_ (ExpenseReserveDSL.csv)
     *          BEN_EXP_APP_ (ExpenseReserveApproveDSL.csv)
     *          BEN_SO_ (SettlementOfferReserveDSL.csv)
     *          BEN_SO_APP (SettlementOfferReserveApproveDSL.csv)
     *          BEN_PAY_ (PaymentDSL.csv)
     *          BEN_PAY_APP_ (PaymentApproveDSL.csv)
     * </pre>
     */
    private void processSimpleCSV() {
        final NotificationDSL notification = withNotification("NOT_");

        if (testCase.getData().keySet().stream().anyMatch(key -> key.startsWith("CLM_"))) {
            final ClaimDSL claim = notification.withClaim("CLM_");

            if (testCase.getData().keySet().stream().anyMatch(key -> key.startsWith("BEN_"))) {
                final BenefitDSL benefit = claim.withBenefit("BEN_");

                if (testCase.getData().keySet().stream().anyMatch(key -> key.startsWith("BEN_PAYEE_"))) {
                    benefit.withPayee("BEN_PAYEE_");
                }

                if (testCase.getData().keySet().stream().anyMatch(key -> key.startsWith("BEN_EXP_"))) {
                    benefit.withReserve("BEN_EXP_");
                }

                if (testCase.getData().keySet().stream().anyMatch(key -> key.startsWith("BEN_PAY_"))) {
                    benefit.withPayment("BEN_PAY_");
                }
            }
        }
    }

    /**
     * <p>Processes a 'complex' CSV file.</p><br>
     *
     * <p>Allows for multiple: Notifications, Claims, Benefits, Payees, Expenses, Settlement Offers and Payments.</p><br>
     *
     * <h3>CSV Header Prefix structure (Suffixed columns override columns in CSV file)</h3>
     * <pre>
     * NOT1_ (NotificationDSL.csv)
     *    NOT1_CLM1_ (ClaimDSL.csv)
     *    NOT1_CLM2_ (ClaimDSL.csv)
     * NOT2_ (NotificationDSL.csv)
     *    NOT2_CLM1_ (ClaimDSL.csv)
     *    NOT2_CLM2_ (ClaimDSL.csv)
     *       NOT2_CLM2_BEN1_ (BenefitDSL.csv)
     *          NOT2_CLM2_BEN1_PAYEE1_ (Test case CSV file - Specify headers suffixed with: PartiesType, firstName, lastName.)
     *          NOT2_CLM2_BEN1_EXP1_ (ExpenseReserveDSL.csv)
     *          NOT2_CLM2_BEN1_EXP_APP_ (ExpenseReserveApproveDSL.csv)
     *          NOT2_CLM2_BEN1_SO_ (SettlementOfferReserveDSL.csv)
     *          NOT2_CLM2_BEN1_SO_APP (SettlementOfferReserveApproveDSL.csv)
     *          NOT2_CLM2_BEN1_PAY1_ (PaymentDSL.csv)
     * </pre>
     */
    private void processComplexCSV() {
        testCase.getData().keySet().stream()
            .filter(key -> key.matches("^NOT/d*_CaseAliasValue"))
            .forEach(notificationCaseAliasValue -> {
                final String notificationCSVHeaderPrefix = notificationCaseAliasValue.substring(0, notificationCaseAliasValue.indexOf('_') + 1);

                final NotificationDSL notification = withNotification(notificationCSVHeaderPrefix);

                testCase.getData().keySet().stream()
                    .filter(key -> key.matches("^" + notificationCSVHeaderPrefix + "CLM/d*_CaseAliasValue"))
                    .forEach(claimCaseAliasValue -> {
                        final String claimCSVHeaderPrefix = claimCaseAliasValue.substring(0, claimCaseAliasValue.indexOf('_') + 1);

                        final ClaimDSL claim = notification.withClaim(claimCSVHeaderPrefix);

                        testCase.getData().keySet().stream()
                            .filter(key -> key.matches("^" + claimCSVHeaderPrefix + "BEN/d*_CaseAliasValue"))
                            .forEach(benefitCaseAliasValue -> {
                                final String benefitCSVHeaderPrefix = benefitCaseAliasValue.substring(0, benefitCaseAliasValue.indexOf('_') + 1);

                                final BenefitDSL benefit = claim.withBenefit(benefitCSVHeaderPrefix);

                                testCase.getData().keySet().stream()
                                    .filter(key -> key.matches("^" + benefitCSVHeaderPrefix + "PAYEE/d*_"))
                                    .map(key -> key.substring(0, key.indexOf('_', (benefitCSVHeaderPrefix + "PAYEE").length()) + 1))
                                    .distinct()
                                    .forEach(benefit::withPayee);

                                testCase.getData().keySet().stream()
                                    .filter(key -> key.matches("^" + benefitCSVHeaderPrefix + "EXP/d*_"))
                                    .map(key -> key.substring(0, key.indexOf('_', (benefitCSVHeaderPrefix + "EXP").length()) + 1))
                                    .distinct()
                                    .forEach(benefit::withReserve);

                                testCase.getData().keySet().stream()
                                    .filter(key -> key.matches("^" + benefitCSVHeaderPrefix + "PAY/d*_"))
                                    .map(key -> key.substring(0, key.indexOf('_', (benefitCSVHeaderPrefix + "PAY").length()) + 1))
                                    .distinct()
                                    .forEach(benefit::withPayment);
                            });
                    });
            });
    }

    /**
     * Reads a CSV file from the package <code>com.gb.fineos.script</code> of the active <i>env-</i> Maven Profile.
     * @param fileName Name of the CSV file to load (may or may not contain <code>.csv</code> extension)
     * @param caseAliasValueHeader Header name for the CaseAliasValue. Typically: CaseAliasValue, ClaimCaseAlias, BenefitCaseAlias
     *
     * @return A map keyed on the value of <code>caseAliasValueHeader</code>
     */
    private Map<String, Map<String, String>> getTestData(final String fileName, final String caseAliasValueHeader) {
        final String csv = "com/gb/fineos/script/" + Optional.of(fileName).filter(fn -> fn.endsWith(".csv")).orElseGet(() -> fileName + ".csv");

        try {
            final CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().withCommentMarker('#')
                .parse(new InputStreamReader(requireNonNull(FineosDSL.class.getClassLoader().getResourceAsStream(csv), "Cannot find " + csv)));

            return csvParser.getRecords().stream().map(CSVRecord::toMap).collect(Collectors.toMap(map -> map.get(caseAliasValueHeader), Function.identity()));
        } catch (IOException | IllegalStateException e) {
            throw new AssertionError(fileName + " encountered " + e.getMessage(), e);
        }
    }

    /**
     * <p>Builds the <code>TestCaseContent</code> for a DSL.</p>
     *
     * <p>Adds in the <code>UserName</code>, <code>Password</code> values from the test case data.</p>
     *
     * <p>Applies any overrides from the test case data.</p><br>
     *
     * <b>If:</b>
     * <ul>
     *     <li><code>NotificationDSL.csv</code> is the DSL</li>
     *     <li>Test case data contained an entry with key (<code>NOT_claimSource</code>) and value (<code>Phone</code>)</li>
     * </ul>
     * <p>The value <code>Phone</code> would <b>replace</b> the value (<code>Email</code>) of the <code>claimSource</code> column.</p>
     *
     * @param csvHeaderPrefix CSV header prefix
     * @param testData Test data map to be added to the <code>TestCaseContext</code>.
     * @param parent Parent Test Case Context
     *
     * @return Test Case Context for a DSL
     */
    private TestCaseContextDSL buildTestCaseContext(final String csvHeaderPrefix, final Map<String, String> testData, final TestCaseContext parent) {
        if (StringUtils.isNotBlank(csvHeaderPrefix)) {
            for (final Map.Entry<String, String> entry : testCase.getData().entrySet()) {
                final Matcher matcher = SPECIAL_CSV_HEADERS.matcher(entry.getKey());

                if (entry.getKey().startsWith(csvHeaderPrefix)) {
                    final String key = entry.getKey().replace(csvHeaderPrefix, "");
                    testData.put(key, entry.getValue());
                } else if (!matcher.find() && !testData.containsKey(csvHeaderPrefix + entry.getKey())) {
                    testData.put(entry.getKey(), entry.getValue());
                }
            }
        }

        return new TestCaseContextDSL(testCase, testData, parent);
    }

    /**
     * Adds a Notification.
     *
     * @param csvHeaderPrefix CSV header prefix
     *
     * @return <code>NotificationDSL</code> object (itself).
     */
    public NotificationDSL withNotification(final String csvHeaderPrefix) {
        final NotificationDSL notification = new NotificationDSL(csvHeaderPrefix, this);

        notifications.add(notification);

        return notification;
    }

    /**
     * Return a list of all Notifications.
     * @return List of <code>NotificationDSL</code>
     */
    public List<NotificationDSL> getNotifications() {
        return notifications;
    }

    /**
     * Return a Notification.
     *
     * @throws AssertionError if zero or more than one Notification exists
     * @return A <code>NotificationDSL</code>
     */
    public NotificationDSL getNotification() {
        return notifications.stream().findFirst().orElseThrow(() -> new AssertionError("Zero or more than one Notification exists."));
    }

    /**
     * Return a Notification for a given <code>caseAliasValue</code>.
     *
     * @param caseAliasValue Case alias value to search for.
     *
     * @return The first <code>NotificationDSL</code> that matches.
     */
    public NotificationDSL getNotification(final String caseAliasValue) {
        return notifications.stream()
            .filter(notification -> notification.getCaseAliasValue().equals(caseAliasValue)).findFirst()
            .orElseThrow(() -> new AssertionError("No Notification found with CaseAliasValue: " + caseAliasValue));
    }

    /**
     * Return a list of Notifications for a given <code>caseAliasValue</code>.
     *
     * @param caseAliasValue Case alias value to search for.
     *
     * @return A list of <code>NotificationDSL</code>
     */
    public List<NotificationDSL> getNotifications(final String caseAliasValue) {
        return notifications.stream()
            .filter(notification -> Optional.ofNullable(caseAliasValue).map(nt -> nt.equalsIgnoreCase(notification.getCaseAliasValue())).orElse(true))
            .collect(Collectors.toList());
    }

    /**
     * Return a Claim.
     *
     * @throws AssertionError if zero or more than one Claim exists
     * @return A <code>ClaimDSL</code>
     */
    public ClaimDSL getClaim() {
        return getNotification().getClaims().stream().findFirst().orElseThrow(() -> new AssertionError("Zero or more than one Claim exists."));
    }

    /**
     * Return a Claim for a given <code>caseAliasValue</code>.
     *
     * @param caseAliasValue Case alias value to search for.
     *
     * @return The first <code>ClaimDSL</code> that matches.
     */
    public ClaimDSL getClaim(final String caseAliasValue) {
        return getNotification().getClaims().stream()
            .filter(claim -> claim.getCaseAliasValue().equals(caseAliasValue)).findFirst()
            .orElseThrow(() -> new AssertionError("No Claim found with CaseAliasValue: " + caseAliasValue));
    }

    /**
     * Return a Benefit.
     *
     * @throws AssertionError if zero or more than one Benefit exists
     * @return A <code>BenefitDSL</code>
     */
    public BenefitDSL getBenefit() {
        return getClaim().getBenefits().stream().findFirst().orElseThrow(() -> new AssertionError("Zero or more than one Benefit exists."));
    }

    /**
     * Return a Benefit for a given <code>caseAliasValue</code>.
     *
     * @param caseAliasValue Case alias value to search for.
     *
     * @return The first <code>BenefitDSL</code> that matches.
     */
    public BenefitDSL getBenefit(final String caseAliasValue) {
        return getClaim().getBenefits().stream()
            .filter(claim -> claim.getCaseAliasValue().equals(caseAliasValue)).findFirst()
            .orElseThrow(() -> new AssertionError("No Benefit found with CaseAliasValue: " + caseAliasValue));
    }

    /**
     * Return a list of Payments for a given <code>caseAliasValue</code>.
     *
     * @param caseAliasValue Case alias value to search for.
     *
     * @return A list of <code>PaymentDSL</code>
     */
    public List<PaymentDSL> getPayments(final String caseAliasValue) {
        return getBenefit(caseAliasValue).getPayments();
    }

    /**
     * Return a list of Payees for a given <code>caseAliasValue</code>.
     *
     * @param caseAliasValue Case alias value to search for.
     *
     * @return A list of <code>PayeeDSL</code>
     */
    public List<PayeeDSL> getPayees(final String caseAliasValue) {
        return getBenefit(caseAliasValue).getPayees();
    }

    /**
     * Creates all test data in FINEOS.
     *
     * @return <code>FineosDSL</code> (itself)
     */
    public FineosDSL build() {
        if (!built) {
            notifications.forEach(NotificationDSL::doBuild);

            final List<OverpaymentDSL> overpayments = notifications.stream()
                .flatMap(notification -> notification.getClaims().stream()
                    .flatMap(claim -> claim.getBenefits().stream()
                        .flatMap(benefit -> benefit.getPayments().stream()
                            .map(PaymentDSL::getOverpayment))))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

            if (!overpayments.isEmpty()) {
                ESBUtils.generatePaymentsForDynamicsAndPerformReconciliation(testCase);
                overpayments.forEach(OverpaymentDSL::doBuild);
            }

            built = true;
        }

        return this;
    }

    /**
     * Optionally builds and executes test steps in the order that they were defined.
     */
    public void test() {
        if (!built) {
            build();
        }

        testSteps.forEach(TestStep::execute);
    }

    /**
     * Wrapper class to capture the data necessary to execute a Test Step.
     */
    private static final class TestStep {
        private final TestCaseContext testCaseContext;
        private final Consumer<TestCaseContext> scenario;

        private TestStep(final TestCaseContext testCaseContext, final Consumer<TestCaseContext> scenario) {
            this.testCaseContext = testCaseContext;
            this.scenario = scenario;
        }

        public void execute() {
            this.scenario.accept(testCaseContext);
        }
    }

    /**
     * Loads data from the <code>NotificationDSL.csv</code> file for row with <code>CaseAliasValue</code> column value
     * and overrides column values with those supplied in the test case data prefixed by <code>csvHeaderPrefix</code>.
     */
    public final class NotificationDSL {
        private final List<ClaimDSL> claims = new ArrayList<>();

        private final String csvHeaderPrefix;
        private final String caseAliasValue;
        private final FineosDSL fineosDSL;
        private final TestCaseContextDSL testCaseContext;

        private Consumer<TestCaseContext> implementation;

        private String caseNumber;

        private NotificationDSL(final String csvHeaderPrefix, final FineosDSL fineosDSL) {
            this.csvHeaderPrefix = csvHeaderPrefix;
            this.caseAliasValue = Optional.ofNullable(testCase.getData().get(csvHeaderPrefix + CASE_ALIAS_VALUE))
                .orElseThrow(() -> new AssertionError(TEST_CASE_COLUMN_IS_MANDATORY + csvHeaderPrefix + CASE_ALIAS_VALUE));
            this.fineosDSL = fineosDSL;

            final Map<String, String> testData = new HashMap<>(notificationDSLTestData.get(caseAliasValue));
            testData.put(NOTIFICATION.getCaseAlias(), caseAliasValue);

            this.testCaseContext = buildTestCaseContext(csvHeaderPrefix, testData, null);

            withImplementation(CaseUtils::createNotification);
        }

        public String getCsvHeaderPrefix() {
            return csvHeaderPrefix;
        }

        public String getCaseAliasValue() {
            return caseAliasValue;
        }

        public String getCaseNumber() {
            return caseNumber;
        }

        public TestCaseContext getTestCaseContext() {
            return testCaseContext;
        }

        public List<ClaimDSL> getClaims() {
            return claims;
        }

        /**
         * Sets the Party's first name.
         *
         * @param partyFirstName Party first name
         *
         * @return <code>NotificationDSL</code> (itself)
         */
        public NotificationDSL withPartyFirstName(final String partyFirstName) {
            testCaseContext.getData().put(FIRST_NAME, partyFirstName);

            return this;
        }

        /**
         * Sets the Party's last name.
         *
         * @param partyLastName Party last name
         *
         * @return <code>NotificationDSL</code> (itself)
         */
        public NotificationDSL withPartyLastName(final String partyLastName) {
            testCaseContext.getData().put(LAST_NAME, partyLastName);

            return this;
        }

        /**
         * Replaces the default Notification creation implementation.
         *
         * Default implementation:
         * <pre>
         * tc -> {
         *     SearchUtils.searchParty(tc);
         *     CaseUtils.createNotification(tc);
         * };
         * </pre>
         *
         * @param implementation Notification creation implementation.
         *
         * @return <code>NotificationDSL</code> (itself)
         */
        public NotificationDSL withImplementation(final Consumer<TestCaseContext> implementation) {
            this.implementation = implementation;

            return this;
        }

        /**
         * Adds a Test step to execute once the DSL has been built.
         *
         * @param testStepImpl Test step implementation.
         *
         * @return <code>NotificationDSL</code> (itself)
         */
        public NotificationDSL withTestStep(final Consumer<TestCaseContext> testStepImpl) {
            testSteps.add(new TestStep(this.testCaseContext, testStepImpl));

            return this;
        }

        /**
         * Adds a Claim to the Notification.
         *
         * @param csvHeaderPrefix CSV header prefix to reference override columns in the test case data.
         *
         * @return <code>ClaimDSL</code>
         */
        public ClaimDSL withClaim(final String csvHeaderPrefix) {
            final ClaimDSL claim = new ClaimDSL(csvHeaderPrefix, this);

            claims.add(claim);

            return claim;
        }

        /**
         * Creates all test data in FINEOS.
         *
         * @return <code>FineosDSL</code>
         */
        public FineosDSL build() {
            return this.fineosDSL.build();
        }

        /**
         * Creates the Notification (and dependent) test data in FINEOS.
         */
        private void doBuild() {
            if (testCaseContext.getData().containsKey(EXISTING_CASE_NUMBER)) {
                testCaseContext.getData().put(NOTIFICATION.getGeneratedCaseAlias(), testCaseContext.getData().get(EXISTING_CASE_NUMBER));
            } else {
                implementation.accept(testCaseContext);
            }

            caseNumber = testCaseContext.getData().get(NOTIFICATION.getGeneratedCaseAlias());

            claims.forEach(ClaimDSL::doBuild);
        }

        /**
         * Executes test steps.
         */
        public void test() {
            this.fineosDSL.test();
        }

        public void test(final Consumer<TestCaseContext> testScenario) {
            if (built) {
                Optional.ofNullable(testScenario).ifPresent(test -> test.accept(testCaseContext));
            } else {
                throw new AssertionError(FINEOS_DSL_MUST_BE_BUILT_FIRST);
            }
        }
    }

    /**
     * Loads data from the <code>ClaimDSL.csv</code> file for row with <code>CaseAliasValue</code> column value and
     * overrides column values with those supplied in the test case data prefixed by <code>csvHeaderPrefix</code>.
     */
    public final class ClaimDSL {
        private final List<BenefitDSL> benefits = new ArrayList<>();

        private final String csvHeaderPrefix;
        private final String caseAliasValue;
        private final NotificationDSL notification;
        private final TestCaseContextDSL testCaseContext;

        private Consumer<TestCaseContext> implementation;

        private String caseNumber;

        private ClaimDSL(final String csvHeaderPrefix, final NotificationDSL notification) {
            this.csvHeaderPrefix = csvHeaderPrefix;
            this.caseAliasValue = Optional.ofNullable(testCase.getValue(csvHeaderPrefix + CASE_ALIAS_VALUE))
                .orElseThrow(() -> new AssertionError(TEST_CASE_COLUMN_IS_MANDATORY + csvHeaderPrefix + CASE_ALIAS_VALUE));
            this.notification = notification;

            final Map<String, String> testData = new HashMap<>(claimDSLTestData.get(caseAliasValue));
            testData.put(CLAIM.getCaseAlias(), caseAliasValue);

            this.testCaseContext = buildTestCaseContext(csvHeaderPrefix, testData, notification.getTestCaseContext());

            withImplementation(CaseUtils::createClaim);
        }

        public String getCsvHeaderPrefix() {
            return csvHeaderPrefix;
        }

        public String getCaseAliasValue() {
            return caseAliasValue;
        }

        public String getCaseNumber() {
            return caseNumber;
        }

        public TestCaseContext getTestCaseContext() {
            return testCaseContext;
        }

        public NotificationDSL getNotification() {
            return notification;
        }

        public List<BenefitDSL> getBenefits() {
            return benefits;
        }


        /**
         * Replaces the default Claim creation implementation.
         *
         * Default implementation:
         * <pre>
         * CaseUtils::createClaim
         * </pre>
         *
         * @param implementation Claim creation implementation.
         *
         * @return <code>ClaimDSL</code> (itself)
         */
        public ClaimDSL withImplementation(final Consumer<TestCaseContext> implementation) {
            this.implementation = implementation;

            return this;
        }

        /**
         * Test step execute once the DSL has been built.
         *
         * @param testStepImpl Test step implementation.
         *
         * @return <code>ClaimDSL</code> (itself)
         */
        public ClaimDSL withTestStep(final Consumer<TestCaseContext> testStepImpl) {
            testSteps.add(new TestStep(this.testCaseContext, testStepImpl));

            return this;
        }

        /**
         * Adds a Benefit to the Claim.
         *
         * @param csvHeaderPrefix CSV header prefix to reference override columns in the test case data.
         *
         * @return <code>BenefitDSL</code> (Itself)
         */
        public BenefitDSL withBenefit(final String csvHeaderPrefix) {
            final BenefitDSL benefit = new BenefitDSL(csvHeaderPrefix, this);

            benefits.add(benefit);

            return benefit;
        }

        public NotificationDSL and() {
            return notification;
        }

        /**
         * Creates all test data in FINEOS.
         *
         * @return <code>FineosDSL</code>
         */
        public FineosDSL build() {
            return this.notification.build();
        }

        /**
         * Creates the Claim (and dependent) test data in FINEOS.
         */
        private void doBuild() {
            testCaseContext.getData().put(NOTIFICATION.getGeneratedCaseAlias(), notification.getCaseNumber());

            if (testCaseContext.getData().containsKey(EXISTING_CASE_NUMBER)) {
                testCaseContext.getData().put(CLAIM.getGeneratedCaseAlias(), testCaseContext.getData().get(EXISTING_CASE_NUMBER));
            } else {
                implementation.accept(testCaseContext);
            }

            caseNumber = testCaseContext.getValue(CLAIM.getGeneratedCaseAlias());

            benefits.forEach(BenefitDSL::doBuild);
        }

        /**
         * Executes test steps.
         */
        public void test() {
            this.notification.test();
        }

        public void test(final Consumer<TestCaseContext> testScenario) {
            if (built) {
                Optional.ofNullable(testScenario).ifPresent(test -> test.accept(testCaseContext));
            } else {
                throw new AssertionError(FINEOS_DSL_MUST_BE_BUILT_FIRST);
            }
        }
    }

    /**
     * Loads data from the <code>BenefitDSL.csv</code> file for row with <code>CaseAliasValue</code> column value
     * and overrides column values with those supplied in the test case data prefixed by <code>csvHeaderPrefix</code>.
     */
    public final class BenefitDSL {
        private final List<PayeeDSL> payees = new ArrayList<>();
        private final List<ReserveDSL> reserves = new ArrayList<>();
        private final List<PaymentDSL> payments = new ArrayList<>();

        private final String csvHeaderPrefix;
        private final String caseAliasValue;
        private final ClaimDSL claim;
        private final TestCaseContextDSL testCaseContext;

        private String caseNumber;

        private boolean reopenForExpensePayment;
        private boolean makeRecommendation;
        private boolean coverageAccepted;
        private boolean coverageDeclined;
        private boolean proceedWithPayment;
        private boolean preClosureCheck;
        private boolean settlementOfferApproved;
        private boolean uploadDocument;

        private boolean approveReserves;
        private boolean approvePayments;

        private Consumer<TestCaseContext> implementation;
        private Consumer<TestCaseContext> paymentApprovalImplementation;

        private BenefitDSL(final String csvHeaderPrefix, final ClaimDSL claim) {
            this.csvHeaderPrefix = csvHeaderPrefix;
            this.caseAliasValue = Optional.ofNullable(testCase.getValue(csvHeaderPrefix + CASE_ALIAS_VALUE))
                .orElseThrow(() -> new AssertionError(TEST_CASE_COLUMN_IS_MANDATORY + csvHeaderPrefix + CASE_ALIAS_VALUE));
            this.claim = claim;

            final Map<String, String> testData = new HashMap<>(requireNonNull(benefitDSLTestData.get(caseAliasValue),
                "Null value found for "  + csvHeaderPrefix + CASE_ALIAS_VALUE));
            testData.put(BENEFIT.getCaseAlias(), getCaseAliasValue());

            approveReserves = Boolean.parseBoolean(testCase.getValue(csvHeaderPrefix + "EXP_APP"));
            approvePayments = Boolean.parseBoolean(testCase.getValue(csvHeaderPrefix + "PAY_APP"));

            if ((approveReserves || settlementOfferApproved || approvePayments) && !testCase.getData().containsKey(USER_NAME_FULL_NAME)) {
                throw new AssertionError(TEST_CASE_COLUMN_IS_MANDATORY + USER_NAME_FULL_NAME);
            }

            reopenForExpensePayment = Boolean.parseBoolean(testCase.getValue(csvHeaderPrefix + REOPEN_FOR_EXPENSE_PAYMENT));
            makeRecommendation = Boolean.parseBoolean(testCase.getValue(csvHeaderPrefix + MAKE_RECOMMENDATION));
            coverageAccepted = Boolean.parseBoolean(testCase.getValue(csvHeaderPrefix + COVERAGE_ACCEPTED));
            coverageDeclined = Boolean.parseBoolean(testCase.getValue(csvHeaderPrefix + COVERAGE_DECLINED));
            settlementOfferApproved = Boolean.parseBoolean(testCase.getValue(csvHeaderPrefix + SETTLEMENT_OFFER_APPROVED));
            proceedWithPayment = Boolean.parseBoolean(testCase.getValue(csvHeaderPrefix + PROCEED_WITH_PAYMENT));
            preClosureCheck = Boolean.parseBoolean(testCase.getValue(csvHeaderPrefix + PRE_CLOSURE_CHECK));
            uploadDocument = Boolean.parseBoolean(testCase.getValue(csvHeaderPrefix + UPLOAD_DOCUMENT));


            if (!makeRecommendation && (coverageAccepted || coverageDeclined)) {
                throw new AssertionError(TEST_CASE_COLUMN_IS_MANDATORY + MAKE_RECOMMENDATION);
            }

            this.testCaseContext = buildTestCaseContext(csvHeaderPrefix, testData, claim.getTestCaseContext());

            withImplementation(BenefitUtils::createBenefit);
            withPaymentApprovalImplementation(BenefitUtils::approvePayments);
        }

        public String getCsvHeaderPrefix() {
            return csvHeaderPrefix;
        }

        public String getCaseAliasValue() {
            return caseAliasValue;
        }

        public String getCaseNumber() {
            return caseNumber;
        }

        public TestCaseContext getTestCaseContext() {
            return testCaseContext;
        }

        public NotificationDSL getNotification() {
            return claim.getNotification();
        }

        public ClaimDSL getClaim() {
            return claim;
        }

        /**
         * Return a list of all Payees.
         * @return List of <code>PayeeDSL</code>
         */
        public List<PayeeDSL> getPayees() {
            return payees;
        }

        /**
         * Return a Payee.
         *
         * @throws AssertionError if zero or more than one Payee exists
         * @return A <code>PayeeDSL</code>
         */
        public PayeeDSL getPayee() {
            return payees.stream().findFirst().orElseThrow(() -> new AssertionError("Zero or more than one Payee exists."));
        }

        /**
         * Return a list of all Reserves.
         * @return List of <code>ReserveDSL</code>
         */
        public List<ReserveDSL> getReserves() {
            return reserves;
        }

        /**
         * Return a Reserve.
         *
         * @throws AssertionError if zero or more than one Reserve exists
         * @return A <code>ReserveDSL</code>
         */
        public ReserveDSL getReserve() {
            return reserves.stream().findFirst().orElseThrow(() -> new AssertionError("Zero or more than one Reserve exists."));
        }

        /**
         * Return a list of all Payments.
         * @return List of <code>PaymentDSL</code>
         */
        public List<PaymentDSL> getPayments() {
            return payments;
        }

        /**
         * Return a Payment.
         *
         * @throws AssertionError if zero or more than one Payment exists
         * @return A <code>PaymentDSL</code>
         */
        public PaymentDSL getPayment() {
            return payments.stream().findFirst().orElseThrow(() -> new AssertionError("Zero or more than one Payment exists."));
        }

        /**
         * Return an Overpayment.
         *
         * @throws AssertionError if zero or more than one Overpayment exists
         * @return A <code>OverpaymentDSL</code>
         */
        public OverpaymentDSL getOverpayment() {
            return Optional.ofNullable(getPayment().getOverpayment()).orElseThrow(() -> new AssertionError("Zero or more than one Overpayment exists."));
        }

        public BenefitDSL withReopenForExpensePayment() {
            this.reopenForExpensePayment = true;

            return this;
        }

        public BenefitDSL withMakeRecommendation() {
            this.makeRecommendation = true;

            return this;
        }

        public BenefitDSL withCoverageAccepted() {
            withMakeRecommendation();

            this.coverageAccepted = true;

            return this;
        }

        public BenefitDSL withCoverageDeclined() {
            withMakeRecommendation();

            this.coverageDeclined = true;

            return this;
        }

        public BenefitDSL withSettlementOfferApproved() {
            this.settlementOfferApproved = true;

            return this;
        }

        public BenefitDSL withProceedWithPayment() {
            withCoverageAccepted();

            this.proceedWithPayment = true;

            return this;
        }

        public BenefitDSL withPreClosureCheck() {
            this.preClosureCheck = true;

            return this;
        }

        public BenefitDSL withDocumentUpload() {
            this.uploadDocument = true;

            return this;
        }

        public BenefitDSL withApproveReserves() {
            if (reserves.isEmpty()) {
                withReserve();
            }

            this.approveReserves = true;

            return this;
        }

        public BenefitDSL withApprovePayments() {
            if (payments.isEmpty()) {
                withPayment();
            }

            this.approvePayments = true;

            return this;
        }

        /**
         * Replaces the default Benefit creation implementation.
         *
         * Default implementation:
         * <pre>
         * BenefitUtils::createBenefit
         * </pre>
         *
         * @param implementation Benefit creation implementation.
         *
         * @return <code>BenefitDSL</code> (itself)
         */
        public BenefitDSL withImplementation(final Consumer<TestCaseContext> implementation) {
            this.implementation = implementation;

            return this;
        }

        /**
         * Replaces the default Payment approval implementation.
         *
         * Default implementation:
         * <pre>
         * BenefitUtils::approvePayments
         * </pre>
         *
         * @param paymentApprovalImplementation Payment approval implementation.
         *
         * @return <code>BenefitDSL</code> (itself)
         */
        public BenefitDSL withPaymentApprovalImplementation(final Consumer<TestCaseContext> paymentApprovalImplementation) {
            this.paymentApprovalImplementation = paymentApprovalImplementation;

            return this;
        }

        /**
         * Adds a Test step to execute once the DSL has been built.
         *
         * @param testStepImpl Test step implementation.
         *
         * @return <code>BenefitDSL</code> (itself)
         */
        public BenefitDSL withTestStep(final Consumer<TestCaseContext> testStepImpl) {
            testSteps.add(new TestStep(this.testCaseContext, testStepImpl));

            return this;
        }

        /**
         * Adds a Reserve to the Benefit.
         *
         * @return <code>ReserveDSL</code>
         */
        public ReserveDSL withReserve() {
            final ReserveDSL reserve = new ReserveDSL(null, this);

            reserves.add(reserve);

            testCaseContext.addChild(reserve.getTestCaseContext());

            return reserve;
        }

        /**
         * Adds a Reserve to the Benefit.
         *
         * @param csvHeaderPrefix CSV header prefix to reference override columns in the test case data.
         * @return <code>ReserveDSL</code>
         */
        public ReserveDSL withReserve(final String csvHeaderPrefix) {
            final ReserveDSL reserve = new ReserveDSL(csvHeaderPrefix, this);

            reserves.add(reserve);

            testCaseContext.addChild(reserve.getTestCaseContext());

            return reserve;
        }

        /**
         * Adds a Payee to the Benefit.
         *
         * @param csvHeaderPrefix CSV header prefix to reference override columns in the test case data.
         * @return <code>PayeeDSL</code>
         */
        public PayeeDSL withPayee(final String csvHeaderPrefix) {
            final PayeeDSL payee = new PayeeDSL(csvHeaderPrefix, this);

            payees.add(payee);

            testCaseContext.addChild(payee.getTestCaseContext());

            return payee;
        }

        /**
         * Adds a Person payee to the Benefit.
         *
         * @return <code>PayeeDSL</code>
         */
        public PayeeDSL withPersonPayee(final String firstName, final String lastName) {
            final PayeeDSL payee = new PayeeDSL("Person", firstName, lastName, this);

            payees.add(payee);

            testCaseContext.addChild(payee.getTestCaseContext());

            return payee;
        }

        /**
         * Adds an Organisation payee to the Benefit.
         *
         * @return <code>PayeeDSL</code>
         */
        public PayeeDSL withOrganisationPayee(final String orgName, final String shortName) {
            final PayeeDSL payee = new PayeeDSL("Organisation", orgName, shortName, this);

            payees.add(payee);

            testCaseContext.addChild(payee.getTestCaseContext());

            return payee;
        }

        /**
         * Adds a Payment to the Benefit.
         *
         * @return <code>PaymentDSL</code>
         */
        public PaymentDSL withPayment() {
            if (!approveReserves) {
                withApproveReserves();
            }

            final PaymentDSL payment = new PaymentDSL(null, this);

            payments.add(payment);

            testCaseContext.addChild(payment.getTestCaseContext());

            return payment;
        }

        /**
         * Adds a Payment to the Benefit.
         *
         * @param csvHeaderPrefix CSV header prefix to reference override columns in the test case data.
         * @return <code>PaymentDSL</code>
         */
        public PaymentDSL withPayment(final String csvHeaderPrefix) {
            final PaymentDSL payment = new PaymentDSL(csvHeaderPrefix, this);

            payments.add(payment);

            testCaseContext.addChild(payment.getTestCaseContext());

            return payment;
        }

        public ClaimDSL and() {
            return claim;
        }

        /**
         * Creates all test data in FINEOS.
         *
         * @return <code>FineosDSL</code>
         */
        public FineosDSL build() {
            return this.claim.build();
        }

        /**
         * Creates the Benefit (and dependent) test data in FINEOS.
         */
        private void doBuild() {
            testCaseContext.getData().put(NOTIFICATION.getGeneratedCaseAlias(), claim.getNotification().getCaseNumber());
            testCaseContext.getData().put(CLAIM.getGeneratedCaseAlias(), claim.getCaseNumber());

            if (testCaseContext.getData().containsKey(EXISTING_CASE_NUMBER)) {
                testCaseContext.getData().put(BENEFIT.getGeneratedCaseAlias(), testCaseContext.getValue(EXISTING_CASE_NUMBER));
                caseNumber = testCaseContext.getValue(BENEFIT.getGeneratedCaseAlias());
            } else {
                implementation.accept(testCaseContext);
                caseNumber = testCaseContext.getValue(BENEFIT.getGeneratedCaseAlias());

                payees.forEach(PayeeDSL::doBuild);

                if (uploadDocument) {
                    BenefitUtils.uploadInsurerApprovalDocument(testCaseContext);
                }

                if (reopenForExpensePayment) {
                    CaseUtils.reopenCaseForAdditionalExpensePayment(testCaseContext);
                }

                if (makeRecommendation) {
                    CaseUtils.moveCaseToCoverageDecision(testCaseContext);
                }

                reserves.forEach(ReserveDSL::doBuild);

                if (approveReserves && !reserves.isEmpty()) {
                    final Map<String, String> testData = new HashMap<>(reserveApproveDSLTestData.get(getCaseAliasValue()));

                    BenefitUtils.approveReserves(buildTestCaseContext(csvHeaderPrefix + "EXP_APP_", testData, getBenefit().getTestCaseContext()));
                }

                if (coverageAccepted) {
                    final Map<String, String> testData = new HashMap<>(coverageAcceptedDSLTestData.get(getCaseAliasValue()));

                    BenefitUtils.recommendCoverageAcceptedAndApprove(buildTestCaseContext(csvHeaderPrefix + "COV_ACC_", testData, getBenefit().getTestCaseContext()));
                }

                if (coverageDeclined) {
                    final Map<String, String> testData = new HashMap<>(coverageDeniedDSLTestData.get(getCaseAliasValue()));

                    BenefitUtils.recommendCoverageDeclinedAndApprove(buildTestCaseContext(csvHeaderPrefix + "COV_DEN_", testData, getBenefit().getTestCaseContext()));
                }

                if (settlementOfferApproved) {
                    final Map<String, String> testData = new HashMap<>(settlementOfferForApproveTestData.get(getCaseAliasValue()));

                    BenefitUtils.approveSettlementOffer(buildTestCaseContext(csvHeaderPrefix + "SO_", testData, getBenefit().getTestCaseContext()));
                }

                if (proceedWithPayment) {
                    BenefitUtils.moveBenefitToProceedWithPayment(testCaseContext);
                }

                if (preClosureCheck) {
                    CaseUtils.moveClaimToPreClosureCheck(testCaseContext);
                }
            }

            payments.forEach(PaymentDSL::doBuild);

            if (approvePayments) {
                final Map<String, String> testData = new HashMap<>(paymentApproveDSLTestData.get(getCaseAliasValue()));

                this.paymentApprovalImplementation.accept(buildTestCaseContext(csvHeaderPrefix + "PAY_APP_", testData, getBenefit().getTestCaseContext()));
            }
        }

        /**
         * Executes test steps.
         */
        public void test() {
            this.claim.test();
        }

        public void test(final Consumer<TestCaseContext> testScenario) {
            if (built) {
                Optional.ofNullable(testScenario).ifPresent(test -> test.accept(testCaseContext));
            } else {
                throw new AssertionError(FINEOS_DSL_MUST_BE_BUILT_FIRST);
            }
        }
    }

    /**
     * Loads data from the <code>PayeeDSL.csv</code> file for row with <code>BenefitCaseAlias</code> column value and
     * overrides column values with those supplied in the test case data prefixed by <code>csvHeaderPrefix</code>.
     */
    public final class PayeeDSL {
        private final BenefitDSL benefit;
        private final TestCaseContextDSL testCaseContext;

        private PayeeDSL(final String csvHeaderPrefix, final BenefitDSL benefit) {
            this.benefit = benefit;

            Assert.assertTrue(Arrays.asList("Person", "Organisation").contains(testCase.getValue(csvHeaderPrefix + PARTIES_TYPE)));
            Assert.assertNotNull(testCase.getValue(csvHeaderPrefix + FIRST_NAME));
            Assert.assertNotNull(testCase.getValue(csvHeaderPrefix + LAST_NAME));

            final Map<String, String> testData = new HashMap<>();
            testData.put(BENEFIT.getCaseAlias(), benefit.getCaseAliasValue());
            testData.put(PARTIES_TYPE, csvHeaderPrefix + PARTIES_TYPE);
            testData.put(FIRST_NAME, csvHeaderPrefix + FIRST_NAME);
            testData.put(LAST_NAME, csvHeaderPrefix + LAST_NAME);

            this.testCaseContext = buildTestCaseContext(csvHeaderPrefix, testData, getBenefit().getTestCaseContext());
        }

        private PayeeDSL(final String partyType, final String firstName, final String lastName, final BenefitDSL benefit) {
            this.benefit = benefit;

            final Map<String, String> testData = new HashMap<>();
            testData.put(BENEFIT.getCaseAlias(), benefit.getCaseAliasValue());
            testData.put(PARTIES_TYPE, partyType);
            testData.put(FIRST_NAME, firstName);
            testData.put(LAST_NAME, lastName);

            this.testCaseContext = buildTestCaseContext(null, testData, getBenefit().getTestCaseContext());
        }

        public TestCaseContext getTestCaseContext() {
            return testCaseContext;
        }

        public BenefitDSL getBenefit() {
            return benefit;
        }

        public BenefitDSL and() {
            return benefit;
        }

        /**
         * Creates all test data in FINEOS.
         *
         * @return <code>FineosDSL</code>
         */
        public FineosDSL build() {
            return this.benefit.build();
        }

        /**
         * Creates the Payee test data in FINEOS.
         */
        private void doBuild() {
            testCaseContext.getData().put(BENEFIT.getGeneratedCaseAlias(), benefit.getCaseNumber());

            PartyUtils.linkPayee(testCaseContext);
        }
    }

    /**
     * Loads data from the <code>ExpenseReserveDSL.csv</code> file for row with <code>BenefitCaseAlias</code> column value and
     * overrides column values with those supplied in the test case data prefixed by <code>csvHeaderPrefix</code>.
     */
    public final class ReserveDSL {
        private final BenefitDSL benefit;
        private final TestCaseContextDSL testCaseContext;

        private Consumer<TestCaseContext> implementation;

        private ReserveDSL(final String csvHeaderPrefix, final BenefitDSL benefit) {
            this.benefit = benefit;

            final Map<String, String> testData = new HashMap<>(reserveDSLTestData.get(benefit.getCaseAliasValue()));

            this.testCaseContext = buildTestCaseContext(csvHeaderPrefix, testData, getBenefit().getTestCaseContext());

            withImplementation(BenefitUtils::addReserves);
        }

        public TestCaseContext getTestCaseContext() {
            return testCaseContext;
        }

        public BenefitDSL getBenefit() {
            return benefit;
        }

        public ReserveDSL withImplementation(final Consumer<TestCaseContext> implementation) {
            this.implementation = implementation;

            return this;
        }

        /**
         * Test step method to execute once the DSL has been built.
         *
         * @param testStepImpl Test step implementation.
         *
         * @return <code>ReserveDSL</code> (itself)
         */
        public ReserveDSL withTestStep(final Consumer<TestCaseContext> testStepImpl) {
            testSteps.add(new TestStep(this.testCaseContext, testStepImpl));

            return this;
        }

        public BenefitDSL and() {
            return benefit;
        }

        /**
         * Creates all test data in FINEOS.
         *
         * @return <code>FineosDSL</code>
         */
        public FineosDSL build() {
            return this.benefit.build();
        }

        /**
         * Creates the Reserve test data in FINEOS.
         */
        private void doBuild() {
            testCaseContext.getData().put(NOTIFICATION.getGeneratedCaseAlias(), benefit.getClaim().getNotification().getCaseNumber());
            testCaseContext.getData().put(CLAIM.getGeneratedCaseAlias(), benefit.getClaim().getCaseNumber());
            testCaseContext.getData().put(BENEFIT.getGeneratedCaseAlias(), benefit.getCaseNumber());

            implementation.accept(testCaseContext);
        }

        /**
         * Executes test steps.
         */
        public void test() {
            this.benefit.test();
        }

        public void test(final Consumer<TestCaseContext> testScenario) {
            if (built) {
                Optional.ofNullable(testScenario).ifPresent(test -> test.accept(testCaseContext));
            } else {
                throw new AssertionError(FINEOS_DSL_MUST_BE_BUILT_FIRST);
            }
        }
    }

    /**
     * Loads data from the <code>PaymentDSL.csv</code> file for row with <code>BenefitCaseAlias</code> column value and
     * overrides column values with those supplied in the test case data prefixed by <code>csvHeaderPrefix</code>.
     */
    public final class PaymentDSL {
        private final String csvHeaderPrefix;
        private final BenefitDSL benefit;
        private final TestCaseContextDSL testCaseContext;

        private OverpaymentDSL overpayment = null;
        private Consumer<TestCaseContext> implementation;

        private PaymentDSL(final String csvHeaderPrefix, final BenefitDSL benefit) {
            this.csvHeaderPrefix = csvHeaderPrefix;
            this.benefit = benefit;

            final Map<String, String> testData = new HashMap<>(paymentDSLTestData.get(benefit.getCaseAliasValue()));

            this.testCaseContext = buildTestCaseContext(csvHeaderPrefix, testData, getBenefit().getTestCaseContext());

            if (Boolean.parseBoolean(testCase.getData().get(this.csvHeaderPrefix + "OVER"))
                || testCase.getData().keySet().stream().anyMatch(key -> key.startsWith(csvHeaderPrefix + "OVER_"))) {
                withOverpayment();
            }

            withImplementation(BenefitUtils::addPayment);
        }

        public TestCaseContext getTestCaseContext() {
            return testCaseContext;
        }

        public BenefitDSL getBenefit() {
            return benefit;
        }

        public OverpaymentDSL getOverpayment() {
            return overpayment;
        }

        public PaymentDSL withImplementation(final Consumer<TestCaseContext> implementation) {
            this.implementation = implementation;

            return this;
        }

        public PaymentDSL withOverpayment() {
            this.overpayment = new OverpaymentDSL(this.csvHeaderPrefix + "OVER_", this);

            testCaseContext.addChild(overpayment.getTestCaseContext());

            return this;
        }

        /**
         * Adds a Test step to execute once the DSL has been built.
         *
         * @param testStepImpl Test step implementation.
         *
         * @return <code>PaymentDSL</code> (itself)
         */
        public PaymentDSL withTestStep(final Consumer<TestCaseContext> testStepImpl) {
            testSteps.add(new TestStep(this.testCaseContext, testStepImpl));

            return this;
        }

        public BenefitDSL and() {
            return benefit;
        }

        /**
         * Creates all test data in FINEOS.
         *
         * @return <code>FineosDSL</code>
         */
        public FineosDSL build() {
            return this.benefit.build();
        }

        /**
         * Creates the Payment test data in FINEOS.
         */
        private void doBuild() {
            testCaseContext.getData().put(NOTIFICATION.getGeneratedCaseAlias(), benefit.getClaim().getNotification().getCaseNumber());
            testCaseContext.getData().put(CLAIM.getGeneratedCaseAlias(), benefit.getClaim().getCaseNumber());
            testCaseContext.getData().put(BENEFIT.getGeneratedCaseAlias(), benefit.getCaseNumber());

            implementation.accept(testCaseContext);
        }

        /**
         * Executes test steps.
         */
        public void test() {
            this.benefit.test();
        }

        public void test(final Consumer<TestCaseContext> testScenario) {
            if (built) {
                Optional.ofNullable(testScenario).ifPresent(test -> test.accept(testCaseContext));
            } else {
                throw new AssertionError(FINEOS_DSL_MUST_BE_BUILT_FIRST);
            }
        }
    }

    public final class OverpaymentDSL {
        private static final String OVERPAYMENT_LOADED_TASK = "Overpayment Loaded";

        private final PaymentDSL payment;
        private final TestCaseContext testCaseContext;

        private String caseNumber;

        private Consumer<TestCaseContext> implementation;

        private OverpaymentDSL(final String csvHeaderPrefix, final PaymentDSL payment) {
            this.payment = payment;

            final Map<String, String> testData = new HashMap<>(overpaymentDSLTestData.get(payment.getBenefit().getCaseAliasValue()));

            this.testCaseContext = buildTestCaseContext(csvHeaderPrefix, testData, getPayment().getTestCaseContext());

            withImplementation(this::addOverpayment);
        }

        public PaymentDSL getPayment() {
            return payment;
        }

        public String getCaseNumber() {
            return caseNumber;
        }

        public TestCaseContext getTestCaseContext() {
            return testCaseContext;
        }

        public OverpaymentDSL withImplementation(final Consumer<TestCaseContext> implementation) {
            this.implementation = implementation;

            return this;
        }

        /**
         * Adds a Test step to execute once the DSL has been built.
         *
         * @param testStepImpl Test step implementation.
         *
         * @return <code>OverpaymentDSL</code> (itself)
         */
        public OverpaymentDSL withTestStep(final Consumer<TestCaseContext> testStepImpl) {
            testSteps.add(new TestStep(this.testCaseContext, testStepImpl));

            return this;
        }

        private void addOverpayment(final TestCaseContext tc) {
            BenefitUtils.editPayment(tc);

            final DisplayCasePageBenefitBase displayCasePageBenefit = tc.getPage(DisplayCasePageBenefitBase.class);
            final DisplayCasePageBaseRequest displayCasePageBenefitRequest = new DisplayCasePageBaseRequest(tc);
            BenefitUtils.submitPaymentsForApproval(displayCasePageBenefit, tc);

            displayCasePageBenefit.clickTasksTab(displayCasePageBenefitRequest);
            assertTrue(displayCasePageBenefit.isTaskFound(displayCasePageBenefitRequest, OVERPAYMENT_LOADED_TASK));

            this.testCaseContext.getData().put(OVERPAYMENT.getGeneratedCaseAlias(), this.payment.getBenefit().getCaseNumber() + "-OP01");
        }

        /**
         * Creates all test data in FINEOS.
         *
         * @return <code>FineosDSL</code>
         */
        public FineosDSL build() {
            return this.payment.build();
        }

        /**
         * Creates the Overpayment test data in FINEOS.
         */
        private void doBuild() {
            testCaseContext.getData().put(NOTIFICATION.getGeneratedCaseAlias(), payment.getBenefit().getClaim().getNotification().getCaseNumber());
            testCaseContext.getData().put(CLAIM.getGeneratedCaseAlias(), payment.getBenefit().getClaim().getCaseNumber());
            testCaseContext.getData().put(BENEFIT.getGeneratedCaseAlias(), payment.getBenefit().getCaseNumber());

            implementation.accept(testCaseContext);

            this.caseNumber = testCaseContext.getValue(OVERPAYMENT.getGeneratedCaseAlias());
            this.payment.getTestCaseContext().getData().put(OVERPAYMENT.getGeneratedCaseAlias(), caseNumber);
        }

        /**
         * Executes test steps.
         */
        public void test() {
            this.payment.test();
        }

        public void test(final Consumer<TestCaseContext> testScenario) {
            if (built) {
                Optional.ofNullable(testScenario).ifPresent(test -> test.accept(testCaseContext));
            } else {
                throw new AssertionError(FINEOS_DSL_MUST_BE_BUILT_FIRST);
            }
        }
    }

    private static final class TestCaseContextDSL implements TestCaseContext {

        private final TestCaseContext parent;
        private final List<TestCaseContext> children = new ArrayList<>();
        private final TestCaseContext testCaseContext;
        private final Map<String, String> testData;

        private TestCaseContextDSL(final TestCaseContext testCaseContext, final Map<String, String> testData, final TestCaseContext parent) {
            this.parent = parent;
            this.testCaseContext = testCaseContext;
            this.testData = testData;
        }

        public void addChild(final TestCaseContext child) {
            children.add(child);
        }

        @Override
        public TestInstance getTestInstance() {
            return testCaseContext.getTestInstance();
        }

        @Override
        public boolean isTestInstance(final TestInstance testInstance) {
            return testCaseContext.isTestInstance(testInstance);
        }

        @Override
        public Map<String, String> getData() {
            return testData;
        }

        @Override
        public String getValue(final String key) {
            String result = testData.get(key);

            int i = 0;
            while (StringUtils.isBlank(result) && i < children.size()) {
                result = children.get(i++).getData().get(key);
            }

            if (StringUtils.isBlank(result)) {
                return parent.getValue(key);
            } else {
                return result;
            }
        }

        @Override
        public String getNotificationCaseNumber() {
            return getValue(NOTIFICATION.getGeneratedCaseAlias());
        }

        @Override
        public String getClaimCaseNumber() {
            return getValue(CLAIM.getGeneratedCaseAlias());
        }

        @Override
        public String getBenefitCaseNumber() {
            return getValue(BENEFIT.getGeneratedCaseAlias());
        }

        @Override
        public <T extends BasePage> T getPage(final Class<T> pageClass) {
            return testCaseContext.getPage(pageClass);
        }

        @Override
        public void log(final String action, final String message) {
            testCaseContext.log(action, message);
        }

        @Override
        public void skip(final String action, final String message) {
            testCaseContext.skip(action, message);
        }

        @Override
        public void error(final String action, final Exception exception) {
            testCaseContext.error(action, exception);
        }

        @Override
        public void error(final String action, final String message) {
            testCaseContext.error(action, message);
        }

        @Override
        public void warning(final String action, final String message, final boolean withScreenshot) {
            testCaseContext.warning(action, message, withScreenshot);
        }
    }
}
