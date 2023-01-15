package cs.qa.te.tests;

import java.awt.AWTException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import cs.qa.te.utils.ExcelUtil;



public class NegotiationsAddOfferPageTest extends BaseTest {
	
	SoftAssert softAssert = new SoftAssert();
	// Navigate to the Login page
	
	// User5 == Supplier ==Jonathan Arnold
	//User6 == Importer == Kimberly SMith
	//User7 == Forwarder == Adam smith
	
		@BeforeClass()
		public void loginPageTest()throws  InterruptedException {
			loginPage.clickLogin();
			
		
		}
		
		@Test(priority =1, enabled=true) 
		public void createAddOffer() throws InterruptedException {
			String ye = loginPage.getText() ;
			
		}
		
	}
