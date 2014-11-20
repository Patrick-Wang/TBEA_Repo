package com.tbea.test.testWebProject.service.yszkpzjh;

import java.sql.Date;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.controller.servlet.yszkpzjh.YSZKPZJHBean;

@Service
@Transactional("transactionManager")
public class YSZKPZJHServiceImpl implements YSZKPZJHService {

	@Override
	public YSZKPZJHBean getYszkpzjhData(Date d, Company comp) {
		YSZKPZJHBean bean = new YSZKPZJHBean();
		bean.setList1(new String[]{"1.000","2.000","6.000","4.000","5.000"});
		bean.setList2(new String[]{"1.000","2.000","6.000","4.000","5.000","6.000","7.000"});
		bean.setList3(new String[]{"1.000"});
		bean.setList4(new String[]{"1.000","2.000","6.000","4.000","5.000","6.000","7.000","1.000","2.000","6.000","4.000"});
		return bean;
	}

}
