package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import utility.Log;

public class LoginPageTest extends BaseClass{
	
	private LoginPage loginPage;
	private HomePage homePage;
	
	@Parameters("browser")
	@BeforeMethod(groups="Smoke")
	public void setup(String browser) {
		loadApp(browser);
	}
	
	@AfterMethod(groups="Smoke")
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(groups="Smoke")
	public void verifyValidCredenetialLogin() throws InterruptedException {
		Log.testCasestarts("Entering Credentials! ");
		String username = "Admin";
		String password = "admin123";
		Thread.sleep(3000);
		loginPage = new LoginPage();
		Thread.sleep(3000);
		Log.info("validating login title");
		String title = loginPage.validateLoginTitle(username, password);
		 Log.info("Asserting: ");
		Assert.assertEquals("OrangeHRM",title);
		Log.testCaseEnds("VerfifyLoginTest");
	}
	
	@Test(groups="Smoke")
	public void verifyNullCredentialLogin() throws InterruptedException {
		Log.testCasestarts("Entering Credentials! ");
		String username = " ";
		String password = " ";
		Thread.sleep(3000);
		loginPage = new LoginPage();
		Thread.sleep(3000);
		Log.info("validating login title");
		String title = loginPage.validateLoginTitle(username, password);
		Assert.assertEquals("OrangeHRM",title);
		
		boolean result = loginPage.isFieldNull();
		Assert.assertTrue(result, "No login with Null Creds");
		Log.testCaseEnds("VerfifyLoginTest");
	}
	
}
