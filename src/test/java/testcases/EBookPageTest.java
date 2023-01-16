package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;
import pageobjects.BlogPage;
import pageobjects.ClientPage;
import pageobjects.EBookPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import utility.Log;

public class EBookPageTest extends BaseClass {
	
	private LoginPage loginPage;
	private HomePage homePage;
	private ClientPage clientPage;
	private EBookPage eBookPage;
	
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
	public void verifyDownloadLinks() throws IOException, InterruptedException {
		String username = "Admin";
		String password = "admin123";
		loginPage = new LoginPage();
		homePage = loginPage.loginToHomePage(username, password);
		clientPage = homePage.goToClientPage();
		Log.info("switching to EBook tab");
		eBookPage = clientPage.goToEBookPage();
		
		Log.info("verifying download link status");
		boolean result = eBookPage.checkEBookDownloadable();
		Log.info(eBookPage.linkCount());
		Assert.assertFalse(result, "Not AllEBooks download links working");
	}

}
