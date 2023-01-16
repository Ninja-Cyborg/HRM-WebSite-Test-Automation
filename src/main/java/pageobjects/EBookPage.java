package pageobjects;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class EBookPage extends BaseClass{

	@FindBy(xpath="//*[@class='col-md-4 pb-5']")
	private List<WebElement> eBookList;
	@FindBy(xpath="//div[@class='ebook-download']/a")
	private List<WebElement> downloadLinksList;
	
	public EBookPage() {
		PageFactory.initElements(BaseClass.getDriver(), this);
	}
	
	public boolean checkEBookDownloadable() throws IOException {
		if(geteBookListCount() == validDownloadLinkCount()) {
			return true;
		}
		return false;
	}

	public int geteBookListCount() {
		int ebooks = geteBookList().size();
		return ebooks;
	}
	
	public int getDownloadLinkCount() {
		int linkCount = getDownloadLinksList().size();
		return linkCount;
	}
	
	public List<WebElement> geteBookList() {
		return eBookList;
	}

	public List<WebElement> getDownloadLinksList() {
		return downloadLinksList;
	}
	
	public String linkCount() throws IOException {
		return "Valid Links :" + validDownloadLinkCount();
	}
	
	private int validDownloadLinkCount() throws IOException {
		
		int validLinkCount = 0;
		List<String> urlList = getLinkUrl(downloadLinksList);
		validLinkCount = getUrlStatus(urlList);
		return validLinkCount;
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
		int validUrl = 0;
		for(String e : urlList) {
			if(isUrlValid(e)){
				URL url = new URL(e);
				HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
				httpUrlConnection.setConnectTimeout(5000);
				httpUrlConnection.connect();
				
				if(httpUrlConnection.getResponseCode() <= 400) {
					validUrl =+1;
				}
			}
		}
		return validUrl;
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
	
}
