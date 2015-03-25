package com.tbea.datatransfer.model.dao.zj.jygk.yj20zb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zj.jygk.YJ20ZB;

@Transactional("transactionManagersb2")
public class YJ20ZBSBDaoImpl extends AbstractReadOnlyDaoImpl<YJ20ZB> implements
		YJ20ZBDao {

	@Override
	@PersistenceContext(unitName = "sbDB2")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YJ20ZB> getAllYJ20ZB() {
		String sql = "From YJ20ZB";
		Query query = getEntityManager().createQuery(sql);
		List<YJ20ZB> resultList = query.getResultList();
		return resultList;
	}

}
