package webdriver;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interaction_Part_II {
	WebDriver driver;
	WebDriverWait explicitWait;
	Actions action;
	JavascriptExecutor jsExecutor;
	String projectPath=System.getProperty("user.dir");

	@BeforeClass 
	public void beforeClass() {
		//fire fox 
		System.setProperty("webdriver.gecko.driver", projectPath+ ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		action= new Actions(driver);
		// map driver qua kiểu Js
		jsExecutor=(JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}

	//@Test
	public void TC_06_Double_Mouse() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//scroll to element
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//button[text()='Double click me']")));
		
		//Double click
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']")))
		.perform();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
	
	}
	//@Test
	public void TC_07_RightClick_Mouse_02() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		//click right
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(3);
		//hover
		action.moveToElement(driver.findElement(By.cssSelector(".context-menu-icon-quit"))).perform();
		sleepInSecond(3);
		//Verify hành vi hover
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-visible.context-menu-hover")).isDisplayed());
		//Click
		action.click(driver.findElement(By.cssSelector(".context-menu-icon-quit"))).perform();
		sleepInSecond(3);
		//Verify alert
		Assert.assertEquals(driver.switchTo().alert().getText(),"clicked: quit");
		//Accept alert
		driver.switchTo().alert().accept();
		
	
	}

	//@Test
	public void TC_08_Drag_And_Drop(){
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement smallcycle= driver.findElement(By.id("draggable"));
		WebElement bigcycle= driver.findElement(By.id("droptarget"));
		action.dragAndDrop(smallcycle,bigcycle).perform();
		
		sleepInSecond(3);
		//Verify text
		Assert.assertEquals(bigcycle.getText(),"You did great!");

		//Verify màu
		Assert.assertEquals(Color.fromString(bigcycle.getCssValue("background-color")).asHex(),"#03a9f4");
		
		
	}
	@Test
	public void TC_09_Drag_And_Drop_HTML5_JS_JQuery_Css_Selector(){
		//chỉ xem demo cho vui thôi, chứ thực tế thì ko statble
	
	}

	@Test
	public void TC_10_Drag_And_Drop_HTML5_Toado_Css_Xpath() {
		//chỉ xem demo cho vui thôi, chứ thực tế thì ko statble

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