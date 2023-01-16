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

public class HomePageTest extends BaseClass{

	private HomePage homePage;
	private LoginPage loginPage;
	
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
	public void verifyUsername() throws InterruptedException {
		Log.testCasestarts("verfiyUsername()");
		String username = "Admin";
		String password = "admin123";
		loginPage = new LoginPage();
		homePage = loginPage.loginToHomePage(username, password);
		Log.info("Entered credentials");
		boolean result = homePage.checkIfUsernameNull();
		
		Assert.assertEquals(true, result);
		Log.testCaseEnds("verifyUsername()");
	}
	
	@Test(groups="Smoke")
	public void verifyLogout() throws InterruptedException {
		String username = "Admin";
		String password = "admin123";
		loginPage = new LoginPage();
		homePage = loginPage.loginToHomePage(username, password);
		homePage.logout();
		String title = homePage.getLogoutPageTitle();
		Assert.assertEquals("OrangeHRM", title);
	}
		
}
