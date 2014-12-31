package com.tbea.test.testWebProject.service.byqfkfstj;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.common.companys.CompanyManager;
import com.tbea.test.testWebProject.common.companys.Organization;
import com.tbea.test.testWebProject.common.companys.CompanyManager.CompanyType;
import com.tbea.test.testWebProject.model.dao.byqfkfstj.BYQFKFSDao;
import com.tbea.test.testWebProject.model.entity.BYQFDWFKFS;
import com.tbea.test.testWebProject.model.entity.BYQGWFKFS;
import com.tbea.test.testWebProject.model.entity.BYQNWFKFS;

@Service
@Transactional("transactionManager")
public class BYQFKFSTJServiceImpl implements BYQFKFSTJService {

	private static Map<Company, Integer> qymap = new HashMap<Company, Integer>();

	static {
		Organization org = CompanyManager.getBMOrganization();
		qymap.put(org.getCompany(CompanyType.SB), 0);
		qymap.put(org.getCompany(CompanyType.HB), 1);
		qymap.put(org.getCompany(CompanyType.XB), 2);
	}

	@Autowired
	BYQFKFSDao byqfkfsDao;

	private Integer getIndex(Company comp) {
		if (qymap.containsKey(comp)) {
			return qymap.get(comp);
		} else {
			for (Company com : qymap.keySet()) {
				if (com.contains(comp)) {
					return qymap.get(com);
				}
			}
		}
		return null;
	}

	public String[][] getFdwData(Date d) {
		Organization org = CompanyManager.getBMOrganization();
		List<BYQFDWFKFS> byqfdwfkfss = byqfkfsDao.getFdwfs(d);
		String[][] result = new String[4][16];
		Integer col;
		for (BYQFDWFKFS byqfdw : byqfdwfkfss) {
			if (null != byqfdw) {
				Company comp = org
						.getCompany(Integer.valueOf(byqfdw.getGsbm()));
				col = getIndex(comp);
				if (null != col) {
					result[col][0] = byqfdw.getFdwhtddzlbs() + "";
					result[col][1] = byqfdw.getFdwhtddzlje() + "";
					result[col][2] = byqfdw.getWyfkhtbs() + "";
					result[col][3] = byqfdw.getWyfkhtje() + "";
					result[col][4] = byqfdw.getYfkxybfzshtbs() + "";
					result[col][5] = byqfdw.getYfkxybfzshtje() + "";
					result[col][6] = byqfdw.getYfkzbfzsdsszjhtbs() + "";
					result[col][7] = byqfdw.getYfkzbfzsdsszjhtje() + "";
					result[col][8] = byqfdw.getHwjfhfkblxybfzbshtbs() + "";
					result[col][9] = byqfdw.getHwjfhfkblxybfzbshtje() + "";
					result[col][10] = byqfdw.getWddsjhtbs() + "";
					result[col][11] = byqfdw.getWddsjhtje() + "";
					result[col][12] = byqfdw.getZbqdysegyhtbs() + "";
					result[col][13] = byqfdw.getZbqdysegyhtje() + "";
					result[col][14] = byqfdw.getXkxhhtbs() + "";
					result[col][15] = byqfdw.getXkxhhtje() + "";
				}
			}
		}
		for (int i = 0; i < result[0].length; ++i) {
			result[3][i] = Util.plus(result[0][i],
					Util.plus(result[2][i], result[1][i]));
		}
		return result;
	}

	public String[][] getGwData(Date d) {
		Organization org = CompanyManager.getBMOrganization();
		List<BYQGWFKFS> byqgwfkfss = byqfkfsDao.getGwfs(d);
		String[][] result = new String[4][18];
		Integer col;
		for (BYQGWFKFS byqgw : byqgwfkfss) {
			if (null != byqgw) {
				Company comp = org.getCompany(Integer.valueOf(byqgw.getGsbm()));
				col = getIndex(comp);
				if (null != col) {
					result[col][0] = byqgw.getGwhtddzlbs() + "";
					result[col][1] = byqgw.getGwhtddzlje() + "";
					result[col][2] = byqgw.getN3_4_2_1bs() + "";
					result[col][3] = byqgw.getN3_4_2_1je() + "";
					result[col][4] = byqgw.getN3_4_2d5_0d5bs() + "";
					result[col][5] = byqgw.getN3_4_2d5_0d5je() + "";
					result[col][6] = byqgw.getN0_9_0_1bs() + "";
					result[col][7] = byqgw.getN0_9_0_1je() + "";
					result[col][8] = byqgw.getN1_4_4_1bs() + "";
					result[col][9] = byqgw.getN1_4_4_1je() + "";
					result[col][10] = byqgw.getN1_4_4d5_0d5bs() + "";
					result[col][11] = byqgw.getN1_4_4d5_0d5je() + "";
					result[col][12] = byqgw.getN0_10_0_0bs() + "";
					result[col][13] = byqgw.getN0_10_0_0je() + "";
					result[col][14] = byqgw.getN9d5_0d5bs() + "";
					result[col][15] = byqgw.getN9d5_0d5je() + "";
					result[col][16] = byqgw.getQtbs() + "";
					result[col][17] = byqgw.getQtje() + "";
				}
			}
		}
		for (int i = 0; i < result[0].length; ++i) {
			result[3][i] = Util.plus(result[0][i],
					Util.plus(result[2][i], result[1][i]));
		}
		return result;
	}

	public String[][] getNwData(Date d) {
		List<BYQNWFKFS> byqgwfkfss = byqfkfsDao.getNwfs(d);
		Organization org = CompanyManager.getBMOrganization();
		String[][] result = new String[4][14];
		Integer col;
		for (BYQNWFKFS byqnw : byqgwfkfss) {
			if (null != byqnw) {
				Company comp = org.getCompany(Integer.valueOf(byqnw.getGsbm()));
				col = getIndex(comp);
				if (null != col) {
					result[col][0] = byqnw.getNwhtddzlbs() + "";
					result[col][1] = byqnw.getNwhtddzlje() + "";
					result[col][2] = byqnw.getN_3_3_3_1bs() + "";
					result[col][3] = byqnw.getN_3_3_3_1je() + "";
					result[col][4] = byqnw.getN_1_4_4_0d5_0d5bs() + "";
					result[col][5] = byqnw.getN_1_4_4_0d5_0d5je() + "";
					result[col][6] = byqnw.getN_1_2_6d5_0d5bs() + "";
					result[col][7] = byqnw.getN_1_2_6d5_0d5je() + "";
					result[col][8] = byqnw.getN_1_4_4d5_0d5bs() + "";
					result[col][9] = byqnw.getN_1_4_4d5_0d5je() + "";
					result[col][10] = byqnw.getQtybs() + "";
					result[col][11] = byqnw.getQtyje() + "";
					result[col][12] = byqnw.getQtebs() + "";
					result[col][13] = byqnw.getQteje() + "";
				}
			}
		}

		for (int i = 0; i < result[0].length; ++i) {
			result[3][i] = Util.plus(result[0][i],
					Util.plus(result[2][i], result[1][i]));
		}

		return result;
	}

}
