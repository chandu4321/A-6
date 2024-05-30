package reports;

import java.time.LocalDateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class LearningExtentReport {

	@Test
	public void script() {
		WebDriver driver;
		ScreenShotMethod ss= new ScreenShotMethod();
		LocalDateTime dateTime= LocalDateTime.now();
		String date= dateTime.toString().replace(":", "-");
		ExtentReports reports= new ExtentReports();
		ExtentSparkReporter reporter= new ExtentSparkReporter("./extentReports/"+date+".html");
		reports.attachReporter(reporter);
		ExtentTest extentTest= reports.createTest("login");
		driver= new ChromeDriver();
		extentTest.log(Status.INFO, "browser is launched");
		driver.manage().window().maximize();
		extentTest.log(Status.INFO, "browser is maximised");
		driver.get("https://demowebshop.tricentis.com/");
		extentTest.log(Status.INFO, "Demowebshop is launched");
		driver.findElement(By.linkText("Log in")).click();
		extentTest.log(Status.INFO, "user clicked on login button");
		driver.findElement(By.id("Email")).sendKeys("bangalore541@gmail.com");
		extentTest.log(Status.INFO, "user entered email");
		driver.findElement(By.id("Password")).sendKeys("banga123");
		extentTest.log(Status.INFO, "user entered password");
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		extentTest.log(Status.INFO, "logined");
		try {
			if(driver.findElement(By.linkText("Log out")).isDisplayed()) {
				extentTest.pass(MediaEntityBuilder.createScreenCaptureFromPath(ss.takeScreenShot(driver)).build());
			}
		}catch (Exception e) {
			// TODO: handle exception
			extentTest.fail(MediaEntityBuilder.createScreenCaptureFromPath(ss.takeScreenShot(driver)).build());
		}
		reports.flush();
	}
}
