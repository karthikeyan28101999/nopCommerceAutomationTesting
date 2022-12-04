package nopCommerce.testcase;

import org.testng.annotations.Test;

import java.util.HashMap;

import org.testng.Assert;

import com.nopecommerce.dataprovider.Dataprovider;
import com.nopecommerce.utility.Log;
import com.nopecommmerce.pageObjects.HomePage;
import com.nopecommmerce.pageObjects.SearchResultPage;

import nopCommerce.basetest.BaseTest;

public class SearchResultPageTest extends BaseTest{
	
	HomePage homepage;
    SearchResultPage searchProducts ;
	@Test(dataProvider = "Productname" , dataProviderClass = Dataprovider.class)
	public void verifySearchResult(HashMap<String, String> data) {
		Log.startTestCase("verfiy user get list of product or not based on search"); 
		homepage=new HomePage();
		searchProducts= homepage.searchProduct(data.get("ProductName"));
		if(searchProducts.numberOfProductListed()==0)
		{
			Assert.assertEquals(searchProducts.ProductSearchResult(), "No products were found that matched your criteria.");
			Log.info("user search product is not avalaible");
		}
		else
		{
			int noOfListedProducts=searchProducts.numberOfProductListed();
			Assert.assertEquals(searchProducts.ProductSearchResult(), String.valueOf(noOfListedProducts));
			Log.info("user get related products");
		}
		Log.endTestCase("verfiy user get list of product or not based on search");
		
	}

}
