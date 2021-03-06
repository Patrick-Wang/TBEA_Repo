package com.tbea.ic.weixin.model.dao.oragnization;


import java.util.List;

import com.tbea.ic.weixin.model.dao.oragnization.OragnizationDao;
import com.tbea.ic.weixin.model.entity.OrganizationEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository(OragnizationDaoImpl.NAME)
@Transactional("transactionManager")
public class OragnizationDaoImpl implements OragnizationDao {
	public final static String NAME = "OragnizationDaoImpl";

	@PersistenceContext(unitName = "HRDB")
	EntityManager entityManager;

	@Override
	public OrganizationEntity getByOcode(String oname) {
		Query q = entityManager.createNativeQuery("select v.oname, v.ocode,v.fatherocod from V_comorg v where v.oname = :oname where isseal='N'");
		q.setParameter("oname", oname);
		SQLQuery sql = q.unwrap(SQLQuery.class);
		sql.setResultTransformer(Transformers.aliasToBean(OrganizationEntity.class));
		List<OrganizationEntity> rets = q.getResultList();
		if (rets.isEmpty()){
			return null;
		}
		return rets.get(0);
	}

	@Override
	public List<OrganizationEntity> getByFatherocod(String fatherocod) {
		Query q = entityManager.createNativeQuery("select v.oname, v.ocode,v.fatherocod from V_comorg v where v.fatherocod = :fatherocod and isseal='N'");
		q.setParameter("fatherocod", fatherocod);
		SQLQuery sql = q.unwrap(SQLQuery.class);
		sql.setResultTransformer(Transformers.aliasToBean(OrganizationEntity.class));
		return q.getResultList();
	}

	@Override
	public List<OrganizationEntity> getAll() {
		Query q = entityManager.createNativeQuery("select v.oname, v.ocode,v.fatherocod from V_comorg v where isseal='N'");
		SQLQuery sql = q.unwrap(SQLQuery.class);
		sql.setResultTransformer(Transformers.aliasToBean(OrganizationEntity.class));
		return q.getResultList();
	}
}
