package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;
import pageobjects.DirectoryPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import utility.Log;

public class DirectoryPageTest extends BaseClass {

	private LoginPage loginPage;
	private HomePage homePage;
	private DirectoryPage directoryPage;
	
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
	public void validateEmployeeList() throws InterruptedException {
		String username = "Admin";
		String password = "admin123";
		loginPage = new LoginPage();
		homePage = loginPage.loginToHomePage(username, password);
		Log.info("switching to directory tab");
		directoryPage = homePage.goToDirectoryPage();
		Log.info("Validating employee count");
		boolean result = directoryPage.validateEmployeeCounts();
		
		Assert.assertTrue(result, " Employee List is Accurate");
	}
}
