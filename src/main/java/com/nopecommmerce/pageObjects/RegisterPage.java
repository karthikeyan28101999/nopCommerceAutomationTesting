package com.nopecommmerce.pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nopcommerce.base.Base;
import com.nopecommerce.actiondriver.Action;

public class RegisterPage extends Base {
	public RegisterPage() {
		PageFactory.initElements(getDriver(),this);
	}
	@CacheLookup
	@FindBy(tagName = "h1")
	private WebElement registerHeading;
	
	@CacheLookup
	@FindBy(xpath = "//div[@id='gender']/span/input")
	private List<WebElement> gender;
	
	@CacheLookup
	@FindBy(id = "FirstName")
	private WebElement firstName;
	
	@CacheLookup
	@FindBy(id="LastName")
	private WebElement lastName;
	
	@CacheLookup
	@FindBy(xpath = "//select[@name='DateOfBirthDay']")
	private WebElement date;
	
	@CacheLookup
	@FindBy(xpath = "//select[@name='DateOfBirthMonth']")
	private WebElement month;
	
	@CacheLookup
	@FindBy(xpath = "//select[@name='DateOfBirthYear']")
	private WebElement year;
	
	@CacheLookup
	@FindBy(id = "Email")
	private WebElement email;
		
	@CacheLookup
	@FindBy(id = "Company")
	private WebElement company;
	
	@CacheLookup
	@FindBy(id = "Password")
	private  WebElement password;
	
	@CacheLookup
	@FindBy(id = "ConfirmPassword")
	private WebElement confirmPassword;
	
	@CacheLookup
	@FindBy(id = "register-button")
	private WebElement registerBtn;
	
	@CacheLookup
	@FindBy(xpath = "//div[@class='result']")
	private WebElement registerResult;
	
	@CacheLookup
	@FindBy(className = "register-continue-button")
	private WebElement registerContinue;
	
	@CacheLookup
	@FindBy(xpath = "//div[@class='message-error validation-summary-errors']/ul/li")
	private WebElement accountAlerdyExistMessage;
	
	public String registerPageTitle()
	{
		String title = Action.getTitle(getDriver());
		return title;
	}
	
	public String registerPageUrl()
	{
		return getDriver().getCurrentUrl();
	}
	
	public String resgisterHeading()
	{
		return registerHeading.getText();
	}
	
	public void register(String sex,String firstname,String lastname,String dob,String mail,String com,String pass,String confirmPass){
		Action.clickCheckBoxByText(gender, sex);
		Action.sendText(firstName, firstname);
		Action.sendText(lastName, lastname);
		Action.clickDOB(dob, date, month, year);
		Action.sendText(email, mail);
		Action.sendText(company, com);
		Action.sendText(password,pass);
		Action.sendText(confirmPassword,pass);
		Action.click(registerBtn);
	}
	public HomePage clickRegisterContinue()
	{
		Action.click(registerContinue);
		return new HomePage();
		
	}
	public String accountAlerdyExistMessage() {
		return Action.getInnerText(getDriver(), Action.fluentWaistVisibility(getDriver(), accountAlerdyExistMessage, 5, 400));
	}
	
	public String registerSucessesfullMessage() {
		
		return Action.getInnerText(getDriver(), Action.fluentWaistVisibility(getDriver(), registerResult, 5, 500));
	
	}
	
	

}
