package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//bt 5, 6 trong file bai tap topic4 Xpath

public class Topic_05_Web_Element_Execise {
	// Khai bao bien (Declare)
	WebDriver driver;
	String firstName, lastName, emailAddress, password, fullName;
	
	By emailTextbox =By.id("mail");
	By educationTextArea = By.id("edu");
	By under18Radio = By.id("under_18");
    By javaCheckbox = By.id("java"); 
    
    By passwordTextbox= By.id("password");
    By disableCheckbox= By.id("check-disbaled");
    By disableButton= By.id("button-disabled");

	@BeforeClass
	public void beforeClass() {
		// Khởi tạo biến driver
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Khởi tạo data test
		firstName = "Tomoe";
		lastName = "Yuyama";
		fullName=firstName +" "+lastName;
		emailAddress = "tomoe" + generateEmail(); // Nhấn F3 ra hàm phía dưới
		//tomoe9947@mail.com
		//.....
		password = "123456";

	}

	//@Test
	public void TC_01_Create_New_Account() {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.findElement(By.xpath("//a[@title='Create an Account'] ")).click();

		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);

		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Thank you for registering with Main Website Store.");
		
		//dùng hàm isdisplayed để kiểm tra
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(string(),'" +fullName +"')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(string(),'" +emailAddress +"')]")).isDisplayed());
		
		//Dùng hàm getText và verify contains (fullname/ email)
		
		String contactInformation= driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(contactInformation);
		
		//contactInformation.contains(fullName): xem contactInformation có chứa fullName thì trả về True
		Assert.assertTrue(contactInformation.contains(fullName));
		Assert.assertTrue(contactInformation.contains(emailAddress));
		
		driver.findElement(By.cssSelector(".skip-account")).click();
		
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();

	}

	//@Test
	public void TC_02_Login() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		driver.findElement(By.cssSelector("#email")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("#pass")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@title='Login'] ")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='page-title']//h1")).getText(),"MY DASHBOARD");
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),"Hello, " +fullName +"!");
		
		
		
		
		
		
		

	}
    
	//Bt Element trong topic 6
	//@Test
	public void TC_03_Displayed_newbie() {// kiểu viết basic cho người mới, if else cứ lặp lại 
		//liên tục -> không tối ưu code
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// ham kiem tra dk
		// neu dk dung thi moi vao dk
		// neu sai thi ko vao
		if(driver.findElement(By.id("mail")).isDisplayed()) {
			driver.findElement(By.id("mail")).sendKeys("Automation FC");
			System.out.println("Mail textbox is displayed");
			
		} else {
			System.out.println("Mail textbox is not displayed (undisplayed)");
		}
		
		if(driver.findElement(By.id("edu")).isDisplayed()) {
			driver.findElement(By.id("edu")).sendKeys("Edu Automation FC");
			System.out.println("Edu textarea is displayed");
			
		} else {
			System.out.println("Edu textarea is not displayed (undisplayed)");
		}
		
		if(driver.findElement(By.id("under_18")).isDisplayed()) {
			driver.findElement(By.id("under_18")).click();
			System.out.println("Radio button 'Under 18' is displayed");
			
		} else {
			System.out.println("Radio button 'Under 18' is not displayed (undisplayed)");
		}
	}
	
	//@Test
	public void TC_03_Displayed_function() {// Cách viết để tối ưu code
		//driver.get("https://automationfc.github.io/basic-form/index.html");
		
		
		if(isElementDisplayed(emailTextbox)) {
			sendkeyToElement(emailTextbox,"Automation FC");
		}
		if(isElementDisplayed(educationTextArea)) {
			sendkeyToElement(educationTextArea,"Automation FC");
		}
		
		if(isElementDisplayed(under18Radio)) {
			clickToElement(under18Radio);
		}
	}
		
		
	//@Test
	public void TC_04_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		clickToElement(under18Radio);
		clickToElement(javaCheckbox);
		
		//Sau khi click xong thì verify checkbox/ radio đã được chọn rồi
		Assert.assertTrue(isElementSelected(under18Radio));
		Assert.assertTrue(isElementSelected(javaCheckbox));
		
		clickToElement(javaCheckbox);
		clickToElement(under18Radio);
		
		//verify checkbox is deselected
				Assert.assertFalse(isElementSelected(javaCheckbox));
					
				Assert.assertTrue(isElementSelected(under18Radio));
		
	}
	//@Test
	public void TC_05_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Expected nó enabled
		Assert.assertTrue(isElementEnabled(emailTextbox));
		Assert.assertTrue(isElementEnabled(educationTextArea));
		Assert.assertTrue(isElementEnabled(under18Radio));
		Assert.assertTrue(isElementEnabled(javaCheckbox));
		
		//Expected nó disabled(readonly)
		Assert.assertFalse(isElementEnabled(passwordTextbox));
		Assert.assertFalse(isElementEnabled(disableCheckbox));
		Assert.assertFalse(isElementEnabled(disableButton));
	}

	//Bài tập Mailchimp trong topic 6
	@Test
	public void TC_06_Register_Validate() {
		driver.get("https://login.mailchimp.com/signup/");
		
		By passwordTextbox= By.id("new_password");
		By newsletterCheckbox=By.id("marketing_newsletter");
		By signUpButton=By.cssSelector("#create-account");
		By upperCaseCompleted = By.cssSelector(".uppercase-char.completed");
		By lowerCaseCompleted= By.cssSelector(".lowercase-char.completed");
		By numberCompleted= By.cssSelector(".number-char.completed");
		By specialCompleted= By.cssSelector(".special-char.completed");
		By greaterThan8CharCompleted= By.xpath("//li[@class=\"8-char completed\"]");
		
		driver.findElement(By.id("email")).sendKeys("Kimngan@gmail.com");
		driver.findElement(By.id("new_username")).sendKeys("Kimngan");
		
		//Uppercase
		driver.findElement(passwordTextbox).sendKeys("AUTO");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(upperCaseCompleted));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		//Lowercase
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("auto");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(lowerCaseCompleted));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		//Number
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(numberCompleted));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		//Special
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("!@#");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(specialCompleted));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		//>=8 char
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("automationtesting");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(lowerCaseCompleted));
		Assert.assertTrue(isElementDisplayed(greaterThan8CharCompleted ));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		//All criteria -> tất cả nó không còn hiện nữa nên dùng false
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("Kimngan#123");
		sleepInSecond(2);
		Assert.assertFalse(isElementDisplayed(upperCaseCompleted));
		Assert.assertFalse(isElementDisplayed(lowerCaseCompleted));
		Assert.assertFalse(isElementDisplayed(specialCompleted));
		Assert.assertFalse(isElementDisplayed(numberCompleted));
		Assert.assertFalse(isElementDisplayed(greaterThan8CharCompleted ));
		
		Assert.assertTrue(isElementEnabled(signUpButton));
		
		clickToElement(newsletterCheckbox);
		Assert.assertTrue(isElementSelected(newsletterCheckbox));
			}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	
	public boolean isElementDisplayed(By by) {
		if (driver.findElement(by).isDisplayed()) {
			System.out.println(by + " is displayed");
			return true;
		} else {
			System.out.println(by +" is not displayed");
			
		}
		return false;
		
	}
	
	public boolean isElementSelected(By by) {
		if (driver.findElement(by).isSelected()) {
			System.out.println(by + "is selected");
			return true;
		} else {
			System.out.println(by +"is not selected");
			
		}
		return false;
		
	}
	
	

	public boolean isElementEnabled(By by) {
		if (driver.findElement(by).isEnabled()) {
			System.out.println(by + "is enabled");
			return true;
		} else {
			System.out.println(by +"is not disabled");
			
		}
		return false;
		
	}
	
	
	public void sendkeyToElement(By by, String value) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
	}
	public void clickToElement(By by) {
		driver.findElement(by).click();
	}
	
	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999)+ "@mail.vn";
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}