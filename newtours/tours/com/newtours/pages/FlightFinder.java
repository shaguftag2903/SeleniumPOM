package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.newtours.config.UtilityClass;

public class FlightFinder extends UtilityClass {

	@FindBy(xpath = "//select[@name='fromPort']")
	@CacheLookup
	private WebElement departFrom;

	@FindBy(xpath = "(//input[@name='tripType'])[2]")
	@CacheLookup
	private WebElement type;

	@FindBy(xpath = "//select[@name='fromMonth']")
	@CacheLookup
	private WebElement onMonth;

	@FindBy(xpath = "//select[@name='fromDay']")
	@CacheLookup
	private WebElement onDay;

	@FindBy(xpath = "//select[@name='toPort']")
	@CacheLookup
	private WebElement arrivingIN;

	@FindBy(xpath = "//select[@name='toMonth']")
	@CacheLookup
	private WebElement returningMonth;

	@FindBy(xpath = "//select[@name='toDay']")
	@CacheLookup
	private WebElement returningDay;

	@FindBy(xpath = "//input[@value='Business']")
	@CacheLookup
	private WebElement businessClass;

	@FindBy(name = "airline")
	@CacheLookup
	private WebElement flight;

	@FindBy(name = "findFlights")
	@CacheLookup
	private WebElement submit;

	public FlightFinder(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void filghtSelection() throws Exception {

		type.click();
		departFrom.click();
		onMonth.click();
		onDay.click();
		arrivingIN.click();
		returningMonth.click();
		returningDay.click();
		businessClass.click();
		Select drpDownFlight = new Select(flight);
		drpDownFlight.selectByIndex(2);
		UtilityClass.captureScreenShot(driver);
		submit.click();
	}

}
