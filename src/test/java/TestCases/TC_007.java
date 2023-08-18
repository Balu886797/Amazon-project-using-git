package TestCases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObject.HomePage;
import PageObject.SignIn;
import PageObject.YourAccount;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_007 {

	public  WebDriver driver;
    public Properties pro;
    public FileInputStream fis;
    public static HSSFWorkbook workbook;
	 public static HSSFSheet worksheet;
	 public static DataFormatter formatter= new DataFormatter();
	 
	 static String SheetName= "Sheet1";
	 public  String Res;
	 public int DataSet=-1;
	@BeforeTest
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
		
		@AfterTest
		public void CloseApplication(){
			driver.close();
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
