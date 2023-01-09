package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_02_Run_On_Browser {
	WebDriver driver;
	String projectPath=System.getProperty("user.dir");
	//@Test
	public void TC_01_Latest() {
		// Firefox latest:89
		// SElenium 3.141.59
		// TestNG 6.14.3
		// Gecko Driver
		System.setProperty("webdriver.gecko.driver", projectPath+ ".\\browserDrivers\\geckodriver.exe"); 
				driver = new FirefoxDriver();
				driver.get("https://m.facebook.com/");
				driver.quit();

	}
	@Test
	public void TC_02_Firefox_Old() {
		//Firefox 47.0.2
		// Selenium 2.53.1
		//ko TestNG
		// Ko Gecko Driver
		driver= new FirefoxDriver();
		driver.get("https://m.facebook.com/");
		driver.quit();
	}
	@Test
	public void TC_03_Run_On_Edge_Chromium() {
		System.setProperty("webdriver.edge.driver",projectPath +"\\browserDrivers\\msedgedriver.exe");
		driver=new EdgeDriver();
	}
	

}
