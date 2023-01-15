package cs.qa.te.tests;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.common.base.Verify;


import cs.qa.te.utils.Constants;

public class LoginPageTest extends BaseTest {
	
	SoftAssert softAssert = new SoftAssert();
	
	
	// Navigate to the Login page
	@BeforeClass(groups= {"Smoke", "Regression"}, enabled = true)
	public void clickToLoginLink() throws  InterruptedException{
		
		loginPage.clickLogin();
		
	}
	
	
	//For Language settings
	@Test(enabled = true, priority=2)
	public void changeLanguageTest(){
			String text = loginPage.getText();
			
			System.out.println(text);
		}
		
		
	
}
