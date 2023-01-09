package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.server.handler.SendKeys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



public class Topic_14_Window_Tab {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath=System.getProperty("user.dir");
	String childID; 

	@BeforeClass 
	public void beforeClass() {
		//fire fox 
		System.setProperty("webdriver.gecko.driver", projectPath+ ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait= new WebDriverWait(driver, 15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);// apply cho page nó đang đứng
			}

	//@Test
	public void TC_01_Window() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		//Get ra ID của active tab/windows (driver đang đứng)
		String parentID=driver.getWindowHandle(); //trả về string nên mình nên có 1 cái biến để lưu lại
		System.out.println("Parent ID="+ parentID);
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);
		
		switchToWindowByID(parentID);
		
		/* nên dùng hàm để nó gọn hơn để giảm sự trùng lập
		//Get ra ID của tất cả các tab/ windows đang có -> tất cả
		Set<String> allIDs=driver.getWindowHandles(); // trả về set(string). Java collection chứa data structure : set/list/queue
		
		   //giả dụ id của tab/window là thằng con 
		
		//In ra tất cả ID nó lấy ra được -> dùng vòng lặp
		for (String id : allIDs) {
			System.out.println(id);
			
			if(!id.equals(parentID)) {
				childID=id;
			}
			
		}
		System.out.println(driver.getTitle());
		//Switch vào tab/ windows nào đó bằng ID
		driver.switchTo().window(childID); //Nó sẽ truyền vào cái id để switch
		System.out.println(driver.getTitle());
		*/
		
		
		driver.findElement(By.cssSelector("#email")).sendKeys("child@gmail.com");
		
		// Lấy ra id của tab con đang đứng
		
		//switch về trang home
		String childID=driver.getWindowHandle();
		switchToWindowByID(childID);
		driver.findElement(By.cssSelector("#email")).sendKeys("parent@gmail.com");
	}
	@Test
	public void TC_02_windowviaTitle() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String parentID= driver.getWindowHandle(); 	
		
		//Click vào fb link -> hành vi của app tự động nhảy qua tab đó luôn
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		sleepInSecond(3);
	
		//Switch vào fb tab bằng title thì mới thao tác được
		switchToWindowByTitle("Facebook – log in or sign up"); //expected là nhảy qua title này
	
		driver.findElement(By.cssSelector("#email")).sendKeys("facebook@gmail.com");
		
		//Switch vào home tab bằng title
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
	
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("automation");
		
		//Click vào Tiki limk -> hành vi của app tự động nhảy qua tab đó luôn
		
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(3);
		
		//switch vào tab tiki bằng title để thao tác trên tab tiki
		
		switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		sleepInSecond(3);
		
		driver.findElement(By.xpath("//input[@data-view-id='main_search_form_input']")).sendKeys("Sách");
		driver.findElement(By.cssSelector(".icon-search")).click();
		sleepInSecond(3);
		
		//Switch vào parent page lại 
	    switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
	    //click vào lazada link -> hành vi của app tự động nhảy qua tab đó lun
	    driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
	    sleepInSecond(3);
	    
	    //Switch vào lazada tab bằng title
	    switchToWindowByTitle("Shopping online - Buy online on Lazada.vn");
	    
	    //đóng các tab con trừ trang parent
	    closeAllWithoutParent(parentID);
	    
	    driver.findElement(By.cssSelector("#email")).sendKeys("fb@gmail.com");
	    driver.findElement(By.xpath("//input[@name='password']")).sendKeys("12345");
	    //driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	@Test
	public void TC_03_Random_Not_In_Dom(){
		
	}
	

	@AfterClass
	public void afterClass() {

		//driver.quit();
	}
	
	
	//Dùng cho đúng duy nhất 2 windows/tab
	//kiểm tra cái id trước
	//Khác với parent
	//Thì mới switch
	public void switchToWindowByID(String parentID) { // định nghĩa biến parentID ở đây 
		//Get ra tất cả các tab/ window đang có
		Set<String> allWindows = driver.getWindowHandles();
		
		// Dùng vòng lặp để duyệt qua từng window
		for (String id : allWindows) { // id đại diện cho allwindows
			//Nếu như id nào mà khác với parent id
			if(!id.equals(parentID)) {
				//switch vào id đó 
				driver.switchTo().window(id);
			}
		}
		
	}
	
	//Dùng cho 2 hoặc nhiều hơn 2 windows/ tab
	//Switch vào từng window trước
	//Get ra title của window đó
	//Kiểm tra với title mong muốn
	//Nếu như  mà = thì stop ko kiểm tra tiếp
	public void switchToWindowByTitle(String expectedTittle) {
		//Get ra tất cả các tab/window đang có
	
        Set<String> allWindows = driver.getWindowHandles();
		
		// Dùng vòng lặp để duyệt qua từng window
		for (String id : allWindows) {
			//Switch vào từng window trước
			driver.switchTo().window(id);
			//Get ra title của window đó
			String windowTitle= driver.getTitle();
			System.out.println(windowTitle);
			//Nếu như  mà = thì stop ko kiểm tra tiếp
			if(windowTitle.equals(expectedTittle)){
				break;
				
			}
		
	}
	}
	
	//Hàm close các tab
	public void closeAllWithoutParent(String parentID) {
		//Get ra tất cả các tab/ window đang có
		Set<String> allWindows = driver.getWindowHandles();
				
		// Dùng vòng lặp để duyệt qua từng window
		for (String id : allWindows) { // id đại diện cho allwindows
			//Nếu như cái id nào mà nó khác parentid thì switch qua id đó và đóng nó đi
			if(!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close(); // close là close tab đứng hiện tại thôi. quite thì close lun browser
			}// sau khi đóng hết các tab trừ trang parent thì id nó vẫn đang đứng tab cuối cùng nó mới đóng
			// nên phải switch về lại
	}
		//Switch về lại parent
		driver.switchTo().window(parentID);
	}
	
	
	
	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	}

}