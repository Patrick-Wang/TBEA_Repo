package com.tbea.test.testWebProject.service.ydzb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.common.CompanyGroup;
import com.tbea.test.testWebProject.common.Util;
import com.tbea.test.testWebProject.model.entity.YDZBBean;

public abstract class ZbfdwhzZBHZStrategy implements ZBHZStrategy {
	
	protected static Map<String, Integer> zbbh_gcygdwMap = new HashMap<String, Integer>();
	static {
		zbbh_gcygdwMap.put("5", 0);//报表利润
		zbbh_gcygdwMap.put("7", 1);
		zbbh_gcygdwMap.put("8", 2);
		zbbh_gcygdwMap.put("23", 3);
		zbbh_gcygdwMap.put("25", 4);
	}
	protected static String GFHJ = "gfhj";
	protected static Company.Type[] CY_TYPES = new Company.Type[]{
		Company.Type.SBDCY,
		Company.Type.XNYCY,
		Company.Type.NYCY,
		Company.Type.GCL,
		null,//gfhj need to be calculated by program
		Company.Type.ZH,
		Company.Type.JT
	};
	
	private CompanyGroup sbdcy = (CompanyGroup)Company.get(Company.Type.SBDCY);
	private CompanyGroup xnycy = (CompanyGroup)Company.get(Company.Type.XNYCY);
	private CompanyGroup nycy = (CompanyGroup)Company.get(Company.Type.NYCY);
	private CompanyGroup gcl = (CompanyGroup)Company.get(Company.Type.GCL);
	
	private String accumulate(String[][] data, int row, int col, String val2){
		try{
			return data[row][col] = String.format("%.2f", Double.valueOf(Util.plus(data[row][col], val2)));
		}
		catch (Exception e){
			return data[row][col] = "--";
		}
	}
	
	
	
	private String completeRate(String[][] data, int row, int col,  int baseCol, int completeCol){
		try{
			return data[row][col] = String.format("%.2f", Double.valueOf(Util.mult(Util.division(data[row][baseCol], data[row][completeCol]), "100")))  + "%";
		}
		catch (Exception e){
			return data[row][col] = "--";
		}
	}
	
	private void fillZbhzRow(String[][] data, int index, YDZBBean ydzb){
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
	}
		
	private void fillGfhzRow(String[][] data, int index, YDZBBean ydzb){
		if (sbdcy.getId().equals(ydzb.getXh()) ||
				xnycy.getId().equals(ydzb.getXh()) ||
				nycy.getId().equals(ydzb.getXh()) ||
				gcl.getId().equals(ydzb.getXh())){
				accumulate(data, index, 0, ydzb.getNdjh());
				accumulate(data, index, 1, ydzb.getByjh());
				accumulate(data, index, 2, ydzb.getBywc());
				completeRate(data, index, 3, 1, 2);
				accumulate(data, index, 4, ydzb.getJdjh());
				accumulate(data, index, 5, ydzb.getJdlj());
				completeRate(data, index, 6, 4, 5);
				accumulate(data, index, 7, ydzb.getNdlj());
				completeRate(data, index, 8, 0, 7);
				accumulate(data, index, 9, ydzb.getQntq());
				try{
					data[index][10] = String.format("%.2f", Double.valueOf(
							Util.mult(
									Util.division(data[index][9],	
											Util.minus(data[index][2], data[index][9])),
								"100")))  + "%";
				}
				catch (Exception e){
					data[index][10] = "--";
				}

				accumulate(data, index, 11, ydzb.getQntqlj());
				try{
					data[index][12] = String.format("%.2f", Double.valueOf(
							Util.mult(
									Util.minus(
											Util.division(ydzb.getQntqlj(), ydzb.getNdlj()), 
											"1"), 
									"100"))) + "%";
				}
				catch (Exception e){
					data[index][12] = "--";
				}
			}
	}
	
	protected void fillData(String[][] data, List<YDZBBean> ydzbs, int len, Map<String, Integer> qybhMap) {
		int zb = 0;
		int gfhjIndex = qybhMap.get(GFHJ);
		for (YDZBBean ydzb : ydzbs) {
			if (ydzb != null && zbbh_gcygdwMap.get(ydzb.getZblx()) != null && null != qybhMap.get(ydzb.getXh())) {
				zb = zbbh_gcygdwMap.get(ydzb.getZblx());
				try {
					fillZbhzRow(data, zb * len + qybhMap.get(ydzb.getXh()), ydzb);
					fillGfhzRow(data, zb * len + gfhjIndex, ydzb);
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}

}
