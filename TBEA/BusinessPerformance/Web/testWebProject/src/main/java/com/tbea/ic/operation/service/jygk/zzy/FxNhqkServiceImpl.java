package com.tbea.ic.operation.service.jygk.zzy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
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
import com.tbea.ic.operation.model.dao.jygk.zzy.FxNhqkDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ZzyCksjDao;
import com.tbea.ic.operation.model.dao.qxgl.QXGLDao;
import com.tbea.ic.operation.model.entity.jygk.zzy.*;



@Service
@Transactional("transactionManager")
public class FxNhqkServiceImpl implements FxNhqkService{
	@Autowired
	FxNhqkDao jygkZzyFxNhqkDao;
	@Autowired
	FxJkcbZtnhqkDao jygkZzyFxJkcbZtnhqkDao;			//20009  20010
	@Autowired
	ZzyCksjDao zzyCksjDao;
	@Autowired
	CcCcwcqkDao jygkZzyCcCcwcqkDao;					//20012  20013
	@Autowired
	DWXXDao dwxxDao;
	@Autowired
	QXGLDao qxglDao;
	
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
		//当年
		String[] currentyear_cl = new String[9];
		String[] currentyear_cz = new String[9];
		currentyear_cl[0] = "currentyear_cl";
		currentyear_cl[1] = cal.get(Calendar.YEAR) + "年";
		currentyear_cz[0] = "currentyear_cz";
		currentyear_cz[1] = cal.get(Calendar.YEAR) + "年";
		List<Object[]> val = jygkZzyFxJkcbZtnhqkDao.getSumDataListByDwData(dwxxs, cal.get(Calendar.YEAR));
		if(val != null && val.size() > 0){
			currentyear_cl[2] = val.get(0)[8] == null ? null : val.get(0)[8].toString();
			currentyear_cz[2] = val.get(0)[7] == null ? null : val.get(0)[7].toString();
			currentyear_cl[3] = val.get(0)[1] == null ? null : val.get(0)[1].toString();
			currentyear_cl[5] = val.get(0)[3] == null ? null : val.get(0)[3].toString();
			currentyear_cl[7] = val.get(0)[5] == null ? null : val.get(0)[5].toString();
			currentyear_cz[3] = val.get(0)[2] == null ? null : val.get(0)[2].toString();
			currentyear_cz[5] = val.get(0)[4] == null ? null : val.get(0)[4].toString();
			currentyear_cz[7] = val.get(0)[6] == null ? null : val.get(0)[6].toString();
			currentyear_cl[4] = calZb((BigDecimal)val.get(0)[1], (BigDecimal)val.get(0)[8]).toString();
			currentyear_cl[6] = calZb((BigDecimal)val.get(0)[3], (BigDecimal)val.get(0)[8]).toString();
			currentyear_cl[8] = calZb((BigDecimal)val.get(0)[5], (BigDecimal)val.get(0)[8]).toString();
			currentyear_cz[4] = calZb((BigDecimal)val.get(0)[2], (BigDecimal)val.get(0)[7]).toString();
			currentyear_cz[6] = calZb((BigDecimal)val.get(0)[3], (BigDecimal)val.get(0)[7]).toString();
			currentyear_cz[8] = calZb((BigDecimal)val.get(0)[4], (BigDecimal)val.get(0)[7]).toString();
		}
		//前一年
		cal.add(Calendar.YEAR, -1);
		String[] lastyear_cl = new String[9];
		String[] lastyear_cz = new String[9];
		lastyear_cl[0] = "lastyear_cl";
		lastyear_cl[1] = cal.get(Calendar.YEAR) + "年";
		lastyear_cz[0] = "lastyear_cl";
		lastyear_cz[1] = cal.get(Calendar.YEAR) + "年";
		val = jygkZzyFxJkcbZtnhqkDao.getSumDataListByDwData(dwxxs, cal.get(Calendar.YEAR));
		if(val != null && val.size() > 0){
			lastyear_cl[2] = val.get(0)[8] == null ? null : val.get(0)[8].toString();
			lastyear_cz[2] = val.get(0)[7] == null ? null : val.get(0)[7].toString();
			lastyear_cl[3] = val.get(0)[1] == null ? null : val.get(0)[1].toString();
			lastyear_cl[5] = val.get(0)[3] == null ? null : val.get(0)[3].toString();
			lastyear_cl[7] = val.get(0)[5] == null ? null : val.get(0)[5].toString();
			lastyear_cz[3] = val.get(0)[2] == null ? null : val.get(0)[2].toString();
			lastyear_cz[5] = val.get(0)[4] == null ? null : val.get(0)[4].toString();
			lastyear_cz[7] = val.get(0)[6] == null ? null : val.get(0)[6].toString();
			lastyear_cl[4] = calZb((BigDecimal)val.get(0)[1], (BigDecimal)val.get(0)[8]).toString();
			lastyear_cl[6] = calZb((BigDecimal)val.get(0)[3], (BigDecimal)val.get(0)[8]).toString();
			lastyear_cl[8] = calZb((BigDecimal)val.get(0)[5], (BigDecimal)val.get(0)[8]).toString();
			lastyear_cz[4] = calZb((BigDecimal)val.get(0)[2], (BigDecimal)val.get(0)[7]).toString();
			lastyear_cz[6] = calZb((BigDecimal)val.get(0)[3], (BigDecimal)val.get(0)[7]).toString();
			lastyear_cz[8] = calZb((BigDecimal)val.get(0)[4], (BigDecimal)val.get(0)[7]).toString();
		}
		
		list.add(lastyear_cl);
		list.add(currentyear_cl);
		list.add(lastyear_cz);
		list.add(currentyear_cz);
		return list;
	}
	
	private BigDecimal calZb(BigDecimal val, BigDecimal valsum){
		if(valsum != null && valsum.compareTo(BigDecimal.valueOf(0)) != 0){
			if(val != null){
				return val.divide(valsum,2,BigDecimal.ROUND_HALF_UP);
			}
		}
		return null;
	}
}
