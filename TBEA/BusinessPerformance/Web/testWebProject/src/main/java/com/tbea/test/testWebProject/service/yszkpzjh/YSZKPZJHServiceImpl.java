package com.tbea.test.testWebProject.service.yszkpzjh;

import java.sql.Date;




import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.companys.Company;
import com.tbea.test.testWebProject.controller.servlet.yszkpzjh.YSZKPZJHBean;
import com.tbea.test.testWebProject.model.dao.hkjhjgb.HKJHJGDao;
import com.tbea.test.testWebProject.model.dao.yszkpzjh.YSZKPZJHDao;
import com.tbea.test.testWebProject.model.entity.HKJHJG;
import com.tbea.test.testWebProject.model.entity.YSZKPZGH;

@Service
@Transactional("transactionManager")
public class YSZKPZJHServiceImpl implements YSZKPZJHService {
	@Autowired
	YSZKPZJHDao yszkpzjhDao;
	
	@Autowired
	private HKJHJGDao hkjhjgDao;
	
	@Override
	public YSZKPZJHBean getYszkpzjhData(Date d, Company comp) {
		YSZKPZJHBean bean = new YSZKPZJHBean();
		bean.setCompanyType(comp.getType().ordinal());
		List<YSZKPZGH> pzghs = yszkpzjhDao.getPzjhData(d, comp);
		if (!pzghs.isEmpty()){
			YSZKPZGH yszkpzgh = pzghs.get(0);
			Double D6 = (yszkpzgh.getSymljxssr() + yszkpzgh.getByjhxssr())/9*12*0.18;
			Double E6 = yszkpzgh.getByysnkzb();
			Double B8 = yszkpzgh.getSymzmysye();
			Double H10 = B8 - 
					yszkpzgh.getSymykpwfhcsysje() + 
					yszkpzgh.getSymyfhwkpzjsjysje() + 
					yszkpzgh.getSymblhkcjysje() +
					yszkpzgh.getSymyscjysje() +
					yszkpzgh.getQtcjys();
			Double K14 = 0.0;
			List<HKJHJG> hkjhjgs = hkjhjgDao.getHkjhjg(d, comp);
			if (!hkjhjgs.isEmpty()){
				K14 = hkjhjgs.get(0).getByhlxj();
			}
			
			
			Double K10 = H10  + 
					yszkpzgh.getByfhcpxzysje() -
					yszkpzgh.getByhkjdysje();
			
			Double G8 = B8	 +
					yszkpzgh.getByxssrxzysje() -
					yszkpzgh.getBykjyszjhlje() +
					yszkpzgh.getByghblzjysje() -
					yszkpzgh.getByxzblhkcjysje();
			
			bean.setList1(new String[]{
					yszkpzgh.getSymljxssr() + "",
					yszkpzgh.getByjhxssr() + "",
					D6 + "",
					E6 + "",
					K14 + ""
			});
			
			bean.setList2(new String[]{
					yszkpzgh.getByxssrxzysje() + "",
					yszkpzgh.getBykjyszjhlje() + "",
					yszkpzgh.getByghblzjysje() + "",
					yszkpzgh.getByxzblhkcjysje() + "",
					G8 + "",
					G8 - D6 + "",
					G8 - E6 + ""
			});
			bean.setList3(new String[]{B8 + ""});
			bean.setList4(new String[]{
					yszkpzgh.getSymykpwfhcsysje() + "",
					yszkpzgh.getSymyfhwkpzjsjysje() + "",
					yszkpzgh.getSymblhkcjysje() + "",
					yszkpzgh.getSymyscjysje() + "",
					yszkpzgh.getQtcjys() + "",
					H10 + "",
					yszkpzgh.getByfhcpxzysje() + "",
					yszkpzgh.getByhkjdysje() + "",
					K10 + "",
					K10 - D6 + "",
					K10 - E6 + ""
			});
			
		}
		else{
			bean.setList1(new String[5]);
			bean.setList2(new String[7]);
			bean.setList3(new String[1]);
			bean.setList4(new String[11]);
		}

		return bean;
	}

}
