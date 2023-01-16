package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;
import pageobjects.AddUserPage;
import pageobjects.AdminPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import utility.Log;

public class AddUserPageTest extends BaseClass {

	private LoginPage loginPage;
	private HomePage homePage;
	private AdminPage adminPage;
	private AddUserPage addUserPage;
	
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
	public void verifyAddSystemUser() throws InterruptedException {
		String username = "Admin";
		String password = "admin123";
		loginPage = new LoginPage();
		homePage = loginPage.loginToHomePage(username, password);
		Log.info("switching to add user tab");
		adminPage = homePage.goToAdminPage();
		addUserPage = adminPage.goToAddUserPage();
		Log.info("Adding User");
		boolean result = addUserPage.verifySystemUserCreated();
		
		Assert.assertTrue(result, "Created new user");
	}
}
