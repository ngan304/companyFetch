package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part_III_Implicit {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By startButtonBy = By.cssSelector("div[id='start'] button");
	By loadingIconBy = By.cssSelector("#loading");
	By helloWordTextBy = By.cssSelector("div[id='finish'] h4");

	@BeforeClass
	public void beforeClass() {
		// fire fox
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

	}

	@Test
	public void TC_01_Less_Than() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(startButtonBy).click();
		//Sau khi click button start thì mất tới 5s thì Helloword mới xuất hiện trong DOM
		
		
		//Tìm element helloWordTextBy
		// nữa s đầu tiên tìm -> ko thấy
		//nữa s tiếp tìm -> ko thấy
		//nữa s tiếp tìm -> ko thấy
		//nữa s tiếp tìm -> ko thấy --> hết 2 s -> hết timeout
		Assert.assertEquals(driver.findElement(helloWordTextBy).getText(),"Hello World!");
	}
	

	@Test
	public void TC_02_Enough() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(startButtonBy).click();
		
		//Sau khi click button start thì mất tới 5s thì Helloword mới xuất hiện trong DOM
		
				//Tìm element helloWordTextBy
				// nữa s đầu tiên tìm -> ko thấy
				//nữa s tiếp tìm -> ko thấy
		     	//nữa s tiếp tìm -> ko thấy
				//nữa s tiếp tìm -> ko thấy 
				//nữa s tiếp tìm -> ko thấy
				//nữa s tiếp tìm -> ko thấy 
				//nữa s tiếp tìm -> ko thấy
				//nữa s tiếp tìm -> ko thấy 
				//nữa s tiếp tìm -> ko thấy
				//nữa s tiếp tìm ->  thấy 
		
		Assert.assertEquals(driver.findElement(helloWordTextBy).getText(),"Hello World!");

	}

	@Test
	public void TC_03_Great_Than() {
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(startButtonBy).click();
		
		//Sau khi click button start thì mất tới 5s thì Helloword mới xuất hiện trong DOM
		
		//Tìm element helloWordTextBy
		// nữa s đầu tiên tìm -> ko thấy
		//nữa s tiếp tìm -> ko thấy
     	//nữa s tiếp tìm -> ko thấy
		//nữa s tiếp tìm -> ko thấy 
		//nữa s tiếp tìm -> ko thấy
		//nữa s tiếp tìm -> ko thấy 
		//nữa s tiếp tìm -> ko thấy
		//nữa s tiếp tìm -> ko thấy 
		//nữa s tiếp tìm -> ko thấy
		//nữa s tiếp tìm ->  thấy --giay th5 
		//nữa s tiếp theo ko tìm ->  giây thứ 6/7/8 nó sẽ nhảy qua step khác làm            
		
		Assert.assertEquals(driver.findElement(helloWordTextBy).getText(),"Hello World!");

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}