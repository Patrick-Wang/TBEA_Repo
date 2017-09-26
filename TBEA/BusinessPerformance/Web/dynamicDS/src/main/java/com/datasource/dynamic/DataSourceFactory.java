package com.datasource.dynamic;

import javax.sql.DataSource;

public interface DataSourceFactory {
	public DataSource getDataSource(String dsName);
}
