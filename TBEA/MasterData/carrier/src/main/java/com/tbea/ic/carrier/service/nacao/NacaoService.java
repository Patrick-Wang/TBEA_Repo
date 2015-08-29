package com.tbea.ic.carrier.service.nacao;

import net.sf.json.JSONArray;

public interface NacaoService {


	void fetchCompanyWithUnfixedKeywords();

	void fetchCompanyWithAllKeywords();

	JSONArray findByName(String name);

}
