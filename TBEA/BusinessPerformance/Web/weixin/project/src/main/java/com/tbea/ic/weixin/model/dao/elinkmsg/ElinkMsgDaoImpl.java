package com.tbea.ic.weixin.model.dao.elinkmsg;


import com.speed.frame.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.weixin.model.entity.ElinkMsgEntity;
import com.tbea.ic.weixin.model.dao.elinkmsg.ElinkMsgDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(ElinkMsgDaoImpl.NAME)
@Transactional("elinkTransaction")
public class ElinkMsgDaoImpl extends AbstractReadWriteDaoImpl<ElinkMsgEntity> implements ElinkMsgDao {
	public final static String NAME = "ElinkMsgDaoImpl";

	@PersistenceContext(unitName = "elink")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
