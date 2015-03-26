package com.tbea.datatransfer.model.dao.zj.jygk.yj28zb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zj.jygk.YJ28ZB;

@Transactional("transactionManagersb2")
public class YJ28ZBSBDaoImpl extends AbstractReadOnlyDaoImpl<YJ28ZB> implements
		YJ28ZBDao {

	@Override
	@PersistenceContext(unitName = "sbDB2")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YJ28ZB> getAllYJ28ZB() {
		String sql = "From YJ28ZB";
		Query query = getEntityManager().createQuery(sql);
		List<YJ28ZB> resultList = query.getResultList();
		return resultList;
	}

}
