package webdriver;




import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_17_Wait_Part_VII_Fluent_Wait {
	WebDriver driver;
	WebDriverWait explicitWait; // thư viện WebDriverWait
	String projectPath = System.getProperty("user.dir");
	WebElement element;
	

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
	
	}

	//@Test
	public void TC_01_() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		waitForElementAndClick(By.cssSelector("div#start>button"));
		Assert.assertTrue(waitForElementAndDisplayed(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
	}

	//@Test
		public void TC_02_Implicit() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='javascript_countdown_time' and text()='01:01:00']")).isDisplayed());
			 
	 }
		
		//@Test
		public void TC_03_Explicit() {
			driver.get("https://automationfc.github.io/fluent-wait/");
			
			explicitWait = new WebDriverWait(driver,13);
			
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='javascript_countdown_time' and text()='01:01:00']")));
		
		}
		@Test
		public void TC_04_Fluent() {
			driver.get("https://automationfc.github.io/fluent-wait/");
			
			WebElement countDownTime =driver.findElement(By.id("javascript_countdown_time"));
			
			FluentWait<WebElement> fluentWait= new FluentWait<WebElement>(countDownTime);
			
			fluentWait.withTimeout(13, TimeUnit.SECONDS)
			.pollingEvery(100, TimeUnit.MILLISECONDS)
			.ignoring(NoSuchElementException.class)
			.until(new Function<WebElement,Boolean>(){
				public Boolean apply(WebElement countdown) {
					System.out.println(countdown.getText());
					return countdown.getText().endsWith("00");
					
					
				}
			});

		
		}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}
	public WebElement getWebElement(By locator) {
		//Khai báo và khởi tạo fluent wait
		FluentWait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				// Tổng thời gian chờ là bao nhiêu s
				.withTimeout(15000,TimeUnit.MILLISECONDS)
				//Thời gian để lặp lại là bao nhiêu s
				.pollingEvery(1000,TimeUnit.MILLISECONDS)
				//nếu sau mỗi lần lặp mà gặp exception thì sẽ ignore
				.ignoring(NoSuchElementException.class);
		
		WebElement element =wait.until(new Function<WebDriver,WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
	}
	public void waitForElementAndClick(By locator) {
		FluentWait<WebDriver>wait=new FluentWait<WebDriver>(driver)
				.withTimeout(15000,TimeUnit.MILLISECONDS)
				.pollingEvery(1000,TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		
		WebElement element=wait.until(new Function<WebDriver,WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		element.click();
	}
	public boolean waitForElementAndDisplayed(By locator) {
	WebElement	element=getWebElement(locator);
		FluentWait<WebElement>wait=new FluentWait <WebElement>(element)
				.withTimeout(15000,TimeUnit.MILLISECONDS)
				.pollingEvery(1000,TimeUnit.MILLISECONDS)
				.ignoring(NoSuchElementException.class);
		
		boolean isDisplayed= wait.until(new Function<WebElement,Boolean>(){
			public Boolean apply(WebElement element) {
				boolean flag= element.isDisplayed();
				return flag;
			}
		});
		return isDisplayed;
	}
	
	
	
	
}