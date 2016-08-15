package com.tbea.ic.operation.service.cpzlqk.pdcpycssbhgxxfb;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;

public interface PdcpycssbhgxxfbService {

	List<List<String>> getPdcpycssbhgxxfb(Date d, YDJDType yjType);

	List<String> getBhglbs();

	List<List<String>> getPdcpycssbhgxxfb(Date d, YDJDType yjType, Company company);

	List<WaveItem> getWaveItems(Date d, YDJDType yjType);

	List<WaveItem> getWaveItems(Date d, YDJDType yjType, Company company);


}
