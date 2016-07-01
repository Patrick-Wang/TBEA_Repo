package com.tbea.ic.operation.reportframe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.reportframe.XmlUtil.OnLoop;

public class TableXmlInterpreter implements XmlInterpreter {

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) {

		if (!"table".equals(e.getTagName())) {
			return false;
		}
		Table tb = new Table();
		String id = e.getAttribute("id");
		tb.setIds((List) component.getVar(e.getAttribute("rowIds")));
		List<List<Object>> tbValues = new ArrayList<List<Object>>();
		tb.setValues(tbValues);
		XmlUtil.each(e.getChildNodes(), new OnLoop(){

			@Override
			public void on(Element elem) {
				if ("col".equals(elem.getTagName())){
					parseCol(component, tb, elem);
				}else if ("sumRow".equals(elem.getTagName())){
					parseSumRow(component, tb, elem);
				}else if ("divRow".equals(elem.getTagName())){
					parseDivRow(component, tb, elem);
				}
			}
			
		});
		component.local(id, tb);
		return true;
	}

	protected void parseCol(AbstractXmlComponent component, Table tb, Element elem) {
		List list = (List) component.getVar(elem.getAttribute("list"));
		if (null == list){
			list = Util.resize(new ArrayList<Object>(),	tb.getIds().size());
		} else if (list.size() != tb.getIds().size()){
			list = Util.resize(list, tb.getIds().size());
		}
		tb.getValues().add(list);		
	}

	private int getTargetIndex(AbstractXmlComponent component, Element item,
			Table tb) {
		String target = item.getAttribute("toId");

		if (null != target && !target.isEmpty()) {
			ELParser elp = new ELParser(component);
			List<ELExpression> elexps = elp.parser(target);
			if (elexps.isEmpty()) {
				return tb.getIds().indexOf(Integer.valueOf(target));
			} else {
				try {
					return tb.getIds().indexOf(
							(Integer) (elexps.get(0).value()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		target = item.getAttribute("toRow");
		if (null != target && !target.isEmpty()) {
			ELParser elp = new ELParser(component);
			List<ELExpression> elexps = elp.parser(target);
			if (elexps.isEmpty()) {
				return Integer.valueOf(target);
			} else {
				try {
					return (Integer) (elexps.get(0).value()); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return -1;
	}

	private List<Integer> parserArray(AbstractXmlComponent component, String arr) {
		String[] sarr = arr.replaceAll(" ", "").split(",");
		List<Integer> ret = new ArrayList<Integer>();
		ELParser elp = new ELParser(component);
		for (int i = 0; i < sarr.length; ++i) {
			List<ELExpression> elexps = elp.parser(sarr[i]);
			if (elexps.isEmpty()) {
				ret.add(Integer.valueOf(sarr[i]));
			} else {
				try {
					ret.add((Integer) (elexps.get(0).value()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
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
						parserArray(component, ((Element) list.item(0))
								.getFirstChild().getTextContent()), tb);
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
				List<Integer> rangeRow = parserArray(component,
						((Element) list.item(0)).getFirstChild()
								.getTextContent());
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
					List<Integer> ids = parserArray(component,
							((Element) list.item(0)).getFirstChild()
									.getTextContent());
					rows.addAll(idsToRows(ids, tb));
				}

				list = item.getElementsByTagName("inRows");
				if (list.getLength() > 0) {
					rows.addAll(parserArray(component, ((Element) list.item(0))
							.getFirstChild().getTextContent()));
				}
			}

			list = item.getElementsByTagName("excIds");
			if (list.getLength() > 0) {
				List<Integer> excRows = idsToRows(
						parserArray(component, ((Element) list.item(0))
								.getFirstChild().getTextContent()), tb);
				for (Integer row : excRows) {
					rows.remove(row);
				}
			}

			list = item.getElementsByTagName("excRows");
			if (list.getLength() > 0) {
				List<Integer> excRows = parserArray(component,
						((Element) list.item(0)).getFirstChild()
								.getTextContent());
				for (Integer row : excRows) {
					rows.remove(row);
				}
			}

			for (int i = 0; i < tb.getValues().size(); ++i) {
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
				List<Object> col = null;
				for (int i = 0; i < tb.getValues().size(); ++i) {
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