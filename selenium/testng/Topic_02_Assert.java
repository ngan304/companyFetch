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



public class Topic_02_Assert {
	Object studentNumber = null;
	@Test()
	public void TC_01_Assert() {
		String studentName="Kim Ngan";
		

		//Những hàm trả về kiểu boolean: isDisplayed/isEnabled/isSelected/isMultiple
		
		//Verify 1 điều kiện trả về là đúng
		Assert.assertTrue(studentName.contains("Ngan"));
		
		//Verify 1 điều kiện trả về là sai
		Assert.assertFalse(studentName.contains("trung"));
		
		//Verify 2 cái điều kiện bằng nhau 
		Assert.assertEquals(studentName, "Kim Ngan");
		
		//Verify 2 cái điều kiện ko bằng nhau bằng nhau 
		Assert.assertNotEquals(studentName, "UY");
		
		Assert.assertNull(studentNumber);
		studentNumber="Trinh Kim";
		Assert.assertNotNull(studentNumber);
				
	}
	
}
