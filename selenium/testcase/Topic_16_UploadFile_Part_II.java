package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Topic_16_UploadFile_Part_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String uploadFilePath = projectPath + File.separator + "uploadFiles" + File.separator;
	JavascriptExecutor jsExecutor;

	String dellName = "Dell.jpg";
	String razerName = "Razer.jpg";
	String thinkpadName = "Thinkpad.jpg";

	String dellFilePath = uploadFilePath + dellName;
	String razerFilePath = uploadFilePath + razerName;
	String thinkpadFilePath = uploadFilePath + thinkpadName;
	
	//khai báo đường dẫn 
	String firefoxOncePath= projectPath + "\\autoIT\\firefox.exe";
	String chromeOncePath= projectPath + "\\autoIT\\chromeUploadfile.exe";
	String firefoxMultiplePath= projectPath + "\\autoIT\\firefoxMultiplefile.exe";
	String chromeMultiplePath= projectPath + "\\autoIT\\chromeMultiplefile.exe";
	


	@BeforeClass
	public void beforeClass() {
		// fire fox
		//System.setProperty("webdriver.gecko.driver", projectPath + ".\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();

		 //chrome
		System.setProperty("webdriver.chrome.driver", projectPath +"\\browserDrivers\\chromedriver.exe");
	    driver = new ChromeDriver();

		// Edge
		// System.setProperty("webdriver.edge.driver",projectPath
		// +"\\browserDrivers\\msedgedriver.exe");
		// driver=new EdgeDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

	}

	 //@Test
	public void TC_01_AutoIT_One_File() throws IOException{ 
		//Click to add file button -> show open file dialog
		driver.findElement(By.cssSelector(".btn-success")).click();
	
		//Đã bât open file dialog lên -> dùng autoIT để load      
		//Câu lệnh này dùng để thực hiện file exe trong code của mình
		//trong trường hợp nó ko tìm ra đường dẫn thì nó throw ra IOexception
		//if(driver.toString().contains("firefox")) {
		//	Runtime.getRuntime().exec(new String[]{firefoxAutoITPath,dellFilePath});
		//}else if(driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[]{chromeOncePath,dellFilePath});

			
		
		
		
		//driver.findElement(By.cssSelector("span.btn-success")).click();
		//Runtime.getRuntime().exec(new String[]{firefoxAutoITPath,razerFilePath});
		//sleepInSecond(2);
		
		//driver.findElement(By.cssSelector("span.btn-success")).click();
		//Runtime.getRuntime().exec(new String[]{firefoxAutoITPath,thinkpadFilePath});
		//sleepInSecond(2);

		// Verify files loads success
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + dellName + "']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + razerName + "']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + thinkpadName + "']")).isDisplayed());

		// Click to Start button -> Upload
		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button[class*='start']"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(2);
			// Nó duyệt qua từng element rồi click,
			// sau mỗi lần click, nghỉ 2s
		}

		// Verify file upload thành công
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + dellName + "']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + razerName + "']")).isDisplayed());
		//Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + thinkpadName + "']")).isDisplayed());

	}

	//@Test
	public void TC_02_AutoIT_Multiple_File() throws IOException {
				driver.findElement(By.cssSelector(".btn-success")).click();
				if(driver.toString().contains("firefox")) {
				Runtime.getRuntime().exec(new String[]{firefoxMultiplePath,dellFilePath,razerFilePath,thinkpadFilePath});
				}else if(driver.toString().contains("chrome")) {
					Runtime.getRuntime().exec(new String[]{chromeMultiplePath,dellFilePath,razerFilePath,thinkpadFilePath});
				}
							
				//driver.findElement(By.cssSelector("span.btn-success")).click();
				//Runtime.getRuntime().exec(new String[]{firefoxAutoITPath,razerFilePath});
				//sleepInSecond(2);
				
				//driver.findElement(By.cssSelector("span.btn-success")).click();
				//Runtime.getRuntime().exec(new String[]{firefoxAutoITPath,thinkpadFilePath});
				//sleepInSecond(2);

				// Verify files loads success
				Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + dellName + "']")).isDisplayed());
				Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + razerName + "']")).isDisplayed());
				Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + thinkpadName + "']")).isDisplayed());

				// Click to Start button -> Upload
				List<WebElement> startButtons = driver.findElements(By.cssSelector("table button[class*='start']"));
				for (WebElement start : startButtons) {
					start.click();
					sleepInSecond(2);
					// Nó duyệt qua từng element rồi click,
					// sau mỗi lần click, nghỉ 2s
				}

				// Verify file upload thành công
				Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + dellName + "']")).isDisplayed());
				Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + razerName + "']")).isDisplayed());
				Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='" + thinkpadName + "']")).isDisplayed());


	}

	@Test
	public void TC_03_Java_Robot() throws AWTException {

		
		//copy đường dẫn của file lưa vào trong clipboard (bộ nhớ tạm)
		StringSelection select = new StringSelection(dellFilePath);
		
		//click vào button upload để bât dialog
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select,null); 
		driver.findElement(By.cssSelector(".btn-success")).click();
		
		Robot robot= new Robot();
		sleepInSecond(2);
	
		
		//Nhấn phím Enter //-mục đích để chuột focus vào textbox thôi-- lệnh này ko bắt buộc
		//robot.keyPress(KeyEvent.VK_ENTER);
		//robot.keyRelease(KeyEvent.VK_ENTER);
		
		//Nhấn xuống ctrl-V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		//Nhã ctrl-V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		sleepInSecond(2);
		
		//Nhấn Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='" + dellName + "']")).isDisplayed());

		List<WebElement> startButtons = driver.findElements(By.cssSelector("table button[class*='start']"));
		for (WebElement start : startButtons) {
			start.click();
			sleepInSecond(2);
			
		}


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