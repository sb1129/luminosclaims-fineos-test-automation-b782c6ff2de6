package com.gb.fineos.domain;

import java.util.stream.Stream;

public enum CaseType {
    NOTIFICATION("NotificationCaseAlias"),
    CLAIM("ClaimCaseAlias"),
    BENEFIT("BenefitCaseAlias"),
    OVERPAYMENT("OverpaymentCaseAlias");

    private final String caseAlias;

    CaseType(final String caseAlias) {
        this.caseAlias = caseAlias;
    }

    public String getCaseAlias() {
        return caseAlias;
    }

    public String getGeneratedCaseAlias() {
        return "Generated" + caseAlias;
    }

    public static CaseType valueOfCaseAlias(final String property) {
        return Stream.of(values()).filter(e -> e.caseAlias.equalsIgnoreCase(property)).findFirst().orElse(null);
    }
}
