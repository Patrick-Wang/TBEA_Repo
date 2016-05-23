package com.tbea.ic.operation.service.pricelib.jcycljg.storage.validation.excel;

import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import com.tbea.ic.operation.service.pricelib.jcycljg.storage.validation.ValidationException;

public interface FormatValidator {
	List<Object[]> validate(XSSFSheet sheet) throws ValidationException;
}
