package com.tbea.ic.carrier.service.nacao;

import org.openqa.selenium.WebDriver;

import net.sf.json.JSONArray;

public interface NacaoService {

	int fetchCompanyWithUnfixedKeywords(WebDriver driver, int start, int count);

	int fetchCompanyWithAllKeywords(WebDriver driver, int start, int count);

	JSONArray findByName(String name);

	int getUnfixedKeywordsCount();

	int getKeywordsCount();

	int getUnfoundKeywordsCount();

	int fetchCompanyWithUnfoundKeywords(WebDriver driver, int start, int i);

}
