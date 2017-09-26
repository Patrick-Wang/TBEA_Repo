package com.xml.frame.report.interpreter;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.frame.script.el.ELParser;
import com.frame.script.util.TypeUtil;
import com.util.tools.xml.Loop;
import com.xml.frame.report.ReportLogger;
import com.xml.frame.report.component.AbstractXmlComponent;
import com.xml.frame.report.component.manager.ComponentManager;
import com.xml.frame.report.component.service.JpaTransaction;
import com.xml.frame.report.util.Util;
import com.xml.frame.report.util.excel.ExcelUtil;
import com.xml.frame.report.util.excel.ValidationException;
import com.xml.frame.report.util.xml.XmlElWalker;
import com.xml.frame.report.util.xml.XmlUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;


/***********************************
 * tagName : merge
 * attributes :
 * 		table : 更新的表名称
 * 		data : 数据EL对象，可以为json 数组 , list 数组, xlsx 文档, iterator *
 **************** sub nodes*************
 * 	tagName : where
 *			where 条件，where 下面子节点为表的各个字段 eg.
 *	<where>
 *   <nf type="int" value="${cal.year}"/>
 *   <yf type="int" value="${cal.month}"/>
 *   <dwid type="int" value="${compId}"/>
 *   <hyid type="int" ref="0"/>
 *  </where>
 *  tagName : set
 *  	eg.
 *  <set>
 *    <lsh type="string" ref="${counter.reset[0].val}"/>
 *    <xmqc type="string" ref="${counter.next.val}" test="${this != null}"/>
 *    <xmgs type="string" ref="${counter.next.val}"/>
 *    <xmlx type="string" ref="${counter.next.val}"/>
 *  </set>
 *  
 *  <set>
 *    <account_id type="int" ref="1" join="jygk_account" in="name" select="id" joinType="string"/>
 *    <company_id type="int" ref="2" join="jygk_dwxx" in="name" select="id" joinType="string"/>
 *    <auth_type type="int" ref="3" join="auth_instruction" in="instruction" select="id" joinType="string"/>
 *	</set>
 * ************************************/

public class MergeXmlInterpreter implements XmlInterpreter {

	
	private final int UPDATE_COUNT = 200;
	private final int INSERT_COUNT = 200;
	private final int FLUSH_COUNT = 400;
	
	ELParser elp;
	List<FieldSql> where;
	List<FieldSql> set;
	StringBuilder insertValues;
	StringBuilder updateValues;
	int insertCount;
	int updateCount;
	int flushCount;
	AbstractXmlComponent component;
	EntityManager em = null;
	Connection con = null;
	
	private int getTypes(Map<Integer, Integer> types) {
		int max = 0;
		for (FieldSql sql : where) {
			types.put(sql.getRef(), sql.getType());
			if (sql.getRef() != null && max < sql.getRef()) {
				max = sql.getRef();
			}
		}
		for (FieldSql sql : set) {
			types.put(sql.getRef(), sql.getType());
			if (sql.getRef() != null && max < sql.getRef()) {
				max = sql.getRef();
			}
		}
		return max;
	}

