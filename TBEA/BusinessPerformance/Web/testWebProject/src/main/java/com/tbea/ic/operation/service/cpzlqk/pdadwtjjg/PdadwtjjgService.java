package com.tbea.ic.operation.service.cpzlqk.pdadwtjjg;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;

public interface PdadwtjjgService {

	List<List<String>> getPdadwtjjg(Date d, YDJDType yjType, List<Integer> zts);

	List<List<String>> getPdadwtjjg(Date d, YDJDType yjType, Company company, List<Integer> zts);

	List<WaveItem> getPdYdAdwtjjgWaveItems(Date d, Company company, List<Integer> zts);

	List<WaveItem> getPdYdAdwtjjgWaveItems(Date d, List<Integer> zts);

	List<WaveItem> getWaveItems(Date d, List<Integer> zts);


}
