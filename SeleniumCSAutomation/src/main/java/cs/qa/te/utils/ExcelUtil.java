package cs.qa.te.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	private static final String TEST_DATA_SHEET ="./src/test/resources/testdata/sample1.xlsx";
	
	private static Workbook book;
	private static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) {
		Object data[][] = null;
		
		try {
			FileInputStream ip = new FileInputStream(TEST_DATA_SHEET);
			
				book = WorkbookFactory.create(ip);
				book.getSheet(sheetName);
				
				data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
				
				for(int i=0; i<sheet.getLastRowNum(); i++) {
					for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
						data[i][j] = sheet.getRow(i+1).getCell(j).toString();
						
					}
				}
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return data;
		
	}
	
	
	public static void writeTestData(String value) throws InvalidFormatException, IOException {
		
		book.createSheet().createRow(0).createCell(0).setCellValue(value);
		
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(TEST_DATA_SHEET);
			book.write(fos);
			fos.close();
		}catch (Exception e) {
			e.printStackTrace();
		 
	}
	
}
}
