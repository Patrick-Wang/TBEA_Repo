package com.tbea.ic.operation.service.cbfx.nymyywmlfx;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;

public interface NymyywmlfxService {

	List<List<String>> getNymyywmlfx(Date d, Company company);

	void importFromNC(Date d, List<Company> comps);

}
