package com.tbea.test.testWebProject.service.ztyszkfx;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.common.companys.CompanyManager;
import com.tbea.test.testWebProject.common.companys.Organization;
import com.tbea.test.testWebProject.common.companys.CompanyManager.CompanyType;
import com.tbea.test.testWebProject.model.dao.ztyszkfx.ZTYSZKFXDao;
import com.tbea.test.testWebProject.model.entity.ZTYSZKFX;

@Service
@Transactional("transactionManager")
public class ZTYSZKFXServiceImpl implements ZTYSZKFXService{

	
	private static Map<Integer, Integer> compMap = new HashMap<Integer, Integer>();
	static {
		Organization org = CompanyManager.getOperationOrganization();
		compMap.put(org.getCompany(CompanyType.SB).getId(), 0);
		compMap.put(org.getCompany(CompanyType.HB).getId(), 1);
		compMap.put(org.getCompany(CompanyType.XB).getId(), 2);
		compMap.put(org.getCompany(CompanyType.TB).getId(), 3);
		compMap.put(org.getCompany(CompanyType.LL).getId(), 5);
		compMap.put(org.getCompany(CompanyType.XL).getId(), 6);
		compMap.put(org.getCompany(CompanyType.DL).getId(), 7);
	}
	
	
	@Autowired
	ZTYSZKFXDao ztysDao;
	
	private double[] sum(List<ZTYSZKFX> ztyszkfxs, int curMon) {
		double[] ret = new double[14];
		for (ZTYSZKFX ztys : ztyszkfxs) {
			if (compMap.get(ztys.getQybh()) != null) {
				ret[0] += ztys.getByzmyszkye();
				ret[1] += ztys.getByblkzye();
				ret[2] += ztys.getByyszksjs();
				ret[3] += ztys.getLjsr();
				ret[4] = ret[0] / (ret[3] / curMon * 12);
				ret[5] += ztys.getQntqzmyszkye();
				ret[6] += ztys.getQntqblye();
				ret[7] += ztys.getQntqyszksjs();
				ret[8] += ztys.getQntqsr();
				ret[9] = ret[5] / (ret[8] / curMon * 12);
				ret[10] = (ret[0] - ret[5])
						/ ret[5] ;
				ret[11] = (ret[1] - ret[6])
						/ ret[6] ;
				ret[12] = (ret[2] - ret[7])
						/ ret[7];
				ret[13] = (ret[3] - ret[8]) / ret[8];
			}
		}
		return ret;
	}
	
	public String[][] getZtyszkfxData(Date d){

		String[][] ret = new String[10][14];
		List<ZTYSZKFX> ztyszkfxs = ztysDao.getZtyszkfxData(d);
		Integer row = 0;
		List<ZTYSZKFX> byqZtyszkfxs = new ArrayList<ZTYSZKFX>();
		List<ZTYSZKFX> xlZtyszkfxs = new ArrayList<ZTYSZKFX>();

		for (ZTYSZKFX ztys : ztyszkfxs){
			row = compMap.get(ztys.getQybh());
			if (null != row) {
				ret[row][0] = ztys.getByzmyszkye() + "";
				ret[row][1] = ztys.getByblkzye() + "";
				ret[row][2] = ztys.getByyszksjs() + "";
				ret[row][3] = ztys.getLjsr() + "";
				ret[row][4] = ztys.getZmyszsrb() + "";
				ret[row][5] = ztys.getQntqzmyszkye() + "";
				ret[row][6] = ztys.getQntqblye() + "";
				ret[row][7] = ztys.getQntqyszksjs() + "";
				ret[row][8] = ztys.getQntqsr() + "";
				ret[row][9] = ztys.getQntqzmyszsrb() + "";
				ret[row][10] = ztys.getZmyejqntqzzb() + "";
				ret[row][11] = ztys.getBljqntqzzb() + "";
				ret[row][12] = ztys.getSjysjqntqzzb() + "";
				ret[row][13] = ztys.getSrjqntqzzb() + "";
				
				if (row < 4){
					byqZtyszkfxs.add(ztys);
				}else if (row > 4 && row < 8)
				{
					xlZtyszkfxs.add(ztys);
				}
			}
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int curMon = cal.get(Calendar.MONTH) + 1;
		double[] retbyq = sum(byqZtyszkfxs, curMon);
		double[] retXl = sum(xlZtyszkfxs, curMon);
		double[] retSbd = sum(ztyszkfxs, curMon);
		for (int i = 0; i < 14; ++i){
			if (i >= 9 || i == 4){
				ret[4][i] = String.format("%.2f", retbyq[i] * 100) + "%";
				ret[8][i] = String.format("%.2f", retXl[i] * 100) + "%";
				ret[9][i] = String.format("%.2f", retSbd[i] * 100) + "%";
			}else {
				ret[4][i] = retbyq[i] + "";
				ret[8][i] = retXl[i] + "";
				ret[9][i] = retSbd[i] + "";
			}
			
		}
		
		return ret;
	}

	@Override
	public Date getLatestDate() {
		ZTYSZKFX yszk = ztysDao.getLatestYszk();
		if (null != yszk){
			return yszk.getGxrq();
		}
		return null;
	}

}
