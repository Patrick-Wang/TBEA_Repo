package com.tbea.datatransfer.model.dao.zjsb.ydsjhkqk;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjsb.YDSJHKQKSB;
import com.tbea.datatransfer.model.entity.zjtb.YDSJHKQKTB;

@Transactional("transactionManagertb2")
public class YDSJHKQKSBDaoImpl extends AbstractReadOnlyDaoImpl<YDSJHKQKSB> implements
		YDSJHKQKSBDao {

	@Override
	@PersistenceContext(unitName = "sbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YDSJHKQKSB> getAllYDSJHKQKSB() {
		String sql = "From YDSJHKQKSB";
		Query query = getEntityManager().createQuery(sql);
		List<YDSJHKQKSB> resultList = query.getResultList();
		return resultList;
	}

}
