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
public class CcCcwcqkServiceImpl implements CcCcwcqkService{
	@Autowired
	CcCcwcqkDao jygkZzyCcCcwcqkDao;
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
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		List<String[]> list = new ArrayList<String[]>();
		List<JygkZzyDwReferBglxfl> zblist = zzyCksjDao.getDwReferBglxfl(Integer.parseInt(comp), Integer.parseInt(entryType));
		String[] total = new String[8];
		String[] total1 = new String[8];
		if("20012".equals(entryType)){
			total[0] = "total";
			total[1] = "合计";
		} else {
			total[0] = "total";
			total[1] = "合计（铜）";
			total1[0] = "total1";
			total1[1] = "合计（铝）";
		}
		DecimalFormat df = new DecimalFormat("0.0000");
		for(int i = 0; i < zblist.size(); i++){
			String[] val = new String[8];
			val[0] = zblist.get(i).getJygkZzyFl().getId() + "";
			val[1] = zblist.get(i).getJygkZzyFl().getName();
			Object[] data = zzyCksjDao.getZb(Integer.parseInt(val[0]), date, Integer.parseInt(comp), Integer.parseInt(entryType));
			if(data != null){
				val[2] = data[0] == null ? null : data[0].toString();
				val[5] = data[1] == null ? null : data[1].toString();
				if("20012".equals(entryType)){
					total[2] = total[2] == null ? (data[0] == null ? "0" : data[0].toString()) : (data[0] == null ? total[2] : String.valueOf(Float.parseFloat(total[2]) + Float.parseFloat(data[0].toString())));
					total[5] = total[5] == null ? (data[1] == null ? "0" : data[1].toString()) : (data[1] == null ? total[5] : String.valueOf(Float.parseFloat(total[5]) + Float.parseFloat(data[1].toString())));
				} else {
					if("20006".equals(val[0]) || "20009".equals(val[0]) || "20015".equals(val[0])){
						total1[2] = total1[2] == null ? (data[0] == null ? "0" : data[0].toString()) : (data[0] == null ? total1[2] : String.valueOf(Float.parseFloat(total1[2]) + Float.parseFloat(data[0].toString())));
						total1[5] = total1[5] == null ? (data[1] == null ? "0" : data[1].toString()) : (data[1] == null ? total1[5] : String.valueOf(Float.parseFloat(total1[5]) + Float.parseFloat(data[1].toString())));
					} else {
						total[2] = total[2] == null ? (data[0] == null ? "0" : data[0].toString()) : (data[0] == null ? total[2] : String.valueOf(Float.parseFloat(total[2]) + Float.parseFloat(data[0].toString())));
						total[5] = total[5] == null ? (data[1] == null ? "0" : data[1].toString()) : (data[1] == null ? total[5] : String.valueOf(Float.parseFloat(total[5]) + Float.parseFloat(data[1].toString())));
					}
				}
			}
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date);
			cal1.add(Calendar.YEAR, -1);
			data = zzyCksjDao.getZb(Integer.parseInt(val[0]), new java.sql.Date(cal1.getTimeInMillis()), Integer.parseInt(comp), Integer.parseInt(entryType));
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
				if(data[0] == null || Float.parseFloat(data[0].toString()) == 0){
					val[4] = null;
				} else {
					if(val[4] == null){
						val[4] = "1.00";
					} else {
						val[4] = df.format((Double.parseDouble(val[2].toString()) - Double.parseDouble(val[3].toString()))/ Double.parseDouble(val[2].toString()));
					}
				}
				if(data[1] == null || Float.parseFloat(data[1].toString()) == 0){
					val[7] = null;
				} else {
					if(val[7] == null){
						val[7] = "1.00";
					} else {
						val[7] = df.format((Double.parseDouble(val[5].toString()) - Double.parseDouble(val[6].toString()))/ Double.parseDouble(val[5].toString()));
					}
				}
			}
			list.add(val);
		}
		if("20012".equals(entryType)){
			if(total[2] == null || Float.parseFloat(total[2].toString()) == 0){
				total[4] = null;
			} else {
				if(total[4] == null){
					total[4] = "1.00";
				} else {
					total[4] = df.format((Double.parseDouble(total[2].toString()) - Double.parseDouble(total[3].toString()))/ Double.parseDouble(total[2].toString()));
				}
			}
			if(total[5] == null || Float.parseFloat(total[5].toString()) == 0){
				total[7] = null;
			} else {
				if(total[7] == null){
					total[7] = "1.00";
				} else {
					total[7] = df.format((Double.parseDouble(total[5].toString()) - Double.parseDouble(total[6].toString()))/ Double.parseDouble(total[5].toString()));
				}
			}
		} else {
			if(total[2] == null || Float.parseFloat(total[2].toString()) == 0){
				total[4] = null;
			} else {
				if(total[4] == null){
					total[4] = "1.00";
				} else {
					total[4] = df.format((Double.parseDouble(total[2].toString()) - Double.parseDouble(total[3].toString()))/ Double.parseDouble(total[2].toString()));
				}
			}
			if(total[5] == null || Float.parseFloat(total[5].toString()) == 0){
				total[7] = null;
			} else {
				if(total[7] == null){
					total[7] = "1.00";
				} else {
					total[7] = df.format((Double.parseDouble(total[5].toString()) - Double.parseDouble(total[6].toString()))/ Double.parseDouble(total[5].toString()));
				}
			}
			if(total1[2] == null || Float.parseFloat(total1[2].toString()) == 0){
				total1[4] = null;
			} else {
				if(total1[4] == null){
					total1[4] = "1.00";
				} else {
					total1[4] = df.format((Double.parseDouble(total1[2].toString()) - Double.parseDouble(total1[3].toString()))/ Double.parseDouble(total1[2].toString()));
				}
			}
			if(total1[5] == null || Float.parseFloat(total1[5].toString()) == 0){
				total1[7] = null;
			} else {
				if(total1[7] == null){
					total1[7] = "1.00";
				} else {
					total1[7] = df.format((Double.parseDouble(total1[5].toString()) - Double.parseDouble(total1[6].toString()))/ Double.parseDouble(total1[5].toString()));
				}
			}
		}
		list.add(total);
		if("20013".equals(entryType)){
			list.add(total1);
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
