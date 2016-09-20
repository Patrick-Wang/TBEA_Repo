package com.tbea.ic.operation.service.cpzlqk.byqcpycssbhgxxfb;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;

public interface ByqcpycssbhgxxfbService {

	List<List<String>> getByqcpycssbhgxxfb(Date d, YDJDType yjType, ZBStatus status);

	List<String> getBhglbs();

	List<List<String>> getByqcpycssbhgxxfb(Date d, YDJDType yjType, Company company, ZBStatus status);

	List<WaveItem> getWaveItems(Date d, YDJDType yjType, ZBStatus status);

	List<WaveItem> getWaveItems(Date d, YDJDType yjType, Company company, ZBStatus status);


}
