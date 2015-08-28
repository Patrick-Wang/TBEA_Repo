package com.tbea.ic.carrier.service.nacao;

import org.openqa.selenium.WebDriver;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface NacaoService {


	void fetchCompanyWithUnfixedKeywords();

	void fetchCompanyWithAllKeywords();

	JSONArray findByName(String name);

}
