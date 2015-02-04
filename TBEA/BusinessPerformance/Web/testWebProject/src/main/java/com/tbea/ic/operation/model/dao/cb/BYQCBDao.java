package com.tbea.ic.operation.model.dao.cb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.CBBYQTBDD;
import com.tbea.ic.operation.model.entity.CBBYQWGDD;
import com.tbea.ic.operation.model.entity.CBBYQZXDD;


public interface BYQCBDao {
	List<CBBYQTBDD> getTbdd();
	List<CBBYQZXDD> getZxdd();
	List<CBBYQWGDD> getWgdd();
	CBBYQTBDD getTbddById(Integer tbcpbh);
	CBBYQZXDD getZxddById(Integer zxcpbh);
	boolean containsTbCompany(Company company);
	List<Integer> getZxCompany();
	CBBYQWGDD getLatestWgdd();
	List<Integer> getWgCompany();
	List<CBBYQWGDD> getWgdd(Company comp);
}
