package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponets;

public class YourAccount extends AbstractComponets{

	WebDriver driver;

	public YourAccount(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(xpath="//div[@id='nav-cart-count-container']")
	WebElement cartButton;
	@FindBy(xpath="//input[@id='twotabsearchtextbox']")
	WebElement searchBox;
	@FindBy(xpath="//input[@value='Go']")
	WebElement go;
	
	@FindBy(xpath="//a[@data-nav-ref='nav_youraccount_btn']")
	WebElement YourAccountButton;
	@FindBy(xpath="//span[text()='Sign Out']")
	WebElement SignOut;
	
	public void SearchProduct() {
		searchBox.sendKeys("think like a monk by jay shetty");
		go.click();
		
	}
	 public void NavigateToCart()
	 {
		 cartButton.click();
	 }
	 public void SignOut()
	 {
		 Actions a=new Actions(driver);
		 a.moveToElement(YourAccountButton).build().perform();
		 SignOut.click();
	 }
	 
}
