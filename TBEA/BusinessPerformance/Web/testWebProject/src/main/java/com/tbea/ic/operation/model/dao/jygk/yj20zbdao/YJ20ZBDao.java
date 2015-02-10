package com.tbea.ic.operation.model.dao.jygk.yj20zbdao;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.YJ20ZB;

public interface YJ20ZBDao  extends AbstractReadWriteDao<YJ20ZB> {

	YJ20ZB getZb(Integer zbId, Date date, Company company);

	List<YJ20ZB> getZbs(Date date, Company company);

	List<YJ20ZB> getApprovedZbs(List<Date> dateList, List<Company> comps);

	List<YJ20ZB> getUnapprovedZbs(List<Date> dateList, List<Company> comps);

}
