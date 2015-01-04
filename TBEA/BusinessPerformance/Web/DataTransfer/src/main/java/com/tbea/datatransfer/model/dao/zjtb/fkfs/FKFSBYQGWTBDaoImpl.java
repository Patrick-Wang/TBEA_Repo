package com.tbea.datatransfer.model.dao.zjtb.fkfs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjtb.FKFSBYQGWTB;

@Transactional("transactionManagertb2")
public class FKFSBYQGWTBDaoImpl extends AbstractReadOnlyDaoImpl<FKFSBYQGWTB>
		implements FKFSBYQGWTBDao {

	@Override
	@PersistenceContext(unitName = "tbDB2")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FKFSBYQGWTB> getAllFKFSBYQGWTB() {
		String sql = "From FKFSBYQGWTB";
		Query query = getEntityManager().createQuery(sql);
		List<FKFSBYQGWTB> resultList = query.getResultList();
		return resultList;
	}

}
