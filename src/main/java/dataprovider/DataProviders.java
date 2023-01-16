package dataprovider;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;


public class DataProviders {
	
	utility.ExcelSheet excelSheet = new utility.ExcelSheet();
	
	@DataProvider(name="credentials")
	public Object[][] getCredentials(){
		String sheetName = "Credentials";
		
		int rows = excelSheet.getRowCount(sheetName);
		int columns = excelSheet.getColumnCount(sheetName);
		rows = rows-1;
		
		Object [][] data = new Object[rows][columns];
		for(int i =0; i< rows; i++) {
			for(int j = 0; j<columns; j++) {
				data[i][j] = excelSheet.getCellData(sheetName, j, rows+2);
			}
		}
		return data;
	}
	
	@DataProvider(name="email")
	public Object[][] getEmail(){
		String sheetName = "Email";
		
		int rows = excelSheet.getRowCount(sheetName);
		int columns = excelSheet.getColumnCount(sheetName);
		rows = rows-1;
		
		Object [][] data = new Object[rows][columns];
		for(int i =0; i< rows; i++) {
			for(int j = 0; j<columns; j++) {
				data[i][j] = excelSheet.getCellData(sheetName, j, rows+2);
			}
		}
		return data;
	}
	
	@DataProvider(name="getProduct")
	public Object[][] getProduct(){
		String sheetName = "ProductDetails";
		
		int rows = excelSheet.getRowCount(sheetName);
		int columns = excelSheet.getColumnCount(sheetName);
		rows = rows-1;
		
		Object [][] data = new Object[rows][columns];
		for(int i =0; i< rows; i++) {
			for(int j = 0; j<columns; j++) {
				data[i][j] = excelSheet.getCellData(sheetName, j, rows+2);
			}
		}
		return data;
	}
	
	@DataProvider(name="productPrice")
	public Object[][] getProductPrice(){
		String sheetName = "ProductPrice";
		
		int rows = excelSheet.getRowCount(sheetName);
		int columns = excelSheet.getColumnCount(sheetName);
		rows = rows-1;
		
		Object [][] data = new Object[rows][columns];
		for(int i =0; i< rows; i++) {
			for(int j = 0; j<columns; j++) {
				data[i][j] = excelSheet.getCellData(sheetName, j, rows+2);
			}
		}
		return data;
	}
	
	@DataProvider(name="newAccountData")
	public Object[][] createAccountData(){
		String sheetName = "NewAccountData";
		
		int rows = excelSheet.getRowCount(sheetName);
		int columns = excelSheet.getColumnCount(sheetName);
		
		Object [][] data = new Object[rows][columns];
		for(int i =0; i< rows; i++) {
			Map<String, String> hashMap = new HashMap<>();
			for(int j = 0; j<columns; j++) {
				data[i][j] = hashMap;
			}
		}
		return data;
	}
	
}
