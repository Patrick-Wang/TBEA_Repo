package com.tbea.ic.greet.model.dao.hruser;

import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.ic.greet.model.entity.Account;
import com.tbea.ic.greet.model.entity.HrUser;

public interface HrUserDao {

	HrUser getByName(String name);

}
