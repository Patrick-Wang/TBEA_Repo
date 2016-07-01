package com.tbea.ic.operation.reportframe;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.tbea.ic.operation.common.excel.ExcelTemplate;
import com.tbea.ic.operation.common.formatter.v2.core.DefaultMatcher;
import com.tbea.ic.operation.common.formatter.v2.core.EmptyFormatter;
import com.tbea.ic.operation.common.formatter.v2.core.FormatterHandler;
import com.tbea.ic.operation.common.formatter.v2.core.FormatterMatcher;
import com.tbea.ic.operation.common.formatter.v2.core.IndicatorMatcher;
import com.tbea.ic.operation.common.formatter.v2.core.MergeRegion;
import com.tbea.ic.operation.common.formatter.v2.core.Offset;
import com.tbea.ic.operation.common.formatter.v2.data.NumberFormatter;
import com.tbea.ic.operation.common.formatter.v2.data.PercentFormatter;
import com.tbea.ic.operation.common.formatter.v2.data.PercentSingleFormatter;
import com.tbea.ic.operation.common.formatter.v2.data.TextFormatter;
import com.tbea.ic.operation.common.formatter.v2.excel.ExcelHeaderCenterFormatter;
import com.tbea.ic.operation.common.formatter.v2.excel.ExcelHeaderFormatter;
import com.tbea.ic.operation.common.formatter.v2.excel.ExcelMergeFormatter;
import com.tbea.ic.operation.common.formatter.v2.excel.ExcelOffsetFormatter;
import com.tbea.ic.operation.common.formatter.v2.excel.ExcelTextFormatter;
import com.tbea.ic.operation.reportframe.XmlUtil.OnLoop;


public class FormatterXmlInterpreter implements XmlInterpreter {

	FormatterMatcher parserMatcher(ELParser elp, Element handler){
		FormatterMatcher[] mRet = new FormatterMatcher[]{null};
		XmlUtil.each(handler.getChildNodes(), new XmlUtil.OnEach(){

			@Override
			public boolean on(Element elem) {
				if ("DefaultMatcher".equals(elem.getTagName())){
					mRet[0] = parserDefaultMatcher(elp, elem);
				}else if ("IndicatorMatcher".equals(elem.getTagName())){
					mRet[0] = parserIndicatorMatcher(elp, elem);
				}
				return mRet[0] != null;
			}
			
		});
		return mRet[0];
	}
	
	public List<Integer> asIntArray(ELParser elp, String str){
		String[] arr = str.replaceAll(" ", "").split(",");
		if (!arr[0].isEmpty()){
			List<Integer> ret = new ArrayList<Integer>();
			for (String item : arr){
				Integer retVal = XmlUtil.getInt(item, elp, null);
				if (retVal != null){
					ret.add(retVal);
				}
			}
			return ret;
		}
		return null;
	}
	
	public List<String> asStringArray(String str){
		String[] arr = str.replaceAll(" ", "").split(",");
		if (!arr[0].isEmpty()){
			List<String> ret = new ArrayList<String>();
			for (String item : arr){
				ret.add(item);
			}
			return ret;
		}
		return null;
	}
	
	
	private FormatterMatcher parserIndicatorMatcher(ELParser elp, Element matcher) {
		String rows = matcher.getAttribute("rows");
		String cols = matcher.getAttribute("cols");
		return new IndicatorMatcher(asStringArray(rows), asIntArray(elp, cols));
	}

