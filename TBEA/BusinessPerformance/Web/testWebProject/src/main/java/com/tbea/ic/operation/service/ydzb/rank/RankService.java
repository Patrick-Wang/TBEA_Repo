package com.tbea.ic.operation.service.ydzb.rank;

import java.sql.Date;
import java.util.List;

public interface RankService {

	/**
	 * 利润计划完成率排名
	 * @param date
	 * @return
	 */
	List<String[]> getJhlrRank(Date date);

	/**
	 * @param date
	 * @return
	 */
	List<String[]> getLjlrRank(Date date);

	List<String[]> getJxjlRank(Date date);	
	
	List<String[]> getRjlrRank(Date date);

	List<String[]> getRjsrRank(Date date);

	List<String[]> getXmgsRjsrRank(Date date);

	List<String[]> getXmgsRjlrRank(Date date);

	List<String[]> getXmgsJhlrRank(Date date);	
	
	/**
	 * 应收账款占收入比排名
	 * @param date
	 * @return
	 */
	List<String[]> getYszkzsrbRank(Date date);	
	
	/**
	 * 应收账款加保理占收入排名
	 * @param date
	 * @return
	 */
	List<String[]> getYszkAndBlzbRank(Date date);	
	
	/**
	 * 应收账款加保理占收入排名
	 * @param date
	 * @return
	 */
	List<String[]> getChzbRank(Date date);	
	
	/**
	 * 应收加存货占收入比排名
	 * @param date
	 * @return
	 */
	List<String[]> getYsAndChzbRank(Date date);	
}
