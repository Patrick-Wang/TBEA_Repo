package com.tbea.datatransfer.model.dao.zjtb.ydsjhkqk;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjtb.YDSJHKQKTB;

@Transactional("transactionManagertb2")
public class YDSJHKQKTBDaoImpl extends AbstractReadOnlyDaoImpl<YDSJHKQKTB> implements
		YDSJHKQKTBDao {

	@Override
	@PersistenceContext(unitName = "tbDB2")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YDSJHKQKTB> getAllYDSJHKQKTB() {
		String sql = "From YDSJHKQKTB";
		Query query = getEntityManager().createQuery(sql);
		List<YDSJHKQKTB> resultList = query.getResultList();
		return resultList;
	}

}