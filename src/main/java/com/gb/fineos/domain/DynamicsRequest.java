package com.gb.fineos.domain;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DynamicsRequest {
    private final Date date;
    private final String accountNumber;
    private final String accountName;
    private final String caseNumber;
    private final BigDecimal amount;
    private final boolean caseNumberIncludedInReference;
    private final TestInstance testInstance;

    private DynamicsRequest(final Date date, final String accountNumber, final String accountName, final String caseNumber, final BigDecimal amount, final boolean caseNumberIncludedInReference, final TestInstance testInstance) {
        this.date = date;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.caseNumber = caseNumber;
        this.amount = amount;
        this.caseNumberIncludedInReference = caseNumberIncludedInReference;
        this.testInstance = testInstance;
    }

    public static DynamicsRequestBuilder builder() {
        return new DynamicsRequestBuilder();
    }

    public Date getDate() {
        return date;
    }

    public String getFormattedDate() {
        final DateFormat dateFormat = new SimpleDateFormat("yyMMdd");

        return dateFormat.format(date);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public String getAmount() {
        return amount.movePointRight(2).toPlainString();
    }

    public boolean isCaseNumberIncludedInReference() {
        return caseNumberIncludedInReference;
    }

    public TestInstance getTestInstance() {
        return testInstance;
    }

    public static final class DynamicsRequestBuilder {
        private Date date;
        private String accountNumber;
        private String accountName;
        private String caseNumber;
        private BigDecimal amount;
        private boolean caseNumberIncludedInReference;
        private TestInstance testInstance;

        private DynamicsRequestBuilder() {
            this.caseNumberIncludedInReference = true;
        }

        public DynamicsRequestBuilder date(final Date date) {
            this.date = date;
            return this;
        }

        public DynamicsRequestBuilder accountNumber(final String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public DynamicsRequestBuilder accountName(final String accountName) {
            this.accountName = accountName;
            return this;
        }

        public DynamicsRequestBuilder caseNumber(final String caseNumber) {
            this.caseNumber = caseNumber;
            return this;
        }

        public DynamicsRequestBuilder amount(final BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public DynamicsRequestBuilder caseNumberIncludedInReference(final boolean caseNumberIncludedInReference) {
            this.caseNumberIncludedInReference = caseNumberIncludedInReference;
            return this;
        }

        public DynamicsRequestBuilder testInstance(final TestInstance testInstance) {
            this.testInstance = testInstance;
            return this;
        }

        public DynamicsRequest build() {
            return new DynamicsRequest(date, accountNumber, accountName, caseNumber, amount, caseNumberIncludedInReference, testInstance);
        }
    }
}
