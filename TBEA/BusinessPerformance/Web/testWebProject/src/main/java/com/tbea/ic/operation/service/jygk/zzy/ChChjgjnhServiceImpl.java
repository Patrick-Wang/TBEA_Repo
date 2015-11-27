package com.tbea.ic.operation.service.jygk.zzy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.jygk.zzy.ChChjgjnhDao;

@Service
@Transactional("transactionManager")
public class ChChjgjnhServiceImpl implements ChChjgjnhService{
	@Autowired
	ChChjgjnhDao zzyChjgjnhDao;
	
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
		String[] val = new String[10];
		
		val[0] = "去年同期";
	
		Object[] data = zzyChjgjnhDao.getViewData(Integer.parseInt(year)-1+"", month, dwxxs);
		if(data != null){
			for(int j = 0; j < data.length ; j++){
				if(data[j] == null) {
					val[j + 1] = null;
				} else {
					val[j + 1] = data[j] + "";
				}
			}
		}
		list.add(val);
		val = new String[10];
		val[0] = year + "年当期";
		
		Object[] data2 = zzyChjgjnhDao.getViewData(year, month, dwxxs);
		if(data2 != null){
			for(int j = 0; j < data2.length ; j++){
				if(data2[j] == null) {
					val[j + 1] = null;
				} else {
					val[j + 1] = data2[j] + "";
				}
			}
		}
		list.add(val);
		val = new String[10];
		val[0] = "同比";
		double x = 0;
		double y = 0;
		for(int j = 0; j < 9 ; j++){
			if(list.get(1)[j+1]==null || "".equals(list.get(1)[j+1])) {
				val[j + 1] = null;
			} else if(list.get(0)[j+1]==null || "".equals(list.get(0)[j+1])) {
				val[j + 1] = null;
			} else {
				x = Double.parseDouble(list.get(0)[j+1]);
				y = Double.parseDouble(list.get(1)[j+1]);
				val[j + 1] = y/x-1+"";
			}
		}
		list.add(val);
		return list;
	}
}
