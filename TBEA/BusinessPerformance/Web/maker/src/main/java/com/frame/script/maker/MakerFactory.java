package com.frame.script.maker;

import com.frame.script.maker.sql.SqlMaker;
import com.frame.script.maker.xml.XmlEntryComponentMaker;
import com.frame.script.maker.xml.XmlImportComponentMaker;
import com.frame.script.maker.xml.XmlShowComponentMaker;

public class MakerFactory {
	public static Maker createSqlMaker() {
		return new SqlMaker();
	}
	
	public static Maker createImportXmlMaker(String template, String wrapperName) {
		return new XmlImportComponentMaker(template, wrapperName);
	}
	
	public static Maker createShowXmlMaker(String template, String wrapperName) {
		return new XmlShowComponentMaker(template, wrapperName);
	}
	
	public static Maker createEntryXmlMaker(String template, String wrapperName) {
		return new XmlEntryComponentMaker(template, wrapperName);
	}
}
