package com.tbea.ic.operation.service.cpzlqk.byqadwtjjg;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;

public interface ByqadwtjjgService {

	List<List<String>> getByqadwtjjg(Date d, YDJDType yjType, Company company,  List<Integer> zts);

	List<List<String>> getByqadwtjjg(Date d, YDJDType yjType,  List<Integer> zts);

	List<WaveItem> getByqYdAdwtjjgWaveItems(Date d, Company company,  List<Integer> zts);

	List<WaveItem> getWaveItems(Date d,  List<Integer> zts);


}
