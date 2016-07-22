package com.tbea.ic.operation.service.futures;

import net.sf.json.JSONArray;

public interface FuturesService {

	JSONArray getTableValues(String type);

	JSONArray getChartValues(String type);

}
