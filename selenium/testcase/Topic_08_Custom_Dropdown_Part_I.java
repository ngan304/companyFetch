package webdriver;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
	
	String [] firstMonth={"February","May","August"}; 
	String[] secondMonth= {"February","May","August","September"};

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

	//@Test
	public void TC_03_VueJS() {

		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		selectItemInCustomDropdow("//span[@class='caret']", "//ul[@class='dropdown-menu']//li", "Third Option");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]"))
						.isDisplayed());
	}

	//@Test
	public void TC_04_Angular() {
		driver.get("https://valor-software.com/ng2-select/");

		selectItemInCustomDropdow("//tab[@heading='Single']//i[@class='caret pull-right']", "//tab[@heading='Single']//a[@class='dropdown-item']/div", "Palermo");
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Select a single city']/following-sibling::ng-select//span[contains(@class,'ui-select-allow-clear')]")).getText(),"Palermo");

	}
		//@Test
		public void TC_05_Editable_01() {
			driver.get("https://valor-software.com/ng2-select/");
			
			enterAndSelectItemInCustomDropdow("//tab[@heading='Single']//i[@class='caret pull-right']","//tab[@heading='Single']//input[@placeholder='No city selected']", "//tab[@heading='Single']//a[@class='dropdown-item']/div", "Palermo");
			sleepInSecond(4);
			Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Select a single city']/following-sibling::ng-select//span[contains(@class,'ui-select-allow-clear')]")).getText(),"Palermo");
		}
		//@Test
		public void TC_05_Editable_02() {
			driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
			sleepInSecond(4);
			enterAndTabToCustomDropdow("//input[@class='search']", "Austria");
			sleepInSecond(4);
			Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Austria']")).isDisplayed());
			sleepInSecond(4);
		}
		
		@Test
		public void TC_06_Multiple() {
			driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");

			selectMultiItemInDropdown("(//button[@class='ms-choice'])[1]", "//div[@class='form-group row'][2]//div[@class=\"ms-drop bottom\"]//li", firstMonth);
			sleepInSecond(5);
			Assert.assertTrue(areItemSelected(firstMonth));
			
			driver.navigate().refresh();
			
			selectMultiItemInDropdown("(//button[@class='ms-choice'])[1]", "//div[@class='form-group row'][2]//div[@class=\"ms-drop bottom\"]//li", secondMonth);
			sleepInSecond(5);
			Assert.assertTrue(areItemSelected(secondMonth));
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

	//Hàm cho dropdown editable (enter and select selection)
	public void enterAndSelectItemInCustomDropdow(String parentXpath,String textboxXbath, String childXpath, String expectedItem) { // sử dụng biến
		
       driver.findElement(By.xpath(parentXpath)).click();
       sleepInSecond(4);
       
       driver.findElement(By.xpath(textboxXbath)).sendKeys(expectedItem);
       sleepInSecond(1);
       
       List<WebElement> allItems = driver.findElements(By.xpath(childXpath));
       System.out.println(allItems.size());

       
       for (WebElement item : allItems) {

    	   if (item.getText().trim().equals(expectedItem)) {

    		   if (!item.isDisplayed()) {

    			   jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
    			   sleepInSecond(1);

    		   }
    		   item.click();
    		   break;
    	   }
       }

	}
	
	//Hàm cho dropdown editable (enter and tab)
		public void enterAndTabToCustomDropdow(String textboxXbath, String expectedItem) { // sử dụng biến
			
	       driver.findElement(By.xpath(textboxXbath)).sendKeys(expectedItem);
	       sleepInSecond(1);
	       
	       driver.findElement(By.xpath(textboxXbath)).sendKeys(Keys.TAB);
	       sleepInSecond(1);
	    	   }
		
	//Hàm cho multiple select
		public void selectMultiItemInDropdown(String parentXpath, String childXpath, String[] expectedValueItem) {
			// 1: click vào cái dropdown cho nó xổ hết tất cả các giá trị ra
			driver.findElement(By.xpath(parentXpath)).click();

			// 2: chờ cho tất cả các giá trị trong dropdown được load ra thành công
			explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));

			List<WebElement> allItems = driver.findElements(By.xpath(childXpath));

			// Duyệt qa hết tất cả các phần tử cho đến khi thỏa mãn điều kiện
			for (WebElement childElement : allItems) {
				// "January", "April", "July"
				for (String item : expectedValueItem) {
					if (childElement.getText().equals(item)) {
						// 3: scroll đến item cần chọn (nếu như item cần chọn có thể nhìn thấy thì ko cần scroll)
						jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
						sleepInSecond(1);

						// 4: click vào item cần chọn
						childElement.click();
						sleepInSecond(1);
						
						List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
						System.out.println("Item selected = " + itemSelected.size());
						if (expectedValueItem.length == itemSelected.size()) {
							break;
						}
					}
				}
			}
		}
		
		//Ham verify cho case multiple select
		 public boolean areItemSelected(String[] months) {
				List<WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
				int numberItemSelected = itemSelected.size();
				//3

				String allItemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']/span)[1]")).getText();
				System.out.println("Text da chon = " + allItemSelectedText);
				//"February","May","August"
 
				//months ="February","May","August"
				if (numberItemSelected <= 3 && numberItemSelected > 0) {
					System.out.println(">0 & <=3 = "+ numberItemSelected);
					boolean status = true;
					for (String item : months) {
						if (!allItemSelectedText.contains(item)) {
							status = false;
							return status;
						}
					}
					return status;
				} else if (numberItemSelected == 12) {
					System.out.println("=12 => "+ numberItemSelected);
					return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='All selected']")).isDisplayed();
				} else if (numberItemSelected > 3 && numberItemSelected < 12) {
					System.out.println(">3 & <12 = "+ numberItemSelected);
					return driver.findElement(By.xpath("//button[@class='ms-choice']/span[text()='" + numberItemSelected + " of 12 selected']")).isDisplayed();
				} else {
					return false;
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