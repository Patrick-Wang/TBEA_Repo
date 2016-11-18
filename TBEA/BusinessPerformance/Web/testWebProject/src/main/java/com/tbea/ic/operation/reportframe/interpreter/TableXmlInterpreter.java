package com.tbea.ic.operation.reportframe.interpreter;

import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.reportframe.component.AbstractXmlComponent;
import com.tbea.ic.operation.reportframe.component.entity.Table;
import com.tbea.ic.operation.reportframe.el.ELParser;
import com.tbea.ic.operation.reportframe.util.StringUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil.OnLoop;

public class TableXmlInterpreter implements XmlInterpreter {

	ELParser elp;
	
	ListXmlInterpreter listInterpreter = new ListXmlInterpreter();
	
	List<Integer> tempCols;
	
	private void putTemp(Integer col){
		if (tempCols == null){
			tempCols = new ArrayList<Integer>();
		}

		boolean inserted = false;
		for (int i = 0; i < tempCols.size(); ++i){
			if (tempCols.get(i) > col){
				tempCols.add(i, col);
				inserted = true;
				break;
			}else if (tempCols.get(i).equals(col)){
				inserted = true;
				break;
			}
		}
		
		if (!inserted){
			tempCols.add(col);
		}
	}
	
	private void clearTemps(List<List<Object>> tbValues){
		if (tempCols != null){
			
			for(int i = tempCols.size() - 1; i >= 0; --i){
				tbValues.remove(tempCols.get(i).intValue());
			}
			tempCols.clear();
		}
	}
	
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {

		if (!Schema.isTable(e)) {
			return false;
		}
		//ReportLogger.trace().debug(component.getConfig().getTagName() + " : " + XmlUtil.toStringFromDoc(e));
		elp = new ELParser(component);
		Table tb = new Table();
		tb.setIds((List) component.getVar(e.getAttribute("rowIds")));
		List<List<Object>> tbValues = new ArrayList<List<Object>>();
		tb.setValues(tbValues);
		
		if (e.hasAttribute("table")){
			Object obj = XmlUtil.getObjectAttr(e, "table", elp);
			if (obj instanceof List){
				parseTable((List)obj, tb);
			}
		}

		XmlUtil.each(e.getChildNodes(), new OnLoop(){

			@Override
			public void on(Element elem) throws Exception {
				if ("col".equals(elem.getTagName()) || "list".equals(elem.getTagName())){
					parseCol(component, tb, elem);
				}else if ("sumRow".equals(elem.getTagName())){
					parseSumRow(component, tb, elem);
				}else if ("divRow".equals(elem.getTagName())){
					parseDivRow(component, tb, elem);
				}else if ("divCol".equals(elem.getTagName())){
					parseDivCol(component, tb, elem);
				}else if ("growthRates".equals(elem.getTagName())){
					parseGrowthRates(component, tb, elem);
				}else if ("copyCol".equals(elem.getTagName())){
					parseCopyCol(tb, elem);
				}
			}
			
		});
		
		
		if (e.hasAttribute("order") ){
			parseOrderby(component, tb, e);
		}

		
		this.clearTemps(tbValues);
		component.put(e, tb);
		return true;
	}

	private void parseOrderby(AbstractXmlComponent component, Table tb,
			Element e) throws Exception {
		String orders = e.getAttribute("order");
		String[] orderList = orders.split(",");
		boolean asc = true;
		if (!orderList[0].contains("asc")){
			asc = false;
		}
		String colVal = orderList[0].replaceAll("asc", "").replaceAll("desc", "");
		Integer col = Util.toIntNull(StringUtil.trim(colVal));
		if (null != col){
			List<Comparable> copies = new ArrayList<Comparable>();
			copies.addAll((Collection<? extends Comparable>) tb.getValues().get(col));
			List<Integer> newOrder = null;
			if (asc){
				newOrder = parseAscOrder(copies);
			}else{
				newOrder = parseDescOrder(copies);
			}
			
			reorderTable(newOrder, tb);
		}
	}

	private List<Object> reorderList(List<Integer> newOrder, List oldVals, List target){
		target.clear();
		for (int i = 0; i < newOrder.size(); ++i){
			target.add(oldVals.get(newOrder.get(i)));
		}
		return oldVals;
	}
	
	private void reorderTable(List<Integer> newOrder, Table tb) {
		List workList = new ArrayList();
		List depList;
		for (int i = 0; i < tb.getValues().size(); ++i){
			depList = reorderList(newOrder, tb.getValues().get(i), workList);
			tb.getValues().set(i, workList);
			workList = depList;
		}
		depList = reorderList(newOrder, tb.getIds(), workList);
		tb.setIds(workList);
	}

