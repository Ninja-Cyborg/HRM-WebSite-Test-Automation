package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;
import pageobjects.AdminPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import utility.Log;

public class AdminPageTest extends BaseClass{

	private LoginPage loginPage;
	private HomePage homePage;
	private AdminPage adminPage;
	
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
	public void verifyEmployeeList() throws InterruptedException {
		String username = "Admin";
		String password = "admin123";
		loginPage = new LoginPage();
		Log.info("switchin to Admin Tab");
		homePage = loginPage.loginToHomePage(username, password);
		adminPage = homePage.goToAdminPage();
		Log.info("Checking employee count");
		boolean result = adminPage.isEmployeeCountAccurate();
		
		Assert.assertTrue(result, "Employee Count is Accurate");
	}
	
}
