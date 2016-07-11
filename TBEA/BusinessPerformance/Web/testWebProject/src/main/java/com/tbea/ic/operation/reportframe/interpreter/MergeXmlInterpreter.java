package com.tbea.ic.operation.reportframe.interpreter;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import net.sf.json.JSONArray;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.reportframe.component.service.Transaction;
import com.tbea.ic.operation.reportframe.el.ELParser;
import com.tbea.ic.operation.reportframe.util.TypeUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil;
import com.tbea.ic.operation.reportframe.util.XmlUtil.OnLoop;

class FieldSql{
	String prop;
	int type;
	Object value;
	Integer ref;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Integer getRef() {
		return ref;
	}
	public void setRef(Integer ref) {
		this.ref = ref;
	}
	public String getProp() {
		return prop;
	}
	public void setProp(String prop) {
		this.prop = prop;
	}
	public FieldSql(String prop, int type, Object value, Integer ref) {
		super();
		this.prop = prop;
		this.type = type;
		this.value = value;
		this.ref = ref;
	}
	
}

public class MergeXmlInterpreter implements XmlInterpreter {

	ELParser elp;
	
	@Override
	public boolean accept(AbstractXmlComponent component, Element e) {
		
		if (!Schema.isMerge(e)){
			return false;
		}
		elp = new ELParser(component);
		String table = (String) XmlUtil.getObjectAttr(e, "table", elp);
		JSONArray data = (JSONArray) XmlUtil.getObjectAttr(e, "data", elp);
		if (table != null && !table.isEmpty() && data != null){
			
			List<FieldSql> where = compile(e.getElementsByTagName("where"));
			List<FieldSql> set = compile(e.getElementsByTagName("set"));
			Transaction tx = (Transaction) component.getVar(component.getConfig().getAttribute("transaction"));
			EntityManager em = tx.getEntityManager();
			
			for (int i = 0, len = data.size(); i < len; ++i){
				JSONArray row = data.getJSONArray(i);
				merge(em, table, row, where, set);
			}
		}
		return true;
	}

	private String getValue(JSONArray row, FieldSql sql){
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
				sbValues.append(getValue(row, setSql));
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
				sb.append(getValue(row, setSql));
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
				sb.append(getValue(row, whereSql));
				if (whereSql != where.get(where.size() - 1)){
					sb.append("and ");
				}
			}
		}
		em.createNativeQuery(sb.toString()).executeUpdate();
	}

	private FieldSql compileElement(Element elem){
		Object val = XmlUtil.getObjectAttr(elem, "value", elp);
		if (val != null){
			return new FieldSql(
					elem.getTagName(), 
					TypeUtil.typeof(elem), 
					val, 
					null);
		}else{
			Integer ref = XmlUtil.getIntAttr(elem, "ref", elp, null);
			if (null != ref){
				return new FieldSql(
						elem.getTagName(), 
						TypeUtil.typeof(elem), 
						null, 
						ref);
			}
		}
		return null;
	}
	
	private List<FieldSql> compile(NodeList list) {
		List<FieldSql> result = new ArrayList<FieldSql>();
		XmlUtil.each(list, new OnLoop(){

			@Override
			public void on(Element e) {
				XmlUtil.each(e.getChildNodes(), new OnLoop(){

					@Override
					public void on(Element elem) {
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