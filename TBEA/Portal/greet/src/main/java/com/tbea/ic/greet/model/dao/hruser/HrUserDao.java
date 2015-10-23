package com.tbea.ic.greet.model.dao.hruser;

import com.tbea.ic.greet.model.entity.HrUser;

public interface HrUserDao {

	HrUser getByName(String name);

}
