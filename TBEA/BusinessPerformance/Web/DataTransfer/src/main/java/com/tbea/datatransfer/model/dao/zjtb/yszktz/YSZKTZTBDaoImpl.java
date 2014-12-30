package com.tbea.datatransfer.model.dao.zjtb.yszktz;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjtb.YSZKTZTB;

@Transactional("transactionManagertb")
public class YSZKTZTBDaoImpl extends AbstractReadOnlyDaoImpl<YSZKTZTB>
		implements YSZKTZTBDao {

	@Override
	@PersistenceContext(unitName = "tbDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YSZKTZTB> getAllYSZKTZTB() {
		// TODO
		// gxrq <= 2
//		String sql = "From YSZKTZTB Where DateDiff(dd,gxrq,getDate())<=2";
		String sql = "From YSZKTZTB";
		Query query = getEntityManager().createQuery(sql);
		List<YSZKTZTB> resultList = query.getResultList();
		return resultList;
	}

}
