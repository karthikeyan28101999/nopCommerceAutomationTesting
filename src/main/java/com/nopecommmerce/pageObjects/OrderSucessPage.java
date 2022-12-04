package com.nopecommmerce.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nopcommerce.base.Base;
import com.nopecommerce.actiondriver.Action;

public class OrderSucessPage extends Base {
	public OrderSucessPage() {
		PageFactory.initElements(getDriver(),this);
	}
	@CacheLookup
	@FindBy(xpath = "//div[@class='page checkout-page order-completed-page']")
	private WebElement orderConfirmMessage;
	
	@CacheLookup
	@FindBy(className = "order-completed-continue-button")
	private WebElement orderCompleteContinue;
	
	public String orderSucessMessage()
	{
		return orderConfirmMessage.getText();
	}
	
	public HomePage clickOrderComplete()
	{
		Action.click(orderCompleteContinue);
		return new HomePage();
	}
}