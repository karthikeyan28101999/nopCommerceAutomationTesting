package nopCommerce.testcase;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.nopecommerce.dataprovider.Dataprovider;
import com.nopecommerce.utility.Log;
import com.nopecommmerce.pageObjects.HomePage;
import com.nopecommmerce.pageObjects.LoginPage;
import com.nopecommmerce.pageObjects.ProductDetailPage;
import com.nopecommmerce.pageObjects.SearchResultPage;
import com.nopecommmerce.pageObjects.ShoppingCartPage;

import nopCommerce.basetest.BaseTest;

public class ShoppingCartPageTest extends BaseTest{
	
	HomePage homepage;
    SearchResultPage searchProducts ;
    ProductDetailPage productDeatil ;
    ShoppingCartPage shoppingCart ;
    LoginPage loginpage;
		
	@Test(priority = 1,dataProvider = "completeProductDetails" , dataProviderClass = Dataprovider.class)
	public void verifyQuantityAndPrice(HashMap<String, String> data) {
		Log.startTestCase("verify the selected product quantity and all the  price(unit , total and granttotal) of the product");
		homepage=new HomePage();
		searchProducts=homepage.searchProduct(
				data.get("ProductName"));
		productDeatil = searchProducts.selectProduct(
				data.get("UserProduct"));
		productDeatil.setproductQuantity(Integer.valueOf(
				data.get("Quantity").substring(0,1)));
		productDeatil.addTocart();
		shoppingCart = productDeatil.shoppingCart();
		
		double productunitPrice = shoppingCart.productunitPrice();
		double  expectunitPrice=Double.valueOf(
				data.get("ProductUnitPrice"));
		
		assertEquals(productunitPrice, expectunitPrice);
		Log.info("product price is verified");
		
		int actualquantity = shoppingCart.quantity();
		int expectquantity=Integer.valueOf(
				data.get("Quantity").substring(0,1));
		assertEquals(actualquantity, expectquantity);
		Log.info("product quantity is verified");
		
		double subTotalPrice = shoppingCart.subTotalPrice();
		double expectSubtotalPrice= expectunitPrice*expectquantity;
		assertEquals(subTotalPrice, expectSubtotalPrice);
		Log.info("product subtotal is verfied");
		
		shoppingCart.selectGiftWrapping(
				data.get("GiftWrapping"));
		double actualgrantTotal = shoppingCart.grantTotal();
		double expectgrantTotal=expectSubtotalPrice+10;
		assertEquals(actualgrantTotal, expectgrantTotal);
		Log.info("finally grant total is verified");
		Log.endTestCase("verify the selected product quantity and all the  price(unit , total and granttotal) of the product");
	}
	
	@Test(priority = 3,dataProvider = "completeProductDetails" , dataProviderClass = Dataprovider.class)
	public void verfiyWarningMessageandCheckout(HashMap<String, String> data)
	{
		Log.startTestCase("verify terms and condition alert message and proceed checkout");
		homepage=new HomePage();
		searchProducts=homepage.searchProduct(
				data.get("ProductName"));
		productDeatil = searchProducts.selectProduct(
				data.get("UserProduct"));
		productDeatil.setproductQuantity(Integer.valueOf(
				data.get("Quantity").substring(0,1)));
		productDeatil.addTocart();
		shoppingCart = productDeatil.shoppingCart();
		shoppingCart.clickCheckout();
		
		String actualWarningMessage = shoppingCart.warningMessage();
		assertEquals(actualWarningMessage, "Please accept the terms of service before the next step.");
		Log.info("warning alert is verified");
		
		shoppingCart.closeWarningMessage();
		shoppingCart.clickTerms();
	
		loginpage = shoppingCart.clickCheckoutWithOutLogin();
	    String actualcheckoutPagetitle = loginpage.loginPageTitle();
	    assertEquals(actualcheckoutPagetitle, "nopCommerce demo store. Login");
		Log.info("user sucessesfully forward to checkout page");
		Log.endTestCase("verify terms and condition alert message and proceed checkout");
	}

}
