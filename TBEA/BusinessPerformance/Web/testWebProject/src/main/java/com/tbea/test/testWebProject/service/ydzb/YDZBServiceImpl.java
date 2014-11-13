package com.tbea.test.testWebProject.service.ydzb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.model.dao.ydzb.YDZBDao;
import com.tbea.test.testWebProject.model.entity.XJL;
import com.tbea.test.testWebProject.model.entity.YDZBBean;
import com.tbea.test.testWebProject.service.ydzb.Company.Type;



@Service
@Transactional("transactionManager2")
public class YDZBServiceImpl implements YDZBService {

	@Autowired
	private YDZBDao ydzbDao;
	private static Map<String, Integer> zbbh_hzMap = new HashMap<String, Integer>();
	private static Map<String, String> zbid_mcMap = new HashMap<String, String>();
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
		
		zbid_mcMap.put("5", "报表利润");
		zbid_mcMap.put("7", "销售收入");
		zbid_mcMap.put("8", "现金流");
		zbid_mcMap.put("23", "应收帐款");
		zbid_mcMap.put("25", "存货");
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

	private List<List<YDZBBean>> getOverviewData(int year, int monthFrom, int monthTo, Company company){
		Calendar month = Calendar.getInstance();
		List<List<YDZBBean>> preYearODs = new ArrayList<List<YDZBBean>>();
		for (int i = monthFrom; i <= monthTo; ++i){
			month.set(year, i, 1);
			preYearODs.add(ydzbDao.getYDZB_V2(month, company));
		}
		return preYearODs;
	}
	
	
	private String fromatNumber(String n){
		if (n != null){
			if (n.equals("--")){
				n = "0";
			} else if (n.contains("%")){
				n = (Double.parseDouble(n.replace("%", ""))) + "";
			}
		}
		return n;
	}

