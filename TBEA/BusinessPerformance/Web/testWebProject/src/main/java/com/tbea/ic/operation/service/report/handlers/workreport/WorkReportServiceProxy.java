package com.tbea.ic.operation.service.report.handlers.workreport;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.service.report.workreport.WorkReportService;


public class WorkReportServiceProxy implements WorkReportService{

	WorkReportService stub;
	
	
	public WorkReportServiceProxy(WorkReportService stub) {
		super();
		this.stub = stub;
	}


	public List<Double[]> getJydwztzb(Integer compId, Date date){
		return stub.getJydwztzb(compId, date);
	}
}