	private List<Integer> parseDescOrder(List<Comparable> copies) {
		List<Integer> orders = new ArrayList<Integer>();
		int index = 0;
		Set<Integer> excludes = new HashSet<Integer>();
		for (int i = copies.size() - 1; i >= 0; --i){
			index = MathUtil.max(copies, excludes);
			orders.add(index);
			excludes.add(index);
		}
		return orders;
	}

	private List<Integer> parseAscOrder(List<Comparable> copies) {
		List<Integer> orders = new ArrayList<Integer>();
		int index = 0;
		Set<Integer> excludes = new HashSet<Integer>();
		for (int i = copies.size() - 1; i >= 0; --i){
			index = MathUtil.min(copies, excludes);
			orders.add(index);
			excludes.add(index);
		}	
		return orders;
	}

	private void parseTable(List rows, Table tb) {
		if (rows.isEmpty()){
			return;
		}
		
		if (rows.get(0) instanceof List){
			parseList(rows, tb);
			return;
		}
		
		if (rows.get(0) != null && rows.get(0).getClass().isArray()){
			parseArray(rows, tb);
			return;
		}
		
		parseRow(rows, tb);
	}

	private void parseRow(List<List<Object>> rows, Table tb) {
		for (int i = 0; i < rows.size(); ++i){
			for (int j = 0; j < rows.get(i).size(); ++j){
				if (tb.getValues().size() <= j){
					tb.getValues().add(new ArrayList<Object>());
				}
				tb.getValues().get(j).add(o2s(rows.get(i).get(j)));
			}
		}
	}

	private String o2s(Object o){
		String s = null;
		if (null != o){
			if (o instanceof Date){
				s = Util.formatToDay((Date)o);
			}else if (o instanceof Timestamp){
				s = Util.formatToSecond((Timestamp)o);
			}else{
				s = o.toString();
			}
		}
		return s;
	}
	
	private void parseArray(List<Object[]> rows, Table tb) {
		for (int i = 0; i < rows.size(); ++i){
			for (int j = 0; j < rows.get(i).length; ++j){
				if (tb.getValues().size() <= j){
					tb.getValues().add(new ArrayList<Object>());
				}
				tb.getValues().get(j).add(o2s(rows.get(i)[j]));
			}
		}
	}

	private void parseList(List<Object> row, Table tb) {
		for (int i = 0; i < row.size(); ++i){
			if (tb.getValues().size() <= i){
				tb.getValues().add(new ArrayList<Object>());
			}
			List<Object> col = new ArrayList<Object>();
			col.add(o2s(row.get(i)));
			tb.getValues().add(col);
		}
	}

	protected void parseGrowthRates(AbstractXmlComponent component, Table tb,
			Element elem) throws Exception {
		ELParser elp = new ELParser(component);
		Integer sj = XmlUtil.getIntAttr(elem, "sj", elp, null);
		Integer tq = XmlUtil.getIntAttr(elem, "tq", elp, null);
		Integer target = XmlUtil.getIntAttr(elem, "toCol", elp, null);
		if (sj != null && tq != null && target != null){
			NodeList list = elem.getElementsByTagName("excludeCol");
			List<Integer> excludeRows = parserArray(XmlUtil.elementText(list, 0));
			List<Object> tarCols = tb.getValues().get(target);
			List<Object> sjCols = tb.getValues().get(sj);
			List<Object> tqCols = tb.getValues().get(tq);
			for (int i = 0; i < tarCols.size(); ++i) {
				if (excludeRows.indexOf(i) < 0){
					tarCols.set(i, MathUtil.minus(div(
									MathUtil.o2d(sjCols.get(i)), 
									MathUtil.o2d(tqCols.get(i))), 1.0));
				}
			}
		}
	}

