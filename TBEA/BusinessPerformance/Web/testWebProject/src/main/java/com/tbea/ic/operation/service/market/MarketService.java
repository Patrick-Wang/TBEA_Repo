package com.tbea.ic.operation.service.market;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tbea.ic.operation.common.ErrorCode;

public interface MarketService {

	String[][] getBidData(String companyName, Integer year);
	
	String[][] getPrjData(String companyName, Integer year);
	
	String[][] getContData(String companyName);

	void carryDownBidInfo(Date dStart, Date dEnd, Date target);

	void carryDownProjectInfo(Date dStart, Date dEnd, Date target);
	
	String importProjectData(XSSFWorkbook workbook);

	String importSignData(XSSFWorkbook workbook);

	String importBidData(XSSFWorkbook workbook);
	
	ErrorCode editProjectData(JSONArray jsonArray, String rawKey) throws InstantiationException, IllegalAccessException;

	ErrorCode editSignData(JSONArray jsonArray, String rawKey);

	ErrorCode editBidData(JSONArray jsonArray, String rawKey);

	ErrorCode addProjectData(JSONArray jsonArray);

	ErrorCode addSignData(JSONArray jsonArray);

	ErrorCode addBidData(JSONArray jsonArray);

	List<String[]> getIndustryBidData(String companyName, Date dStart, Date dEnd);

}
