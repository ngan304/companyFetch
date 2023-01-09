package webdriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown { //BT3
	WebDriver driver;
	Select select;
	JavascriptExecutor jsExecutor; // thư viện selenium cho phép thực hiện đoạn lệnh javascript trên trình duyêt
	List<String> expectdItemItext;
	String firstName, lastName, emailAddress, companyName, day, month, year;

	@BeforeClass
	public void beforeClass() {
		// fire fox
		driver = new FirefoxDriver();
		jsExecutor= (JavascriptExecutor) driver; //ép kiểu interface của driver sang interface của javascript
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("");

		firstName = "Tran";
		lastName = "Kim";
		emailAddress = "autofc"+generateEmail();
		companyName = "Viet Nam";
		
		day = "5";
		month = "April";
		year = "1997";
		
		expectdItemItext = new ArrayList<String>(Arrays.asList("Month","January","February","March","April","May","June","July","August","September","October","November","December"));

	}

	//@Test
	public void TC_01_NopCommerce() {
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.className("ico-register")).click();
		
		sleepInSecond(2);

		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);

		// Khởi tạo biến select có liên kết với element dropdown
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));

		// Chọn item tron dropdown
		select.selectByVisibleText("5");

		// Kiểm tra đã chọn đúng item này hay chưa
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);

		// hàm này dùng để verify xem trong dropdown này có tổng cộng bao nhiêu item
		// Assert.assertEquals(select.getOptions().size(),32);

		// Dùng verify dropdown có cho chọn nhiều giá trị ko. trả về true thì cho chon
		// nhiều
		// Assert.assertFalse(select.isMultiple());

		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText("April");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);

		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText("1997");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);

		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
		
		clickByJS(By.id("register-button"));
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");
		
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"),lastName);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"),emailAddress);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"),companyName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), day);
		
		sleepInSecond(2);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), month);
		
		sleepInSecond(1);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), year);
		

	}

	@Test
	public void TC_02() {  // Verify tất cả tháng trong dropdown tháng
		
		driver.get("https://demo.nopcommerce.com/");

		driver.findElement(By.className("ico-register")).click();
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		
		List<WebElement> allItems=select.getOptions();
		List<String> allItemsText = new ArrayList<>();
		
		//Duyệt qua tất cả các item có trong list WebElement
		for (WebElement item : allItems) {
		 allItemsText.add(item.getText());
			
		}
		Assert.assertEquals(expectdItemItext,allItemsText);

	}

	@Test
	public void TC_03() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void clickByJS(By by) {
		jsExecutor.executeScript("arguments[0].click();",driver.findElement(by));
		
	}

	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) + "@mail.net";
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}