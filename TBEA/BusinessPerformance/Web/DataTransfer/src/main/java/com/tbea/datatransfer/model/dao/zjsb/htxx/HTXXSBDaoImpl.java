package com.tbea.datatransfer.model.dao.zjsb.htxx;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjsb.HTXXSB;
import com.tbea.datatransfer.model.entity.zjtb.HTXXTB;

@Transactional("transactionManagersb")
public class HTXXSBDaoImpl extends AbstractReadOnlyDaoImpl<HTXXSB> implements
		HTXXSBDao {

	@Override
	@PersistenceContext(unitName = "sbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<HTXXSB> getAllHTXXSB() {
		String sql = "From HTXXSB";
		Query query = getEntityManager().createQuery(sql);
		List<HTXXSB> resultList = query.getResultList();
		return resultList;
	}

}
