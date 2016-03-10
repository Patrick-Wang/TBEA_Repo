package com.tbea.ic.carrier.model.dao.psn;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.carrier.model.entity.Psn;

@Repository
@Transactional("transactionManager_psn")
public class PsnDaoImpl implements PsnDao{

	@PersistenceContext(unitName = "localDB_psn")
	EntityManager manager;
	

	public String getPsnNoByID(String id) {
		Query q = manager.createQuery("from Psn where text = :id");
		q.setParameter("id", id);
		List<Psn> keys = q.getResultList();

		return keys.get(0).getBasgroupdef20();
	}

	public List<Psn> getPsns(int pageIndex) {
		Query q = manager.createQuery("from Psn");
		q.setFirstResult(pageIndex);
		q.setMaxResults(200);
		return q.getResultList();
	}
	
	public int getPsnPagesCount(){
		Query q = manager.createQuery("select count(*) from Psn");
		
		int count = Integer.parseInt(q.getResultList().get(0).toString());
		
		int pages = 0;
		
		if (count%200 > 0)
		{
			pages++;
		}
		
		pages += count/200;
		
		return pages;
	}
	

	public List<Psn> getPsnsById(String id){
		Query q = manager.createQuery("from Psn where text = :id");
		q.setParameter("id", id);
		List<Psn> keys = q.getResultList();

		return keys;
	}
}
