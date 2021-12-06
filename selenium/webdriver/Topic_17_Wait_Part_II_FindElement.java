package webdriver;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part_II_FindElement {
	WebDriver driver;
	String projectPath=System.getProperty("user.dir");

	@BeforeClass 
	public void beforeClass() {
		//fire fox
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe"); 
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	
		driver.get("https://vi-vn.facebook.com/");
		
	}
	/*
	 * Điểm chung là: 
-Nó đều chịu ảnh hưởng bởi timeout: implicit
- Nết set implicit =10s thì nó sẽ là thời gian tối đa chờ cho element có trong cái DOM (HTML)
- Nếu như nó xuất hiện ngay trong vòng ít hơn 10s (2-3s xuất hiện thì 7s còn lại ko cần chờ nữa, nó sẽ chuyển sang step tiếp theo)
-Nếu như sau 10s vẫn chưa xuất hiện thì tùy vào hàm đang sử dụng findElement (trả ra exception)/ findElements(trả ra list rỗng)
- Trong thời gian wait thì có cơ chế tìm lại sau mỗi 0.5 giây (trong thời gian 10s)


	 * */

	//@Test
	public void TC_01_Find_Element() {
		//Find single element
		 WebElement emailTextbox= driver.findElement(By.id("email"));
		 
		 //1-Tìm thấy 1 matching node
		 // ko cần chờ hết timeout nên chạy rất nhanh (milisecond)
		 //System.out.println("Start 1:" + getDateTimeNow());
		 //driver.findElement(By.id("email")).sendKeys("a@gmail.com");
		// System.out.println("End 1:" + getDateTimeNow());
		
		 
		 //2- ko tìm thấy node nào hết
		 //- chờ hết timeout
		 //-sau khi chờ xong thì throw 1 cái ngoại lệ (exception): NosuchElementException
		 //- Đánh testcase fail luôn ngay tại step đó
		 //-ko chạy step tiếp theo nữa
		 
		/* System.out.println("Start 2:" + getDateTimeNow());
	      
	       try { driver.findElement(By.id("tiki")).isDisplayed();
	    	   
	       }finally {
	    	   System.out.println("End 2:" + getDateTimeNow());
	    	   
	       }
	       */
		 //3-Tìm thấy nhiều hơn 1 node
	       //thì sẽ thao tác với node đầu tiên
	       // ko quan tâm các node sau
	       System.out.println("Start 3:" + getDateTimeNow());
	       WebElement links=driver.findElement(By.cssSelector("div#pageFooter a"));
	       System.out.println("End 3:" + getDateTimeNow());
	       
	       links.click();
		 
		 //4 dòng này ko chạy được vì ở step 2 nó failed rồi
	//	 driver.findElement(By.id("pass")).sendKeys("1234");;
		 
		
	}

	@Test
	public void TC_02_Find_Elements() {
		//Find multiple element
	List <WebElement> elements;
	
	 //1-Tìm thấy 1 matching node --giống với số 3
	//- Nó sẽ trả về 1 list chứa 1 element (node) đó = size =1
	System.out.println("Start 4:" + getDateTimeNow());
	elements =driver.findElements(By.xpath("//input[@id='email']"));
	System.out.println("End 4:" + getDateTimeNow());
	System.out.println("List size = " + elements.size());
	 
	 //2- ko tìm thấy node nào hết
	// Cũng phải chờ hết time out của implicit 10s
	// trong thời gian đang chờ , cơ chế tìm lại mỗi 0.5/ lần
	// Chờ hết timeout rồi thì ko đánh fail testcase
	// Ko ném ra ngoại lệ nào hết
	//vẫn chạy các step tiếp theo
	System.out.println("Start 5:" + getDateTimeNow());
	elements =driver.findElements(By.xpath("//input[@id='tiki']"));
	System.out.println("End 5:" + getDateTimeNow());
	System.out.println("List size = " + elements.size()); 
	
	 //3-Tìm thấy nhiều hơn 1 node 
	// nó sẽ trả vể 1 list chứ n element (node) đó => size =n
	System.out.println("Start 6:" + getDateTimeNow());
	elements =driver.findElements(By.cssSelector("div#pageFooter a"));
	System.out.println("End 6:" + getDateTimeNow());
	System.out.println("List size = " + elements.size()); 
	
	}

	@Test
	public void TC_03_LoginFormDisplayed() {
		
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	// hàm kiểm tra xem nó chạy hết mấy giây
	public String getDateTimeNow() {
		Date date= new Date();
		return date.toString();
	}
	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}