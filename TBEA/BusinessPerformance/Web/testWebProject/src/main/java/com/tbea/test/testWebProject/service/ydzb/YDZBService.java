package com.tbea.test.testWebProject.service.ydzb;

import java.sql.Date;

import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.common.Company.Type;

public interface YDZBService {

	public String[][] getHzb_zbhzData(Date d);

	public String[][] getGcy_zbhzData(Date d);

	public String[][] getGdw_zbhzData(Date d);

	public String[][] getXjlrbData(Date d);

	public String[][] getYszkrb_qkbData(Date d);

	public String[][] getYdZbhz_overviewData(Date d, Company company, String zb);
	
	public String[][] getJdZbhz_overviewData(Date d, Company company, String zb);
	
	public String[][] getNdZbhz_overviewData(Date d, Company company, String zb);
	
	public String[][] getYdtbZbhz_overviewData(Date d, Company company, String zb);
	
	public String[][] getJdtbZbhz_overviewData(Date d, Company company, String zb);

	String getZbmc(String id);

}
