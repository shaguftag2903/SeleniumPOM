package com.newtours.pom;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.newtours.config.UtilityClass;
import com.newtours.pages.LoginToNewTours;
import com.newtours.testData.Constants;

public class InvalidLoginForNewTours extends UtilityClass {

	// create a log that will contain number of test cases
	List<String> log = new ArrayList<String>();
	// create an instance of PdfUtilityClass
	PDFResult pdfResult = new PDFResult();

	public InvalidLoginForNewTours() {
	}

	// Login test
	@Test
	public void loginTest() throws TimeoutException, ElementNotVisibleException, Exception {
		try {
			String URL = driver.getCurrentUrl();

			LoginToNewTours loginpage = PageFactory.initElements(driver, LoginToNewTours.class);
			loginpage.applicationLogin(Constants.userName, Constants.password);
			UtilityClass.captureScreenShot(driver);
			log.add("1: New Tours Form page is displayed");
			log.add("2: Enter the UserName:" + Constants.userName);
			log.add("3: Enter the Password:" + Constants.password);
			Reporter.log("Button is clicked successfully");
			log.add("4: Failed to login, Incorrect credentials");
			Assert.assertNotEquals(URL, "verificationURL");

		} catch (TimeoutException e1) {
			Reporter.log("Timeout Exception::: Element not loaded");
			UtilityClass.captureScreenShot(driver);
		}

		catch (ElementNotVisibleException e2) {
			Reporter.log("ElementNotVisibleException Exception::: Element not available in the web page");
			UtilityClass.captureScreenShot(driver);
		}
	}

	@AfterMethod
	public void tearDown() throws COSVisitorException, IOException {
		// close the driver
		driver.close();
		// define a time stamp string to add to the test result
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		// add time stamp to the resultList
		log.add("Test Ends: " + timeStamp);
		// write the test result pdf file with file name LoginResult
		pdfResult.writeTestResultToPdfFile("ResultForInvalidLogin.pdf", log);
		// quit the driver
		driver.quit();

	}
}
