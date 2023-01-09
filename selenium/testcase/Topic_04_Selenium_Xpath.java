package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;


import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_04_Selenium_Xpath {
	//Khai báo 1 biến đại diện cho Selenium Webdriber
	WebDriver driver; // là hàm tương tác với browser

	@BeforeClass
	public void beforeClass() {
		//Mở trình duyệt Firefox lên
		driver= new FirefoxDriver();
		
		//Set timeout để tìm element
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		
	}

	@Test
	public void TC_01_Login_Empty_Email_Password() {
		// Open Home page
	driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	//Open My Account page at footer
	driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
		
	driver.findElement(By.id("email")).sendKeys("");
	driver.findElement(By.id("pass")).sendKeys("");
	driver.findElement(By.name("send")).click();
	
	Assert.assertEquals(driver.findElement(By.xpath("//div[@id=\"advice-required-entry-email\"]")).getText(),"This is a required field.");
	
	Assert.assertEquals(driver.findElement(By.xpath("//div[@id=\"advice-required-entry-pass\"]")).getText(),"This is a required field.");
	
	}
	@Test
	public void TC_02_Login_Invalid_Email() {
		// Open Home page
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		//Open My Account page at footer
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
		driver.findElement(By.id("email")).sendKeys("1234@123");
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		driver.findElement(By.name("send")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id=\"advice-validate-email-email\"]")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
		
		
	}
	@Test
	public void TC_03_Login_Invalid_Passwordl() {
		// Open Home page
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		//Open My Account page at footer
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
		driver.findElement(By.id("email")).sendKeys("Kimngan@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");
		driver.findElement(By.name("send")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id=\"advice-validate-password-pass\"]")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");
	
		
	}
	@Test
	public void TC_04_Login_Incorrect_Passwordl() {
		// Open Home page
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		//Open My Account page at footer
		driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
		
		driver.findElement(By.id("email")).sendKeys("ngankim@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123123");
		driver.findElement(By.name("send")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class=\"error-msg\"]//span")).getText(),"Invalid login or password.");
		
	}
	
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
