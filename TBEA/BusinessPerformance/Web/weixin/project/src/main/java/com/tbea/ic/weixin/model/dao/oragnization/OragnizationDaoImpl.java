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


@Repository
@Transactional("hrTransactionManager")
public class OragnizationDaoImpl implements OragnizationDao {


	@PersistenceContext(unitName = "HRDB")
	EntityManager entityManager;

	@Override
	public OrganizationEntity getByPk(String pk) {
		Query q = entityManager.createNativeQuery("select v.oname, v.pk, v.fatherpk from V_comorg v where v.pk = :pk");
		q.setParameter("pk", pk);
		SQLQuery sql = q.unwrap(SQLQuery.class);
		sql.setResultTransformer(Transformers.aliasToBean(OrganizationEntity.class));
		List<OrganizationEntity> rets = q.getResultList();
		if (rets.isEmpty()){
			return null;
		}
		return rets.get(0);
	}

	@Override
	public List<OrganizationEntity> getByFatherPK(String fatherPk) {
		Query q = entityManager.createNativeQuery("select v.oname, v.pk, v.fatherpk from V_comorg v where v.fatherpk = :pk");
		q.setParameter("pk", fatherPk);
		SQLQuery sql = q.unwrap(SQLQuery.class);
		sql.setResultTransformer(Transformers.aliasToBean(OrganizationEntity.class));
		return q.getResultList();
	}
}
