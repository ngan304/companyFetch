package testng;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;



public class Topic_03_Priority_Enabled {
	// sort: Alphabet
	// 0-9 A-Z

	@Test(priority=1, description=" customer can new an account")
	public void Create_New_Account() {
		
	}
	
	@Test(priority=2)
	public void View_Account() {
		
	}
	
	@Test(priority=3)
	public void Edit_Account() {
		
	}
	
	@Test(priority=4)
	public void Move_Account() {
		
	}
	
	@Test(enabled=false)
	public void Delete_Account() {
		
	}
	
}
