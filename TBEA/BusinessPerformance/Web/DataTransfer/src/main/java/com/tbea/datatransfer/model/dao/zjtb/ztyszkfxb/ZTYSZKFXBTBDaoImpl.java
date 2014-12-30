package com.tbea.datatransfer.model.dao.zjtb.ztyszkfxb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjtb.ZTYSZKFXBTB;

@Transactional("transactionManagertb2")
public class ZTYSZKFXBTBDaoImpl extends AbstractReadOnlyDaoImpl<ZTYSZKFXBTB> implements
		ZTYSZKFXBTBDao {

	@Override
	@PersistenceContext(unitName = "tbDB2")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ZTYSZKFXBTB> getAllZTYSZKFXBTB() {
		String sql = "From ZTYSZKFXBTB";
		Query query = getEntityManager().createQuery(sql);
		List<ZTYSZKFXBTB> resultList = query.getResultList();
		return resultList;
	}

}
