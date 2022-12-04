package nopCommerce.testcase;

import java.util.HashMap;

import org.testng.annotations.Test;

import com.nopecommerce.dataprovider.Dataprovider;
import com.nopecommerce.utility.Log;
import com.nopecommmerce.pageObjects.CheckoutPage;
import com.nopecommmerce.pageObjects.HomePage;
import com.nopecommmerce.pageObjects.ProductDetailPage;
import com.nopecommmerce.pageObjects.RegisterPage;
import com.nopecommmerce.pageObjects.SearchResultPage;
import com.nopecommmerce.pageObjects.ShoppingCartPage;

import nopCommerce.basetest.BaseTest;

public class CheckOutPageTest extends BaseTest{
	
	HomePage homepage;
	RegisterPage registerpage;
    SearchResultPage searchProducts ;
    ProductDetailPage productDeatil ;
    ShoppingCartPage shoppingCart ;
    CheckoutPage checkoutPage;	
	@Test(dataProvider = "checkouttDetails",dataProviderClass = Dataprovider.class)
	public void verfiyEnterAddress(HashMap<String, String> data)
	{
		Log.startTestCase("verify the address given for checkout page");
		homepage=new HomePage();
		registerpage = homepage.ClickRegisterButton();
		registerpage.register(
				data.get("Gender"),
				data.get("FirstName"), 
				data.get("LastName"), 
				data.get("DateOfBirth"), 
				data.get("Mail"), 
				data.get("Company"), 
				data.get("Password"), 
				data.get("ConfirmPassword"));
		homepage = registerpage.clickRegisterContinue();
		searchProducts = homepage.searchProduct(data.get("ProductName"));
		productDeatil = searchProducts.selectProduct(data.get("UserProduct"));
		productDeatil.addTocart();
		shoppingCart = productDeatil.shoppingCart();
		shoppingCart.clickTerms();
		checkoutPage = shoppingCart.clickCheckout();
		checkoutPage.enterUserAddess(
				data.get("Country"),
				data.get("State"),
				data.get("City"),
				data.get("Address1"), 
				data.get("Address2"),
				data.get("Postalcode"), 
				data.get("PhoneNo"));
		Log.info("user successesfully pass the address in input fields");
		Log.endTestCase("verify the address given for checkout page");
		
	}

}
