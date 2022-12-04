package nopCommerce.testcase;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.nopecommerce.dataprovider.Dataprovider;
import com.nopecommerce.utility.Log;
import com.nopecommmerce.pageObjects.HomePage;
import com.nopecommmerce.pageObjects.LoginPage;

import nopCommerce.basetest.BaseTest;

public class LoginPageTest extends BaseTest{
	
	public HomePage homepage;
	public LoginPage loginpage;	
	@Test(priority = 0)
	public void verfifyLoginPageTitleAndWelcomeMessage()
	{
		Log.startTestCase("verify the login page title and WelcomeMessage");
		homepage=new HomePage();
		loginpage=homepage.clickLoginButton();
		Log.info("user clicked the login button on the home page");
		String actualLoginTitle = loginpage.loginPageTitle();
		Assert.assertEquals(actualLoginTitle, "nopCommerce demo store. Login");
		Log.info("login page title is verified");
		String actualWelcomeMessage=loginpage.loginPageTitleMessage();
		Assert.assertEquals(actualWelcomeMessage, "Welcome, Please Sign In!");
		Log.info("login page welcome message is verified");
		Log.endTestCase("verify the login page title and WelcomeMessage");
	}
	@Test(priority = 1 ,dataProvider = "LoginDetails" , dataProviderClass = Dataprovider.class)
	public void loginAccount(HashMap<String, String> data)
	{
		Log.startTestCase("verify the login account with user details");
		homepage=new HomePage();
		loginpage=homepage.clickLoginButton();
		homepage=loginpage.loginAccount(
				data.get("Email"),
				data.get("Password"));
		if(data.get("Result").contains("demo store"))	
		{
			Assert.assertEquals(homepage.homePageTitle(),"nopCommerce demo store");
			Log.info("user provide the login details");
			Log.info("user Account is sucessfuly created");
		}
		else if(data.get("Result").contains("No customer account found")){
			Assert.assertEquals(loginpage.unSucessesMessage(),"Login was unsuccessful. Please correct the errors and try again.\n"
					+ "No customer account found");
			Log.info("pass the invalid user details");
			Log.info("user get error  message like correct the errors and try again");
		}
		else {
			Assert.assertEquals(loginpage.unSucessesMessage(),"Login was unsuccessful. Please correct the errors and try again.\n"
					+ "The credentials provided are incorrect");
			Log.info("pass the invalid user details");
			Log.info("user get error  message like no account found");
		}
		Log.endTestCase("verify the login account with user details");
	}
	
	
}
