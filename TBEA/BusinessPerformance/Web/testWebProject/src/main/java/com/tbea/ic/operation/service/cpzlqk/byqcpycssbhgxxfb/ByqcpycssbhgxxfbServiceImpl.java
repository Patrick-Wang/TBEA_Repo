package com.tbea.ic.operation.service.cpzlqk.byqcpycssbhgxxfb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.ZBStatus;
import com.tbea.ic.operation.controller.servlet.cpzlqk.ByqBhgType;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.model.dao.cpzlqk.byqbhgwtmx.ByqBhgwtmxDao;
import com.tbea.ic.operation.model.dao.cpzlqk.byqbhgwtmx.ByqBhgwtmxDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqk.dwmc.DwmcDao;
import com.tbea.ic.operation.model.dao.identifier.cpzlqk.byqbhglb.ByqBhglbDao;
import com.tbea.ic.operation.model.dao.identifier.cpzlqk.byqbhglb.ByqBhglbDaoImpl;
import com.tbea.ic.operation.model.entity.identifier.cpzlqk.ByqBhglbEntity;

@Service(ByqcpycssbhgxxfbServiceImpl.NAME)
@Transactional("transactionManager")
public class ByqcpycssbhgxxfbServiceImpl implements ByqcpycssbhgxxfbService {
	@Resource(name = ByqBhglbDaoImpl.NAME)
	ByqBhglbDao byqBhglbDao;

	@Resource(name = ByqBhgwtmxDaoImpl.NAME)
	ByqBhgwtmxDao byqBhgwtmxDao;

	@Autowired
	DwmcDao dwmcDao;

	public final static String NAME = "ByqcpycssbhgxxfbServiceImpl";

	@Override
	public List<List<String>> getByqcpycssbhgxxfb(Date d, YDJDType yjType,
			ByqBhgType bhgType) {
		List<Object[]> entities = null;// [Integer dwid, Integer bhglxid, Long
										// count]
		if (yjType == YDJDType.YD) {
			entities = byqBhgwtmxDao.getByYdFb(d, bhgType.ordinal(), ZBStatus.APPROVED);
		} else {
			entities = byqBhgwtmxDao.getByJdFb(d, bhgType.ordinal(), ZBStatus.APPROVED);
		}
		List<List<String>> result = new ArrayList<List<String>>();
		if (!entities.isEmpty()) {

			List<ByqBhglbEntity> bhglbs = byqBhglbDao.getAll();
			Map<Integer, Integer> bhglbsIds = new HashMap<Integer, Integer>();
			for (int i = 0; i < bhglbs.size(); ++i) {
				bhglbsIds.put(bhglbs.get(i).getId(), i + 1);
			}

			List<Integer> hj = Util.resize(new ArrayList<Integer>(),
					bhglbs.size());

			Map<Integer, Integer> dwCpMap = new HashMap<Integer, Integer>();

			for (Object[] entity : entities) {
				if (!dwCpMap.containsKey((Integer) entity[0])) {
					List<String> list = Util.resize(new ArrayList<String>(),
							1 + bhglbs.size());
					result.add(list);
					list.set(0, dwmcDao.getByDwid((Integer) entity[0])
							.getDwmc().getName());
					dwCpMap.put((Integer) entity[0], result.size() - 1);
				}

				List<String> row = result.get(dwCpMap.get((Integer) entity[0]));
				row.set(bhglbsIds.get((Integer) entity[1]), ""
						+ ((Long) entity[2]).intValue());
				hj.set(bhglbsIds.get((Integer) entity[1]) - 1, MathUtil.sum(
						hj.get(bhglbsIds.get((Integer) entity[1]) - 1),
						((Long) entity[2]).intValue()));
			}

			List<String> list = new ArrayList<String>();
			result.add(list);
			list.add("合计");
			for (int i = 0; i < hj.size(); ++i) {
				list.add("" + hj.get(i));
			}
		}
		return result;
	}

	@Override
	public List<String> getBhglbs() {
		List<ByqBhglbEntity> bhglbs = byqBhglbDao.getAll();
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < bhglbs.size(); ++i) {
			result.add(bhglbs.get(i).getName());
		}
		return result;
	}
}
