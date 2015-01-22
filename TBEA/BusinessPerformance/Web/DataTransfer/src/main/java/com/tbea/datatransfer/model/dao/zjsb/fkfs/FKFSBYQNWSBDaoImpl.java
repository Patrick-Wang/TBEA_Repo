package com.tbea.datatransfer.model.dao.zjsb.fkfs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjsb.FKFSBYQNWSB;
import com.tbea.datatransfer.model.entity.zjtb.FKFSBYQNWTB;

@Transactional("transactionManagersb")
public class FKFSBYQNWSBDaoImpl extends AbstractReadOnlyDaoImpl<FKFSBYQNWSB>
		implements FKFSBYQNWSBDao {

	@Override
	@PersistenceContext(unitName = "sbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FKFSBYQNWSB> getAllFKFSBYQNWSB() {
		String sql = "From FKFSBYQNWSB";
		Query query = getEntityManager().createQuery(sql);
		List<FKFSBYQNWSB> resultList = query.getResultList();
		return resultList;
	}

}
