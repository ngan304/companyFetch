package webdriver;




import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_13_Iframe_Frame {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath=System.getProperty("user.dir");

	@BeforeClass 
	public void beforeClass() {
		//fire fox 
		System.setProperty("webdriver.gecko.driver", projectPath+ ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		jsExecutor=(JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}

	//@Test
	public void TC_06_Iframe() {
		driver.get("https://kyna.vn/");
		
		scrollToButtomPage();
		//switch từ trang parent sang iframe (switch vào fb fanpage iframe)
		//Index : độ chính xác ko cao, nếu thêm sửa xóa frame nào đó thì index nó sẽ thay đổi
		//driver.switchTo().frame(0);
		//Name or ID
		//Element
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
		
		//Verify facebook page= 168k lượt thích 
		Assert.assertEquals(driver.findElement(
				By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(),"168K likes");
		//Vể parent page trước
		driver.switchTo().defaultContent();
		sleepInSecond(2);
		//switch vể chat iframe
		driver.switchTo().frame(driver.findElement(By.cssSelector("#cs_chat_iframe")));
		sleepInSecond(2);
		//click vào chat iframe
		driver.findElement(By.cssSelector(".button_bar")).click();
		//click vào button gửi tin nhắn
		driver.findElement(By.cssSelector(".submit")).click();
		sleepInSecond(2);
		
		Assert.assertEquals(driver.findElement(By.cssSelector(".input_name+.error_message")).getText(),"Tên của bạn chưa được nhập");
		Assert.assertEquals(driver.findElement(By.cssSelector("#serviceSelect+.error_message")).getText(),"Bạn chưa chọn dịch vụ hỗ trợ");
		
		//Switch vể parent page
		driver.switchTo().defaultContent();
		// Search từ khóa excel
		driver.findElement(By.cssSelector("#live-search-bar")).sendKeys("excel");
		driver.findElement(By.cssSelector(".search-button")).click();
		//Verify có 10 kết quả
		List<WebElement> Searchoutput=driver.findElements(By.cssSelector("section div>h4"));
		
		Assert.assertEquals(Searchoutput.size(),10);
		//Verify các kết quả chứa text Excel, dùng for each để quet list
		for (WebElement coursename : Searchoutput) {
			System.out.println(coursename.getText());
			//Assert.assertTrue(coursename.getText().contains("excel"));
			
			//Trường hợp verify ko phân biệt hoa thường thì mình convert nó về lowercase hoặc upercase rồi mới check
			Assert.assertTrue(coursename.getText().toLowerCase().contains("excel"));
			Assert.assertTrue(coursename.getText().toLowerCase().matches("(.*)excel(.*)"));
		}
		
		
	}
	@Test
	public void TC_07_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		driver.switchTo().frame("login_page");
		driver.findElement(By.cssSelector(".form-control")).sendKeys("automationfc");
		driver.findElement(By.cssSelector(".login-btn")).click();
		
		//Verify textbox Password hiển thị 
		Assert.assertTrue(driver.findElement(By.cssSelector("#fldPasswordDispId")).isDisplayed());
		
		driver.findElement(By.xpath("//div[@class='footer-btm']//a[text()='Terms and Conditions']")).click();
	    sleepInSecond(7);
	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void scrollToButtomPage() {
		jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	}

}