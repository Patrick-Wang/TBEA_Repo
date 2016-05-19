package com.tbea.ic.operation.service.cwgbjyxxjl;


import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;

public interface CwgbjyxxjlService {

	void importFromNC(Date d, List<Company> comps);
}
