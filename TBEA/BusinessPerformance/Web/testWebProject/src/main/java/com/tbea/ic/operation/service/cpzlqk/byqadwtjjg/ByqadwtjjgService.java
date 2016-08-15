package com.tbea.ic.operation.service.cpzlqk.byqadwtjjg;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;

public interface ByqadwtjjgService {

	List<List<String>> getByqadwtjjg(Date d, YDJDType yjType);

	List<List<String>> getByqadwtjjg(Date d, YDJDType yjType, Company company);

	List<WaveItem> getByqYdAdwtjjgWaveItems(Date d, Company company);


}
