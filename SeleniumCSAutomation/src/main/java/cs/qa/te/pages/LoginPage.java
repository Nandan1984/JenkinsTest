package cs.qa.te.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import cs.qa.te.utils.Constants;
import cs.qa.te.utils.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil elementUtil;
	
	//1. By Locator
	private By enterU = By.xpath("//input[@name='username']");
	private By enterP = By.xpath("//input[@name='password']");
	
	private By clickLogin = By.xpath("//button[@type='submit']");
	
	private By getT = By.xpath("//p[@class='oxd-text oxd-text--p']"); 
	
	
	
	//2. Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	//3. Page Actions:
	
	
	
	public void clickLogin() throws InterruptedException {
		Thread.sleep(25000);
		
		elementUtil.waitForSendKeys(enterU, 8, "Admin");
		elementUtil.waitForSendKeys(enterP, 8, "admin123");
		
		elementUtil.doVisiblityOfElementLocated(clickLogin, 8).click();
	
	}
	
	public String getText() {
		
		String t = elementUtil.waitDoGetText(getT, 15);
		
		return t;
	}
	
	
}
