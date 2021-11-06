package webdriver;



import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Popup {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath=System.getProperty("user.dir");

	@BeforeClass 
	public void beforeClass() {
		//fire fox 
		System.setProperty("webdriver.gecko.driver", projectPath+ ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}

	//@Test
	public void TC_01_Fixed_Popup() {
		driver.get("https://ngoaingu24h.vn/");
		sleepInSecond(3);
		driver.findElement(By.cssSelector("button.login_")).click();
		
		//Verify pop up có hiển thị
		Assert.assertTrue(driver.findElement(By.cssSelector("#modal-login-v1>div")).isDisplayed()); 
		
		sleepInSecond(3);
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("Password@123");
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//button[@data-text='Đăng nhập']")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(),"Tài khoản không tồn tại!");
		
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		sleepInSecond(3);
		
		Assert.assertFalse(driver.findElement(By.cssSelector("#modal-login-v1>div")).isDisplayed()); 
		
		
		
	}
	//@Test
	public void TC_02_Random_In_DOM() {
		driver.get("https://blog.testproject.io/");
		sleepInSecond(2);
		
		WebElement Popup=driver.findElement(By.cssSelector("#mailch-bg>.mailch-wrap"));
		//Nếu pop up xuất hiện thì close nó đi
		if (Popup.isDisplayed()) {
			System.out.println("Popup is displayed");
			driver.findElement(By.cssSelector("#close-mailch")).click();
			sleepInSecond(2);
		} else {
			System.out.println("Popup is Not displayed");
		}
		//nếu pop up ko xuất hiện thì thực hiện các step sau lun 
		driver.findElement(By.cssSelector("#search-2 .search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("#search-2 span.glass")).click();
		sleepInSecond(3);
		
		//Verify title chứa text = Selenium
		List<WebElement> articleTitle= driver.findElements(By.cssSelector(".post-title>a"));
		//Dùng vòng lặp for để xét duyệt cái list
		for (WebElement article : articleTitle) {
			Assert.assertTrue(article. getText().contains("Selenium"));
			
		}
	}

	@Test
	public void TC_03_Random_Not_In_Dom(){
		driver.get("https://shopee.vn/");
		sleepInSecond(8);
		
		//nếu element ko có trong dom thì hàm findeElement ko tìm thấy
		//Chờ hết time out của implicate 
		//Đánh fail testcase ngay tại step đó lun 
		//Throw ra 1 exception: Nosuch Element
		//=> mình sẽ dùng  List<Webelement>

		
		//cơ chế của list , nếu ko có trong DOM thì hàm findelement ko tìm thấy
		//Chờ hết timeout của implicate rồi chuyển sang step tiếp theo
		//Nó sẽ trả về 1 list empty (size =0)
		//ko đánh fail testcase
		//Ko throw exception
		
		List<WebElement> pop_up=driver.findElements(By.cssSelector("div.shopee-popup img"));
		if(pop_up.size()>0 && pop_up.get(0).isDisplayed()) {
			//close pop up
			System.out.println("Pop up is displayed");
			driver.findElement(By.cssSelector(".shopee-popup__close-btn")).click();
			sleepInSecond(2);
		}else {
			System.out.println("Pop up is Not displayed");
		}
		driver.findElement(By.cssSelector(".shopee-searchbar-input__input")).sendKeys("iphone");
	}
	

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	}

}