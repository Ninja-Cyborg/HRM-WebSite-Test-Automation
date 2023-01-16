package pageobjects;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class BlogPage {

	@FindBy(xpath="//div[@class='blog-data-link']//a")
	private List<WebElement> blogList;
	@FindBy(xpath="//div[@class='blog-data-img']//img")
	private List<WebElement> blogsImageList;
		
	public BlogPage() {
		PageFactory.initElements(BaseClass.getDriver(), this);
	}
	
	public boolean checkAllImagesShows() {
		if(validImageCount() == totalBlogsCount())
			return true;
		return false;
	}
	
	public boolean isBlogExist() throws IOException {
		boolean isLinkBroken = false;
		if(getBrokenLinks() > 0) {
			isLinkBroken = true;
		}
		return isLinkBroken;
	}
	
	public int validImageCount() {
		int validImage = 0;
		for(WebElement e : blogsImageList) {
			Boolean p = (Boolean) ((JavascriptExecutor)BaseClass.getDriver())
					.executeScript("return arguments[0].complete "
					+ "&& typeof arguments[0].naturalWidth != \"undefined\" "
					+ "&& arguments[0].naturalWidth > 0", e);
			if(p) {
					validImage =+ 1;
				}
			}
		return validImage;
	}
	
	public int totalBlogsCount() {
		return blogList.size();
	}
	
	public int getBrokenLinks() throws IOException {
		int brokenCount = 0;
		List<String> urlList = getLinkUrl(blogList);
		brokenCount = getUrlStatus(urlList);
		return brokenCount;
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
	
}
