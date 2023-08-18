package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponets;

public class HomePage extends AbstractComponets {

	public  WebDriver driver=null;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		

	}
	@FindBy(xpath="(//a[@data-nav-role='signin'])[1]")
	WebElement login_button;
	public void loginButtonClick() {
		login_button.click();
	}
	
}
