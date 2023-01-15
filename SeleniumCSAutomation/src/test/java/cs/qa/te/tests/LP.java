package cs.qa.te.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LP extends BaseTest{
	
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
