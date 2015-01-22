package com.tbea.datatransfer.model.dao.zjsb.yszkpzgh;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjsb.YSZKPZGHSB;
import com.tbea.datatransfer.model.entity.zjtb.YSZKPZGHTB;

@Transactional("transactionManagersb")
public class YSZKPZGHSBDaoImpl extends AbstractReadOnlyDaoImpl<YSZKPZGHSB>
		implements YSZKPZGHSBDao {

	@Override
	@PersistenceContext(unitName = "sbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YSZKPZGHSB> getAllYSZKPZGHSB() {
		String sql = "From YSZKPZGHSB";
		Query query = getEntityManager().createQuery(sql);
		List<YSZKPZGHSB> resultList = query.getResultList();
		return resultList;
	}

}
