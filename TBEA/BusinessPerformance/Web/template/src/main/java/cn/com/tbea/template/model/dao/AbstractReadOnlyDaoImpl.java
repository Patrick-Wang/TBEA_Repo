package cn.com.tbea.template.model.dao;

import cn.com.tbea.template.model.entity.AbstractReadOnlyEntity;

public class AbstractReadOnlyDaoImpl<T extends AbstractReadOnlyEntity> extends
		DaoBaseImpl<T> implements AbstractReadOnlyDao<T> {
}
