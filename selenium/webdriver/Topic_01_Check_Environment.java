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

public class Topic_01_Check_Environment {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	public String email = "trinhkim6@gmail.com";
	public String pass = "Trinhkim6#";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://vnexpress.net/trong-tre-du-co-ve-lam-to-4545196.html");
	}

	@Test
	public void TC_01_CreateNewAccount() {
		driver.findElement(By.xpath("//a[@title='Đăng nhập']")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='mfp-iframe iframe_guest']")));
		sleepInSecond(5);
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản']")).click();
		driver.findElement(By.name("myvne_email")).sendKeys(email);
		driver.findElement(By.name("myvne_password")).sendKeys(pass);
		driver.findElement(By.id("myvne_button_register")).click();

		// driver.findElement(By.xpath("//input[@id='myvne_email_input']")).sendKeys("Kim");
		sleepInSecond(5);

	}

	// @Test
	public void TC_03_LoginFormDisplayed() {
		// Login form displayed
		Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
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