package com.tbea.ic.operation.model.dao.cpzlqk.dwmc;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;

import com.tbea.ic.operation.model.entity.cpzlqk.DwmcEntity;



@Repository(DwmcDaoImpl.NAME)
@Transactional("transactionManager")
public class DwmcDaoImpl extends AbstractReadWriteDaoImpl<DwmcEntity> implements DwmcDao {
	public final static String NAME = "DwmcDaoImpl";

	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Override
	public DwmcEntity getByDwid(Integer dwid) {
        Query q = getEntityManager().createQuery("from DwmcEntity where dw.id = :dwid");
        q.setParameter("dwid", dwid);
        List<DwmcEntity> result = q.getResultList();
        if (result.isEmpty()){
        	return null;
        }
		return result.get(0);
	}
}
