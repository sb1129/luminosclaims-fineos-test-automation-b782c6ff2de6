package com.gb.fineos.page;

import com.gb.fineos.domain.TestCase;
import com.gb.fineos.page.LoginPage.LoginPageRequest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class LoginPageRequestTest {

    @Test
    public void testGetters() {
        final Map<String, String> data = new HashMap<>();
        data.put("UserName", "John_Smith");
        data.put("Password", "Password10");

        final LoginPageRequest loginPageRequest = new LoginPageRequest(new TestCase("LoginPageRequestTest", "LoginPageRequestTest", data, null, null, null));

        assertEquals(loginPageRequest.getUsername("UserName"), "John_Smith");
        assertEquals(loginPageRequest.getPassword("Password"), "Password10");
    }
}
