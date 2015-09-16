package com.tbea.ic.carrier.service.nacao;

import java.util.List;

import org.openqa.selenium.WebDriver;

import com.tbea.ic.carrier.model.entity.Organization;

import net.sf.json.JSONArray;

public interface NacaoService {

	int fetchCompanyWithUnfixedKeywords(WebDriver driver, int start, int count);

	int fetchCompanyWithAllKeywords(WebDriver driver, int start, int count);

	List<Organization> findByName(String name);

	int getUnfixedKeywordsCount();

	int getKeywordsCount();

	int getUnfoundKeywordsCount();

	int fetchCompanyWithUnfoundKeywords(WebDriver driver, int start, int i);

	List<Organization> findByNameExactly(String compName);

}
