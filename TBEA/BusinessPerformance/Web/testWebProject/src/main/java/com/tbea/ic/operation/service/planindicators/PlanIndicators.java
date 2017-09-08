package com.tbea.ic.operation.service.planindicators;

import com.tbea.ic.operation.common.companys.Company;

public interface PlanIndicators {

	Double getYearPlan(Integer zbId, Company comp, int year);

	Double getMonthPlan(Integer zbId, Company comp, int year, int month);


}
