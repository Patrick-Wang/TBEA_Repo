package com.tbea.ic.operation.service.cpzlqk.xlnwbzlztqk;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;

public interface XlnwbzlztqkService {

	List<List<String>> getYdnwbzlqk(Date d);

	List<List<String>> getJdnwbzlqk(Date date);
	
	List<List<String>> getYdnwbzlqk(Date d, Company company);

	List<WaveItem> getWaveItems(Date d, YDJDType yjType, Company company);

	List<List<String>> getJdnwbzlqk(Date d, Company company);

	List<WaveItem> getWaveItems(Date d, YDJDType yjType);

	List<List<String>> getJdGyzlwt(Date d);

	List<List<String>> getYdGyzlwt(Date d);

	List<List<String>> getJdGyzlwt(Date d, Company company);

	List<List<String>> getYdGyzlwt(Date d, Company company);

	List<WaveItem> getWaveItemsGyzlwt(Date d, YDJDType yjType, Company company);

	List<List<String>> getJdYclzlwt(Date d);

	List<List<String>> getYdYclzlwt(Date d);
	
	List<WaveItem> getWaveItemsGyzlwt(Date d, YDJDType yjType);

	List<List<String>> getJdYclzlwt(Date d, Company company);

	List<List<String>> getYdYclzlwt(Date d, Company company);

	List<WaveItem> getYclzlwtWaveItems(Date d, YDJDType yjType, Company company);
	
	List<WaveItem> getYclzlwtWaveItems(Date d, YDJDType yjType);

	List<List<String>> getJdSczzzlqk(Date d);

	List<List<String>> getYdSczzzlqk(Date d);

	List<WaveItem> getWaveItemsSczzzlqk(Date d, YDJDType yjType);

	List<List<String>> getJdSczzzlqk(Date d, Company company);

	List<List<String>> getYdSczzzlqk(Date d, Company company);

	List<WaveItem> getWaveItemsSczzzlqk(Date d, YDJDType yjType, Company company);

	List<List<String>> getJdSczzzlqkxxxx(Date d);

	List<List<String>> getYdSczzzlqkxxxx(Date d);

	List<List<String>> getJdSczzzlqkxxxx(Date d, Company company);

	List<List<String>> getYdSczzzlqkxxxx(Date d, Company company);

	List<List<String>> getYdYsazzlwt(Date d);

	List<List<String>> getJdYsazzlwt(Date d);

	List<WaveItem> getWaveItemsYsazzlwt(Date d, YDJDType yjType);

	List<List<String>> getJdYsazzlwt(Date d, Company company);

	List<WaveItem> getWaveItemsYsazzlwt(Date d, YDJDType yjType, Company company);

	List<List<String>> getYdYsazzlwt(Date d, Company company);

	List<List<String>> getJdNbzlwtfl(Date d);

	List<List<String>> getYdNbzlwtfl(Date d);

	List<List<String>> getJdNbzlwtfl(Date d, Company company);

	List<List<String>> getYdNbzlwtfl(Date d, Company company);

	List<List<String>> getJdWbzlwtfl(Date d);

	List<List<String>> getYdWbzlwtfl(Date d);

	List<List<String>> getJdWbzlwtfl(Date d, Company company);

	List<List<String>> getYdWbzlwtfl(Date d, Company company);

	List<List<String>> getNbzlwttjqk(Date d, Company company);

	List<List<String>> getWbzlwttjqk(Date d, Company company);
}
