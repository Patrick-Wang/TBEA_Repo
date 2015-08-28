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
	

	public List<KeyWords> getUnfixedKeyWorks() {
		Query q = manager.createQuery("from KeyWords where fixed = 'N'");
		return q.getResultList();
	}


	public void update(KeyWords keywords) {
		manager.merge(keywords);
	}


	public List<KeyWords> getKeyWorks() {
		Query q = manager.createQuery("from KeyWords");
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



}
