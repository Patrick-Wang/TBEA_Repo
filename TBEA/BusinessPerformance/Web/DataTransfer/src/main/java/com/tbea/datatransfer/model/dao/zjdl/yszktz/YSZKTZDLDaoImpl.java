package com.tbea.datatransfer.model.dao.zjdl.yszktz;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjdl.YSZKTZDL;

@Transactional("transactionManagerdl")
public class YSZKTZDLDaoImpl extends AbstractReadOnlyDaoImpl<YSZKTZDL>
		implements YSZKTZDLDao {

	@Override
	@PersistenceContext(unitName = "dlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YSZKTZDL> getAllYSZKTZDL() {
		// TODO
//		String sql = "From YSZKTZDL Where DateDiff(dd,gxrq,getDate())<=2";
		String sql = "From YSZKTZDL";
		Query query = getEntityManager().createQuery(sql);
		List<YSZKTZDL> resultList = query.getResultList();
		return resultList;
	}

}
