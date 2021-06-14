package com.gb.fineos.domain;

import com.gb.fineos.page.BasePage;

import java.util.Map;

public interface TestCaseContext {

    TestInstance getTestInstance();

    boolean isTestInstance(TestInstance testInstance);

    Map<String, String> getData();

    String getValue(String key);

    String getNotificationCaseNumber();

    String getClaimCaseNumber();

    String getBenefitCaseNumber();

    <T extends BasePage> T getPage(Class<T> pageClass);

    void log(String action, String message);

    void skip(String action, String message);

    void error(String action, Exception exception);

    void error(String action, String message);

    void warning(String action, String message, boolean withScreenshot);
}
