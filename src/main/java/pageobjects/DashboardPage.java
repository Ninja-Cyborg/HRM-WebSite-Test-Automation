package pageobjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class DashboardPage extends BaseClass{
	
	@FindBy(xpath="//div[@class='oxd-layout-context']//*[@class='orangehrm-dashboard-widget-header']//*[@class='oxd-text oxd-text--p']")
	private List<WebElement> taskList;
	
	public DashboardPage() {
		PageFactory.initElements(BaseClass.getDriver(), this);
	}
	
	private int getNumberOfDashboardTasks() {
		
		return taskList.size();
	}

	public List<WebElement> getTaskList() {
		return taskList;
	}
	
	public boolean verifyDashboardTasks() {
		if(getNumberOfDashboardTasks() == 6){
			return true;
		}
		return false;
	}

	public int getDashboardCount() {
		return taskList.size();
	}
	
	
}
