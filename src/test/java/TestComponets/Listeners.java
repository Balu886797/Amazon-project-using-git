package TestComponets;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Resources.ExtentReportDetails;
import io.cucumber.core.resource.Resource;

public class Listeners extends StandAloneTest implements ITestListener{
 ExtentTest test;
 ExtentReports extent =ExtentReportDetails.getReportObject();
 ThreadLocal<ExtentTest> extentTest =new ThreadLocal<ExtentTest>();
	@Override
	public void onTestStart(ITestResult result) {
		test =extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		extentTest.get().fail(result.getThrowable());
		try
		{
			driver =(WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			
		 }
		
		 catch (Exception e) {
			
			e.printStackTrace();
		} 
		
		String filePath =null;
		try {
			filePath=getScreenshot(result.getMethod().getMethodName());
			
		}
		catch (IOException e){
			e.printStackTrace();
		} 
		extentTest.get().addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
		
	}

	

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	
}
