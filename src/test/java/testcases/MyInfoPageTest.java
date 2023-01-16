package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.MyInfoPage;

public class MyInfoPageTest extends BaseClass{

	private LoginPage loginPage;
	private HomePage homePage;
	private MyInfoPage myInfoPage;
	
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
	public void validateUserInfo() throws InterruptedException {
		String username = "Admin";
		String password = "admin123";
		loginPage = new LoginPage();
		homePage = loginPage.loginToHomePage(username, password);
		myInfoPage = homePage.goToInfoPage();
		boolean result = myInfoPage.isNameMatchProfile();
		
		Assert.assertTrue(result, "Profile name matches the data");
	}
}
