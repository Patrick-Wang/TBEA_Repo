package com.tbea.datatransfer.model.dao.zjsb.tbbzjxx;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjsb.TBBZJXXSB;
import com.tbea.datatransfer.model.entity.zjtb.TBBZJXXTB;

@Transactional("transactionManagersb")
public class TBBZJXXSBDaoImpl extends AbstractReadOnlyDaoImpl<TBBZJXXSB>
		implements TBBZJXXSBDao {

	@Override
	@PersistenceContext(unitName = "sbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TBBZJXXSB> getAllTBBZJXXSB() {
		String sql = "From TBBZJXXSB";
		Query query = getEntityManager().createQuery(sql);
		List<TBBZJXXSB> resultList = query.getResultList();
		return resultList;
	}

}
