package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Element_Method {
	WebDriver driver;

	@BeforeClass 
	public void beforeClass() {
		//Mở ra 1 cái trình duyệt
		driver = new FirefoxDriver();
		
		//Wait cho element xuất hiện trong 1 khoảng time là xxx second
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Mở 1 page url lên
		driver.get("https://demo.nopcommerce.com/");
		
			}

	@Test
	public void TC_01_Web_Element() {
		// Muốn thao tác được với element thì phải tìm element trước
		
		// Thao tác với 1 element thì dùng hàm này
		driver.findElement(By.id("")); //**
		
		// Hàm tìm nhiều element 
		driver.findElements(By.id("")); //**
		
		// Nếu như mình chỉ thao tác với element 1 lần thì không cần khai báo biến
		driver.findElement(By.id("small-searchterms")).sendKeys("Kim Ngan");  //**
		
		//Nếu cần thao tác với element nhiều lần thì nên khai báo biến
		WebElement searchTextbox=driver.findElement(By.id("small-searchterms"));
		searchTextbox.clear();  //**
		searchTextbox.sendKeys("Apple");
		searchTextbox.getAttribute("value");  //**
		
		//Đếm xem có bao nhiêu element thỏa mãn điều kiện
		// vd đếm có bao nhiêu check box
		//Verify số lượng element trả về như mong đợi
		// Thao tác với tất cả các loại element giống nhau trong 1 page (checkbox/tickbox)
		
		// Lấy ra số lượng thì phải lưu list lại thì mới thao tác được ()dùng list java của thư viện Util
		List<WebElement> checkboxes=driver.findElements(By.xpath("//div[@class='inputs']/input[not(@type='checkbox')]"));
		
		
		//Verify có đúng 6 textbox tại form register
		Assert.assertEquals(checkboxes.size(),"6");
		
		WebElement singleElement=driver.findElement(By.className(""));
		//Textbox/ TextArea/ Editable dropdown
		//Dữ liệu được toàn vẹn 
		singleElement.clear();
		singleElement.sendKeys("");
		
		//Button/ link/radio/ checkbox/ Custom dropdown/...
		singleElement.click();  //**
		
		//Các hàm có tiền tố bắt đầu bằng get luôn luôn trả về dữ liệu
		// vd: getTitle/ getCurrentUrl/ getPageSource/ getAttribute/ getCssValue/ getText/....
		
		singleElement=driver.findElement(By.xpath("//input[@id='FirstName']"));
		singleElement.getAttribute("value");// lấy value trong field firstname
		
		singleElement=driver.findElement(By.xpath("//input[@id='small-searchterms']"));
		singleElement.getAttribute("placeholder");// có thể dùng trong trường hợp lấy placeholder text
		
		//Lấy ra giá trị của các thuộc tính css-thường dùng để test giao diện (GUI) - ít dùng
		// Font/ Size/ Color/ Background/...
		singleElement=driver.findElement(By.cssSelector(".search-box-button"));
		singleElement.getCssValue("background-color"); //-> Khi chạy xong sẽ lấy ra màu này : #4ab2f1  //**
		
		singleElement.getCssValue("text-transform"); //-> Khi chạy xong sẽ lấy ra  : uppercase
		
		//Lấy ra tọa độ của element so với page hiện tại(Get góc bên ngoài element)
		singleElement.getLocation(); // test UI
		
		//Lấy ra kích thước của element (rộng X cao) -> get góc bên trong element
		singleElement.getSize();
		
		// Lấy ra location + Size
		singleElement.getRect();
		
		// Chụp hình lỗi => đưa vào HTML report
		singleElement.getScreenshotAs(OutputType.FILE);  //**
		
		
		//id/class/Css/name/....
		// Từ 1 element ko biết tagname -> lấy ra được cái tagname truyền vào cho 1 locator khác
		singleElement=driver.findElement(By.cssSelector(".search-box-button"));
		String searchButtonTagname=singleElement.getTagName();  //**
		searchTextbox=driver.findElement(By.xpath("//"+searchButtonTagname+"[@class='inputs']/input[not(@type='checkbox')]"));
		
		
		//Lấy ra text của element (Header/Link/Message/...)
		singleElement.getText();  //**
		
		// Các hàm có tiền tố là isXXX thì 100% trả về kiểu boolean
		// true/false
		
		//Kiểm tra xem 1 element là hiển thị cho người dùng thao tác hay ko
		// true: đang hiển thị 
		// false: không hiển thị
		singleElement.isDisplayed();   //**
		
		//Kiểm tra xem 1 element có disable ko 
		// Disable: người dùng ko thao tác được
		// true: ko thao tác được
		// false: có thể thao tác
		singleElement.isEnabled();  //*
		
		//Kiểm tra xem 1 element đã được chọn rồi hay chưa
		//Checkbox/ Radio/ Dropdown (Dropdown có thư viện riêng của nó rồi)
		//true: đã chọn rồi
		//false: chưa được chọn
		singleElement.isSelected();  //*
		
		// NÓ thay cho hành vi ENTER vào textbox/ click vào button
		// Chỉ dùng được trong form (Login/ Search/ đăng ký)
		singleElement.submit();
		
		singleElement=driver.findElement(By.id("small-searchterms"));
		singleElement.sendKeys("Apple");
		singleElement.submit();
	}

	@Test
	public void TC_02(){
		
	}

	@Test
	public void TC_03() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	}


}
