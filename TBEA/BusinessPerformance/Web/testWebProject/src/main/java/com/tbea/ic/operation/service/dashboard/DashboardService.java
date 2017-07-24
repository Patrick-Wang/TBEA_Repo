package com.tbea.ic.operation.service.dashboard;

import java.sql.Date;
import java.util.List;

public interface DashboardService {



	public List<Double> getScqyLjzb(Date date);


	public List<Double> getScqyZtydzb(Integer nf);


	public List<String[]> getSbdgnscqye(Date date);


	public List<String[]> getSbdgjscqye(Date date);


	public Double getSbdztqye(Date current);

}
