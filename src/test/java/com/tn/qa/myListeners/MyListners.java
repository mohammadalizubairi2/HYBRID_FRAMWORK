package com.tn.qa.myListeners;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tn.qa.extentReports.ExtentReporters;
import com.tutorialNinja.qa.testBase.TestBase;


public class MyListners implements ITestListener{
	public ExtentReports extentReport;
	public String testName;
	public ExtentTest extentTest;
	public WebDriver driver;
	
	
	@Override
	public void onStart(ITestContext context) {
		 try {
		extentReport = ExtentReporters.generateExtentReport();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();}
	}

	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, "Testing Started for " +testName);

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		testName = result.getName();

		extentTest.log(Status.PASS, testName +" passed testing.");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		driver = TestBase.driver;
		testName = result.getName();

		
		File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir")+ "\\test-output\\ScreenShots\\" + "failed.png";
		
		try {
			FileHandler.copy(source, new File(destination));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.addScreenCaptureFromPath(destination);
		extentTest.log(Status.FAIL, testName +" failed testing.");
		
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		testName = result.getName();

		extentTest.log(Status.SKIP, testName +" skipped testing.");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}



	@Override
	public void onFinish(ITestContext context) {
		extentReport.flush();
	}
	
	


}
