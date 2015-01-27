package com.tbea.datatransfer.model.dao.zjxl.ztyszkfxb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjxl.ZTYSZKFXBXL;

@Transactional("transactionManagerll")
public class ZTYSZKFXBLLDaoImpl extends AbstractReadOnlyDaoImpl<ZTYSZKFXBXL> implements
		ZTYSZKFXBXLDao {

	@Override
	@PersistenceContext(unitName = "llDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ZTYSZKFXBXL> getAllZTYSZKFXB() {
		String sql = "From ZTYSZKFXBXL";
		Query query = getEntityManager().createQuery(sql);
		List<ZTYSZKFXBXL> resultList = query.getResultList();
		return resultList;
	}

}
