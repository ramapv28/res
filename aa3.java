package revive.test1;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class aa3 {
	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeTest
	public void start() {
		ExtentSparkReporter reporter=new ExtentSparkReporter("reports/ExtentReports/Rama1/ExtentReport.html");
		extent=new ExtentReports();
		extent.attachReporter(reporter);
		
	}
	
	@BeforeMethod
	public void initial() {
		driver=new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.manage().window().maximize();
		
		test=extent.createTest("Login facebook testcase");
		
	}
	
	@Test
	public void loginFacebookTest() {
		driver.findElement(By.id("qqqq")).click();//intentionally giving wrong location
	}
	
	@AfterMethod
	public void tear(ITestResult result)throws Exception{
		if(result.getStatus()==ITestResult.FAILURE) {
			//take screenshot
			TakesScreenshot ts=(TakesScreenshot)driver;
			File source=ts.getScreenshotAs(OutputType.FILE);
			String path="C:\\Users\\Hi\\testing\\revive.test\\reports"+result.getName()+".png";
			FileHandler.copy(source, new File(path));
			
			//log failure+ attach screenshot
			
			test.fail("Test failed:" +result.getName()).addScreenCaptureFromPath("../"+path);
		}
		else {
			test.pass("Test passed");
		}
		driver.quit();
	}
	@AfterTest
	public void end() {
		extent.flush();//generate the report
	}

}
