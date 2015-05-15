package com.tbea.ic.operation.service.ydzb.rank;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.GSZB;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.Account;

public interface RankService {

	List<String[]> getLrzeRank(Date date);	
}
