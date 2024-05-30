package beauty;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LaunchLoreal_Test {

	@Test
	public void loreal() throws InterruptedException {
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
	//	driver.get("https://www.lorealparis.co.in/");
		String url = System.getProperty("url");
		driver.get(url);
		Thread.sleep(2000);
		driver.quit();
	}
}
