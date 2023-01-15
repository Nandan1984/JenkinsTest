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
	private By closeButton = By.xpath("//div[@class='mc-closeModal']"); 
	private By secondCloseButton = By.xpath("//div[@class='mc-closeModal']");
	
	private By loginLink = By.xpath("//a[contains(text(), 'Login')]");
	private By clickLangBtn = By.id("dropdownMenuLink");
	//Change the "@data-val" for as per lang settings
	private By clickLangDropDownOption = By.xpath("//a[@class='dropdown-item' and @data-val='RU']");
	private By userNameTxt = By.xpath("//span[@class='input-group-addon timberuser_label']");
	private By usernamePlaceholderText = By.xpath("//div[@class=\"input-group\"]/input");
	private By username = By.id("timberuser");
	private By nextButton = By.xpath("//input[@class='scheduleBtn w-100 next next_butt username_btn']");
	private By passwordTxt = By.xpath("//span[@class='input-group-addon timberuser_label']");
	private By passwordPlaceholderText = By.xpath("//div[@class=\"input-group\"]/input[@id ='timberpass']");
	private By password = By.xpath("//input[@id='timberpass']");
	private By takeMeIn = By.id("submit");
	private By loginText = By.xpath("//div[@class='col-lg-12 col-md-12']/h2");
	private By loginPageAboveLink = By.xpath("//ul[@class='navbar-nav mx-auto']//li");
	private By forgotPWDLink = By.xpath("//a[@href='https://www.pellets.supply/working/forgot-password']");
	private By timberExchangeLogo = By.xpath("//img[contains(@src, 'logo/te_home')]");
	private By resourceClick = By.xpath("//a[@class='nav-link dropdown-toggle']");
	private By resourceDropDownList = By.xpath("//ul[@class='dropdown-menu show']//a");
	private By clickResourceDropDownLink = By.xpath("//div[@aria-labelledby='navbarDropdown']/a");
	private By newsConfirmText = By.xpath("//div[@class='col-md-3 align-self-center mb-3']/h3");
	
	
	 
	
	
	
	//2. Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(this.driver);
	}
	
	//3. Page Actions:
	
	public void closePopUp() throws  InterruptedException{
		Thread.sleep(4000);
		try {
		elementUtil.doPresenceOfElementLocated(closeButton, 18).click();
		//for Testing Env
		}catch(Exception e) {
			System.out.println("No popup");
		}
		
		
	}
	
	public void clickLogin() throws InterruptedException {
		Thread.sleep(2000);
		try {
			elementUtil.doPresenceOfElementLocated(secondCloseButton, 8).click();
		}catch (Exception e) {
			System.out.println("No popup");
		}
		elementUtil.doVisiblityOfElementLocated(loginLink, 8).click();
	
	}
	
	public void changeLanguage(){
		elementUtil.doVisiblityOfElementLocated(clickLangBtn, 8).click();
		elementUtil.doVisiblityOfElementLocated(clickLangDropDownOption, 8).click();
	
		
	}
	
	
	
	public String userNameEnter(String un) {
		String userNamePlaceholderTxt = elementUtil.getElement(usernamePlaceholderText).getAttribute("placeholder");
		String userTxt = elementUtil.doGetText(userNameTxt);
		String userTxtPlaceholderTxt = userNamePlaceholderTxt.concat(userTxt);
		System.out.println(userTxtPlaceholderTxt);
		elementUtil.doClick(username);
		elementUtil.doPresenceOfElementLocated(username, Constants.DEFALUT_TIMEOUT).sendKeys(un); 
		elementUtil.doClick(nextButton);
		
		return userTxtPlaceholderTxt;
	}
	
	public String passwordEnter(String pwd) {
		String passwordPlaceholderTxt = elementUtil.doPresenceOfElementLocated(passwordPlaceholderText, 8).getAttribute("placeholder");
		String pwdTxt = elementUtil.doGetText(passwordTxt);
		String pwdTxtPlaceholderTxt = passwordPlaceholderTxt.concat(pwdTxt);
		System.out.println(pwdTxtPlaceholderTxt);
		elementUtil.doPresenceOfElementLocated(password, Constants.DEFALUT_TIMEOUT).sendKeys(pwd);
		elementUtil.doClick(takeMeIn);
		return pwdTxtPlaceholderTxt;
		
	}
	
	
	//This code will be used when translation not to be tested for TE login time
	public void doLogin(String un, String pwd) {
		elementUtil.doVisiblityOfElementLocated(username, 8).click();
		elementUtil.doVisiblityOfElementLocated(username, Constants.DEFALUT_TIMEOUT).sendKeys(un); 
		elementUtil.doVisiblityOfElementLocated(nextButton, 8).click();
		
		elementUtil.doVisiblityOfElementLocated(password, Constants.DEFALUT_TIMEOUT).sendKeys(pwd);
		elementUtil.doVisiblityOfElementLocated(takeMeIn, 8).click();
		
	}
	
	
	
	
	public List<String> getAboveLinks() {
		List<WebElement> aboveList = new ArrayList<WebElement>(elementUtil.getElements(loginPageAboveLink));
		List<String> aboveTextList = new ArrayList<String>();
		for(WebElement e : aboveList) {
		aboveTextList.add(e.getText());
		}
		return aboveTextList;
	}
	
	public String loginPageText() {
		return elementUtil.doGetText(loginText);
	}
	
	public String forogtPasswordLink() {
		elementUtil.doElementDisplay(forgotPWDLink);
		return elementUtil.doGetText(forgotPWDLink);
	}
		
	public String logoUrl() {
	String logoUrlText = elementUtil.getElement(timberExchangeLogo).getAttribute("src");
	return logoUrlText;
	}
	
	public List<String> resourceOptionsList() {
		elementUtil.doClick(resourceClick);
		List<WebElement> resourceList = new ArrayList<WebElement>(elementUtil.getElements(resourceDropDownList));
		List<String> resourceTextList = new ArrayList<String>();
		for(WebElement e : resourceList) {
			resourceTextList.add(e.getText());
			}
		return resourceTextList;
	}
	
		
	public List<String> clickOnResourceListOptions(){
		elementUtil.doPresenceOfElementLocated(resourceClick, 6);
		return elementUtil.doSelectFromDropDownRepeat(resourceClick, clickResourceDropDownLink);
	
	}
	
	
}
