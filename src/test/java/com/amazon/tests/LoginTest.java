package com.amazon.tests;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.amazon.base.Driver;
import com.amazon.base.GenericLibrary;
import com.amazon.pages.LoginPage;

import jxl.read.biff.BiffException;
import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.NoSuchElementException;

/*
 * Class : LoginTest class 
 * Description: Contains Test class for Positive, Negative, Validation Scenarios of Login page with Label validation
 * Date Created: 17-July-2020 
 * Author: Madhuri Pavani
 */

public class LoginTest extends Driver {

	public LoginTest() throws MalformedURLException {
		super();
	}

	// Function Name: LoginCred(Method method)
	// Description: DataProvider method for Login page objects
	// Number Of Parameters: NA
	// Parameter Value: NA
	// Return Value: data
	// Date Modified: NA
	//  Date Created: 17-July-2020 
	 // Author: Madhuri Pavani
	
	@DataProvider
	public Object[][] loginCred(Method method) throws IOException {
		Object data[][] = GenericLibrary.getTestData("LoginPage");
		return data;
	}

	// Initialization of Driver & LoginPage class
	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		initialization();
		lp = new LoginPage();
	}
	

	// Function Name: loginWithValidCredentials((String userName,String pwd)
	// Description: Amazon login with Valid credentials
	// Date Created: 15-Apr-2019
	// Number Of Parameters: 2
	// Parameter Value: String pwd, String verifcode
	// Return Value: NA
    //  Date Created: 17-July-2020 
    // Author: Madhuri Pavani

	@Test(priority = 1, dataProvider="loginCred")
	public void loginWithValidCredentials(String userName,String pwd)
			throws InvalidFormatException, Exception {
		
		//lp.signInDeviceClick();
		//lp.signInalreadyExistsDeviceClick();
		driver.findElement(By.id("com.amazon.mShop.android.shopping:id/sign_in_button")).click();
		lp.userNameEntry(userName);
		lp.contClick();
		lp.passwordEntry(pwd);
		lp.logInAmazon();
		
		/*try {
			assertEquals(true, lp.homePageDisplaysVerification());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		Log.endTestCase("loginWithValidCredentials");*/
	}

	// Close Driver
	@AfterClass
	public void afterClass() {
	driver.quit();
	}

}
