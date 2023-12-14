package com.tutorialsninja.qa.testData;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelData {

	public static FileInputStream ip;
	public static XSSFWorkbook workBook;
	public static XSSFSheet sheet;
	
	@DataProvider(name = "TN")
	public static Object[][] excelData () throws IOException {
		Object[][] data = dataFromExcelSheet("loginTN");
		return data;}
	
	
	
	public static Object[][] dataFromExcelSheet(String sheetName) throws IOException {
		
		ip = new FileInputStream(System.getProperty("user.dir")+ "\\src\\test\\java\\com\\tutorialsninja\\qa\\testData\\LoginData.xlsx");
		
		workBook = new XSSFWorkbook(ip);
		
		sheet = workBook.getSheet(sheetName);
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		
		
		for(int i = 0; i<rows; i++) 			{
			XSSFRow row = sheet.getRow(i+1);
					
					for(int j = 0; j<cols; j++) {
						XSSFCell cell = row.getCell(j);
						
						CellType cellType = cell.getCellType();
						
						switch(cellType) 		{
						
						case STRING:
							data[i][j] = cell.getStringCellValue();
							break;
						case NUMERIC:
							data[i][j] = cell.getNumericCellValue();
							break;
						case BOOLEAN:
							data[i][j] = cell.getBooleanCellValue();
						case BLANK:
							break;
						case ERROR:
							break;
						case FORMULA:
							break;
						case _NONE:
							break;
						default:
							break;

												}					
												}
												}
		return data;
		
			
		}	
	}
	
	
	

