package Cucumber;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import PageObject.ChooseProductPage;
import PageObject.ChuckOutPage;
import PageObject.HomePage;
import PageObject.ShoppingCartPage;
import PageObject.SignIn;
import PageObject.YourAccount;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StepDefination {
	public  WebDriver driver;
    public Properties pro;
    public FileInputStream fis;
    public static HSSFWorkbook workbook;
	 public static HSSFSheet worksheet;
	 public static DataFormatter formatter= new DataFormatter();
	 
	 static String SheetName= "Sheet1";
	 public  String Res;
	 public int DataSet=-1;
	 String actualPasswordAlert;
	 String email="bbbbk1508@gmail.com";
	 String ActualUser;
	 YourAccount account;
	 
	 
	 @Given("User will Lucch the Amazon application")
	 public void user_will_lucch_the_amazon_application() throws Throwable {
		 WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
			 pro =new Properties();
			 fis =new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//testData.properties");
			 pro.load(fis);
			 driver.get(pro.getProperty("url"));
		}
	
	@Given("^User logged in with Email (.+) and password (.+)$")
	public void user_logged_in_with_email_com_and_password_(String email ,String password) {
		HomePage homePage=new HomePage(driver);
		homePage.loginButtonClick();
		SignIn login = new SignIn(driver);
		 ActualUser =login.SignInuser(email, password);   
	}

	@When("^user will search the Product (.+)$")
	public void user_will_search_the_product_(String Product) throws Throwable {
		YourAccount account=new YourAccount(driver);
		account.SearchProduct();
		
	}

	@And("^User will add the product into the Cart$")
	public void user_will_add_the_product_into_the_cart() throws Throwable {
		ChooseProductPage ChooseProduct=new ChooseProductPage(driver);
		ChooseProduct.selectProduct();
		
	}

	@When("User will Successfully Sign In  Amazon Application with valid credentials")
	public void user_will_successfully_sign_in_amazon_application_with_valid_credentials() {
		String expectedUser=pro.getProperty("expectedUser");
		Assert.assertEquals(ActualUser,expectedUser);
		
	}
	@And("^user will select the address$")
	public void user_will_select_the_address() {
	   
	}

	@When("User will try to logged without entering Email")
	public void user_will_try_to_logged_without_entering_email() {
		HomePage homePage=new HomePage(driver);
		homePage.loginButtonClick();
		
		
	}

	@Then("user will get Alert message")
	public void user_will_get_alert_message() {
		SignIn login = new SignIn(driver);
		String actualEmailAlert=login.SignInuser();
		String expectedEmailAlert=pro.getProperty("expectedEmailAlert");
		Assert.assertEquals(actualEmailAlert, expectedEmailAlert);
	}

	@Then("^User will verify the Order Summary Details$")
	public void user_will_verify_the_order_summary_details() throws InterruptedException, Throwable {
		ShoppingCartPage cart=new ShoppingCartPage(driver);
		cart.selectCartProduct();
		ChuckOutPage chuckOut= new ChuckOutPage(driver);
		ArrayList<Float> arr = chuckOut.chuckOutProcess();
		Float SubTotal = arr.get(0);
		Float DeliveryAmount = arr.get(1);
		Float ActualTotal = arr.get(2);
		float ExpectedTotal = SubTotal+DeliveryAmount;
		Assert.assertEquals(ActualTotal,ExpectedTotal);
		chuckOut.BacktoHomepage();
		YourAccount account=new YourAccount(driver);
		account.SignOut();
	    
	}

	@Then("user will successfully lunch the Amazon application")
	public void user_will_successfully_lunch_the_amazon_application() throws Throwable {
		Properties pro =new Properties();
		FileInputStream fis =new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//testData.properties");
		pro.load(fis);
		
		 driver.get(pro.getProperty("url"));
		String actualHomePageTitle= driver.getTitle();
		String expectedHomepageTitle=pro.getProperty("HomePageTitle");
		Assert.assertEquals(actualHomePageTitle, expectedHomepageTitle);
	}
	@Then("user will close the application")
	public void user_will_close_the_application() {
	  driver.quit();
	}
	@When("User will try to logged without entering Password")
	public void user_will_try_to_logged_without_entering_password() {
		
		
		HomePage homePage=new HomePage(driver);
		homePage.loginButtonClick();
		SignIn login = new SignIn(driver);
		  actualPasswordAlert = login.SignInuser(email);
		
	}
	@Then("user will get password Alert message")
	public void user_will_get_password_alert_message() {
		String expectedPasswordAlert=pro.getProperty("expectedPasswordAlert");
		Assert.assertEquals(actualPasswordAlert, expectedPasswordAlert);
	}
	@When("User will try to logged with email and click on forget Password link")
	public void user_will_try_to_logged_with_email_and_click_on_forget_password_link() {
	    
		
		HomePage homePage=new HomePage(driver);
		homePage.loginButtonClick();
		SignIn login = new SignIn(driver);
		login.forgetPassword(email);
		
	}

	@Then("user will on Password assistance page")
	public void user_will_on_password_assistance_page() {
		String actualPasswordAssistancePageTitle=driver.getTitle();
		String expectedPasswordAssistancePageTitle=pro.getProperty("expectedPasswordAssistancePageTitle");
		Assert.assertEquals(actualPasswordAssistancePageTitle, expectedPasswordAssistancePageTitle);
	}
	@Then("user will succussfully add produt into the cart")
	public void user_will_succussfully_add_produt_into_the_cart() {
		ShoppingCartPage cart=new ShoppingCartPage(driver);
		String ActualCartMessage = cart.cartMessage();
		
		String ActualShoppingcartpageTitle = driver.getTitle();
		System.out.println(ActualShoppingcartpageTitle );
		System.out.println(ActualCartMessage);
		String expectedShoppingcartpageTitle=pro.getProperty("expectedShoppingcartpageTitle");
		String expectedCartMessage=pro.getProperty("expectedCartMessage");
		Assert.assertEquals(ActualShoppingcartpageTitle,expectedShoppingcartpageTitle);
		Assert.assertEquals(ActualCartMessage,expectedCartMessage);
	}
	@When("User will click on Shopping cart button")
	public void user_will_click_on_shopping_cart_button() {
        account=new YourAccount(driver); 
		account.NavigateToCart();
		
	}

	@Then("User will navigate Shopping cart page")
	public void user_will_navigate_shopping_cart_page() {
		String ActualShoppingcartpageTitle = driver.getTitle();
		System.out.println(ActualShoppingcartpageTitle );
		
		String expectedShoppingcartpageTitle=pro.getProperty("expectedShoppingcartpageTitle");
		Assert.assertEquals(ActualShoppingcartpageTitle,expectedShoppingcartpageTitle);
		account.SignOut();
	}

@When("User will click on Proced to buy button")
public void user_will_click_on_proced_to_buy_button() {
	ShoppingCartPage cart=new ShoppingCartPage(driver);
	cart.selectCartProduct();
}

@Then("User will Navigate Chuckout page")
public void user_will_navigate_chuckout_page() {
	String ActualAddressSelectTitle = driver.getTitle();
	String expectedAddressSelectTitle=pro.getProperty("expectedAddressSelectTitle");
	Assert.assertEquals(ActualAddressSelectTitle,expectedAddressSelectTitle);
	ChuckOutPage chuckOut= new ChuckOutPage(driver);
	chuckOut.BacktoHomepage(); 
}
	@Then("user will SignOut from the application")
	public void user_will_logout_from_the_application() {
		YourAccount accout=new YourAccount(driver);
		accout.SignOut();
	}

}
