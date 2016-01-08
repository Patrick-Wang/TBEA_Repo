package com.speed.frame.model.dao;

import com.speed.frame.model.entity.AbstractReadOnlyEntity;

public class AbstractReadOnlyDaoImpl<T extends AbstractReadOnlyEntity> extends
		DaoBaseImpl<T> implements AbstractReadOnlyDao<T> {
}
