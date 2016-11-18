package com.tbea.ic.operation.reportframe.hibernate.dialect.sqlserver;

import java.sql.Types;

import org.hibernate.dialect.SQLServerDialect;
import org.hibernate.type.StandardBasicTypes;

public class SQLDialect extends SQLServerDialect {
	public SQLDialect(){
		super();
		registerHibernateType( Types.NVARCHAR, StandardBasicTypes.STRING.getName() );
	}
}
