package com.tbea.ic.weixin.model.dao.oragnization;

import java.util.List;

import com.tbea.ic.weixin.model.entity.OrganizationEntity;


public interface OragnizationDao {

	OrganizationEntity getByOcode(String ocode);

	List<OrganizationEntity> getByFatherocod(String fatherocod);
	
	List<OrganizationEntity> getAll();

}
