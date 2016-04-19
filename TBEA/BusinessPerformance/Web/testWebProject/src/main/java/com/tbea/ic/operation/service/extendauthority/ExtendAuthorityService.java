package com.tbea.ic.operation.service.extendauthority;

import java.sql.Date;
import java.util.List;

import net.sf.json.JSONArray;

import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.model.entity.jygk.Account;

public interface ExtendAuthorityService {

	Boolean hasPriceLibAuthority(Account account);

	
}
