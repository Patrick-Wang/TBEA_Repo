package com.tbea.test.testWebProject.service.cqk;

import java.sql.Date;

public interface CQKService {

	public String[][] getCqkData(Date d);
	public String[][] getCompareData(Date d);
	public boolean importCQK();

}
