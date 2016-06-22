package com.tbea.ic.operation.service.ydzb.gszb;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.Account;
import com.tbea.ic.operation.service.util.pipe.core.configurator.IPipeConfigurator;
import com.tbea.ic.operation.service.ydzb.pipe.configurator.ConfiguratorFactory;

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

	List<String[]> getThirdSeasonPredictionZBsOverview(Date date, List<Company> coms);
	List<String[]> getGsThirdSeasonPredictionZBsOverview(Date date);
	List<String[]> getGcyThirdSeasonPredictionZBs(Date date);
	List<String[]> getGdwThirdSeasonPredictionZBs(GSZB gszb, Date d);

	List<String[]> getGdwzb(Date d, List<Company> comps);
	List<Company> getCompanies(Account account);
	
	List<String[]> getGsztzbNC(Date d, com.tbea.ic.operation.service.nczb.pipe.configurator.ConfiguratorFactory config);
	List<Double[]> getCorpIndicators(Date date);
}
