package com.tbea.ic.operation.service.yszkpzjh;

import java.sql.Date;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.yszkpzjh.YSZKPZJHBean;

public interface YSZKPZJHService {

	YSZKPZJHBean getYszkpzjhData(Date d, Company comp);

	Date getLatestDate();

}
