package com.tbea.ic.carrier.model.dao.keywords;

import java.util.List;

import com.tbea.ic.carrier.model.entity.KeyWords;



public interface KeyWordsDao {

	void update(KeyWords keywords);
	
	List<KeyWords> getUnfixedKeyWorks();

	List<KeyWords> getKeyWorks();

	KeyWords getKeyWordsByKey(String name);
}
