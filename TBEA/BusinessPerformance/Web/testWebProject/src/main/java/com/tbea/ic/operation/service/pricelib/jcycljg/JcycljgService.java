package com.tbea.ic.operation.service.pricelib.jcycljg;

import java.sql.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tbea.ic.operation.service.pricelib.jcycljg.importvalidation.ValidationException;

public interface JcycljgService {

	void importExcel(JcycljgType type, XSSFWorkbook workbook) throws ValidationException;

	List<List<String>> getValues(JcycljgType type, Date start, Date end);


}
