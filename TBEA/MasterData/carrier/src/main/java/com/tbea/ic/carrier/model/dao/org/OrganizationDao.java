package com.tbea.ic.carrier.model.dao.org;

import java.util.List;

import com.tbea.ic.carrier.model.entity.Organization;

public interface OrganizationDao {

	void update(Organization orgEntity);

	List<Organization> getByName(String name);

	List<Organization> getByNameExactly(String compName);

}
