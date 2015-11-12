package com.tbea.ic.greet.model.dao.cameluser;

import com.tbea.ic.greet.model.entity.CamelUser;

public interface CamelUserDao {

	CamelUser getByName(String name);

}
