package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.BaseClass;

public class LoginPage extends BaseClass {
	
	@FindBy(xpath="//input[@placeholder='Username']")
	private WebElement usernameField;
	@FindBy(xpath="//input[@placeholder='Password']")
	private WebElement passwordField;
	@FindBy(xpath="//button[normalize-space()='Login']")
	private WebElement loginBtn;
	@FindBy(xpath="(//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message'][normalize-space()='Required'])[1]")
	private WebElement usernamePrompt;
	
	public LoginPage() {
		PageFactory.initElements(BaseClass.getDriver(), this);
	}
	
	public String validateLoginTitle(String username, String password) throws InterruptedException {
		enterCredentials(username, password);
		clickLogin();
		String title = getDriver().getTitle();
		return title;
	}
	
	public void enterCredentials(String username, String password) throws InterruptedException {
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
	}
	
	
	public void clickLogin() {
		getLoginBtn().click();
	}
	
	public HomePage loginToHomePage(String username, String password) throws InterruptedException {
		enterCredentials(username, password);
		clickLogin();
		return new HomePage();
	}

	public WebElement getUsernameField() {
		return usernameField;
	}

	public WebElement getPasswordField() {
		return passwordField;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}

	public boolean isFieldNull() {
		if(usernamePrompt.getText().contains("Required"))
			return true;
		return false;
	}

}
