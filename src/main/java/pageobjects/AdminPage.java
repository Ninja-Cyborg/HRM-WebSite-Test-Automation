package pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class AdminPage extends BaseClass{
	
	@FindBy(xpath="//div[@class='oxd-table-card']/div/div[2]/div")
	private List<WebElement> employeeList;
	
	@FindBy(xpath="//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']/span")
	private WebElement employeeRecord;

	@FindBy(xpath="//*[@class='orangehrm-header-container']/button")
	private WebElement addUserBtn;
	
	public AdminPage() {
		PageFactory.initElements(BaseClass.getDriver(), this);
	}
	
	public boolean isEmployeeCountAccurate() {
		boolean isCountAccurate = false;
		if(getShownEmployeeCount() == getRecordEmployeeCount()) {
			return true;
		}
		return isCountAccurate;	
	}
	
	public int getShownEmployeeCount() {
		int employeeCount = getEmployeeList().size();
		return employeeCount;
	}
	
	public void clickAddUser() {
		getAddUserBtn().click();
	}
	
	public int getRecordEmployeeCount() {
		String records = getEmployeeRecord().getText();   // (12) records found
		String recordValue = records.substring(1, records.lastIndexOf(')') ); // extract 12
		int value = Integer.parseInt(recordValue);
		return value;
	}
	
	public WebElement getAddUserBtn() {
		return addUserBtn;
	}

	public List<WebElement> getEmployeeList() {
		return employeeList;
	}

	public WebElement getEmployeeRecord() {
		return employeeRecord;
	}

	public AddUserPage goToAddUserPage() {
		clickAddUser();
		return new AddUserPage();
	}

	
}
