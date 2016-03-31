package com.tbea.ic.operation.service.pricelib.jcycljg;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tbea.ic.operation.service.pricelib.jcycljg.storage.DataStorage;
import com.tbea.ic.operation.service.pricelib.jcycljg.validation.FormatValidator;
import com.tbea.ic.operation.service.pricelib.jcycljg.validation.ValidationException;

class ImportHandler {
	private FormatValidator validator;
	private DataStorage storage;
	public ImportHandler(FormatValidator validator, DataStorage storage) {
		super();
		this.validator = validator;
		this.storage = storage;
	}
	public void handle(XSSFWorkbook workbook) throws ValidationException{
		storage.store(validator.validate(workbook.getSheetAt(0)));
	}
};
