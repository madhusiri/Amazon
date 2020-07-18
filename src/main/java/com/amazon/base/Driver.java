package com.amazon.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;


import org.openqa.selenium.remote.DesiredCapabilities;

import com.amazon.pages.SearchnCheckOutPage;
import com.amazon.pages.LoginPage;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class Driver 
{
    

/*
 * Class : Driver 
 * Description: Contains Android Driver Initialization and Properties file reading.
 * Date Created: 17-July-2020 
 * Author: Madhuri Pavani
 */


	public static DesiredCapabilities capabilities;
	public static AndroidDriver<MobileElement> driver;
	public static Properties prop;
	public static String DEVICENAME, DEVICEVERSION, VERSION;
	public static long startTime, endTime;
	
 // Object Creation for Pages
	
	public static LoginPage lp;
	public static SearchnCheckOutPage hp;
 	

 	
  //  Function Name : Driver Constructor
  //  Description : Initialization of Driver, Reading Property file.
  //  Date Created :  17-July-2020 
  //  Number Of Parameters : NA 
  //  Parameter Value : NA 
  //  Return Value : NA 
  //  Date Modified : NA 
  //  Author : Madhuri Pavani

	public Driver() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/main/java/com/amazon" + "/base/generic.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	  //  Function Name : initialization 
	  //  Description : Android Server start with adding capabilities 
	  //  Date Created : 17-July-2020
	  //  Number Of Parameters : NA 
	  //  Parameter Value : NA 
	  //  Return Value : NA 
	  //  Date Modified : NA 
	  //  Author : Madhuri Pavani

	public static void initialization() throws MalformedURLException {
		
		startTime = System.currentTimeMillis();
		capabilities = new DesiredCapabilities();
		DEVICENAME = GenericLibrary.getDeviceName();
		DEVICEVERSION = GenericLibrary.getDeviceVersion();
		capabilities.setCapability("deviceName", DEVICENAME);
		capabilities.setCapability("noReset", "true");
		capabilities.setCapability("version", DEVICEVERSION);
		capabilities.setCapability("clearSystemFiles", true);
		capabilities.setCapability("platformName", prop.getProperty("platformName"));
		capabilities.setCapability("appPackage", prop.getProperty("appPackage"));
		capabilities.setCapability("appActivity", prop.getProperty("appActivity"));
		driver = new AndroidDriver<MobileElement>(new URL(prop.getProperty("url")), capabilities);
		endTime = System.currentTimeMillis();
	}




}