package com.gb.fineos.script.auth;

import com.gb.fineos.provider.CSVDataProvider;
import com.gb.fineos.script.utils.BaseTest;
import org.testng.annotations.Test;

import java.util.Map;

/*
 * Class Name : LoginTest
 * Description : Flow for logging into the application
 */
public class LoginTest extends BaseTest {

	/*
	 * Method Name : loginTest
	 * Description : Flow for logging into the application
	 */
	@Test(dataProviderClass = CSVDataProvider.class, dataProvider = "csvDataProvider", groups = "auth")
	public void loginAuthTest(final Map<String, String> testData) {
		doTest("loginAuthTest", "Successful login and logout scenario", testData, tc -> {});
	}
}
