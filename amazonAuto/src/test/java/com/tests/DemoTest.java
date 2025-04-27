package com.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.pageComponents.CartPage;
import com.pageComponents.LandingPage;
import com.testComponents.BaseTest;

public class DemoTest extends BaseTest{

	@Test(dataProvider="driveTest")
	public void searchItems(String product, String category) throws IOException, InterruptedException {
		
		try {
		launchApp();
		LandingPage landingPage = new LandingPage(driver);
		landingPage.searchSomeItem(product, category);
		landingPage.priceRangeSelection();
		landingPage.addToCart();
		CartPage cp = new CartPage(driver);
		int totalBillAmount = cp.getTotal();
		System.out.println(totalBillAmount);
		Assert.assertEquals(totalBillAmount, cp.subTotal());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider="driveTest")
	public void itemsCount(String product, String category) throws IOException, InterruptedException {

		try {
			launchApp();
			LandingPage landingPage = new LandingPage(driver);
			landingPage.searchSomeItem(product, category);
			landingPage.priceRangeSelection();
			landingPage.addToCart();
			CartPage cp = new CartPage(driver);
			cp.getTotal();
			Assert.assertEquals(cp.totalCount(), cp.cartCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test(dataProvider="driveTest")
	public void saveProducts(String product, String category) throws IOException, InterruptedException {

		try {
			launchApp();
			LandingPage landingPage = new LandingPage(driver);
			landingPage.searchSomeItem(product, category);
			landingPage.priceRangeSelection();
			landingPage.addToCart();
			CartPage cp = new CartPage(driver);
			cp.getTotal();
			cp.saveItems();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@DataProvider(name="driveTest")
	public Object[][] getData() throws IOException {
		DataFormatter df = new DataFormatter();
		FileInputStream fis = new FileInputStream("D:\\Excel Workbook\\AmazonData.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet= wb.getSheet("DataDriven1");
		int rowCount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int columnCount = row.getLastCellNum();
		Object[][] data = new Object[rowCount-1][columnCount];
		
		for (int i =0; i < rowCount-1; i++) {
			row = sheet.getRow(i+1);
			for(int j = 0; j < columnCount; j++) {
				XSSFCell cell = row.getCell(j);
				data[i][j] = df.formatCellValue(cell);
			}
		}
		
		return data;
	}
}
