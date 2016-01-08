package com.speed.frame.model.dao;

import javax.persistence.EntityManager;

import com.speed.frame.model.entity.AbstractEntity;

public interface DaoBase<T extends AbstractEntity> {

	public Class<T> getEntityClass();

	public void setEntityManager(EntityManager entityManager);

	public EntityManager getEntityManager();

	public T read(T entity);

	public T getById(int id);
}
