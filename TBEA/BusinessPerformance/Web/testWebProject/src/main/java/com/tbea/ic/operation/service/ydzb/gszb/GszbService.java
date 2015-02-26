package com.tbea.ic.operation.service.ydzb.gszb;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.GSZB;

public interface GszbService {
	List<String[]> getGsztzb(Date date);
	List<String[]> getGcyzb(Date date);

	List<String[]> getFirstSeasonPredictionZBsOverview(Date date);
	List<String[]> getSrqy(Date date);
	List<String[]> getCompanyTop5zb(GSZB gszb, Date date);

}
