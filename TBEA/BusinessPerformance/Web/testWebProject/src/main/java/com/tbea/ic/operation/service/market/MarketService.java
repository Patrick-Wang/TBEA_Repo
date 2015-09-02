package com.tbea.ic.operation.service.market;

import java.sql.Date;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface MarketService {

	String importProjectData(XSSFWorkbook workbook);

	String importSignData(XSSFWorkbook workbook);

	String importBidData(XSSFWorkbook workbook);
	
	String[][] getBidData(String companyName, Integer year);
	
	String[][] getPrjData(String companyName, Integer year);
	
	String[][] getContData(String companyName);

	void carryDownBidInfo(Date dStart, Date dEnd, Date target);

	void carryDownProjectInfo(Date dStart, Date dEnd, Date target);

}
