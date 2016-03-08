package com.tbea.ic.carrier.service.person;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.tbea.ic.carrier.model.entity.Psn;

import net.sf.json.JSONArray;

public interface PersonService {
	
	public int queryPersonInfoPagesCount();

	public List<Psn> queryPersonInfo(int pageIndex);

	public List<Psn> queryPersonInfoById(String id);

	public String queryPersonNoById(String id);

}
