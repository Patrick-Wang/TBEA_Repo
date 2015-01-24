package com.tbea.datatransfer.model.dao.zjxl.ydhkjhjgb;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadOnlyDaoImpl;

import com.tbea.datatransfer.model.entity.zjxl.YDHKJHJGBXL;

@Transactional("transactionManagerll")
public class YDHKJHJGBLLDaoImpl extends AbstractReadOnlyDaoImpl<YDHKJHJGBXL>
		implements YDHKJHJGBXLDao {

	@Override
	@PersistenceContext(unitName = "llDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<YDHKJHJGBXL> getAllYDHKJHJGB() {
		String sql = "From YDHKJHJGBXL";
		Query query = getEntityManager().createQuery(sql);
		List<YDHKJHJGBXL> resultList = query.getResultList();
		return resultList;
	}

}
