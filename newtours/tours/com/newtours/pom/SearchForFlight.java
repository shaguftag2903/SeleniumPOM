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
import com.newtours.email.SendGmailWithAttachment;
import com.newtours.pages.FindAFlight;
import com.newtours.pages.FlightFinder;
import com.newtours.pages.LoginToNewTours;
import com.newtours.testData.Constants;

public class SearchForFlight extends UtilityClass {

	// create a log that will contain number of test cases
	List<String> log = new ArrayList<String>();
	// create an instance of PdfUtilityClass
	PDFResult pdfResult = new PDFResult();

	// Constructor
	public SearchForFlight() {
	}

	// TicketBookingTest
	@Test
	public void returnTicket() throws Exception {

		try {
			LoginToNewTours loginpage = PageFactory.initElements(driver, LoginToNewTours.class);
			loginpage.applicationLogin(Constants.userName1, Constants.password1);
			log.add("Step#1: New Tours Form page is displayed");
			log.add("Step#2:, Enter the UserName:" + Constants.userName1);
			log.add("Step#3: Enter the Password:" + Constants.password1);
			log.add("Step#4: Button is clicked successfully");

			FlightFinder flight = PageFactory.initElements(driver, FlightFinder.class);
			flight.filghtSelection();
			log.add("Step#5: departingFrom is:" + Constants.departingFrom);
			log.add("Step#6: departingOnMonth is:" + Constants.departMonth);
			log.add("Step#7: departingOnDay is:" + Constants.departDay);
			log.add("Step#8: Arriving in is:" + Constants.arrivingIn);
			log.add("Step#9: ReturningMonth is:" + Constants.returnMonth);
			log.add("Step#10: Returning Day is:" + Constants.returnDay);
			log.add("Step#11: Airline selected is:" + Constants.airline);
			log.add("Step#12: Continue button is clicked for booking the flight");

			FindAFlight select = PageFactory.initElements(driver, FindAFlight.class);
			select.findFlight();

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
		pdfResult.writeTestResultToPdfFile("ResultForSearchFlight.pdf", log);
		// call the sendGmail method
		SendGmailWithAttachment.sendGmail(obj.getProperty("userEmail"), obj.getProperty("emailPswd"));
		driver.quit();// quit the driver
	}
}
