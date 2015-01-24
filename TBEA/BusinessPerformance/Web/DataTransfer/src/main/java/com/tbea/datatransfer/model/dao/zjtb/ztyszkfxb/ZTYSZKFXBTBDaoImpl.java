package com.tbea.datatransfer.model.dao.zjtb.ztyszkfxb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjbyq.ZTYSZKFXBBYQ;

@Transactional("transactionManagertb2")
public class ZTYSZKFXBTBDaoImpl extends AbstractReadOnlyDaoImpl<ZTYSZKFXBBYQ> implements
		ZTYSZKFXBTBDao {

	@Override
	@PersistenceContext(unitName = "tbDB2")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ZTYSZKFXBBYQ> getAllZTYSZKFXB() {
		String sql = "From ZTYSZKFXBBYQ";
		Query query = getEntityManager().createQuery(sql);
		List<ZTYSZKFXBBYQ> resultList = query.getResultList();
		return resultList;
	}

}
