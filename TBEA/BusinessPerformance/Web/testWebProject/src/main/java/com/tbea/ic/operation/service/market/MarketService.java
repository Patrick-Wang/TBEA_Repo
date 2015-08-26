package com.tbea.ic.operation.service.market;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public interface MarketService {

	String importProjectData(XSSFWorkbook workbook);

	String importSignData(XSSFWorkbook workbook);

	String importBidData(XSSFWorkbook workbook);

}
