package com.nopcommerce.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import com.nopecommerce.actiondriver.Action;
import com.nopecommerce.utility.ExtentReportManager;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {

	public  static Properties pro;
	protected static ThreadLocal<WebDriver> driver=new ThreadLocal<WebDriver>();	
	
	public static WebDriver getDriver()
	{
		return driver.get();
	}
	
	
	@BeforeSuite
	public static void loadconfig(){
		ExtentReportManager.setExtent();
		pro=new Properties();
		FileInputStream propertyFile;
		try {
			propertyFile = new FileInputStream("./configuration/config.properties");
			pro.load(propertyFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	@AfterSuite
	public void close()
	{
		ExtentReportManager.endReport();
	}
	
	//launching browser using parameteres
	public  void launchBrowser(String browsr)
	{
		String browser=browsr.toLowerCase();
		if(browser.contains("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions option=new ChromeOptions();
			if(browser.contains("headless"))
			{
				option.addArguments("--headless");
			}
			driver.set(new  ChromeDriver(option));;
		}
		else if(browser.contains("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions option=new FirefoxOptions();
			if(browser.equals("firefox headless"))
			{
				option.addArguments("--headless");
			}
			driver.set(new  FirefoxDriver(option));
			
		}
		else if(browser.contains("edge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions option=new EdgeOptions();
			if(browser.equals("edge headless"))
			{
				option.setHeadless(true);
			}
			driver.set(new  EdgeDriver(option));;	
		}
		getDriver(). get(pro.getProperty("url"));
		Action.maximizeWindow(getDriver());
		Action.pageLoadTimeOut(getDriver(), 10);
		Action.implicitlyWait(getDriver(), 10);
	}
		
	//launch browser using propertyfile or systemproperty
	public  void launchBrowser()
	{
		String browser=System.getProperty("browser").toLowerCase();
		if(browser.contains("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions option=new ChromeOptions();
			if(browser.equals("chrome headless"))
			{
				option.addArguments("--headless");
			}
			driver.set(new  ChromeDriver(option));;
		}
		else if(browser.contains("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions option=new FirefoxOptions();
			if(browser.equals("firefox headless"))
			{
				option.addArguments("--headless");
			}
			driver.set(new  FirefoxDriver(option));
			
		}
		else if(browser.contains("edge")) {
			WebDriverManager.edgedriver().setup();
			EdgeOptions option=new EdgeOptions();
			if(browser.equals("edge headless"))
			{
				option.setHeadless(true);
			}
			driver.set(new  EdgeDriver(option));;	
		}
		getDriver(). get(pro.getProperty("url"));
		Action.maximizeWindow(getDriver());
		Action.pageLoadTimeOut(getDriver(), 20);
		Action.implicitlyWait(getDriver(), 20);
	}

}
