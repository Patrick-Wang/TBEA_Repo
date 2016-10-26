package com.tbea.ic.operation.reportframe.interpreter;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.reportframe.ReportLogger;
import com.tbea.ic.operation.reportframe.component.AbstractXmlComponent;
import com.tbea.ic.operation.reportframe.component.service.Transaction;
import com.tbea.ic.operation.reportframe.el.ELParser;
import com.tbea.ic.operation.reportframe.interpreter.excel.ExcelUtil;
import com.tbea.ic.operation.reportframe.util.TypeUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil.OnLoop;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.validation.ValidationException;

public class MergeXmlInterpreter implements XmlInterpreter {

	ELParser elp;
	List<FieldSql> where;
	List<FieldSql> set;
	StringBuilder insertValues;
	int insertCount;
	AbstractXmlComponent component;
	private int getTypes(Map<Integer, Integer> types){
		int max = 0;
		for (FieldSql sql : where){
			types.put(sql.getRef(), sql.getType());
			if (sql.getRef() != null && max < sql.getRef()){
				max = sql.getRef();
			}
		}
		for (FieldSql sql : set){
			types.put(sql.getRef(), sql.getType());
			if (sql.getRef() != null && max < sql.getRef()){
				max = sql.getRef();
			}
		}
		return max;
	}
	
	
	JSONArray XSSF2Json(XSSFRow row, Map<Integer, Integer> types, int maxCol) throws ValidationException{
		JSONArray jrow = new JSONArray();
		for (int j = 0; j <= maxCol; ++j){

			XSSFCell cell = row.getCell(j);
			if(cell == null || !types.containsKey(j)){
				jrow.add(null);
			}else{
				switch(types.get(j)){
				case TypeUtil.DOUBLE:
					jrow.add(ExcelUtil.parseNumber(cell));
					break;
				case TypeUtil.INT:
					Double dVal = ExcelUtil.parseNumber(cell);
					if (null != dVal){
						jrow.add(dVal.intValue());
					}else{
						jrow.add(null);
					}
					break;
				case TypeUtil.STRING:
					if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING){
						jrow.add(cell.getStringCellValue());
					}else{
						String raw = cell.getRawValue();
						jrow.add(raw);
					}
					
					break;
				case TypeUtil.OBJECT:
					jrow.add(null);
					break;
				case TypeUtil.SQLDATE:
					Date d = ExcelUtil.parseDate(cell);
					if (null != d){
						jrow.add(Util.formatToDay(d));
					}else{
						jrow.add(null);
					}
					
					break;
				}
			}
		}
		return jrow;
	}
	
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) throws Exception {
		
		if (!Schema.isMerge(e)){
			return false;
		}
		//ReportLogger.trace().debug(component.getConfig().getTagName() + " : " + XmlUtil.toStringFromDoc(e));
		this.component = component;
		elp = new ELParser(component);
		String table = (String) XmlUtil.getObjectAttr(e, "table", elp);
		if (table == null || table.isEmpty()){
			return true;
		}
		
		Object dataObj = null;
		if("true".equalsIgnoreCase(XmlUtil.getAttr(e, "nodata"))){
			JSONArray ja = new JSONArray();
			ja.add(new JSONArray());
			dataObj = ja;
		}else{
			dataObj = component.getVar(XmlUtil.getAttr(e, "data"));
			if (dataObj == null){
				dataObj = XmlUtil.getObjectAttr(e, "data", elp);
			}
		}		

		if (dataObj == null){
			return true;
		}
		
		where = compile(e.getElementsByTagName("where"));
		set = compile(e.getElementsByTagName("set"));
		String trans = component.getConfigAttribute("transaction");
		ReportLogger.logger().debug("database : {}", trans);
		Transaction tx = (Transaction) component.getVar(trans);
		EntityManager em = tx.getEntityManager();
		if (dataObj instanceof JSONArray){
			mergeJson((JSONArray)dataObj, em, table);
		} else if (dataObj instanceof XSSFWorkbook){
			mergeExcel((XSSFWorkbook)dataObj, em, table);
		} else if (dataObj instanceof List){
			mergeList((List)dataObj, em, table);
		}  else if (dataObj instanceof Iterator){
			mergeIterator((Iterator)dataObj, em, table);
		} 
		
		completeInsert(em);
		
		this.component.removeLocal("i");
		return true;
	}

	
	
	private void completeInsert(EntityManager em) {
		if (null != insertValues){
			String insertSql = insertValues.toString();
			insertValues = null;
			insertCount = 0;
			ReportLogger.logger().info(insertSql);
			em.createNativeQuery(insertSql).executeUpdate() ;
		}
	}


	private void mergeIterator(Iterator it, EntityManager em, String table) {
		for (int i = 0; it.hasNext(); ++i){
			JSONArray row = list2Json(it.next());
			this.component.local("i", i - 1);
			merge(em, table, row);
		}
	}


	private void mergeList(List dataList, EntityManager em, String table) {
		for (int i = 0; i < dataList.size(); ++i){
			JSONArray row = list2Json(dataList.get(i));
			this.component.local("i", i - 1);
			merge(em, table, row);
		}
	}


	private void mergeExcel(XSSFWorkbook dataExcel, EntityManager em, String table) throws ValidationException {
		XSSFWorkbook workbook = (XSSFWorkbook) dataExcel;
		XSSFSheet sheet = workbook.getSheetAt(0);
		boolean isInsert = where.isEmpty();
		Map<Integer, Integer> types = new HashMap<Integer, Integer>();
		int maxCol = getTypes(types);
		for (int i = 1; i <= sheet.getLastRowNum(); ++i){
			XSSFRow row = sheet.getRow(i);
			if (null != row){
				JSONArray jrow = XSSF2Json(row, types, maxCol);
				if (isInsert){
					jrow.add("add");
				}
				this.component.local("i", i - 1);
				merge(em, table, jrow);
			}
		}		
	}


	private void mergeJson(JSONArray dataJson, EntityManager em, String table) {
		for (int i = 0, len = dataJson.size(); i < len; ++i){
			JSONArray row = dataJson.getJSONArray(i);
			this.component.local("i", i);
			merge(em, table, row);
		}
	}


	private JSONArray list2Json(Object lRow) {
		JSONArray jrow = new JSONArray();
		if (lRow.getClass().isArray()){
			Object[] arr = (Object[]) lRow;
			for(int i = 0; i < arr.length; ++i){
				if (null != arr[i]){
					if (arr[i] instanceof Date){
						arr[i] = Util.formatToMill((Date)arr[i]);
					} else if (arr[i] instanceof java.util.Date){
						arr[i] = Util.formatToMill((java.util.Date)arr[i]);
					} if (arr[i] instanceof Timestamp){
						arr[i] = Util.formatToMill(new Date(((Timestamp)arr[i]).getTime()));
					}
				}
				jrow.add(arr[i]);
			}
		}else if (lRow instanceof List){
			List<Object> list = (List<Object>) lRow;
			for (Object obj : list){
				if (null != obj){
					if (obj instanceof Date){
						obj = Util.formatToMill((Date)obj);
					} else if (obj instanceof java.util.Date){
						obj = Util.formatToMill((java.util.Date)obj);
					} else if (obj instanceof Timestamp){
						obj = Util.formatToMill(new Date(((Timestamp)obj).getTime()));
					}
				}
				jrow.add(obj);
			}
		}else{
			ReportLogger.logger().error("list2Json type error : " + lRow);
		}
		return jrow;
	}

	private String getValue(FieldSql fs){
		String ret = null;
		switch (fs.getType()){
		case TypeUtil.INT:
		case TypeUtil.DOUBLE:
			ret = "" + fs.getValue();
			break;
		case TypeUtil.STRING:
		case TypeUtil.SQLDATE:
			Object val = fs.getValue();
			if (null != val){
				ret = "'" + val + "'";
			}
			break;
		}
		return ret;
	}
	
	private String getJoin(JSONArray row, FieldSql fs, EntityManager em){
		String ret = null;
		String querySql = "select " + fs.getSelect() + " from " + fs.getJoin() + " where " + fs.getIn() + " = ?0";
		Query q = em.createNativeQuery(querySql);
		List<Object> result = null;
		switch (fs.getJoinType()){
		case TypeUtil.INT:
			Integer val = Util.toIntNull(row.getString(fs.getRef()));
			if (null != val){
				q.setParameter(0, val);
				result = q.getResultList();
			}
			break;
		case TypeUtil.DOUBLE:
			Double d = Util.toDoubleNull(row.getString(fs.getRef()));
			if (null != d){
				q.setParameter(0, d);
				result = q.getResultList();
			}
			break;
		case TypeUtil.STRING:
			String s = row.getString(fs.getRef());
			if (!s.isEmpty()){
				q.setParameter(0, s);
				result = q.getResultList();
			}
			break;
		case TypeUtil.SQLDATE:
			String date = row.getString(fs.getRef());
			if (!date.isEmpty()){
				q.setParameter(0, Date.valueOf(date));
				result = q.getResultList();
			}
			break;
		}
		
		if (null != result && !result.isEmpty()){
			if (!result.get(0).getClass().isArray()){
				switch (fs.getType()){
				case TypeUtil.INT:
				case TypeUtil.DOUBLE:
					ret = "" + result.get(0);
					break;
				case TypeUtil.STRING:
				case TypeUtil.SQLDATE:
					Object val = result.get(0);
					if (null != val){
						ret = "'" + result.get(0) + "'";
					}
					break;
				}
			}
		}
		return ret;
	}
	
	private String getRef(JSONArray row, FieldSql fs){
		String ret = null;
		switch (fs.getType()){
		case TypeUtil.INT:
			Integer val = Util.toIntNull(row.getString(fs.getRef()));
			if (null != val){
				ret = val + "";
			}
			break;
		case TypeUtil.DOUBLE:
			Double d = Util.toDoubleNull(row.getString(fs.getRef()));
			if (null != d){
				ret = d + "";
			}
			break;
		case TypeUtil.STRING:
		case TypeUtil.SQLDATE:
			Integer ref = fs.getRef();
			if (!(row.get(ref) instanceof JSONNull)){
				String s = row.getString(ref);
				if (!s.isEmpty()){
					ret = "'" + s + "'";
				}
			}
			break;
		}
		return ret;
	}

	private String getQueryValue(JSONArray row, FieldSql fs, EntityManager em){
		String ret = null;
		if (fs.hasValue()){
			ret = getValue(fs);
		}else if (fs.hasJoin()){
			ret = getJoin(row, fs, em);
		}else{
			ret = getRef(row, fs);
		}
		return ret;
	}
	
	private void merge(EntityManager em, String table, JSONArray row) {
		boolean doInsert = false;
		String firstWhere = null;
		component.local("row", row);
		do{
			if (where.isEmpty()){
				break;
			}
			
			Integer ref = where.get(0).getRef();
			if (null == ref){
				break;
			}
			
			firstWhere = row.getString(ref);
			if (null == firstWhere || !firstWhere.startsWith("add")){
				break;
			}
			
			doInsert(em, table, row, set);
			doInsert = true;
		}while(false);
		
		if (!doInsert){
			doUpdate(em, table, row);
		}
		
		component.removeLocal("row");
	}

	private void doInsert(EntityManager em, String table, JSONArray row, List<FieldSql> set) {
		if (!set.isEmpty()){
			if (insertValues == null){
				insertValues = new StringBuilder();
				insertValues.append(" INSERT INTO ");
				insertValues.append(table);
				insertValues.append(" \r\n(");
				for (FieldSql setSql: set){
					insertValues.append(setSql.getProp());
					if (setSql != set.get(set.size() - 1)){
						insertValues.append(", ");
					}
				}
				insertValues.append(") VALUES ");
				insertValues.append(" \r\n(");
			}else{
				insertValues.append(",\r\n(");
			}
			
			for (FieldSql setSql: set){
				insertValues.append(getQueryValue(row, setSql, em));
				if (setSql != set.get(set.size() - 1)){
					insertValues.append(", ");
				}
			}
			insertValues.append(")");
			++insertCount;
			if (insertCount == 200){
				completeInsert(em);
			}
		}
	}

	private String parseWhereSql(EntityManager em, JSONArray row){
		if (!where.isEmpty()){
			StringBuilder sb = new StringBuilder();
			sb.append(" WHERE ");
			for (FieldSql whereSql: where){
				sb.append(whereSql.getProp());
				sb.append(whereSql.getOper());
				sb.append(getQueryValue(row, whereSql, em));
				if (whereSql != where.get(where.size() - 1)){
					sb.append(" and ");
				}
			}
			return sb.toString();
		}
		return null;
	}
	
	private void doUpdate(EntityManager em, String table, JSONArray row) {
		String whereSql = parseWhereSql(em, row);
		Integer count = 0;
		if (null != whereSql){
			String sql = "select count(*) from " + table + whereSql;
			List ret = em.createNativeQuery(sql).getResultList();
			ReportLogger.logger().info(sql);
			count = (Integer) ret.get(0);
		}
		if (count > 0){
			StringBuilder sb = new StringBuilder();
			sb.append(" UPDATE ");
			sb.append(table);
			sb.append(" ");
			if (!set.isEmpty()){
				sb.append(" SET ");
				for (FieldSql setSql: set){
					sb.append(setSql.getProp());
					sb.append("=");
					sb.append(getQueryValue(row, setSql, em));
					if (setSql != set.get(set.size() - 1)){
						sb.append(", ");
					}
				}
			}
			sb.append(whereSql);
			String sql = sb.toString();
			ReportLogger.logger().debug(sql);
			em.createNativeQuery(sql).executeUpdate();
		}else{
			doInsert(em, table, row, set);
		}
	}

	private FieldSql compileElement(Element elem) throws Exception{
		//Object val = XmlUtil.getObjectAttr(elem, "value", elp);
		FieldSql fs = new FieldSql(elp);
		fs.setProp(elem.getTagName());
		fs.setType(TypeUtil.typeof(elem));
		if (elem.hasAttribute("op")){
			fs.setOper(elem.getAttribute("op"));
		}
		if (elem.hasAttribute("value")){
			fs.setValue(elem.getAttribute("value"));
		}else{
			Integer ref = XmlUtil.getIntAttr(elem, "ref", elp, null);
			if (null != ref){
				fs.setRef(ref);
				if (elem.hasAttribute("join") && elem.hasAttribute("in") && elem.hasAttribute("select") && elem.hasAttribute("joinType")){
					fs.setJoin(elem.getAttribute("join"));
					fs.setIn(elem.getAttribute("in"));
					fs.setSelect(elem.getAttribute("select"));
					fs.setJoinType(TypeUtil.typeof(elem.getAttribute("joinType")));
				}
			}
			else {
				fs = null;
			}
		}	
		
		return fs;
	}
	
	private List<FieldSql> compile(NodeList list) throws Exception {
		List<FieldSql> result = new ArrayList<FieldSql>();
		XmlUtil.each(list, new OnLoop(){

			@Override
			public void on(Element e) throws Exception {
				XmlUtil.each(e.getChildNodes(), new OnLoop(){

					@Override
					public void on(Element elem) throws Exception {
						FieldSql us = compileElement(elem);
						if (null != us){
							result.add(us);	
						}						
					}
					
				});
			}
		});
		return result;
	}
}