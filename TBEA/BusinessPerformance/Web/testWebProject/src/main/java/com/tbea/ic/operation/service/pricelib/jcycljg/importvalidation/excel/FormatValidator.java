package com.tbea.ic.operation.service.pricelib.jcycljg.importvalidation.excel;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.tbea.ic.operation.service.pricelib.jcycljg.importvalidation.ValidationException;

public interface FormatValidator {
	List<Object[]> validate(XSSFSheet sheet) throws ValidationException;
}
