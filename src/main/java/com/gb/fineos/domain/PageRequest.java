package com.gb.fineos.domain;

public interface PageRequest {
    String getTestRunIdentifier();

    TestInstance getTestInstance();

    boolean isTestInstance(TestInstance testInstance);

    TestCaseContext getContext();

    void log(String action, String message);
}
