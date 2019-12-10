package com.newtours.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisteringUser {

	@FindBy(linkText = "REGISTER")
	@CacheLookup
	private WebElement register;

	@FindBy(name = "firstName")
	@CacheLookup
	private WebElement newFirstName;

	@FindBy(name = "lastName")
	@CacheLookup
	private WebElement newLastName;

	@FindBy(name = "phone")
	@CacheLookup
	private WebElement phone;

	@FindBy(name = "userName")
	@CacheLookup
	private WebElement registerEmail;

	@FindBy(name = "address1")
	@CacheLookup
	private WebElement address1;

	@FindBy(name = "city")
	@CacheLookup
	private WebElement city;

	@FindBy(name = "state")
	@CacheLookup
	private WebElement state;

	@FindBy(name = "postalCode")
	@CacheLookup
	private WebElement postalCode;

	@FindBy(name = "email")
	@CacheLookup
	private WebElement newUserName;

	@FindBy(name = "password")
	@CacheLookup
	private WebElement newPassword;

	@FindBy(name = "confirmPassword")
	@CacheLookup
	private WebElement confirmPassword;

	@FindBy(name = "register")
	@CacheLookup
	private WebElement submit;

	public RegisteringUser(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public void personalDetails(String fName, String lName, String uphone, String uemail) {
		register.click();
		newFirstName.sendKeys(fName);
		newLastName.sendKeys(lName);
		phone.sendKeys(uphone);
		registerEmail.sendKeys(uemail);
	}

	public void address(String uaddress, String ucity, String ustate, String upostalCode) {

		address1.sendKeys(uaddress);
		city.sendKeys(ucity);
		state.sendKeys(ustate);
		postalCode.sendKeys(upostalCode);
	}

	public void newUserDetails(String userN, String userP, String confirmPw) throws InterruptedException {

		newUserName.sendKeys(userN);
		newPassword.sendKeys(userP);
		confirmPassword.sendKeys(confirmPw);
		submit.click();
	}
}
