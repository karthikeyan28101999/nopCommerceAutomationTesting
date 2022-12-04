package nopCommerce.testcase;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopecommerce.dataprovider.Dataprovider;
import com.nopecommerce.utility.Log;
import com.nopecommmerce.pageObjects.HomePage;
import com.nopecommmerce.pageObjects.ProductDetailPage;
import com.nopecommmerce.pageObjects.SearchResultPage;

import nopCommerce.basetest.BaseTest;

public class ProductDetailsPageTest extends BaseTest{
	HomePage homepage;
    SearchResultPage searchProducts ;
    ProductDetailPage productDeatil ;
	
	@Test(priority = 0,dataProvider = "selectProduct",dataProviderClass = Dataprovider.class)
	public void verifySelectedproductName(HashMap<String, String> data)
	{
		Log.startTestCase("verfiy the  name of the user selected product ");
		homepage=new HomePage();
		searchProducts=homepage.searchProduct(data.get("ProductName"));
		productDeatil = searchProducts.selectProduct(data.get("UserProduct"));
		Assert.assertEquals(productDeatil.validateProductName(), "HTC One Mini Blue");
		Log.info("user get the search result and select the  product name " + data.get("UserProduct"));
		Log.info("verified the user selected product name");
		Log.endTestCase("verfiy the  name of the user selected product ");
	}
	
	@Test(priority = 1,dataProvider = "selectProduct",dataProviderClass = Dataprovider.class)
	public void verifyProdctDetailSection(HashMap<String, String> data)
	{
		Log.startTestCase("verify product details is displayed or not");
		homepage=new HomePage();
		searchProducts=homepage.searchProduct(data.get("ProductName"));
		productDeatil = searchProducts.selectProduct(data.get("UserProduct"));
		Assert.assertTrue(productDeatil.checkProductDetailSection());
		Log.info("verified product detail is displayed properly");
		Log.endTestCase("verify product details is displayed or not");
	}
	
	@Test(priority = 2,dataProvider = "selectProduct",dataProviderClass = Dataprovider.class)
	public void verifyAddToCartSucessesMessage(HashMap<String, String> data)
	{
		Log.startTestCase("verify the AddtocartSuccesses message");
		homepage=new HomePage();
		searchProducts=homepage.searchProduct(data.get("ProductName"));
		productDeatil = searchProducts.selectProduct(data.get("UserProduct"));
		productDeatil.setproductQuantity(2);
		productDeatil.addTocart();
		Log.info("selected product is add to cart");
		Assert.assertEquals(productDeatil.addtoCartSucessesMessage(), "The product has been added to your shopping cart"); 
		Log.info("add to cart successes message is verified");
		Log.endTestCase("verify the AddtocartSuccesses message");
	}

}
