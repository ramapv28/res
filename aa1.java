package revive.test1;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class aa1 {
	WebDriver driver = new ChromeDriver();

	@Test
	public void testExample() {
		driver.get("https://facebook.com");
		driver.manage().window().maximize();
		driver.findElement(By.id("wrongId")).click(); // Fails on purpose
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		
		if(ITestResult.FAILURE == result.getStatus()) {
			System.out.println("❌ Test Failed -> Capturing Screenshot...");

			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(src, new File("C:\\Users\\Hi\\testing\\revive.test\\test-output\\Failed_" + result.getName() + ".png"));

			System.out.println("Screenshot Saved for Failed Test: " + result.getName());
		}
		driver.quit();
	}
}
	


