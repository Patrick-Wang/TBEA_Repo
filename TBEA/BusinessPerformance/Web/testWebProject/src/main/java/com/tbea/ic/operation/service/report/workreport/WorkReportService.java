package com.tbea.ic.operation.service.report.workreport;

import java.sql.Date;
import java.util.List;


public interface WorkReportService {

	List<Double[]> getJydwztzb(Integer compId, Date date);
}
