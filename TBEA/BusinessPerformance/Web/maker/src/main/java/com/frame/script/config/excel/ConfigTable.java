package com.frame.script.config.excel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.frame.script.config.ColType;

public class ConfigTable {
	List<ColType> colTypes = new ArrayList<ColType>();
	List<String> colNames = new ArrayList<String>();
	List<String> colTitles = new ArrayList<String>();
	List<String> needImport = new ArrayList<String>();
	List<String> entryable = new ArrayList<String>();
	public List<String> getEntryable() {
		return entryable;
	}

	public void setEntryable(List<String> entryable) {
		this.entryable = entryable;
	}

	List<String> visiablity = new ArrayList<String>();
	public List<String> getVisiablity() {
		return visiablity;
	}

	public void setVisiablity(List<String> visiablity) {
		this.visiablity = visiablity;
	}

	public List<String> getNeedImport() {
		return needImport;
	}

	public void setNeedImport(List<String> needImport) {
		this.needImport = needImport;
	}

	String title;
	String tableName;
	
	
	public List<String> getColTitles() {
		return colTitles;
	}

	public void setColTitles(List<String> colTitles) {
		this.colTitles = colTitles;
	}

	public ConfigTable(String title, String tableName) {
		super();
		this.tableName = tableName;
		this.title = title;
	}
	
	public List<ColType> getColTypes() {
		return colTypes;
	}
	public void setColTypes(List<ColType> colTypes) {
		this.colTypes = colTypes;
	}
	public List<String> getColNames() {
		return colNames;
	}

	public void setColNames(List<String> colNames) {
		this.colNames = colNames;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	static String parseColName(String tbName){
		String[] names = tbName.split(" ");
		String colName = names[0];
		for (int i = 1; i < names.length; ++i) {
			colName += names[i].substring(0, 1).toUpperCase() + names[i].substring(1);
		}
		return colName;
	}
	
	static String[] parseHeader(String title) {
		return title.replaceAll("\\)", "").replaceAll(" ", "").split("\\(");
	}
	
	public static final ConfigTable parse(XSSFSheet sheet) {
		XSSFRow row = sheet.getRow(0);
		String[] header = parseHeader(row.getCell(0).getStringCellValue());
		ConfigTable tb = new ConfigTable(header[0], header[1]);
		for (int i = 2; i <= sheet.getLastRowNum(); ++i) {
			row = sheet.getRow(i);
			if (row == null || null == row.getCell(0) || row.getCell(0).getCellType() != XSSFCell.CELL_TYPE_STRING ||
				null == row.getCell(1) || row.getCell(1).getCellType() != XSSFCell.CELL_TYPE_STRING ||
				null == row.getCell(2) || row.getCell(3).getCellType() != XSSFCell.CELL_TYPE_STRING) {
				break;
			}
			
			tb.getColTitles().add(row.getCell(0).getStringCellValue());
			tb.getColNames().add(parseColName(row.getCell(1).getStringCellValue()));
			if (row.getCell(7) != null && row.getCell(7).getCellType() == XSSFCell.CELL_TYPE_STRING) {
				tb.getNeedImport().add(row.getCell(7).getStringCellValue());
			}else {
				tb.getNeedImport().add("Y");
			}
			if (row.getCell(8) != null && row.getCell(8).getCellType() == XSSFCell.CELL_TYPE_STRING) {
				tb.getVisiablity().add(row.getCell(8).getStringCellValue());
			}else {
				tb.getVisiablity().add("Y");
			}
			if (row.getCell(9) != null && row.getCell(9).getCellType() == XSSFCell.CELL_TYPE_STRING) {
				tb.getEntryable().add(row.getCell(9).getStringCellValue());
			}else {
				tb.getEntryable().add("Y");
			}
			
			String sz = null;
			if (row.getCell(4) != null) {
				if (row.getCell(4).getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
					sz = row.getCell(4).getNumericCellValue() + "";
				}else if (row.getCell(4).getCellType() == XSSFCell.CELL_TYPE_STRING) {
					sz = row.getCell(4).getStringCellValue();
				}
			}
			
			String constraint = null;
			if (null != row.getCell(5) && row.getCell(5).getCellType() == XSSFCell.CELL_TYPE_STRING) {
				constraint = row.getCell(5).getStringCellValue();
			}
			
			String defaultVal = null;
			if (null != row.getCell(6)){
				if (row.getCell(6).getCellType() == XSSFCell.CELL_TYPE_STRING) {
					defaultVal = row.getCell(6).getStringCellValue();
				} else if (row.getCell(6).getCellType() == XSSFCell.CELL_TYPE_NUMERIC ||
						row.getCell(6).getCellType() == XSSFCell.CELL_TYPE_FORMULA) {
					defaultVal = "" + row.getCell(6).getNumericCellValue();
				} 
			}
			
			tb.getColTypes().add(ColType.parse(row.getCell(3).getStringCellValue(), sz, constraint, defaultVal));
		}
		
		return tb;
		
	}
	
}
