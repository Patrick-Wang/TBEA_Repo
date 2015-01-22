package com.tbea.datatransfer.model.dao.zjsb.ztyszkfxb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjsb.ZTYSZKFXBSB;
import com.tbea.datatransfer.model.entity.zjtb.ZTYSZKFXBTB;

@Transactional("transactionManagersb")
public class ZTYSZKFXBSBDaoImpl extends AbstractReadOnlyDaoImpl<ZTYSZKFXBSB> implements
		ZTYSZKFXBSBDao {

	@Override
	@PersistenceContext(unitName = "sbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ZTYSZKFXBSB> getAllZTYSZKFXBSB() {
		String sql = "From ZTYSZKFXBSB";
		Query query = getEntityManager().createQuery(sql);
		List<ZTYSZKFXBSB> resultList = query.getResultList();
		return resultList;
	}

}
