package com.tbea.test.testWebProject.model.dao.transfer.yszktz;

import java.util.List;
import java.util.Map;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;

import com.tbea.test.testWebProject.model.entity.local.YSZKTZLocal;

public interface YSZKTZLocalDao extends AbstractReadWriteDao<YSZKTZLocal> {

	public List<YSZKTZLocal> getAllYSZKTZLocal();

	public Double getCQK(String baseMonth, Integer startTime, Integer endTime,
			List<String> sshyList, boolean isIncluded, boolean isTotal)
			throws Exception;

	public Map<String, Double> getCQKByQY(String baseMonth, Integer startTime,
			Integer endTime, List<String> sshyList, boolean isIncluded,
			boolean isTotal) throws Exception;

	public Double getYQK(String baseMonth, Integer startTime, Integer endTime)
			throws Exception;

	public Map<String, Double> getYQKByQY(String baseMonth, Integer startTime,
			Integer endTime) throws Exception;

	public Double getYSZKJE(String baseMonth, List<String> sshyList,
			boolean isIncluded, boolean isTotal) throws Exception;

	public Double getYSZKJG(String baseMonth, Integer startTime,
			Integer endTime, List<String> sshyList, boolean isIncluded,
			boolean isTotal, boolean isKXLB, boolean isZBJ) throws Exception;

	public Map<String, Double> getYSZKJGByQY(String baseMonth,
			Integer startTime, Integer endTime, List<String> sshyList,
			boolean isIncluded, boolean isTotal, boolean isKXLB, boolean isZBJ)
			throws Exception;

}
