package com.tbea.ic.operation.service.pricelib.jcycljg.validation;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;

public interface FormatValidator {
	List<Object[]> validate(XSSFSheet sheet) throws ValidationException;
}
