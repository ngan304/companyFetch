package webdriver;

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

public class Topic_17_Wait_Part_I_Element_Status {
	WebDriver driver;
	WebDriverWait explicitWait; // thư viện WebDriverWait
	String projectPath = System.getProperty("user.dir");
	By confirmEmailTextbox = By.xpath("//input[@name='reg_email_confirmation__']");

	@BeforeClass
	public void beforeClass() {
		// fire fox
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		explicitWait = new WebDriverWait(driver, 15);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);// cái này khác cái ở trên
		driver.get("https://vi-vn.facebook.com/");

	}

	// @Test
	public void TC_01_Visible() {
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		driver.findElement(By.name("reg_email__")).sendKeys("automation@gmail.com");

		// chờ cho element dược hiển thị
		// Hiển thị trên UI
		// Có trong DOM

		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(confirmEmailTextbox));
		// visibilityOfAllElementsLocatedBy : chờ nó hiển thị
	}

	// @Test
	public void TC_02_Invisible_01() {
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();

		// Case1: Element ko có tren UI và vẫn có trong HTML (DOM)
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(confirmEmailTextbox));
		// invisibilityOfElementLocated chờ nó ko hiển thị

	}

	// @Test
	public void TC_02_Invisible_02() {
		driver.navigate().refresh();
		// Case 2: Element ko có trên UI và ko có trong HTML (DOM)
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(confirmEmailTextbox));

	}

	//@Test
	public void TC_03_Presence() {
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();

		// Wait presence (in DOM- ko có trên UI)
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(confirmEmailTextbox));

		driver.findElement(By.name("reg_email__")).sendKeys("automation@gmail.com");
		// Wait presence (in DOM-  có trên UI)
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(confirmEmailTextbox));

	}

	//@Test
	public void TC_04_Staleness_01() {
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		WebElement emailTextbox=driver.findElement(By.name("reg_email__"));
		
		driver.findElement(By.xpath("//div[text()='Đăng ký']/parent::div/preceding-sibling::img")).click();
		
		// Wait email textbox is staleness,stalenessOf : chỉ dùng với web element thôi ko dùng với BY
		explicitWait.until(ExpectedConditions.stalenessOf(emailTextbox));		
		

	}
	@Test
	public void TC_04_Staleness_02() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		driver.findElement(By.id("SubmitCreate")).click();
		
		//1
		WebElement textError =driver.findElement(By.id("create_account_error"));
		
		//2 -> sau khi refresh nó làm cho element tại bước số 1 bị update lại - no longer attch to 
		//the Dome
		driver.navigate().refresh();
		
		//3- wait element staleness: wait cho 1 element ko còn trạng thái cũ
		explicitWait.until(ExpectedConditions.stalenessOf(textError));
		
		//StaleElementException: element đã bị thay đổi trạng thái rồi mà mình vẫn lôi ra để thao tác
		
		
		
		
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}