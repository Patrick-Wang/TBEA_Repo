package com.frame.script.maker.xml;

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

public class XmlImportComponentMaker extends XmlComponentMaker {

	public XmlImportComponentMaker(String template, String wrapperName) {
		super(template, wrapperName);
	}


	protected String onMake(ConfigTable src) throws MakerException {		
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
		
		createImportController(doc, eRoot, componentName, src);
		
		createImportService(doc, eRoot, componentName, src);	
		
		return Util.toStringFromDoc(eRoot);
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
	
	private void createImportService(Document doc, Element eRoot, String componentName, ConfigTable src) {
		Element service = doc.createElement(Schema.TAG_SERVICE);
		service.setAttribute("id", componentName + "ImportServ");
		service.setAttribute("transaction", "transactionManager");
		eRoot.appendChild(service);
		
		
		Element merge = doc.createElement(Schema.TAG_MERGE);
		merge.setAttribute("data", "${data}");
		merge.setAttribute("table", src.getTableName());
		service.appendChild(merge);
		
		Element set = doc.createElement("set");
		
		
		Element where = doc.createElement("where");
		
		
		for (int i = 0, j = 0; i < src.getColNames().size(); ++i) {
			if ("Y".equals(src.getNeedImport().get(i))) {
				ColType ct = src.getColTypes().get(i);
				if (ColType.UNIQUE.equals(ct.getConstraint()) || ColType.PRIMARY.equals(ct.getConstraint())) {
					Element wChild = doc.createElement(src.getColNames().get(i));
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

	private void createImportController(Document doc, Element eRoot, String componentName, ConfigTable src) {
		Element controller = doc.createElement(Schema.TAG_CONTROLLER);
		controller.setAttribute("id", componentName + "ImportClr");
		eRoot.appendChild(controller);
		Element context = doc.createElement(Schema.TAG_CONTEXT);
		context.setAttribute("key", "data");
		context.setAttribute("value", "${request.upfile.asExcel}");
		controller.appendChild(context);
		context = doc.createElement(Schema.TAG_CONTEXT);
		context.setAttribute("key", "src");
		context.setAttribute("value", "${session.account.name} Excel 导入");
		controller.appendChild(context);
		context = doc.createElement(Schema.TAG_CONTEXT);
		context.setAttribute("key", "time");
		context.setAttribute("value", "${calendar.current.secondFormat}");
		controller.appendChild(context);
		
		Element callservice = doc.createElement(Schema.TAG_CALLSERVICE);
		callservice.setAttribute("id", componentName + "ImportServ");
		controller.appendChild(callservice);
		
		Element response = doc.createElement(Schema.TAG_RESPONSE);
		response.setAttribute("type", "json");
		controller.appendChild(response);
		
		Element errorCode = doc.createElement("errorCode");
		errorCode.appendChild(doc.createTextNode("0"));
		response.appendChild(errorCode);
		
		Element message = doc.createElement("message");
		message.appendChild(doc.createTextNode("OK"));
		response.appendChild(message);
	}

	private void createShowController(Document doc, Element eRoot, String componentName, ConfigTable src) {
		Element controller = doc.createElement(Schema.TAG_CONTROLLER);
		controller.setAttribute("id", componentName + "IMPORTJSP");
		eRoot.appendChild(controller);
		Element response = doc.createElement(Schema.TAG_RESPONSE);
		response.setAttribute("type", "jsp");
		response.setAttribute("name", "components/import_data");
		controller.appendChild(response);
		Element map = doc.createElement("map");
		map.setAttribute("key", "importUrl");
		map.setAttribute("value", componentName + "ImportClr");
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
		controller.setAttribute("id", this.getId("IWJ"));
		
		Element list = doc.createElement(Schema.TAG_LIST);
		controller.appendChild(list);
		list.setAttribute("id", "ids");
		list.setAttribute("type", "int");
		list.appendChild(doc.createTextNode(Util.join(ids)));
		
		list = doc.createElement(Schema.TAG_LIST);
		controller.appendChild(list);
		list.setAttribute("id", "vals");
		list.setAttribute("type", "string");
		list.appendChild(doc.createTextNode(Util.join(vals)));
		
		
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
