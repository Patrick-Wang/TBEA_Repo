package com.tbea.ic.operation.reportframe.component;

import com.tbea.ic.operation.reportframe.component.entity.Context;


public interface Component {
	public final static String TRANSACTION = "transaction";
	public final static String REQUEST = "request";
	public final static String RESPONSE = "response";
	public final static String SESSION = "session";
	public final static String CALENDAR = "calendar";
	void run(Context context) throws Exception;
	String getId();
}
