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
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import junit.framework.Assert;
import jxl.read.biff.BiffException;

/*
 * Class : HomePage class 
 * Description: Contains Page Objects and methods for Home page by PageFactory Implementation
 * Date Created: 17-jul-2020
 * Author: Madhuri Pavani
 */

public class SearchnCheckOutPage extends Driver {

	//private By login_btn = By.id("com.amazon.mShop.android.shopping:id/sign_in_button");
	
	private By search_btn = By.id("com.amazon.mShop.android.shopping:id/rs_search_src_text");
	private By edt_userName = By.id("ap_email_login");
	private By edt_pwd = By.id("ap_password");
	private By btn_login = By.id("signInSubmit");
	private By cart_btn = By.id("add-to-cart-button");
	private By search_itm = By.id("com.amazon.mShop.android.shopping:id/item_title");
	

	
	// Constructor to initialize Home Page Objects
	public SearchnCheckOutPage() {
		
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	@SuppressWarnings("deprecation")
	public void searchKeyEntry(String searchKey) throws NoSuchElementException, BiffException, IOException, Exception{
		
		GenericLibrary.enterText(driver, search_btn, "65 inch tv");
		driver.pressKeyCode(66);
		GenericLibrary.verifyObjectExists(driver, search_itm);
		driver.findElement(By.id("com.amazon.mShop.android.shopping:id/item_title")).click();
		Thread.sleep(2000);
		GenericLibrary.swipeTheScreen(driver, "VerticalBottomToTop");
		GenericLibrary.verifyObjectExists(driver, cart_btn);
		GenericLibrary.swipeTheScreen(driver, "VerticalBottomToTop");
		driver.findElement(By.id("add-to-cart-button")).click();
		driver.findElement(By.id("com.amazon.mShop.android.shopping:id/chrome_action_bar_cart_image")).click();
		driver.findElement(By.className("android.widget.Button")).click();
		//driver.findElement(By.id("a-autoid-3-announce")).click();
		//driver.hideKeyboard();
	}
	
	public void verifyCheckedProd() {
		
		driver.findElement(By.className("android.widget.Button")).click();
		
	}
	
	


}
