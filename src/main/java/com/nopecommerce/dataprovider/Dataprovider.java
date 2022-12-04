package com.nopecommerce.dataprovider;

import java.util.HashMap;

import org.testng.annotations.DataProvider;

import com.nopecommerce.utility.ExcelLibrary;

public class Dataprovider {
	
	
	@DataProvider(name="AccountRegisterDetails")
	public Object accountData()
	{
		ExcelLibrary excel=new ExcelLibrary("./src/test/resources/TestData.xlsx");
		int row=excel.getRowCount("AccountRegisterDetails");
		int col=excel.getColumncount("AccountRegisterDetails");
		
		Object[][] data=new Object[row-1][1];
		
		for(int i=1;i<row;i++)
		{
			HashMap< String, String> map=new HashMap<String,String>();
			for (int j = 0; j < col; j++) {
				
				map.put(excel.getCellData("AccountRegisterDetails",j,0),excel.getCellData("AccountRegisterDetails", j, i));
			}
			data[i-1][0]=map;
		}
		return data;
	}
	
	@DataProvider(name="LoginDetails")
	public Object loginData()
	{
		ExcelLibrary excel=new ExcelLibrary("./src/test/resources/TestData.xlsx");
		int row=excel.getRowCount("LoginData");
		int col=excel.getColumncount("LoginData");
		
		Object[][] data=new Object[row-1][1];
		
		for(int i=1;i<row;i++)
		{
			HashMap< String, String> map=new HashMap<String,String>();
			for (int j = 0; j < col; j++) {
				
				map.put(excel.getCellData("LoginData",j,0),excel.getCellData("LoginData", j, i));
			}
			data[i-1][0]=map;
		}
		return data;
	}
	
	
	@DataProvider(name="Productname" ,indices = {0,1})
	public Object products()
	{
		ExcelLibrary excel=new ExcelLibrary("./src/test/resources/TestData.xlsx");
		int row=excel.getRowCount("Products");
		int col=excel.getColumncount("Products");
		
		Object[][] data=new Object[row-1][1];
		
		for(int i=1;i<row;i++)
		{
			HashMap< String, String> map=new HashMap<String,String>();
			for (int j = 0; j < col; j++) {
				
				map.put(excel.getCellData("Products",j,0),excel.getCellData("Products", j, i));
			}
			data[i-1][0]=map;
		}
		return data;
	}
	
	@DataProvider(name="selectProduct",indices = {0})
	public Object selectProduct()
	{
		ExcelLibrary excel=new ExcelLibrary("./src/test/resources/TestData.xlsx");
		int row=excel.getRowCount("Products");
		int col=excel.getColumncount("Products");
		
		Object[][] data=new Object[row-1][1];
		
		for(int i=1;i<row;i++)
		{
			HashMap< String, String> map=new HashMap<String,String>();
			for (int j = 0; j < col; j++) {
				
				map.put(excel.getCellData("Products",j,0),excel.getCellData("Products", j, i));
			}
			data[i-1][0]=map;
		}
		return data;
	}
	
	@DataProvider(name="completeProductDetails",indices = {2})
	public Object shoppingcartDetail()
	{
		ExcelLibrary excel=new ExcelLibrary("./src/test/resources/TestData.xlsx");
		int row=excel.getRowCount("Products");
		int col=excel.getColumncount("Products");
		
		Object[][] data=new Object[row-1][1];
		
		for(int i=1;i<row;i++)
		{
			HashMap< String, String> map=new HashMap<String,String>();
			for (int j = 0; j < col; j++) {
				
				map.put(excel.getCellData("Products",j,0),excel.getCellData("Products", j, i));
			}
			data[i-1][0]=map;
		}
		return data;
	}
	
	@DataProvider(name="endToEndTest" ,indices = 1)
	public Object buyProduct()
	{
		ExcelLibrary excel=new ExcelLibrary("./src/test/resources/TestData.xlsx");
		int row=excel.getRowCount("AllData");
		int col=excel.getColumncount("AllData");
		
		Object[][] data=new Object[row-1][1];
		
		for(int i=1;i<row;i++)
		{
			HashMap< String, String> map=new HashMap<String,String>();
			for (int j = 0; j < col; j++) {
				
				map.put(excel.getCellData("AllData",j,0),excel.getCellData("AllData", j, i));
			}
			data[i-1][0]=map;
		}
		return data;
	}
	
	@DataProvider(name="checkouttDetails",indices = 0)
	public Object checkoutDetails()
	{
		ExcelLibrary excel=new ExcelLibrary("./src/test/resources/TestData.xlsx");
		int row=excel.getRowCount("AllData");
		int col=excel.getColumncount("AllData");
		
		Object[][] data=new Object[row-1][1];
		
		for(int i=1;i<row;i++)
		{
			HashMap< String, String> map=new HashMap<String,String>();
			for (int j = 0; j < col; j++) {
				
				map.put(excel.getCellData("AllData",j,0),excel.getCellData("AllData", j, i));
			}
			data[i-1][0]=map;
		}
		return data;
	}

}
