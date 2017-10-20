package com.speed.frame.model.dao;

import com.speed.frame.model.entity.AbstractReadOnlyEntity;

public interface AbstractReadOnlyDao<T extends AbstractReadOnlyEntity> extends
		DaoBase<T> {
}
