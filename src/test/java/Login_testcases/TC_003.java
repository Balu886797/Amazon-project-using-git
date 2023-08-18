package Login_testcases;

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
import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_003 {

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

		@Test
		public void TC_003() throws Throwable {

			HomePage homePage=new HomePage(driver);
			homePage.loginButtonClick();
			SignIn login = new SignIn(driver);
			String actualEmailAlert=login.SignInuser();
			String expectedEmailAlert=pro.getProperty("expectedEmailAlert");
			Assert.assertEquals(actualEmailAlert, expectedEmailAlert);
			
		}
		
		@AfterTest
		public void CloseApplication(){
			//driver.close();
		}
		
}
