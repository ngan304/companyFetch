package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part_IV_Static {
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
		

		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(startButtonBy).click();
		
		//sau click xong, time bị thiếu
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(helloWordTextBy).getText(),"Hello World!");
	}
	

	@Test
	public void TC_02_Enough() {
		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(startButtonBy).click();
		
		//Time vừa đủ
		//Khó biết bao nhiêu s sẽ đủ
		//lúc này nó là xx time
		// lúc khác nó lại là nhỏ hơn xx time
		// lúc khác nó lại là lớn hơn xx time
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElement(helloWordTextBy).getText(),"Hello World!");

	}

	@Test
	public void TC_03_Great_Than() {
	
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.findElement(startButtonBy).click();
		//Time bị dư và pass testcase
		//chạy được/ chạy đúng nhưng chưa tối ưu
		// 1 step dư mất 5s
		//set sleep này cho nhiều step trong 1 testcase
		//1 test case có 30 steps
		// set 1 nữa các step: 15 steps x 5s =75s ~ 1min15s/ 1 test case
		//200-300 tcs x 1m15s ~ >3 tiếng (dư) : rât là lãng phí
		sleepInSecond(10);
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