	protected void parseCopyCol(Table tb,
			Element elem) throws Exception {
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
			Element elem) throws Exception {
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
					tarCols.set(i, div(
									MathUtil.o2d(subCols.get(i)), 
									MathUtil.o2d(baseCols.get(i))));
				}
			}
		}
	}

	protected void parseCol(AbstractXmlComponent component, Table tb, Element elem) throws Exception {
		List list = null;
		if ("col".equals(elem.getTagName())){
			if (elem.hasAttribute("rank")){
				int col = XmlUtil.getIntAttr(elem, "rank", elp, -1);
				if (col >= 0){
					list = parseRank(tb, col, "true".equals(elem.getAttribute("desc")));
				}
			}else{
				if (component.hasObject(elem.getAttribute("list"))){
					list = (List) component.getVar(elem.getAttribute("list"));
				}else{
					list = (List) XmlUtil.getObjectAttr(elem, "list", elp);
				}
				
			}
		}else{
			elem.setAttribute("id", "_tb_col_");
			listInterpreter.accept(component, elem);
			list = (List) component.removeLocal("_tb_col_");
		}
		if (null == list){
			list = Util.resize(new ArrayList<Object>(),	tb.getIds().size());
		} else if (list.size() != tb.getIds().size()){
			list = Util.resize(list, tb.getIds().size());
		}
		tb.getValues().add(list);	
		if ("true".equals(elem.getAttribute("temp"))){
			this.putTemp(tb.getValues().size() - 1);
		}	
	}

	private List parseRank(Table tb, int col, boolean desc) {
		List column = tb.getValues().get(col);
		List<Comparable> compare = new ArrayList<Comparable>();
		boolean inserted = false;
		for (Object obj : column){
			inserted = false;
			if (obj != null && obj instanceof Comparable){
				for (int i = 0; i < compare.size(); ++i){
					if (compare.get(i).compareTo(obj) > 0){
						compare.add(i, (Comparable) obj);
						inserted = true;
						break;
					}
				}
				if (!inserted){
					compare.add((Comparable) obj);
				}
			}
		}
		
		List<Integer> rank = new ArrayList<Integer>();
		for (Object obj : column){
			if (obj != null){
				int order = compare.indexOf(obj);
				if (order >= 0){
					if (desc){
						rank.add(compare.size() - order);
					}else{
						rank.add(order + 1);
					}					
					continue;
				}
			}
			rank.add(null);
		}
		
		return rank;
	}

	private int getTargetIndex(AbstractXmlComponent component, Element item,
			Table tb) throws Exception {
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

	private List<Integer> parserArray(String arr) throws Exception {
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

	Set<Integer> getAllRows(Element item, Table tb) throws Exception{
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
		return rows;
	}
	
	
	Set<Integer> getExcludeCols(Element item) throws Exception{
		NodeList cols = item.getElementsByTagName("excludeCol");
		Set<Integer> excludeCols = null;
		if (cols.getLength() > 0){
			excludeCols = XmlUtil.toIntSet(XmlUtil.elementText(cols, 0), elp);
		}
		return excludeCols;
	}
	
	private void parseSumRow(AbstractXmlComponent component, Table tb,
			Element item) throws Exception {
		int targetCol = getTargetIndex(component, item, tb);
		if (targetCol < 0) {
			return;
		}
			Set<Integer> rows = getAllRows(item, tb);
			
			if (rows.isEmpty()){
				return;
			}
			
			
			Set<Integer> excludeCols = getExcludeCols(item);
			
			Object val = null;
			List<Object> tbCol = null;
			if (excludeCols != null){
				for (int i = 0, len = tb.getValues().size(); i < len; ++i) {
					if (excludeCols.contains(i)){
						continue;
					}
					tbCol = tb.getValues().get(i);
					for (Integer row : rows) {
						val = tbCol.get(row);
						if (val instanceof Double) {
							tbCol.set(targetCol,
							MathUtil.sum((Double) tbCol.get(targetCol), (Double) val));
						} else if (val instanceof Integer) {
							tbCol.set(targetCol,
							MathUtil.sum((Integer) tbCol.get(targetCol), (Integer) val));
						}
					}
				}
			}else{
				for (int i = 0, len = tb.getValues().size(); i < len; ++i) {
					tbCol = tb.getValues().get(i);
					for (Integer row : rows) {
						val = tbCol.get(row);
						if (val instanceof Double) {
							tbCol.set(targetCol,
							MathUtil.sum((Double) tbCol.get(targetCol), 
											(Double) val));
						} else if (val instanceof Integer) {
							tbCol.set(targetCol,
							MathUtil.sum((Integer) tbCol.get(targetCol), 
												(Integer) val));
						}
					}
				}
			}
		
	}
	
	private Double div(Double sub, Double base){
		if (sub != null &&
			base != null &&
			(Util.isNegative(sub) || 
			Util.isNegative(base) || 
			Util.isZero(base) || 
			Util.isZero(sub))) {
			return null;
		}
		return MathUtil.division(sub, base);
	}
	
	private void parseDivRow(AbstractXmlComponent component, Table tb,
			Element item) throws Exception {
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
							col.set(index, div(
									(Double)col.get(subRow),
									(Double)col.get(baseRow)));
						}
					}
				}
			}
		}
	}
}