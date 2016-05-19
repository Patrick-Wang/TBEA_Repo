package com.tbea.ic.operation.model.dao.jygk.dwxx;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.entity.jygk.ZBXX;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class DWXXDaoImpl extends AbstractReadWriteDaoImpl<DWXX> implements DWXXDao{
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<DWXX> getDwxxs(List<Company> comps) {
		Query q = this.getEntityManager().createQuery("from DWXX where id in (" + Util.toString(comps) + ")");
		return q.getResultList();
	}

	@Override
	public DWXX getByName(String compName) {
		Query q = this.getEntityManager().createQuery("from DWXX where name = :name");
		q.setParameter("name", compName);
		List<DWXX> ret = q.getResultList();
		if (ret.isEmpty()){
			return null;
		}
		return ret.get(0);
	}
}
