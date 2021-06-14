package com.gb.fineos.domain;

import com.gb.fineos.factory.PropertiesFactory;

import java.util.Optional;

/**
 * Abstract Page Request domain object. Contains useful methods for testing of pages.
 * <p>
 * Extend this class in a specific 'page' implementation (e.g. com.gb.fineos.page.LoginPage.LoginPageRequest).
 */
public abstract class AbstractPageRequest implements PageRequest {
    private final TestCaseContext context;

    public AbstractPageRequest(final TestCaseContext context) {
        this.context = context;
    }

    public String getTestRunIdentifier() {
        return PropertiesFactory.getInstance().getProperties().getProperty("testRunIdentifier");
    }

    @Override
    public TestInstance getTestInstance() {
        return context.getTestInstance();
    }

    @Override
    public boolean isTestInstance(final TestInstance testInstance) {
        return context.isTestInstance(testInstance);
    }

    @Override
    public TestCaseContext getContext() {
        return context;
    }

    protected String get(final String key) {
        return Optional.ofNullable(context.getValue(key)).orElseThrow(() -> new IllegalArgumentException("Unable to find key: " + key + " in Test Case data."));
    }

    protected String getOrDefault(final String key, final String defaultValue) {
        return context.getData().getOrDefault(key, defaultValue);
    }

    public void log(final String action, final String message) {
        context.log(action, message);
    }

    public void warning(final String action, final String message, final boolean withScreenshot) {
        context.warning(action, message, withScreenshot);
    }

    public void error(final String action, final Exception exception) {
        context.error(action, exception);
    }

    public void error(final String action, final String message) {
        context.error(action, message);
    }
}
