package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import base.BaseClass;

public class BookDemoPage extends BaseClass {

	@FindBy(xpath="//input[@id='Form_getForm_FullName']")
	private WebElement fullNameInputField;
	@FindBy(xpath="//input[@id='Form_getForm_Email']")
	private WebElement emailInputField;
	@FindBy(xpath="//input[@id='Form_getForm_Contact']")
	private WebElement phoneInputField;
	@FindBy(xpath="//input[@id='Form_getForm_action_submitForm']")
	private WebElement submitBtn;
	@FindBy(id="//select[@id='Form_getForm_Country']")
	private WebElement countryDropDown;
	
	public BookDemoPage() {
		PageFactory.initElements(BaseClass.getDriver(), this);
	}
	
	public void enterBookDemoInfo(String fullname, String email,
							String phone, String country) {
		
		fullNameInputField.sendKeys(fullname);
		emailInputField.sendKeys(email);
		phoneInputField.sendKeys(phone);
	//	selectCountry(country);
		
	}
	
	public boolean isFormSubmitted() throws InterruptedException {
		enterBookDemoInfo("Joe",
							"Joe@hotmail.corp",
							"+09922134478",
							"Canada");
		// wait for captcha 
		Thread.sleep(10000);
		Actions actions = new Actions(getDriver());
		actions.moveToElement(submitBtn);
		submitBtn.click();
		
		return true;
	}
	
	public void selectCountry(String country) {
		Select dropdown = new Select(countryDropDown);
		dropdown.selectByValue(country);
	}
}
