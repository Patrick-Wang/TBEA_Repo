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
import com.tbea.ic.operation.model.dao.jygk.zzy.CcCcwcqkDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.FxJkcbZtnhqkDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ZzyCksjDao;
import com.tbea.ic.operation.model.dao.qxgl.QXGLDao;
import com.tbea.ic.operation.model.entity.jygk.zzy.*;



@Service
@Transactional("transactionManager")
public class FxJkcbZtnhqkServiceImpl implements FxJkcbZtnhqkService{
	@Autowired
	FxJkcbZtnhqkDao jygkZzyFxJkcbZtnhqkDao;

	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	@Autowired
	public void init(){
	}
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
	public List<String[]> getZb(Date date, String comp, String entryType) {
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
		cal.add(Calendar.YEAR, -1);
		String[] val0 = new String[12];
		val0[0] = "0";
		val0[1] = "去年同期";
		List<Object[]> data = null;
		if("20009".equals(entryType)){
			data = jygkZzyFxJkcbZtnhqkDao.getViewDataListByq(cal.get(Calendar.YEAR) + "", (cal.get(Calendar.MONTH) + 1) + "", dwxxs);
		} else {
			data = jygkZzyFxJkcbZtnhqkDao.getViewDataListXl(cal.get(Calendar.YEAR) + "", (cal.get(Calendar.MONTH) + 1) + "", dwxxs);
		}
		if(data != null && data.size() > 0){
			for(int i = 0; i < data.size(); i++){
				for(int j = 0; j < val0.length - 2; j++){
					val0[j + 2] = val0[j + 2] == null ? (data.get(i)[j] + "") : (getBigDecimal(Double.parseDouble(val0[j + 2]) + Double.parseDouble(data.get(i)[j] + ""))) + "";
				}
			}
		}
		list.add(val0);
		cal.add(Calendar.YEAR, 1);
		String[] val = new String[12];
		val[0] = "0";
		val[1] = cal.get(Calendar.YEAR) + "年当月";
		if("20009".equals(entryType)){
			data = jygkZzyFxJkcbZtnhqkDao.getViewDataListByq(cal.get(Calendar.YEAR) + "", (cal.get(Calendar.MONTH) + 1) + "", dwxxs);
		} else {
			data = jygkZzyFxJkcbZtnhqkDao.getViewDataListXl(cal.get(Calendar.YEAR) + "", (cal.get(Calendar.MONTH) + 1) + "", dwxxs);
		}
		if(data != null && data.size() > 0){
			for(int i = 0; i < data.size(); i++){
				for(int j = 0; j < val0.length - 2; j++){
					val[j + 2] = val[j + 2] == null ? (data.get(i)[j] + "") : (getBigDecimal(Double.parseDouble(val[j + 2]) + Double.parseDouble(data.get(i)[j] + ""))) + "";
				}
			}
		}
		list.add(val);
		return list;
	}
}
