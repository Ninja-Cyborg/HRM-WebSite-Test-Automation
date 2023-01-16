package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class MyInfoPage extends BaseClass{

	@FindBy(xpath="//input[@placeholder='First Name']")
	private WebElement firstNameField;
	@FindBy(xpath="//input[@placeholder='Last Name']")
	private WebElement lastNameField;
	@FindBy(xpath="//p[@class='oxd-userdropdown-name']")
	private WebElement profileName;
	
	public MyInfoPage() {
		PageFactory.initElements(BaseClass.getDriver(), this);
	}
	
	public boolean isNameMatchProfile() {
		String firstName = firstNameField.getText();
		String lastName = lastNameField.getText();
		
		if(getUserProfileName().contains(firstName) 
				&&  getUserProfileName().contains(lastName)) {

				return true;
		}
		return false;
	}

	private String getUserProfileName() {
		return profileName.getText();
	}
	
}
