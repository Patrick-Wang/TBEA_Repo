package com.tbea.ic.operation.service.jygk.zzy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.jygk.zzy.ChZljjDao;

@Service
@Transactional("transactionManager")
public class ChZljjServiceImpl implements ChZljjService{
	@Autowired
	ChZljjDao zzyZljjDao;

	@Override
	public List<String[]> getViewDataList(String year, String month, String dwxxid) {
		String dwxxs = "";
		if(dwxxid.equals("900000")){//变压器产业
			dwxxs="1,2,3";
		}else if(dwxxid.equals("800000")){//线缆产业
			dwxxs="4,5,6";
		}else{
			dwxxs=dwxxid;
		}
		
		List<String[]> list = new ArrayList<String[]>();
		String[] val = new String[25];
		Object[] data = zzyZljjDao.getViewData(year, month, dwxxs);
		if(data != null){
			for(int j = 0; j < data.length; j++){
				if(data[j] == null) {
					val[j] = null;
				} else {
					val[j] = data[j] + "";
				}
			}
		}
		list.add(val);
		
		return list;
	}
}
