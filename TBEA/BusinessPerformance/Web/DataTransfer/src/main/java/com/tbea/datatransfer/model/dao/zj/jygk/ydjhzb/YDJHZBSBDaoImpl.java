package com.tbea.datatransfer.model.dao.zj.jygk.ydjhzb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zj.jygk.YDJHZB;

@Transactional("transactionManagersb")
public class YDJHZBSBDaoImpl extends AbstractReadOnlyDaoImpl<YDJHZB> implements
		YDJHZBDao {

	@Override
	@PersistenceContext(unitName = "sbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YDJHZB> getAllYDJHZB() {
		String sql = "From YDJHZB";
		Query query = getEntityManager().createQuery(sql);
		List<YDJHZB> resultList = query.getResultList();
		return resultList;
	}

}
