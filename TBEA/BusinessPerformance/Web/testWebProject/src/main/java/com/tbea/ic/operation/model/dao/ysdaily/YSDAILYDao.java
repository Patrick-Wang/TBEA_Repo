package com.tbea.ic.operation.model.dao.ysdaily;

import java.sql.Date;
import java.util.List;

import cn.com.tbea.template.model.dao.AbstractReadWriteDao;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.Yszkrb;
import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.entity.jygk.YSDAILY;

public interface YSDAILYDao extends AbstractReadWriteDao<Yszkrb>{

	YSDAILY getYsdaily(Date date, DWXX dwxx);

	/**
	 * 得到已经录入的录入项
	 * @param date
	 * @param dwxx
	 * @return
	 */
	Yszkrb getYsdailyRBLy(Date date, DWXX dwxx);

	/**
	 * 根据年份、月份、日期获取应收账款日报信息
	 * @param date
	 * @param dwxx
	 * @return
	 */
	Yszkrb getYsdailyRB(Date date, DWXX dwxx);

	/**
	 * 得到在某个月份的所有日报信息
	 * @param date
	 * @param dwxx
	 * @return
	 */
	List<Yszkrb> getYszkrbCountByNfYf(Date date, DWXX dwxx);

	void update(YSDAILY daily);

	Double getJzydyszkzmye(Date start, Date end, List<Company> companies);

	Double getZqbc(Date start, Date end, List<Company> companies);

	Double getQbbc(Date start, Date end, List<Company> companies);

	Double getYhkzkjyshkje(Date start, Date end, List<Company> companies);

	Double getJrhk(Date start, Date end, List<Company> companies);

	Double getGdwzxzdhkjh(Date start, Date end, List<Company> companies);

	Double getJtxdydzjhlzb(Date start, Date end, List<Company> companies);

	/**
	 * 获取应收账款指标 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	Double getYszkzb(Date lrDate, List<Company> companies);

	/**
	 * 获取回款计划 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	Double getHkjh(Date lrDate, List<Company> companies);

	/**
	 * 获取确保款项 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	Double getQbkx(Date lrDate, List<Company> companies);

	/**
	 * 获取争取款项 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	Double getZqkx(Date lrDate, List<Company> companies);

	/**
	 * 获取上月应收余额 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	Double getSyysye(Date lrDate, List<Company> companies);

	/**
	 * 获取今日新增应收账款 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	Double getJrxzyszk(Date lrDate, List<Company> companies);

	/**
	 * 获取本月累计新增应收 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	Double getByljxzys(Date lrDate, List<Company> companies);

	/**
	 * 获取今日回款 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	Double getJrhkNew(Date lrDate, List<Company> companies);

	/**
	 * 获取累计回款 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	Double getLjhk(Date lrDate, List<Company> companies);

	/**
	 * 获取回款完成率 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	Double getHkwcl(Date lrDate, List<Company> companies);

	/**
	 * 获取累计可降应收回款 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	Double getLjkjyshk(Date lrDate, List<Company> companies);

	/**
	 * 获取今日应收账款余额 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	Double getJryszkye(Date lrDate, List<Company> companies);

	/**
	 * 获取应收账款完成率 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	Double getYszkwcl(Date lrDate, List<Company> companies);

}
