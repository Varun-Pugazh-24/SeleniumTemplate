package com.appname.Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelOperations {

	public static Workbook book;
	public static Sheet sheet;
	public static FileInputStream fis;
	public static FileOutputStream fout;

	String sheetName;
	String input;
	int row;
	int column;
	String excelPath;

	private ExcelOperations(Builder builder) {
		this.sheetName = builder.bSheetName;
		this.input = builder.bInput;
		this.row = builder.bRow;
		this.column = builder.bColumn;
		this.excelPath = builder.bExcelPath;
	}

	public void writeToExcel() {
		

		try {
			fis = new FileInputStream(this.getExcelPath());
			book = WorkbookFactory.create(fis);
			sheet = book.getSheet(this.getSheetName());
			sheet.getRow(this.getRow()).createCell(this.getColumn()).setCellValue(this.getInput());
			fout = new FileOutputStream(this.getExcelPath());
			book.write(fout);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public String readFromExcel() {

		try {
			fis = new FileInputStream(this.getExcelPath());
			book = WorkbookFactory.create(fis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		sheet = book.getSheet(this.getSheetName());
		String Data = (String) sheet.getRow(this.getRow()).getCell(this.getColumn()).getStringCellValue().trim();

		return Data;

	}

	public int getRowCount() {

		try {
			fis = new FileInputStream(this.getExcelPath());
			book = WorkbookFactory.create(fis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		sheet = book.getSheet(this.getSheetName());
		int row = sheet.getLastRowNum();

		return row;
	}
	
	public int getColumnCount() {

		try {
			fis = new FileInputStream(this.getExcelPath());
			book = WorkbookFactory.create(fis);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		sheet = book.getSheet(this.getSheetName());
		int column = sheet.getRow(0).getLastCellNum();

		return column;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public String getExcelPath() {
		return excelPath;
	}

	public void setExcelPath(String excelPath) {
		this.excelPath = excelPath;
	}

	public static class Builder {

		private String bSheetName;
		private String bInput;
		private int bRow;
		private int bColumn;
		private String bExcelPath;

		public Builder(String excelPath) {

			this.bExcelPath = excelPath;
		}

		public Builder setSheetName(String sheetName) {

			this.bSheetName = sheetName;

			return this;
		}

		public Builder setRow(int row) {

			this.bRow = row;

			return this;
		}

		public Builder setColumn(int column) {

			this.bColumn = column;

			return this;
		}

		public Builder setInputToWrite(String input) {

			this.bInput = input;

			return this;
		}

		public ExcelOperations build() {
			return new ExcelOperations(this);
		}

	}

}
