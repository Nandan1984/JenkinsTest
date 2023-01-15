package cs.qa.te.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class ElementUtil {
	
	private WebDriver driver;
	private char[] arr;
	private String counter;
	private static Sheet sheet;
	
	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}
	
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}
	public boolean doElementDisplay(By locator) {
		return driver.findElement(locator).isDisplayed();
	}
	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
		}
	
	public String doGetText(By locator) {
		return getElement(locator).getText().trim();
		
	}
	
	
	
	public void doClick(By locator) {
		getElement(locator).click();
	}
	
	public void sendKeys(By locator, String text) {
		getElement(locator).sendKeys(text);
	}
	
	
	public List<String> getElementsTextList(By locator){
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e : eleList) {
			String text = e.getText().trim();
			if(!text.isEmpty()) {
				eleTextList.add(text);
			}
			
		}
		System.out.println(eleTextList);
		 return eleTextList;
	}
	
	
	public List<String> getElementsSortTextList(By locator){
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e : eleList) {
			String text = e.getText().trim();
			if(!text.isEmpty()) {
				eleTextList.add(text);
				Collections.sort(eleTextList);
			}
			
		}
		System.out.println(eleTextList);
		 return eleTextList;
	}
	
	
	public List<String> getElementsRemoveIndexTextList(By locator, int rowNumber){
		List<WebElement> eleList = getElements(locator);
		eleList.remove(rowNumber);
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e : eleList) {
			String text = e.getText().trim();
			if(!text.isEmpty()) {
				eleTextList.add(text);
				Collections.sort(eleTextList);
			}
			
		}
		System.out.println(eleTextList);
		 return eleTextList;
	}
	
	public List<String> getElementsRemoveIndex2TextList(By locator, int rowNumber1, int rowNumber2){
		List<WebElement> eleList = getElements(locator);
		eleList.remove(rowNumber1);
		eleList.remove(rowNumber2);
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e : eleList) {
			String text = e.getText().trim();
			if(!text.isEmpty()) {
				eleTextList.add(text);
				Collections.sort(eleTextList);
			}
			
		}
		System.out.println(eleTextList);
		 return eleTextList;
	}
	
	
	
	public List<String> getElementsRemoveFromIndexTextList(By locator, int removeCell){
		List<WebElement> eleList = getElements(locator);
		eleList.remove(removeCell);
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e : eleList) {
			String text = e.getText().trim();
			if(!text.isEmpty()) {
				eleTextList.add(text);
				Collections.sort(eleTextList);
			}
			
		}
		System.out.println(eleTextList);
		 return eleTextList;
	}
	
		
	public List<String> getSelectElementsTextList(By locator){
		Select dropdown = new Select(getElement(locator));
		List<WebElement> dd = dropdown.getOptions();
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement j : dd) {
	        String text = j.getText();
	        eleTextList.add(text);
			}
		return eleTextList;
	}	
	
	public List<String> getSortedElementsTextList(By locator){
		List<WebElement> elLst = getElements(locator);
		List<String> elLstTxt =  new ArrayList<String>();
		
		for(WebElement e : elLst) {
			String text = e.getText().trim();
			if(!text.isEmpty()) {
				elLstTxt.add(text);
				Collections.sort(elLstTxt);
			}
			System.out.println(elLstTxt);
		}
		 return elLstTxt;
	}


	
	public void doSelectFromDropDown(By locator, String text) {
		List<WebElement> opt = getElements(locator);
		for(WebElement e : opt) {
			if(e.getText().equalsIgnoreCase(text)) {
				e.click();
				break;
			}
		}
	}
	
	
	
	
	
	
		
	public List<String> doSelectFromDropDownRepeat(By locator, By locator1){
		int dropDownList = getElements(locator1).size();
		List<String> eleTextList = new ArrayList<String>();
		
		for(int i=0; i<dropDownList; i++) {
			List<WebElement> lst = getElements(locator1);
			WebElement  lstGet = lst.get(i);
			String text = lstGet.getText();
			if(!text.isEmpty()) {
				eleTextList.add(text);
			}
			System.out.println(text);
			lstGet.click();
			driver.navigate().back();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			doClick(locator);
			dropDownList = getElements(locator1).size();
		}
			
			return  eleTextList;
		
		}
	
	
	
	public void doSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	
	public void doSelectByVisibleIndex(By locator, int value) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(value);
	}
	
	
	
	public void selectChoice(By locator, String... value) {
		List<WebElement> choiceList = getElements(locator);
		for(int i =0; i<choiceList.size(); i++) {
			String text = choiceList.get(i).getText();
			System.out.println(text);
			
			for(int j=0; j<value.length; j++) {
				if(text.equals(value[j])) {
					choiceList.get(i).click();
					break;
				}
			}
		}
		
	}
	
	
	
	public String getAttributeForValue(By locator, String text) {
		String getText = getElement(locator).getAttribute(text);
		return getText;
	}
	
	public  String getAttributeForValues(By locator, String text) {
	List<WebElement> lst = getElements(locator);
	List<String> elLstTxt =  new ArrayList<String>();
	StringBuilder strbul=new StringBuilder();
			
	for(WebElement txtList: lst) {
		String getTxt = txtList.getAttribute(text);
		elLstTxt.add(getTxt);
	}
	
	for(String str : elLstTxt) {
		strbul.append(str);
		strbul.append(",");
		
	}
	
	String str=strbul.toString();
	return str;
		
	}
	
	
	
	
	public boolean isAttribtuePresent(WebElement element, String attribute) {
	    Boolean result = false;
	    try {
	        String value = element.getAttribute(attribute);
	        if (value != null){
	            result = true;
	        }
	    } catch (Exception e) {}

	    return result;
	}
	
	
	public ArrayList<String> readTheExcelRow(String fileLocator ,int cellValue) throws IOException, InterruptedException {
		File src = new File(fileLocator);
		
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		
		
		ArrayList<String> arr = new ArrayList<>();

		
		for(int i=1; i<30; i++) {
			try {
			String str = sheet.getRow(i).getCell(cellValue).getStringCellValue();
	         	
			 System.out.println(str);

			 arr.add(str);
			 
			 
			 if(str.contains("-")==true) {
					break;
				}else if(str.isBlank()) {
					break;
				}
		
			} catch(IllegalStateException a) {
				int number = (int) sheet.getRow(i).getCell(cellValue).getNumericCellValue();
				
				String str = String.valueOf(number);
				 System.out.println(str);
				 
				
				 
				 arr.add(str);
				 if(str.matches("[0-9]+")==false) {
						break;
					}else if(str.contains("-")) {
						break;
					}
				
			}catch(NullPointerException e) {
				break;
			}
			
		}
		
		
		return arr;
	}
	

	
	public String readTheExcelSpecificRowCell(String fileLocator ,String sheetName, int rowValue,int cellValue) throws IOException, InterruptedException {
		File src = new File(fileLocator);
		
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		//XSSFSheet sheet = wb.getSheet("Sheet1");
		XSSFSheet sheet = wb.getSheet(sheetName);
		
		StringBuffer sb = new StringBuffer();
		
		try {
		String st = sheet.getRow(rowValue).getCell(cellValue).getStringCellValue();
		
		sb.append(st);
		}catch(IllegalStateException i) {
			double number = sheet.getRow(rowValue).getCell(cellValue).getNumericCellValue();
			
			String st2 = String.valueOf(number);
			sb.append(st2);
		}
		
		String str = sb.toString();
		return str;
	}
	

	public String readTheExcelRowWithMultipleCoulmnTotalSum(String fileLocator ) throws IOException, InterruptedException {
		File src = new File(fileLocator);
		
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		
		ArrayList<Integer> arr = new ArrayList<>();
		ArrayList<Integer> arr1 = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		
		for(int i=1; i<30; i++) {
			for(int j=14; j<30;j++) {
			
			try {
					
			String str = sheet.getRow(i).getCell(j).getStringCellValue();
			
			int getValue = Integer.parseInt(str);
			int getValue1 = +getValue;
			
			arr1.add(getValue1);
			System.out.println("getValue1 :"+getValue1);
			}
			catch(Exception e) {
				
				try {
				
					int number = (int) sheet.getRow(i).getCell(j).getNumericCellValue();
				
				
					int getValue1 = number;
				
				
					arr1.add(getValue1);
				
				//System.out.println("getValue1 :"+getValue1);
				}catch(Exception e1) {
					System.out.println("Ignore this");
				}
				
			}
			System.out.println("arr1 addAll :"+arr1);
		}
		
		int sum = 0;
		for(int l : arr1) {
			sum +=l;
		//	System.out.println("sum :"+sum);	
			
			sb.append(sum);
		}
		arr.add(sum);
		System.out.println("arr test " +arr);
		}
		int int1 = arr.get(28);
		System.out.println("INt1 :" +int1);
		
		
		String str = String.valueOf(int1);
		System.out.println("STR 4 :" +str);
		return str;
	
	}
	
	
	
	public String readTheExcelMultipleRowWithACoulmnTotalSum(String fileLocator, int cellValue ) throws IOException, InterruptedException {
		File src = new File(fileLocator);
		
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		
		ArrayList<Integer> arr = new ArrayList<>();
		ArrayList<Integer> arr1 = new ArrayList<>();
		StringBuffer sb = new StringBuffer();
		
		for(int i=1; i<30; i++) {
			try {
				String str = sheet.getRow(i).getCell(cellValue).getStringCellValue();
			
				int getValue = Integer.parseInt(str);
				int getValue1 = +getValue;
			
			arr1.add(getValue1);
			System.out.println("getValue1 :"+getValue1);
			}
			catch(Exception e) {
				
				try {
				
					int number = (int) sheet.getRow(i).getCell(cellValue).getNumericCellValue();
					int getValue1 = number;
					arr1.add(getValue1);
				
				//System.out.println("getValue1 :"+getValue1);
				}catch(Exception e1) {
					System.out.println("Ignore this");
				}
				
			}
			System.out.println("arr1 :"+arr1);
		
		
		int sum = 0;
		for(int l : arr1) {
			sum +=l;
		//	System.out.println("sum :"+sum);	
			
			sb.append(sum);
		}
		arr.add(sum);
		System.out.println("arr test " +arr);
		}
		int int1 = arr.get(28);
		System.out.println("INt1 :" +int1);
		
		
		String str = String.valueOf(int1);
		System.out.println("STR :" +str);
		return str;
	
	}
	
	public String readTheXlsExcelSpecificRowCell(String fileLocator ,String sheetName, int rowValue,int cellValue) throws IOException, InterruptedException {
		File src = new File(fileLocator);
		
		FileInputStream fis = new FileInputStream(src);
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		//XSSFSheet sheet = wb.getSheet("Sheet1");
		HSSFSheet sheet = wb.getSheet(sheetName);
		
		StringBuffer sb = new StringBuffer();
		
		try {
		String st = sheet.getRow(rowValue).getCell(cellValue).getStringCellValue();
		
		sb.append(st);
		}catch(IllegalStateException i) {
			double number = sheet.getRow(rowValue).getCell(cellValue).getNumericCellValue();
			
			String st2 = String.valueOf(number);
			sb.append(st2);
		}
		
		fis.close(); //new
		String str = sb.toString();
		return str;
	}
	
	public String readThePDFCell(String fileLocator) throws IOException, InterruptedException {
		
		
		File file = new File(fileLocator);
		PDDocument document = PDDocument.load(file);
		PDFTextStripper pdfStripper = new PDFTextStripper();
		
		String text = pdfStripper.getText(document);
		
		return text;
	}
	
	
	
	
	/*
	 * Wait using
	 * 
	 * 
	 */
	
	public String waitForGetPageTitle(String titleFraction, int timeOut) {
		waitForTitle(titleFraction, timeOut);
		return driver.getTitle();
	}
	

	
	public boolean waitForTitle(String titleFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.titleContains(titleFraction));
	}
	
	
	
	public boolean waitForTitleIs(String titleVal, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.titleIs(titleVal));
	}
	
	public WebElement doPresenceOfElementLocated(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	
	
	public List<WebElement> doPresenceOfElementsLocated(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}
	
	public void waitDoWithoutSelectByVisibleText(By locator, String text, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		List<WebElement> allOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		
		for(int i=0; i<allOptions.size() -1; i++) {
			if(allOptions.get(i).getText().contains(text)) {
				allOptions.get(i).click();
				break;
			}
		}
	}
	
	
	public String waitDoGetText(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		WebElement wb = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		 return wb.getText().trim();
		
	}
	
	public void waitDoClicks(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		List <WebElement> wb = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		System.out.println("x size:" + wb.size());
		for(WebElement e : wb) {
			e.click();
		}
	}
	

	
	public void waitWriteInExcelRow1(By locator, int cellValue, int timeOut) throws IOException, InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		
		
		File src = new File(".\\src\\test\\resources\\testdata\\sample5.xlsx ");
		
		FileInputStream fis = new FileInputStream(src);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheet("Sheet1");
		
		List<WebElement> list = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)));
		
		
		for(int b=0; b<list.size();b++) 
			
		for (WebElement wbE : list) {
			
			int u = b++;
			//int u = b;
			
            for(int i=u+1; i <=list.size(); i++) {
     
            	if(wbE.isDisplayed()) {
            		sheet.getRow(i).createCell(cellValue).setCellValue(wbE.getText());
            		break;
            	}else {
            		System.out.println("displaye blanked");
            	}
            
            
		}
        }
		FileOutputStream fos = new FileOutputStream(src);
		
		wb.write(fos);
		fis.close();
		
}
	
	


	
	public HashMap<String, String> convertArrayListToHashMap(ArrayList<String> arrayList) {
		
		HashMap<String, String> hashMap = new HashMap<>();
		for (String str : arrayList) {
			  
            hashMap.put(str, str);
        }
  
        return hashMap;
	}
	
