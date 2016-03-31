package com.tbea.ic.operation.service.pricelib.jcycljg;

import java.sql.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tbea.ic.operation.service.pricelib.jcycljg.validation.ValidationException;

public interface JcycljgService {

	List<List<String>> getYsjs(Date start, Date end);

	List<List<String>> getGgp(Date start, Date end);

	void importExcel(JcycljgType type, XSSFWorkbook workbook) throws ValidationException;


}
