package com.tbea.datatransfer.model.dao.zjdl.fkfs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjdl.FKFSXLGWDL;

@Transactional("transactionManagerdl")
public class FKFSXLGWDLDaoImpl extends AbstractReadOnlyDaoImpl<FKFSXLGWDL>
		implements FKFSXLGWDLDao {

	@Override
	@PersistenceContext(unitName = "dlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FKFSXLGWDL> getAllFKFSXLGWDL() {
		String sql = "From FKFSXLGWDL";
		Query query = getEntityManager().createQuery(sql);
		List<FKFSXLGWDL> resultList = query.getResultList();
		return resultList;
	}

}
