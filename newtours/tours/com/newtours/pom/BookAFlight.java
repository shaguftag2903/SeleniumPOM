package com.newtours.pom;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.pdfbox.exceptions.COSVisitorException;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.newtours.config.UtilityClass;
import com.newtours.email.SendGmailWithAttachment;
import com.newtours.pages.FindAFlight;
import com.newtours.pages.FlightFinder;
import com.newtours.pages.LoginToNewTours;
import com.newtours.pages.SearchAndBookFlight;
import com.newtours.testData.Constants;

public class BookAFlight extends UtilityClass {

	// create a log that will contain number of test cases
	List<String> log = new ArrayList<String>();
	// create an instance of PdfUtilityClass
	PDFResult pdfResult = new PDFResult();

	// Constructor
	public BookAFlight() {
	}

	// TicketBookingTest
	@Test
	public void bookTicket() throws Exception {

		try {
			LoginToNewTours loginpage = PageFactory.initElements(driver, LoginToNewTours.class);
			loginpage.applicationLogin(Constants.userName1, Constants.password1);
			UtilityClass.captureScreenShot(driver);
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

			SearchAndBookFlight book = PageFactory.initElements(driver, SearchAndBookFlight.class);
			book.flightBooking(Constants.firstName, Constants.lastName, Constants.number);
			log.add("Step#13: Enter the FirstName: " + Constants.firstName);
			log.add("Step#14: Enter the LastName: " + Constants.lastName);
			log.add("Step#15: Enter the contact number: " + Constants.number);
			Reporter.log("Ticket is purchased");
			log.add("Step#16: Ticket is purchased");

			Assert.assertTrue(driver.findElement(By.xpath(obj.getProperty("confirmationMessage"))).getText()
					.equalsIgnoreCase(Constants.confirmationMessage));
			log.add("Step#17: Your itinerary has been booked!!!!!!");
			log.add("Step#18: Successfully completed capturing of Test data");
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
		pdfResult.writeTestResultToPdfFile("ResultForBookATicket.pdf", log);
// call the sendGmail method
		SendGmailWithAttachment.sendGmail("validAddress@gmail.com", "Password@123");
//		SendGmailWithAttachment.sendGmail(obj.getProperty("userEmail"), obj.getProperty("emailPswd"));
// quit the driver
		driver.quit();
	}
}
