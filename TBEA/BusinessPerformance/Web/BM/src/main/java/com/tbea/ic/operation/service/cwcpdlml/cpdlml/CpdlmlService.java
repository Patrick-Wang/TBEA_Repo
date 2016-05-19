package com.tbea.ic.operation.service.cwcpdlml.cpdlml;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;

public interface CpdlmlService {

	List<List<String>> getCpdlml(Date d);


}
