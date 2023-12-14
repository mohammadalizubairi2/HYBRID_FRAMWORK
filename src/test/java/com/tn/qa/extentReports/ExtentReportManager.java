package com.tn.qa.extentReports;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.util.HashMap;
import java.util.Map;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static Map<String, ExtentTest> testNameToTestMap = new HashMap<>();

    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            // Initialize ExtentReports with the path to the report file
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/extentReport/extentReport.html");
            extent.attachReporter(spark);

            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("Extent Report V4");
            spark.config().setTheme(Theme.DARK);

            extent.setSystemInfo("Tester", "Sher Ali");
            // ... add other system info as needed
        }
        return extent;
    }

    public static void startTest(String testName, String description) {
        ExtentTest test = getReporter().createTest(testName, description);
        testNameToTestMap.put(testName, test);
    }

    public static ExtentTest getTest(String testName) {
        return testNameToTestMap.get(testName);
    }

    public static void endTest(String testName) {
        ExtentTest test = getTest(testName);
        if (test != null) {
            extent.removeTest(test);
            testNameToTestMap.remove(testName);
        }
    }

    public static void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }

    // Other utility methods as needed
}
