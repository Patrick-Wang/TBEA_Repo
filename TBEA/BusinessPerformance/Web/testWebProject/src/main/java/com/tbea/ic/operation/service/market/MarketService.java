package com.tbea.ic.operation.service.market;

import java.sql.Date;

import net.sf.json.JSONArray;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface MarketService {

	String[][] getBidData(String companyName, Integer year);
	
	String[][] getPrjData(String companyName, Integer year);
	
	String[][] getContData(String companyName);

	void carryDownBidInfo(Date dStart, Date dEnd, Date target);

	void carryDownProjectInfo(Date dStart, Date dEnd, Date target);
	
	String importProjectData(XSSFWorkbook workbook);

	String importSignData(XSSFWorkbook workbook);

	String importBidData(XSSFWorkbook workbook);
	
	String importProjectData(JSONArray arrData);

	String importSignData(JSONArray arrData);

	String importBidData(JSONArray arrData);

}
