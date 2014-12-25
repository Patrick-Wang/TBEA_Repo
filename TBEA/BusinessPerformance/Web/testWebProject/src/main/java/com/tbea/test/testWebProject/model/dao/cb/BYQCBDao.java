package com.tbea.test.testWebProject.model.dao.cb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.model.entity.CBBYQTBDD;
import com.tbea.test.testWebProject.model.entity.CBBYQWGDD;
import com.tbea.test.testWebProject.model.entity.CBBYQZXDD;


public interface BYQCBDao {
	List<CBBYQTBDD> getTbdd();
	List<CBBYQZXDD> getZxdd();
	List<CBBYQWGDD> getWgdd();
	CBBYQTBDD getTbddById(Integer tbcpbh);
	CBBYQZXDD getZxddById(Integer zxcpbh);
}
