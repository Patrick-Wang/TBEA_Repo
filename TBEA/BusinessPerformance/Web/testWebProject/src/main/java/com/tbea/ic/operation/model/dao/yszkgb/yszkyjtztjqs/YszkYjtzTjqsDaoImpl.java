package com.tbea.ic.operation.model.dao.yszkgb.yszkyjtztjqs;


import com.tbea.ic.operation.model.entity.yszkgb.YszkYjtzTjqsEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.yszkgb.yszkyjtztjqs.YszkYjtzTjqsDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(YszkYjtzTjqsDaoImpl.NAME)
@Transactional("transactionManager")
public class YszkYjtzTjqsDaoImpl extends AbstractReadWriteDaoImpl<YszkYjtzTjqsEntity> implements YszkYjtzTjqsDao {
	public final static String NAME = "YszkYjtzTjqsDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
