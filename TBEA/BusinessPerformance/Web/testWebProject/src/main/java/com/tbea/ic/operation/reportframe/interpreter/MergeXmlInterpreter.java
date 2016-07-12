package com.tbea.ic.operation.reportframe.interpreter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.sf.json.JSONArray;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.tbea.ic.operation.common.Util;
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
					jrow.add(cell.getStringCellValue());
					break;
				case TypeUtil.OBJECT:
					jrow.add(null);
					break;
				case TypeUtil.SQLDATE:
					jrow.add(Util.formatToDay(ExcelUtil.parseDate(cell)));
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
		elp = new ELParser(component);
		String table = (String) XmlUtil.getObjectAttr(e, "table", elp);
		Object dataObj = component.getVar("data");
		if (table != null && !table.isEmpty() && dataObj != null){
			where = compile(e.getElementsByTagName("where"));
			set = compile(e.getElementsByTagName("set"));
			Map<Integer, Integer> types = new HashMap<Integer, Integer>();
			Transaction tx = (Transaction) component.getVar(component.getConfig().getAttribute("transaction"));
			EntityManager em = tx.getEntityManager();
		
			if (dataObj instanceof JSONArray){
				JSONArray data = (JSONArray) dataObj;				
				for (int i = 0, len = data.size(); i < len; ++i){
					JSONArray row = data.getJSONArray(i);
					merge(em, table, row, where, set);
				}
				
			} else if (dataObj instanceof XSSFWorkbook){
				XSSFWorkbook workbook = (XSSFWorkbook) dataObj;
				XSSFSheet sheet = workbook.getSheetAt(0);
				boolean isInsert = where.isEmpty();
				int maxCol = getTypes(types);
				where.add(0, new FieldSql("id", TypeUtil.INT, null, maxCol + 1));
				for (int i = 1; i <= sheet.getLastRowNum(); ++i){
					XSSFRow row = sheet.getRow(i);
					if (null != row){
						JSONArray jrow = XSSF2Json(row, types, maxCol);
						if (isInsert){
							jrow.add("add");
						}
						merge(em, table, jrow, where, set);
					}
				}		
			} 
		}
		
		return true;
	}

	
	
	private String getValue(JSONArray row, FieldSql sql, EntityManager em){
		String ret = null;
		if (sql.getValue() != null){
			switch (sql.getType()){
			case TypeUtil.INT:
			case TypeUtil.DOUBLE:
				ret = "" + sql.getValue();
				break;
			case TypeUtil.STRING:
			case TypeUtil.SQLDATE:
				ret = "'" + sql.getValue() + "'";
				break;
			}
		}else{
			
			if (sql.getJoin() != null){
				String querySql = "select " + sql.getSelect() + " from " + sql.getJoin() + " where " + sql.getIn() + " = ?0";
				Query q = em.createNativeQuery(querySql);
				List<Object> result = null;
				switch (sql.getJoinType()){
				case TypeUtil.INT:
					Integer val = Util.toIntNull(row.getString(sql.getRef()));
					if (null != val){
						q.setParameter(0, val);
						result = q.getResultList();
					}
					
					break;
				case TypeUtil.DOUBLE:
					Double d = Util.toDoubleNull(row.getString(sql.getRef()));
					if (null != d){
						q.setParameter(0, d);
						result = q.getResultList();
					}
					break;
				case TypeUtil.STRING:
					String s = row.getString(sql.getRef());
					if (!s.isEmpty()){
						q.setParameter(0, s);
						result = q.getResultList();
					}
					break;
				case TypeUtil.SQLDATE:
					String date = row.getString(sql.getRef());
					if (!date.isEmpty()){
						q.setParameter(0, Date.valueOf(date));
						result = q.getResultList();
					}
					break;
				}
				
				if (null != result && !result.isEmpty()){
					System.out.println(result.get(0).getClass());
					System.out.println(result.get(0));
					if (!result.get(0).getClass().isArray()){
						switch (sql.getType()){
						case TypeUtil.INT:
						case TypeUtil.DOUBLE:
							ret = "" + result.get(0);
							break;
						case TypeUtil.STRING:
						case TypeUtil.SQLDATE:
							ret = "'"+ result.get(0) + "'";
							break;
						}
						
					}
				}
			}else{
				switch (sql.getType()){
				case TypeUtil.INT:
					Integer val = Util.toIntNull(row.getString(sql.getRef()));
					if (null != val){
						ret = val + "";
					}
					break;
				case TypeUtil.DOUBLE:
					Double d = Util.toDoubleNull(row.getString(sql.getRef()));
					if (null != d){
						ret = d + "";
					}
					break;
				case TypeUtil.STRING:
				case TypeUtil.SQLDATE:
					String s = row.getString(sql.getRef());
					if (!s.isEmpty()){
						ret = "'" + s + "'";
					}
					break;
				}
			}
			
			
		}
		
		
		
		
		return ret;
	}
	
	private void merge(EntityManager em, String table, JSONArray row, List<FieldSql> where,
			List<FieldSql> set) {
		
		if (!where.isEmpty()){
			String firstWhere = row.getString(where.get(0).getRef());
			if (null != firstWhere && firstWhere.startsWith("add")){
				doInsert(em, table, row, where, set);
				return;
			}
		}
		doUpdate(em, table, row, where, set);
		
	}

	private void doInsert(EntityManager em, String table, JSONArray row,
			List<FieldSql> where, List<FieldSql> set) {
		StringBuilder sb = new StringBuilder();
		sb.append(" INSERT INTO ");
		sb.append(table);
		sb.append(" ");
		StringBuilder sbCols = new StringBuilder();
		StringBuilder sbValues = new StringBuilder();
		if (!set.isEmpty()){
			sbCols.append("(");
			sbValues.append("(");
			for (FieldSql setSql: set){
				sbCols.append(setSql.getProp());
				sbValues.append(getValue(row, setSql, em));
				if (setSql != set.get(set.size() - 1)){
					sbCols.append(", ");
					sbValues.append(", ");
				}
			}
			sbValues.append(")");
			sbCols.append(") VALUES ");
		}
		sb.append(sbCols.toString());
		sb.append(sbValues.toString());
		em.createNativeQuery(sb.toString()).executeUpdate() ;
	}

	private void doUpdate(EntityManager em, String table, JSONArray row,
			List<FieldSql> where, List<FieldSql> set) {
		StringBuilder sb = new StringBuilder();
		sb.append(" UPDATE ");
		sb.append(table);
		sb.append(" ");
		if (!set.isEmpty()){
			sb.append(" SET ");
			for (FieldSql setSql: set){
				sb.append(setSql.getProp());
				sb.append("=");
				sb.append(getValue(row, setSql, em));
				if (setSql != set.get(set.size() - 1)){
					sb.append(", ");
				}
			}
		}
		
		if (!where.isEmpty()){
			sb.append(" WHERE ");
			for (FieldSql whereSql: where){
				sb.append(whereSql.getProp());
				sb.append("=");
				sb.append(getValue(row, whereSql, em));
				if (whereSql != where.get(where.size() - 1)){
					sb.append("and ");
				}
			}
		}
		em.createNativeQuery(sb.toString()).executeUpdate();
	}

	private FieldSql compileElement(Element elem) throws Exception{
		Object val = XmlUtil.getObjectAttr(elem, "value", elp);
		FieldSql fs = new FieldSql();
		fs.setProp(elem.getTagName());
		fs.setType(TypeUtil.typeof(elem));
		if (val != null){
			fs.setValue(val);
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