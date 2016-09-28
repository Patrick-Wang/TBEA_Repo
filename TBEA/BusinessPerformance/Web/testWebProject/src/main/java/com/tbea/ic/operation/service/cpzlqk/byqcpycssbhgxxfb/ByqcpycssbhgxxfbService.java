package com.tbea.ic.operation.service.cpzlqk.byqcpycssbhgxxfb;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;

public interface ByqcpycssbhgxxfbService {

	List<List<String>> getByqcpycssbhgxxfb(Date d, YDJDType yjType,  List<Integer> zts);

	List<String> getBhglbs();

	List<List<String>> getByqcpycssbhgxxfb(Date d, YDJDType yjType, Company company,  List<Integer> zts);

	List<WaveItem> getWaveItems(Date d, YDJDType yjType,  List<Integer> zts);

	List<WaveItem> getWaveItems(Date d, YDJDType yjType, Company company,  List<Integer> zts);


}
