package com.tbea.ic.weixin.model.dao.oragnization;

import java.util.List;

import com.tbea.ic.weixin.model.entity.OrganizationEntity;


public interface OragnizationDao {

	OrganizationEntity getByPk(String pk);

	List<OrganizationEntity> getByFatherPK(String fatherPk);

}
