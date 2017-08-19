package com.xml.frame.report.component.controller;

import com.xml.frame.report.component.entity.Context;


public interface Scheduler {
	void onSchedule(Context context, Controller controller) throws Exception;
}
