package cs.qa.te.tests;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import cs.qa.te.factory.DriverFactory;

import cs.qa.te.pages.LoginPage;
import cs.qa.te.pages.NegotiationsAddOfferPage;


public class BaseTest {
	
	public DriverFactory df;
	public Properties prop;
	public WebDriver driver;
	public LoginPage loginPage;
	
	public NegotiationsAddOfferPage addOfferPage;
	
		
	@BeforeTest(groups= {"Smoke", "Regression"})
	public void setUp() {
		df = new DriverFactory();
		prop = df.init_prop();
		driver = df.init_driver(prop);
		loginPage = new LoginPage(driver);
		addOfferPage = new NegotiationsAddOfferPage(driver);
		
				
	}
	
	
	
	@AfterTest(groups= {"Smoke", "Regression"})
	public void tearDown() {
		driver.quit();
	}

}
