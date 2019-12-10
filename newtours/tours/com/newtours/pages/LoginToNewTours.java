package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.newtours.config.UtilityClass;

public class LoginToNewTours extends UtilityClass {
	@FindBy(name = "userName")
	private WebElement user;

	@FindBy(name = "password")
	private WebElement pass;

	@FindBy(name = "login")
	private WebElement submit;

	public LoginToNewTours(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void applicationLogin(String name, String passw) throws Exception {
		user.sendKeys(name);
		pass.sendKeys(passw);
		UtilityClass.captureScreenShot(driver);
		submit.click();
	}
}
