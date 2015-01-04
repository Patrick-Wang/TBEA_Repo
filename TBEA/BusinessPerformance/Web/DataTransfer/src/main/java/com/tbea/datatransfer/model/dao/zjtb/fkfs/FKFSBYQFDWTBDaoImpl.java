package com.tbea.datatransfer.model.dao.zjtb.fkfs;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjtb.FKFSBYQFDWTB;

@Transactional("transactionManagertb2")
public class FKFSBYQFDWTBDaoImpl extends AbstractReadOnlyDaoImpl<FKFSBYQFDWTB>
		implements FKFSBYQFDWTBDao {

	@Override
	@PersistenceContext(unitName = "tbDB2")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FKFSBYQFDWTB> getAllFKFSBYQFDWTB() {
		String sql = "From FKFSBYQFDWTB";
		Query query = getEntityManager().createQuery(sql);
		List<FKFSBYQFDWTB> resultList = query.getResultList();
		return resultList;
	}

}
