package testcase;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Automation_vnExpress_Page {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String email = "trinhkim6@gmail.com";
	String pass = "Trinhkim6#";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
	}

	@Test
	public void Testing_vnExpressPage() {
		driver.get("https://vnexpress.net/trong-tre-du-co-ve-lam-to-4545196.html");
		driver.findElement(By.xpath("//a[@title='Đăng nhập']")).click();
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='mfp-iframe iframe_guest']")));
		driver.findElement(By.xpath("//input[@id='myvne_email_input']")).sendKeys(email);
		driver.findElement(By.id("myvne_password_input")).sendKeys(pass);
		driver.findElement(By.id("myvne_button_login")).click();
		driver.switchTo().defaultContent();
		Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Trồng tre dụ cò về làm tổ ']")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
