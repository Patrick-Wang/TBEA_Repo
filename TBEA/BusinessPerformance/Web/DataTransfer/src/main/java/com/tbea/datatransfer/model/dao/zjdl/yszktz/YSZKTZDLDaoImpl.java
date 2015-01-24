package com.tbea.datatransfer.model.dao.zjdl.yszktz;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjxl.YSZKTZXL;

@Transactional("transactionManagerdl")
public class YSZKTZDLDaoImpl extends AbstractReadOnlyDaoImpl<YSZKTZXL>
		implements YSZKTZDLDao {

	@Override
	@PersistenceContext(unitName = "dlDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YSZKTZXL> getAllYSZKTZ() {
		// TODO
//		String sql = "From YSZKTZDL Where DateDiff(dd,gxrq,getDate())<=2";
		String sql = "From YSZKTZXL";
		Query query = getEntityManager().createQuery(sql);
		List<YSZKTZXL> resultList = query.getResultList();
		return resultList;
	}

}
