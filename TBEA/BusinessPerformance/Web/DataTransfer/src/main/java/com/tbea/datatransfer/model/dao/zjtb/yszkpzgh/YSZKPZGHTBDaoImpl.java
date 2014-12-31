package com.tbea.datatransfer.model.dao.zjtb.yszkpzgh;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjtb.YSZKPZGHTB;

@Transactional("transactionManagertb2")
public class YSZKPZGHTBDaoImpl extends AbstractReadOnlyDaoImpl<YSZKPZGHTB>
		implements YSZKPZGHTBDao {

	@Override
	@PersistenceContext(unitName = "tbDB2")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YSZKPZGHTB> getAllYSZKPZGHTB() {
		String sql = "From YSZKPZGHTB";
		Query query = getEntityManager().createQuery(sql);
		List<YSZKPZGHTB> resultList = query.getResultList();
		return resultList;
	}

}
