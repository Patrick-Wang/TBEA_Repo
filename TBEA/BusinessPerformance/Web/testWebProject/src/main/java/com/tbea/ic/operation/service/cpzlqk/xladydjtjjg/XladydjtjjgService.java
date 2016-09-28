package com.tbea.ic.operation.service.cpzlqk.xladydjtjjg;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;

public interface XladydjtjjgService {

	List<List<String>> getXladydjtjjg(Date d, YDJDType yjType, List<Integer> zts);

	List<List<String>> getXladydjtjjg(Date d, YDJDType yjType, Company company, List<Integer> zts);

	List<WaveItem> getXladydjWaveItems(Date d, YDJDType yjType, Company company, List<Integer> zts);


}
