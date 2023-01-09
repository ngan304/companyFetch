package webdriver;

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

public class Topic_15_Javascript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String emailAddress, loginPageUrl, userID, passWord, customerID;

	// data test cho màn hình new customer.

	String name, dob, city, address, state, pin, phone;

	// Locator UI cho 2 màn hình new customer/ Edit Customer
	By nameTextboxBy = By.name("name");
	By genderTextboxBy = By.name("gender");
	By dobTextboxBy = By.name("dob");
	By addressTextboxBy = By.name("addr");
	By cityTextboxBy = By.name("city");
	By stateTextboxBy = By.name("state");
	By pinTextboxBy = By.name("pinno");
	By phoneTextboxBy = By.name("telephoneno");
	By emailTextboxBy = By.name("emailid");
	By passwordTextboxBy = By.name("password");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 20);

		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	// @Test
	public void TC_01_Live_Guru() {
		navigateToUrlByJS("http://demo.guru99.com/v4/");

		String demoGuruDomain = (String) executeForBrowser("return document.domain;");
		Assert.assertEquals(demoGuruDomain, "demo.guru99.com");

		String demoGuruURL = (String) executeForBrowser("return document.url");
		Assert.assertEquals(demoGuruURL, "http://demo.guru99.com/v4/");

		hightlightElement("//a[text()='here']");

		clickToElementByJS("//a[text()='here']");

	}

	// @Test
	public void TC_02() {
		driver.get("https://sieuthimaymocthietbi.com/account/register");
		String validationMessage;
		validationMessage = getElementValidationMessage("//input[@id='lastName']");

		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();
		Assert.assertEquals(validationMessage, "Please fill out this field.");

		// Input Lastname
		driver.findElement(By.id("lastName")).sendKeys("kim Ngan");
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();

		validationMessage = getElementValidationMessage("//input[@id='firstName']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");

		// Input firstname
		driver.findElement(By.id("firstName")).sendKeys("Tran");
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();

		validationMessage = getElementValidationMessage("//input[@id='email']");
		Assert.assertEquals(validationMessage, "Please fill out this field.");

		// Input email : abc
		driver.findElement(By.id("email")).sendKeys("abc");
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();

		validationMessage = getElementValidationMessage("//input[@id='email']");
		Assert.assertEquals(validationMessage, "Please enter an email address.");

		driver.findElement(By.id("email")).sendKeys("@testing");
		driver.findElement(By.xpath("//button[text()='Đăng ký']")).click();

		validationMessage = getElementValidationMessage("//input[@id='email']");
		Assert.assertEquals(validationMessage, "Please match the requested format.");

	}

	@Test
	public void TC_03() {
		driver.get("http://demo.guru99.com/v4");
		/// Init (gán giá trị) data (new customer)
		emailAddress = "jonlips@gmail.com";
		name = "Jonh";
		dob = "1970-01-01";
		address = "33 Hunter";
		city = "HCM";
		state = "Pendle";
		pin = "123456";
		phone = "3434343435";

		loginPageUrl = driver.getCurrentUrl();

		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(emailAddress);

		driver.findElement(By.name("btnLogin")).click();
		// hàm getText sẽ trả về String, tạo một biến có kiểu string để hứng dữ liệu
		userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		passWord = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

		driver.get(loginPageUrl);

		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(passWord);

		driver.findElement(By.name("btnLogin")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(nameTextboxBy).sendKeys(name);
		//remove attribute
		removeAttributeInDOM("//input[@id='dob']", "type");
		sleepInSecond(5);
		driver.findElement(dobTextboxBy).sendKeys(dob);
		
		driver.findElement(addressTextboxBy).sendKeys(address);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinTextboxBy).sendKeys(pin);
		driver.findElement(phoneTextboxBy).sendKeys(phone);
		driver.findElement(emailTextboxBy).sendKeys(emailAddress);
		driver.findElement(passwordTextboxBy).sendKeys(passWord);

		driver.findElement(By.name("sub")).click();

		Assert.assertEquals(driver.findElement(By.cssSelector(".heading3")).getText(),
				"Customer Registered Successfully!!!");
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dob);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				emailAddress);
	}

	@AfterClass
	public void afterClass() {

		// driver.quit();
	}

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));

		return status;

	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}