package cn.com.tbea.template.model.dao;

import cn.com.tbea.template.model.entity.AbstractReadOnlyEntity;

public interface AbstractReadOnlyDao<T extends AbstractReadOnlyEntity> extends
		DaoBase<T> {
}
