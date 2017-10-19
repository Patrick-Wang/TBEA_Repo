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

public class XmlEntryComponentMaker extends XmlComponentMaker {


	public XmlEntryComponentMaker(String template, String wrapperName) {
		super(template, wrapperName);
	}


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
		
		createEntryController(doc, eRoot, componentName, src);
		
		createUpdateController(doc, eRoot, componentName, src);
		
		createFmatterController(doc, eRoot, componentName, src);
		
		createUpdateService(doc, eRoot, componentName, src);	
		
		createSubmitController(doc, eRoot, componentName, src);
		
		createSubmitService(doc, eRoot, componentName, src);	
		
		return Util.toStringFromDoc(eRoot);
	}
	
	
	private void createSubmitController(Document doc, Element eRoot, String componentName, ConfigTable src) {
		Element controller = doc.createElement(Schema.TAG_CONTROLLER);
		controller.setAttribute("id", componentName + "EntrySubmitClr");
		eRoot.appendChild(controller);
		
		Element context = doc.createElement(Schema.TAG_CONTEXT);
		context.setAttribute("key", "cal");
		context.setAttribute("value", "${request.date.asCalendar}");
		controller.appendChild(context);
		
		context = doc.createElement(Schema.TAG_CONTEXT);
		context.setAttribute("key", "data");
		context.setAttribute("value", "${request.data.asJsonArray}");
		controller.appendChild(context);
		
		context = doc.createElement(Schema.TAG_CONTEXT);
		context.setAttribute("key", "src");
		context.setAttribute("value", "${session.account.name} 录入");
		controller.appendChild(context);
		
		context = doc.createElement(Schema.TAG_CONTEXT);
		context.setAttribute("key", "time");
		context.setAttribute("value", "${calendar.current.secondFormat}");
		controller.appendChild(context);
		
		Element callservice = doc.createElement(Schema.TAG_CALLSERVICE);
		callservice.setAttribute("id", componentName + "EntrySubmitServ");
		controller.appendChild(callservice);
		
		Element response = doc.createElement(Schema.TAG_RESPONSE);
		response.setAttribute("type", "json");
		controller.appendChild(response);
		
		Element errorCode = doc.createElement("errorCode");
		errorCode.setTextContent("0");
		response.appendChild(errorCode);
		
		Element message = doc.createElement("message");
		message.setTextContent("OK");
		response.appendChild(message);
	}


	private void createUpdateService(Document doc, Element eRoot, String componentName, ConfigTable src) {
		Element service = doc.createElement(Schema.TAG_SERVICE);
		service.setAttribute("id", componentName + "EntryUpdateServ");
		service.setAttribute("transaction", "transactionManager");
		eRoot.appendChild(service);
		
		Element sql = doc.createElement(Schema.TAG_SQL);
		sql.setAttribute("id", "data");
		
		String sqlTmp = "select \n\tid";
		for (int i = 0; i < src.getColNames().size(); ++i) {
			if ("Y".equals(src.getEntryable().get(i)) || "Y".equals(src.getVisiablity().get(i))){
				sqlTmp += ",";
				if (src.getColTypes().get(i).getType() == ColType.DATE ||
					src.getColTypes().get(i).getType() == ColType.DATETIME) {
					sqlTmp += "\n\tCONVERT(varchar(20)," +  src.getColNames().get(i) + ", 23) tmp" + i;
				}else {
					sqlTmp += "\n\t" + src.getColNames().get(i);
				}
			}
		}
		
		sqlTmp += "\nfrom " + src.getTableName();
		
		sql.setTextContent(sqlTmp);
		service.appendChild(sql);
		
		Element list = doc.createElement(Schema.TAG_LIST);
		list.setAttribute("id", "ids");
		list.setAttribute("sql", "data");
		list.setAttribute("value", "0");
		service.appendChild(list);

		
		//<context value="${counterFactory.newCounter}" key="counter" />
		
		Element counter = doc.createElement(Schema.TAG_CONTEXT);
		counter.setAttribute("key", "counter");
		counter.setAttribute("value", "${counterFactory.newCounter}");
		service.appendChild(counter);
		
		Element table = doc.createElement(Schema.TAG_TABLE);
		table.setAttribute("id", "result");
		table.setAttribute("rowIds", "ids");
		table.setAttribute("export", "true");
		service.appendChild(table);
		
		
		boolean reset = true;
		for (int i = 0, j = 1; i < src.getColNames().size(); ++i) {
			if ("Y".equals(src.getVisiablity().get(i)) || "Y".equals(src.getEntryable().get(i))){
				list = doc.createElement(Schema.TAG_LIST);
				setType(src.getColTypes().get(i), list);			
				list.setAttribute("sql", "data");
				if (reset) {
					list.setAttribute("value", "${counter.reset[" + j + "].val}");
				}else {
					list.setAttribute("value", "${counter.next.val}");
				}
				table.appendChild(list);
				++j;
				reset = false;
			}else {
				reset = true;
			}
		}		
	}
	

	void setType(ColType type, Element e) {
		switch(type.getType()) {
		case ColType.CURRENCY_SYMBOL:
		case ColType.STRING:
		case ColType.TEXT:
		case ColType.OPTION:
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
	
	private void createSubmitService(Document doc, Element eRoot, String componentName, ConfigTable src) {
		Element service = doc.createElement(Schema.TAG_SERVICE);
		service.setAttribute("id", componentName + "EntrySubmitServ");
		service.setAttribute("transaction", "transactionManager");
		eRoot.appendChild(service);
		
		
		Element merge = doc.createElement(Schema.TAG_MERGE);
		merge.setAttribute("data", "${data}");
		merge.setAttribute("table", src.getTableName());
		service.appendChild(merge);
		
		Element set = doc.createElement("set");
		
		Element where = doc.createElement("where");
		Element wChild = doc.createElement("id");
		wChild.setAttribute("type", "int");
		wChild.setAttribute("ref", "0");
		where.appendChild(wChild);
		
		for (int i = 0, j = 1; i < src.getColNames().size(); ++i) {
			if ("Y".equals(src.getEntryable().get(i))) {
				ColType ct = src.getColTypes().get(i);
				if (ColType.UNIQUE.equals(ct.getConstraint()) || ColType.PRIMARY.equals(ct.getConstraint())) {
					wChild = doc.createElement(src.getColNames().get(i));
					setType(ct, wChild);
					wChild.setAttribute("ref", "" + j);
					where.appendChild(wChild);
				}
				Element sChild = doc.createElement(src.getColNames().get(i));
				setType(ct, sChild);
				sChild.setAttribute("ref", "" + j);
				if (ColType.NOTNULL.equals(ct.getConstraint()) || ColType.PRIMARY.equals(ct.getConstraint())) {
					sChild.setAttribute("test", "${this != null}");
				}
				set.appendChild(sChild);
				++j;
			} else if ("Y".equals(src.getVisiablity().get(i))) {
				++j;
			}
		}
		
		Element sChild = doc.createElement("_src");
		sChild.setAttribute("type", "string");
		sChild.setAttribute("value", "${src}");
		set.appendChild(sChild);
		
		sChild = doc.createElement("_time");
		sChild.setAttribute("type", "date");
		sChild.setAttribute("value", "${time}");
		set.appendChild(sChild);
		
		
		if (where.hasChildNodes()) {
			merge.appendChild(where);
		}
		
		merge.appendChild(set);
		
	}

	
	private void createFmatterController(Document doc, Element eRoot, String componentName, ConfigTable src) {
		Element controller = doc.createElement(Schema.TAG_CONTROLLER);
		controller.setAttribute("id", componentName + "EntryFmtClr");
		eRoot.appendChild(controller);
		
		Element formatter = doc.createElement(Schema.TAG_FORMATTER);
		formatter.setAttribute("id", "fmtData");
		formatter.setAttribute("export", "true");
		controller.appendChild(formatter);
		
		
		List<Integer> sVals = new ArrayList<Integer>();
		List<Integer> intVals = new ArrayList<Integer>();
		List<Integer> percentVals = new ArrayList<Integer>();
		List<Integer> numVals = new ArrayList<Integer>();
		
		for (int i = 0, j = 1; i < src.getColTypes().size(); ++i) {
			if ("Y".equals(src.getEntryable().get(i)) || "Y".equals(src.getVisiablity().get(i))){
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
			matcher.setAttribute("cols", Util.join(intVals));
			formatter.appendChild(fmtTmp);
		}
		
		if (!percentVals.isEmpty()) {
			Element fmtTmp = doc.createElement("PercentFormatter");
			fmtTmp.setAttribute("reservedCount", "1");
			Element matcher = doc.createElement("DefaultMatcher");
			fmtTmp.appendChild(matcher);
			matcher.setAttribute("cols", Util.join(percentVals));
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
	
	private void createUpdateController(Document doc, Element eRoot, String componentName, ConfigTable src) {
		Element controller = doc.createElement(Schema.TAG_CONTROLLER);
		controller.setAttribute("id", componentName + "EntryUpdateClr");
		eRoot.appendChild(controller);
		
		Element context = doc.createElement(Schema.TAG_CONTEXT);
		context.setAttribute("key", "cal");
		context.setAttribute("value", "${request.date.asCalendar}");
		controller.appendChild(context);
		
		Element callservice = doc.createElement(Schema.TAG_CALLSERVICE);
		callservice.setAttribute("id", componentName + "EntryUpdateServ");
		controller.appendChild(callservice);
		
		Element callcontroller = doc.createElement(Schema.TAG_CALLCONTROLLER);
		callcontroller.setAttribute("id", componentName + "EntryFmtClr");
		controller.appendChild(callcontroller);
		
		Element fmtserver = doc.createElement(Schema.TAG_FORMATTERSERVER);
		fmtserver.setAttribute("id", "fmtServ");
		fmtserver.setAttribute("table", "${result.matrix}");
		fmtserver.setAttribute("acceptNullAs", "");
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
			if ("Y".equals(src.getEntryable().get(i)) || "Y".equals(src.getVisiablity().get(i))){
				Element item = doc.createElement("item");
				header.appendChild(item);
				Element name = doc.createElement("name");
				item.appendChild(name);
				name.setTextContent(src.getColTitles().get(i));
				
				if(src.getColTypes().get(i).getType() == ColType.TEXT ||
						src.getColTypes().get(i).getType() == ColType.STRING ||	
						src.getColTypes().get(i).getType() == ColType.DATETIME ) {
					Element type = doc.createElement("type");
					item.appendChild(type);
					type.setTextContent("text");
				}else if(src.getColTypes().get(i).getType() == ColType.DATE) {
					Element type = doc.createElement("type");
					item.appendChild(type);
					type.setTextContent("date");
				}else if(src.getColTypes().get(i).getType() == ColType.OPTION) {
					Element type = doc.createElement("type");
					item.appendChild(type);
					type.setTextContent("select");
					Element options = doc.createElement("options");
					item.appendChild(options);
					options.setAttribute("type", "array");
					options.setTextContent(src.getColTypes().get(i).getOptions());
				}
			}
		}
		
		
		Element date = doc.createElement("data");
		date.setTextContent("${fmtServ.result}");
		response.appendChild(date);
		
	}

	private void createEntryController(Document doc, Element eRoot, String componentName, ConfigTable src) {
		Element controller = doc.createElement(Schema.TAG_CONTROLLER);
		controller.setAttribute("id", componentName + "EntryJSP");
		eRoot.appendChild(controller);
		Element response = doc.createElement(Schema.TAG_RESPONSE);
		response.setAttribute("type", "jsp");
		response.setAttribute("name", "framework/templates/singleDateReport/entry");
		controller.appendChild(response);
		Element map = doc.createElement("map");
		map.setAttribute("key", "updateUrl");
		map.setAttribute("value", componentName + "EntryUpdateClr");
		response.appendChild(map);
		map = doc.createElement("map");
		map.setAttribute("key", "submitUrl");
		map.setAttribute("value", componentName + "EntrySubmitClr");
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
		controller.setAttribute("id", this.getId("entryWrapperJsp"));
		
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
		map.setAttribute("key", "updateUrl");
		map.setAttribute("value", this.getId("entryUpdateWrapperClr"));
		response.appendChild(map);
		map = doc.createElement("map");
		map.setAttribute("key", "submitUrl");
		map.setAttribute("value", this.getId("entrySubmitWrapperClr"));
		response.appendChild(map);
		map = doc.createElement("map");
		map.setAttribute("key", "nodeData");
		map.setAttribute("value", "${dataNodeFactory[ids][vals].asJson}");
		response.appendChild(map);
		map = doc.createElement("map");
		map.setAttribute("key", "year");
		map.setAttribute("value", "${calendar.current.year}");
		response.appendChild(map);
		
		map = doc.createElement("map");
		map.setAttribute("key", "month");
		map.setAttribute("value", "${calendar.current.month}");
		response.appendChild(map);
		
		controller = doc.createElement(Schema.TAG_CONTROLLER);
		eRoot.appendChild(controller);
		controller.setAttribute("id", this.getId("entryUpdateWrapperClr"));
		
		Element context = doc.createElement(Schema.TAG_CONTEXT);
		context.setAttribute("key", "item");
		context.setAttribute("value", "${request.item.asInt}");
		controller.appendChild(context);
		
		Element IF = doc.createElement(Schema.TAG_IF);
		IF.setAttribute("test", "${item == " + ids.get(0) + "}");
		controller.appendChild(IF);
		
		Element callcontroller = doc.createElement(Schema.TAG_CALLCONTROLLER);
		callcontroller.setAttribute("id", controllers.get(0) + "EntryUpdateClr");
		IF.appendChild(callcontroller);
	
		for (int i = 1; i < ids.size(); ++i) {
			Element ELSEIF = doc.createElement(Schema.TAG_ELSEIF);
			ELSEIF.setAttribute("test", "${item == " + ids.get(i) + "}");
			controller.appendChild(ELSEIF);
			
			callcontroller = doc.createElement(Schema.TAG_CALLCONTROLLER);
			callcontroller.setAttribute("id", controllers.get(i) + "EntryUpdateClr");
			ELSEIF.appendChild(callcontroller);
		}
		
		
		controller = doc.createElement(Schema.TAG_CONTROLLER);
		eRoot.appendChild(controller);
		controller.setAttribute("id", this.getId("entrySubmitWrapperClr"));
		
		context = doc.createElement(Schema.TAG_CONTEXT);
		context.setAttribute("key", "item");
		context.setAttribute("value", "${request.item.asInt}");
		controller.appendChild(context);
		
		IF = doc.createElement(Schema.TAG_IF);
		IF.setAttribute("test", "${item == " + ids.get(0) + "}");
		controller.appendChild(IF);
		
		callcontroller = doc.createElement(Schema.TAG_CALLCONTROLLER);
		callcontroller.setAttribute("id", controllers.get(0) + "EntrySubmitClr");
		IF.appendChild(callcontroller);
	
		for (int i = 1; i < ids.size(); ++i) {
			Element ELSEIF = doc.createElement(Schema.TAG_ELSEIF);
			ELSEIF.setAttribute("test", "${item == " + ids.get(i) + "}");
			controller.appendChild(ELSEIF);
			
			callcontroller = doc.createElement(Schema.TAG_CALLCONTROLLER);
			callcontroller.setAttribute("id", controllers.get(i) + "EntrySubmitClr");
			ELSEIF.appendChild(callcontroller);
		}
		
		return Util.toStringFromDoc(eRoot);
	}

}
