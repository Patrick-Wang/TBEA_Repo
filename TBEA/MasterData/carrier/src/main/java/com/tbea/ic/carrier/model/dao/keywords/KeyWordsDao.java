package com.tbea.ic.carrier.model.dao.keywords;

import java.util.List;

import com.tbea.ic.carrier.model.entity.KeyWords;



public interface KeyWordsDao {

	void update(KeyWords keywords);
	
	List<KeyWords> getUnfixedKeyWorks(int start, int count);

	List<KeyWords> getKeyWorks(int start, int count);

	KeyWords getKeyWordsByKey(String name);

	int getUnfixedKeyWorksCount();

	int getKeyWorksCount();

	int getUnfoundKeyWorksCount();

	List<KeyWords> getUnfoundKeyWorks(int start, int count);
}