	JSONArray XSSF2Json(XSSFRow row, Map<Integer, Integer> types, int maxCol)
			throws ValidationException {
		JSONArray jrow = new JSONArray();
		for (int j = 0; j <= maxCol; ++j) {
 
			XSSFCell cell = row.getCell(j);
			if (cell == null || !types.containsKey(j)) {
				jrow.add(null);
			} else {
				switch (types.get(j)) {
				case TypeUtil.DOUBLE:
					jrow.add(ExcelUtil.parseNumber(cell));
					break;
				case TypeUtil.INT:
					Double dVal = ExcelUtil.parseNumber(cell);
					if (null != dVal) {
						jrow.add(dVal.intValue());
					} else {
						jrow.add(null);
					}
					break;
				case TypeUtil.STRING:
					if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
						jrow.add(cell.getStringCellValue());
					} else {
						String raw = cell.getRawValue();
						jrow.add(raw);
					}

					break;
				case TypeUtil.OBJECT:
					jrow.add(null);
					break;
				case TypeUtil.SQLDATE:
					Date d = ExcelUtil.parseDate(cell);
					if (null != d) {
						jrow.add(Util.formatToDay(d));
					} else {
						jrow.add(null);
					}

					break;
				}
			}
		}
		return jrow;
	}

	@Override
	public boolean accept(AbstractXmlComponent component, Element e)
			throws Exception {

		if (!Schema.isMerge(e)) {
			return false;
		}

		this.component = component;
		elp = new ELParser(component);
		String table = (String) XmlUtil.getObjectAttr(e, "table", elp);
		if (table == null || table.isEmpty()) {
			return true;
		}

		Integer from = XmlUtil.getIntAttr(e, "from", elp, null);
		Integer to = XmlUtil.getIntAttr(e, "to", elp, null);
		
		Object dataObj = null;
		if ("true".equalsIgnoreCase(XmlUtil.getAttr(e, "nodata"))) {
			JSONArray ja = new JSONArray();
			ja.add(new JSONArray());
			dataObj = ja;
		} else {
			dataObj = component.getVar(XmlUtil.getAttr(e, "data"));
			if (dataObj == null) {
				dataObj = XmlUtil.getObjectAttr(e, "data", elp);
			}
		}

		if (dataObj == null) {
			return true;
		}

		where = compile(e.getElementsByTagName("where"));
		set = compile(e.getElementsByTagName("set"));
		String trans = component.getConfigAttribute("transaction");
		ReportLogger.trace().debug("database : {}", trans);
		JpaTransaction tx = (JpaTransaction) component.getVar(trans);
		
		if (null == tx){
			DataSource ds = ComponentManager.getInstance().getDataSourceFactory().getDataSource(component.getConfigAttribute("ds"));
			if (null == ds) {
				throw new Exception("请指定 transaction " + e.toString());
			}else {
				con = ds.getConnection();
				con.setAutoCommit(false);
			}
		}else {
			em = tx.getEntityManager();
		}
		
		if (dataObj instanceof JSONArray) {
			mergeJson((JSONArray) dataObj, em, table, from, to);
		} else if (dataObj instanceof XSSFWorkbook) {
			mergeExcel((XSSFWorkbook) dataObj, em, table, from, to);
		} else if (dataObj instanceof List) {
			mergeList((List) dataObj, em, table, from, to);
		} else if (dataObj instanceof Iterator) {
			mergeIterator((Iterator) dataObj, em, table, from, to);
		}

		completeUpdate();
		completeInsert();
		if (null != con) {
			con.setAutoCommit(true);
			con.close();
			con = null;
		}
		this.component.removeLocal("i");
		return true;
	}

	
	private void updateSql(String sql) throws SQLException{
		ReportLogger.trace().info(sql);
		if (null != em) {
			em.createNativeQuery(sql).executeUpdate();
			if (flushCount % FLUSH_COUNT == 0){
				em.flush();
				Session session = (Session)em.getDelegate();
				session.clear();
				ReportLogger.trace().info("flush count " + flushCount);
			}
		}else {
			Statement st = con.createStatement();
			st.executeUpdate(sql);
			if (flushCount % FLUSH_COUNT == 0){
				con.commit();
				ReportLogger.trace().info("flush count " + flushCount);
			}
		}
		
	}
	
	private void completeInsert() throws SQLException {
		if (null != insertValues) {
			String insertSql = insertValues.toString();
			insertValues = null;
			updateSql(insertSql);	
			ReportLogger.trace().info("insert count " + insertCount);
		}
	}

	private void mergeIterator(Iterator it, EntityManager em, String table, Integer from, Integer to) throws SQLException {
		int start = (from != null ? from : 0);
		for (int i = 0; it.hasNext(); ++i) {
			if (i >= start){
				if (to != null && i > to){
					break;
				}
				
				JSONArray row = list2Json(it.next());
				this.component.local("i", i - start);
				merge(table, row);
			}
			
		}
	}

	private void mergeList(List dataList, EntityManager em, String table, Integer from, Integer to) throws SQLException {
		int end = (to != null ? Math.min(dataList.size(), to + 1) : dataList.size());
		int start = (from != null ? from : 0);
		for (int i = start; i < end; ++i) {
			JSONArray row = list2Json(dataList.get(i));
			this.component.local("i", i);
			merge(table, row);
		}
	}

	private void mergeExcel(XSSFWorkbook dataExcel, EntityManager em,
			String table, Integer from, Integer to) throws ValidationException, SQLException {
		XSSFWorkbook workbook = (XSSFWorkbook) dataExcel;
		XSSFSheet sheet = workbook.getSheetAt(0);
		boolean isInsert = where.isEmpty();
		Map<Integer, Integer> types = new HashMap<Integer, Integer>();
		int maxCol = getTypes(types);
		int end = (to != null ? Math.min(sheet.getLastRowNum() + 1, to + 1) : (sheet.getLastRowNum() + 1));
		int start = (from != null ? from : 1);
		for (int i = start; i < end; ++i) {
			XSSFRow row = sheet.getRow(i);
			if (null != row) {
				JSONArray jrow = XSSF2Json(row, types, maxCol);
				if (isInsert) {
					jrow.add("add");
				}
				this.component.local("i", i - start);
				merge(table, jrow);
			}
		}
	}

	private void mergeJson(JSONArray dataJson, EntityManager em, String table, Integer from, Integer to) throws SQLException {
		int end = (to != null ? Math.min(dataJson.size(), to + 1) : dataJson.size());
		int start = (from != null ? from : 0);
		for (int i = start; i < end; ++i) {
			JSONArray row = dataJson.getJSONArray(i);
			this.component.local("i", i - start);
			merge(table, row);
		}
	}

	private JSONArray list2Json(Object lRow) {
		JSONArray jrow = new JSONArray();
		if (lRow.getClass().isArray()) {
			Object[] arr = (Object[]) lRow;
			for (int i = 0; i < arr.length; ++i) {
				if (null != arr[i]) {
					if (arr[i] instanceof Date) {
						arr[i] = Util.formatToMill((Date) arr[i]);
					} else if (arr[i] instanceof java.util.Date) {
						arr[i] = Util.formatToMill((java.util.Date) arr[i]);
					} else if (arr[i] instanceof Timestamp) {
						arr[i] = Util.formatToMill(new Date(
								((Timestamp) arr[i]).getTime()));
					}
				}
				jrow.add(arr[i]);
			}
		} else if (lRow instanceof List) {
			List<Object> list = (List<Object>) lRow;
			for (Object obj : list) {
				if (null != obj) {
					if (obj instanceof Date) {
						obj = Util.formatToMill((Date) obj);
					} else if (obj instanceof java.util.Date) {
						obj = Util.formatToMill((java.util.Date) obj);
					} else if (obj instanceof Timestamp) {
						obj = Util.formatToMill(new Date(((Timestamp) obj)
								.getTime()));
					}
				}
				jrow.add(obj);
			}
		} else {
			ReportLogger.trace().error("list2Json type error : " + lRow);
		}
		return jrow;
	}

	private String getValue(FieldSql fs) {
		String ret = null;
		switch (fs.getType()) {
		case TypeUtil.INT:
		case TypeUtil.DOUBLE:
			ret = "" + fs.getValue();
			break;
		case TypeUtil.STRING:
		case TypeUtil.SQLDATE:
			Object val = fs.getValue();
			if (null != val) {
				ret = "'" + val + "'";
			}
			break;
		}
		return ret;
	}

	private String getJoin(JSONArray row, FieldSql fs, EntityManager em) {
		String ret = null;
		String querySql = "select " + fs.getSelect() + " from " + fs.getJoin()
				+ " where " + fs.getIn() + " = ?0";
		Query q = em.createNativeQuery(querySql);
		List<Object> result = null;
		switch (fs.getJoinType()) {
		case TypeUtil.INT:
			Integer val = Util.toIntNull(row.getString(fs.getRef()));
			if (null != val) {
				q.setParameter(0, val);
				result = q.getResultList();
			}
			break;
		case TypeUtil.DOUBLE:
			Double d = Util.toDoubleNull(row.getString(fs.getRef()));
			if (null != d) {
				q.setParameter(0, d);
				result = q.getResultList();
			}
			break;
		case TypeUtil.STRING:
			String s = row.getString(fs.getRef());
			if (!s.isEmpty()) {
				q.setParameter(0, s);
				result = q.getResultList();
			}
			break;
		case TypeUtil.SQLDATE:
			String date = row.getString(fs.getRef());
			if (!date.isEmpty()) {
				q.setParameter(0, Date.valueOf(date));
				result = q.getResultList();
			}
			break;
		}

		if (null != result && !result.isEmpty()) {
			if (!result.get(0).getClass().isArray()) {
				switch (fs.getType()) {
				case TypeUtil.INT:
				case TypeUtil.DOUBLE:
					ret = "" + result.get(0);
					break;
				case TypeUtil.STRING:
				case TypeUtil.SQLDATE:
					Object val = result.get(0);
					if (null != val) {
						ret = "'" + result.get(0) + "'";
					}
					break;
				}
			}
		}
		return ret;
	}

	private String getRef(JSONArray row, FieldSql fs) {
		String ret = null;
		switch (fs.getType()) {
		case TypeUtil.INT:
			Integer val = Util.toIntNull(row.getString(fs.getRef()));
			if (null != val) {
				ret = val + "";
			}
			break;
		case TypeUtil.DOUBLE:
			Double d = Util.toDoubleNull(row.getString(fs.getRef()));
			if (null != d) {
				ret = d + "";
			}
			break;
		case TypeUtil.STRING:
		case TypeUtil.SQLDATE:
			Integer ref = fs.getRef();
			if (!(row.get(ref) instanceof JSONNull)) {
				String s = row.getString(ref);
				if (!s.isEmpty()) {
					ret = "'" + s + "'";
				}
			}
			break;
		}
		return ret;
	}

	private String getQueryValue(JSONArray row, FieldSql fs, EntityManager em) {
		String ret = null;
		if (fs.hasValue()) {
			ret = getValue(fs);
		} else if (fs.hasJoin()) {
			ret = getJoin(row, fs, em);
		} else {
			ret = getRef(row, fs);
		}
		return ret;
	}

	private void merge(String table, JSONArray row) throws SQLException {
		boolean doInsert = false;
		String firstWhere = null;
		component.local("row", row);
		if (checkSetTest(row, em)) {
			do {
				if (where.isEmpty()) {
					break;
				}

				Integer ref = where.get(0).getRef();
				if (null == ref) {
					break;
				}

				firstWhere = row.getString(ref);
				if (null == firstWhere || !firstWhere.startsWith("add")) {
					break;
				}

				doInsert(table, row, set);
				doInsert = true;
			} while (false);

			if (!doInsert) {
				String whereSql = parseWhereSql(em, row);
				if (null != whereSql){
					doUpdate(table, whereSql, row);
				}else{
					doInsert(table, row, set);
				}				
			}
		}
		component.removeLocal("row");
	}

	private boolean checkSetTest(JSONArray row, EntityManager em) {
		boolean bRet = !set.isEmpty();
		try {
			for (FieldSql setSql : set) {
				if (setSql.getTest() != null
						&& setSql.getTest().contains("this")) {
					component
							.local("this", this.getQueryValue(row, setSql, em));
				}
				if (!setSql.test()) {
					ReportLogger.logger().info(
							setSql.getProp() + " 未通过  " + setSql.getTest()
									+ " 测试条件 ，导入失败 ：");
					ReportLogger.logger().info(row.toString());
					return false;
				}
			}
		} catch (Exception e) {
			ReportLogger.logger().info("checkSetTest error", e);
			bRet = false;
		}
		return bRet;
	}

	
	private void doInsert(String table, JSONArray row,
			List<FieldSql> set) throws SQLException {
		if (!set.isEmpty()) {
			if (insertValues == null) {
				insertValues = new StringBuilder();
				insertValues.append(" INSERT INTO ");
				insertValues.append(table);
				insertValues.append(" \r\n(");
				for (FieldSql setSql : set) {
					insertValues.append(setSql.getProp());
					if (setSql != set.get(set.size() - 1)) {
						insertValues.append(", ");
					}
				}
				insertValues.append(") VALUES ");
				insertValues.append(" \r\n(");
			} else {
				insertValues.append(",\r\n(");
			}

			for (FieldSql setSql : set) {
				insertValues.append(getQueryValue(row, setSql, em));
				if (setSql != set.get(set.size() - 1)) {
					insertValues.append(", ");
				}
			}
			insertValues.append(")");
			++insertCount;
			++flushCount;
			if (insertCount % INSERT_COUNT == 0) {
				completeInsert();
			}
		}
	}

	private String parseWhereSql(EntityManager em, JSONArray row) {
		if (!where.isEmpty()) {
			StringBuilder sb = new StringBuilder();
			sb.append(" WHERE ");
			for (FieldSql whereSql : where) {
				sb.append(whereSql.getProp());
				sb.append(whereSql.getOper());
				sb.append(getQueryValue(row, whereSql, em));
				if (whereSql != where.get(where.size() - 1)) {
					sb.append(" and ");
				}
			}
			return sb.toString();
		}
		return null;
	}

	private void doUpdate(String table, String whereSql, JSONArray row) throws SQLException {
		StringBuilder sbUpdate = new StringBuilder();
		sbUpdate.append(" UPDATE ")
		.append(table)
		.append(" set ");

		StringBuilder sbInsert = new StringBuilder();	
		sbInsert.append(" INSERT INTO ");
		sbInsert.append(table);
		sbInsert.append(" (");
		
		StringBuilder sbInsertValues = new StringBuilder();
		sbInsertValues.append(") VALUES ( ");	

		String prop = null;
		String value = null;
		for (FieldSql setSql : set) {
			prop = setSql.getProp();
			value = getQueryValue(row, setSql, em);
			
			sbInsert.append(prop);
			sbInsertValues.append(value);
			
			sbUpdate.append(prop);
			sbUpdate.append("=");
			sbUpdate.append(value);
			
			if (setSql != set.get(set.size() - 1)) {
				sbInsert.append(", ");
				sbInsertValues.append(", ");
				sbUpdate.append(", ");
			}
		}
		sbInsert
		.append(sbInsertValues.toString())
		.append(")");
		if (updateValues == null){
			updateValues = new StringBuilder();
		}

		updateValues.append("\r\nif exists (select * from ")
		.append(table)
		.append(whereSql)
		.append(")\r\n\t")
		.append(sbUpdate.toString())
		.append(whereSql)
		.append("\r\nelse \r\n\t")
		.append(sbInsert.toString());
		
		++flushCount;
		++updateCount;

		if (this.updateCount % UPDATE_COUNT == 0){
			completeUpdate();
		}
	}

	private void completeUpdate() throws SQLException {
		if (null != updateValues) {
			String sql = updateValues.toString();
			updateValues = null;
			updateSql(sql);	
			ReportLogger.trace().info("update count " + updateCount);			
		}
	}

	private FieldSql compileElement(Element elem) throws Exception {
		// Object val = XmlUtil.getObjectAttr(elem, "value", elp);
		FieldSql fs = new FieldSql(elp);
		fs.setProp(elem.getTagName());
		fs.setType(TypeUtil.typeof(elem));
		if (elem.hasAttribute("op")) {
			fs.setOper(elem.getAttribute("op"));
		}

		if (elem.hasAttribute("test")) {
			fs.setTest(elem.getAttribute("test"));
		}

		if (elem.hasAttribute("value")) {
			fs.setValue(elem.getAttribute("value"));
		} else {
			Integer ref = XmlUtil.getIntAttr(elem, "ref", elp, null);
			if (null != ref) {
				fs.setRef(ref);
				if (elem.hasAttribute("join") && elem.hasAttribute("in")
						&& elem.hasAttribute("select")
						&& elem.hasAttribute("joinType")) {
					fs.setJoin(elem.getAttribute("join"));
					fs.setIn(elem.getAttribute("in"));
					fs.setSelect(elem.getAttribute("select"));
					fs.setJoinType(TypeUtil.typeof(elem
							.getAttribute("joinType")));
				}
			} else {
				fs = null;
			}
		}

		return fs;
	}

	private List<FieldSql> compile(NodeList list) throws Exception {
		List<FieldSql> result = new ArrayList<FieldSql>();
		XmlElWalker.each(list, elp, new Loop() {

			@Override
			public void on(Element e) throws Exception {
				XmlElWalker.eachChildren(e, elp, new Loop() {

					@Override
					public void on(Element elem) throws Exception {
						FieldSql us = compileElement(elem);
						if (null != us) {
							result.add(us);
						}
					}

				});
			}
		});
		return result;
	}
}