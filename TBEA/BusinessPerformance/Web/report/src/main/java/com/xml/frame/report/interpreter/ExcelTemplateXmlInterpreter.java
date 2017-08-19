package com.xml.frame.report.interpreter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.w3c.dom.Element;

import com.frame.script.el.ELParser;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.util.ExcelHelper;
import com.xml.frame.report.util.XmlUtil;


public class ExcelTemplateXmlInterpreter implements XmlInterpreter {

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		
		if (!Schema.isExcelTemplate(e)){
			return false;
		}
	
		String sheetName = XmlUtil.getString(e.getAttribute("sheet"), new ELParser(component));
		HSSFWorkbook workbook = new HSSFWorkbook();
		workbook.createSheet(sheetName);

		component.put(e, new ExcelHelper(workbook));
		return true;
	}
}