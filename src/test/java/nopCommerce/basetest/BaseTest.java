package nopCommerce.basetest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.nopcommerce.base.Base;
import com.nopecommerce.utility.Log;

public class BaseTest extends Base {
	@Parameters("browser")
	@BeforeMethod
	public void start(@Optional("") String browser) {
		if (browser.equals("")) {
			launchBrowser();
			Log.info("------>  Initialize the " + System.getProperty("browser") + " browser");
		} else {
			launchBrowser(browser);
			Log.info("------>  Initialize the " + browser + " browser");
		}
	}

	@AfterMethod
	public void quit() {
		getDriver().quit();
		Log.info("-------->   Quit the  browser");
	}

}
