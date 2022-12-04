package com.nopecommerce.actiondriver;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nopcommerce.base.Base;

public class Action extends Base {
	static String foldername=null;

	public static void scrollByVisibilityOfElement(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);
	}

	public static void JSClick(WebDriver driver, WebElement ele) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", ele);
	}
	public static String getInnerText(WebDriver driver, WebElement ele) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		Object executeScript = executor.executeScript("return arguments[0].innerHTML;", ele);
		return (String)executeScript;
	}
	
	public static String getTextIInInputByJS(WebDriver driver, WebElement ele) {

		JavascriptExecutor executor = (JavascriptExecutor) driver;
		Object executeScript = executor.executeScript("return arguments[0].value;", ele);
		return executeScript.toString();
	}
	public static void sendText(WebElement ele, String text) {
		ele.isDisplayed();
		ele.clear();
		ele.sendKeys(text);
	}
	public static void click(WebElement ele) {
		ele.isDisplayed();
		ele.click();
	}
	
	//methode for this web application oly
	public static void clickCheckBoxByText(List<WebElement> webelements,String text)
	{
		for(WebElement element:webelements)
		{
			if (element.getAttribute("value").equals(text.toUpperCase().substring(0,1))) {
				element.click();
				break;
			}
		}
	}
	//handle calender to click date of birth
	public static void clickDOB(String date,WebElement day,WebElement month,WebElement year) {
		
		if(date=="")
		{
			return;
		}
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int targetDay = calendar.get(Calendar.DAY_OF_MONTH);
		int targetMonth = calendar.get(Calendar.MONTH)+1;
		int targetYear = calendar.get(Calendar.YEAR);
	
		selectByVisibleText(day, String.valueOf(targetDay));
		selectByValue(month, String.valueOf(targetMonth));
		selectByVisibleText(year, String.valueOf(targetYear));
		
		
	}
	
	
	// Below methods are used to handle DropDowns
	public static void selectByIndex(WebElement element, int index) {
		Select s = new Select(element);
		s.selectByIndex(index);
	}

	public static void selectByValue(WebElement element, String value) {
		Select s = new Select(element);
		s.selectByValue(value);
	}

	public static  void selectByVisibleText(WebElement ele,String visibletext) {

		Select s = new Select(ele);
		s.selectByVisibleText(visibletext);
	}

	// Below methods are used to handle Handle Frames by Different arguments

	public static  void switchToFrameByIndex(WebDriver driver, int index) {
		new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
		driver.switchTo().frame(index);
	}

	public static void switchToFrameById(WebDriver driver, String idValue) {
		driver.switchTo().frame(idValue);
	}

	public static void switchToFrameByName(WebDriver driver, String nameValue) {

		driver.switchTo().frame(nameValue);
	}

	public static void switchToDefaultFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	// Below Method is used to perform mouse related Action
	public static void mouseOverOnElement(WebDriver driver, WebElement element) {
		scrollByVisibilityOfElement(driver, element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}

	public static void mouseOverOnElementAndClick(WebDriver driver, WebElement element) {
		scrollByVisibilityOfElement(driver, element);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().perform();
	}

	public static void draganddrop(WebDriver driver, WebElement source, WebElement target) {
		new Actions(driver).dragAndDrop(source, target).perform();
	}

	public static void slider(WebDriver driver, WebElement ele, int x, int y) {
		new Actions(driver).dragAndDropBy(ele, x, y).build().perform();
	}

	public static void rightclick(WebDriver driver, WebElement ele) {

		new Actions(driver).contextClick(ele).perform();
	}

	public static void doubleClick(WebDriver driver, WebElement ele) {

		new Actions(driver).doubleClick(ele).perform();
	}
	
	//below methods are  used to perform Switch to multiple windows
	
	public static String getTitle(WebDriver driver) {
		String text = driver.getTitle();
		return text;
	}
	
	public static  String getCurrentURL(WebDriver driver)  {
		String text = driver.getCurrentUrl();
		return text;
	}
	
	//below methods are used to perform Waits operation
	
	public static WebElement fluentWait(WebDriver driver,WebElement element, int timeOut,int poll) {
	    Wait<WebDriver> wait = null;
	
	        wait = new FluentWait<WebDriver>((WebDriver) driver)
	        		.withTimeout(Duration.ofSeconds(timeOut))
	        	    .pollingEvery(Duration.ofSeconds(poll))
	        	    .ignoring(Exception.class);
	        return wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	public static WebElement fluentWaistVisibility(WebDriver driver,WebElement element, int timeOut,int poll) {
	    Wait<WebDriver> wait = null;
	
	        wait = new FluentWait<WebDriver>((WebDriver) driver)
	        		.withTimeout(Duration.ofSeconds(timeOut))
	        	    .pollingEvery(Duration.ofMillis(poll))
	        	    .ignoring(Exception.class);
	        return wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public static WebElement explicitWait(WebDriver driver, WebElement element, int timeOut ) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		WebElement ele = wait.until(ExpectedConditions.visibilityOf(element));
		return ele;
	}
	public static WebElement explicitWaitElementClick(WebDriver driver, WebElement element, int timeOut ) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(element));
		return ele;
	}
	public static boolean explicitWaitToStringChange(WebDriver driver, WebElement element, String str,int timeOut ) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		Boolean bool = wait.until(ExpectedConditions.textToBePresentInElement(element,str));
		System.out.println(bool);
		return bool;
	}
	public static void pageLoadTimeOut(WebDriver driver, int timeOut) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeOut));
	}
	
	public static void implicitlyWait(WebDriver driver, int timeOut) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeOut));
	}
	
	
	public static void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	public static String getCurrentTime() {
		String currentDate = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
		return currentDate;
	}

	public static String screenShot(WebDriver driver, String name) {
		if(foldername==null)
		{
		  foldername= new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date());
		}
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/screenshots/"+foldername +"/"+ name + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		return destination;
	}
}
