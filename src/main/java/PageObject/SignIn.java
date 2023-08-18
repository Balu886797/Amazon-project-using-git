package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponets;

public class SignIn extends AbstractComponets{
	
	public WebDriver driver;

	public SignIn(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//input[@type='email']")
	WebElement email_textField;
	
	@FindBy(xpath="//input[@id='continue']")
	WebElement continueButton;
	
	@FindBy(xpath="//input[@type='password']")
	WebElement password;
	
	@FindBy(xpath="//input[@id='signInSubmit']")
	WebElement signInButton;
	
	@FindBy(xpath="(//div[@class='a-alert-content'])[3]")
	WebElement emailAlert;
	@FindBy(xpath="//span[text()='Hello, Balu']")
	WebElement user;
	@FindBy(xpath="(//div[@class=\"a-alert-content\"])[3]")
	WebElement passwordAlert;
	@FindBy(xpath="//a[@id='auth-fpp-link-bottom']")
	WebElement forgetLink;
	public String SignInuser(String email, String pass)
	{
		email_textField.sendKeys(email);
		continueButton.click();
		password.sendKeys(pass);
		signInButton.click();
		 String username = user.getText();
		return username;
	}
	
	public String SignInuser()
	{
		email_textField.sendKeys("");
		continueButton.click();	
		String EmailAlert = emailAlert.getText();
		return EmailAlert;
	}

	public String SignInuser(String email) {
		email_textField.sendKeys(email);
		continueButton.click();
		password.sendKeys("");
		signInButton.click();
		String PasswordAlert=passwordAlert.getText();
		return PasswordAlert;
	}
	public void forgetPassword(String email) {
		email_textField.sendKeys(email);
		continueButton.click();
	    forgetLink.click();

	}
	
}
