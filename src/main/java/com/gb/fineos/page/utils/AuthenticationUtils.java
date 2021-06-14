package com.gb.fineos.page.utils;

import com.gb.fineos.domain.TestCaseContext;
import com.gb.fineos.page.DashboardPage;
import com.gb.fineos.page.DashboardPage.DashboardPageRequest;
import com.gb.fineos.page.LoginPage;
import com.gb.fineos.page.LoginPage.LoginPageRequest;

public final class AuthenticationUtils {

    private static final String FULL_NAME = "FullName";

    private AuthenticationUtils() {
        // Do nothing.
    }

    public static void loginAsUser(final TestCaseContext tc, final String userName) {
        final DashboardPage dashboardPage = tc.getPage(DashboardPage.class);
        final DashboardPageRequest dashboardPageRequest = new DashboardPageRequest(tc);

        if (dashboardPage.verifyCurrentUser(dashboardPageRequest, tc.getValue(userName + FULL_NAME))) {
            final LoginPage loginPage = tc.getPage(LoginPage.class);
            final LoginPageRequest loginPageRequest = new LoginPageRequest(tc);
            loginPage.clickOnReLoginButton(loginPageRequest);
            loginPage.doLogin(loginPageRequest, userName);
        }
    }
}
