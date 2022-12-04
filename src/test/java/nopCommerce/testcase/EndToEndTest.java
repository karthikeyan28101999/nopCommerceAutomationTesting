package nopCommerce.testcase;

import java.util.HashMap;

import org.testng.Assert;
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

public class EndToEndTest extends BaseTest{
	
	HomePage homepage;
	RegisterPage registerpage;
    SearchResultPage searchProducts ;
    ProductDetailPage productDeatil ;
    ShoppingCartPage shoppingCart ;
    CheckoutPage checkoutPage;	
	@Test(dataProvider = "endToEndTest",dataProviderClass = Dataprovider.class)
	public void verifyCompleteProductBuying(HashMap<String, String> data)
	{
		Log.startTestCase("verfiy whether user get order confirm message or not");
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
				data.get("City"),
				data.get("Address1"), 
				data.get("Address2"),
				data.get("Postalcode"), 
				data.get("PhoneNo"));
		checkoutPage.selectShippingMethod(data.get("ShippingMethod"));
		checkoutPage.selectPaymentMehtode(data.get("PaymentMethod"));
		checkoutPage.clickPaymentInformationClickButton();
		checkoutPage.confirmOrder();
		Assert.assertEquals(checkoutPage.orderSucessesMessage(), "Your order has been successfully processed!");
		Log.info("user get the orderconfirm message sucessesfully");
		Log.endTestCase("verfiy whether user get order confirm message or not");
	}

}
