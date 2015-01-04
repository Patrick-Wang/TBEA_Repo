package com.tbea.datatransfer.model.dao.zjtb.fkfs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjtb.FKFSBYQNWTB;

@Transactional("transactionManagertb2")
public class FKFSBYQNWTBDaoImpl extends AbstractReadOnlyDaoImpl<FKFSBYQNWTB>
		implements FKFSBYQNWTBDao {

	@Override
	@PersistenceContext(unitName = "tbDB2")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FKFSBYQNWTB> getAllFKFSBYQNWTB() {
		String sql = "From FKFSBYQNWTB";
		Query query = getEntityManager().createQuery(sql);
		List<FKFSBYQNWTB> resultList = query.getResultList();
		return resultList;
	}

}
