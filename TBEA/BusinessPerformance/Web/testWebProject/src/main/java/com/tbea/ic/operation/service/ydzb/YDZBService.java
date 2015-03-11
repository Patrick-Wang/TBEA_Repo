package com.tbea.ic.operation.service.ydzb;

import java.sql.Date;
import java.util.Calendar;

import com.tbea.ic.operation.common.companys.Company;

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

	public Date getLatestHzbDate();

	public Date getLatestGcyDate();

	public Date getLatestXjlDate();
	
	public  String getZBNameById(int id);

}
