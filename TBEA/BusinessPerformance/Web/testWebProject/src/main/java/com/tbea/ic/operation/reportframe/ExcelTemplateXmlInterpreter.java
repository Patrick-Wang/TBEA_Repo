package com.tbea.ic.operation.reportframe;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.w3c.dom.Element;

import com.tbea.ic.operation.common.excel.ExcelTemplate;


public class ExcelTemplateXmlInterpreter implements XmlInterpreter {

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) {
		
		if (!"ExcelTemplate".equals(e.getTagName())){
			return false;
		}

		String id = e.getAttribute("id");
		component.local(id, new ExcelTemplate(new HSSFWorkbook()));
		return true;
	}
}