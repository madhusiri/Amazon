package com.amazon.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.sun.media.sound.InvalidFormatException;

public class ExcelLibrary {
	
	public static String filePath = "./src/main/java/com/amazon/utils/TestData.xlsx";
	public static String getExcelData(String sheetNAme, int rowNum, int colNum) 
			throws EncryptedDocumentException, InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException{
		FileInputStream fis = new FileInputStream(filePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetNAme);
		Row row = sh.getRow(rowNum);
		String data = row.getCell(colNum).getStringCellValue();
	    wb.close();
		return data;
	}
	
	public void setExcelData(String sheetNAme, int rowNum, int colNum,String data) 
			throws EncryptedDocumentException, InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException{
		FileInputStream fis = new FileInputStream(filePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetNAme);
		Row row = sh.getRow(rowNum);
	    Cell cel = row.createCell(colNum);
	    cel.setCellValue(data);
	    FileOutputStream fos = new FileOutputStream(filePath);
	    wb.write(fos);
	    wb.close();
		
	}
	public int getRowCount(String sheetNAme) throws EncryptedDocumentException, InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException{
		FileInputStream fis = new FileInputStream(filePath);
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheetNAme);
		int rowIndex = sh.getLastRowNum();
		return rowIndex;
		
	}


}
