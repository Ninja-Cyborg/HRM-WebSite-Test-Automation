package utility;

import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	
	public static ExtentSparkReporter sparkReporter;  // html file
	public static ExtentReports extent;              // build reports
	public static ExtentTest test;					// define reports: add logs, screenshot, etc
	
	public static void setExtent() throws IOException {
		sparkReporter= new ExtentSparkReporter(System.getProperty("user.dir")+"/test-output/ExtentReport/"+"report.html");
		sparkReporter.loadXMLConfig(System.getProperty("user.dir")+"/Configuration/extent-config.xml");
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Tester", "Abhijot");
		extent.setSystemInfo("ProjectName", "SeleniumTestingProject");
		extent.setSystemInfo("Tester", "Selenium");
		extent.setSystemInfo("OS", "Win10");
		extent.setSystemInfo("Browser", "Chrome");
	}
	public static void endReport() {
		extent.flush();
	}
}
