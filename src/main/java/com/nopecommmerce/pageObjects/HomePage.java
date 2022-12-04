package com.nopecommmerce.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nopcommerce.base.Base;
import com.nopecommerce.actiondriver.Action;

public class HomePage extends Base {
	public HomePage() {
		PageFactory.initElements(getDriver(), this);
	}
	@CacheLookup
	@FindBy(xpath = "//a[text()='Register']")
	private WebElement register;
	
	@CacheLookup
	@FindBy(className = "header-logo")
	private WebElement logo;
	
	@CacheLookup
	@FindBy(xpath = "//a[text()='Log in']")
	private WebElement login;
	
	@CacheLookup
	@FindBy(xpath = "//a[text()='Wishlist']")
	private WebElement wishlist;
	
	@CacheLookup
	@FindBy(xpath = "//li[@id='topcartlink']")
	private WebElement cart;
	
	@CacheLookup
	@FindBy(id = "nivo-slider")
	private WebElement banner;
	
	@CacheLookup
	@FindBy(id = "small-searchterms")
	private WebElement searchInputBox;
	
	@CacheLookup
	@FindBy(css = ".search-box-button")
	private WebElement searchBtn;
	
	public String homePageTitle()
	{
		String title = Action.getTitle(getDriver());
		return title;
	}
	public boolean logo()
	{
		return logo.isDisplayed();
	}
	
	public boolean verifyBanner()
	{
		return banner.isDisplayed();
	}
	
	public LoginPage clickLoginButton()
	{
		Action.click(login);
		return new LoginPage();
	}
	
	public  RegisterPage ClickRegisterButton()
	{
		Action.click(register);
		return new RegisterPage();
	}
	
	public void clickWishlistButton() {
		Action.click(wishlist);
	}
	
	public void clickCartButton() {
		Action.click(cart);
	}
	
	public SearchResultPage searchProduct(String product)
	{
		Action.sendText(searchInputBox, product);
		Action.click(searchBtn);
		return new SearchResultPage();
	}
}