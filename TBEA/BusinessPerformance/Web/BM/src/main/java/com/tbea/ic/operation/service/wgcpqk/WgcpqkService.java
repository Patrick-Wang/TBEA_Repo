package com.tbea.ic.operation.service.wgcpqk;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;



public interface WgcpqkService {
	void importByqFromNC(Date d, List<Company> comps);

	void importXlFromNC(Date d, List<Company> comps);


}
