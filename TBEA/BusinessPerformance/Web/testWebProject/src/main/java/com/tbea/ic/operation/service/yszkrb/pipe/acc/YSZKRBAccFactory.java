package com.tbea.ic.operation.service.yszkrb.pipe.acc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.dao.ysdaily.YSDAILYDao;
import com.tbea.ic.operation.service.util.pipe.filter.acc.IAccumulator;

public class YSZKRBAccFactory {

	YSDAILYDao ysdailyDao;
	// 集团下达月度资金回笼指标
	IAccumulator jtxdydzjhlzbAcc;
	

	// 各单位自行制定的回款计划
	IAccumulator gdwzxzdhkjhAcc;

	// 今日回款
	IAccumulator jrhkAcc;

	// 已回款中可降应收的回款金额
	IAccumulator yhkzkjyshkjeAcc;

	// 确保办出
	IAccumulator qbbcAcc;

	// 争取办出
	IAccumulator zqbcAcc;

	// 截止月底应收账款账面余额
	IAccumulator jzydyszkzmyeAcc;

	
	
	public YSZKRBAccFactory(YSDAILYDao ysdailyDao) {
		this.ysdailyDao = ysdailyDao;
	}

	// 集团下达月度资金回笼指标
	public IAccumulator getJtxdydzjhlzbAcc() {
		return new IAccumulator() {
			@Override
			public List<Double> compute(int col, Date start, Date end,
					List<Integer> zbs, List<Company> companies) {
				List<Double> result = new ArrayList<Double>();
				result.add(ysdailyDao.getJtxdydzjhlzb(start, end, companies));
				return result;
			}
		};
	}

	// 各单位自行制定的回款计划
	public IAccumulator getGdwzxzdhkjhAcc() {
		return (null != jtxdydzjhlzbAcc) ? jtxdydzjhlzbAcc : (jtxdydzjhlzbAcc = new IAccumulator() {
			@Override
			public List<Double> compute(int col, Date start, Date end,
					List<Integer> zbs, List<Company> companies) {
				List<Double> result = new ArrayList<Double>();
				result.add(ysdailyDao.getGdwzxzdhkjh(start, end, companies));
				return result;
			}
		});
	}

	// 今日回款
	public IAccumulator getJrhkAcc() {
		return (null != jrhkAcc) ? jrhkAcc : (jrhkAcc = new IAccumulator() {
			@Override
			public List<Double> compute(int col, Date start, Date end,
					List<Integer> zbs, List<Company> companies) {
				List<Double> result = new ArrayList<Double>();
				result.add(ysdailyDao.getJrhk(start, end, companies));
				return result;
			}
		});
	}

	// 已回款中可降应收的回款金额
	public IAccumulator getYhkzkjyshkjeAcc() {
		return (null != yhkzkjyshkjeAcc) ? yhkzkjyshkjeAcc : (yhkzkjyshkjeAcc = new IAccumulator() {
			@Override
			public List<Double> compute(int col, Date start, Date end,
					List<Integer> zbs, List<Company> companies) {
				List<Double> result = new ArrayList<Double>();
				result.add(ysdailyDao.getYhkzkjyshkje(start, end, companies));
				return result;
			}
		});
	}

	// 确保办出
	public IAccumulator getQbbcAcc() {
		return (null != qbbcAcc) ? qbbcAcc : (qbbcAcc = new IAccumulator() {
			@Override
			public List<Double> compute(int col, Date start, Date end,
					List<Integer> zbs, List<Company> companies) {
				List<Double> result = new ArrayList<Double>();
				result.add(ysdailyDao.getQbbc(start, end, companies));
				return result;
			}
		});
	}

	// 争取办出
	public IAccumulator getZqbcAcc() {
		return (null != zqbcAcc) ? zqbcAcc : (zqbcAcc = new IAccumulator() {
			@Override
			public List<Double> compute(int col, Date start, Date end,
					List<Integer> zbs, List<Company> companies) {
				List<Double> result = new ArrayList<Double>();
				result.add(ysdailyDao.getZqbc(start, end, companies));
				return result;
			}
		});
	}

	// 截止月底应收账款账面余额
	public IAccumulator getJzydyszkzmyeAcc() {
		return (null != jzydyszkzmyeAcc) ? jzydyszkzmyeAcc : (jzydyszkzmyeAcc =new IAccumulator() {
			@Override
			public List<Double> compute(int col, Date start, Date end,
					List<Integer> zbs, List<Company> companies) {
				List<Double> result = new ArrayList<Double>();
				result.add(ysdailyDao.getJzydyszkzmye(start, end, companies));
				return result;
			}
		});
	}
}
