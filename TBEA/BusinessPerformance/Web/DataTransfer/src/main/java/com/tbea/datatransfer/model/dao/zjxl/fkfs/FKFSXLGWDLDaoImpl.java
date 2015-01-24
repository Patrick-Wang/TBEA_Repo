package com.tbea.datatransfer.model.dao.zjxl.fkfs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjxl.FKFSXLGWXL;

@Transactional("transactionManagerdl")
public class FKFSXLGWDLDaoImpl extends AbstractReadOnlyDaoImpl<FKFSXLGWXL>
		implements FKFSXLGWXLDao {

	@Override
	@PersistenceContext(unitName = "dlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FKFSXLGWXL> getAllFKFSXLGW() {
		String sql = "From FKFSXLGWXL";
		Query query = getEntityManager().createQuery(sql);
		List<FKFSXLGWXL> resultList = query.getResultList();
		return resultList;
	}

}
