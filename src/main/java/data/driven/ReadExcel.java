package data.driven;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
		public static void main(String[] args) throws IOException {
			File file=new File("C:\\Users\\Pooja\\Desktop\\userCredential.xlsx");
			FileInputStream fis=new FileInputStream(file);
			XSSFWorkbook excelBook=new XSSFWorkbook(fis);
			XSSFSheet sheet1=excelBook.getSheetAt(0);
//			String dataoo=sheet1.getRow(0).getCell(0).getStringCellValue();
//			System.out.print("data at 00 " + dataoo);
//			String datao1=sheet1.getRow(0).getCell(1).getStringCellValue();
//			System.out.print(" data at 01 " + datao1);
			int totalRow=sheet1.getLastRowNum();
			System.out.println("total row " + totalRow);
			for(int i=0; i<=totalRow; i++) {
				String dataR=sheet1.getRow(i).getCell(0).getStringCellValue();
				String dataC=sheet1.getRow(i).getCell(1).getStringCellValue();
				System.out.println(dataR+ " " + dataC);
				
			}
			
		}

}
