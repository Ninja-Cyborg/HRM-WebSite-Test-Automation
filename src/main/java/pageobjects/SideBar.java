package pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class SideBar extends BaseClass{

	@FindBy(xpath="//nav[@aria-label='Sidepanel']//*[@class='oxd-text oxd-text--span oxd-main-menu-item--name']")
	private List<WebElement> sidebarList;

	@FindBy(xpath="//input[@placeholder='Search']")
	private WebElement searchField;
	
	public SideBar() {
		PageFactory.initElements(BaseClass.getDriver(), this);
	}
	
	public void enterSearchInput(String searchItem) {
		searchField.sendKeys(searchItem);
	}
	
	public List<WebElement> getSidebarList() {
		return sidebarList;
	}

	public WebElement getSearchField() {
		return searchField;
	}
	
	public boolean checkSearchListCount() {
		if(getSidebarList().size() == 11) {
			return true;
		}
		return false;
	}
	
	public boolean searchPageList() throws InterruptedException {
		boolean pageExist = false;
		pageExist = true;
		for(String e : getTextFromList()) {	
			searchField.sendKeys(Keys.CONTROL + "a");  // clear search Field 
			searchField.sendKeys(Keys.DELETE);
			Thread.sleep(500);
			searchField.sendKeys(e);     // search page
			if(sidebarList.contains(e)) { // verify is page shown in searchList
				pageExist = true;
			}
		}
		
		return pageExist;
	}
	
	public List<String> getTextFromList() {
		List<String> textList = new ArrayList<>();
		for(WebElement e : sidebarList) {
			textList.add(e.getText());
		}
		return textList;
	}
	
}
