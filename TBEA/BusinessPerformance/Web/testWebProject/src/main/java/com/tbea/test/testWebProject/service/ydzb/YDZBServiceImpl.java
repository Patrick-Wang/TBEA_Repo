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
	private static Map<String, Integer> zbbh_gcygdwMap = new HashMap<String, Integer>();
	private static Map<String, Integer> qybh_gcyMap = new HashMap<String, Integer>();
	private static Map<String, Integer> qybh_gdwMap = new HashMap<String, Integer>();

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

	static {
		zbbh_gcygdwMap.put("5", 0);//报表利润
		zbbh_gcygdwMap.put("7", 1);
		zbbh_gcygdwMap.put("8", 2);
		zbbh_gcygdwMap.put("23", 3);
		zbbh_gcygdwMap.put("25", 4);
	}
	private static String GFHJ = "gfhj";
	public YDZBServiceImpl(){
		Company.Type[] types = new Company.Type[]{
			Company.Type.SBDCY,
			Company.Type.XNYCY,
			Company.Type.NYCY,
			Company.Type.GCL,
			null,//gfhj need to calculate by program
			Company.Type.ZH,
			Company.Type.JT
		};
		Company cy;
		CompanyGroup cyg;
		Company[] cys;
		for (int i = 0, j = 0; i < types.length; ++i){
			if (types[i] != null){
				cy = Company.get(types[i]);
				qybh_gcyMap.put(cy.getId(), i);
				
				if (cy instanceof CompanyGroup && cy.getId() != Company.get(Company.Type.JT).getId()){
					cyg = (CompanyGroup)cy;
					cys = cyg.getCompanys();
					for (int k = 0; k < cys.length; ++k){
						qybh_gdwMap.put(cys[k].getId(), j++);
					}
				}
				qybh_gdwMap.put(cy.getId(), j++);
			}
			else{
				qybh_gdwMap.put(GFHJ, j++);
				qybh_gcyMap.put(GFHJ, i);
			}
		}
		
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
	
	private String accumulate(String[][] data, int row, int col, String val2){
		try{
			return data[row][col] = String.format("%.2f", Double.valueOf(plus(data[row][col], val2)));
		}
		catch (Exception e){
			return data[row][col] = "--";
		}
	}
	
	private String plus(String val1, String val2){
		double v1 = 0;
		double v2 = 0;
		try{
			v1 = Double.parseDouble(val1);
		}
		catch (Exception e){
			
		}
		try{
			v2 = Double.parseDouble(val2); 
		}
		catch (Exception e){
			
		}
		
		return (v2 + v1) + "";
	}
	
	private String minus(String val1, String val2){
		double v1 = 0;
		double v2 = 0;
		try{
			v1 = Double.parseDouble(val1);
		}
		catch (Exception e){
			
		}
		try{
			v2 = Double.parseDouble(val2); 
		}
		catch (Exception e){
			
		}
		
		return (v1 - v2) + "";
	}
	
	
	private String mult(String val1, String val2){
		double v1 = 0;
		double v2 = 0;
		try{
			v1 = Double.parseDouble(val1);
		}
		catch (Exception e){
			return "--";
		}
		try{
			v2 = Double.parseDouble(val2); 
		}
		catch (Exception e){
			return "--";
		}
		return v2 * v1 + "";
	}
	
	private String division(String base, String sub){
		double v1 = 0;
		double v2 = 0;
		try{
			v1 = Double.parseDouble(base);
		}
		catch (Exception e){
			return "--";
		}
		try{
			v2 = Double.parseDouble(sub); 
		}
		catch (Exception e){
			return "--";
		}
		if (v1 == 0){
			return "--";
		}
		else{
			return v2 / v1 + "";
		}
	}
	
	private String completeRate(String[][] data, int row, int col,  int baseCol, int completeCol){
		try{
			return data[row][col] = String.format("%.2f", Double.valueOf(mult(division(data[row][baseCol], data[row][completeCol]), "100")))  + "%";
		}
		catch (Exception e){
			return data[row][col] = "--";
		}
	}
	
	private void fillZbhzData(String[][] data, List<YDZBBean> ydzbs, int len, Map<String, Integer> qybhMap) {
		
		if (ydzbs == null){
			return;
		}
		
		CompanyGroup sbdcy = (CompanyGroup)Company.get(Company.Type.SBDCY);
		CompanyGroup xnycy = (CompanyGroup)Company.get(Company.Type.XNYCY);
		CompanyGroup nycy = (CompanyGroup)Company.get(Company.Type.NYCY);
		CompanyGroup gcl = (CompanyGroup)Company.get(Company.Type.GCL);
	
		int zb = 0;
		int qybh = 0;
		int index = 0;
		int gfhjIndex = qybhMap.get(GFHJ);
		int glhjRow = 0;
		for (YDZBBean ydzb : ydzbs) {
			if (ydzb != null && zbbh_gcygdwMap.get(ydzb.getZblx()) != null && null != qybhMap.get(ydzb.getXh())) {
				zb = zbbh_gcygdwMap.get(ydzb.getZblx());
				qybh = qybhMap.get(ydzb.getXh());
				index = zb * len + qybh;
				try {
					data[index][0] = ydzb.getNdjh();
					data[index][1] = ydzb.getByjh();
					data[index][2] = ydzb.getBywc();
					data[index][3] = ydzb.getJhwcl();
					data[index][4] = ydzb.getJdjh();
					data[index][5] = ydzb.getJdlj();
					data[index][6] = ydzb.getJdjhwcl();
					data[index][7] = ydzb.getNdlj();
					data[index][8] = ydzb.getNdjhwcl();
					data[index][9] = ydzb.getQntq();
					data[index][10] = ydzb.getJqntqzzb();
					data[index][11] = ydzb.getQntqlj();
					data[index][12] = ydzb.getJqntqljzzb();
					
					if (sbdcy.getId().equals(ydzb.getXh()) ||
						xnycy.getId().equals(ydzb.getXh()) ||
						nycy.getId().equals(ydzb.getXh()) ||
						gcl.getId().equals(ydzb.getXh())){
						glhjRow = zb * len + gfhjIndex;
						accumulate(data, glhjRow, 0, ydzb.getNdjh());
						accumulate(data, glhjRow, 1, ydzb.getByjh());
						accumulate(data, glhjRow, 2, ydzb.getBywc());
						completeRate(data, glhjRow, 3, 1, 2);
						accumulate(data, glhjRow, 4, ydzb.getJdjh());
						accumulate(data, glhjRow, 5, ydzb.getJdlj());
						completeRate(data, glhjRow, 6, 4, 5);
						accumulate(data, glhjRow, 7, ydzb.getNdlj());
						completeRate(data, glhjRow, 8, 0, 7);
						accumulate(data, glhjRow, 9, ydzb.getQntq());
						try{
							data[glhjRow][10] = String.format("%.2f", Double.valueOf(
									mult(
										division(data[glhjRow][9],
												minus(data[glhjRow][2], data[glhjRow][9])),
										"100")))  + "%";
						}
						catch (Exception e){
							data[glhjRow][10] = "--";
						}

						accumulate(data, glhjRow, 11, ydzb.getQntqlj());
						try{
							data[glhjRow][12] = String.format("%.2f", Double.valueOf(mult(minus(this.division(ydzb.getQntqlj(), ydzb.getNdlj()), "1"), "100"))) + "%";
						}
						catch (Exception e){
							data[glhjRow][12] = "--";
						}
					}
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	

	@Override
	public String[][] getGcy_zbhzData(Date d) {
		String[][] result = new String[7 * zbbh_gcygdwMap.size()][13];
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<YDZBBean> ydzbs = ydzbDao.getYDZB(cal, Company.get(Company.Type.JT));
		fillZbhzData(result, ydzbs, 7, qybh_gcyMap);
		return result;
	}

	@Override
	public String[][] getGdw_zbhzData(Date d) {
		String[][] result = new String[21 * zbbh_gcygdwMap.size()][13];
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		List<YDZBBean> ydzbs = ydzbDao.getYDZB(cal, Company.get(Company.Type.JT));
		fillZbhzData(result, ydzbs, 21, qybh_gdwMap);
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
