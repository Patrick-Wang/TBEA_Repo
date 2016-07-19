package com.tbea.ic.operation.reportframe.interpreter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.w3c.dom.Element;

import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.reportframe.component.AbstractXmlComponent;
import com.tbea.ic.operation.reportframe.el.ELParser;
import com.tbea.ic.operation.reportframe.util.XmlUtil;


public class ExcelTemplateXmlInterpreter implements XmlInterpreter {

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		
		if (!Schema.isExcelTemplate(e)){
			return false;
		}

		
		String sheetName = XmlUtil.getString(e.getAttribute("sheet"), new ELParser(component));
		HSSFWorkbook workbook = new HSSFWorkbook();
		workbook.createSheet(sheetName);

		component.put(e, new ExcelTemplate(workbook));
		return true;
	}
}