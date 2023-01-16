package pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class HomePage extends BaseClass{
	
	@FindBy(xpath="//div[@class='oxd-topbar-header-userarea']")
	private WebElement userProfileIcon;
	@FindBy(xpath="//p[@class='oxd-userdropdown-name']")
	private WebElement profileUsername;
	@FindBy(xpath="//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']")
	private List<WebElement> userProfileItems;
	@FindBy(xpath="//img[@alt='client brand banner']")
	private WebElement clientPageLink;
	@FindBy(xpath="//span[.='Admin']")
	private WebElement adminPageLink;
	@FindBy(xpath="//span[.='My Info']")
	private WebElement myInfoPageLink;
	@FindBy(xpath="//span[.='Dashboard']")
	private WebElement dashboardPageLink;
	@FindBy(xpath="//span[.='Directory']")
	private WebElement directoryPageLink;
	@FindBy(xpath="//i[@class='oxd-icon bi-chevron-right']")
	private WebElement sideBarBtn;
	@FindBy(xpath="input[placeholder='Search']")
	private WebElement searchField;
	
	public HomePage() {
		PageFactory.initElements(BaseClass.getDriver(), this);
	}
	public void logout() {
		getUserProfileIcon().click();
		clickLogout();
	}
	
	public AdminPage goToAdminPage() {
		adminPageLink.click();
		return new AdminPage();
	}
	
	public MyInfoPage goToInfoPage() {
		myInfoPageLink.click();
		return new MyInfoPage();
	}
	
	public ClientPage goToClientPage() {
		getClientPageLink().click();
		return new ClientPage();
	}
	
	public String getUsername() {
		String username = getProfileUsername().getText();
		return username;
	}
	
	public boolean checkIfUsernameNull() {
		boolean isNull= false;
		String username = getProfileUsername().getText();
		if(!username.isEmpty()) {
			isNull = true;
		}
		return isNull;
	}
	
	public void clickLogout() {
		for(WebElement e: getUserProfileItems()) {
			if(e.getText().equalsIgnoreCase("logout"))
				e.click();
		}
	}
	
	
	
	public WebElement getUserProfileIcon() {
		return userProfileIcon;
	}

	public WebElement getProfileUsername() {
		return profileUsername;
	}

	public WebElement getClientPageLink() {
		return clientPageLink;
	}

	public List<WebElement> getUserProfileItems() {
		return userProfileItems;
	}

	public String getLogoutPageTitle() {
		String title = BaseClass.getDriver().getTitle();
		return title;
	}

	public SideBar openSideBar() {
//		boolean isToogle = searchField.getAttribute("class")
//						.contains("toogled");
//				
//		if(isToogle) {
//			sideBarBtn.click();
//		}
		return new SideBar();
	}

	public DashboardPage goToDashboardPage() {
		dashboardPageLink.click();
		return new DashboardPage();
	}

	public DirectoryPage goToDirectoryPage() {
		directoryPageLink.click();
		return new DirectoryPage();
	}
}
