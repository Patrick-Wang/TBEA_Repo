package com.tbea.ic.operation.service.jygk.zzy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.dao.jygk.zzy.FxJkcbXsjbDao;



@Service
@Transactional("transactionManager")
public class FxJkcbXsjbServiceImpl implements FxJkcbXsjbService{
	@Autowired
	FxJkcbXsjbDao jygkZzyFxJkcbXsjbDao;
	
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
		String[] val0 = new String[8];
		val0[0] = "0";
		val0[1] = cal.get(Calendar.YEAR) + "年当月";
//		val0[1] = cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月";
		List<Object[]> data = jygkZzyFxJkcbXsjbDao.getViewDataList(cal.get(Calendar.YEAR) + "", (cal.get(Calendar.MONTH) + 1) + "", dwxxs);
		if(data != null && data.size() > 0){
			float total = 0;
			for(int i = 0; i < data.size(); i++){
				for(int j = 0; j < val0.length - 3; j++){
					val0[j + 2] = val0[j + 2] == null ? (data.get(i)[j] + "") : (getBigDecimal(Double.parseDouble(val0[j + 2]) + Double.parseDouble(data.get(i)[j] + ""))) + "";
					if(j != val0.length - 5){
						total += Float.parseFloat(data.get(i)[j] == null ? "0" : data.get(i)[j].toString());
					}
				}
				val0[val0.length - 1] = total + "";
			}
		}
		list.add(val0);
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		cal1.add(Calendar.YEAR, -1);
		String[] val = new String[8];
		val[0] = "0";
		val[1] = "去年同期";
		data = jygkZzyFxJkcbXsjbDao.getViewDataList(cal1.get(Calendar.YEAR) + "", (cal1.get(Calendar.MONTH) + 1) + "", dwxxs);
		if(data != null){
			float total = 0;
			for(int i = 0; i < data.size(); i++){
				for(int j = 0; j < val.length - 3; j++){
					val[j + 2] = val[j + 2] == null ? (data.get(i)[j] + "") : (getBigDecimal(Double.parseDouble(val[j + 2]) + Double.parseDouble(data.get(i)[j] + ""))) + "";
					if(j != val.length - 5){
						total += Float.parseFloat(data.get(i)[j] == null ? "0" :data.get(i)[j].toString());
					}
				}
				val[val.length - 1] = total + "";
			}
		}
		list.add(0, val);
		return list;
	}
}
