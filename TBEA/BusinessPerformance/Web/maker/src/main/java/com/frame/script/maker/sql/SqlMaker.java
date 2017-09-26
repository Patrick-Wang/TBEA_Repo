package com.frame.script.maker.sql;

import com.frame.script.config.ColType;
import com.frame.script.config.excel.ConfigTable;
import com.frame.script.maker.Maker;
import com.frame.script.maker.MakerException;

public class SqlMaker implements Maker {
	
	
	
	static String HEADER = "--#TITLE#\r\n" +
			"IF EXISTS ( SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = '#TBNAME#')\r\n" + 
			"DROP TABLE #TBNAME#\r\n" + 
			"CREATE TABLE [dbo].[#TBNAME#](\r\n" + 
				"\t[id] [int] IDENTITY(1,1) NOT NULL";
	
	static String TAIL = 
				"\r\n\t[_src] [varchar](50),\r\n" + 
				"\t[_time] [datetime]\r\n" + 
				"\tPRIMARY KEY CLUSTERED\r\n" + 
				"\t(\r\n" + 
					"\t\t[id] ASC\r\n" + 
				"\t)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\r\n" + 
			") ON [PRIMARY]";
	
	StringBuilder sqlAll = new StringBuilder();
	
	public String make(ConfigTable src) {
		String sql = HEADER.replaceAll("#TBNAME#", src.getTableName()).replaceAll("#TITLE#", src.getTitle());
		for (int i = 0; i < src.getColNames().size(); ++i) {
			sql += ",";
			if (i > 0) {
				sql += " --" + src.getColTitles().get(i - 1);
			}
			
			sql += "\r\n";
			ColType ct = src.getColTypes().get(i);
			sql += "\t" + src.getColNames().get(i) + " " + ct.getSqlVal();
			if (null != ct.getDefaultVal() && ct.getType() != null) {
				if (ColType.NUMBER == ct.getType().intValue() || ColType.PERCENT == ct.getType().intValue()
						 || ColType.INTEGER == ct.getType().intValue()) {
					sql += " DEFAULT " + ct.getDefaultVal();
				}else{
					sql += " DEFAULT '" + ct.getDefaultVal() + "'";
				}
			}
			if (ColType.UNIQUE.equals(ct.getConstraint())) {
				sql += " UNIQUE"; 
			} else if (ColType.NOTNULL.equals(ct.getConstraint())) {
				sql += " NOT NULL"; 
			} else if (ColType.PRIMARY.equals(ct.getConstraint())) {
				sql += " UNIQUE NOT NULL"; 
			}
			
			if (i == src.getColNames().size() - 1) {
				sql += ", --" + src.getColTitles().get(i);
			}
		}
		sql += TAIL;
		sqlAll.append(sql).append("\r\n");
		return sql;
		
	}

	@Override
	public String makeAll() throws MakerException {
		return sqlAll.toString();
	}


}
