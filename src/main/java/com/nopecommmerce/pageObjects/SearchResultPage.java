package com.nopecommmerce.pageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.nopcommerce.base.Base;
import com.nopecommerce.actiondriver.Action;

public class SearchResultPage extends Base {
	public SearchResultPage() {
		PageFactory.initElements(getDriver(), this);
	}
	
	@CacheLookup
	@FindBy(xpath = "//div[@class='item-grid']/div//h2/a")
	private  List<WebElement> productlist;
	
	@CacheLookup
	@FindBy(className = "no-result")
	private WebElement noResult;
	
	public String productSreachTitle()
	{
		return Action.getTitle(getDriver());
	}
	
	public int numberOfProductListed()
	{
		return productlist.size();
	}
	
	public String ProductSearchResult()
	{
		if(!(productlist.size()==0))
		{
			int noOfProducts=productlist.size();
			return ""+noOfProducts;
		}
		return noResult.getText();
			
	}
	
	public  ProductDetailPage selectProduct(String productName)
	{
		if(!(productlist.size()==0))
		{
			for(WebElement product:productlist)
			{
				if(product.getText().equalsIgnoreCase(productName)) {
					
					product.click();
				}
			}
		}
		return new ProductDetailPage();
	}


}
