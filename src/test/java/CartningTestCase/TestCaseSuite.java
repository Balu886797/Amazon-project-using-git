package CartningTestCase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import PageObject.ChooseProductPage;
import PageObject.ChuckOutPage;
import PageObject.HomePage;
import PageObject.ShoppingCartPage;
import PageObject.SignIn;
import PageObject.YourAccount;
import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(TestComponets.Listeners.class)
public class TestCaseSuite {

	public  WebDriver driver;
    public Properties pro;
    public FileInputStream fis;
    public static HSSFWorkbook workbook;
	 public static HSSFSheet worksheet;
	 public static DataFormatter formatter= new DataFormatter();
	 
	 static String SheetName= "Sheet1";
	 public  String Res;
	 public int DataSet=-1;
	@BeforeMethod
	public void lunchApplication() throws Throwable
	{
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		 pro =new Properties();
		 fis =new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//testData.properties");
		 pro.load(fis);
		 driver.get(pro.getProperty("url"));
		
			
	}

	
	@Test
	public void TC_001() throws Throwable {
		
		Properties pro =new Properties();
		FileInputStream fis =new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//testData.properties");
		pro.load(fis);
		
		 driver.get(pro.getProperty("url"));
		String actualHomePageTitle= driver.getTitle();
		String expectedHomepageTitle=pro.getProperty("HomePageTitle");
		Assert.assertEquals(actualHomePageTitle, expectedHomepageTitle);
	}
	@Test(dataProvider="ReadVariant")
	public void TC_002(String email, String pass) throws Throwable {

	    String expectedUser=pro.getProperty("expectedUser");			
		HomePage homePage=new HomePage(driver);
		homePage.loginButtonClick();
		SignIn login = new SignIn(driver);
		String ActualUser = login.SignInuser(email, pass);
		Assert.assertEquals(ActualUser,expectedUser);
		YourAccount accout=new YourAccount(driver);
		accout.SignOut();
	}
	@Test
	public void TC_003() throws Throwable {

		HomePage homePage=new HomePage(driver);
		homePage.loginButtonClick();
		SignIn login = new SignIn(driver);
		String actualEmailAlert=login.SignInuser();
		String expectedEmailAlert=pro.getProperty("expectedEmailAlert");
		Assert.assertEquals(actualEmailAlert, expectedEmailAlert);
		
	}
	@Test(dataProvider="ReadVariant")
	public void TC_004(String email, String pass) throws Throwable {
	    String expectedPasswordAlert=pro.getProperty("expectedPasswordAlert");
		HomePage homePage=new HomePage(driver);
		homePage.loginButtonClick();
		SignIn login = new SignIn(driver);
		String actualPasswordAlert = login.SignInuser(email);
		Assert.assertEquals(actualPasswordAlert, expectedPasswordAlert);
	}
	@Test(dataProvider="ReadVariant")
	public void TC_005(String email, String pass) throws Throwable {
	    String expectedPasswordAssistancePageTitle=pro.getProperty("expectedPasswordAssistancePageTitle");
		HomePage homePage=new HomePage(driver);
		homePage.loginButtonClick();
		SignIn login = new SignIn(driver);
		login.forgetPassword(email);
		String actualPasswordAssistancePageTitle=driver.getTitle();
		System.out.println(actualPasswordAssistancePageTitle);
		Assert.assertEquals(actualPasswordAssistancePageTitle, expectedPasswordAssistancePageTitle);
	}
	@Test(dataProvider="ReadVariant")
	public void TC_006(String email, String pass) throws Throwable {

	    			
		HomePage homePage=new HomePage(driver);
		homePage.loginButtonClick();
		SignIn login = new SignIn(driver);
		login.SignInuser(email, pass);
		YourAccount account=new YourAccount(driver);
		account.SearchProduct();
		ChooseProductPage ChooseProduct=new ChooseProductPage(driver);
		ChooseProduct.selectProduct();
		ShoppingCartPage cart=new ShoppingCartPage(driver);
		String ActualCartMessage = cart.cartMessage();
		
		String ActualShoppingcartpageTitle = driver.getTitle();
		System.out.println(ActualShoppingcartpageTitle );
		System.out.println(ActualCartMessage);
		String expectedShoppingcartpageTitle=pro.getProperty("expectedShoppingcartpageTitle");
		String expectedCartMessage=pro.getProperty("expectedCartMessage");
		Assert.assertEquals(ActualShoppingcartpageTitle,expectedShoppingcartpageTitle);
		Assert.assertEquals(ActualCartMessage,expectedCartMessage);
		
		YourAccount accout=new YourAccount(driver);
		accout.SignOut();
	}
	@Test(dataProvider="ReadVariant")
	public void TC_007(String email, String pass) throws Throwable {

	    			
		HomePage homePage=new HomePage(driver);
		homePage.loginButtonClick();
		SignIn login = new SignIn(driver);
		login.SignInuser(email, pass);
		YourAccount account=new YourAccount(driver); 
		account.NavigateToCart();
		String ActualShoppingcartpageTitle = driver.getTitle();
		System.out.println(ActualShoppingcartpageTitle );
		
		String expectedShoppingcartpageTitle=pro.getProperty("expectedShoppingcartpageTitle");
		Assert.assertEquals(ActualShoppingcartpageTitle,expectedShoppingcartpageTitle);
		account.SignOut();
	}
	
