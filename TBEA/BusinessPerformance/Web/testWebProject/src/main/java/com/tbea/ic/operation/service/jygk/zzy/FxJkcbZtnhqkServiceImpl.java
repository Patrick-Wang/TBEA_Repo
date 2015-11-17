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
	public List<Map> getBgCompanies(String bglx) {
		List<Object[]> dwxxs = zzyCksjDao.getBgCompanies(bglx);
		List<Map> list = new ArrayList<Map>();
		for(int i = 0; i < dwxxs.size(); i++){
			Object[] dwxx = dwxxs.get(i);
			Map map = new HashMap();
			Map submap = new HashMap();
			submap.put("id", dwxx[0]);
			submap.put("value", dwxx[1]);
			map.put("data", submap);
			map.put("parent", null);
			map.put("subNodes", new ArrayList());
			list.add(map);
		}
		return list;
	}

	@Override
	public List<String[]> getZb(Date date, String comp, String entryType) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		List<String[]> list = new ArrayList<String[]>();
		cal.add(Calendar.YEAR, -1);
		String[] val0 = new String[12];
		val0[0] = "0";
		val0[1] = "去年同期";
		Object[] data = zzyCksjDao.getZb(0, new java.sql.Date(cal.getTimeInMillis()), Integer.parseInt(comp), Integer.parseInt(entryType));
		if(data != null){
			for(int j = 0; j < val0.length - 4; j++){
				val0[j + 2] = data[j] + "";
			}
		}
		List<Object[]> cc = jygkZzyCcCcwcqkDao.getSumDataListByDwData(Integer.parseInt(comp), cal.get(Calendar.YEAR));
		if(cc!= null && cc.size() > 0){
			val0[10] = cc.get(0)[1] == null ? null : cc.get(0)[1].toString();
			val0[11] = cc.get(0)[2] == null ? null : cc.get(0)[2].toString();
		}
		list.add(val0);
		String[] val = new String[12];
		val[0] = "0";
		val[1] = cal.get(Calendar.YEAR) + "年当月";
		data = zzyCksjDao.getZb(0, date, Integer.parseInt(comp), Integer.parseInt(entryType));
		if(data != null){
			for(int j = 0; j < val.length - 4; j++){
				val[j + 2] = data[j] + "";
			}
		}
		cal.add(Calendar.YEAR, 1);
		cc = jygkZzyCcCcwcqkDao.getSumDataListByDwData(Integer.parseInt(comp), cal.get(Calendar.YEAR));
		if(cc!= null && cc.size() > 0){
			val[10] = cc.get(0)[1] == null ? null : cc.get(0)[1].toString();
			val[11] = cc.get(0)[2] == null ? null : cc.get(0)[2].toString();
		}
		list.add(val);
		return list;
	}
	
	private BigDecimal calZb(BigDecimal val, BigDecimal valsum){
		if(valsum != null && valsum.compareTo(BigDecimal.valueOf(0)) != 0){
			if(val != null){
				return val.divide(valsum,4,BigDecimal.ROUND_HALF_UP);
			}
		}
		return null;
	}
	
	private List<String[]> toArray(Map<Integer, String[]> map){
		Object[] key_arr = map.keySet().toArray();   
		Arrays.sort(key_arr);   
		List<String[]> ret = new ArrayList<String[]>();
		for(Object id : key_arr){
			ret.add(map.get(id));
		}
		return ret;
	}

	@Override
	public List<JygkZzyBglx> getCksjBgList() {
		// TODO Auto-generated method stub
		return zzyCksjDao.getCksjBgList();
	}
}
