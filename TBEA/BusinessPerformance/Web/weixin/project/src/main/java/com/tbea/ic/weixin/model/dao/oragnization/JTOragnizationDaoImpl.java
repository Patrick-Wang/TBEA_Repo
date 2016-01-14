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


@Repository(JTOragnizationDaoImpl.NAME)
@Transactional("jtTransactionManager")
public class JTOragnizationDaoImpl implements OragnizationDao {
	public final static String NAME = "JTOragnizationDaoImpl";

	@PersistenceContext(unitName = "JTDB")
	EntityManager entityManager;

	@Override
	public OrganizationEntity getByOcode(String pk) {
		Query q = entityManager.createNativeQuery("select v.oname, v.ocode, v.fatherocod from V_comorg v where v.ocode = :pk  and isseal='N'");
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
	public List<OrganizationEntity> getByFatherocod(String fatherPk) {
		Query q = entityManager.createNativeQuery("select v.oname, v.ocode, v.fatherocod from V_comorg v where v.fatherocod = :pk and  isseal='N'");
		q.setParameter("pk", fatherPk);
		SQLQuery sql = q.unwrap(SQLQuery.class);
		sql.setResultTransformer(Transformers.aliasToBean(OrganizationEntity.class));
		return q.getResultList();
	}

	@Override
	public List<OrganizationEntity> getAll() {
		Query q = entityManager.createNativeQuery("select v.oname, v.ocode, v.fatherocod from V_comorg v where  isseal='N'");
		SQLQuery sql = q.unwrap(SQLQuery.class);
		sql.setResultTransformer(Transformers.aliasToBean(OrganizationEntity.class));
		return q.getResultList();
	}
}
