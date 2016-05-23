package com.tbea.ic.operation.service.jygk.zzy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.jygk.zzy.ChYclchDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ReferBglxflDao;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyDwReferBglxfl;

@Service
@Transactional("transactionManager")
public class ChYclchServiceImpl implements ChYclchService{
	@Autowired
	ChYclchDao zzyYclchDao;
	@Autowired
	ReferBglxflDao referBglxflDao;

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
		List<JygkZzyDwReferBglxfl> bglxflList=referBglxflDao.getDataList(Integer.parseInt(dwxxid), 20019);
		
		String[] val;
		double ydkc1 = 0;
		double nckc1 = 0;
		double ydkc2 = 0;
		List<Object[]> data1list = zzyYclchDao.getViewDataList(year, month, dwxxs);
		List<Object[]> data2list = zzyYclchDao.getViewDataList(Integer.parseInt(year)-1+"", month, dwxxs);
		
		for(JygkZzyDwReferBglxfl fl : bglxflList) {
			val = new String[6];
			val[0] = fl.getJygkZzyFl().getName();
			for(Object[] data1 : data1list) {
				if(data1[0].equals(fl.getJygkZzyFl().getId())) {
					if(data1[1]==null || "".equals(data1[1])) {
						val[1] = null;
					} else {
						ydkc1 = Double.parseDouble(data1[1].toString());
						val[1] = ydkc1 + "";
					}
					if(data1[2]==null || "".equals(data1[2])) {
						val[2] = null;
					} else {
						nckc1 = Double.parseDouble(data1[2].toString());
						val[2] = nckc1 + "";
					}
					break;
				}
			}
			for(Object[] data2 : data2list) {
				if(data2[0].equals(fl.getJygkZzyFl().getId())) {
					if(data2[1]==null || "".equals(data2[1])) {
						val[4] = null;
					} else {
						ydkc2 = Double.parseDouble(data2[1].toString());
						val[4] = ydkc2 + "";
					}
					break;
				}
			}
			
			if(nckc1 != 0 && val[1] != null) {
				val[3] = ydkc1 / nckc1 -1 + "";
			} else {
				val[3] = null;
			}
			if(ydkc2 != 0 && val[1]!= null) {
				val[5] = ydkc1 / ydkc2 -1 + "";
			} else {
				val[5] = null;
			}
			list.add(val);
		}
		
		return list;
	}
}
