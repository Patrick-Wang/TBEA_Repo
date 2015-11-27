package com.tbea.ic.operation.model.dao.ysdaily;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.entity.jygk.YSDAILY;

public interface YSDAILYDao {

	YSDAILY getYsdaily(Date date, DWXX dwxx);

	void update(YSDAILY daily);

	Double getJzydyszkzmye(Date start, Date end, List<Company> companies);

	Double getZqbc(Date start, Date end, List<Company> companies);

	Double getQbbc(Date start, Date end, List<Company> companies);

	Double getYhkzkjyshkje(Date start, Date end, List<Company> companies);

	Double getJrhk(Date start, Date end, List<Company> companies);

	Double getGdwzxzdhkjh(Date start, Date end, List<Company> companies);

	Double getJtxdydzjhlzb(Date start, Date end, List<Company> companies);

}
