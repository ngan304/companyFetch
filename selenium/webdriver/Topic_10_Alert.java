package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Alert {
	WebDriver driver;
	WebDriverWait explicitWait;
	Alert alert;  // Alert là thư viện, alert là biến
	String projectPath=System.getProperty("user.dir");

	@BeforeClass 
	public void beforeClass() {
		//fire fox 
		System.setProperty("webdriver.gecko.driver", projectPath+ ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
			}

	//@Test
	public void TC_01_Accept_Alert() {
		driver.get("http://demo.guru99.com/v4/index.php");
		
		driver.findElement(By.xpath("//input[@name=\"btnLogin\"]")).click();
		
		//Wait cho alert xuất hiện(dùng thư viện WedDriverWait)
		//---explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// Switch qua alert (Nó có 1 thư viện alert, cần phải khai báo ở trên)
		///--alert=driver.switchTo().alert(); // không quan tâm là alert bật hay chưa, nó sẽ swith lun, Nếu chưa bật, nó sẽ failed
		
		///Wait cho alert xuất hiện + Switch
		alert= explicitWait.until(ExpectedConditions.alertIsPresent());
		sleepInSecond(5);
		
		//Verify alert text
		Assert.assertEquals(alert.getText(),"User or Password is not valid");
		
		//Accept
		alert.accept();
		
		//Cancel
		//alert.dismiss();
		
		//Get text của alert
		//alert.getText();
		
		//sendkey
		//alert.sendKeys("");
		
	}
	//@Test
	public void TC_01_Accept_Alert_02() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		alert= explicitWait.until(ExpectedConditions.alertIsPresent());
		sleepInSecond(5);
		
		//Verify alert text
		Assert.assertEquals(alert.getText(),"I am a JS Alert");
		
		//Accept
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked an alert successfully");
		
	}

	//@Test
	public void TC_02_Confirm_Alert(){
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		alert=explicitWait.until(ExpectedConditions.alertIsPresent());
	    sleepInSecond(5);
	    
	    Assert.assertEquals(alert.getText(),"I am a JS Confirm");
	    
	    alert.dismiss();
	    Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You clicked: Cancel");
	
		
	}

	//@Test
	public void TC_03_Prompt_Alert() {
		String fullname="Ngan Kim";
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		alert=explicitWait.until(ExpectedConditions.alertIsPresent());
		sleepInSecond(5);
		
		Assert.assertEquals(alert.getText(),"I am a JS prompt");
		
		alert.sendKeys(fullname);
		sleepInSecond(5);
		
		alert.accept();
		Assert.assertEquals(driver.findElement(By.id("result")).getText(),"You entered: " + fullname);

	}
	//@Test
	public void TC_04_Authentication_Alert() {// cách 1
		driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth");
			
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),' Congratulations! You must have the proper credentials.')]")).isDisplayed());

	}
    @Test
	public void TC_05_Authentication_Alert() {
		driver.get("http://the-internet.herokuapp.com");
		String hrefValue= driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		
		passValueToUrl(hrefValue,"admin", "admin");
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),' Congratulations! You must have the proper credentials.')]")).isDisplayed());
	
	}
	// Mình sẽ tạo 1 hàm 
    public void passValueToUrl(String url,String username,String pasword) {
    	String [] hrefValue=url.split("//");  //Giá trị này sẽ trả về mảng strint
		url= hrefValue[0] + "//" + username +":" + pasword +"@" + hrefValue[1];
		driver.get(url);
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