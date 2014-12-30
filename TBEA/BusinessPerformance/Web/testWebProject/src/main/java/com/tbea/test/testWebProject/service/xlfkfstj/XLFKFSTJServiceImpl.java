package com.tbea.test.testWebProject.service.xlfkfstj;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.model.dao.xlfkfstj.XLFKFSDao;
import com.tbea.test.testWebProject.model.entity.XLFDWFKFS;
import com.tbea.test.testWebProject.model.entity.XLGWFKFS;
import com.tbea.test.testWebProject.model.entity.XLNWFKFS;

@Service
@Transactional("transactionManager")
public class XLFKFSTJServiceImpl implements XLFKFSTJService {

//	国网	01
//	南网	02
//	火电	03
//	水电	04
//	核电	05
//	风电	06
//	光电	07
//	轨道交通	08
//	石油石化	09
//	煤炭及煤化工	10
//	钢铁冶金	11
//	航天军工	12
//	其他	13


	private static Map<Integer, Integer> hymap = new HashMap<Integer, Integer>();

	static {
		hymap.put(3,0);
		hymap.put(4,1);
		hymap.put(5,2);
		hymap.put(6,3);
		hymap.put(7,3);
		hymap.put(8,4);
		hymap.put(9,5);
		hymap.put(10,6);
		hymap.put(11,7);
		hymap.put(12,8);
		hymap.put(14,9);
		hymap.put(13,10);

	}
	
	
	@Autowired
	XLFKFSDao xlfkfsDao;
	
	public String[][] getFdwData(Date d, Company comp){
		List<XLFDWFKFS> fdwfkfss = xlfkfsDao.getFdwfkfs(d, comp);
		String[][] result = new String[12][22];
		int col = 0;
		for (XLFDWFKFS xlfdw : fdwfkfss) {
			if (null != xlfdw && hymap.containsKey(Integer.valueOf(xlfdw.getKhbh()))) {
				col = hymap.get(Integer.valueOf(xlfdw.getKhbh()));
				if (3 == col){
					result[col][0] = Util.plus(result[col][0], xlfdw.getDdzlbs() + "");
					result[col][1] = Util.plus(result[col][1], xlfdw.getDdzlje() + "");
					result[col][2] = Util.plus(result[col][2], xlfdw.getWyfkhtbs() + "");
					result[col][3] = Util.plus(result[col][3], xlfdw.getWyfkhtje() + "");
					result[col][4] = Util.plus(result[col][4], xlfdw.getYfkxybfzshtbs() + "");
					result[col][5] = Util.plus(result[col][5], xlfdw.getYfkxybfzshtje() + "");
					result[col][6] = Util.plus(result[col][6], xlfdw.getYfkzbfzsdsszjhtbs() + "");
					result[col][7] = Util.plus(result[col][7], xlfdw.getYfkzbfzsdsszjhtje() + "");
					result[col][8] = Util.plus(result[col][8], xlfdw.getHwjfhfkblxybfzbshtbs() + "");
					result[col][9] = Util.plus(result[col][9], xlfdw.getHwjfhfkblxybfzbshtje() + "");
					result[col][10] = Util.plus(result[col][10], xlfdw.getZbjbfzshtbs() + "");
					result[col][11] = Util.plus(result[col][11], xlfdw.getZbjbfzshtje() + "");
					result[col][12] = Util.plus(result[col][12], xlfdw.getZbjbfzwhtbs() + "");
					result[col][13] = Util.plus(result[col][13], xlfdw.getZbjbfzwhtje() + "");
					result[col][14] = Util.plus(result[col][14], xlfdw.getWzbjhtbs() + "");
					result[col][15] = Util.plus(result[col][15], xlfdw.getWzbjhtje() + "");
					result[col][16] = Util.plus(result[col][16], xlfdw.getZbqcgynhtbs() + "");
					result[col][17] = Util.plus(result[col][17], xlfdw.getZbqcgynhtje() + "");
					result[col][18] = Util.plus(result[col][18], xlfdw.getWddsjhtbs() + "");
					result[col][19] = Util.plus(result[col][19], xlfdw.getWddsjhtje() + "");
					result[col][20] = Util.plus(result[col][20], xlfdw.getXkxhhtbs() + "");
					result[col][21] = Util.plus(result[col][21], xlfdw.getXkxhhtje() + "");
				} else{
					result[col][0] = xlfdw.getDdzlbs() + "";
					result[col][1] = xlfdw.getDdzlje() + "";
					result[col][2] = xlfdw.getWyfkhtbs() + "";
					result[col][3] = xlfdw.getWyfkhtje() + "";
					result[col][4] = xlfdw.getYfkxybfzshtbs() + "";
					result[col][5] = xlfdw.getYfkxybfzshtje() + "";
					result[col][6] = xlfdw.getYfkzbfzsdsszjhtbs() + "";
					result[col][7] = xlfdw.getYfkzbfzsdsszjhtje() + "";
					result[col][8] = xlfdw.getHwjfhfkblxybfzbshtbs() + "";
					result[col][9] = xlfdw.getHwjfhfkblxybfzbshtje() + "";
					result[col][10] = xlfdw.getZbjbfzshtbs() + "";
					result[col][11] = xlfdw.getZbjbfzshtje() + "";
					result[col][12] = xlfdw.getZbjbfzwhtbs() + "";
					result[col][13] = xlfdw.getZbjbfzwhtje() + "";
					result[col][14] = xlfdw.getWzbjhtbs() + "";
					result[col][15] = xlfdw.getWzbjhtje() + "";
					result[col][16] = xlfdw.getZbqcgynhtbs() + "";
					result[col][17] = xlfdw.getZbqcgynhtje() + "";
					result[col][18] = xlfdw.getWddsjhtbs() + "";
					result[col][19] = xlfdw.getWddsjhtje() + "";
					result[col][20] = xlfdw.getXkxhhtbs() + "";
					result[col][21] = xlfdw.getXkxhhtje() + "";
				}
				
			}
		}
		
		
		for (int i = 0; i < result[0].length; ++i){
			result[11][i] = Util.plus(
					Util.plus(result[0][i], result[1][i], result[2][i], result[3][i]),
					Util.plus(result[4][i], result[5][i], result[6][i], result[7][i]),
					Util.plus(result[8][i], result[9][i], result[10][i]));
		}
		return result;
	}

