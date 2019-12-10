package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FindAFlight {

	@FindBy(xpath = "(//input[@name='outFlight'])[2]")
//	@CacheLookup
	private WebElement flightDepart;

	@FindBy(xpath = "(//input[@name='inFlight'])[2]")
//	@CacheLookup
	private WebElement flightArrive;

	@FindBy(name = "reserveFlights")
//	@CacheLookup
	private WebElement submit;

	public FindAFlight(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void findFlight() {
		flightDepart.click();
		flightArrive.click();
		submit.click();
	}

}
