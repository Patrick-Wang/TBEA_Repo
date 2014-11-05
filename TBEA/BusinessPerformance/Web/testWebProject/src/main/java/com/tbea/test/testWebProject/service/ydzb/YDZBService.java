package com.tbea.test.testWebProject.service.ydzb;

import java.sql.Date;
import java.util.Calendar;

import com.tbea.test.testWebProject.model.entity.QYZJK;

public interface YDZBService {

	public String[][] getHzb_zbhzData(Date d);

	public String[][] getGcy_zbhzData(Date d);

	public String[][] getGdw_zbhzData(Date d);

	public String[][] getXjlrbData(Date d);

	public String[][] getYszkrb_qkbData(Date d);

}
