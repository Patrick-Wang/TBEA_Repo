package cn.com.tbea.template.model.dao;

import cn.com.tbea.template.model.entity.AbstractReadWriteEntity;

public interface AbstractReadWriteDao<T extends AbstractReadWriteEntity>
		extends DaoBase<T> {
	public void create(T entity);

	public void persist(T entity);

	public T merge(T entity);

	public void delete(T entity);

	public void deleteById(int id);
}
