package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_03_Selenium_Locator {
	//Khai báo 1 biến đại diện cho Selenium Webdriber
	WebDriver driver; // là hàm tương tác với browser

	@BeforeClass
	public void beforeClass() {
		//Mở trình duyệt Firefox lên
		driver= new FirefoxDriver();
		
		//Set timeout để tìm element
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		
		//Mở application lên (AUT, SUT)
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	//@Test
	public void TC_01_FindElement() {
	
		//Single element: weblement
		WebElement loginButton=driver.findElement(By.className(""));
		loginButton.click();
		
		
		// findElement: tìm element
		// By.xxx: vs locator nào
		// Action gì lên element đó: click/ sendkey/ getText/...
		
		
		//Multiple element: List<webelement>
		//WenElement là nhưng hàm tương tác với element
		List<WebElement>buttons=driver.findElements(By.className(""));
		buttons.get(0).click();
	}
	
	//@Test
		public void TC_02_ID() {
			// Selenium locator
		driver.findElement(By.id("send2")).click();
		
		// Verify email error message xuất hiện, thi co ham isDisplay
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
			
	}
	
	///@Test
	//Class dùng 1 phần class hoyyy
	public void TC_03_Class() {
		driver.navigate().refresh();
		
		driver.findElement(By.className("validate-password")).sendKeys("123456789");
		
	}
	
	//@Test
	public void TC_04_Name() {
		
		driver.navigate().refresh();
		driver.findElement(By.name("send")).click();
		// Verify email error message xuất hiện, thi co ham isDisplay
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
	}
	
	
	//@Test
	//tìm có bao nhiêu element giống nhau thì hay dùng tag name
	public void TC_05_Tagname() {
		driver.navigate().refresh();
		//hiển thị hết hết tất cả đường link tại màn hình này, sau đó gettext ra
		List<WebElement>loginPageLinks=driver.findElements(By.tagName("a"));
		//dùng vòng lặp for
		
		for (WebElement webElement : loginPageLinks) {
			System.out.println(webElement.getText());
			
		}		
	}
	
	//@Test
	// Dùng LinkText khi muốn lấy toàn bộ chuỗi của link đó
	public void TC_06_LinkText() {
		driver.navigate().refresh();
		driver.findElement(By.linkText("Forgot Your Password?")).click();
		
		Assert.assertTrue(driver.findElement(By.id("email_address")).isDisplayed());
		
	}
	
	//@Test
	//PartialLinkText lấy 1 phần text là có thể chạy được rồi
	public void TC_07_PartialLinkText() {
		driver.findElement(By.partialLinkText("Back to")).click();
		Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
	}
	
	@Test
	public void TC_08_Css() {
		
		driver.findElement(By.cssSelector("#email")).sendKeys("Kim@gmail.com");
		driver.findElement(By.cssSelector("input[name='login[password]'")).sendKeys("123456789");
		
		
	}
	
	@Test
	public void TC_09_Xpath() {
		driver.navigate().refresh();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("Kim@gmail.com");
		driver.findElement(By.xpath("//input[@title='Password']")).sendKeys("123456789");
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

}
