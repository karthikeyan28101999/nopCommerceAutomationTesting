package com.nopecommerce.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Calendar;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelLibrary {
	public static String projectPath=System.getProperty("user.dir");
	public String path;
	
	public FileInputStream excel;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public ExcelLibrary() {
		this.path=projectPath+"/excel/name.xlsx";
		
		try {
			excel=new FileInputStream(new File(path));
			workbook=new XSSFWorkbook(excel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ExcelLibrary(String path) {
		this.path=path;
		
		try {
			excel=new FileInputStream(new File(path));
			workbook=new XSSFWorkbook(excel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Check given sheet Name is avalaible in workbook or not
	public boolean isSheetExist(String sheetName)
	{
		int index=workbook.getSheetIndex(sheetName);
	//	System.out.println(index);
		if(index==-1)
		{
			index=workbook.getSheetIndex(sheetName.toUpperCase());
			if(index==-1)
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		else {
			return true;
		}
	}
	
	// Methode to get exact row count
	public int getRowCount(String sheetName)
	{
		if(!isSheetExist(sheetName))
		{
			return -1;
		}
		sheet=workbook.getSheet(sheetName);;
		int row=sheet.getLastRowNum()+1;
		return row;
	}
	
	// Methode to get exact column count
	public  int getColumncount(String sheetName)
	{
		if(!isSheetExist(sheetName))
		{
			return -1;
		}
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(0);
		if(row==null)
		{
			return -1;
		}
		return row.getLastCellNum();
	}
	
	//methode is used to get data from the exel 
	public String getCellData(String sheetName,int colNum,int rowNum){
		try{
		 	if(!isSheetExist(sheetName))
			{
				return null;
			}
			if(rowNum <0)
				return null;	
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		if(row==null)
			return null;
		cell = row.getCell(colNum);
		if(cell==null)
			return "";
		
	  if(cell.getCellType().name().equals("STRING")) {
		  return cell.getStringCellValue();
	  }
		  
	  else if(cell.getCellType().name().equals("NUMERIC") || cell.getCellType().name().equals("FORMULA") ){
		  
		  String cellText  = String.valueOf(cell.getNumericCellValue());
		  System.out.println("numeric  value : "+cellText);
		  if (DateUtil.isCellDateFormatted(cell)) {
	           // format in form of M/D/YY
			  double d = cell.getNumericCellValue();

			  Calendar cal =Calendar.getInstance();
			  cal.setTime(DateUtil.getJavaDate(d));
	            cellText =
	             (String.valueOf(cal.get(Calendar.YEAR)));
	           cellText = cal.get(Calendar.MONTH)+1 + "/" +
	                      cal.get(Calendar.DAY_OF_MONTH) + "/" +
	                      cellText;

	         }
		  return cellText;
	  }else if(cell.getCellType().name().equals("BLANK"))
	      return "";
	  else 
		  return String.valueOf(cell.getBooleanCellValue());
		}
		catch(Exception e){
			
			e.printStackTrace();
			return "row "+rowNum+" or column "+colNum +" does not exist  in xls";
		}
	} 
	
	// this Method is used to get cell data in particular column
	public String getCellData(String sheetName,String colName,int rowNum){
		try{
			if(!isSheetExist(sheetName))
			{
				return null;
			}
			if(rowNum <0)
				return null;
		
		int col_Num=-1;		
		sheet = workbook.getSheet(sheetName);
		row=sheet.getRow(0);
		for(int i=0;i<row.getLastCellNum();i++){
			if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
				col_Num=i;
		}
		if(col_Num==-1)
			return null;
		
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNum);
		if(row==null)
			return null;
		cell = row.getCell(col_Num);
		
		if(cell==null)
			return "";
		if(cell.getCellType().name().equals("STRING"))
			  return cell.getStringCellValue();
		else if(cell.getCellType().name().equals("NUMERIC") || cell.getCellType().name().equals("FORMULA") ){
			  
			  String cellText  = String.valueOf(cell.getNumericCellValue());
			  if (DateUtil.isCellDateFormatted(cell)) {
		           // format in form of M/D/YY
				  double d = cell.getNumericCellValue();

				  Calendar cal =Calendar.getInstance();
				  cal.setTime(DateUtil.getJavaDate(d));
		            cellText =
		             (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
		           cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
		                      cal.get(Calendar.MONTH)+1 + "/" + 
		                      cellText;
		           
		           //System.out.println(cellText);

		         }

			  
			  
			  return cellText;
		  }else if(cell.getCellType().name().equals("BLANK"))
		      return ""; 
		  else 
			  return String.valueOf(cell.getBooleanCellValue());
		
		}
		catch(Exception e){
			
			e.printStackTrace();
			return "row "+rowNum+" or column "+colName +" does not exist in xls";
		}
	}
}
