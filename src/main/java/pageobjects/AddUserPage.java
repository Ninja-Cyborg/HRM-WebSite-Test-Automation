package pageobjects;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;
import utility.Log;

public class AddUserPage extends BaseClass{
	
	@FindBy(xpath="//button[text()=' Save ']")
	private WebElement saveBtn;
	@FindBy(xpath="//div[@class='oxd-select-text oxd-select-text--active']//i[1] ")
	private WebElement userRoleDropDown;
	@FindBy(xpath="//input[@placeholder='Type for hints...']")
	private WebElement employeeName;
	@FindBy(xpath="//div[@class='oxd-select-text oxd-select-text--active']//i[1] ")
	private WebElement employeeStatusDropDown;
	@FindBy(xpath="//div[@class='oxd-form-row']//div[@class='oxd-grid-2 orangehrm-full-width-grid']//div[@class='oxd-grid-item oxd-grid-item--gutters']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@class='oxd-input oxd-input--active']")
	private WebElement usernameField;
	@FindBy(xpath="//div[@class='oxd-grid-item oxd-grid-item--gutters user-password-cell']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@type='password']")
	private WebElement passwordField;
	@FindBy(xpath="//div[@class='oxd-grid-item oxd-grid-item--gutters']//div[@class='oxd-input-group oxd-input-field-bottom-space']//div//input[@type='password']")
	private WebElement confirmPasswordField;
	
	@FindBy(xpath="//div[@role='listbox']//div//span")
	private List<WebElement> userRoleList;
	@FindBy(xpath="(//div[@class='oxd-grid-item oxd-grid-item--gutters'])[3]//div[@role='listbox']//div//span")
	private List<WebElement> empStatusList;	
	@FindBy(xpath="//div[@class='oxd-table-card']/div/div[2]/div")
	private List<WebElement> employeeList;
	
	public AddUserPage() {
		PageFactory.initElements(BaseClass.getDriver(), this);
	}
	
	public boolean verifySystemUserCreated() throws InterruptedException {
		addUserInfo("odison", "E", "Odis  Adalwin", "E", "odis4@mE");
		saveBtn.click();
		
		Log.info("Saving User");
		
		
		boolean goThrough = isUserAddedToList("odis");
		return goThrough;
	}
	
	public boolean isUserAddedToList(String username) {
		boolean ifExist = true;
		for(WebElement e: employeeList) {
			if(e.getText().contains(username)) {
				return true;
			}
		}
		return ifExist; 	
	}
	
	public void addUserInfo(String username,
							String empRole,
							String empName,
							String status,
							String password) throws InterruptedException {
		selectUserRole(empRole);
		employeeName.sendKeys(empName);
	//	selectEmpStatus(status);
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(password);
	}

	public void selectUserRole(String role) throws InterruptedException {
		userRoleDropDown.click();
		Thread.sleep(2000);
		Actions actions = new Actions(getDriver());
		actions.sendKeys(Keys.ARROW_DOWN);
		for(WebElement e: userRoleList) {
			if(e.getText().contains(role))
				e.click();
		}
	}
	
	public void selectEmpStatus(String status) throws InterruptedException {
	employeeStatusDropDown.click();
	Thread.sleep(2000);
		for(WebElement e: empStatusList) {
			if(e.getText().contains(status))
				e.click();
		}
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
}
