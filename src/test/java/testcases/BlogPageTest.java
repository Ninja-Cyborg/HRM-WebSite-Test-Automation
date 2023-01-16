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
import pageobjects.HomePage;
import pageobjects.LoginPage;
import utility.Log;

public class BlogPageTest extends BaseClass {

	private LoginPage loginPage;
	private HomePage homePage;
	private ClientPage clientPage;
	private BlogPage blogPage;
	
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
	public void verifyImagesDisplayed() throws IOException, InterruptedException {
		String username = "Admin";
		String password = "admin123";
		loginPage = new LoginPage();
		homePage = loginPage.loginToHomePage(username, password);
		clientPage = homePage.goToClientPage();
		blogPage = clientPage.goToBlogPage();
		
		boolean result = blogPage.checkAllImagesShows();
		int blogCount = blogPage.totalBlogsCount();
		Log.info("Shown images" + blogCount);
		Assert.assertFalse(result, "Only "+ blogCount + " blog page images are shown");
	}
	
	@Test(groups="Smoke")
	public void verifyIfBlogsExist() throws IOException, InterruptedException {
		String username = "Admin";
		String password = "admin123";
		loginPage = new LoginPage();
		homePage = loginPage.loginToHomePage(username, password);
		clientPage = homePage.goToClientPage();
		blogPage = clientPage.goToBlogPage();
		
		boolean result = blogPage.isBlogExist();
		int blogCount = blogPage.totalBlogsCount();
		Log.info("Working blogs" + blogCount);
		Assert.assertFalse(result, "Only "+ blogCount + " blogs Works");
	}
}
