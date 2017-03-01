package com.tbea.ic.operation.reportframe.interpreter;

import java.util.Stack;

import org.w3c.dom.Element;

import com.tbea.ic.operation.reportframe.component.AbstractXmlComponent;
import com.tbea.ic.operation.reportframe.el.ELParser;
import com.tbea.ic.operation.reportframe.util.XmlUtil;

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
	
	private Boolean getTest(Element e, AbstractXmlComponent component) throws Exception{
		String boolVal = e.getAttribute("test");
		if (boolVal.isEmpty() && XmlUtil.hasText(e)){
			boolVal = XmlUtil.getText(e);
		}
		return XmlUtil.getBoolean(boolVal, new ELParser(component));	
	}
	
	private Boolean hasNextCase(Element e){
		Element eNext = XmlUtil.nextElement(e);
		return null != eNext && (Schema.isElse(eNext) || Schema.isElseIf(eNext));
	}
	
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {

		if (Schema.isIf(e)){
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