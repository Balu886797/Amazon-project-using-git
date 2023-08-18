package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportDetails {
	
	public static ExtentReports getReportObject() {
	String path=System.getProperty("user.dir")+"//Reports//index.html";
	ExtentSparkReporter reporter =new ExtentSparkReporter(path);
	reporter.config().setReportName("Amazon application Automation test Reports");
	
	ExtentReports extent =new ExtentReports();
	extent.attachReporter(reporter);
	extent.setSystemInfo("Tester ", "Balu");
	return extent;
   

}
}