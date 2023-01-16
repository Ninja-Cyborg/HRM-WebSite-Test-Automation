package pageobjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class DirectoryPage extends BaseClass{

	@FindBy(xpath="//div[@class='oxd-grid-4']//div[@class='oxd-grid-item oxd-grid-item--gutters']")
	private List<WebElement> employeeList;
	
	@FindBy(xpath="//span[@class='oxd-text oxd-text--span']")
	private WebElement employeeRecordCount;
	
	JavascriptExecutor jsExecutor;
	
	public DirectoryPage() {
		PageFactory.initElements(BaseClass.getDriver(), this);
	}
	
	public boolean validateEmployeeCounts() throws InterruptedException {
		Thread.sleep(2000);
		scrollEmployeeList();
		if(getEmployeeRecordCount() == getEmployeeListCount()) {
			return true;
		}
		return false;
	}
	
	// scrolling in inner div container
	private void scrollEmployeeList() throws InterruptedException {
		jsExecutor = (JavascriptExecutor) BaseClass.getDriver(); 
		String scrollScript = "return document.querySelector(\".orangehrm-container\").scrollTop=2000";
		int i = 0;
		while(i<5) {
			jsExecutor.executeScript(scrollScript);
			Thread.sleep(2000);
			i++;
		}
		
	}
	
	public int getEmployeeRecordCount() {
		String recordText = getEmpRecordCount().getText();
		String count = recordText.substring(recordText.lastIndexOf('(') + 1, 3 );
		int countValue = Integer.parseInt(count);
		return countValue;
	}
	
	public int getEmployeeListCount() {
		return employeeList.size();
	}

	public List<WebElement> getEmployeeList() {
		return employeeList;
	}

	public WebElement getEmpRecordCount() {
		return employeeRecordCount;
	}
	
}
