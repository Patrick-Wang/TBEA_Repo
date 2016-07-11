package com.tbea.ic.operation.reportframe.interpreter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.reportframe.component.entity.Table;
import com.tbea.ic.operation.reportframe.el.ELParser;
import com.tbea.ic.operation.reportframe.util.XmlUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil.OnLoop;

public class TableXmlInterpreter implements XmlInterpreter {

	ELParser elp;
	
	ListXmlInterpreter listInterpreter = new ListXmlInterpreter();
	
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) {

		if (!Schema.isTable(e)) {
			return false;
		}
		elp = new ELParser(component);
		Table tb = new Table();
		String id = e.getAttribute("id");
		tb.setIds((List) component.getVar(e.getAttribute("rowIds")));
		List<List<Object>> tbValues = new ArrayList<List<Object>>();
		tb.setValues(tbValues);
		XmlUtil.each(e.getChildNodes(), new OnLoop(){

			@Override
			public void on(Element elem) {
				if ("col".equals(elem.getTagName()) || "list".equals(elem.getTagName())){
					parseCol(component, tb, elem);
				}else if ("sumRow".equals(elem.getTagName())){
					parseSumRow(component, tb, elem);
				}else if ("divRow".equals(elem.getTagName())){
					parseDivRow(component, tb, elem);
				}else if ("divCol".equals(elem.getTagName())){
					parseDivCol(component, tb, elem);
				}else if ("copyCol".equals(elem.getTagName())){
					parseCopyCol(tb, elem);
				}
			}
			
		});
		component.local(id, tb);
		return true;
	}

	protected void parseCopyCol(Table tb,
			Element elem) {
		Integer row = tb.getIds().indexOf(XmlUtil.getIntAttr(elem, "rowId", elp, null));
		Integer from = XmlUtil.getIntAttr(elem, "from", elp, null);
		List<Integer> targets = parserArray(elem.getAttribute("to"));
		if (row != null && from != null && !targets.isEmpty()){
			for (Integer tar : targets){
				tb.getValues().get(tar).set(row, tb.getValues().get(from).get(row));
			}
		}
	}

	protected void parseDivCol(AbstractXmlComponent component, Table tb,
			Element elem) {
		ELParser elp = new ELParser(component);
		Integer sub = XmlUtil.getIntAttr(elem, "sub", elp, null);
		Integer base = XmlUtil.getIntAttr(elem, "base", elp, null);
		Integer target = XmlUtil.getIntAttr(elem, "toCol", elp, null);
		if (sub != null && base != null && target != null){
			NodeList list = elem.getElementsByTagName("excludeRow");
			List<Integer> excludeRows = parserArray(XmlUtil.elementText(list, 0));
			List<Object> tarCols = tb.getValues().get(target);
			List<Object> subCols = tb.getValues().get(sub);
			List<Object> baseCols = tb.getValues().get(base);
			for (int i = 0; i < tarCols.size(); ++i) {
				if (excludeRows.indexOf(i) < 0){
					tarCols.set(i, MathUtil.division(
									MathUtil.o2d(subCols.get(i)), 
									MathUtil.o2d(baseCols.get(i))));
				}
			}
		}
	}

	protected void parseCol(AbstractXmlComponent component, Table tb, Element elem) {
		List list = null;
		if ("col".equals(elem.getTagName())){
			list = (List) component.getVar(elem.getAttribute("list"));
		}else{
			listInterpreter.accept(component, elem);
			list = (List) component.getVar(elem.getAttribute("id"));
		}
		if (null == list){
			list = Util.resize(new ArrayList<Object>(),	tb.getIds().size());
		} else if (list.size() != tb.getIds().size()){
			list = Util.resize(list, tb.getIds().size());
		}
		tb.getValues().add(list);	
			
	}

	private int getTargetIndex(AbstractXmlComponent component, Element item,
			Table tb) {
		ELParser elp = new ELParser(component);
		Integer target = XmlUtil.getIntAttr(item, "toId", elp, -1);
		if (target >= 0){
			target = tb.getIds().indexOf(target);
		}
		
		if (target < 0){
			target = XmlUtil.getIntAttr(item, "toRow", elp, -1);
		}
		return target;
	}

	private List<Integer> parserArray(String arr) {
		return XmlUtil.toIntList(arr, elp);
	}

	private List<Integer> idsToRows(List<Integer> ids, Table tb) {
		List<Integer> ret = new ArrayList<Integer>();
		int index = 0;
		for (int i = 0; i < ids.size(); ++i) {
			index = tb.getIds().indexOf(ids.get(i));
			if (index >= 0) {
				ret.add(index);
			}
		}
		return ret;
	}

	private void parseSumRow(AbstractXmlComponent component, Table tb,
			Element item) {
		int index = getTargetIndex(component, item, tb);
		if (index >= 0) {
			NodeList list = item.getElementsByTagName("rangeIds");
			Set<Integer> rows = new HashSet<Integer>();
			if (list.getLength() > 0) {
				List<Integer> rangeRow = idsToRows(
						parserArray(XmlUtil.elementText(list, 0)), tb);
				for (Integer row : rows) {
					if (row < rangeRow.get(0) || row > rangeRow.get(1)) {
						rows.remove(row);
					}
				}

				for (int i = rangeRow.get(0); i <= rangeRow.get(1); ++i) {
					rows.add(i);
				}
			}

			list = item.getElementsByTagName("rangeRows");
			if (list.getLength() > 0) {
				List<Integer> rangeRow = parserArray(XmlUtil.elementText(list, 0));
				for (Integer row : rows) {
					if (row < rangeRow.get(0) || row > rangeRow.get(1)) {
						rows.remove(row);
					}
				}

				for (int i = rangeRow.get(0); i <= rangeRow.get(1); ++i) {
					rows.add(i);
				}
			}

			if (rows.isEmpty()) {
				list = item.getElementsByTagName("inIds");

				if (list.getLength() > 0) {
					List<Integer> ids = parserArray(XmlUtil.elementText(list, 0));
					rows.addAll(idsToRows(ids, tb));
				}

				list = item.getElementsByTagName("inRows");
				if (list.getLength() > 0) {
					rows.addAll(parserArray(XmlUtil.elementText(list, 0)));
				}
			}

			list = item.getElementsByTagName("excIds");
			if (list.getLength() > 0) {
				List<Integer> excRows = idsToRows(
						parserArray(XmlUtil.elementText(list, 0)), tb);
				for (Integer row : excRows) {
					rows.remove(row);
				}
			}

			list = item.getElementsByTagName("excRows");
			if (list.getLength() > 0) {
				List<Integer> excRows = parserArray(XmlUtil.elementText(list, 0));
				for (Integer row : excRows) {
					rows.remove(row);
				}
			}

			list = item.getElementsByTagName("excludeCol");
			List<Integer> excludeCols = parserArray(XmlUtil.elementText(list, 0));
			
			for (int i = 0; i < tb.getValues().size(); ++i) {
				if (excludeCols.indexOf(i) < 0){
					for (Integer row : rows) {
						Object val = tb.getValues().get(i).get(row);
						if (val instanceof Double) {
							tb.getValues().get(i).set(
									index,
									MathUtil.sum((Double) tb.getValues().get(i).get(index), 
											(Double) val));
						} else if (val instanceof Integer) {
							
							tb.getValues().get(i).set(
										index,
										MathUtil.sum((Integer) tb.getValues().get(i).get(index), 
												(Integer) val));
						}
					}
				}
			}
		}
	}
	
	private void parseDivRow(AbstractXmlComponent component, Table tb,
			Element item) {
		int index = getTargetIndex(component, item, tb);
		if (index >= 0) {

			Integer subRow = null;
			Integer baseRow = null;
			NodeList list = item.getElementsByTagName("subId");
			ELParser elp = new ELParser(component);
			if (list.getLength() > 0) {
				Integer subId = XmlUtil.getInt(
						XmlUtil.elementText(list, 0),
						elp, null);
				subRow = tb.getIds().indexOf(subId);
			}

			if (null == subRow) {
				list = item.getElementsByTagName("subRow");
				if (list.getLength() > 0) {
					subRow = XmlUtil.getInt(
							XmlUtil.elementText(list, 0),
							elp, null);
				}
			}

			if (null != subRow) {
				list = item.getElementsByTagName("baseId");
				if (list.getLength() > 0) {
					Integer baseId = XmlUtil.getInt(
							XmlUtil.elementText(list, 0),
							elp, null);
					baseRow = tb.getIds().indexOf(baseId);
				}

				if (null == baseRow) {
					list = item.getElementsByTagName("baseRow");
					if (list.getLength() > 0) {
						baseRow = XmlUtil.getInt(
								XmlUtil.elementText(list, 0),
								elp, null);
					}
				}
			}

			
			
			if (subRow != null && baseRow != null) {
				list = item.getElementsByTagName("excludeCol");
				List<Integer> excludeCols = parserArray(XmlUtil.elementText(list, 0));
				
				List<Object> col = null;
				for (int i = 0; i < tb.getValues().size(); ++i) {
					if (excludeCols.indexOf(i) < 0){
						col = tb.getValues().get(i);
						if (col.get(subRow) instanceof Double ||
								col.get(baseRow) instanceof Double) {
							col.set(index, MathUtil.division(
									(Double) col.get(subRow),
									(Double)col.get(baseRow)));
						}
					}
				}
			}
		}
	}
}