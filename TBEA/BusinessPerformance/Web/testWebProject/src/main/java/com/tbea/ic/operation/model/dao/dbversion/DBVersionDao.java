package com.tbea.ic.operation.model.dao.dbversion;

import com.tbea.ic.operation.model.entity.DBVersion;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

public interface DBVersionDao extends AbstractReadWriteDao<DBVersion> {

	DBVersion getLatestVersion();

}
