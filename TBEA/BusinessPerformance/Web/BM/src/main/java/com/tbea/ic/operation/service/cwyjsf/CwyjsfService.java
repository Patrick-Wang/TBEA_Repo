package com.tbea.ic.operation.service.cwyjsf;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;

public interface CwyjsfService {

	void importFromNC(Date d, List<Company> comps);


}
