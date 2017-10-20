package com.speed.frame.model.dao;

import com.speed.frame.model.entity.AbstractReadWriteEntity;

public class AbstractReadWriteDaoImpl<T extends AbstractReadWriteEntity>
		extends DaoBaseImpl<T> implements AbstractReadWriteDao<T> {

	@Override
	public void create(T entity) {
		getEntityManager().persist(entity);
	}

	@Override
	public T merge(T entity) {
		return getEntityManager().merge(entity);
	}

	@Override
	public void persist(T entity) {
		getEntityManager().persist(entity);
	}

	@Override
	public void delete(T entity) {
		getEntityManager().remove(entity);
	}

	@Override
	public void deleteById(int id) {
		T entity = getEntityManager().find(getEntityClass(), id);
		getEntityManager().remove(entity);
	}
}
