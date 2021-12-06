package webdriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part_VI_Mixing {
	WebDriver driver;
	WebDriverWait explicitWait; // thư viện WebDriverWait
	String projectPath = System.getProperty("user.dir");
	WebElement element;
	

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		

	}

	// @Test
	public void TC_01_Element_Found_Explicit_implicit() {
		 driver.get("https://vi-vn.facebook.com/");
		 
		 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 explicitWait= new WebDriverWait(driver, 5);
		 
		 //implicit 
		 System.out.println("Start implicit:" + getDateTimeNow());
		 driver.findElement(By.cssSelector("input#email"));
		 System.out.println("End  implicit:" + getDateTimeNow());
		 
		 //explicit
		 System.out.println("Start explicit:" + getDateTimeNow());
		 explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		 System.out.println("End  explicit:" + getDateTimeNow());
		 
	
	}

	 //@Test
		public void TC_02_01_Element_Not_Found_Only_Implicit() {
			 driver.get("https://vi-vn.facebook.com/");
			 
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 
			 //implicit 
			 System.out.println("Start implicit:" + getDateTimeNow());
			 try {
				driver.findElement(By.cssSelector("input#testing"));
			} finally {
				System.out.println("End  implicit:" + getDateTimeNow());
			}
	 }
		//@Test
		public void TC_02_02_Element_Not_Found_Only_explicit() {
			 driver.get("https://vi-vn.facebook.com/");
			 
			 explicitWait= new WebDriverWait(driver, 5);
			 
			 //implicit 
			 System.out.println("Start implicit:" + getDateTimeNow());
			 try {
				 //tham số By
				 explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#testing")));
				 
				 // tham số  Webelement
				 explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#testing"))));
			} finally {
				System.out.println("End  implicit:" + getDateTimeNow());
			}
	 }
		
		@Test
		public void TC_02_3_Element_Found_Explicit_implicit() {
			 driver.get("https://vi-vn.facebook.com/");
			 
			//implicit > Explicit
			 driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			 explicitWait= new WebDriverWait(driver, 8);
			 
			 //implicit
			 System.out.println("Start implicit:" + getDateTimeNow());
			 // Nhận timeout của implicit
			 try {
				driver.findElement(By.cssSelector("input#tiki"));
			} catch (Exception e1) {
				
			}
			 System.out.println("End  implicit:" + getDateTimeNow());
			 
			 //explicit
			 System.out.println("Start explicit:" + getDateTimeNow());
			// Nhận timeout của cả 2 trong hàm visibilityOfElementLocated
			 //Driver.findElement(locator) => bị ảnh hưởng timeout của implicit: 5
			 //elementIfVisible => bị ảnh hưởng timeout của explicit: 3s
			 
			try {
				element = explicitWait
						.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#tiki")));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			 System.out.println("End  explicit:" + getDateTimeNow());
			 Assert.assertNull(element);
			 
		
		}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
	public String getDateTimeNow() {
		Date date= new Date();
		return date.toString();
	}
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}