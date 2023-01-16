package pageobjects;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class ClientPage extends BaseClass {
	
	@FindBy(xpath="//div[@class='d-flex web-menu-btn']//li[2]//a[1]")
	private WebElement contactBtn;
	@FindBy(xpath="//div[@class='d-flex web-menu-btn']//li[1]//a[1]")
	private WebElement bookDemoBtn;
	@FindBy(xpath="//a[text()='Resources']")
	private WebElement resourcesBtn;
	@FindBy(xpath="//a[text()='Blog']")
	private WebElement blogBtn;
	@FindBy(xpath="//a[text()='eBooks']")
	private WebElement eBookBtn;
	@FindBy(tagName="a")
	private List<WebElement> links;
	
	public ClientPage() {
		PageFactory.initElements(BaseClass.getDriver(), this);
	}
	public int totalLinkCount() {
		return getLinks().size();
	}
	
	public int getBrokenLinks() throws IOException {
		int brokenCount = 0;
		List<String> urlList = getLinkUrl(getLinks());
		brokenCount = getUrlStatus(urlList);
		return brokenCount;
	}
	
	public boolean areLinksBroken() throws IOException {
		boolean isLinkBroken = false;
		if(getBrokenLinks() > 0) {
			isLinkBroken = true;
		}
		return isLinkBroken;
	}
	
	// returns list with url
	public List<String> getLinkUrl(List<WebElement> links) {
		List<String> urlList = new ArrayList<String>();
		for(WebElement e : links) {
			urlList.add(e.getAttribute("href"));
		}
		
		return urlList;
	}
	
	// iterate urlList to validate status
	public int getUrlStatus(List<String> urlList) throws IOException {
		int brokenUrl = 0;
		for(String e : urlList) {
			if(isUrlValid(e)){
				URL url = new URL(e);
				HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
				httpUrlConnection.setConnectTimeout(5000);
				httpUrlConnection.connect();
				
				if(httpUrlConnection.getResponseCode() >= 400) {
					brokenUrl =+1;
				}
			}
		}
		return brokenUrl;
	}
	
	// helper method to validate url
	public static boolean isUrlValid(String url) {
	      try {
	         URL obj = new URL(url);
	         obj.toURI();
	         return true;
	      } catch (MalformedURLException e) {
	         return false;
	      } catch (URISyntaxException e) {
	         return false;
	      }
	   }
	
	public String brokenLinkMessage() throws IOException {
		return "Links broken " + getBrokenLinks() + ":" + totalLinkCount(); 
	}
	
	public WebElement getContactSalesBtn() {
		return contactBtn;
	}
	public WebElement getBookDemoBtn() {
		return bookDemoBtn;
	}
	public WebElement getResourcesBtn() {
		return resourcesBtn;
	}
	public WebElement getBlogBtn() {
		return blogBtn;
	}
	public WebElement geteBookBtn() {
		return eBookBtn;
	}
	public List<WebElement> getLinks() {
		return links;
	}

	public BookDemoPage goToBookDemoPage() {
		getBookDemoBtn().click();
		return new BookDemoPage();
	}

	public ContactSalesPage goToContactSalesPage() {
		getContactSalesBtn().click();
		return new ContactSalesPage();
	}
	
	public BlogPage goToBlogPage() {
		Actions actions = new Actions(BaseClass.getDriver());
		actions.moveToElement(resourcesBtn).perform();
		blogBtn.click();
		return new BlogPage();
	}

	public EBookPage goToEBookPage() {
		Actions actions = new Actions(BaseClass.getDriver());
		actions.moveToElement(resourcesBtn).perform();
		eBookBtn.click();
		return new EBookPage();
	}

}
