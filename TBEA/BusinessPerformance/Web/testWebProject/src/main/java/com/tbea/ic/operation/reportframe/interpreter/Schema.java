package com.tbea.ic.operation.reportframe.interpreter;

import org.w3c.dom.Element;

public class Schema {
	static final String TAG_CALLSERVICE = "callservice";
	static final String TAG_CALLCONTROLLER = "callcontroller";
	static final String TAG_CONTEXT = "context";
	static final String TAG_EXCELTEMPLATE = "ExcelTemplate";
	static final String TAG_FORMATTERSERVER = "formatterServer";
	static final String TAG_FORMATTER = "formatter";
	static final String TAG_LOOP = "loop";
	static final String TAG_LIST = "list";
	static final String TAG_RESPONSE = "response";
	static final String TAG_SQL = "sql";
	static final String TAG_TABLE = "table";
	static final String TAG_MERGE = "merge";
	static final String TAG_CALL = "call";
	static final String TAG_IF = "if";
	public static boolean isCallService(Element e){
		return TAG_CALLSERVICE.equals(e.getTagName());
	}
	
	public static boolean isCallController(Element e){
		return TAG_CALLCONTROLLER.equals(e.getTagName());
	}
	
	public static boolean isContext(Element e){
		return TAG_CONTEXT.equals(e.getTagName());
	}
	
	public static boolean isExcelTemplate(Element e){
		return TAG_EXCELTEMPLATE.equals(e.getTagName());
	}
	
	public static boolean isFormatterServer(Element e){
		return TAG_FORMATTERSERVER.equals(e.getTagName());
	}
	
	public static boolean isFormatter(Element e){
		return TAG_FORMATTER.equals(e.getTagName());
	}
	
	public static boolean isList(Element e){
		return TAG_LIST.equals(e.getTagName());
	}
	
	public static boolean isResponse(Element e){
		return TAG_RESPONSE.equals(e.getTagName());
	}
	
	public static boolean isSql(Element e){
		return TAG_SQL.equals(e.getTagName());
	}
	
	public static boolean isTable(Element e){
		return TAG_TABLE.equals(e.getTagName());
	}
	
	public static boolean isMerge(Element e){
		return TAG_MERGE.equals(e.getTagName());
	}
	
	public static boolean isCall(Element e){
		return TAG_CALL.equals(e.getTagName());
	}
	
	public static boolean isIf(Element e){
		return TAG_IF.equals(e.getTagName());
	}
	public static boolean isLoop(Element e){
		return TAG_LOOP.equals(e.getTagName());
	}
	
}
