package com.tbea.datatransfer.model.dao.zjsb.fkfs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjsb.FKFSBYQFDWSB;


@Transactional("transactionManagersb")
public class FKFSBYQFDWSBDaoImpl extends AbstractReadOnlyDaoImpl<FKFSBYQFDWSB>
		implements FKFSBYQFDWSBDao {

	@Override
	@PersistenceContext(unitName = "sbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FKFSBYQFDWSB> getAllFKFSBYQFDWSB() {
		String sql = "From FKFSBYQFDWSB";
		Query query = getEntityManager().createQuery(sql);
		List<FKFSBYQFDWSB> resultList = query.getResultList();
		return resultList;
	}

}
