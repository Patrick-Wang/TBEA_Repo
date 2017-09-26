package com.xml.frame.report.component.manager;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.xml.frame.report.component.controller.Controller;
import com.xml.frame.report.component.entity.Context;
import com.xml.frame.report.util.QuartzManager;

public class ScheduleJob implements Job {

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		if (null != ComponentManager.getInstance()) {
			Controller controller = ComponentManager.getInstance().createController(null,
					arg0.getJobDetail().getName());
			if (null != controller) {
				try {
					ComponentManager.getInstance().scheduler.onSchedule(new Context(), controller);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				QuartzManager.removeJob(arg0.getJobDetail().getName());
				QuartzManager.startJobs();
			}
		}
	}
};