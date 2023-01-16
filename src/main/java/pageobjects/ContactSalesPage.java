package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import base.BaseClass;

public class ContactSalesPage extends BaseClass {

	@FindBy(xpath="//input[@id='Form_getForm_FullName']")
	private WebElement fullNameInputField;
	@FindBy(xpath="//input[@id='Form_getForm_Contact']")
	private WebElement phoneInputField;
	@FindBy(xpath="//input[@id='Form_getForm_Email']")
	private WebElement emailInputField;
	@FindBy(xpath="//select[@id='Form_getForm_Country']")
	private WebElement countryDropDown;
	@FindBy(xpath="//input[@id='Form_getForm_JobTitle']")
	private WebElement jobTitleInputField;
	@FindBy(xpath="//textarea[@id='Form_getForm_Comment']")
	private WebElement commentInputField;
	@FindBy(xpath="//input[@id='Form_getForm_action_submitForm']")
	private WebElement submitBtn;
	
	public ContactSalesPage() {
		PageFactory.initElements(BaseClass.getDriver(), this);
	}
	public boolean verifyFormSubmission() {
		enterContactInfo("Carl Jay", 
							"+0991212101",
							"carl1@carl.corp",
							"Algeria",
							"Tech Suport Lead", 
							"Want to request more developers for team! ");
		Actions actions = new Actions(getDriver());
		actions.moveToElement(submitBtn);
		submitBtn.click();
		
		return true;
	}
	
	public void enterContactInfo(String fullname,
								String phone,
								String email,
								String country,
								String jobTitle,
								String comment) {
		fullNameInputField.sendKeys(fullname);
		phoneInputField.sendKeys(phone);
		emailInputField.sendKeys(email);
		selectCountry(country);
		jobTitleInputField.sendKeys(jobTitle);
		commentInputField.sendKeys(comment);
	}
	
	public void selectCountry(String country) {
		Select dropdown = new Select(countryDropDown);
		dropdown.selectByValue(country);
	}
	
}
