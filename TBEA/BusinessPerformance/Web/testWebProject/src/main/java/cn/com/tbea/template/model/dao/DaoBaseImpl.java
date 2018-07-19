package cn.com.tbea.template.model.dao;

import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cn.com.tbea.template.model.entity.AbstractEntity;

public class DaoBaseImpl<T extends AbstractEntity> implements DaoBase<T> {
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getEntityClass() {
		ParameterizedType pType = (ParameterizedType) getClass()
				.getGenericSuperclass();
		return (Class<T>) pType.getActualTypeArguments()[0];
	}

	@Override
	@PersistenceContext(unitName = "localDB")
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public T read(T entity) {
		return getById(entity.getId());
	}

	@Override
	public T getById(int id) {
		return getEntityManager().find(getEntityClass(), id);
	}
}
