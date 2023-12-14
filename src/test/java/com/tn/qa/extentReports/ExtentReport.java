package com.tn.qa.extentReports;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	
	
	
	public void extentReport() throws IOException {
		ExtentReports extent = new ExtentReports();
		
		ExtentSparkReporter spark = new ExtentSparkReporter("extent");
		
		
		
		spark.config().getDocumentTitle();
		
		extent.attachReporter(spark);
		Properties prop = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\java\\com\\tutorialsninja\\qa\\config\\config.properties");


		prop.load(ip);
		extent.setSystemInfo("Application Url: ", prop.getProperty("url"));
		extent.getTestSubject();
	}

}
