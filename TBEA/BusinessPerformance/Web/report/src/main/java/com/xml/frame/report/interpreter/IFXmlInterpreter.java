package com.xml.frame.report.interpreter;

import java.util.Stack;

import org.w3c.dom.Element;

import com.frame.script.el.ELParser;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.util.xml.XmlUtil;
import com.xml.frame.report.util.xml.XmlWalker;

/***********************************
 * tagName : if/elseif
 * text	: EL Object 判断条件
 * attributes :
 * 		test : EL Object 判断条件
 * 	
 * 
 * tagName : if/elseif
 * text	: EL Object 判断条件
 * attributes :
 * 		test : EL Object 判断条件
 * 
 * 
 * tagName : else
 * 
 * ************************************/

public class IFXmlInterpreter implements XmlInterpreter {

	Stack<Boolean> conditionStack = new Stack<Boolean>();
	ELParser elp;
	private Boolean getTest(Element e, AbstractXmlComponent component) throws Exception{
		String boolVal = e.getAttribute("test");
		if (boolVal.isEmpty() && XmlUtil.hasText(e)){
			boolVal = XmlUtil.getText(e);
		}
		return XmlUtil.getBoolean(boolVal, elp);	
	}
	
	private Boolean hasNextCase(Element e){
		Element eNext;
		try {
			eNext = XmlWalker.nextElement(e, elp);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		return null != eNext && (Schema.isElse(eNext) || Schema.isElseIf(eNext));
	}
	
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {

		if (Schema.isIf(e)){
			elp = new ELParser(component);
			Boolean condition = getTest(e, component);	
			if (hasNextCase(e)){
				conditionStack.add(condition);
			}
			if (condition){ 
				e = (Element) e.cloneNode(true);
				XmlUtil.copyAttr(component.getConfig(), e);
				component.clone(e).run(component.globalContext());
			}
			
			return true;
		} else if (Schema.isElseIf(e)){
			Boolean lastCondition = conditionStack.peek();
			Boolean hasNext = hasNextCase(e);
			if (!lastCondition){
				Boolean condition = getTest(e, component);	
				if (condition){
					if (hasNext){
						conditionStack.pop();
						conditionStack.push(condition);
					}
					e = (Element) e.cloneNode(true);
					XmlUtil.copyAttr(component.getConfig(), e);
					component.clone(e).run(component.globalContext());
				}
			}
			
			if (!hasNext){
				conditionStack.pop();
			}
			return true;
		} else if (Schema.isElse(e)){
			if (!conditionStack.pop()){
				e = (Element) e.cloneNode(true);
				XmlUtil.copyAttr(component.getConfig(), e);
				component.clone(e).run(component.globalContext());
			}
			return true;
		} 
		return false;
	}
}