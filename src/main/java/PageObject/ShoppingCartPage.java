package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponets;

public class ShoppingCartPage extends AbstractComponets {

	WebDriver driver;

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath="//input[@name='proceedToRetailCheckout']")
	WebElement ProceedtoCheckout;
	@FindBy(xpath="//span[contains(text(),'Added to Cart')]")
	WebElement cartMessage;
	
	 
	public void selectCartProduct() {
		ProceedtoCheckout.click();		
	}
	 public String cartMessage() {
		String cartmessage = cartMessage.getText();
		 return cartmessage;
	 }
}
