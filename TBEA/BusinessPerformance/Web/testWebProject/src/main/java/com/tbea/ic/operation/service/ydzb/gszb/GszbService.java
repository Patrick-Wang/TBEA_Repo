package com.tbea.ic.operation.service.ydzb.gszb;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.Account;

public interface GszbService {
	List<String[]> getGsztzb(Date date);
	List<String[]> getSrqy(Date date);
	List<String[]> getGcyzb(Date date);
	List<String[]> getCompanyTop5zb(GSZB gszb, Date date);
	
	List<String[]> getFirstSeasonPredictionZBsOverview(Date date, List<Company> coms);
	List<String[]> getGsFirstSeasonPredictionZBsOverview(Date date);
	List<String[]> getGcyFirstSeasonPredictionZBs(Date date);
	List<String[]> getGdwFirstSeasonPredictionZBs(GSZB gszb, Date d);
	
	List<String[]> getSecondSeasonPredictionZBsOverview(Date date, List<Company> coms);
	List<String[]> getGsSecondSeasonPredictionZBsOverview(Date date);
	List<String[]> getGcySecondSeasonPredictionZBs(Date date);
	List<String[]> getGdwSecondSeasonPredictionZBs(GSZB gszb, Date d);

	List<String[]> getJDZBMY(Date date, List<Company> coms);
	List<String[]> getGsJDZBMY(Date date);
	List<String[]> getGcyJDZBMY(Date date);
	List<String[]> getGdwJDZBMY(GSZB gszb, Date d);

	List<String[]> getGdwzb(Date d, List<Company> comps);
	List<Company> getCompanies(Account account);
	List<String[]> getLrzeRank(Date date);	
}
