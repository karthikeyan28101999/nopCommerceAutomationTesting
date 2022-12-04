package com.nopecommerce.utility;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.nopcommerce.base.Base;

public class ExtentReportManager {
	
	public static ExtentSparkReporter sparkreport;
	public static ExtentReports extent;
	public  static ExtentTest test;
	protected static ThreadLocal<ExtentTest> tests=new ThreadLocal<ExtentTest>();
	
	protected static ExtentTest getTest()
	{
		return tests.get();
	}
	
	public static void setExtent() {
		sparkreport= new ExtentSparkReporter("./extentreport/testreport.html");
		try {
			sparkreport.loadXMLConfig("./extent-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}		
		extent = new ExtentReports();
		extent.attachReporter(sparkreport);
		extent.setSystemInfo("HostName", "carthi");
		extent.setSystemInfo("ProjectName", "nopCommerceProject");
		extent.setSystemInfo("Tester", "karthikeyan");
		extent.setSystemInfo("OS", "Linux mint");
		extent.setSystemInfo("Browser", System.getProperty("browser"));
	}
	public static void endReport() {
		extent.flush();
		try {
			Desktop.getDesktop().browse(new File("./extentreport/testreport.html").toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
