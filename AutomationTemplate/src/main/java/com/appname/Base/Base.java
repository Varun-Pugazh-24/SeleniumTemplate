package com.appname.Base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.appname.Utilities.ExcelOperations;
import com.appname.Utilities.ExcelOperations.Builder;

public class Base extends Constants {
	static Properties prop;

	public static Properties initProperties() {

		File PropertyFile = new File(CONFIG_PROPERTIES_FILE_PATH);
		try {
			FileInputStream fis = new FileInputStream(PropertyFile);
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return prop;

	}

	public static Object[][] getTestDataSheet(String sheetName) {
		Builder ExcelBuilder = new ExcelOperations.Builder(SCENARIO_SHEET_PATH).setSheetName(sheetName);
		int totalRows = ExcelBuilder.build().getRowCount();
		int totalColumns = ExcelBuilder.setRow(0).build().getColumnCount()-1;
		int firstRow = 1;
		int firstColumn = 1;
		int ci, cj;

		String[][] testDataSheetArray = new String[totalRows][totalColumns];

		ci = 0;

		for (int i = firstRow; i <= totalRows; i++, ci++) {

			cj = 0;

			for (int j = firstColumn; j <= totalColumns; j++, cj++) {

				testDataSheetArray[ci][cj] = ExcelBuilder.setRow(i).setColumn(j).build().readFromExcel();

			}

		}

		return testDataSheetArray;
	}
	
}
