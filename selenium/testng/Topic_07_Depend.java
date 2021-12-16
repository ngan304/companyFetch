package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

import listenerConfig.TestListener;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;


@Listeners(TestListener.class)
public class Topic_07_Depend {
	//Testcase nên bị phụ thuộc
	//Test trên mà fail thì test dưới cũng fail

	@Test()
	public void Account_01_Create_New_Account() {
		
	}
	
	@Test(dependsOnMethods="Account_01_Create_New_Account")
	public void Account_02_View_Account() {
		
	}
	
	@Test(dependsOnMethods="Account_02_View_Account")
	public void Account_03_Edit_Account() {
		Assert.assertTrue(false);
	}
	
	@Test(dependsOnMethods="Account_03_Edit_Account")
	public void Account_04_Move_Account() {
		
	}
	
	@Test(dependsOnMethods="Account_04_Move_Account")
	public void Delete_Account() {
		
	}
	
}
