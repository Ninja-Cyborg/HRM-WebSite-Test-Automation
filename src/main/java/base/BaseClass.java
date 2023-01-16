package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import utility.ExtentManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

// test grouping, browser, driver, wait setup
public class BaseClass {

	public static Properties prop;
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	
	@BeforeSuite(groups= {"Smoke", "Sanity", "Regression"})
	public void loadConfig() throws IOException {
		ExtentManager.setExtent();
		DOMConfigurator.configure( System.getProperty("user.dir") + "//Configuration//log4j.xml");
		
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\Configuration\\Config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	@Parameters("browser")   // testng.xml file <paramters name=value pair>
	public static void loadApp(String browser) {
	//		System.getProperty(("user.dir" +"\\Configuration\\Config.properties"));
			long pageTimeout = 20; //Long.parseLong(prop.getProperty("pageLoadTimeout"));
			long implicitWait = 10; //Long.parseLong(prop.getProperty("implicitWait"));
			
			if( browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver.set(new ChromeDriver());
			} else if(browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver.set(new FirefoxDriver());
			} else if (browser.equalsIgnoreCase("edge")) {
				WebDriverManager.edgedriver().setup();
				driver.set(new EdgeDriver());
			} else {
				System.out.println("Browser Name Invalid !");
			}
			getDriver().manage().deleteAllCookies();
			getDriver().manage().window().maximize();
			getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
			getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageTimeout));
			
			getDriver().get(prop.getProperty("url"));
		}
	
	@AfterSuite(groups= {"Smoke", "Regression", "Sanity"})
	public void afterSuite() {
		ExtentManager.endReport();
	}
	
	public static RemoteWebDriver getThreadDriver() {
		return driver.get();
	}

	public static RemoteWebDriver getDriver() {
		return driver.get();
	}
}
