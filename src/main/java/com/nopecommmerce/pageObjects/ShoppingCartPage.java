package com.nopecommmerce.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nopcommerce.base.Base;
import com.nopecommerce.actiondriver.Action;

public class ShoppingCartPage extends Base {
	
	public ShoppingCartPage() {
		PageFactory.initElements(getDriver(), this);
	}

	@CacheLookup
	@FindBy(className = "page-title")
	private WebElement title;
	
	@CacheLookup
	@FindBy(className = "product-unit-price")
	private WebElement productUnitPrice;
	
	@CacheLookup
	@FindBy(xpath = "//input[contains(@id,'itemquantity')]")
	private WebElement productQunatity;
	
	@CacheLookup
	@FindBy(className = "page-title")
	private WebElement productTotalPrice;
	
	@CacheLookup
	@FindBy(className = "product-subtotal")
	private WebElement productSubTotal;
	
	@CacheLookup
	@FindBy(id = "checkout_attribute_1")
	private WebElement giftWrapping;
	
	
	@FindBy(xpath = "//span[@class='value-summary']/strong")
	private  WebElement grantTotal;
	
	@CacheLookup
	@FindBy(id = "termsofservice")
	private WebElement termsAndCondition;
	
	@CacheLookup
	@FindBy(xpath = "//div[@id='terms-of-service-warning-box']/p")
	private WebElement termsAndConditionwarningMeaasge;
	
	@CacheLookup
	@FindBy(xpath = "//button[@title='Close']")
	private WebElement closeWarningMessage;
	
	@CacheLookup
	@FindBy(id = "checkout")
	private WebElement checkoutbtn;
	
	public String shoppingCartPageTitle() {
		return title.getText();
	}
	
	public double productunitPrice()
	{
		String pric = productUnitPrice.getText().replaceAll("[^a-zA-Z0-9]", "");
		double unitprice=Integer.valueOf(pric);
		return unitprice/100;
	}
	
	public double subTotalPrice()
	{
		String pric = productSubTotal.getText().replaceAll("[^a-zA-Z0-9]", "");
		double subTotal=Integer.valueOf(pric);
		return subTotal/100;
	}
	public int quantity()
	{
		String quantity=Action.getTextIInInputByJS(getDriver(),productQunatity );	
		return Integer.valueOf(quantity);
	}
	
	public void selectGiftWrapping(String option)
	{
		if(option.equals("yes")) {
		 Action.selectByVisibleText(giftWrapping,"Yes [+$10.00]");
		}
		else {
			Action.selectByVisibleText(giftWrapping, "No");
		}
	}
	
	public double  grantTotal()
	{
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {

				e.printStackTrace();
		}
		String pric=Action.getInnerText(getDriver(), Action.fluentWaistVisibility(getDriver(), grantTotal, 5, 100)).replaceAll("[^a-zA-Z0-9]", "");
		double grantTotl=Integer.valueOf(pric)/100;
		return grantTotl;
	}
	public void clickTerms()
	{
		Action.click(termsAndCondition);
	}
	public String warningMessage()
	{
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return termsAndConditionwarningMeaasge.getText();
	}
	public void closeWarningMessage()
	{
		Action.click(closeWarningMessage);
	}
	public LoginPage clickCheckoutWithOutLogin()
	{
		Action.click(checkoutbtn);
		return new LoginPage();
	}
	public CheckoutPage clickCheckout()
	{
		Action.click(checkoutbtn);
		return new CheckoutPage();
	}
}
