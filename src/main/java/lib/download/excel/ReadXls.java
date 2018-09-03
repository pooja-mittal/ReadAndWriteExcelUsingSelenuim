package lib.download.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadXls {
	
	XSSFWorkbook workBook;
	XSSFSheet sheet1;
	
	public ReadXls(String excelPath) {
		try {
			File file=new File(excelPath);
			FileInputStream fis=new FileInputStream(file);
			workBook=new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	
	public String getData(int sheetNo, int row, int col) {
		sheet1=workBook.getSheetAt(sheetNo);
		String data=sheet1.getRow(row).getCell(col).getStringCellValue();
		return data;		
	}
	
	public int totalRows(int sheetIndex) {
		int rowCount=workBook.getSheetAt(sheetIndex).getLastRowNum();
		rowCount++;
		return rowCount;	
	}
	
}
