package com.vs.utility;

import java.io.File;

import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;
import java.lang.reflect.*;
import com.vs.base.TestBase;

public class TestUtil extends TestBase{
	
	public static String screenshotPath;
	public static String screenshotName;
	
	public static void captureScreenShot() throws IOException {
		
		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		screenshotPath = System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+screenshotName;
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File(screenshotPath));
	}
	
	
	@DataProvider(name="dp")
	public Object[][] getData(Method m) {

		String sheetName = m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][1];
		
		Hashtable<String, String> table = null;

		for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2
			
			table = new Hashtable<String, String>();

			for (int colNum = 0; colNum < cols; colNum++) {
				
				table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
				data[rowNum - 2][0] = table;
			}

		}

		return data;

	}
	
	public static boolean isTestRunable(String testName,ExcelReader excel ) {
		
		String sheetName = "TestSuite";
		
		int rows = excel.getRowCount(sheetName);
		
		for(int rNum = 2; rNum<=rows; rNum++) {
			String testcasename = excel.getCellData(sheetName, "TestCaseName", rNum);
			if(testcasename.equalsIgnoreCase(testName)) {
				String runmode = excel.getCellData(sheetName, "Runmode", rNum);
				if(runmode.equalsIgnoreCase("y"))
					return true;
				return false;
			}
		}
		
		return false;
		
		
		
	}

}
