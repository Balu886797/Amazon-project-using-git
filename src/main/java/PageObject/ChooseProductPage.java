package PageObject;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponets;

public class ChooseProductPage extends AbstractComponets{

	WebDriver driver;

	public ChooseProductPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath="(//a[@target=\"_blank\"])[2]")
	WebElement product;
	
	@FindBy(xpath="//input[@value=\"Add to Cart\"]")
	WebElement addToCart;
	 @ FindBy(xpath="//a[@class='a-link-normal close-button']")
	 WebElement closeButton;
	public void selectProduct() throws Throwable {

		product.click();
		Set<String> allwin = driver.getWindowHandles();
		ArrayList<String> ar=new ArrayList<String>(allwin);
		Object cartWindow = ar.get(1);
		driver.switchTo().window( (String) cartWindow);
		ScrollDown();
		addToCart.click();
			
	}
}
