package com.tbea.ic.operation.reportframe.interpreter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.w3c.dom.Element;

import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.reportframe.component.AbstractXmlComponent;


public class ExcelTemplateXmlInterpreter implements XmlInterpreter {

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) {
		
		if (!Schema.isExcelTemplate(e)){
			return false;
		}

		String id = e.getAttribute("id");
		String sheetName = e.getAttribute("sheet");
		HSSFWorkbook workbook = new HSSFWorkbook();
		workbook.createSheet(sheetName);
		if ("true".equals(e.getAttribute("export"))){
			component.global(id, new ExcelTemplate(workbook));
		}else{
			component.local(id, new ExcelTemplate(workbook));
		}
		return true;
	}
}