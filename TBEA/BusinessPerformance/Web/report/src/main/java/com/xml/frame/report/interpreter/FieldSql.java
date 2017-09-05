package com.xml.frame.report.interpreter;

import com.frame.script.el.ELParser;
import com.xml.frame.report.util.xml.XmlUtil;

public class FieldSql{
	ELParser elp;
	String prop;
	int type;
	String value;
	Integer ref;
	String join;
	String in;
	String select;
	Integer joinType;
	String oper="=";
	String test;
	
	public boolean test() throws Exception {
		if (null == test){
			return true;
		}else{
			return XmlUtil.getBoolean(test, elp);
		}
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public FieldSql(ELParser elp) {
		this.elp = elp;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	public boolean hasValue(){
		return value != null;
	}
	
	public Object getValue() {
		try {
			return XmlUtil.parseELText(value, elp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public boolean hasRef(){
		return ref != null;
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
	
	public boolean hasJoin(){
		return join != null;
	}
	
	public String getJoin() {
		return join;
	}
	public void setJoin(String join) {
		this.join = join;
	}
	public String getIn() {
		return in;
	}
	public void setIn(String in) {
		this.in = in;
	}
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	public Integer getJoinType() {
		return joinType;
	}
	public void setJoinType(Integer joinType) {
		this.joinType = joinType;
	}
	public String getOper() {
		return oper;
	}
	public void setOper(String oper) {
		this.oper = oper;
	}
	
}