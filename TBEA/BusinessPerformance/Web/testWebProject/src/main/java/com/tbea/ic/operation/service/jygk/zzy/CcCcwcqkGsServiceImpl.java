package com.tbea.ic.operation.service.jygk.zzy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.CcCcwcqkGsDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ReferBglxflDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ZzyCksjDao;
import com.tbea.ic.operation.model.dao.qxgl.QXGLDao;
import com.tbea.ic.operation.model.entity.jygk.zzy.*;



@Service
@Transactional("transactionManager")
public class CcCcwcqkGsServiceImpl implements CcCcwcqkGsService{
	@Autowired
	CcCcwcqkGsDao jygkZzyCcCcwcqkGsDao;
	@Autowired
	ReferBglxflDao referBglxflDao;
	
	public static BigDecimal getBigDecimal( Object value ) {
        BigDecimal ret = null;
        if( value != null && !value.equals("")) {
            if( value instanceof BigDecimal ) {
                ret = (BigDecimal) value;
            } else if( value instanceof String ) {
                ret = new BigDecimal( (String) value );
            } else if( value instanceof BigInteger ) {
                ret = new BigDecimal( (BigInteger) value );
            } else if( value instanceof Number ) {
                ret = new BigDecimal( ((Number)value).doubleValue() );
            } else {
                throw new ClassCastException("Not possible to coerce ["+value+"] from class "+value.getClass()+" into a BigDecimal.");
            }
        }
        return ret;
    }
	
	@Override
	public List<String[]> getViewDataList(Date date, String comp, String entryType) {
		String dwxxs = "";
		if(comp.equals("900000")){//变压器产业
			dwxxs="1,2,3";
		}else if(comp.equals("800000")){//线缆产业
			dwxxs="4,5,6";
		}else{
			dwxxs=comp;
		}
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		List<String[]> list = new ArrayList<String[]>();
		List<JygkZzyDwReferBglxfl> zblist = referBglxflDao.getDataList(Integer.parseInt(comp), Integer.parseInt(entryType));
		String[] total = new String[5];
		total[0] = "total";
		total[1] = "合计";
		for(int i = 0; i < zblist.size(); i++){
			String[] val = new String[5];
			val[0] = zblist.get(i).getJygkZzyFl().getId() + "";
			val[1] = zblist.get(i).getJygkZzyFl().getName();
			Object[] data = jygkZzyCcCcwcqkGsDao.getViewData(Integer.parseInt(val[0]), date, dwxxs);
			if(data != null){
				val[2] = data[0] == null ? null : data[0].toString();
				total[2] = total[2] == null ? (data[0] == null ? "0" : data[0].toString()) : (data[0] == null ? total[2] : String.valueOf(Float.parseFloat(total[2]) + Float.parseFloat(data[0].toString())));
			}
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date);
			cal1.add(Calendar.YEAR, -1);
			data = jygkZzyCcCcwcqkGsDao.getViewData(Integer.parseInt(val[0]), new java.sql.Date(cal1.getTimeInMillis()), dwxxs);
			if(data != null){
				val[3] = data[0] == null ? null : data[0].toString();
				total[3] = total[3] == null ? (data[0] == null ? "0" : data[0].toString()) : (data[0] == null ? total[3] : String.valueOf(Float.parseFloat(total[3]) + Float.parseFloat(data[0].toString())));
				if(val[3] == null || Float.parseFloat(val[3].toString()) == 0){
					val[4] = null;
				} else {
					val[4] = getBigDecimal(Double.parseDouble(val[2].toString()) / Double.parseDouble(val[3].toString()) - 1) + "";
				}
			}
			list.add(val);
		}
		if(total[3] == null || Float.parseFloat(total[3].toString()) == 0){
			total[4] = null;
		} else {
			total[4] = getBigDecimal(Double.parseDouble(total[2].toString()) / Double.parseDouble(total[3].toString()) - 1) + "";
		}
		list.add(total);
		return list;
	}
}