public Hashtable<String, String> convertArrayListToHashTable(ArrayList<String> arrayList) {
		
		Hashtable<String, String> hashTable = new Hashtable<>();
		for (String str : arrayList) {
			  
			hashTable.put(str, str);
        }
  
        return hashTable;
	}
	
	
	public HashSet<String> converArrayListToHashSet(By locator, int timeOut){
		HashSet<String> hs = new HashSet<>();
		ArrayList<String> stringList = new ArrayList<String>();
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		List<WebElement> allOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		for(WebElement a: allOptions)
		{
			stringList.add(a.getText().toString());
			
		}
		
		hs.addAll(stringList);
		
		return hs;
	}
	
	
	public List<String> arrayListMethod(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		ArrayList<String> stringList = new ArrayList<String>();
		List<WebElement> allOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		for(WebElement a: allOptions)
		{
			stringList.add(a.getText().toString());
			
		}
		return stringList;
	}
	
	public String arrayListToString(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		ArrayList<String> stringList = new ArrayList<String>();
		List<WebElement> allOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		for(WebElement a: allOptions)
		{
			stringList.add(a.getText());
			
		}
		Collections.sort(stringList);
		String str = stringList.toString();
		return str;
	}
	
	public ArrayList<String> dualDimensionalArray(By locator1, By locator2, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		List<WebElement> allOptions1 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator1));
		List<WebElement> allOptions2 = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator2));
		ArrayList<String> arr = new ArrayList<String>();
		
		for(int i=0; i<allOptions1.size(); i++) {
			for(int j=0; j<allOptions2.size();j++) {
				System.out.println(allOptions2.size());
				arr.add(allOptions2.get(j).getText());
				
				
			}
		}
		return arr;
	}
	
	
	public void waitDoSelectFromDropDown(By locator, String text, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		List<WebElement> allOptions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		for(WebElement e : allOptions) {
			if(e.getText().equalsIgnoreCase(text)) {
				e.click();
				break;
			}
		}
	}
	
	public void waitDoSelectByVisibleText(By locator, String text, int timeOut) {
		Select select = new Select(doVisiblityOfElementLocated(locator, timeOut));
		select.selectByVisibleText(text);
	}
	
	public String getSelectedValueTextFromDropDown(By locator, int timeOut) {
		Select select = new Select(doVisiblityOfElementLocated(locator, timeOut));
		WebElement s = select.getFirstSelectedOption();
		String strSelectedValue = s.getText();
		
		return strSelectedValue;
	}
	
	
	public void getSelectedValueTextFromDropDown(By locator,  int timeOut, String...text) {
		Select select = new Select(doVisiblityOfElementLocated(locator, timeOut));
		List<WebElement> s = select.getOptions();
		
		for(WebElement wb : s) {
			String str = wb.getText();
			
			for(int j=0; j < s.size(); j++) {
				if(str.equals(text)) {
					wb.click();
				}
			}
		}
		
	}
	
	
	public void waitDoSelectElementsTextList(By locator,int timeOut, String...value ){
		
		List<WebElement> choiceList =  getElements(locator);
		if(!value[0].equalsIgnoreCase("select_all")) {
			for(int i=0; i<choiceList.size();i++) {
				String text = choiceList.get(i).getText();
				System.out.println(text);
				
				for(int j=0; j<value.length; j++) {
					if(text.equals(value[j])) {
						choiceList.get(i).click();
						break;
					}
				}
			}
		}else {
			try {
				for(WebElement e : choiceList) {
					e.click();
				}
			}catch(Exception e) {
					
				}
			}
		}
		
	
	
	
	
	
	public void waitDoSelectByVisibleValue(By locator, String text, int timeOut) {
		Select select = new Select(doVisiblityOfElementLocated(locator, timeOut));
		select.selectByValue(text);
	}
	

	
	public void waitForSendKeys(By locator, int timeOut, String text) {
		doPresenceOfElementLocated(locator, timeOut).sendKeys(text);
	}
	
	public void waitActionClicksPerform(By locator, int timeOut,By locator1, int timeOut1 ) {
		Actions builder = new Actions(driver);
		Action act = (Action) builder.moveToElement(doPresenceOfElementLocated(locator, timeOut)).click()
				.moveToElement(doPresenceOfElementLocated(locator1, timeOut1)).click().build();
		act.perform();
	}
	
	public void waitActionClickperForm(By locator, int timeOut) {
		Actions builder = new Actions(driver);
		builder.moveToElement(doVisiblityOfElementLocated(locator, timeOut)).click().build().perform();
	}
	
	public WebElement doVisiblityOfElementLocated(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public List<WebElement> doVisiblityOfElementsLocated(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public WebElement doClickForAdd3Product(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		WebElement wb = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		int number = 3;
		
		for(int i=0; i<=number;i++) {
			wb.click();
		}
		
		return wb;
	}
	
	
	public String WaitGetAttributeForValue(By locator, String text, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		WebElement wb = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		String getText = wb.getAttribute(text);
		return getText;
	}
	
	public  String waitGetAttributeForValues(By locator, String text, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		List<WebElement> lst = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		List<String> elLstTxt =  new ArrayList<String>();
		StringBuilder strbul=new StringBuilder();
				
		for(WebElement txtList: lst) {
			String getTxt = txtList.getAttribute(text);
			elLstTxt.add(getTxt);
		}
		
		for(String str : elLstTxt) {
			strbul.append(str);
			strbul.append(",");
			
		}
		
		String str=strbul.toString();
		return str;
			
		}
	
	public  void waitJavaScriptExecutorSelectDropDown(By locator, String text, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		WebElement dropdownElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		
		jse.executeScript("arguments[0].value='"+text+"'", dropdownElement);
	}
	
	
	public String waitDoGetDigitFromString(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		String str = wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
	      char[] chars = str.toCharArray();
	      StringBuilder sb = new StringBuilder();
	      for(char c : chars){
	         if(Character.isDigit(c)){
	            sb.append(c);
	         }
	      }
	      
	      String db = sb.toString();
	      return db;
	}
	
	
	
	public String waitDoGetFirstSelectedOptionText(By locator, int timeOut) {
		
		Select select = new Select(doVisiblityOfElementLocated(locator, 8));
		WebElement option = select.getFirstSelectedOption();
		
		String getText = option.getText();
		
		return getText;
		
	}
	
	
	
}
