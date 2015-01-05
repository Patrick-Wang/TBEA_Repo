package com.tbea.test.testWebProject.service.yszkpzjh;

import java.sql.Date;

import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.controller.servlet.yszkpzjh.YSZKPZJHBean;

public interface YSZKPZJHService {

	YSZKPZJHBean getYszkpzjhData(Date d, Company comp);

	Date getLatestDate();

}
