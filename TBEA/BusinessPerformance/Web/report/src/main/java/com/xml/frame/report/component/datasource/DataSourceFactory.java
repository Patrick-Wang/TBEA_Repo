package com.xml.frame.report.component.datasource;

import javax.sql.DataSource;

public interface DataSourceFactory {
	public DataSource getDataSource(String dsName);
}
