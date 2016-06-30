package com.tbea.ic.operation.reportframe;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;


public class SqlListXmlInterpreter implements XmlInterpreter {

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) {
		
		if (!"sqllist".equals(e.getTagName())){
			return false;
		}
		
		String id = e.getAttribute("id");
		List<Object> objs = new ArrayList<Object>();
		List<Object[]> sqlRet = (List<Object[]>) component.getVar(e.getAttribute("sql"));
		if (null != sqlRet){
			int index = 0;
			String value = e.getAttribute("value");
			if (null != value && !value.isEmpty()){
				index = Integer.valueOf(value).intValue();
			}
			List order = (List) component.getVar(e.getAttribute("order"));
			int by = 0;
			if (null != order){
				String sBy = e.getAttribute("by");
				if (null != sBy && !sBy.isEmpty()){
					by = Integer.valueOf(sBy).intValue();
				}
				
				injectFromSql(objs, sqlRet, index, order, by);
			}else{
				injectFromSql(objs, sqlRet, index);
			}
		}
		component.local(id, objs);
		return true;
	}

	private void injectFromSql(List<Object> objs, List<Object[]> sqlRet, int index,
			List order, int by) {
		for (int i = 0; i < order.size(); ++i){
			objs.add(null);
			for (int j = 0; j < sqlRet.size(); ++j){
				if (order.get(index).equals(sqlRet.get(j)[by])){
					objs.set(objs.size() - 1, sqlRet.get(j)[index]);
				}
			}
		}
	}

	private void injectFromSql(List<Object> objs, List<Object[]> sqlRet, int index) {
		for (int i = 0; i < sqlRet.size(); ++i){
			objs.add(sqlRet.get(i)[index]);
		}
	}
}