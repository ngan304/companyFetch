package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Web_Browser_Execise {
	WebDriver driver;

	@BeforeClass 
	public void beforeClass() {
		//fire fox 
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/");
		
			}

	@Test
	public void TC_01_Vierfy_Url() {
		//Click vào My Account ở dưới footer
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title='My Account']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		// Click để chuyển vào trang Register
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_Verify_Title(){
		//Click vào My Account ở dưới footer
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title='My Account']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getTitle(),"Customer Login");
		
		// Click để chuyển vào trang Register
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
	}

	@Test
	public void TC_03_Verify_Navigation() {
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title='My Account']")).click();
		
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		Assert.assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/create/");
		
		driver.navigate().back();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.navigate().forward();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.getTitle(),"Create New Customer Account");
		
		
	}
	@Test
	public void TC_04_Verify_PageSource() {
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title='My Account']")).click();
		sleepInSecond(3);
		//Khai báo biến và khởi tạo biến tại màn hình login
		String currentPageSouce=driver.getPageSource();
		
		Assert.assertTrue(currentPageSouce.contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		sleepInSecond(3);
		
		// Khởi tạo lại giá trị mới tại màn hình register
		currentPageSouce=driver.getPageSource();
		Assert.assertTrue(currentPageSouce.contains("Create an Account"));

	}
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	}

}