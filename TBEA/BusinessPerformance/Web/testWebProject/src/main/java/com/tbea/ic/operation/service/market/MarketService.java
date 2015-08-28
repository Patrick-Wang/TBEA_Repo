package com.tbea.ic.operation.service.market;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tbea.ic.operation.common.companys.Company;

public interface MarketService {

	String importProjectData(XSSFWorkbook workbook);

	String importSignData(XSSFWorkbook workbook);

	String importBidData(XSSFWorkbook workbook);
	
	String[][] getBidData(String companyName);
	
	String[][] getPrjData(String companyName);
	
	String[][] getContData(String companyName);

}
