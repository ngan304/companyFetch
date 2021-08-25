package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown_Part_I {
	WebDriver driver;
	// Thư viện dùng để wait
	WebDriverWait explicitWait;

	// Thư viện để inject 1 đoạn javarscript code
	JavascriptExecutor jsExecutor;

	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		// fire fox
		// driver = new FirefoxDriver();

		// chrome
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 15); // khi nào tìm thấy element thì thôi, vidu : 2s đầu thấy element
														// thì nó ko cần chờ tới 15s

		// Ép kiểu tường minh
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); // nt

	}

	// @Test
	public void TC_01_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		selectItemInCustomDropdow(" //span[@id='number-button']//span[contains(@class,'ui-selectmenu-icon')] ",
				"//ul[@id='number-menu']//div", "5");
		sleepInSecond(2);

		Assert.assertTrue(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='5']"))
				.isDisplayed());

		selectItemInCustomDropdow(" //span[@id='number-button']//span[contains(@class,'ui-selectmenu-icon')] ",
				"//ul[@id='number-menu']//div", "15");
		sleepInSecond(2);

		Assert.assertTrue(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='15']"))
				.isDisplayed());

		selectItemInCustomDropdow(" //span[@id='number-button']//span[contains(@class,'ui-selectmenu-icon')] ",
				"//ul[@id='number-menu']//div", "3");
		sleepInSecond(2);

		Assert.assertTrue(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='3']"))
				.isDisplayed());

	}

	// @Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectItemInCustomDropdow("//i[@class='dropdown icon']", "//div[@role='option']//span", "Jenny Hess");
		sleepInSecond(2);
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='divider text' and text()='Jenny Hess']")).isDisplayed());

		selectItemInCustomDropdow("//i[@class='dropdown icon']", "//div[@role='option']//span", "Justen Kitsune");
		sleepInSecond(2);
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='divider text' and text()='Justen Kitsune']")).isDisplayed());

		selectItemInCustomDropdow("//i[@class='dropdown icon']", "//div[@role='option']//span", "Christian");
		sleepInSecond(2);
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@class='divider text' and text()='Christian']")).isDisplayed());
	}

	 @Test
	public void TC_03_VueJS() {

		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		selectItemInCustomDropdow("//span[@class='caret']", "//ul[@class='dropdown-menu']//li", "Third Option");
		sleepInSecond(2);
		Assert.assertTrue(
				driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]"))
						.isDisplayed());
	}

	@Test
	public void TC_04_Angular() {
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

		selectItemInCustomDropdow("//span[@aria-owns='games_options']", "//li[@class='e-list-item']", "Football");
		sleepInSecond(1);

		selectItemInCustomDropdow("//span[@aria-owns='games_options']", "//li[@class='e-list-item']", "Tennis");
		sleepInSecond(1);

	}

	public void selectItemInCustomDropdow(String parentXpath, String childXpath, String expectedItem) { // sử dụng biến
																										// parentXpath
																										// định nghĩa
																										// cho locator
		// -Click vào 1 element để cho xổ hết tất cả các item trong dropdown ra ->
		// parent element
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(4);

		// Chờ cho tất cả các item được load ra thành công -> child element
		// explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		// Chờ xong thì lấy hết tất cả các item này lưu vào list element (allItems) có
		// 19 items
		List<WebElement> allItems = driver.findElements(By.xpath(childXpath));
		System.out.println(allItems.size());

		// Duyệt qua từng item (dùng vòng lặp)
		// (Định nghĩa ra biến item là biến tạm,,, mỗi lần chạy thì nó duyệt qau items
		// trong 19 items)
		for (WebElement item : allItems) {
			// Get text của item đó ra và kiểm tra xem nó có bằng vs item text mình mong
			// muốn hay k
			if (item.getText().trim().equals(expectedItem)) {
				// Item cần chọn nó hiển thị -> Click vào item đó luôn
				/*
				 * if (item.isDisplayed()) { item.click(); }else { // Item cần chọn không hiển
				 * thị (ẩn bên dưới)
				 * 
				 * //scroll(selenium không hỗ trợ hàm scroll mà phải dùng javascript) đến item
				 * đó -> click vào item //
				 * jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				 * sleepInSecond(1);
				 * 
				 * item.click();
				 * 
				 * 
				 * }
				 */
				// Cách khác gọn hơn, không hiển thị thì scroll
				if (!item.isDisplayed()) {

					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					sleepInSecond(1);

				}
				item.click();
				break;

			}
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}