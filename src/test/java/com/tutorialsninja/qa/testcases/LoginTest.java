package com.tutorialsninja.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tn.qa.pages.HomePage;
import com.tn.qa.pages.LoginPage;
import com.tutorialNinja.qa.testBase.TestBase;

public class LoginTest extends TestBase {


	
public LoginTest() throws IOException {
		super();

	}
    public LoginPage loginpage;
    public HomePage homepage;
    public String email;
    public WebDriver driver;


	@BeforeMethod
	public void beforeMethod() {

	    driver = openBrowser("Chrome");
	    homepage = new HomePage(driver);
	    homepage.selectMyAccountLink();
	    homepage.SelectLoginLink();
	    loginpage = new LoginPage(driver);
	    
	}


	
//	@Test(priority =1, dataProvider = "TN", dataProviderClass = ExcelData.class)
//	public void loginWithValidCredentials (String anyusername, String anypassword) {
//
//		loginpage.enterEmailAddress(anyusername);
//		loginpage.enterPassword(anypassword);
//		loginpage.clickOnSubmitButton();
//		
//		softassert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
//		softassert.assertAll();
//		
//	}
//	@Test(testName = "Login With Invalid Credentials", priority = 2)
//	public void loginWithInValidCredentials () {
//		
//
//		driver.findElement(By.id("input-email")).sendKeys("seleniumpanda9876y87678@gmail.com");
//		driver.findElement(By.id("input-password")).sendKeys("Selenium@123uy87987987");
//		loginpage.clickOnSubmitButton();
//		String actualWarning = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
//		String expectedWarning = dataProp.getProperty("noMatchEmailPasswordWarning");
//		softassert.assertEquals(actualWarning, expectedWarning);
//		softassert.assertAll();
//	
//		
//	}
	@Test(priority = 3 )
	public void loginWithValidEmailandInValidPassword () {

		driver.findElement(By.id("input-email")).sendKeys("seleniumpanda@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
		loginpage.clickOnSubmitButton();
		String actualWarning = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
		softassert.assertTrue(actualWarning.contains(expectedWarning));
		softassert.assertAll();
		
	}
//	@Test(priority = 4)
//	public void loginWithInValidEmailandValidPassword () {
//
//		driver.findElement(By.id("input-email")).sendKeys("seleniumpanda563737@gmail.com");
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
//		loginpage.clickOnSubmitButton();
//		String actualWarning = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
//		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
//		softassert.assertTrue(actualWarning.contains(expectedWarning));
//		softassert.assertAll();
//		
//	}
//	@Test(priority = 5)
//	public void loginWithNoCredentials () {
//
//		driver.findElement(By.id("input-email")).sendKeys("");
//		driver.findElement(By.id("input-password")).sendKeys("");
//		loginpage.clickOnSubmitButton();
//		String actualWarning = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
//		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
//		softassert.assertTrue(actualWarning.contains(expectedWarning));
//		softassert.assertAll();
//	}
//	@Test(priority = 6)
//	public void loginWithNoEmailandValidPassword() {
//
//		driver.findElement(By.id("input-email")).sendKeys("");
//		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassword"));
//		loginpage.clickOnSubmitButton();
//		String actualWarning = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
//		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
//		softassert.assertTrue(actualWarning.contains(expectedWarning));
//		softassert.assertAll();
//
//	}
//	@Test(priority = 7)
//	public void loginWithValidEmailandNoPassword() {
//
//		driver.findElement(By.id("input-email")).sendKeys(prop.getProperty("validEmail"));
//		driver.findElement(By.id("input-password")).sendKeys("");
//		loginpage.clickOnSubmitButton();
//		String actualWarning = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
//		String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
//		softassert.assertEquals(actualWarning, expectedWarning);
//		softassert.assertAll();

}
