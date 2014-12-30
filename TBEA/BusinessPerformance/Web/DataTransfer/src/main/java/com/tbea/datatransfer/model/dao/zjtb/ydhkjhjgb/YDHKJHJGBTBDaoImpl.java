package com.tbea.datatransfer.model.dao.zjtb.ydhkjhjgb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjtb.YDHKJHJGBTB;

@Transactional("transactionManagertb2")
public class YDHKJHJGBTBDaoImpl extends AbstractReadOnlyDaoImpl<YDHKJHJGBTB> implements
		YDHKJHJGBTBDao {

	@Override
	@PersistenceContext(unitName = "tbDB2")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YDHKJHJGBTB> getAllYDHKJHJGBTB() {
		String sql = "From YDHKJHJGBTB";
		Query query = getEntityManager().createQuery(sql);
		List<YDHKJHJGBTB> resultList = query.getResultList();
		return resultList;
	}

}
