package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.entity.jygk.ZBXX;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxCpylspHqlyddzl;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class ZzyDWXXDaoImpl extends AbstractReadWriteDaoImpl<DWXX> implements ZzyDWXXDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public DWXX getDwxx(int id) {
		Query q = this.getEntityManager().createQuery("from DWXX where id=:id");
		q.setParameter("id", id);
		List<DWXX> DWXXList = q.getResultList();
		if (!DWXXList.isEmpty()){
			return DWXXList.get(0);
		}
		return null;
	}	
}
