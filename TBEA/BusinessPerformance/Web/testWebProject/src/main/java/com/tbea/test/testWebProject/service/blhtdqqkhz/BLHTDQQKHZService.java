package com.tbea.test.testWebProject.service.blhtdqqkhz;

import java.sql.Date;

import com.tbea.test.testWebProject.model.entity.BLHTDQQKHZ;

public interface BLHTDQQKHZService {

	public BLHTDQQKHZ getBLById(int id);
	public String[][] getBlhtdqqk(Date date);
	public String[][] getBlyeqs(Date date);

}
