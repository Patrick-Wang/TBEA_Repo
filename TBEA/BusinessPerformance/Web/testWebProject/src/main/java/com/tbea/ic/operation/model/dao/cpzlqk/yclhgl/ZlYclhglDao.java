package com.tbea.ic.operation.model.dao.cpzlqk.yclhgl;
import java.sql.Date;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.cpzlqk.XlBhgwtmxEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.ZlYclhglEntity;



public interface ZlYclhglDao extends AbstractReadWriteDao<ZlYclhglEntity> {

	ZlYclhglEntity getFirst(Date d, Company company);

}
