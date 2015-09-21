package com.tbea.ic.operation.model.dao.market.projectInfo;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.MktProjectInfo;

public interface MktProjectInfoDao {

	void update(MktProjectInfo mktObject);

	List<MktProjectInfo> getData(String companyName);

	MktProjectInfo getById(String projectNo);

	List<MktProjectInfo> getCarryDownProjectInfo(Date dStart, Date dEnd);

	List<MktProjectInfo> getData(String companyName, Integer year);

	void remove(String key);
}
