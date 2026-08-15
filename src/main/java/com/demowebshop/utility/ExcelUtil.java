package com.demowebshop.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//ExcelFile--Workbook--Sheet--Row-Cell

public class ExcelUtil {
	FileInputStream fis;
	FileOutputStream fos;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	
	public Object[][] readExcelData(String fileName,String SheetName) throws Exception {
		fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\testDataFolder\\"+fileName);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet(SheetName);
		
		int noOfRows=sheet.getLastRowNum(); //4 [0,1,2,3,4]-->Java Count
		int noOfCells=sheet.getRow(0).getLastCellNum();  // 3 [1,2,3]-->Excel Count
		 
		Object[][] data= new Object[noOfRows][noOfCells];
		
		for (int r = 1; r <= noOfRows; r++) {//Earlier we started with 0 now 1 bcz we dont want headers
			row = sheet.getRow(r);
			for (int c = 0; c < noOfCells; c++) {
				cell = row.getCell(c);
				data[r-1][c] = cell.toString();
             }
			
		}
		
		workbook.close();
		fis.close();
		
		return data;	
		
		
/*
data[1 - 1][0] = cell.toString();
data[0][0] = "abdullapinjari123@gmail.com"; data[0][1] = "Bts*06bangtanarmy";
           
We don't want to pass the headers, that's why we start from r = 1.
We use data[r - 1][c] because the Object[][] array starts from index 0, whereas Excel data starts from row 1 (after the header).
cell.toString() converts the Excel cell value into a String so that we can store it, print it, or pass it.
We copy the values from Excel (String) into the Object[][] array because TestNG DataProviders only accept Object[][]. Excel itself cannot be returned directly to a test.
That's why we use Object[][]. Now the object is ready to inject data into the test.
Object can store any type of data, such as String, Integer, Double, Boolean, etc., making the DataProvider flexible for different data types.
*/
		
			
	}
}


/* 
public static void main(String[] args) throws Exception {
	FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\testDataFolder\\TestData.xlsx");
	XSSFWorkbook workbook= new XSSFWorkbook(file);
	XSSFSheet sheet =workbook.getSheet("LoginData");
	
	int noOfRows=sheet.getLastRowNum();
	int noOfCells=sheet.getRow(0).getLastCellNum();
	
	System.out.println("Total Rows:"+noOfRows);//4 [0,1,2,3,4]-->Java Count
	System.out.println("Total Cells:"+noOfCells);// 3 [1,2,3]-->Excel Count
	
	for(int r=0;r<=noOfRows;r++) {
		XSSFRow currentRow = sheet.getRow(r);
		for(int c=0;c<noOfCells;c++) {
			XSSFCell currentCell = currentRow.getCell(c);
			System.out.print(currentCell.toString()+"\t");  //converts the Excel cell value into a String,Provide Tab space
	}
		System.out.println();
	
}
	workbook.close();
	file.close();
}                                                                                                **/
