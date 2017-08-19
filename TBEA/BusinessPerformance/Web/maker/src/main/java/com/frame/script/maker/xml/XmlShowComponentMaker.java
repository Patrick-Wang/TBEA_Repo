package com.frame.script.maker.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.frame.script.config.ColType;
import com.frame.script.config.excel.ConfigTable;
import com.frame.script.maker.MakerException;
import com.frame.script.util.Util;
import com.xml.frame.report.interpreter.Schema;

public class XmlShowComponentMaker extends XmlComponentMaker{

	public XmlShowComponentMaker(String template, String wrapperName) {
		super(template, wrapperName);
	}


	@Override
	public String onMake(ConfigTable src) throws MakerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory
				.newInstance();
		Document doc = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.newDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MakerException(e.getMessage());
		}
		Element eRoot = doc.createElement(Schema.TAG_COMPONENTS);
		
		String componentName = Util.tableName2ComponentName(src.getTableName());
		
		createShowController(doc, eRoot, componentName, src);
		
		createUpdateController(doc, eRoot, componentName, src);
		
		createFmatterController(doc, eRoot, componentName, src);
		
		createExportController(doc, eRoot, componentName, src);
		
		createShowService(doc, eRoot, componentName, src);	
		
		return Util.toStringFromDoc(eRoot);
	}
	
	
	private void createExportController(Document doc, Element eRoot, String componentName, ConfigTable src) {
		Element controller = doc.createElement(Schema.TAG_CONTROLLER);
		controller.setAttribute("id", componentName + "ExportClr");
		eRoot.appendChild(controller);
		
		Element context = doc.createElement(Schema.TAG_CONTEXT);
		context.setAttribute("key", "cal");
		context.setAttribute("value", "${request.date.asCalendar}");
		controller.appendChild(context);
		
		Element callservice = doc.createElement(Schema.TAG_CALLSERVICE);
		callservice.setAttribute("id", componentName + "ShowServ");
		controller.appendChild(callservice);
		
		Element callcontroller = doc.createElement(Schema.TAG_CALLCONTROLLER);
		callcontroller.setAttribute("id", componentName + "FmtClr");
		controller.appendChild(callcontroller);
		
		Element excelTemplate = doc.createElement("ExcelTemplate");
		excelTemplate.setAttribute("id", "excelTemplate");
		excelTemplate.setAttribute("sheet", src.getTitle());
		controller.appendChild(excelTemplate);

		Element list = doc.createElement(Schema.TAG_LIST);
		list.setAttribute("id", "title");
		list.setAttribute("type", "string");
		String stitles = "";
		for (int i = 0; i < src.getColTitles().size(); ++i) {
			if ("Y".equals(src.getVisiablity().get(i))) {
				stitles += "," + src.getColTitles().get(i);
			}
		}
		
		list.setTextContent(stitles.substring(1));	
		controller.appendChild(list);
			
		 
		Element formatter = doc.createElement(Schema.TAG_FORMATTER);
		formatter.setAttribute("id", "excel");
		controller.appendChild(formatter);
		
		Element etf = doc.createElement("ExcelTitleFilter");
		formatter.appendChild(etf);
		
		Element eTemplate = doc.createElement("ExcelTemplate");
		eTemplate.setAttribute("ref", "excelTemplate");
		etf.appendChild(eTemplate);
		
		Element offset = doc.createElement("Offset");
		offset.setAttribute("row", "0");
		offset.setAttribute("col", "0");
		etf.appendChild(offset);
		
		Element titles = doc.createElement("titles");
		etf.appendChild(titles);
		
		Element title = doc.createElement("title");
		title.setAttribute("ref", "title");
		titles.appendChild(title);
		
		Element ehcf = doc.createElement("ExcelHeaderCenterFormatter");
		formatter.appendChild(ehcf);
		
		Element defa = doc.createElement("DefaultMatcher");
		defa.setAttribute("cols", "0");
		ehcf.appendChild(defa);
		
		Element et = doc.createElement("ExcelTemplate");
		et.setAttribute("ref", "excelTemplate");
		ehcf.appendChild(et);
		
		offset = doc.createElement("Offset");
		offset.setAttribute("row", "1");
		offset.setAttribute("col", "0");
		ehcf.appendChild(offset);
		
		Element eof = doc.createElement("ExcelOffsetFormatter");
		formatter.appendChild(eof);

		et = doc.createElement("ExcelTemplate");
		et.setAttribute("ref", "excelTemplate");
		eof.appendChild(et);
		
		offset = doc.createElement("Offset");
		offset.setAttribute("row", "1");
		offset.setAttribute("col", "0");
		eof.appendChild(offset);
		
		Element fmtserver = doc.createElement(Schema.TAG_FORMATTERSERVER);
		fmtserver.setAttribute("id", "fmtServ");
		fmtserver.setAttribute("table", "${result.matrixNoIds}");
		controller.appendChild(fmtserver);
		
		formatter = doc.createElement(Schema.TAG_FORMATTER);
		formatter.setAttribute("ref", "fmtData");
		fmtserver.appendChild(formatter);
		
		formatter = doc.createElement(Schema.TAG_FORMATTER);
		formatter.setAttribute("ref", "excel");
		fmtserver.appendChild(formatter);
		
		Element response = doc.createElement(Schema.TAG_RESPONSE);
		response.setAttribute("type", "excel");
		response.setAttribute("ref", "excelTemplate");
		response.setAttribute("name", "${request.date.asString}" + src.getTitle() + ".xls");
		response.setAttribute("serv", "fmtServ");
		controller.appendChild(response);
	}


	private void createFmatterController(Document doc, Element eRoot, String componentName, ConfigTable src) {
		Element controller = doc.createElement(Schema.TAG_CONTROLLER);
		controller.setAttribute("id", componentName + "FmtClr");
		eRoot.appendChild(controller);
		
		Element formatter = doc.createElement(Schema.TAG_FORMATTER);
		formatter.setAttribute("id", "fmtData");
		formatter.setAttribute("export", "true");
		controller.appendChild(formatter);
		
		
		List<Integer> sVals = new ArrayList<Integer>();
		List<Integer> intVals = new ArrayList<Integer>();
		List<Integer> percentVals = new ArrayList<Integer>();
		List<Integer> numVals = new ArrayList<Integer>();
		
		for (int i = 0, j = 0; i < src.getColTypes().size(); ++i) {
			if ("Y".equals(src.getVisiablity().get(i))){
				ColType colType = src.getColTypes().get(i);
				if (colType.getType() == ColType.INTEGER) {
					intVals.add(j);
				}else if (colType.getType() == ColType.NUMBER) {
					numVals.add(j);
				}else if (colType.getType() == ColType.PERCENT) {
					percentVals.add(j);
				}else {
					sVals.add(j);
				}
				++j;
			}
		}
		
		if (!intVals.isEmpty()) {
			Element fmtTmp = doc.createElement("NumberFormatter");
			fmtTmp.setAttribute("reservedCount", "0");
			Element matcher = doc.createElement("DefaultMatcher");
			fmtTmp.appendChild(matcher);
			matcher.setAttribute("col", Util.join(intVals));
			formatter.appendChild(fmtTmp);
		}
		
		if (!percentVals.isEmpty()) {
			Element fmtTmp = doc.createElement("PercentFormatter");
			fmtTmp.setAttribute("reservedCount", "1");
			Element matcher = doc.createElement("DefaultMatcher");
			fmtTmp.appendChild(matcher);
			matcher.setAttribute("col", Util.join(percentVals));
			formatter.appendChild(fmtTmp);
		}
		
		if (!numVals.isEmpty()) {
			Element fmtTmp = doc.createElement("NumberFormatter");
			fmtTmp.setAttribute("reservedCount", "1");
			Element matcher = doc.createElement("DefaultMatcher");
			fmtTmp.appendChild(matcher);
			matcher.setAttribute("cols", Util.join(numVals));
			formatter.appendChild(fmtTmp);
		}
		
		Element fmtTmp = doc.createElement("EmptyFormatter");
		formatter.appendChild(fmtTmp);
	}


	

	void setType(ColType type, Element e) {
		switch(type.getType()) {
		case ColType.CURRENCY_SYMBOL:
		case ColType.STRING:
		case ColType.TEXT:
			e.setAttribute("type", "string");
			break;
		case ColType.DATE:
		case ColType.DATETIME:
			e.setAttribute("type", "date");
			break;
		case ColType.NUMBER:
		case ColType.PERCENT:
			e.setAttribute("type", "double");
			break;
		case ColType.INTEGER:
			e.setAttribute("type", "int");
			break;
		}
	}
	
	private void createShowService(Document doc, Element eRoot, String componentName, ConfigTable src) {
		Element service = doc.createElement(Schema.TAG_SERVICE);
		service.setAttribute("id", componentName + "ShowServ");
		service.setAttribute("transaction", "transactionManager");
		eRoot.appendChild(service);
		
		Element sql = doc.createElement(Schema.TAG_SQL);
		sql.setAttribute("id", "data");
		
		String sqlTmp = "select id";
		for (int i = 0; i < src.getColNames().size(); ++i) {
			if ("Y".equals(src.getVisiablity().get(i))){
				sqlTmp += ",";
				if (src.getColTypes().get(i).getType() == ColType.DATE ||
					src.getColTypes().get(i).getType() == ColType.DATETIME) {
					sqlTmp += "\nCONVERT(varchar(20)," +  src.getColNames().get(i) + ", 23) tmp" + i;
				}else {
					sqlTmp += "\n" + src.getColNames().get(i);
				}
			}
		}
		
		sqlTmp += " from " + src.getTableName();
		
		sql.setTextContent(sqlTmp);
		service.appendChild(sql);
		
		Element list = doc.createElement(Schema.TAG_LIST);
		list.setAttribute("id", "ids");
		list.setAttribute("sql", "data");
		list.setAttribute("value", "0");
		service.appendChild(list);

		Element table = doc.createElement(Schema.TAG_TABLE);
		table.setAttribute("id", "result");
		table.setAttribute("rowIds", "ids");
		table.setAttribute("export", "true");
		service.appendChild(table);
		
		for (int i = 0, j = 1; i < src.getColNames().size(); ++i) {
			if ("Y".equals(src.getVisiablity().get(i))){
				list = doc.createElement(Schema.TAG_LIST);
				setType(src.getColTypes().get(i), list);			
				list.setAttribute("sql", "data");
				list.setAttribute("value", "" + j);
				table.appendChild(list);
				++j;
			}
		}		
	}

	private void createUpdateController(Document doc, Element eRoot, String componentName, ConfigTable src) {
		Element controller = doc.createElement(Schema.TAG_CONTROLLER);
		controller.setAttribute("id", componentName + "UpdateClr");
		eRoot.appendChild(controller);
		
		Element context = doc.createElement(Schema.TAG_CONTEXT);
		context.setAttribute("key", "cal");
		context.setAttribute("value", "${request.date.asCalendar}");
		controller.appendChild(context);
		
		Element callservice = doc.createElement(Schema.TAG_CALLSERVICE);
		callservice.setAttribute("id", componentName + "ShowServ");
		controller.appendChild(callservice);
		
		Element callcontroller = doc.createElement(Schema.TAG_CALLCONTROLLER);
		callcontroller.setAttribute("id", componentName + "FmtClr");
		controller.appendChild(callcontroller);
		
		Element fmtserver = doc.createElement(Schema.TAG_FORMATTERSERVER);
		fmtserver.setAttribute("id", "fmtServ");
		fmtserver.setAttribute("table", "${result.matrixNoIds}");
		controller.appendChild(fmtserver);
		
		Element formatter = doc.createElement(Schema.TAG_FORMATTER);
		formatter.setAttribute("ref", "fmtData");
		fmtserver.appendChild(formatter);
		

		Element response = doc.createElement(Schema.TAG_RESPONSE);
		response.setAttribute("type", "json");
		controller.appendChild(response);
		
		Element header = doc.createElement("header");
		header.setAttribute("type", "array");
		response.appendChild(header);
		
		for (int i = 0; i < src.getColTitles().size(); ++i) {
			if ("Y".equals(src.getVisiablity().get(i))){
				Element item = doc.createElement("item");
				header.appendChild(item);
				Element name = doc.createElement("name");
				item.appendChild(name);
				name.setTextContent(src.getColTitles().get(i));
			}
		}
		
		
		Element date = doc.createElement("data");
		date.setTextContent("${fmtServ.result}");
		response.appendChild(date);
		
	}

	private void createShowController(Document doc, Element eRoot, String componentName, ConfigTable src) {
		Element controller = doc.createElement(Schema.TAG_CONTROLLER);
		controller.setAttribute("id", componentName + "Show");
		eRoot.appendChild(controller);
		
		Element response = doc.createElement(Schema.TAG_RESPONSE);
		response.setAttribute("type", "jsp");
		response.setAttribute("name", "framework/templates/singleDateReport/show");
		controller.appendChild(response);
		
		Element map = doc.createElement("map");
		map.setAttribute("key", "year");
		map.setAttribute("value", "${calendar.current.year}");
		response.appendChild(map);
		
		map = doc.createElement("map");
		map.setAttribute("key", "month");
		map.setAttribute("value", "${calendar.current.month}");
		response.appendChild(map);
		
		map = doc.createElement("map");
		map.setAttribute("key", "updateUrl");
		map.setAttribute("value", componentName + "UpdateClr");
		response.appendChild(map);
		
		map = doc.createElement("map");
		map.setAttribute("key", "exportUrl");
		map.setAttribute("value", componentName + "ExportClr");
		response.appendChild(map);
	}

	@Override
	protected String onMakeAll(List<Integer> ids, List<String> vals, List<String> controllers) throws MakerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory
				.newInstance();
		Document doc = null;
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.newDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new MakerException(e.getMessage());
		}
		Element eRoot = doc.createElement(Schema.TAG_COMPONENTS);
		Element controller = doc.createElement(Schema.TAG_CONTROLLER);
		eRoot.appendChild(controller);
		controller.setAttribute("id", this.getId("importWrapperJsp"));
		
		Element list = doc.createElement(Schema.TAG_LIST);
		controller.appendChild(list);
		list.setAttribute("id", "ids");
		list.setAttribute("type", "int");
		list.setTextContent(Util.join(ids));
		
		list = doc.createElement(Schema.TAG_LIST);
		controller.appendChild(list);
		list.setAttribute("id", "vals");
		list.setAttribute("type", "string");
		list.setTextContent(Util.join(vals));
		
		
		Element response = doc.createElement(Schema.TAG_RESPONSE);
		response.setAttribute("type", "jsp");
		response.setAttribute("name", template);
		controller.appendChild(response);
		Element map = doc.createElement("map");
		map.setAttribute("key", "importUrl");
		map.setAttribute("value", this.getId("importWrapper"));
		response.appendChild(map);
		map = doc.createElement("map");
		map.setAttribute("key", "itemNodes");
		map.setAttribute("value", "${dataNodeFactory[ids][vals].asJson}");
		response.appendChild(map);
		
		
		controller = doc.createElement(Schema.TAG_CONTROLLER);
		eRoot.appendChild(controller);
		controller.setAttribute("id", this.getId("importWrapper"));
		
		Element context = doc.createElement(Schema.TAG_CONTEXT);
		context.setAttribute("key", "item");
		context.setAttribute("value", "${request.item.asInt}");
		controller.appendChild(context);
		
		Element IF = doc.createElement(Schema.TAG_IF);
		IF.setAttribute("test", "${item == " + ids.get(0) + "}");
		controller.appendChild(IF);
		
		Element callcontroller = doc.createElement(Schema.TAG_CALLCONTROLLER);
		callcontroller.setAttribute("id", controllers.get(0) + "ImportClr");
		IF.appendChild(callcontroller);
	
		for (int i = 1; i < ids.size(); ++i) {
			Element ELSEIF = doc.createElement(Schema.TAG_ELSEIF);
			ELSEIF.setAttribute("test", "${item == " + ids.get(i) + "}");
			controller.appendChild(ELSEIF);
			
			callcontroller = doc.createElement(Schema.TAG_CALLCONTROLLER);
			callcontroller.setAttribute("id", controllers.get(i) + "ImportClr");
			ELSEIF.appendChild(callcontroller);
		}
		
		return Util.toStringFromDoc(eRoot);
	}

}
