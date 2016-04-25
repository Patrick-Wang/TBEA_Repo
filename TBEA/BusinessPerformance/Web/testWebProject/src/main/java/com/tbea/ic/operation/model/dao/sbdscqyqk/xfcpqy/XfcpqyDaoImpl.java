package com.tbea.ic.operation.model.dao.sbdscqyqk.xfcpqy;


import com.tbea.ic.operation.model.entity.sbdscqyqk.XfcpqyEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.sbdscqyqk.xfcpqy.XfcpqyDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(XfcpqyDaoImpl.NAME)
@Transactional("transactionManager")
public class XfcpqyDaoImpl extends AbstractReadWriteDaoImpl<XfcpqyEntity> implements XfcpqyDao {
	public final static String NAME = "XfcpqyDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
