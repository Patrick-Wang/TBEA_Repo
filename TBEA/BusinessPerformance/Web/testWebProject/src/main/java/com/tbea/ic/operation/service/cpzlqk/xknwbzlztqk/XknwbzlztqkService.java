package com.tbea.ic.operation.service.cpzlqk.xknwbzlztqk;

import java.sql.Date;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;

public interface XknwbzlztqkService {

	List<List<String>> getYdnwbzlqk(Date d, List<Integer> zts);

	List<List<String>> getJdnwbzlqk(Date date, List<Integer> zts);
	
	List<List<String>> getYdnwbzlqk(Date d, Company company, List<Integer> zts);

	List<WaveItem> getWaveItems(Date d, YDJDType yjType, Company company, List<Integer> zts);

	List<List<String>> getJdnwbzlqk(Date d, Company company, List<Integer> zts);

	List<WaveItem> getWaveItems(Date d, YDJDType yjType, List<Integer> zts);

	List<List<String>> getJdsjzlqk(Date d, List<Integer> zts);

	List<List<String>> getYdsjzlqk(Date d, List<Integer> zts);

	List<WaveItem> getSjzlqkWaveItems(Date d, YDJDType yjType, List<Integer> zts);

	List<List<String>> getJdsjzlqk(Date d, Company company, List<Integer> zts);

	List<List<String>> getYdsjzlqk(Date d, Company company, List<Integer> zts);

	List<WaveItem> getSjzlqkWaveItems(Date d, YDJDType yjType, Company company, List<Integer> zts);

	List<List<String>> getJdYclzlwt(Date d, List<Integer> zts);

	List<List<String>> getYdYclzlwt(Date d, List<Integer> zts);

	List<WaveItem> getWaveItemsYclzlwt(Date d, YDJDType yjType, List<Integer> zts);

	List<List<String>> getJdYclzlwt(Date d, Company company, List<Integer> zts);

	List<List<String>> getYdYclzlwt(Date d, Company company, List<Integer> zts);

	List<WaveItem> getWaveItemsYclzlwt(Date d, YDJDType yjType, Company company, List<Integer> zts);

	List<List<String>> getJdSczzzlqk(Date d, List<Integer> zts);

	List<List<String>> getYdSczzzlqk(Date d, List<Integer> zts);

	List<WaveItem> getWaveItemsSczzzlqk(Date d, YDJDType yjType, List<Integer> zts);

	List<List<String>> getJdSczzzlqk(Date d, Company company, List<Integer> zts);

	List<List<String>> getYdSczzzlqk(Date d, Company company, List<Integer> zts);

	List<WaveItem> getWaveItemsSczzzlqk(Date d, YDJDType yjType, Company company, List<Integer> zts);

	List<List<String>> getJdSczzzlqkxxxx(Date d, List<Integer> zts);

	List<List<String>> getYdSczzzlqkxxxx(Date d, List<Integer> zts);

	List<List<String>> getJdSczzzlqkxxxx(Date d, Company company, List<Integer> zts);

	List<List<String>> getYdSczzzlqkxxxx(Date d, Company company, List<Integer> zts);

	List<List<String>> getJdYsazzlwt(Date d, List<Integer> zts);

	List<List<String>> getJdYsazzlwt(Date d, Company company, List<Integer> zts);

	List<WaveItem> getWaveItemsYsazzlwt(Date d, YDJDType yjType, Company company, List<Integer> zts);

	List<WaveItem> getWaveItemsYsazzlwt(Date d, YDJDType yjType, List<Integer> zts);

	List<List<String>> getYdYsazzlwt(Date d, List<Integer> zts);

	List<List<String>> getYdYsazzlwt(Date d, Company company, List<Integer> zts);

	List<List<String>> getJdNbzlwtfl(Date d, List<Integer> zts);

	List<List<String>> getYdNbzlwtfl(Date d, List<Integer> zts);

	List<List<String>> getJdNbzlwtfl(Date d, Company company, List<Integer> zts);

	List<List<String>> getYdNbzlwtfl(Date d, Company company, List<Integer> zts);

	List<List<String>> getJdWbzlwtfl(Date d, List<Integer> zts);

	List<List<String>> getYdWbzlwtfl(Date d, List<Integer> zts);

	List<List<String>> getJdWbzlwtfl(Date d, Company company, List<Integer> zts);

	List<List<String>> getYdWbzlwtfl(Date d, Company company, List<Integer> zts);

	List<List<String>> getNbzlwttjqk(Date d, Company company, List<Integer> zts);

	List<List<String>> getWbzlwttjqk(Date d, Company company, List<Integer> zts);

}