	@Override
	public String[][] getYdZbhz_overviewData(Date d, Company company, String zb) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		String[][] ret = new String[3][cal.get(Calendar.MONTH) + 1];
		List<List<YDZBBean>> curYearOds = getOverviewData(cal.get(Calendar.YEAR), 0, cal.get(Calendar.MONTH), company);
		List<YDZBBean> monthYdzbs;
		for (int month = curYearOds.size() - 1; month >= 0; --month){
			monthYdzbs = curYearOds.get(month);
			for (YDZBBean ydzb : monthYdzbs){
				if (zb.equals(ydzb.getZblx())){
					ret[0][month] = fromatNumber(ydzb.getByjh());
					ret[1][month] = fromatNumber(ydzb.getBywc());
					ret[2][month] = fromatNumber(ydzb.getJhwcl());
					break;
				}
			}
		}		
		return ret;
	}

	private List<List<YDZBBean>> getQuarterData(int year, int month, Company company){
		Calendar cal = Calendar.getInstance();
		List<List<YDZBBean>> ods = new ArrayList<List<YDZBBean>>();
		int quarterCount = (month + 1) / 3;
		int lastQuarter = (month + 1) % 3; 
		for (int i = 1; i <= quarterCount; ++i){
			cal.set(year, i * 3 - 1, 1);
			ods.add(ydzbDao.getYDZB_V2(cal, company));
		}
		
		if (lastQuarter > 0){
			cal.set(year, month, 1);
			ods.add(ydzbDao.getYDZB_V2(cal, company));
		}
		return ods;
	}
	

	@Override
	public String[][] getJdZbhz_overviewData(Date d, Company company, String zb) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<List<YDZBBean>> jdData = getQuarterData(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), company);
		String[][] ret = new String[3][jdData.size()];
		List<YDZBBean> jdYdzbs;
		for (int jd = jdData.size() - 1; jd >= 0; --jd){
			jdYdzbs = jdData.get(jd);
			for (YDZBBean ydzb : jdYdzbs){
				if (zb.equals(ydzb.getZblx())){
					ret[0][jd] = fromatNumber(ydzb.getJdjh());
					ret[1][jd] = fromatNumber(ydzb.getJdlj());
					ret[2][jd] = fromatNumber(ydzb.getJdjhwcl());
					break;
				}
			}
		}		
		return ret;
	}


	@Override
	public String[][] getNdZbhz_overviewData(Date d, Company company, String zb) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<List<YDZBBean>> yearData = new ArrayList<List<YDZBBean>>();
		cal.set(cal.get(Calendar.YEAR) - 2, 11, 1);
		yearData.add(ydzbDao.getYDZB_V2(cal, company));
		cal.set(cal.get(Calendar.YEAR) + 1, 11, 1);
		yearData.add(ydzbDao.getYDZB_V2(cal, company));
		cal.set(cal.get(Calendar.YEAR) + 2, 11, 1);
		yearData.add(ydzbDao.getYDZB_V2(cal, company));
		String[][] ret = new String[3][yearData.size()];
		List<YDZBBean> ydzbs;
		for (int i = yearData.size() - 1; i >= 0; --i) {
			ydzbs = yearData.get(i);
			for (YDZBBean ydzb : ydzbs) {
				if (zb.equals(ydzb.getZblx())) {
					ret[0][i] = fromatNumber(ydzb.getNdjh());
					ret[1][i] = fromatNumber(ydzb.getNdlj());
					ret[2][i] = fromatNumber(ydzb.getNdjhwcl());
					break;
				}
			}
		}
		return ret;
	}


	@Override
	public String[][] getYdtbZbhz_overviewData(Date d, Company company, String zb) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		String[][] ret = new String[3][cal.get(Calendar.MONTH) + 1];
		
		List<List<YDZBBean>> curYearOds = getOverviewData(cal.get(Calendar.YEAR), 0, cal.get(Calendar.MONTH), company);
		List<YDZBBean> monthYdzbs;
		for (int month = curYearOds.size() - 1; month >= 0; --month){
			monthYdzbs = curYearOds.get(month);
			for (YDZBBean ydzb : monthYdzbs){
				if (zb.equals(ydzb.getZblx())){
					ret[0][month] = fromatNumber(ydzb.getBywc());
					ret[1][month] = fromatNumber(ydzb.getQntq());
					ret[2][month] = fromatNumber(ydzb.getJqntqzzb());
					break;
				}
			}
		}
		return ret;
	}


	@Override
	public String[][] getJdtbZbhz_overviewData(Date d, Company company, String zb) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<List<YDZBBean>> curYearJdData = getQuarterData(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), company);
		String[][] ret = new String[3][curYearJdData.size()];
		List<YDZBBean> jdYdzbs;
		for (int jd = curYearJdData.size() - 1; jd >= 0; --jd){
			jdYdzbs = curYearJdData.get(jd);
			for (YDZBBean ydzb : jdYdzbs){
				if (zb.equals(ydzb.getZblx())){
					ret[1][jd] = fromatNumber(ydzb.getJdlj());
					break;
				}
			}
		}
		
		List<List<YDZBBean>> preYearJdData = getQuarterData(cal.get(Calendar.YEAR) - 1, (cal.get(Calendar.MONTH) + 4) / 3 * 3 - 1 , company);
		for (int jd = preYearJdData.size() - 1; jd >= 0; --jd){
			jdYdzbs = preYearJdData.get(jd);
			for (YDZBBean ydzb : jdYdzbs){
				if (zb.equals(ydzb.getZblx())){
					ret[0][jd] = fromatNumber(ydzb.getJdlj());
					if (ret[1][jd].equals("0")){
						ret[2][jd] = "0";
					}
					else{
						ret[2][jd] = Util.doubleFormat(
								Util.mult("100", 
										Util.division(ret[0][jd], 
												Util.minus(ret[1][jd], ret[0][jd]))));
						if (ret[0][jd].charAt(0) == '-'){
							ret[2][jd] = Util.minus("100", ret[2][jd]);
						}
					}
					break;
				}
			}
		}
		return ret;
	}

	@Override
	public String getZbmc(String id){
		return zbid_mcMap.get(id);
	}


	@Override
	public void batEnd() {
		ydzbDao.uninit();
		
	}


	@Override
	public void batBegin() {
		ydzbDao.init();
		
	}
	
}
