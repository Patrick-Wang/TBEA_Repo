package com.tbea.datatransfer.model.dao.zjxl.bl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjxl.BLXL;

//@Repository
@Transactional("transactionManagerxl")
public class BLXLDaoImpl extends AbstractReadOnlyDaoImpl<BLXL> implements
		BLXLDao {

	@Override
	@PersistenceContext(unitName = "xlDB")
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
