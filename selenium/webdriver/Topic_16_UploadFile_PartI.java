package webdriver;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.media.webkitMediaStream;

public class Topic_16_UploadFile_PartI {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	String dellName = "Dell.jpg";
	String razerName = "Razer.jpg";
	String thinkpadName = "Thinkpad.jpg";

	String uploadFilePath = projectPath + File.separator + "uploadFiles" + File.separator;

	String dellFilePath = uploadFilePath + dellName;
	String razerFilePath = uploadFilePath + razerName;
	String thinkpadFilePath = uploadFilePath + thinkpadName;

	@BeforeClass
	public void beforeClass() {
		// fire fox
		// System.setProperty("webdriver.gecko.driver", projectPath+
		// ".\\browserDrivers\\geckodriver.exe");
		// driver = new FirefoxDriver();

		// chrome
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		
		//Edge
		//System.setProperty("webdriver.edge.driver",projectPath +"\\browserDrivers\\msedgedriver.exe");
		//driver=new EdgeDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

	}

	// @Test
	public void TC_01_Sendkey_One_File() {
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(dellFilePath);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(razerFilePath);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(thinkpadFilePath);

		// Verify files loads success
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + dellName + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + razerName + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + thinkpadName + "']")).isDisplayed());

		// Click to Start button -> Upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button[class*='start']"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(2);
			// Nó duyệt qua từng element rồi click,
			// sau mỗi lần click, nghỉ 2s
		}

		// Verify file upload thành công
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[text()='" + dellName + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[text()='" + razerName + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[text()='" + thinkpadName + "']")).isDisplayed());

	}

	@Test
	public void TC_02_Sendkey_Multiple_File() {
		By uploadFile = By.xpath("//input[@type='file']");

		// Note: Với vs firefox bản cũ 47 -> ko work (work với bản mới thôi)

		driver.findElement(uploadFile).sendKeys(dellFilePath + "\n" + razerFilePath + "\n" + thinkpadFilePath);
		sleepInSecond(1);

		// Verify files loads success
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + dellName + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + razerName + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + thinkpadName + "']")).isDisplayed());

		// Click to Start button -> Upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button[class*='start']"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(2);
			// Nó duyệt qua từng element rồi click,
			// sau mỗi lần click, nghỉ 2s
		}

		// Verify file upload thành công
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[text()='" + dellName + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[text()='" + razerName + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']/a[text()='" + thinkpadName + "']")).isDisplayed());

	}

	@Test
	public void TC_03() {

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