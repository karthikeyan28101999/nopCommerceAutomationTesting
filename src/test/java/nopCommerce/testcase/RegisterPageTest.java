package nopCommerce.testcase;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.nopecommerce.dataprovider.Dataprovider;
import com.nopecommerce.utility.Log;
import com.nopecommmerce.pageObjects.HomePage;
import com.nopecommmerce.pageObjects.RegisterPage;

import nopCommerce.basetest.BaseTest;

public class RegisterPageTest extends BaseTest{
	
	public HomePage homepage;
	public RegisterPage registerpage;
	@Test(priority = 0)
	public void verifyRegisterPageUrlAndTitle()
	{
		Log.startTestCase("verify the register page URL and title");
		Log.info("verify the register page URL and title");
		homepage=new HomePage();
		registerpage = homepage.ClickRegisterButton();
		Log.info("click the register button in home page");
		String actualRegisterPageUrl=registerpage.registerPageUrl();
		Assert.assertEquals(actualRegisterPageUrl,"https://demo.nopcommerce.com/register?returnUrl=%2F");
		Log.info("URL of the  register page is verified");
		String actualTitle=registerpage.registerPageTitle();
		Assert.assertEquals(actualTitle,"nopCommerce demo store. Register");
		Log.info("Title of  the register page is verified");
		Log.endTestCase("verify the register page URL and title");
	}
	
	
	
	
	@Test(priority = 1 , dataProvider = "AccountRegisterDetails" , dataProviderClass = Dataprovider.class)
	public void createNewAccount(HashMap<String, String> map)
	{
		Log.startTestCase("verfiy the user acount creation");
		homepage=new HomePage();
		registerpage = homepage.ClickRegisterButton();
		registerpage.register(
				map.get("Gender"), 
				map.get("FirstName"),
				map.get("LastName"), 
				map.get("DateOfBirth"),
				map.get("Mail"), 
				map.get("Company"),
				map.get("Password"),
				map.get("ConfirmPassword"));
		Log.info("user credential is passed to in given fields");
		Log.info("Register Button is clicked");
		if(map.get("Result").equals("pass"))
		{
			assertEquals(registerpage.registerSucessesfullMessage(), "Your registration completed");
			Log.info("user account is sucessesfully created");
		}
		else{
			assertEquals(registerpage.accountAlerdyExistMessage(), "The specified email already exists");
			Log.info("warning messaage showed is verified");
		}

		Log.endTestCase("verfiy the user acount creation");
	}
}