	public String[][] getGwData(Date d, Company comp){
		List<XLGWFKFS> gwfkfss = xlfkfsDao.getGwfkfs(d, comp);
		String[][] result = new String[3][22];
		int col = 0;
		for (XLGWFKFS xlgw : gwfkfss) {
			if (null != xlgw) {
				if ("Y".equalsIgnoreCase(xlgw.getSfjzzb())){
					col = 0;
				} else{
					col = 1;
				}
				result[col][0] = xlgw.getGwhtddzlbs() + "";
				result[col][1] = xlgw.getGwhtddzlje() + "";
				result[col][2] = xlgw.getN3_06_0_01bs() + "";
				result[col][3] = xlgw.getN3_06_0_01je() + "";
				result[col][4] = xlgw.getN0_09_0_01bs() + "";
				result[col][5] = xlgw.getN0_09_0_01je() + "";
				result[col][6] = xlgw.getN3_4_2_1bs() + "";
				result[col][7] = xlgw.getN3_4_2_1je() + "";
				result[col][8] = xlgw.getN2_5_2_1bs() + "";
				result[col][9] = xlgw.getN2_5_2_1je() + "";
				result[col][10] = xlgw.getN2_5_2d5_0d5bs() + "";
				result[col][11] = xlgw.getN2_5_2d5_0d5je() + "";
				result[col][12] = xlgw.getN0_10_0_0bs() + "";
				result[col][13] = xlgw.getN0_10_0_0je() + "";
				result[col][14] = xlgw.getN0_9d5_0d5bs() + "";
				result[col][15] = xlgw.getN0_9d5_0d5je() + "";
				result[col][16] = xlgw.getQtbs() + "";
				result[col][17] = xlgw.getQtje() + "";
				result[col][18] = xlgw.getWzbjhtbs() + "";
				result[col][19] = xlgw.getWzbjhtje() + "";
				result[col][20] = xlgw.getZbqcgynhtbs() + "";
				result[col][21] = xlgw.getZbqcgynhtje() + "";
			}
		}
		for (int i = 0; i < result[0].length; ++i){
			result[2][i] = Util.plus(result[0][i], result[2][i], result[1][i]);
		}
		return result;
	}

	public String[][] getNwData(Date d, Company comp){
		List<XLNWFKFS> nwfkfss = xlfkfsDao.getNwfkfs(d, comp);
		String[][] result = new String[3][12];
		int col = 0;
		for (XLNWFKFS xlnw : nwfkfss) {
			if (null != xlnw) {
				if ("Y".equalsIgnoreCase(xlnw.getSfjzzb())){
					col = 0;
				} else{
					col = 1;
				}
				result[col][0] = xlnw.getNwhtddzlbs() + "";
				result[col][1] = xlnw.getNwhtddzlje() + "";
				result[col][2] = xlnw.getN_1_6_2_1bs() + "";
				result[col][3] = xlnw.getN_1_6_2_1je() + "";
				result[col][4] = xlnw.getN_1_2_6_1bs() + "";
				result[col][5] = xlnw.getN_1_2_6_1je() + "";
				result[col][6] = xlnw.getN_0_09_01bs() + "";
				result[col][7] = xlnw.getN_0_09_01je() + "";
				result[col][8] = xlnw.getQtbs() + "";
				result[col][9] = xlnw.getQtje() + "";
				result[col][10] = xlnw.getZbqcgynhtbs() + "";
				result[col][11] = xlnw.getZbqcgynhtje() + "";
			}
		}
		for (int i = 0; i < result[0].length; ++i){
			result[2][i] = Util.plus(result[0][i], result[2][i], result[1][i]);
		}
		return result;
	}

	@Override
	public boolean containsCompany(Company comp) {
		return xlfkfsDao.fdwContainsCompany(comp) || 
				xlfkfsDao.nwContainsCompany(comp) || 
				xlfkfsDao.gwContainsCompany(comp);

	}

}
