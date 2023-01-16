package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import pageobjects.SideBar;
import utility.Log;

public class SideBarTest extends BaseClass{

	private LoginPage loginPage;
	private HomePage homePage;
	private SideBar sideBar;
	
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
	public void validateSearchList() throws InterruptedException {
		String username = "Admin";
		String password = "admin123";
		loginPage = new LoginPage();
		homePage = loginPage.loginToHomePage(username, password);
		Log.info("Opening Side Bar");
		sideBar = homePage.openSideBar();
		
		boolean result = sideBar.checkSearchListCount();	
		Assert.assertTrue(result, "All Page showup");
	}
	
	@Test(groups="Smoke")
	public void checkSideBarSearchFeature() throws InterruptedException{
		String username = "Admin";
		String password = "admin123";
		loginPage = new LoginPage();
		homePage = loginPage.loginToHomePage(username, password);
		Log.info("Opening Side Bar");
		sideBar = homePage.openSideBar();
		
		boolean result = sideBar.searchPageList();
		Assert.assertTrue(result, "All page are searchable");
	}
}
