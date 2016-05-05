package com.tbea.ic.operation.model.dao.cwyjsf.yjsfndqcs;


import com.tbea.ic.operation.model.entity.cwyjsf.YjsfNdqcsEntity;
import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.dao.cwyjsf.yjsfndqcs.YjsfNdqcsDao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository(YjsfNdqcsDaoImpl.NAME)
@Transactional("transactionManager")
public class YjsfNdqcsDaoImpl extends AbstractReadWriteDaoImpl<YjsfNdqcsEntity> implements YjsfNdqcsDao {
	public final static String NAME = "YjsfNdqcsDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
}
