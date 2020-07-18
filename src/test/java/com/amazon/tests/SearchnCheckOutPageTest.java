package com.amazon.tests;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.amazon.base.Driver;
import com.amazon.base.GenericLibrary;
import com.amazon.pages.SearchnCheckOutPage;
import com.amazon.pages.LoginPage;

import jxl.read.biff.BiffException;
import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.NoSuchElementException;

/*
 * Class : SearchnCheckOutTest class 
 * Description: Contains tests for search and Checkout functionality 
 * Date Created: 18-July_2020 
 * Author: Madhuri Pavani
 */

public class SearchnCheckOutPageTest extends Driver {

	public SearchnCheckOutPageTest() throws MalformedURLException {
		super();
	}
	
	// Initialization of Driver & LoginPage class
	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		initialization();
		lp = new LoginPage();
		hp = new SearchnCheckOutPage();
	}
		
		
	@DataProvider
	public Object[][] loginCred(Method method) throws IOException {
		Object data[][] = GenericLibrary.getTestData("LoginPage");
		return data;
	}

	@DataProvider
	public Object[][] hmpgdata(Method method) throws IOException {
		Object data[][] = GenericLibrary.getTestData("SearchnCheckOutPage");
		return data;
	}

	// Function Name: loginWithValidCredentials(String pwd,String verifcode)
	// Description: Amazon login with Valid e-mail & Valid password
	// Date Created: 18-July-2020
	// Number Of Parameters: 2
	// Parameter Value: String userName,String pwd
	// Return Value: NA
	// Date Modified: NA
	// Author: Madhuri Pavani
	
	@Test(priority = 1, dataProvider = "loginCred")
	public void loginWithValidCredentials(String userName,String pwd)
			throws InvalidFormatException, Exception {
		
	//	lp.signInDeviceClick();
	//	lp.signInalreadyExistsDeviceClick();
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

	@Test(priority = 2, dataProvider = "hmpgdata")
	public void searchfnctionlty(String searchKey)
			throws Exception {
	
		hp.searchKeyEntry(searchKey);
	
	}

	// Close Driver
	@AfterClass
	public void afterClass() {
	driver.quit();
	}

}