	private FormatterMatcher parserDefaultMatcher(ELParser elp, Element matcher) {
		String rows = matcher.getAttribute("rows");
		String cols = matcher.getAttribute("cols");
		return new DefaultMatcher(asIntArray(elp, rows), asIntArray(elp, cols));
	}

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) {
		
		if (!"formatter".equals(e.getTagName())){
			return false;
		}
		ELParser elp = new ELParser(component);
		List<FormatterHandler> handlers = new ArrayList<FormatterHandler>();
		XmlUtil.each(e.getChildNodes(), new OnLoop(){

			@Override
			public void on(Element elem) {
				FormatterHandler handler = parserHandler(elp, component, elem);
				if (null != handler){
					handlers.add(handler);
				}
			}
			
		});
		
		String id = e.getAttribute("id");
		component.local(id, handlers);
		return true;
	}

	
	private FormatterHandler parserHandler(ELParser elp, AbstractXmlComponent component, Element item) {
		FormatterHandler handler = null;
		if ("EmptyFormatter".equals(item.getTagName())){
			handler = new EmptyFormatter(parserMatcher(elp, item));
		}else if ("NumberFormatter".equals(item.getTagName())){
			String reservedCount = item.getAttribute("reservedCount");
			if (!reservedCount.isEmpty()){
				handler = new NumberFormatter(parserMatcher(elp, item), Integer.valueOf(reservedCount));
			}else{
				handler = new NumberFormatter(parserMatcher(elp, item), 1);
			}
			if ("true".equals(item.getAttribute("trimZero"))){
				((NumberFormatter)handler).trimZero(true);
			}
		}else if ("PercentFormatter".equals(item.getTagName())){
			String reservedCount = item.getAttribute("reservedCount");
			if (!reservedCount.isEmpty()){
				handler = new PercentFormatter(parserMatcher(elp, item), Integer.valueOf(reservedCount));
			}else{
				handler = new PercentFormatter(parserMatcher(elp, item), 1);
			}
			if ("true".equals(item.getAttribute("trimZero"))){
				((PercentFormatter)handler).trimZero(true);
			}
		}else if ("PercentSingleFormatter".equals(item.getTagName())){
			String reservedCount = item.getAttribute("reservedCount");
			if (!reservedCount.isEmpty()){
				handler = new PercentSingleFormatter(parserMatcher(elp, item), Integer.valueOf(reservedCount));
			}else{
				handler = new PercentSingleFormatter(parserMatcher(elp, item), 1);
			}
		}else if ("TextFormatter".equals(item.getTagName())){
			handler = new TextFormatter(parserMatcher(elp, item));
		}else if ("ExcelHeaderCenterFormatter".equals(item.getTagName())){
			handler = new ExcelHeaderCenterFormatter(parserMatcher(elp, item), parserExcelTemplate(component, item), parserOffset(item));
		}else if ("ExcelHeaderFormatter".equals(item.getTagName())){
			handler = new ExcelHeaderFormatter(parserMatcher(elp, item), parserExcelTemplate(component, item), parserOffset(item));
		}else if ("ExcelOffsetFormatter".equals(item.getTagName())){
			handler = new ExcelOffsetFormatter(parserMatcher(elp, item), parserExcelTemplate(component, item), parserOffset(item));
		}else if ("ExcelTextFormatter".equals(item.getTagName())){
			handler = new ExcelTextFormatter(parserMatcher(elp, item), parserExcelTemplate(component, item), parserOffset(item));
		}else if ("ExcelMergeFormatter".equals(item.getTagName())){
			handler = new ExcelMergeFormatter(parserExcelTemplate(component, item));
			parserMergeRegion((ExcelMergeFormatter)handler, item);
		}
		return handler;
	}

	private int getIntAttribute(Element e, String attr, int defaultVal){
		String val = e.getAttribute(attr);
		if (null != val && !val.isEmpty()){
			return Integer.valueOf(val);
		}
		return defaultVal;
	}
	
	private void parserMergeRegion(ExcelMergeFormatter handler, Element item) {
		XmlUtil.each(item.getChildNodes(), new OnLoop(){
			
			@Override
			public void on(Element elem) {
				if ("MergeRegion".equals(elem.getTagName())){
					handler.addMergeRegion(new MergeRegion(
						getIntAttribute(elem, "x", 0),	
						getIntAttribute(elem, "y", 0),	
						getIntAttribute(elem, "width", 0),	
						getIntAttribute(elem, "height", 0)));
				}
			}

		});
	}

	private ExcelTemplate parserExcelTemplate(AbstractXmlComponent component,
			Element item) {
		NodeList list = item.getChildNodes();
		for (int i = 0; i < list.getLength(); ++i){
			Element e = (Element) list.item(i);
			if ("ExcelTemplate".equals(e.getTagName())){
				String refId = e.getAttribute("ref");
				return (ExcelTemplate) component.getVar(refId);
			}
		}
		return null;
	}

	private Offset parserOffset(Element item) {
		NodeList list = item.getChildNodes();
		for (int i = 0; i < list.getLength(); ++i){
			if (list.item(i) instanceof Element){
				Element e = (Element) list.item(i);
				if ("Offset".equals(e.getTagName())){
					return new Offset(getIntAttribute(e, "row", 0), 
							getIntAttribute(e, "col", 0));
				}
			}
		}
		return null;
	}
}