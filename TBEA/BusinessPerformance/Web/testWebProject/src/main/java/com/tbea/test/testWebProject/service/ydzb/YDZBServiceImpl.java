package com.tbea.test.testWebProject.service.ydzb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.model.dao.ydzb.YDZBDao;
import com.tbea.test.testWebProject.model.entity.XJL;
import com.tbea.test.testWebProject.model.entity.YDZBBean;



@Service
@Transactional("transactionManager2")
public class YDZBServiceImpl implements YDZBService {

	@Autowired
	private YDZBDao ydzbDao;
	private static Map<String, Integer> zbbh_hzMap = new HashMap<String, Integer>();

//	5	报表利润
//	6	利润总额
//	7	销售收入
//	33	国内收入
//	37	国际收入
//	8	净现金流
//	9	不含税产值
//	22	资金回笼
//	23	应收帐款
//	24	其中逾期款
//	25	存货
//	26	其中积压物资
//	18	合同签约额
//	19	国内签约
//	20	国际签约(万美元)
//	21	现款现货签约
//	10	产量：变压器
//	11	产量：用铜量
//	12	产量：用铝量
//	13	产量：多晶硅
//	15	产量：逆变器
//	16	产量：发电量
//	17	产量：煤炭
//	27	人数
//	28	人均利润
//	29	人均收入
//	30	三项费用
//	31	三项费用率
//	32	净资产收益率
	
	static {
		zbbh_hzMap.put("6", 0);
		zbbh_hzMap.put("8", 1);
		zbbh_hzMap.put("23", 2);
		zbbh_hzMap.put("24", 3);
		zbbh_hzMap.put("25", 4);
		zbbh_hzMap.put("26", 5);
		zbbh_hzMap.put("18", 6);
		zbbh_hzMap.put("22", 7);
		zbbh_hzMap.put("9", 8);
		zbbh_hzMap.put("10", 9);
		zbbh_hzMap.put("11", 10);
		zbbh_hzMap.put("12", 11);
		zbbh_hzMap.put("27", 12);
		zbbh_hzMap.put("28", 13);
		zbbh_hzMap.put("29", 14);
		zbbh_hzMap.put("30", 15);
		zbbh_hzMap.put("31", 16);
	}


	public YDZBServiceImpl(){
	
	}
	
	
	@Override
	public String[][] getHzb_zbhzData(Date d) {
		String[][] result = new String[zbbh_hzMap.size()][11];
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<YDZBBean> ydzbs = ydzbDao.getYDZB_V2(cal, Company.get(Company.Type.JT));
		int index = 0;
		for (YDZBBean ydzb : ydzbs) {
			if (ydzb != null && zbbh_hzMap.get(ydzb.getZblx()) != null) {
				index = zbbh_hzMap.get(ydzb.getZblx());
				result[index][0] = ydzb.getByjh();
				result[index][1] = ydzb.getBywc();
				result[index][2] = ydzb.getJhwcl();
				result[index][3] = ydzb.getJdlj();
				result[index][4] = ydzb.getJdjhwcl();
				result[index][5] = ydzb.getNdlj();
				result[index][6] = ydzb.getNdjhwcl();
				result[index][7] = ydzb.getQntq();
				result[index][8] = ydzb.getJqntqzzb();
				result[index][9] = ydzb.getQntqlj();
				result[index][10] = ydzb.getJqntqljzzb();
			}
		}
		return result;
	}
	


	@Override
	public String[][] getGcy_zbhzData(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<YDZBBean> ydzbs = ydzbDao.getYDZB(cal, Company.get(Company.Type.JT));
		String[][] result = ZBHZStrategyFactory.createGcyZBHZStrategy().getZBHZData(ydzbs);
		return result;
	}

	@Override
	public String[][] getGdw_zbhzData(Date d) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<YDZBBean> ydzbs = ydzbDao.getYDZB(cal, Company.get(Company.Type.JT));
		String[][] result = ZBHZStrategyFactory.createGdwZBHZStrategy().getZBHZData(ydzbs);
		return result;
	}

	@Override
	public String[][] getXjlrbData(Date d) {
	
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<XJL> xjls = ydzbDao.getXJL(cal);
		String[][] result = new String[xjls.size()][10];
		int i = 0;
		for (XJL xjl : xjls){
			result[i][0] = xjl.getDrlr();
			result[i][1] = xjl.getDylr();
			result[i][2] = xjl.getDnlr();
			result[i][3] = xjl.getDrlc();
			result[i][4] = xjl.getDylc();
			result[i][5] = xjl.getDnlc();
			result[i][6] = xjl.getDrjll();
			result[i][7] = xjl.getDyjll();
			result[i][9] = xjl.getBytzs();
			result[i][8] = xjl.getDnjll();
			++i;
		}
		return result;
	}

	@Override
	public String[][] getYszkrb_qkbData(Date d) {
		// TODO Auto-generated method stub
		return null;
	}

}
