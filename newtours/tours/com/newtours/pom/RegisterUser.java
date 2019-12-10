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
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.newtours.config.UtilityClass;
import com.newtours.pages.RegisteringUser;
import com.newtours.testData.Constants;

public class RegisterUser extends UtilityClass {

	// create a log that will contain number of test cases
	List<String> log = new ArrayList<String>();
	// create an instance of PdfUtilityClass
	PDFResult pdfResult = new PDFResult();

	// Constructor
	public RegisterUser() {
	}

	// Register User
	@Test
	public void registerUser() throws Exception {

		try {
			RegisteringUser ruser = PageFactory.initElements(driver, RegisteringUser.class);
			
			ruser.personalDetails(Constants.newFirstName, Constants.newLastName, Constants.phone, Constants.registerEmail);
			log.add("Step#1: Enter the FirstName: " + Constants.newFirstName);
			log.add("Step#2: Enter the LastName: " + Constants.newLastName);
			log.add("Step#3: Enter the Phone: " + Constants.phone);
			log.add("Step#4: Enter the register Email: " + Constants.registerEmail);
			
			ruser.address(Constants.address1, Constants.city, Constants.state, Constants.postalCode);
			log.add("Step#5: Enter the Address: " + Constants.address1);
			log.add("Step#6: Enter the City: " + Constants.city);
			log.add("Step#7: Enter the state: " + Constants.state);
			log.add("Step#8: Enter the PostalCode: " + Constants.postalCode);
			
			ruser.newUserDetails(Constants.newUserName, Constants.newPassword, Constants.confirmPassword);
			log.add("Step#9: Enter the UserName: " + Constants.newUserName);
			log.add("Step#10: Enter the Password: " + Constants.newPassword);
			log.add("Step#11: Enter the Confirm Password: " + Constants.confirmPassword);
		}
		catch (TimeoutException e1) {
			Reporter.log("Timeout Exception::: Element not loaded");
			UtilityClass.captureScreenShot(driver);
		} catch (ElementNotVisibleException e2) {
			Reporter.log("ElementNotVisibleException Exception::: Element not available in the web page");
			UtilityClass.captureScreenShot(driver);
		}
	}

	@AfterMethod
	public void tearDown() throws COSVisitorException, IOException {
		driver.close();// close the driver
		// define a time stamp string to add to the test result
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		// add time stamp to the resultList
		log.add("Test Ends: " + timeStamp);
		// write the test result pdf file with file name ReturnTicketResult
		pdfResult.writeTestResultToPdfFile("ResultForRegisterUser.pdf", log);
	}
}
