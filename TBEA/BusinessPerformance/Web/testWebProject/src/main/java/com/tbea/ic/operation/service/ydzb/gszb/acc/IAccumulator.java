package com.tbea.ic.operation.service.ydzb.gszb.acc;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;

public interface IAccumulator {
	List<Double> compute(int col, Date start, Date end, List<Integer> zbs, List<Company> companies);
}
