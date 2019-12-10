package com.newtours.Screenshots;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class UtilityClass {

	public static WebDriver driver;
	public static WebDriverWait wait;
	protected String baseUrl = "http://newtours.demoaut.com";
	protected Properties obj = new Properties();

	public UtilityClass() {

	}

	/*
	 * Setting of Properties for Before Method Open Browser, Enter URL & Maximize
	 * Window
	 */
	@BeforeMethod
	public void setProperty() throws Exception {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "\\chromedriver\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 15);
		FileInputStream objFile = new FileInputStream(
				System.getProperty("user.dir") + "\\tours\\com\\mindtree\\newtours\\testData\\Properties.config");
		obj.load(objFile);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get(baseUrl);
		driver.manage().window().maximize();

	}

//	  Close Browser After MEthod

	@AfterMethod
	public void closeBrowser() throws Exception {
		if (driver != null) {
			driver.quit();
		}
	}
	

	public static void captureScreenShot(WebDriver driver) throws Exception {

		try {
			EventFiringWebDriver eDriver = new EventFiringWebDriver(driver);
			File srcFile = eDriver.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "\\tours\\com\\mindtree\\newtours\\Screenshots\\"+ System.currentTimeMillis() + ".png"));
					
		} catch (IOException e) {
			Reporter.log(e.getMessage());
		}

	}

}
