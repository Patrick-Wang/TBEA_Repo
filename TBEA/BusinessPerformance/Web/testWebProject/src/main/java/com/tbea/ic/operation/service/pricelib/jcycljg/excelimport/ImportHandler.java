package com.tbea.ic.operation.service.pricelib.jcycljg.excelimport;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tbea.ic.operation.service.pricelib.jcycljg.excelimport.validation.FormatValidator;
import com.tbea.ic.operation.service.pricelib.jcycljg.excelimport.validation.ValidationException;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.DataStorage;

public class ImportHandler {
	private FormatValidator validator;
	private DataStorage<?> storage;
	public ImportHandler(FormatValidator validator, DataStorage<?> storage) {
		super();
		this.validator = validator;
		this.storage = storage;
	}
	public void handle(XSSFWorkbook workbook) throws ValidationException{
		storage.store(validator.validate(workbook.getSheetAt(0)));
	}
};
