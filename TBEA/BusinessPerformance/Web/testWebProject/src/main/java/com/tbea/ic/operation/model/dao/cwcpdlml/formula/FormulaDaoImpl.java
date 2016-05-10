package com.tbea.ic.operation.model.dao.cwcpdlml.formula;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.cwcpdlml.FormulaEntity;



@Repository(FormulaDaoImpl.NAME)
@Transactional("transactionManager")
public class FormulaDaoImpl extends AbstractReadWriteDaoImpl<FormulaEntity> implements FormulaDao {
	public final static String NAME = "FormulaDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public FormulaEntity getByCpfl(Integer cpid) {
		Query q = this.getEntityManager().createQuery("from FormulaEntity where cpid = :cpid");
		q.setParameter("cpid", cpid);
		List<FormulaEntity> list = q.getResultList();
		if (list.isEmpty()){
			return null;
		}
		return list.get(0);
	}
}
