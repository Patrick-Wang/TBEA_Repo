package com.tbea.ic.carrier.model.dao.keywords;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.carrier.model.entity.KeyWords;

@Repository
@Transactional("transactionManager")
public class KeyWordsDaoImpl implements KeyWordsDao{

	@PersistenceContext(unitName = "localDB")
	EntityManager manager;
	

	public List<KeyWords> getUnfixedKeyWorks(int start, int count) {
		Query q = manager.createQuery("from KeyWords where fixed = 'N'");
		q.setFirstResult(start);
		q.setMaxResults(count);
		return q.getResultList();
	}


	public void update(KeyWords keywords) {
		manager.merge(keywords);
	}

	public void remove(KeyWords keywords){
		manager.merge(keywords);
	}

	public List<KeyWords> getKeyWorks(int start, int count) {
		Query q = manager.createQuery("from KeyWords");
		q.setFirstResult(start);
		q.setMaxResults(count);
		return q.getResultList();
	}


	public KeyWords getKeyWordsByKey(String key) {
		Query q = manager.createQuery("from KeyWords where text = :name");
		q.setParameter("name", key);
		List<KeyWords> keys = q.getResultList();
		if (keys.isEmpty()){
			return null;
		}
		return keys.get(0);
	}


	public int getUnfixedKeyWorksCount() {
		Query q = manager.createQuery("select count(*) from KeyWords where fixed = 'N'");
		List<Object> ret = q.getResultList();
		return ((Long)ret.get(0)).intValue();
	}


	public int getKeyWorksCount() {
		Query q = manager.createQuery("select count(*) from KeyWords");
		List<Object> ret = q.getResultList();
		return ((Long)ret.get(0)).intValue();
	}


	public int getUnfoundKeyWorksCount() {
		Query q = manager.createQuery("select count(*) from KeyWords k where k.fixed = 'Y' and k.count = 0 ");
		List<Object> ret = q.getResultList();
		return ((Long)ret.get(0)).intValue();
	}


	public List<KeyWords> getUnfoundKeyWorks(int start, int count) {
		Query q = manager.createQuery("from KeyWords k where k.fixed = 'Y' and  k.count = 0 ");
		q.setFirstResult(start);
		q.setMaxResults(count);
		return q.getResultList();
	}



}
