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
		loginPage.closePopUp();
		loginPage.clickLogin();
		
	}
	
	
	//For Language settings
	@Test(groups= {"Smoke", "Regression"},enabled = true, priority=2)
	public void changeLanguageTest(){
			loginPage.changeLanguage();
		}
		
		

	
	// Confirm Company logo
	@Test(groups= {"Smoke", "Regression"}, enabled = true, priority=5)
	public void loginPageLogoTest() {
		String urlText = loginPage.logoUrl().trim();
		System.out.println(urlText);
		System.out.println(Constants.LOGIN_PAGE_LOGO_URLText);
		//softAssert.assertTrue(urlText.contains(Constants.LOGIN_PAGE_LOGO_URLText));
		//softAssert.assertAll();
		Verify.verify(urlText.equalsIgnoreCase(Constants.LOGIN_PAGE_LOGO_URLText),"not matched");
			
		}
		
	
		
	//Get the login page above link list
	//Need to check might be not 
	
			
		
	
	//Resource drop downn list
	
	
	//Click on Resource drop down list options
	
	
	/*
	 * 
	 * 
	 * This code will be used when "Translation (Priority =10 & Priority =11 )" not to be tested for TE login time
	 
	@Test(enabled = false, priority=10)
	public void loginPageSignInTest() {
		loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		
	}
	
	*
	*
	*/
	
	
}
