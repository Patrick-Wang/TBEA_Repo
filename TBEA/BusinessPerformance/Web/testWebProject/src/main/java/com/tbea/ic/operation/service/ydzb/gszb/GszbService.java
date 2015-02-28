package com.tbea.ic.operation.service.ydzb.gszb;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;

public interface GszbService {
	List<String[]> getGsztzb(Date date);
	List<String[]> getGcyzb(Date date);

	List<String[]> getFirstSeasonPredictionZBsOverview(Date date);
	List<String[]> getSecondSeasonPredictionZBsOverview(Date date);
	List<String[]> getSrqy(Date date);
	List<String[]> getCompanyTop5zb(GSZB gszb, Date date);

	List<String[]> getJDZBMY(Date date);
	List<String[]> getGcyJDZBMY(Date date);
	List<String[]> getGdwzb(Date d, List<Company> comps);
}
