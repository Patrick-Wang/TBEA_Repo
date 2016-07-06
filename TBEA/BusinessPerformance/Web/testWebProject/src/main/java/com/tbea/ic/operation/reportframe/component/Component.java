package com.tbea.ic.operation.reportframe.component;

import com.tbea.ic.operation.reportframe.component.entity.Context;


public interface Component {
	void run(Context context);
	String getId();
}
