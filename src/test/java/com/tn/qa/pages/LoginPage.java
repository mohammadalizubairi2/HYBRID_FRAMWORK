package com.tn.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "input-email")
	private WebElement inputEmailField;
	
	@FindBy (id = "input-password")
	private WebElement inputPasswordField;
	
	
	@FindBy (xpath = "//input[@type = 'submit']")
	private WebElement submitButton;
	
	
	
	public void enterEmailAddress(String email)
	{
		inputEmailField.sendKeys(email);
	}
	
	public void enterPassword(String password)
	{
		inputPasswordField.sendKeys(password);
	}
	
	public void clickOnSubmitButton()
	{
		submitButton.click();
	}

	
	
	
	
	
	
	

}
