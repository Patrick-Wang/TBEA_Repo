package com.tbea.ic.operation.model.dao.jygk.yj28zbdao;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.YJ28ZB;

public interface YJ28ZBDao  extends AbstractReadWriteDao<YJ28ZB>{

	YJ28ZB getZb(Integer zb, Date date, Company company);

	List<YJ28ZB> getZbs(Date date, Company company);

	List<YJ28ZB> getApprovedZbs(List<Date> dateList, List<Company> comps);

	List<YJ28ZB> getUnapprovedZbs(List<Date> dateList, List<Company> comps);

}
