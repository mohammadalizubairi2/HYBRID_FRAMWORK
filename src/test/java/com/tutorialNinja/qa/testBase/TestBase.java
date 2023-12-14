package com.tutorialNinja.qa.testBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import com.utilities.Util;

public class TestBase {
	public static WebDriver driver; 
	public SoftAssert softassert = new SoftAssert();
	public Properties prop;
	public FileInputStream ip;
	public Properties dataProp;
	public ChromeOptions options;
	public WebDriverWait wait;
	
//	public WebDriver getDriver() {
//	    return this.driver;}
	public TestBase() throws IOException{

		
		prop = new Properties();
		ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
		prop.load(ip);	
		
		dataProp = new Properties();
		ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\java\\com\\tutorialsninja\\qa\\testData\\testData.properties");
		dataProp.load(ip);	

	}

	public WebDriver openBrowser(String browserName) {
		if(browserName.equalsIgnoreCase("Chrome")) {
			options = new ChromeOptions();
			//options.addArguments("--incognito");
			options.addArguments("-start-maximized");
//			options.addArguments("headless");
//			options.addArguments("--window-size=1920,1080");
			//options.setExperimentalOption("excludeSwitches",Arrays.asList("enable-automation,disbale-infobars"));
			driver = new ChromeDriver(options);
			
		}else if(browserName.equalsIgnoreCase( "FireFox")) {
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase( "Edge")) {
			driver = new EdgeDriver();}

			driver.manage().deleteAllCookies();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	        // Set the page load timeout, for example, to 20 seconds
	        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
			//driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(Utils.SCRIPT_TIME));
			driver.manage().window().maximize();
			driver.get("https://tutorialsninja.com/demo");
return driver;
		
	}
    @AfterMethod(alwaysRun = true)
    public void tearDownMethod() {
        if (this.driver != null) {
            this.driver.quit();
}}

}
