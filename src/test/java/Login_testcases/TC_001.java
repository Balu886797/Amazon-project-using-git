package Login_testcases;


import java.io.FileInputStream;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CartningTestCase.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TC_001 extends BaseTest {
public  WebDriver driver;


@BeforeTest
public void lunchApplication()
{
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	// driver.get("https://www.amazon.in/");
	
	
		
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
	
	@AfterTest
	public void CloseApplication(){
		driver.close();
	}
	
	
	
}
