package com.tbea.datatransfer.model.dao.zjxl.fkfs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjxl.FKFSXLNWXL;

@Transactional("transactionManagerll")
public class FKFSXLNWLLDaoImpl extends AbstractReadOnlyDaoImpl<FKFSXLNWXL>
		implements FKFSXLNWXLDao {

	@Override
	@PersistenceContext(unitName = "llDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FKFSXLNWXL> getAllFKFSXLNW() {
		String sql = "From FKFSXLNWXL";
		Query query = getEntityManager().createQuery(sql);
		List<FKFSXLNWXL> resultList = query.getResultList();
		return resultList;
	}

}
