package nopCommerce.testcase;

import org.testng.annotations.Test;
import org.testng.Assert;
import com.nopecommerce.utility.Log;
import com.nopecommmerce.pageObjects.HomePage;
import nopCommerce.basetest.BaseTest;

public class HomePageTest extends BaseTest {
	public HomePage homepage;

	@Test(priority = 0)
	public void verifyTitle()
	{	
		Log.startTestCase("verify the Homepage title");
		homepage=new HomePage();
		String actual = homepage.homePageTitle();
		Assert.assertEquals(actual,"nopCommerce demo store");
		Log.info("Title of the home page is verified");
		Log.endTestCase("verify the Home page title");
	}
	@Test(priority = 1)
	public void verifyLogo()
	{
		Log.startTestCase("verify the application Logo is displayed or not");
		homepage=new HomePage();
		Assert.assertTrue(homepage.logo());
		Log.info("Logo is displayed in home page");
		Log.endTestCase("verify the application Logo is displayed or not");
	}
	
	@Test(priority = 2)
	public void verfiyBanner()
	{
		Log.startTestCase("verify product banner displayed in homepage");
		homepage=new HomePage();
		Assert.assertTrue(homepage.verifyBanner());
		Log.info("product banner is displayed in homepage");
		Log.endTestCase("verify product banner displayed in homepage");
	}
}
