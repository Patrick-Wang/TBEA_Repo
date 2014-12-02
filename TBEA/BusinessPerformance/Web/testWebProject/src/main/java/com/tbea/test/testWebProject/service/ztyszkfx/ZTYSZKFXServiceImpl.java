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

import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.model.dao.ztyszkfx.ZTYSZKFXDao;
import com.tbea.test.testWebProject.model.entity.ZTYSZKFX;

@Service
@Transactional("transactionManager")
public class ZTYSZKFXServiceImpl implements ZTYSZKFXService{

	
	private static Map<String, Integer> compMap = new HashMap<String, Integer>();
	static {
		compMap.put(Company.get(Company.Type.SB).getId(), 0);
		compMap.put(Company.get(Company.Type.HB).getId(), 1);
		compMap.put(Company.get(Company.Type.XB).getId(), 2);
		compMap.put(Company.get(Company.Type.TB).getId(), 3);
		compMap.put(Company.get(Company.Type.LL).getId(), 5);
		compMap.put(Company.get(Company.Type.XL).getId(), 6);
		compMap.put(Company.get(Company.Type.DL).getId(), 7);
	}
	
	
	@Autowired
	ZTYSZKFXDao ztysDao;
	
	private Double[] sum(List<ZTYSZKFX> ztyszkfxs, int curMon) {
		Double[] ret = new Double[14];
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
				ret[10] = (ztys.getByzmyszkye() - ztys.getQntqzmyszkye())
						/ ztys.getQntqzmyszkye();
				ret[11] = (ztys.getByblkzye() - ztys.getQntqblye())
						/ ztys.getByblkzye();
				ret[12] = (ztys.getByyszksjs() - ztys.getQntqyszksjs())
						/ ztys.getByyszksjs();
				ret[13] = (ztys.getLjsr() - ztys.getQntqsr()) / ztys.getLjsr();
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
		Double[] retbyq = sum(byqZtyszkfxs, curMon);
		Double[] retXl = sum(xlZtyszkfxs, curMon);
		Double[] retSbd = sum(ztyszkfxs, curMon);
		for (int i = 0; i < 14; ++i){
			ret[4][i] = retbyq[i] + "";
			ret[8][i] = retXl[i] + "";
			ret[9][i] = retSbd[i] + "";
		}
		
		return ret;
	}

}
