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

public class Topic_04_Web_Browser_Method {
	//Interface
	WebDriver driver; 
	// để tương tác với trình duyệt thì nó thông qua biến driver
	// Biến driver đại diện cho WebDriver là interface

	@BeforeClass 
	public void beforeClass() {
		//fire fox 
		driver = new FirefoxDriver();//class
	}

	@Test
	public void TC_01_Browser() {
		//Những hàm hay sử dụng với browser is below ***********
		
		
		// Bien driver tuong tac voi browser
		
		//Mở 1 page ra, tham số truyền vào là url
		driver.get("https://www.facebook.com/login/");
		
		// Lấy ra đường dãn (url) của page hiện tại
		// tạo ra 1 biến tên là localPageUrl để gán dữ liệu được lây ra vào cái biến này
		String localPageUrl=driver.getCurrentUrl();
		
		//Lấy ra title của page hiện tại 
		driver.getTitle();
		
		// Lấy ra toàn bộ HtML code của page hiện tại
		driver.getPageSource();
		
		//Xử lý tab/windows
		driver.getWindowHandle();
		driver.getWindowHandles();
		
		//Framework (Share trạng thái của 1 class)
		//driver.manage().addCookie(cookie);
		
		// Chờ cho element được tìm thấy trong vòng xxx thời gian
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//setScriptTimeout
		// pageLoadTimeout
		
		//Back về page trước đó
		//Forward tới page trước 
		//Refresh page hiện tại
		// Mở 1 url ra
		driver.navigate().back();
		// Liên quan tới history(navigate.to("https://www.facebook.com/login/")) nó sẽ tracking tốt hơn
		// Nhưng nó không được dùng bao giờ cả
		// Không dùng cho back, forward, refresh lun, demo cho zui hoyyy
		
		
		// browser chỉ có 1 tab duy nhât thì đều đóng browser
		// Ko quan tâm bao nhiêu tab
		driver.quit();
		
		
		// browser chỉ có 1 tab duy nhât thì đều đóng browser
		//Đóng cái tab đang active
		//Xử lý swith tab/ windows
		driver.close();
		
		
		// Xử lý Alert
		driver.switchTo().alert();
		
		// Xử lý Fram/ Iframe
		driver.switchTo().frame(1);
		
		// Switch to windows/ tab
		driver.switchTo().window("");
		
		
		//Chức năng full màn hình
		driver.manage().window().fullscreen();
		
		//Maximize màn hình
		driver.manage().window().maximize();
		
		//Lấy ra vị trí browser so với độ phân giả màn hình hiện tại 
		//KHông cần thiết vì cái này là test UI...
		driver.manage().window().getPosition(); 
		//driver.manage().window().setPosition(targetPosition);
		
		driver.manage().window().getSize();
		//driver.manage().window().setSize(targetSize);
		
		//Biến tương tác với element (text box/dropdown/..)
		
		
	}


}