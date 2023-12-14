package com.tn.qa.myListeners;

import java.awt.Desktop;
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


public class TestListeners implements ITestListener {

	public ExtentReports extentReport;
	public ExtentTest extentTest;
	public String testName;
	@Override
	public void onStart(ITestContext context) {
		System.out.println("Testing started for: " + context.getName());
		try {extentReport = ExtentReporters.generateExtentReport();}
	    catch (Throwable e) {e.printStackTrace();}
	    }
	@Override
	public void onTestStart(ITestResult result) {
		testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName + "-> Testing started");
	}
    @Override
    public void onTestSuccess(ITestResult result) {
        testName = result.getName();
        WebDriver driver = getDriverFromResult(result);
        if (driver != null) {
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String destinationFile = System.getProperty("user.dir")+ "\\test-output\\ScreenShots\\" + testName + ".png";
            try {
                FileHandler.copy(source, new File(destinationFile));
                extentTest.addScreenCaptureFromPath(destinationFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            extentTest.log(Status.PASS,testName + "-->Passed Test", result.getThrowable(), null);
        } else {
            System.err.println("WebDriver instance was null during onTestSuccess");
        }
    }
    @Override
    public void onTestFailure(ITestResult result) {
        testName = result.getName();
        WebDriver driver = getDriverFromResult(result);
        if (driver != null) {
            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String destinationFile = System.getProperty("user.dir")+ "\\test-output\\ScreenShots\\" + testName + ".png";
            try {
                FileHandler.copy(source, new File(destinationFile));
                extentTest.addScreenCaptureFromPath(destinationFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            extentTest.log(Status.FAIL,testName + "-->Failed Test", result.getThrowable(), null);
        } else {
            System.err.println("WebDriver instance was null during onTestFailure");
        }
    }
    @Override
    public void onTestSkipped(ITestResult result) {
		testName = result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.SKIP,testName + "-> skipped");
    }
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }
    @Override
    public void onTestFailedWithTimeout(ITestResult result) {}
    @Override
    public void onFinish(ITestContext context) {

    extentReport.flush();
    String pathOfExtentReport = System.getProperty("user.dir")+ "\\test-output\\ExtentReports\\extentReport.html";
        
    File extentReportPath = new File(pathOfExtentReport);
    try {Desktop.getDesktop().browse(extentReportPath.toURI());}catch(IOException e) {e.printStackTrace();}
    }
    private WebDriver getDriverFromResult(ITestResult result) {
        Object testInstance = result.getInstance();
        if (testInstance instanceof TestBase) {
            return ((TestBase) testInstance).driver; // Directly access the driver here
        }
        return null;
    }
}



 