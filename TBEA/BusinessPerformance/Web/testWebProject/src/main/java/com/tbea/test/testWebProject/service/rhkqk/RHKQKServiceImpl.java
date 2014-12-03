package com.tbea.test.testWebProject.service.rhkqk;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.model.dao.rhkxx.RHKXXDao;
import com.tbea.test.testWebProject.model.entity.RHKXX;
@Service
@Transactional("transactionManager")
public class RHKQKServiceImpl implements   RHKQKService{

	
	private static Map<String, Integer> compMap = new HashMap<String, Integer>();
	static {
		compMap.put(Company.get(Company.Type.SB).getId(), 0);
		compMap.put(Company.get(Company.Type.HB).getId(), 1);
		compMap.put(Company.get(Company.Type.XB).getId(), 2);
		compMap.put(Company.get(Company.Type.TB).getId(), 3);
		compMap.put(Company.get(Company.Type.LL).getId(), 4);
		compMap.put(Company.get(Company.Type.XL).getId(), 5);
		compMap.put(Company.get(Company.Type.DL).getId(), 6);
	}
	
	@Autowired
	RHKXXDao rhkxxDao;
	
	
	private Double[] sum(List<RHKXX> rhkxxs) {
		Double[] ret = new Double[13];
		for (RHKXX rhk : rhkxxs) {
			if (compMap.get(rhk.getQybh()) != null) {
				ret[0] += rhk.getJtxdydzjhlzb();
				ret[1] += rhk.getGdwzxzddhkjh();
				ret[2] += rhk.getHkje();
				ret[3] += rhk.getYlj();
				ret[4] = ret[0] == 0.0 ? 0.0 : ret[3] / ret[0];
				ret[5] = ret[1] == 0.0 ? 0.0 : ret[3] / ret[1];
				ret[6] += rhk.getYhkzkjysdhkje();
				ret[7] += rhk.getQzqbbc();
				ret[8] += rhk.getQzzqbc();
				ret[9] += rhk.getMqzydhkjhhj();
				ret[10] += rhk.getQyqb();
				ret[11] = ret[1] == 0.0 ? 0.0 : ret[10] / ret[1];
				ret[12] += rhk.getJzydyszkzmye();
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
			
			Double[] dRet =  sum(rhkxxs);
			
			for (int i = 0; i < ret[0].length; ++i){
				ret[7][i] = dRet[i] + "";
			}
		}
		return ret;		
	}

}
