package com.xml.frame.report.interpreter;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import com.frame.script.el.ELParser;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.util.ExcelHelper;
import com.xml.frame.report.util.v2.core.DefaultMatcher;
import com.xml.frame.report.util.v2.core.EmptyFormatter;
import com.xml.frame.report.util.v2.core.FormatterHandler;
import com.xml.frame.report.util.v2.core.FormatterMatcher;
import com.xml.frame.report.util.v2.core.IndicatorMatcher;
import com.xml.frame.report.util.v2.core.MergeRegion;
import com.xml.frame.report.util.v2.core.Offset;
import com.xml.frame.report.util.v2.data.NumberFormatter;
import com.xml.frame.report.util.v2.data.PercentFormatter;
import com.xml.frame.report.util.v2.data.PercentSingleFormatter;
import com.xml.frame.report.util.v2.data.TextFormatter;
import com.xml.frame.report.util.v2.excel.ExcelHeaderCenterFormatter;
import com.xml.frame.report.util.v2.excel.ExcelHeaderFormatter;
import com.xml.frame.report.util.v2.excel.ExcelMergeFormatter;
import com.xml.frame.report.util.v2.excel.ExcelOffsetFormatter;
import com.xml.frame.report.util.v2.excel.ExcelTextFormatter;
import com.xml.frame.report.util.v2.excel.ExcelTitleFilter;
import com.xml.frame.report.util.xml.Each;
import com.xml.frame.report.util.xml.Loop;
import com.xml.frame.report.util.xml.XmlUtil;
import com.xml.frame.report.util.xml.XmlWalker;




public class FormatterXmlInterpreter implements XmlInterpreter {

	private ELParser elp;
	AbstractXmlComponent component;
	FormatterMatcher parserMatcher(Element handler) throws Exception{
		FormatterMatcher[] mRet = new FormatterMatcher[]{null};
		XmlWalker.eachChildren(handler, elp, new Each(){

			@Override
			public boolean on(Element elem) throws Exception {
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
	
	
	private FormatterMatcher parserIndicatorMatcher(Element matcher) throws Exception {
		String rows = XmlUtil.getAttr(matcher, "rows");
		String cols = XmlUtil.getAttr(matcher, "cols");
		return new IndicatorMatcher(
				XmlUtil.toStringList(rows, elp),
				XmlUtil.toIntList(cols, elp));
	}

	private FormatterMatcher parserDefaultMatcher(Element matcher) throws Exception {
		String rows = XmlUtil.getAttr(matcher, "rows");
		String cols = XmlUtil.getAttr(matcher, "cols");
		return new DefaultMatcher(
				XmlUtil.toIntList(rows, elp), 
				XmlUtil.toIntList(cols, elp));
	}

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		
		if (!Schema.isFormatter(e)){
			return false;
		}
		//ReportLogger.trace().debug(component.getConfig().getTagName() + " : " + XmlUtil.toStringFromDoc(e));
		this.component = component;
		elp = new ELParser(component);
		List<FormatterHandler> handlers = new ArrayList<FormatterHandler>();
		XmlWalker.eachChildren(e, elp, new Loop(){

			@Override
			public void on(Element elem) throws Exception {
				FormatterHandler handler = parserHandler(component, elem);
				if (null != handler){
					handlers.add(handler);
				}
			}
			
		});
		
		component.put(e, handlers);
		return true;
	}

	
	private FormatterHandler parserHandler(AbstractXmlComponent component, Element item) throws Exception {
		FormatterHandler handler = null;
		if ("EmptyFormatter".equals(item.getTagName())){
			handler = new EmptyFormatter(parserMatcher(item));
		}else if ("NumberFormatter".equals(item.getTagName())){
			String reservedCount = item.getAttribute("reservedCount");
			NumberFormatter nf = null;
			if (!reservedCount.isEmpty()){
				nf = new NumberFormatter(parserMatcher(item), Integer.valueOf(reservedCount));
			}else{
				nf = new NumberFormatter(parserMatcher(item), 1);
			}
			if ("true".equals(item.getAttribute("trimZero"))){
				nf.trimZero(true);
			}if ("true".equals(item.getAttribute("currency"))){
				nf.setCurrency(true);
			}
			handler = nf;
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

	private List<List<String>> parserTitles(Element item) throws Exception {
		List<List<String>> titles = new ArrayList<List<String>>();
		Element ts = XmlWalker.element(item.getElementsByTagName("titles"), elp, 0);
		XmlWalker.eachChildren(ts, elp, new Loop(){
			@Override
			public void on(Element elem) {
				Object obj = component.getVar(elem.getAttribute("ref"));
				if (null != obj){
					titles.add((List<String>) obj);
				}
			}
		});
		return titles;
	}

	private int getIntAttribute(Element e, String attr, int defaultVal) throws Exception{
		return XmlUtil.getIntAttr(e, attr, elp, defaultVal);
	}
	
	private void parserMergeRegion(ExcelMergeFormatter handler, Element item) throws Exception {
		XmlWalker.eachChildren(item, elp, new Loop(){
			
			@Override
			public void on(Element elem) throws Exception {
				if ("MergeRegion".equals(elem.getTagName())){
					MergeRegion mr = new MergeRegion(
							getIntAttribute(elem, "x", 0),	
							getIntAttribute(elem, "y", 0),	
							getIntAttribute(elem, "width", 0),	
							getIntAttribute(elem, "height", 0));
					
					
					Integer yCount = getIntAttribute(elem, "yCount", -1);
					if (-1 == yCount){
						handler.addMergeRegion(mr);
					}else{
						for (int i = 0; i < yCount; ++i){
							handler.addFMR(new MergeRegion(
							mr.getX(),	
							mr.getY() + i * mr.getHeight(),	
							mr.getWidth(),	
							mr.getHeight()));
						}
					}
				}
			}

		});
	}

	private ExcelHelper parserExcelTemplate(AbstractXmlComponent component,
			Element item) {
		Element e = XmlWalker.child(item, elp, "ExcelTemplate");
		if (null != e){
			String refId = e.getAttribute("ref");
			return (ExcelHelper) component.getVar(refId);
		}
		return null;
	}

	private Offset parserOffset(Element item) throws Exception {
		Element e = XmlWalker.child(item, elp, "Offset");
		if (null != e){
			return new Offset(getIntAttribute(e, "row", 0), 
					getIntAttribute(e, "col", 0));
		}
		return null;
	}
}