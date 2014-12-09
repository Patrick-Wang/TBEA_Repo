package com.tbea.test.testWebProject.service.cb;

import java.sql.Date;
import java.util.List;

public interface BYQCBService {

	List<String[][]> getTbmx(Date date);

	List<String[][]> getZxmx(Date valueOf);

//	String[][] getJttb(Date date);
//
//	String[][] getGstb(Date date);

}
