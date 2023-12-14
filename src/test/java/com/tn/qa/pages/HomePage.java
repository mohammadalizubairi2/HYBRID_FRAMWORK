package com.tn.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//span[text()='My Account']")           
	private WebElement MyAccountLink;
	
	@FindBy(linkText = "Login")           
	private WebElement SelectLoginLink;
	
	
	
	
	
	
	public void selectMyAccountLink() {
		MyAccountLink.click();
	}
	
	public void SelectLoginLink() {
		SelectLoginLink.click();
	}
	
	
	
	
	
	
	
	
	
	

}
