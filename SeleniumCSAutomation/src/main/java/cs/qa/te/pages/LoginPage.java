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
	
	public void doLogin(String un, String pwd) {
		//elementUtil.doVisiblityOfElementLocated(username, 8).click();
		elementUtil.doVisiblityOfElementLocated(enterU, Constants.DEFALUT_TIMEOUT).sendKeys(un); 
		//elementUtil.doVisiblityOfElementLocated(nextButton, 8).click();
		
		elementUtil.doVisiblityOfElementLocated(enterP, Constants.DEFALUT_TIMEOUT).sendKeys(pwd);
	//	elementUtil.doVisiblityOfElementLocated(takeMeIn, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickLogin, 8).click();
		
	}
	
	
	
	public String getText() {
		
		String t = elementUtil.waitDoGetText(getT, 15);
		
		return t;
	}
	
	
}
