package com.tbea.datatransfer.model.dao.zjtb.bl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjtb.BLTB;

//@Repository
@Transactional("transactionManagertb")
public class BLTBDaoImpl extends AbstractReadOnlyDaoImpl<BLTB> implements
		BLTBDao {

	@Override
	@PersistenceContext(unitName = "tbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BLTB> getAllBLTB() {
		String sql = "From BLTB";
		Query query = getEntityManager().createQuery(sql);
		List<BLTB> resultList = query.getResultList();
		return resultList;
	}

}