	@Test(dataProvider="ReadVariant")
	public void TC_008(String email, String pass) throws Throwable {

	    			
		HomePage homePage=new HomePage(driver);
		homePage.loginButtonClick();
		SignIn login = new SignIn(driver);
		login.SignInuser(email, pass);
		YourAccount account=new YourAccount(driver); 
		account.NavigateToCart();
		ShoppingCartPage cart=new ShoppingCartPage(driver);
		cart.selectCartProduct();
		
		String ActualAddressSelectTitle = driver.getTitle();
		
		
		String expectedAddressSelectTitle=pro.getProperty("expectedAddressSelectTitle");
		Assert.assertEquals(ActualAddressSelectTitle,expectedAddressSelectTitle);
		ChuckOutPage chuckOut= new ChuckOutPage(driver);
		chuckOut.BacktoHomepage();
		account.SignOut();
	}
	@Test(dataProvider="ReadVariant")
	public void TC_009(String email, String pass) throws Throwable {			
		HomePage homePage=new HomePage(driver);
		homePage.loginButtonClick();
		SignIn login = new SignIn(driver);
		login.SignInuser(email, pass);
		YourAccount account=new YourAccount(driver); 
		account.NavigateToCart();
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
		account.SignOut();
	}
		@AfterMethod
		public void CloseApplication(){
			driver.quit();
		}
		@DataProvider
		 public static Object[][] ReadVariant() throws IOException
		 {
		 FileInputStream fileInputStream= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//userdetails.xls");
		 workbook = new HSSFWorkbook (fileInputStream);
		 worksheet=workbook.getSheet(SheetName);
		       HSSFRow Row=worksheet.getRow(0); 
		   
		     int RowNum = worksheet.getPhysicalNumberOfRows();
		     int ColNum= Row.getLastCellNum(); 
		     
		     Object Data[][]= new Object[RowNum-1][ColNum]; 	     
		     for(int i=0; i<RowNum-1; i++) 
		     {  
		     HSSFRow row= worksheet.getRow(i+1);
		     
		     for (int j=0; j<ColNum; j++) 
		     {
		     if(row==null)
		     Data[i][j]= "";
		     else 
		     {
		     HSSFCell cell= row.getCell(j);
		     if(cell==null)
		     Data[i][j]= ""; 
		     else
		     {
		     String value=formatter.formatCellValue(cell);
		     Data[i][j]=value;
		     }
		     }
		     }
		     }
		 
		     return Data;
		    }
}
