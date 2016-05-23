package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyDwReferBglx;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class ReferBglxDaoImpl extends AbstractReadWriteDaoImpl<JygkZzyDwReferBglx> implements ReferBglxDao{
	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public List<JygkZzyDwReferBglx> getDataList(List<Company> comps) {
		String dwids="";
		for (Company comp : comps){
			dwids += "," + comp.getId();
		}
		dwids=dwids.substring(1);
		Query q = this.getEntityManager().createQuery("from JygkZzyDwReferBglx where dwid in (" + Util.toString(comps) + ")");
		return q.getResultList();
	}	
}
