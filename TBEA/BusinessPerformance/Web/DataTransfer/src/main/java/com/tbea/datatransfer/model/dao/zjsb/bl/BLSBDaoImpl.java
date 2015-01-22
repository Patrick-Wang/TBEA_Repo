package com.tbea.datatransfer.model.dao.zjsb.bl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjsb.BLSB;
import com.tbea.datatransfer.model.entity.zjtb.BLTB;

//@Repository
@Transactional("transactionManagersb")
public class BLSBDaoImpl extends AbstractReadOnlyDaoImpl<BLSB> implements
		BLSBDao {

	@Override
	@PersistenceContext(unitName = "sbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<BLSB> getAllBLSB() {
		String sql = "From BLSB";
		Query query = getEntityManager().createQuery(sql);
		List<BLSB> resultList = query.getResultList();
		return resultList;
	}

}
