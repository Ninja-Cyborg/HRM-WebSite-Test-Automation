package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;
import pageobjects.DashboardPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import utility.Log;

public class DashboardPageTest extends BaseClass {

	private LoginPage loginPage;
	private HomePage homePage;
	private DashboardPage dashboardPage;
	
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
	public void validateDashboardTasks() throws InterruptedException {
		String username = "Admin";
		String password = "admin123";
		loginPage = new LoginPage();
		Log.info("switching to dahboard tab");
		homePage = loginPage.loginToHomePage(username, password);
		dashboardPage = homePage.goToDashboardPage();
		Log.info("Getting task list count");

		boolean result = dashboardPage.verifyDashboardTasks();
		int taskCount = dashboardPage.getDashboardCount();
		
		Log.info("Tasks Count:" + taskCount);
		Assert.assertTrue(result, "Dashboards displays all tasks");
	}
}
