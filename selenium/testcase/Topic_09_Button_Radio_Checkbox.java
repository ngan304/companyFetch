package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
// exices 9
public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	boolean status;
	JavascriptExecutor jsExecutor;
	//String projectPath=System.getProperty("user.dir"); cho chrom

	@BeforeClass 
	public void beforeClass() {
		//fire fox 
		driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver", projectPath+"\\browserDrivers\\chromedriver.exe"); 
		//driver = new ChromeDriver();
		
		jsExecutor =(JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
			}

	//@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
		
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		
		//Verify button disabled
		status=driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Button status" + status);
		Assert.assertFalse(status);
		
		
		driver.findElement(By.cssSelector("#login_username")).sendKeys("0332662819");
		driver.findElement(By.cssSelector("#login_password")).sendKeys("Password@123");
		
		//Verify button enabled
		status=driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Button status" + status);
		Assert.assertTrue(status);
		
		driver.navigate().refresh();
		
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		
		//Trick
		//Remove disaled attribute of Login button
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')",driver.findElement(By.cssSelector(".fhs-btn-login")));
		sleepInSecond(5);
		
		//Verify button enabled
		status=driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Button status" + status);
		Assert.assertTrue(status);
		
		driver.findElement(By.cssSelector(".fhs-btn-login")).click();
		
		//Verify message
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(),"Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(),"Thông tin này không thể để trống");
		
		
		
		
	}

	//@Test
	public void TC_02Radio_Checkbox_Default(){ // nếu ngay từ đầu check box đã được chọn thì dùng hàm điều kiện
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		By RearSideCheckbox=By.xpath("//label[text()=\\\"Rear side airbags\\\"]/preceding-sibling::input");
		
		//Click vào checkbox để chọn nó
		checkToCheckboxOrRadioButton(RearSideCheckbox);
		sleepInSecond(2);
		
		//Verify checkbox is selected
		Assert.assertTrue(driver.findElement(RearSideCheckbox).isSelected());
		
		//click vào để nó bỏ chọn
		uncheckToCheckbox(RearSideCheckbox);
		sleepInSecond(2);
		
		//Verify checkbox bỏ chọn
		Assert.assertFalse(driver.findElement(RearSideCheckbox).isSelected());

		//RADIO Button
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		checkToCheckboxOrRadioButton(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input")).isSelected());
		
		
	}

	//@Test
	public void TC_03_Radio_Checkbox_Select_All() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		
		//Select all checkboxes
		//Khai báo list element để lưu (1 element thì dùng Webelement, nhiều thì mình dùng List webelement)
		List<WebElement>checkboxes=driver.findElements(By.xpath("//input[@type='checkbox']"));
		for (WebElement checkbox: checkboxes) {//single checkbox sẽ duyệt qua từng cái multiple checkboxes
			if(!checkbox.isSelected()) {
				checkbox.click();
				sleepInSecond(1);
			}
			
		}
		for(WebElement checkbox:checkboxes) {
			Assert.assertTrue(checkbox.isSelected());
		}

	}

	
	public void TC_04_Radio_Checkbox_Custom() {
			
		driver.get("https://material.angular.io/components/radio/examples");
		
		//1- Thẻ input bị ẩn không click được + có thể verify được 

		//checkToCheckboxOrRadioButton(winterRadio);
		//sleepInSecond(2);
		//Assert.assertTrue(driver.findElement(winterRadio).isSelected());
		
		//2- Dùng thẻ khác, thẻ span (đang hiển thị để click) + ko verify được, ko dùng iselect được, 
		//--> Nên dùng hàm bình thường để click.
		//By winterSpan=By.xpath("//span[contains(string(),'Winter')]");
		//driver.findElement(winterSpan).click();
		//sleepInSecond(2);
		
		//Span không có trạnh thái isselected nên luôn trả về trại thái false
		//Assert.assertTrue(driver.findElement(winterSpan).isSelected());
		
		//3-Thẻ span để click + thẻ input để verify
		//By winterSpan=By.xpath("//span[contains(string(),'Winter')]");
		//driver.findElement(winterSpan).click();
		//sleepInSecond(2);
		
		//By winterRadio=By.xpath("//input[@value='Winter']");
		//Assert.assertTrue(driver.findElement(winterRadio).isSelected());
		//(Nhược điểm: define 2 locator cho 1 element
		//-> ko hay vì nó tạo ra nhiều element
		//=> khi mà bảo trì(maintain)--> thì phải bảo trì nhiều chỗ
	    //=> Nên mình có thẻ dumgf hàm click by JS
		
		//=> ở dưới
		
		//4- Thẻ input dùng JS để click + verify luôn
		By winterRadio=By.xpath("//input[@value='Winter']");
		clickToElementByJS(winterRadio);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(winterRadio).isSelected());
		
	}
		
	@Test
    public void TC_05_Radio_Checkbox_Custom(){
    	driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
    	sleepInSecond(4);
    	//Verify deselected
    	
    	Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam'and @aria-checked='false']")).isDisplayed());
    	
    	clickToElementByJS(By.xpath("//div[@aria-label='Quảng Nam']/div[contains(@class,'exportInnerBox')]"));
    	sleepInSecond(2);
    	
    	//Verify selected
    	//re chuot vao no phai highlight len thi moi verify duoc
    	Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam'and @aria-checked='true']")).isDisplayed());
    	
    }
	
	

	
	public void clickToElementByJS(By by) {
			WebElement element=driver.findElement(by);
			jsExecutor.executeScript("arguments[0].click();", element);
		}

		
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	//Hàm kiểm tra xem checkbox được select chưa
	public void checkToCheckboxOrRadioButton(By by) {//Nhận vào tham số by
		WebElement checkbox=driver.findElement(by);
		if(!checkbox.isSelected()) {
			checkbox.click();
		}
	}
	
	//Hàm kiểm tra checkbox được bỏ chọn chưa
	public void uncheckToCheckbox (By by) {
		WebElement checkbox=driver.findElement(by);
		if(checkbox.isSelected()) {
			checkbox.click();
		}
	}
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	}

}