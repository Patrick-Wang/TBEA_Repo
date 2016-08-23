package com.tbea.ic.operation.reportframe.component.controller;

import com.tbea.ic.operation.reportframe.component.entity.Context;


public interface Scheduler {
	void onSchedule(Context context, Controller controller);
}
