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
import com.tbea.ic.operation.model.dao.jygk.zzy.FxJkcbXsjbDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ZzyCksjDao;
import com.tbea.ic.operation.model.dao.qxgl.QXGLDao;
import com.tbea.ic.operation.model.entity.jygk.zzy.*;



@Service
@Transactional("transactionManager")
public class FxJkcbXsjbServiceImpl implements FxJkcbXsjbService{
	@Autowired
	FxJkcbXsjbDao jygkZzyFxJkcbXsjbDao;
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
		String[] val0 = new String[8];
		val0[0] = "0";
		val0[1] = cal.get(Calendar.YEAR) + "年当月";
//		val0[1] = cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月";
		Object[] data = zzyCksjDao.getZb(0, date, Integer.parseInt(comp), Integer.parseInt(entryType));
		if(data != null){
			float total = 0;
			for(int j = 0; j < val0.length - 3; j++){
				val0[j + 2] = data[j] + "";
				if(j != val0.length - 5){
					total += Float.parseFloat(data[j] == null ? "0" : data[j].toString());
				}
			}
			val0[val0.length - 1] = total + "";
		}
		list.add(val0);
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date);
		cal1.add(Calendar.YEAR, -1);
		String[] val = new String[8];
		val[0] = "0";
		val[1] = "去年同期";
		data = zzyCksjDao.getZb(0, new java.sql.Date(cal1.getTimeInMillis()), Integer.parseInt(comp), Integer.parseInt(entryType));
		if(data != null){
			float total = 0;
			for(int j = 0; j < val.length - 3; j++){
				val[j + 2] = data[j] + "";
				if(j != val.length - 5){
					total += Float.parseFloat(data[j] == null ? "0" : data[j].toString());
				}
			}
			val[val.length - 1] = total + "";
		}
		list.add(0, val);
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
