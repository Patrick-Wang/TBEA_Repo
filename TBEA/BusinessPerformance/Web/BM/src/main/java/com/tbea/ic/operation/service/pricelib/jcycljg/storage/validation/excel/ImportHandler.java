package com.tbea.ic.operation.service.pricelib.jcycljg.storage.validation.excel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tbea.ic.operation.service.pricelib.jcycljg.storage.DataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.validation.ValidationException;

public class ImportHandler {
	private FormatValidator validator;
	private DataStorage<?> storage;
	ImportHandler(FormatValidator validator, DataStorage<?> storage) {
		this.validator = validator;
		this.storage = storage;
	}
	public void handle(XSSFWorkbook workbook) throws ValidationException{
		storage.store(validator.validate(workbook.getSheetAt(0)));
	}
};
