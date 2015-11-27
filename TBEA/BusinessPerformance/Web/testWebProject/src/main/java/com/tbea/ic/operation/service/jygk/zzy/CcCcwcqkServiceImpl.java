package com.tbea.ic.operation.service.jygk.zzy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.jygk.zzy.CcCcwcqkDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ReferBglxflDao;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyDwReferBglxfl;

@Service
@Transactional("transactionManager")
public class CcCcwcqkServiceImpl implements CcCcwcqkService{
	@Autowired
	CcCcwcqkDao jygkZzyCcCcwcqkDao;
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
		String[] total = new String[8];
		String[] total1 = new String[8];
		total[0] = "total";
		total[1] = "合计（铜）";
		total1[0] = "total1";
		total1[1] = "合计（铝）";
		
		for(int i = 0; i < zblist.size(); i++){
			String[] val = new String[8];
			val[0] = zblist.get(i).getJygkZzyFl().getId() + "";
			val[1] = zblist.get(i).getJygkZzyFl().getName();
			Object[] data = jygkZzyCcCcwcqkDao.getViewData(Integer.parseInt(val[0]), date, dwxxs);
			if(data != null){
				val[2] = data[0] == null ? null : data[0].toString();
				val[5] = data[1] == null ? null : data[1].toString();
				if("20006".equals(val[0]) || "20009".equals(val[0]) || "20015".equals(val[0])){
					total1[2] = total1[2] == null ? (data[0] == null ? "0" : data[0].toString()) : (data[0] == null ? total1[2] : String.valueOf(Float.parseFloat(total1[2]) + Float.parseFloat(data[0].toString())));
					total1[5] = total1[5] == null ? (data[1] == null ? "0" : data[1].toString()) : (data[1] == null ? total1[5] : String.valueOf(Float.parseFloat(total1[5]) + Float.parseFloat(data[1].toString())));
				} else {
					total[2] = total[2] == null ? (data[0] == null ? "0" : data[0].toString()) : (data[0] == null ? total[2] : String.valueOf(Float.parseFloat(total[2]) + Float.parseFloat(data[0].toString())));
					total[5] = total[5] == null ? (data[1] == null ? "0" : data[1].toString()) : (data[1] == null ? total[5] : String.valueOf(Float.parseFloat(total[5]) + Float.parseFloat(data[1].toString())));
				}
			}
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date);
			cal1.add(Calendar.YEAR, -1);
			data = jygkZzyCcCcwcqkDao.getViewData(Integer.parseInt(val[0]), new java.sql.Date(cal1.getTimeInMillis()), dwxxs);
			if(data != null){
				val[3] = data[0] == null ? null : data[0].toString();
				val[6] = data[1] == null ? null : data[1].toString();
				if("20012".equals(entryType)){
					total[3] = total[3] == null ? (data[0] == null ? "0" : data[0].toString()) : (data[0] == null ? total[3] : String.valueOf(Float.parseFloat(total[3]) + Float.parseFloat(data[0].toString())));
					total[6] = total[6] == null ? (data[1] == null ? "0" : data[1].toString()) : (data[1] == null ? total[6] : String.valueOf(Float.parseFloat(total[6]) + Float.parseFloat(data[1].toString())));
				} else {
					if("20006".equals(val[0]) || "20009".equals(val[0]) || "20015".equals(val[0])){
						total1[3] = total1[3] == null ? (data[0] == null ? "0" : data[0].toString()) : (data[0] == null ? total1[3] : String.valueOf(Float.parseFloat(total1[3]) + Float.parseFloat(data[0].toString())));
						total1[6] = total1[6] == null ? (data[1] == null ? "0" : data[1].toString()) : (data[1] == null ? total1[6] : String.valueOf(Float.parseFloat(total1[6]) + Float.parseFloat(data[1].toString())));
					} else {
						total[3] = total[3] == null ? (data[0] == null ? "0" : data[0].toString()) : (data[0] == null ? total[3] : String.valueOf(Float.parseFloat(total[3]) + Float.parseFloat(data[0].toString())));
						total[6] = total[6] == null ? (data[1] == null ? "0" : data[1].toString()) : (data[1] == null ? total[6] : String.valueOf(Float.parseFloat(total[6]) + Float.parseFloat(data[1].toString())));
					}
				}
				if(val[3] == null || Float.parseFloat(val[3].toString()) == 0){
					val[4] = null;
				} else {
					val[4] = getBigDecimal(Double.parseDouble(val[2].toString())/ Double.parseDouble(val[3].toString()) - 1) + "";
				}
				if(val[6] == null || Float.parseFloat(val[6]) == 0){
					val[7] = null;
				} else {
					val[7] = getBigDecimal(Double.parseDouble(val[5].toString())/ Double.parseDouble(val[6].toString()) - 1) + "";
				}
			}
			list.add(val);
		}
		if("20012".equals(entryType)){
			if(total[3] == null || Float.parseFloat(total[3].toString()) == 0){
				total[4] = null;
			} else {
				total[4] = getBigDecimal(Double.parseDouble(total[2].toString()) / Double.parseDouble(total[3].toString()) - 1) + "";
			}
			if(total[6] == null || Float.parseFloat(total[6].toString()) == 0){
				total[7] = null;
			} else {
				total[7] = getBigDecimal(Double.parseDouble(total[5].toString()) / Double.parseDouble(total[6].toString()) - 1) + "";
			}
		} else {
			if(total[3] == null || Float.parseFloat(total[3].toString()) == 0){
				total[4] = null;
			} else {
				total[4] = getBigDecimal(Double.parseDouble(total[2].toString()) / Double.parseDouble(total[3].toString()) - 1) + "";
			}
			if(total[6] == null || Float.parseFloat(total[6].toString()) == 0){
				total[7] = null;
			} else {
				total[7] = getBigDecimal(Double.parseDouble(total[5].toString()) / Double.parseDouble(total[6].toString()) - 1) + "";
			}
			if(total1[3] == null || Float.parseFloat(total1[3].toString()) == 0){
				total1[4] = null;
			} else {
				total1[4] = getBigDecimal(Double.parseDouble(total1[2].toString()) / Double.parseDouble(total1[3].toString()) - 1) + "";
			}
			if(total1[6] == null || Float.parseFloat(total1[6].toString()) == 0){
				total1[7] = null;
			} else {
				total1[7] = getBigDecimal(Double.parseDouble(total1[5].toString()) / Double.parseDouble(total1[6].toString()) - 1) + "";
			}
		}
		list.add(total);
		if("20013".equals(entryType)){
			list.add(total1);
		}
		return list;
	}
}
