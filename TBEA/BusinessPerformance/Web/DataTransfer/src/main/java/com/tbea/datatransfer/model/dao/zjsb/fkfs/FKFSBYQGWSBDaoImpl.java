package com.tbea.datatransfer.model.dao.zjsb.fkfs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjsb.FKFSBYQGWSB;
import com.tbea.datatransfer.model.entity.zjtb.FKFSBYQGWTB;

@Transactional("transactionManagersb")
public class FKFSBYQGWSBDaoImpl extends AbstractReadOnlyDaoImpl<FKFSBYQGWSB>
		implements FKFSBYQGWSBDao {

	@Override
	@PersistenceContext(unitName = "sbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FKFSBYQGWSB> getAllFKFSBYQGWSB() {
		String sql = "From FKFSBYQGWSB";
		Query query = getEntityManager().createQuery(sql);
		List<FKFSBYQGWSB> resultList = query.getResultList();
		return resultList;
	}

}
