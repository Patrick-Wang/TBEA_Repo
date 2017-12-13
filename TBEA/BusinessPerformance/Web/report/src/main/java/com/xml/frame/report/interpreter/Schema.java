package com.xml.frame.report.interpreter;

import org.w3c.dom.Element;

public class Schema {
	public static final String TAG_CALLSERVICE = "callservice";
	public static final String TAG_CALLCONTROLLER = "callcontroller";
	public static final String TAG_CONTEXT = "context";
	public static final String TAG_EXCELTEMPLATE = "ExcelTemplate";
	public static final String TAG_FORMATTERSERVER = "formatterServer";
	public static final String TAG_FORMATTER = "formatter";
	public static final String TAG_LOOP = "loop";
	public static final String TAG_LIST = "list";
	public static final String TAG_RESPONSE = "response";
	public static final String TAG_SQL = "sql";
	public static final String TAG_TABLE = "table";
	public static final String TAG_MERGE = "merge";
	public static final String TAG_CALL = "call";
	public static final String TAG_IF = "if";
	public static final String TAG_ELSE = "else";
	public static final String TAG_ELSEIF = "elseif";
	public static final String TAG_LOG = "log";
	public static final String TAG_COMPONENTS = "components";
	public static final String TAG_CONTROLLER = "controller";
	public static final String TAG_SERVICE = "service";
	public static final String TAG_WORDTEMPLETE = "WordTemplate";
	public static final String TAG_WORDMERGE = "WordMerge";
	public static final String TAG_SCRIPT = "script";
	public static final String TAG_HTTP = "http";
	
	public static boolean isWordTemplete(Element e){
		return TAG_WORDTEMPLETE.equals(e.getTagName());
	}

	public static boolean isWordMerge(Element e){
		return TAG_WORDMERGE.equals(e.getTagName());
	}

	public static boolean isLog(Element e){
		return TAG_LOG.equals(e.getTagName());
	}
	
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
	
	public static boolean isElseIf(Element e){
		return TAG_ELSEIF.equals(e.getTagName());
	}
	public static boolean isElse(Element e){
		return TAG_ELSE.equals(e.getTagName());
	}
	
	public static boolean isLoop(Element e){
		return TAG_LOOP.equals(e.getTagName());
	}
	
	public static boolean isScript(Element e){
		return TAG_SCRIPT.equals(e.getTagName());
	}
	public static boolean isHttp(Element e){
		return TAG_HTTP.equals(e.getTagName());
	}
}
