package com.amazon.pages;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import com.amazon.base.Driver;
import com.amazon.base.GenericLibrary;
import com.amazon.utils.ExcelLibrary;
import com.aventstack.extentreports.model.Log;

//import com.amazon.utils.Log;
//import com.amazon.base.CommonObjects;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import junit.framework.Assert;
import jxl.read.biff.BiffException;

/*
 * Class : LoginPage class 
 * Description: Contains Page Objects and methods for Amazon Login page by PageFactory Implementation
 * Date Created: 17-jul-2020
 * Author: Madhuri Pavani
 */

public class LoginPage extends Driver {

	//private By login_btn = By.id("com.amazon.mShop.android.shopping:id/sign_in_button");
	
	//private By sign_btn = By.id("gwm-Deck-btf");
	private By edt_userName = By.id("ap_email_login");
	private By edt_pwd = By.id("ap_password");
	private By btn_login = By.id("signInSubmit");



	// Constructor to initialize Login Page Objects
	public LoginPage() {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	
	public void signInalreadyExistsDeviceClick() throws NoSuchElementException, BiffException, IOException {
		
		driver.findElement(By.id("com.amazon.mShop.android.shopping:id/sign_in_button")).click();
	}
	
	public void signInDeviceClick() throws NoSuchElementException {
	
		
		driver.findElement(By.id("gwm-Deck-btf")).click();
	}


	
	public void userNameEntry(String userName) throws NoSuchElementException, BiffException, IOException, Exception, InvalidFormatException{
		
		GenericLibrary.enterText(driver, edt_userName, "madhuripavani@gmail.com");
		System.out.println(edt_userName);
		driver.hideKeyboard();
	}
	public void passwordEntry(String pwd) throws NoSuchElementException, BiffException, IOException {
		
		GenericLibrary.enterText(driver, edt_pwd, "M@dhuri1986");
		driver.hideKeyboard();
	}

	public void contClick() {
		
		driver.findElement(By.id("continue")).click();
	}

	public void logInAmazon() throws NoSuchElementException, BiffException, IOException {
		
		GenericLibrary.objectClick(driver, btn_login, "Login");
	}

	
	
	


}
