package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.newtours.config.UtilityClass;

public class SearchAndBookFlight extends UtilityClass {

	@FindBy(name = "passFirst0")
	@CacheLookup
	private WebElement firstName;

	@FindBy(name = "passLast0")
	@CacheLookup
	private WebElement lastName;

	@FindBy(name = "creditnumber")
	@CacheLookup
	private WebElement creditCardInfo;

	@FindBy(name = "buyFlights")
	@CacheLookup
	private WebElement submit;

	public SearchAndBookFlight(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void flightBooking(String fName, String lName, String cNumber) throws Exception {
		firstName.sendKeys(fName);
		lastName.sendKeys(lName);
		creditCardInfo.sendKeys(cNumber);
		UtilityClass.captureScreenShot(driver);
		submit.click();

	}

}
