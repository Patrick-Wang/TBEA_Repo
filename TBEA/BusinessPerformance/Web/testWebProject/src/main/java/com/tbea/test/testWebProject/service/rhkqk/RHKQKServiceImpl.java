package com.tbea.test.testWebProject.service.rhkqk;

import java.sql.Date;
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
import com.tbea.test.testWebProject.model.dao.rhkxx.RHKXXDao;
import com.tbea.test.testWebProject.model.entity.RHKXX;
@Service
@Transactional("transactionManager")
public class RHKQKServiceImpl implements   RHKQKService{

	
	private static Map<Integer, Integer> compMap = new HashMap<Integer, Integer>();
	static {
		Organization org = CompanyManager.getOperationOrganization();
		compMap.put(org.getCompany(CompanyType.SB).getId(), 0);
		compMap.put(org.getCompany(CompanyType.HB).getId(), 1);
		compMap.put(org.getCompany(CompanyType.XB).getId(), 2);
		compMap.put(org.getCompany(CompanyType.TB).getId(), 3);
		compMap.put(org.getCompany(CompanyType.LL).getId(), 4);
		compMap.put(org.getCompany(CompanyType.XL).getId(), 5);
		compMap.put(org.getCompany(CompanyType.DL).getId(), 6);
	}
	
	@Autowired
	RHKXXDao rhkxxDao;
	
	private double valueOf(Double d){
		if (d == null){
			return 0.0;
		}
		return d.doubleValue();
	}
	private double[] sum(List<RHKXX> rhkxxs) {
		double[] ret = new double[13];
		for (RHKXX rhk : rhkxxs) {
			if (compMap.get(rhk.getQybh()) != null) {
				ret[0] += valueOf(rhk.getJtxdydzjhlzb());
				ret[1] += valueOf(rhk.getGdwzxzddhkjh());
				ret[2] += valueOf(rhk.getHkje());
				ret[3] += valueOf(rhk.getYlj());
				ret[4] = ret[0] == 0.0 ? 0.0 : ret[3] / ret[0];
				ret[5] = ret[1] == 0.0 ? 0.0 : ret[3] / ret[1];
				ret[6] += valueOf(rhk.getYhkzkjysdhkje());
				ret[7] += valueOf(rhk.getQzqbbc());
				ret[8] += valueOf(rhk.getQzzqbc());
				ret[9] += valueOf(rhk.getMqzydhkjhhj());
				ret[10] += valueOf(rhk.getQyqb());
				ret[11] = ret[1] == 0.0 ? 0.0 : ret[10] / ret[1];
				ret[12] += valueOf(rhk.getJzydyszkzmye());
			}
		}
		return ret;
	}
	
	public String[][] getRhkqkData(Date d){
		String[][] ret = new String[8][13];
		List<RHKXX> rhkxxs = rhkxxDao.getRhkxxData(d);
		Integer row;
		if (rhkxxs != null){
			for (RHKXX rhkxx: rhkxxs){
				row = compMap.get(rhkxx.getQybh());
				if (null != row) {
					ret[row][0] = rhkxx.getJtxdydzjhlzb() + "";
					ret[row][1] = rhkxx.getGdwzxzddhkjh() + "";
					ret[row][2] = rhkxx.getHkje() + "";
					ret[row][3] = rhkxx.getYlj() + "";
					ret[row][4] = rhkxx.getZjhlzbwc() + "";
					ret[row][5] = rhkxx.getHkjhwcl() + "";
					ret[row][6] = rhkxx.getYhkzkjysdhkje() + "";
					ret[row][7] = rhkxx.getQzqbbc() + "";
					ret[row][8] = rhkxx.getQzzqbc() + "";
					ret[row][9] = rhkxx.getMqzydhkjhhj() + "";
					ret[row][10] = rhkxx.getQyqb() + "";
					ret[row][11] = rhkxx.getYjqyjhwcl() + "";
					ret[row][12] = rhkxx.getJzydyszkzmye() + "";
				}
			}
			
			double[] dRet =  sum(rhkxxs);
			
			for (int i = 0; i < ret[0].length; ++i){
				if (i == 4 || i == 11){
					ret[7][i] = String.format("%.2f", dRet[i] * 100) + "%";
				}
				else{
					ret[7][i] = dRet[i] + "";
				}
			}
		}
		return ret;		
	}
	@Override
	public Date getLatestDate() {
		RHKXX rhkxx = rhkxxDao.getLatestRhkxx();
		if (null != rhkxx){
			return rhkxx.getHkrq();
		}
		return null;
	}

}
