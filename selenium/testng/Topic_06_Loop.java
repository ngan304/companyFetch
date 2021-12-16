package testng;


import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class Topic_06_Loop {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	Select select;
	String  firstName,lastName,emailAddress, companyName, day, month, year;

	// từ khóa @Parameters sẽ đọc dữ liệu
	//từ file xml để lấy ra cái biến name= browser
	// ko thể run trực tiếp từ đây được, vì nó ko hiểu parameter là thằng nào cả mà phải run từ xml
	
	@BeforeClass
	public void beforeClass() {
			System.setProperty("webdriver.gecko.driver",projectPath+ "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		
		jsExecutor= (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get("https://demo.nopcommerce.com/");
		
		firstName = "Tran";
		lastName = "Kim";
		
		companyName = "Viet Nam";
		day = "5";
		month = "April";
		year = "1997";
		
		

	}

	@Test(invocationCount=5)
	public void TC_01_Register_To_NopCommerce() {
		driver.findElement(By.className("ico-register")).click();
		sleepInSecond(1);
		
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		// Khởi tạo biến select có liên kết với element dropdown
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		
		// Chọn item tron dropdown
		select.selectByVisibleText(day);
		
		// Kiểm tra đã chọn đúng item này hay chưa
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		//select.deselectAll();
		
		//List<WebElement> itemsSelected=select.getAllSelectedOptions();
		//Assert.assertEquals(itemsSelected.size(),0);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		
		driver.findElement(By.id("Email")).sendKeys( "autofc"+generateEmail());
		driver.findElement(By.id("Company")).sendKeys(companyName);
		
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
		
		clickByJS(By.id("register-button"));
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");
		
		driver.findElement(By.xpath("//a[@class='ico-logout']")).click();
		sleepInSecond(2);
	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void clickByJS(By by) {
		jsExecutor.executeScript("arguments[0].click();",driver.findElement(by));
		
	}
	public String generateEmail() {
		Random rand= new Random();
		return rand.nextInt(9999)+ "@gmail.net";
	}


	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
