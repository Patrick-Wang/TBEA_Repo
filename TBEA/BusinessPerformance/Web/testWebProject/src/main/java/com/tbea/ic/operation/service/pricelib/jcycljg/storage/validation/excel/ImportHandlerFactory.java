package com.tbea.ic.operation.service.pricelib.jcycljg.storage.validation.excel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgType;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.StorageAssemble;


public class ImportHandlerFactory {
	static 	FormatValidator[] validators = new FormatValidator[] {
		new CommonValidator(2, 7),
		new CommonValidator(2, 9),
		new CommonValidator(1, 3),
		new CommonValidator(2, 7),
		new CommonValidator(1, 5),
		new CommonValidator(1, 5),
		new CommonValidator(1, 7),
		new CommonValidator(1, 9),
		new CommonValidator(1, 7),
		new CommonValidator(2, 9),
		new CommonValidator(1, 3),
		new CommonValidator(1, 4),
		new CommonValidator(1, 4),
		new CommonValidator(1, 2),
		new CommonValidator(2, 9),
		new CommonValidator(1, 4),
		new CommonValidator(2, 7)};
	

	public static ImportHandler create(JcycljgType type, StorageAssemble sa){
		return new ImportHandler(validators[type.ordinal()], sa.getStorage(type));
	}
}
