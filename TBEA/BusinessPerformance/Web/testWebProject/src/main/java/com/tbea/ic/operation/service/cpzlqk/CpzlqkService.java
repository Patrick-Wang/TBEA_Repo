package com.tbea.ic.operation.service.cpzlqk;

import java.sql.Date;

import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;

public interface CpzlqkService {

	ErrorCode approve(Date d, Company company, ZBStatus status);

//	ZBStatus getZltjjgStatus(Date d, Company company);
//	
//	ZBStatus getByqBhgwtmxStatus(Date d, Company company);
//
//	ZBStatus getPdBhgwtmxStatus(Date d, Company company);
//
//	ZBStatus getXlBhgwtmxStatus(Date d, Company company);

	ZBStatus getCpzlqkStatus(Date d, Company company);
	
	ErrorCode approveNwbzlqk(Date d, Company company, ZBStatus status);

	ZBStatus getNwbzlwtStatus(Date d, Company company);
}
