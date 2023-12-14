package com.tutorialsninja.qa.testData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataFromExcel {
	
	public FileInputStream ip;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	
	@DataProvider(name="ExcelData")
	public Object[][] getExcelData() throws Exception {
		Object[][] data = retriveExcelData("LoginData");
		
		return data;
	}
	
	
	
	public Object[][] retriveExcelData(String sheetName) throws IOException{
		
		try {
			ip = new FileInputStream(System.getProperty("user.dir")+ "src\\test\\java\\com\\tutorialsninja\\qa\\testData\\LoginData.xlsx");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		workbook = new XSSFWorkbook(ip);
		
		sheet = workbook.getSheet(sheetName);
			
		int row = sheet.getLastRowNum();
		int col = sheet.getRow(0).getLastCellNum();
		
		Object[][] excelData = new Object[row][col];
		
		for(int i=0; i<row; i++) {
			XSSFRow rows = sheet.getRow(i+1);
			
			for(int j=0; j<col; j++) {
				XSSFCell cell = rows.getCell(j);
				;
				CellType cellType = cell.getCellType();
				
				switch(cellType){
				case STRING:
					excelData[i][j]= cell.getStringCellValue();
					break;
				case NUMERIC: 
					excelData[i][j]= cell.getNumericCellValue();
					break;
				case BOOLEAN:
					excelData[i][j]= cell.getBooleanCellValue();
					break;
				default:
					excelData[i][j]="";
					break;
					
					

				}
			}
		}
		
		
		
		
		
		return excelData;
	}

}
