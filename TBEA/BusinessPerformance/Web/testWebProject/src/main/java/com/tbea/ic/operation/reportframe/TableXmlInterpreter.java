package com.tbea.ic.operation.reportframe;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.tbea.ic.operation.common.Data;
import com.tbea.ic.operation.common.DataNode;
import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Util;

public class TableXmlInterpreter implements XmlInterpreter {

	private void loadHeader(AbstractXmlComponent component, DataNode header,
			Element col, List<List<Object>> tbValues, List ids) {
		Data data = new Data();
		data.setValue(col.getAttribute("name"));
		header.setData(data);
		NodeList cols = col.getElementsByTagName("col");
		for (int i = 0; i < cols.getLength(); ++i) {
			if (cols.item(i) instanceof Element) {
				DataNode header1 = new DataNode();
				Element subCol = (Element) cols.item(i);
				loadHeader(component, header1, subCol, tbValues, ids);
				header.getSubNodes().add(header1);
				header1.setParent(header);
			}
		}

		if (cols.getLength() == 0) {
			NodeList vals = col.getElementsByTagName("value");
			for (int i = 0; i < vals.getLength(); ++i) {
				if (vals.item(i) instanceof Element) {
					Element val = (Element) vals.item(i);
					List list = (List) component.getVar(val
							.getAttribute("list"));
					if (null == list) {
						tbValues.add(Util.resize(new ArrayList<Object>(),
								ids.size()));
					} else {
						tbValues.add(list);
					}
				}
			}

			if (vals.getLength() == 0) {
				tbValues.add(Util.resize(new ArrayList<Object>(), ids.size()));
			}
		}
	}

	@Override
	public boolean accept(AbstractXmlComponent component, Element e) {

		if (!"table".equals(e.getTagName())) {
			return false;
		}
		Table tb = new Table();
		String sql = e.getFirstChild().getTextContent();
		String id = e.getAttribute("id");
		tb.setName(e.getAttribute("name"));
		tb.setIds((List) component.getVar(e.getAttribute("rowIds")));
		List<List<Object>> tbValues = new ArrayList<List<Object>>();
		NodeList cols = e.getElementsByTagName("col");
		DataNode header = new DataNode();
		for (int i = 0; i < cols.getLength(); ++i) {
			if (cols.item(i) instanceof Element) {
				DataNode header1 = new DataNode();
				Element col = (Element) cols.item(i);
				loadHeader(component, header1, col, tbValues, tb.getIds());
				header.getSubNodes().add(header1);
				header1.setParent(header);
			}
		}
		tb.setHeader(header);
		tb.setValues(tbValues);

		NodeList sumRows = e.getElementsByTagName("sumRow");
		for (int i = 0; i < sumRows.getLength(); ++i) {
			parseSumRow(component, tb, (Element) sumRows.item(i));
		}

		NodeList divRows = e.getElementsByTagName("divRow");
		for (int i = 0; i < divRows.getLength(); ++i) {
			parseDivRow(component, tb, (Element) divRows.item(i));
		}

		component.local(id, tb);
		return true;
	}

	private int getTargetIndex(AbstractXmlComponent component, Element item,
			Table tb) {
		String target = item.getAttribute("toId");

		if (null != target && target.isEmpty()) {
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
		if (null != target && target.isEmpty()) {
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
				if (!(tb.getValues().get(i).get(index) instanceof String)) {
					for (Integer row : rows) {
						tb.getValues()
								.get(i)
								.set(index,
										MathUtil.sum((Double) tb.getValues()
												.get(i).get(index), (Double) tb
												.getValues().get(i).get(row)));
					}
				}
			}
		}
	}

	private Integer getInt(AbstractXmlComponent component, String content) {
		ELParser elp = new ELParser(component);
		List<ELExpression> elexps = elp.parser(content);
		if (elexps.isEmpty()) {
			return Integer.valueOf(content);
		} else {
			try {
				return (Integer) (elexps.get(0).value());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private void parseDivRow(AbstractXmlComponent component, Table tb,
			Element item) {
		int index = getTargetIndex(component, item, tb);
		if (index >= 0) {

			Integer subRow = null;
			Integer baseRow = null;
			NodeList list = item.getElementsByTagName("subId");
			if (list.getLength() >= 0) {
				Integer subId = getInt(component, ((Element) list.item(0))
						.getFirstChild().getTextContent());
				subRow = tb.getIds().indexOf(subId);
			}

			if (null == subRow) {
				list = item.getElementsByTagName("subRow");
				if (list.getLength() >= 0) {
					subRow = getInt(component, ((Element) list.item(0))
							.getFirstChild().getTextContent());
				}
			}

			if (null != subRow) {
				list = item.getElementsByTagName("baseId");
				if (list.getLength() >= 0) {
					Integer baseId = getInt(component, ((Element) list.item(0))
							.getFirstChild().getTextContent());
					baseRow = tb.getIds().indexOf(baseId);
				}

				if (null == baseRow) {
					list = item.getElementsByTagName("baseRow");
					if (list.getLength() >= 0) {
						baseRow = getInt(component, ((Element) list.item(0))
								.getFirstChild().getTextContent());
					}
				}
			}

			if (subRow != null && baseRow != null) {
				for (int i = 0; i < tb.getValues().size(); ++i) {
					if (!(tb.getValues().get(i).get(index) instanceof String)) {
						tb.getValues()
								.get(i)
								.set(index,
										MathUtil.division(
												(Double) tb.getValues().get(i)
														.get(subRow),
												(Double) tb.getValues().get(i)
														.get(baseRow)));
					}
				}
			}
		}
	}
}