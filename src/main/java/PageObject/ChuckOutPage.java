package PageObject;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponets;

public class ChuckOutPage extends AbstractComponets {

	WebDriver driver;

	public ChuckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//input[@data-testid='Address_selectShipToThisAddress']")
	WebElement UseThisAddress;
	
	@FindBy(xpath="//div[@id='banner-image']")
	WebElement amezonIcon;
	 @ FindBy(xpath="//a[contains(text(),'Return to')]")
	 WebElement ReturnToCart;
	 @FindBy(xpath="//span[contains(text(),'Cash on Delivery ')]")
	 WebElement CashOnDeliveryButton;
	 
	 @FindBy(xpath="//div[@id='subtotals-marketplace-table']/table/tbody/tr[1]/td[2]")
	 WebElement Items;
	 @FindBy(xpath="//div[@id='subtotals-marketplace-table']/table/tbody/tr[2]/td[2]")
	 WebElement Delivery;
	 @FindBy(xpath="//div[@id='subtotals-marketplace-table']/table/tbody/tr[4]/td[2]")
	 WebElement orderTotal;
	 
	public ArrayList<Float> chuckOutProcess() throws InterruptedException, Throwable {
		Thread.sleep(5000);
		UseThisAddress.click();	
		ScrollDown();
//		Robot robot = new Robot();
//		robot.keyPress(KeyEvent.VK_CONTROL);
//		robot.keyPress(KeyEvent.VK_MINUS);
//		if(CashOnDeliveryButton.isSelected()) {
//		
//		}
//		else
//		{
//			CashOnDeliveryButton.click();	
//		}
		
		
		String items = Items.getText();
		float subTotal = Float.parseFloat(items.replace("₹","" ));
		String delivery = Delivery.getText();
		float DeliveryAmount=Float.parseFloat(delivery.replace("₹", ""));
		String Total = orderTotal.getText();
		float OrderTotal = Float.parseFloat(Total.replace("₹", ""));
		
		 ArrayList<Float> ar= new ArrayList<Float>();
		 ar.add(subTotal);
		 ar.add(DeliveryAmount);
		 ar.add(OrderTotal);
		return ar;
	}
	
	public void BacktoHomepage() {
		amezonIcon.click();
		ReturnToCart.click();
	}
}
