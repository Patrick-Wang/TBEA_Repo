package com.tbea.ic.operation.service.cpzlqk.byqcpycssbhgxxfb;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.cpzlqk.ByqBhgType;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;

public interface ByqcpycssbhgxxfbService {

	List<List<String>> getByqcpycssbhgxxfb(Date d, YDJDType yjType,
			ByqBhgType bhgType);

	List<String> getBhglbs();

	List<List<String>> getByqcpycssbhgxxfb(Date d, YDJDType yjType,
			ByqBhgType bhgType, Company company);


}
