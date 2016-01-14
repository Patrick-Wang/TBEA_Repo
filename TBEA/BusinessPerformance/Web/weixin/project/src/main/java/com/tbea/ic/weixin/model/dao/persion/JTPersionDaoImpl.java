package com.tbea.ic.weixin.model.dao.persion;


import java.util.List;

import com.tbea.ic.weixin.model.dao.persion.PersionDao;
import com.tbea.ic.weixin.model.entity.PersionEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository(JTPersionDaoImpl.NAME)
@Transactional("jtTransactionManager")
public class JTPersionDaoImpl implements PersionDao {
	public final static String NAME = "JTPersionDaoImpl";

	@PersistenceContext(unitName = "JTDB")
	EntityManager entityManager;

	@Override
	public List<PersionEntity> getAllPersion() {
		Query q = entityManager.createNativeQuery("select v.psncode, v.unitcode, v.psnname, v.psnclcod, v.mobile, v.sex from V_compsn v");
		SQLQuery sql = q.unwrap(SQLQuery.class);
		sql.setResultTransformer(Transformers.aliasToBean(PersionEntity.class));
		return q.getResultList();
	}
	
	
}
