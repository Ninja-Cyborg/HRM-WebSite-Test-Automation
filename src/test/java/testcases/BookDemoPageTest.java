package testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseClass;
import pageobjects.BookDemoPage;
import pageobjects.ClientPage;
import pageobjects.HomePage;
import pageobjects.LoginPage;
import utility.Log;

public class BookDemoPageTest extends BaseClass {

	private LoginPage loginPage;
	private HomePage homePage;
	private ClientPage clientPage;
	private BookDemoPage bookDemoPage;
	
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
	public void verifyBookDemo() throws IOException, InterruptedException {
		String username = "Admin";
		String password = "admin123";
		loginPage = new LoginPage();
		homePage = loginPage.loginToHomePage(username, password);
		clientPage = homePage.goToClientPage();
		Log.info("switching to BookADemo tab");
		bookDemoPage = clientPage.goToBookDemoPage();
		Log.info("Confirming Captcha ");
		
		boolean result = bookDemoPage.isFormSubmitted();  // wait for captcha		
		Assert.assertTrue(result, "Submission Successful");
	}
}
