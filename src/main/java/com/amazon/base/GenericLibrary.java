package com.amazon.base;

import java.io.BufferedReader;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.aventstack.extentreports.model.Log;

//import com.ncd.pages.LoginPage;
//import com.ncd.utils.Log;

import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import jxl.read.biff.BiffException;

/*
 * Class : GenericLibrary 
 * Description: Contains Generic methods for the Framework
 * Date Created: 17-July-2020 
 * Author: Madhuri Pavani
 */

public class GenericLibrary extends Driver {

	public static String DEVICENAME, DEVICEVERSION;
	public static HSSFWorkbook workbook;
	public static HSSFSheet worksheet;
	public static DataFormatter formatter = new DataFormatter();
	public static String file_location = System.getProperty("user.dir") + "/src/main/java/com/amazon"
			+ "/utils/TestData.xls";

	public static int SIZE, COUNT = 0;
	public static String REPORTIN = "", TESTCASENAME, APPLICATIONNAME, PLATFORMNAME, PLATFORMVERSION, APPPACKAGE,
			APPACTIVITY, VERSION, ENVIRONMENT, SETUPTEXT, INSTALLEMCMOBILE;
	public static int PASSCOUNT = 0, FAILCOUNT = 0;
	public static long TOTALTIME = 0;

	public static int step = 1, j = 0;

	public static float FLOATTOTALTIME = 0;
	public static AndroidDriver<MobileElement> androidDriver;


	// Function Name : getDeviceName
	// Description : Returns Device Name of connected Device
	// Number Of Parameters : NA
	// Parameter Value : NA
	// Return Value : Device Model
	//Date Created: 17-July-2020 
	// Author: Madhuri Pavani

	public static String getDeviceName() {

		String command = "adb devices -l";
		Runtime rt = Runtime.getRuntime();
		try {
			Process proc = rt.exec(command);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String line = stdInput.readLine();
			int i = 0;
			String deviceModel = "";
			while (line != null && !line.equals("")) {
				if (line.contains("List of devices attached")) {
					String[] deviceProps = stdInput.readLine().split("model:");
					deviceProps = deviceProps[1].split(" ");
					deviceModel = deviceProps[0].replace("model:", "").trim();
					break;
				}
				line = stdInput.readLine();
				i++;
			}
			if (!deviceModel.equals("")) {
				return deviceModel;
			} else {
				return "UnKnown";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "UnKnown";
	}

	// Function Name : getDeviceVersion
	// Description : Returns Device Version of connected Device
	// Number Of Parameters : NA
	// Parameter Value : NA
	// Return Value : Device Version
	//Date Created: 17-July-2020 
	// Author: Madhuri Pavani

	public static String getDeviceVersion() {
		//Log.info("method to returns Device Version by Executing adb command");
		String command = "adb shell getprop ro.build.version.release";
		Runtime rt = Runtime.getRuntime();
		try {
			Process proc = rt.exec(command);
			BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
			String deviceVersion = stdInput.readLine();
			return deviceVersion;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static Object[][] getTestData(String sheetName) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(file_location);
		workbook = new HSSFWorkbook(fileInputStream);
		worksheet = workbook.getSheet(sheetName);
		HSSFRow Row = worksheet.getRow(0);
		int RowNum = worksheet.getPhysicalNumberOfRows();
		int ColNum = Row.getLastCellNum();
		Object Data[][] = new Object[RowNum - 1][ColNum];
		for (int i = 0; i < RowNum - 1; i++) {
			HSSFRow row = worksheet.getRow(i + 1);
			for (int j = 0; j < ColNum; j++) {
				if (row == null)
					Data[i][j] = "";
				else {
					HSSFCell cell = row.getCell(j);
					if (cell == null)
						Data[i][j] = "";
					else {
						String value = formatter.formatCellValue(cell);
						Data[i][j] = value;
					}
				}
			}
		}
		return Data;
	}

	public static void objectClick(AndroidDriver<MobileElement> driver, By element, String btnName)
			throws NoSuchElementException, IOException, BiffException {
		try {
			startTime = System.currentTimeMillis();
			verifyBrowserObjectExist(driver, element, 50);
			driver.findElement(element).click();
			endTime = System.currentTimeMillis();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	public static boolean verifyBrowserObjectExist(WebDriver driver, By element, int waitingTime) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitingTime);
			wait.until(ExpectedConditions.presenceOfElementLocated(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void enterText(AndroidDriver<MobileElement> driver, By element, String value)
			throws NoSuchElementException, IOException, BiffException {
		try {
			startTime = System.currentTimeMillis();
			verifyObjectExists(driver, element);
			driver.findElement(element).sendKeys(value);
			endTime = System.currentTimeMillis();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}
	
	// Function Name: verifyObjectExists(AndroidDriver<MobileElement> driver, By
		// element)
		// Description: verify the object is exist or not by waiting until the
		// object exist or maximum time exceed
		// Date Created:18-July-2020
		// Number Of Parameters: 2
		// Parameter Value: Driver object, Object Locator
		// Return Value: true or false
		// Date Modified: NA
		// Author: Madhuri Pavani

		public static boolean verifyObjectExists(AndroidDriver<MobileElement> driver, By element) {
			try {
				WebDriverWait wait = new WebDriverWait(driver, 60);
				wait.until(ExpectedConditions.presenceOfElementLocated(element));
				return driver.findElement(element).isDisplayed();
			} catch (Exception e) {
				return false;
			}
		}
		// Function Name: swipeTheScreen(AndroidDriver<MobileElement> driver, String
		// angle)
		// Description: swipe the object right to left side
		// Date Created: 18-July-2020
		// Number Of Parameters: 2
		// Parameter Value: Driver object, Object angle
		// Return Value: NA
		// Author: Madhuri Pavani

		public static void swipeTheScreen(AndroidDriver<MobileElement> driver, String angle)
				throws BiffException, IOException {
			try {
				Dimension dimensions = driver.manage().window().getSize();
				// System.out.println("Screen dimensions " + dimensions);
				if (angle.contains("Horizontal")) {
					int startX = (int) (dimensions.getWidth() * (0.8));
					int endX = (int) (dimensions.getWidth() * (0.2));
					int startY = (int) (dimensions.getHeight() * (0.5));
					int endY = startY;
					if (angle.equals("HorizontalRightToLeft")) {
						TouchAction action = new TouchAction(driver);
						action.press(PointOption.point(startX, startY))
								.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1300)))
								.moveTo(PointOption.point(endX, endY)).release().perform();
					} else if (angle.equals("HorizontalLeftToRight")) {
						TouchAction action = new TouchAction(driver);
						action.press(PointOption.point(startX, startY))
								.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1300)))
								.moveTo(PointOption.point(endX, endY)).release().perform();
					}
				} else {
					int startY = (int) (dimensions.getHeight() * (0.8));
					int endY = (int) (dimensions.getHeight() * (0.3));
					int startX = (int) (dimensions.getWidth() * (0.5));
					int endX = startX;
					if (angle.equals("VerticalBottomToTop")) {
						TouchAction action = new TouchAction(driver);
						action.press(PointOption.point(startX, startY))
								.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1300)))
								.moveTo(PointOption.point(endX, endY)).release().perform();
					} else if (angle.equals("VerticalTopToBottom")) {
						TouchAction action = new TouchAction(driver);
						action.press(PointOption.point(startX, startY))
								.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1300)))
								.moveTo(PointOption.point(endY, endX)).release().perform();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}


}
