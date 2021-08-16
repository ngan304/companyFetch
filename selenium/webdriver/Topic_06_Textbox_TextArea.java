package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_TextArea {
	WebDriver driver;
	String emailAddress, loginPageUrl, userID, passWord, customerID;
	
	// data test cho màn hình new customer.
	
	String name,dob, city, address, state, pin, phone;
	
	// data test cho màn hình Edit customer.
	
		String editAddress, editCity, editState, editPin, editPhone, editEmail;
	
	// Locator UI cho 2 màn hình new customer/ Edit Customer
	By nameTextboxBy=By.name("name"); 
	By genderTextboxBy= By.name("gender");
	By dobTextboxBy= By.name("dob");
	By addressTextboxBy=By.name("addr"); 
	By cityTextboxBy= By.name("city");
	By stateTextboxBy=By.name("state"); 
	By pinTextboxBy= By.name("pinno");
	By phoneTextboxBy=By.name("telephoneno"); 
	By emailTextboxBy= By.name("emailid");
	By passwordTextboxBy= By.name("password");

	@BeforeClass 
	public void beforeClass() {
		//fire fox 
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4");
		
		/// Init (gán giá trị) data (new customer)
		emailAddress= "jonlips" + generateEmail();
		name = "Jonh";
		dob = "1970-01-01";
		address = "33 Hunter";
		city = "HCM";
		state = "Pendle";
		pin = "123456";
		phone = "3434343435";
	
		//Init data (edit customer)
		editAddress = "Thuy Sy";
		editCity = "ThuDuc";
		editState = "Bass";
		editPin = "567890";
		editPhone = "0456789087";
		editEmail = "kim" + generateEmail();
		
			}

	@Test
	public void TC_01_Register() {
		loginPageUrl=driver.getCurrentUrl();
		
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(emailAddress);
		
		driver.findElement(By.name("btnLogin")).click();
		//hàm getText sẽ trả về String, tạo một biến có kiểu string để hứng dữ liệu
		 userID= driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		 passWord=driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
		
		
	}
	//Quay về màn hình login để login vào
	
	@Test
	public void TC_02_Login(){
		
		//Cách 1 : không hiệu quả, load 2 lần nên lâu
		//driver.navigate().back();
		//driver.navigate().back();
		
	
		// Cách2
		
		driver.get(loginPageUrl);
		
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(passWord);
		
		driver.findElement(By.name("btnLogin")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(),"Welcome To Manager's Page of Guru99 Bank");
		
	}

	@Test
	public void TC_03_Create_New_User() {
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		driver.findElement(nameTextboxBy).sendKeys(name);
		driver.findElement(dobTextboxBy).sendKeys(dob);
		driver.findElement(addressTextboxBy).sendKeys(address);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinTextboxBy).sendKeys(pin);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys(passWord);
		
		driver.findElement(By.name("sub")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector(".heading3")).getText(),"Customer Registered Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),emailAddress);
		
		 customerID=driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
//
	}
	@Test
	public void TC_04_Update_User() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();
		
		Assert.assertFalse(driver.findElement(nameTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(dobTextboxBy).isEnabled());
		Assert.assertFalse(driver.findElement(genderTextboxBy).isEnabled());
		
		Assert.assertEquals(driver.findElement(nameTextboxBy).getAttribute("value"),name);
		Assert.assertEquals(driver.findElement(dobTextboxBy).getAttribute("value"),dob);
		Assert.assertEquals(driver.findElement(addressTextboxBy).getText(),address);
		Assert.assertEquals(driver.findElement(cityTextboxBy).getAttribute("value"),city);
		Assert.assertEquals(driver.findElement(stateTextboxBy).getAttribute("value"),state);
		Assert.assertEquals(driver.findElement(pinTextboxBy).getAttribute("value"),pin);
		Assert.assertEquals(driver.findElement(phoneTextboxBy).getAttribute("value"),phone);
		Assert.assertEquals(driver.findElement(emailTextboxBy).getAttribute("value"),emailAddress);
		
		//Edit Customer
		driver.findElement(addressTextboxBy).clear();
		driver.findElement(addressTextboxBy).sendKeys(editAddress);
		
		driver.findElement(cityTextboxBy).clear();
		driver.findElement(cityTextboxBy).sendKeys(editCity);
		
		driver.findElement(stateTextboxBy).clear();
		driver.findElement(stateTextboxBy).sendKeys(editState);
		
		driver.findElement(pinTextboxBy).clear();
		driver.findElement(pinTextboxBy).sendKeys(editPin);
		
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys(editPhone);
		
		
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(emailTextboxBy).sendKeys(editEmail);
		
		driver.findElement(By.name("sub")).click();
		
		Assert.assertEquals(driver.findElement(By.cssSelector(".heading3")).getText(),"Customer details updated Successfully!!!");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),dob);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),editPhone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),editEmail);

	}

	@AfterClass
	public void afterClass() {
	driver.quit();
	}
	
	public String generateEmail() {
		Random rand= new Random();
		return rand.nextInt(9999) + "@mail.net";
	}
	
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	}

}