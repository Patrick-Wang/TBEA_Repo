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
import com.tbea.ic.operation.model.dao.jygk.zzy.FxJkcbScjbDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ZzyCksjDao;
import com.tbea.ic.operation.model.dao.qxgl.QXGLDao;
import com.tbea.ic.operation.model.entity.jygk.zzy.*;



@Service
@Transactional("transactionManager")
public class FxJkcbScjbServiceImpl implements FxJkcbScjbService{
	@Autowired
	FxJkcbScjbDao jygkZzyFxJkcbScjbDao;
	@Autowired
	ZzyCksjDao zzyCksjDao;
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
		List<JygkZzyDwReferBglxfl> zblist = zzyCksjDao.getDwReferBglxfl(Integer.parseInt(comp), Integer.parseInt(entryType));
		List<String[]> list = new ArrayList<String[]>();
		for(int i = 0; i < zblist.size(); i++){
			String[] val = new String[8];
			DecimalFormat df = new DecimalFormat("0.000");
			JygkZzyFl fl = zzyCksjDao.getZbfl(zblist.get(i).getJygkZzyFl().getId());
			val[0] = zblist.get(i).getJygkZzyFl().getId() + "";
			val[1] = zblist.get(i).getJygkZzyFl().getName();
			JygkZzyFxJkcbScjb data = jygkZzyFxJkcbScjbDao.getZb(Integer.parseInt(val[0]), date, Integer.parseInt(comp));
			if(data != null){
				val[2] = data.getSjlyl() == null ? null : data.getSjlyl().toString();
				val[3] = data.getFl() == null ? null : data.getFl().toString();
				if(data.getSjlyl() == null || data.getSjlyl().compareTo(BigDecimal.valueOf(0)) == 0){
					val[4] = null;
				} else {
					if(data.getFl() == null){
						val[4] = "1.00";
					} else {
						val[4] = calZb(data.getSjlyl().subtract(data.getFl()), data.getSjlyl()).toString();
					}
				}
			}
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date);
			cal1.add(Calendar.YEAR, -1);
			data = jygkZzyFxJkcbScjbDao.getZb(Integer.parseInt(val[0]), new java.sql.Date(cal1.getTimeInMillis()), Integer.parseInt(comp));
			if(data != null){
				val[5] = data.getSjlyl() == null ? null : data.getSjlyl().toString();
				val[6] = data.getFl() == null ? null : data.getFl().toString();
				if(data.getSjlyl() == null || data.getSjlyl().compareTo(BigDecimal.valueOf(0)) == 0){
					val[7] = null;
				} else {
					if(data.getFl() == null){
						val[7] = "1.00";
					} else {
						val[7] = calZb(data.getSjlyl().subtract(data.getFl()), data.getSjlyl()).toString();
					}
				}
			}
			list.add(val);
		}
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
