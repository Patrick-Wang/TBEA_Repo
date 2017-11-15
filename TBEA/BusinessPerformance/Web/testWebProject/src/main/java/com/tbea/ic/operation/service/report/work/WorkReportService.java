package com.tbea.ic.operation.service.report.work;

import java.sql.Date;
import java.util.List;


public interface WorkReportService {

	List<Double[]> getJydwztzb(Integer compId, Date date);

	List<Double[]> getLrsrzb(Integer compId, Date date);
}
