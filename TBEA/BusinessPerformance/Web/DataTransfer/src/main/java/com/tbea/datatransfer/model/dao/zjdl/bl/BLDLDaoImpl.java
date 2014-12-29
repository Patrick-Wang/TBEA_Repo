package com.tbea.datatransfer.model.dao.zjdl.bl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjdl.BLDL;

//@Repository
@Transactional("transactionManagerdl")
public class BLDLDaoImpl extends AbstractReadOnlyDaoImpl<BLDL> implements
		BLDLDao {

	@Override
	@PersistenceContext(unitName = "dlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BLDL> getAllBLDL() {
		String sql = "From BLDL";
		Query query = getEntityManager().createQuery(sql);
		List<BLDL> resultList = query.getResultList();
		return resultList;
	}

}
