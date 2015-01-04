package com.tbea.datatransfer.model.dao.zjdl.fkfs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjdl.FKFSXLNWDL;

@Transactional("transactionManagerdl")
public class FKFSXLNWDLDaoImpl extends AbstractReadOnlyDaoImpl<FKFSXLNWDL>
		implements FKFSXLNWDLDao {

	@Override
	@PersistenceContext(unitName = "dlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FKFSXLNWDL> getAllFKFSXLNWDL() {
		String sql = "From FKFSXLNWDL";
		Query query = getEntityManager().createQuery(sql);
		List<FKFSXLNWDL> resultList = query.getResultList();
		return resultList;
	}

}