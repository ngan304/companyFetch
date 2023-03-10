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
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='S??? ??i???n tho???i/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(),"Th??ng tin n??y kh??ng th??? ????? tr???ng");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='M???t kh???u']/following-sibling::div[@class='fhs-input-alert']")).getText(),"Th??ng tin n??y kh??ng th??? ????? tr???ng");
		
		
		
		
	}

	//@Test
	public void TC_02Radio_Checkbox_Default(){ // n???u ngay t??? ?????u check box ???? ???????c ch???n th?? d??ng h??m ??i???u ki???n
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		
		By RearSideCheckbox=By.xpath("//label[text()=\\\"Rear side airbags\\\"]/preceding-sibling::input");
		
		//Click v??o checkbox ????? ch???n n??
		checkToCheckboxOrRadioButton(RearSideCheckbox);
		sleepInSecond(2);
		
		//Verify checkbox is selected
		Assert.assertTrue(driver.findElement(RearSideCheckbox).isSelected());
		
		//click v??o ????? n?? b??? ch???n
		uncheckToCheckbox(RearSideCheckbox);
		sleepInSecond(2);
		
		//Verify checkbox b??? ch???n
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
		//Khai b??o list element ????? l??u (1 element th?? d??ng Webelement, nhi???u th?? m??nh d??ng List webelement)
		List<WebElement>checkboxes=driver.findElements(By.xpath("//input[@type='checkbox']"));
		for (WebElement checkbox: checkboxes) {//single checkbox s??? duy???t qua t???ng c??i multiple checkboxes
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
		
		//1- Th??? input b??? ???n kh??ng click ???????c + c?? th??? verify ???????c 

		//checkToCheckboxOrRadioButton(winterRadio);
		//sleepInSecond(2);
		//Assert.assertTrue(driver.findElement(winterRadio).isSelected());
		
		//2- D??ng th??? kh??c, th??? span (??ang hi???n th??? ????? click) + ko verify ???????c, ko d??ng iselect ???????c, 
		//--> N??n d??ng h??m b??nh th?????ng ????? click.
		//By winterSpan=By.xpath("//span[contains(string(),'Winter')]");
		//driver.findElement(winterSpan).click();
		//sleepInSecond(2);
		
		//Span kh??ng c?? tr???nh th??i isselected n??n lu??n tr??? v??? tr???i th??i false
		//Assert.assertTrue(driver.findElement(winterSpan).isSelected());
		
		//3-Th??? span ????? click + th??? input ????? verify
		//By winterSpan=By.xpath("//span[contains(string(),'Winter')]");
		//driver.findElement(winterSpan).click();
		//sleepInSecond(2);
		
		//By winterRadio=By.xpath("//input[@value='Winter']");
		//Assert.assertTrue(driver.findElement(winterRadio).isSelected());
		//(Nh?????c ??i???m: define 2 locator cho 1 element
		//-> ko hay v?? n?? t???o ra nhi???u element
		//=> khi m?? b???o tr??(maintain)--> th?? ph???i b???o tr?? nhi???u ch???
	    //=> N??n m??nh c?? th??? dumgf h??m click by JS
		
		//=> ??? d?????i
		
		//4- Th??? input d??ng JS ????? click + verify lu??n
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
    	
    	Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Qu???ng Nam'and @aria-checked='false']")).isDisplayed());
    	
    	clickToElementByJS(By.xpath("//div[@aria-label='Qu???ng Nam']/div[contains(@class,'exportInnerBox')]"));
    	sleepInSecond(2);
    	
    	//Verify selected
    	//re chuot vao no phai highlight len thi moi verify duoc
    	Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Qu???ng Nam'and @aria-checked='true']")).isDisplayed());
    	
    }
	
	

	
	public void clickToElementByJS(By by) {
			WebElement element=driver.findElement(by);
			jsExecutor.executeScript("arguments[0].click();", element);
		}

		
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	//H??m ki???m tra xem checkbox ???????c select ch??a
	public void checkToCheckboxOrRadioButton(By by) {//Nh???n v??o tham s??? by
		WebElement checkbox=driver.findElement(by);
		if(!checkbox.isSelected()) {
			checkbox.click();
		}
	}
	
	//H??m ki???m tra checkbox ???????c b??? ch???n ch??a
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