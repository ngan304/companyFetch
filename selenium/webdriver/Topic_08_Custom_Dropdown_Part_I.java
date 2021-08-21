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

public class Topic_08_Custom_Dropdown_Part_I {
	WebDriver driver;

	@BeforeClass 
	public void beforeClass() {
		//fire fox 
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
			}

	@Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectItemInCustomDropdow("//span[@id='number-button']");
		
	}

	@Test
	public void TC_02_React(){
		
	}

	@Test
	public void TC_03_VueJS() {

	}
	public void selectItemInCustomDropdow(String parentXpath) { // sử dụng biến parentXpath định nghĩa cho locator
		// -Click vào 1 element để cho xổ hết tất cả các item trong dropdown ra -> parent element
		driver.findElement(By.xpath(parentXpath)).click();
		// Chờ cho tất cả các item được load ra thành công -> child element
		// Tìm cái item cần chọn
		// Item cần chọn nó hiển thị -> Click vào item đó luôn
		// Item cần chọn không hiển thị (ẩn bên dưới) 
		// -> scroll đến item đó -> click vào item
		  
		 
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