package com.tutorialsninja.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tn.qa.pages.HomePage;
import com.tn.qa.pages.RegisterPage;
import com.tutorialNinja.qa.testBase.TestBase;
import com.utilities.Util;

public class RegisterTest extends TestBase{


	
	public RegisterTest() throws IOException {
		super();

	}

	public RegisterPage registerpage;
	public HomePage homepage;

	@BeforeMethod
	public void beforeMethod() {
		openBrowser("Chrome");
		registerpage = new RegisterPage(driver);
	    homepage = new HomePage(driver);
	    homepage.selectMyAccountLink();
		driver.findElement(By.linkText("Register")).click();
		}
	
		
	
	@Test(priority = 1)
	public void CreatAccountEnteringMandatoryData () {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Sher");
		driver.findElement(By.id("input-lastname")).sendKeys("Ali");
		driver.findElement(By.id("input-email")).sendKeys(Util.emailWithDynamicNumbers());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys(prop.getProperty("validPassowrd"));
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium@123");
		driver.findElement(By.xpath("//input[@name = 'agree']")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarning = driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();
		String expectedWarning = "Your Account Has Been Created!";
		softassert.assertEquals(actualWarning, expectedWarning);
		softassert.assertAll();	
	}


	@Test(priority = 2)
	public void CreatAccountEnteringMandatoryPlusNonMandatoryData() {
		driver.findElement(By.id("input-firstname")).sendKeys("Sher");
		driver.findElement(By.id("input-lastname")).sendKeys("Ali");
		driver.findElement(By.id("input-email")).sendKeys(Util.emailWithDynamicNumbers());
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium@123");
		driver.findElement(By.xpath("//input[@name = 'newsletter' and @value = '1']")).click();	
		driver.findElement(By.xpath("//input[@name = 'agree']")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarning = driver.findElement(By.xpath("//*[@id=\"content\"]/h1")).getText();
		String expectedWarning = "Your Account Has Been Created!";
		softassert.assertEquals(actualWarning, expectedWarning);
		softassert.assertAll();	



	}
	@Test(priority = 3)
	public void CreatAccountWithExistingAccountEmail () {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Sher");
		driver.findElement(By.id("input-lastname")).sendKeys("Ali");
		driver.findElement(By.id("input-email")).sendKeys("seleniumpanda@gamil.com");
		driver.findElement(By.id("input-telephone")).sendKeys("1234567890");
		driver.findElement(By.id("input-password")).sendKeys("Selenium@123");
		driver.findElement(By.id("input-confirm")).sendKeys("Selenium@123");
		driver.findElement(By.xpath("//input[@name = 'agree']")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualWarning = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedWarning = "Warning: E-Mail Address is already registered!";
		softassert.assertEquals(actualWarning, expectedWarning);
		softassert.assertAll();	
	}

	@Test(priority = 4)
	public void CreatAccountWithoutEnteringMandatoryData() {

		driver.findElement(By.xpath("//input[@type= 'submit' and @value='Continue']")).click();
		
		String actualFirstNameWarning = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div[1]")).getText();
		String expectedFirstNameWarning = "First Name must be between 1 and 32 characters!";
		softassert.assertEquals(actualFirstNameWarning, expectedFirstNameWarning);
		
		String actualLastNameWarning = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div[1]")).getText();
		String expectedLastNameWarning = "Last Name must be between 1 and 32 characters!";
		softassert.assertEquals(actualLastNameWarning, expectedLastNameWarning);
//		
		String actualEmailWarning = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div[1]")).getText();
		String expectedEmailWarning = "E-Mail Address does not appear to be valid!";
		softassert.assertEquals(actualEmailWarning, expectedEmailWarning);
		
		String actualTelephoneWarning= driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div[1]")).getText();
		String expectedTelephoneWarning = "Telephone must be between 3 and 32 characters!";
		softassert.assertEquals(actualTelephoneWarning, expectedTelephoneWarning);
		
		String actualPasswordWarning = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div[1]")).getText();
		String expectedPasswordWarning = "Password must be between 4 and 20 characters!";
		softassert.assertTrue(actualPasswordWarning.contains(expectedPasswordWarning));
		
		
		String actualPrivacyPolicyWarning = driver.findElement(By.xpath("//div[contains(@class, 'alert-dismissible')]")).getText();
		String expectedPrivacyPolicyWarning = "Warning: You must agree to the Privacy Policy!";
		softassert.assertEquals(actualPrivacyPolicyWarning, expectedPrivacyPolicyWarning);
		softassert.assertAll();	
	}
		@AfterMethod
		public void tearDown() {driver.quit();}	   /*This is class braket*/}
	

