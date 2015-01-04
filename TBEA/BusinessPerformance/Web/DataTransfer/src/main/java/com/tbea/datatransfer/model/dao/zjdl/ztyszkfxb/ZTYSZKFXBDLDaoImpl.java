package com.tbea.datatransfer.model.dao.zjdl.ztyszkfxb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjdl.ZTYSZKFXBDL;

@Transactional("transactionManagerdl")
public class ZTYSZKFXBDLDaoImpl extends AbstractReadOnlyDaoImpl<ZTYSZKFXBDL> implements
		ZTYSZKFXBDLDao {

	@Override
	@PersistenceContext(unitName = "dlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ZTYSZKFXBDL> getAllZTYSZKFXBDL() {
		String sql = "From ZTYSZKFXBDL";
		Query query = getEntityManager().createQuery(sql);
		List<ZTYSZKFXBDL> resultList = query.getResultList();
		return resultList;
	}

}