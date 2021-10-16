package webdriver;



import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interaction_Part_I {
	WebDriver driver;
	WebDriverWait explicitWait;
	Actions action;
	String projectPath=System.getProperty("user.dir");

	@BeforeClass 
	public void beforeClass() {
		//fire fox 
		System.setProperty("webdriver.gecko.driver", projectPath+ ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		action= new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}

	//@Test
	public void TC_01_Hover_Mouse() {
	driver.get("https://automationfc.github.io/jquery-tooltip/");
	
	// Hover chuột vào textbox
	action.moveToElement(driver.findElement(By.id("age"))).perform();
	sleepInSecond(5);
	
	Assert.assertEquals(driver.findElement(By.cssSelector(".ui-tooltip-content")).getText(),"We ask for your age only for statistical purposes.");
	sleepInSecond(5);
	}
	//@Test
	public void TC_01_Hover_Mouse_02() {
		driver.get("https://www.myntra.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb' and text()='Kids Home Bath']")).isDisplayed());
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
		
	}

	//@Test
	public void TC_02_Click_And_Hold(){
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> rectangleNumber=driver.findElements(By.cssSelector("#selectable>li"));
		System.out.println("Number of rectangle= " + rectangleNumber.size() );
		
		//Click and hold vào element đầu tiên -> Hover chuột đến element đích -> Nhả chuột trái ra
		action.clickAndHold(rectangleNumber.get(0))
		.moveToElement(rectangleNumber.get(3))
		.release()
		.perform();
		
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 4);
		
	}
	@Test
	public void TC_02_Click_And_Hold_Random(){
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> rectangleNumber=driver.findElements(By.cssSelector("#selectable>li"));
		//Nhấn phím ctrl xuống (thư viện keys)
		
		action.keyDown(Keys.CONTROL).perform();
		// Chọn các element đích (1.3.6.11)
		action.click(rectangleNumber.get(0))
		.click(rectangleNumber.get(2))
		.click(rectangleNumber.get(5))
		.click(rectangleNumber.get(10)).perform();
		// Nhả phím ctrl ra
		action.keyUp(Keys.CONTROL).perform();
		
        sleepInSecond(3);
		
		Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 4);
		
		
	
	}

	@Test
	public void TC_03() {

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