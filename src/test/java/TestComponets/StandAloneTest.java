package TestComponets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import PageObject.ShoppingCartPage;
import PageObject.ChooseProductPage;
import PageObject.ChuckOutPage;
import PageObject.HomePage;
import PageObject.SignIn;
import PageObject.YourAccount;
import io.cucumber.java.vi.Cho;
import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(TestComponets.Listeners.class)
public class StandAloneTest extends BaseTest {
	public  WebDriver driver;
	public Properties pro;
	 public static HSSFWorkbook workbook;
	 public static HSSFSheet worksheet;
	 public static DataFormatter formatter= new DataFormatter();
	 
	 static String SheetName= "Sheet1";
	 public  String Res;
	// Write obj1=new Write();
	 public int DataSet=-1;
	@BeforeSuite
	public void lunchApplication()
	{
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		 driver.get("https://www.amazon.in/");
		 
		 
		 
	}
	@DataProvider
	 public static Object[][] ReadVariant() throws IOException
	 {
	 FileInputStream fileInputStream= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//userdetails.xls"); //Excel sheet file location get mentioned here
	 workbook = new HSSFWorkbook (fileInputStream);
	 worksheet=workbook.getSheet(SheetName);
	       HSSFRow Row=worksheet.getRow(0); 
	   
	     int RowNum = worksheet.getPhysicalNumberOfRows();
	     int ColNum= Row.getLastCellNum(); 
	     
	     Object Data[][]= new Object[RowNum-1][ColNum]; 	     
	     for(int i=0; i<RowNum-1; i++) 
	     {  
	     HSSFRow row= worksheet.getRow(i+1);
	     
	     for (int j=0; j<ColNum; j++) //Loop work for colNum
	     {
	     if(row==null)
	     Data[i][j]= "";
	     else 
	     {
	     HSSFCell cell= row.getCell(j);
	     if(cell==null)
	     Data[i][j]= ""; //if it get Null value it pass no data 
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
	

public String getScreenshot(String testCaseName) throws IOException {
		
		TakesScreenshot ts =(TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file =new File(System.getProperty("user.dir")+"//reports//"+testCaseName + ".png");
		
		FileUtils.copyFile( source, file);
		
		return System.getenv("user.dir")+"//reports//"+testCaseName+".png";
	}
	@Test(dataProvider="ReadVariant")
	public void SatandAloneTest(String email, String pass) throws Throwable {
		HomePage homePage=new HomePage(driver);
		homePage.loginButtonClick();
		SignIn login = new SignIn(driver);
		login.SignInuser(email, pass);
		YourAccount account=new YourAccount(driver);
		account.SearchProduct();
		ChooseProductPage ChooseProduct=new ChooseProductPage(driver);
		ChooseProduct.selectProduct();
		account.NavigateToCart();
		ShoppingCartPage cartPage=new ShoppingCartPage(driver);
		cartPage.selectCartProduct();
		ChuckOutPage chuckOutPage=new ChuckOutPage(driver);
		chuckOutPage.chuckOutProcess();
		
		
	}
}
