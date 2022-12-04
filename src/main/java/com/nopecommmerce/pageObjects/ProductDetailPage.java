package com.nopecommmerce.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nopcommerce.base.Base;
import com.nopecommerce.actiondriver.Action;

public class ProductDetailPage extends Base{
	public ProductDetailPage() {
		PageFactory.initElements(getDriver(), this);
	}
	@CacheLookup
	@FindBy(xpath = "//div[@class='product-name']/h1")
	private WebElement productName;
	
	@CacheLookup
	@FindBy(className = "overview")
	private WebElement productDetailsSection;
	
	@CacheLookup
	@FindBy(xpath = "//div[@class='product-price']/span")
	private WebElement productPrice;
	
	@CacheLookup
	@FindBy(xpath = "//input[contains(@id,'product_enteredQuantity')]")
	private WebElement productQuantityInput;
	
	@CacheLookup
	@FindBy(id = "add-to-cart-button-19")
	private WebElement addToCartButton;
	
	@CacheLookup
	@FindBy(xpath = "//div[contains(@class,'success')]/p")
	private WebElement cartSuccessMessage;
	
	@CacheLookup
	@FindBy(xpath = "//div[contains(@class,'success')]//a")
	private WebElement shoppingCart;
	
	public String validateProductName() {
		
		return productName.getText();
		
	}
	public boolean checkProductDetailSection()
	{
		return productDetailsSection.isDisplayed();
	}
	public void setproductQuantity(int count)
	{
		Action.sendText(productQuantityInput, String.valueOf(count));
	}
	
	public void addTocart()
	{
		Action.click(addToCartButton);
	}
	
	public double productPrice()
	{
		String pric = productPrice.getText().replaceAll("[^a-zA-Z0-9]", "");
		double price=Integer.valueOf(pric)/100;
		return price;
	}
	public String addtoCartSucessesMessage()
	{
		return cartSuccessMessage.getText();
	}
	public ShoppingCartPage shoppingCart()
	{
		Action.click(shoppingCart);
		return new ShoppingCartPage();
	}

}
