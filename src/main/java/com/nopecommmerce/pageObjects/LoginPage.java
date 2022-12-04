package com.nopecommmerce.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nopcommerce.base.Base;
import com.nopecommerce.actiondriver.Action;

public class LoginPage extends Base {
	public LoginPage() {
		PageFactory.initElements(getDriver(),this);
	}
	
	@CacheLookup
	@FindBy(css = ".page-title h1")
	private WebElement titleMessage;
	
	@CacheLookup
	@FindBy(id = "Email")
	private WebElement emailBox;
	
	@CacheLookup
	@FindBy(id = "Password")
	private WebElement passwordBox;
	
	@CacheLookup
	@FindBy(id="RememberMe")
	private WebElement rememberMeCheckBox;
	
	@CacheLookup
	@FindBy(className = "forgot-password")
	private WebElement forgetPassword;
	
	@CacheLookup
	@FindBy(xpath = "//button[@class='button-1 login-button']")
	private WebElement loginButton;
	
	@CacheLookup
	@FindBy(css = "register-button")
	private WebElement registerButton;
	
	@CacheLookup
	@FindBy(xpath = "//div[@class='message-error validation-summary-errors']")
	private WebElement accountCreationUnsucessesfulMessge;
	
	public String loginPageTitle() {
		String title = Action.getTitle(getDriver());
		return title;
	}
	
	public String loginPageTitleMessage() {
		String message = titleMessage.getText();
		return message;
	}
	
	public HomePage loginAccount(String username, String password)
	{
		Action.sendText(emailBox,username);
		Action.sendText(passwordBox,password);
		Action.click(loginButton);
		return new HomePage();
	}
	
	public String unSucessesMessage()
	{
		Action.fluentWaistVisibility(getDriver(), accountCreationUnsucessesfulMessge, 5, 1);
		return accountCreationUnsucessesfulMessge.getText();
	}
	
	public  void ClickRegisterButton()
	{
		Action.click(registerButton);
	}
}
