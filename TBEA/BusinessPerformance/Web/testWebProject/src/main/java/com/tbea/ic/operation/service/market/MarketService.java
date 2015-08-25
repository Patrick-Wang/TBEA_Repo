package com.tbea.ic.operation.service.market;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public interface MarketService {

	String importProjectData(HSSFWorkbook workbook);

	String importSignData(HSSFWorkbook workbook);

	String importBidData(HSSFWorkbook workbook);

}
