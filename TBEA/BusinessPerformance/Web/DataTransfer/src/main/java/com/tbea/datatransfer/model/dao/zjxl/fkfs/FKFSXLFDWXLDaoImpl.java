package com.tbea.datatransfer.model.dao.zjxl.fkfs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjxl.FKFSXLFDWXL;

@Transactional("transactionManagerxl")
public class FKFSXLFDWXLDaoImpl extends AbstractReadOnlyDaoImpl<FKFSXLFDWXL>
		implements FKFSXLFDWXLDao {

	@Override
	@PersistenceContext(unitName = "xlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FKFSXLFDWXL> getAllFKFSXLFDW() {
		String sql = "From FKFSXLFDWXL";
		Query query = getEntityManager().createQuery(sql);
		List<FKFSXLFDWXL> resultList = query.getResultList();
		return resultList;
	}

}
