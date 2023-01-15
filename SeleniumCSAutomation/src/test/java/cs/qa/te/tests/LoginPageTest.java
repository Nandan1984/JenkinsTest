package cs.qa.te.tests;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.common.base.Verify;


import cs.qa.te.utils.Constants;

public class LoginPageTest extends BaseTest {
	

	
	
	// Navigate to the Login page
	@BeforeClass(enabled = true)
	public void clickToLoginLink() throws  InterruptedException{
		
		loginPage.clickLogin();
		
	}
	
	
	//For Language settings
	@Test(enabled = true)
	public void changeLanguageTest(){
			String text = loginPage.getText();
			
			System.out.println(text);
		}
		
		
	
}
