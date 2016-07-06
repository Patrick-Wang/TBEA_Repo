package com.tbea.ic.operation.reportframe.interpreter;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

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
import com.tbea.ic.operation.common.formatter.v2.excel.ExcelTitleFilter;
import com.tbea.ic.operation.reportframe.el.ELParser;
import com.tbea.ic.operation.reportframe.util.XmlUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil.OnLoop;


public class FormatterXmlInterpreter implements XmlInterpreter {

	private ELParser elp;
	
	FormatterMatcher parserMatcher(Element handler){
		FormatterMatcher[] mRet = new FormatterMatcher[]{null};
		XmlUtil.each(handler.getChildNodes(), new XmlUtil.OnEach(){

			@Override
			public boolean on(Element elem) {
				if ("DefaultMatcher".equals(elem.getTagName())){
					mRet[0] = parserDefaultMatcher(elem);
				}else if ("IndicatorMatcher".equals(elem.getTagName())){
					mRet[0] = parserIndicatorMatcher(elem);
				}
				return mRet[0] != null;
			}
			
		});
		return mRet[0];
	}
	
	
	private FormatterMatcher parserIndicatorMatcher(Element matcher) {
		String rows = matcher.getAttribute("rows");
		String cols = matcher.getAttribute("cols");
		return new IndicatorMatcher(
				XmlUtil.toStringList(rows, elp),
				XmlUtil.toIntList(cols, elp));
	}

	private FormatterMatcher parserDefaultMatcher(Element matcher) {
		String rows = matcher.getAttribute("rows");
		String cols = matcher.getAttribute("cols");
		return new DefaultMatcher(
				XmlUtil.toIntList(rows, elp), 
				XmlUtil.toIntList(cols, elp));
	}

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) {
		
		if (!Schema.isFormatter(e)){
			return false;
		}
		elp = new ELParser(component);
		List<FormatterHandler> handlers = new ArrayList<FormatterHandler>();
		XmlUtil.each(e.getChildNodes(), new OnLoop(){

			@Override
			public void on(Element elem) {
				FormatterHandler handler = parserHandler(component, elem);
				if (null != handler){
					handlers.add(handler);
				}
			}
			
		});
		
		String id = e.getAttribute("id");
		component.local(id, handlers);
		return true;
	}

	
	private FormatterHandler parserHandler(AbstractXmlComponent component, Element item) {
		FormatterHandler handler = null;
		if ("EmptyFormatter".equals(item.getTagName())){
			handler = new EmptyFormatter(parserMatcher(item));
		}else if ("NumberFormatter".equals(item.getTagName())){
			String reservedCount = item.getAttribute("reservedCount");
			if (!reservedCount.isEmpty()){
				handler = new NumberFormatter(parserMatcher(item), Integer.valueOf(reservedCount));
			}else{
				handler = new NumberFormatter(parserMatcher(item), 1);
			}
			if ("true".equals(item.getAttribute("trimZero"))){
				((NumberFormatter)handler).trimZero(true);
			}
		}else if ("PercentFormatter".equals(item.getTagName())){
			String reservedCount = item.getAttribute("reservedCount");
			if (!reservedCount.isEmpty()){
				handler = new PercentFormatter(parserMatcher(item), Integer.valueOf(reservedCount));
			}else{
				handler = new PercentFormatter(parserMatcher(item), 1);
			}
			if ("true".equals(item.getAttribute("trimZero"))){
				((PercentFormatter)handler).trimZero(true);
			}
		}else if ("PercentSingleFormatter".equals(item.getTagName())){
			String reservedCount = item.getAttribute("reservedCount");
			if (!reservedCount.isEmpty()){
				handler = new PercentSingleFormatter(parserMatcher(item), Integer.valueOf(reservedCount));
			}else{
				handler = new PercentSingleFormatter(parserMatcher(item), 1);
			}
		}else if ("TextFormatter".equals(item.getTagName())){
			handler = new TextFormatter(parserMatcher(item));
		}else if ("ExcelHeaderCenterFormatter".equals(item.getTagName())){
			handler = new ExcelHeaderCenterFormatter(parserMatcher(item), parserExcelTemplate(component, item), parserOffset(item));
		}else if ("ExcelHeaderFormatter".equals(item.getTagName())){
			handler = new ExcelHeaderFormatter(parserMatcher(item), parserExcelTemplate(component, item), parserOffset(item));
		}else if ("ExcelOffsetFormatter".equals(item.getTagName())){
			handler = new ExcelOffsetFormatter(parserMatcher(item), parserExcelTemplate(component, item), parserOffset(item));
		}else if ("ExcelTextFormatter".equals(item.getTagName())){
			handler = new ExcelTextFormatter(parserMatcher(item), parserExcelTemplate(component, item), parserOffset(item));
		}else if ("ExcelMergeFormatter".equals(item.getTagName())){
			handler = new ExcelMergeFormatter(parserExcelTemplate(component, item));
			parserMergeRegion((ExcelMergeFormatter)handler, item);
		}else if ("ExcelTitleFilter".equals(item.getTagName())){
			handler = new ExcelTitleFilter(parserExcelTemplate(component, item), parserOffset(item), parserTitles(item));
		}
		return handler;
	}

	private List<List<String>> parserTitles(Element item) {
		List<List<String>> titles = new ArrayList<List<String>>();
		Element ts = XmlUtil.element(item.getElementsByTagName("titles"), 0);
		XmlUtil.each(ts.getChildNodes(), new OnLoop(){
			@Override
			public void on(Element elem) {
				Object obj = XmlUtil.getObjectAttr(elem, "ref", elp);
				if (null != obj){
					titles.add((List<String>) obj);
				}
			}
		});
		return titles;
	}

	private int getIntAttribute(Element e, String attr, int defaultVal){
		return XmlUtil.getIntAttr(e, attr, elp, defaultVal);
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
		Element e = XmlUtil.element(item.getChildNodes(), "ExcelTemplate");
		if (null != e){
			String refId = e.getAttribute("ref");
			return (ExcelTemplate) component.getVar(refId);
		}
		return null;
	}

	private Offset parserOffset(Element item) {
		Element e = XmlUtil.element(item.getChildNodes(), "Offset");
		if (null != e){
			return new Offset(getIntAttribute(e, "row", 0), 
					getIntAttribute(e, "col", 0));
		}
		return null;
	}
}