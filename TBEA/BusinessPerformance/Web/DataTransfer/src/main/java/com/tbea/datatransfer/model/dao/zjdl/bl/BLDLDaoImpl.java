package com.tbea.datatransfer.model.dao.zjdl.bl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjxl.BLXL;

//@Repository
@Transactional("transactionManagerdl")
public class BLDLDaoImpl extends AbstractReadOnlyDaoImpl<BLXL> implements
		BLDLDao {

	@Override
	@PersistenceContext(unitName = "dlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BLXL> getAllBL() {
		String sql = "From BLXL";
		Query query = getEntityManager().createQuery(sql);
		List<BLXL> resultList = query.getResultList();
		return resultList;
	}

}
