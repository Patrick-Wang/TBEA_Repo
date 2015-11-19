package com.tbea.ic.operation.model.dao.jygk.zzy;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.NDJHZB;
import com.tbea.ic.operation.model.entity.jygk.YDJHZB;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
@Repository
@Transactional("transactionManager")
public class ZzyNdjhzbDaoImpl extends AbstractReadWriteDaoImpl<NDJHZB> implements ZzyNdjhzbDao{

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}
	@Override
	public List<NDJHZB> getDataListByDwDate(int dwxxId,String zbidstrs,int nf) {		
		Query q = this.getEntityManager().createQuery("from NDJHZB where dwxx.id = :dwid and nf = :nf and zbxx.id in (" + zbidstrs + ")");
		q.setParameter("dwid", dwxxId);
		q.setParameter("nf", nf);
		return q.getResultList();
	}
	@Override
	public NDJHZB readDataByDwFlData(int dwxxId,int zbid,int nf){		
		Query q = this.getEntityManager().createQuery("from NDJHZB where dwxx.id = :dwid and nf = :nf and zbxx.id=:zbid");
		q.setParameter("dwid", dwxxId);
		q.setParameter("zbid", zbid);
		q.setParameter("nf", nf);
		List<NDJHZB> NDJHZBList = q.getResultList();
		if (!NDJHZBList.isEmpty()){
			return NDJHZBList.get(0);
		}
		return null;
	}
}
