package reports;

import java.time.LocalDateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Script01 {

	@Test
	public void script1() throws InterruptedException {
		WebDriver driver;
		ScreenShotMethod ss= new ScreenShotMethod();
		LocalDateTime dateTime= LocalDateTime.now();
		String date= dateTime.toString().replace(":", "-");
		ExtentReports reports= new ExtentReports();
		ExtentSparkReporter reporter= new ExtentSparkReporter("./extentReports/"+date+".html");
		reports.attachReporter(reporter);
		ExtentTest test= reports.createTest("books");
		driver= new ChromeDriver();
		driver.manage().window().maximize();
		test.log(Status.INFO, "browser is maximised");
		driver.get("https://demowebshop.tricentis.com/");
		test.log(Status.INFO, "Demowebshop is launched");
		driver.findElement(By.linkText("Books")).click();
		test.log(Status.INFO, "books page is displayed");
		Thread.sleep(2000);
		test.log(Status.INFO, "wait for 2sec");
		driver.findElement(By.linkText("Computing and Internet")).click();
		test.log(Status.INFO, "user clicked on computer & internet books");
		Thread.sleep(2000);
		test.log(Status.INFO, "wait for 2sec");
		driver.findElement(By.id("add-to-cart-button-13")).click();
		test.log(Status.INFO, "product added to cart");
		try {
			if(driver.getTitle().contains("Demo Web Shop. Computing and Internet")) {
				test.pass(MediaEntityBuilder.createScreenCaptureFromPath(ss.takeScreenShot(driver)).build());
			}
		}catch (Exception e) {
			// TODO: handle exception
			test.fail(MediaEntityBuilder.createScreenCaptureFromPath(ss.takeScreenShot(driver)).build());
		}
		reports.flush();
	}
}
