package com.tbea.ic.operation.service.jygk.zzy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ChYclchDao;
import com.tbea.ic.operation.model.dao.qxgl.QXGLDao;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyChYclch;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyDwReferBglxfl;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFl;



@Service
@Transactional("transactionManager")
public class ChYclchServiceImpl implements ChYclchService{
	@Autowired
	ChYclchDao zzyYclchDao;

	@Autowired
	DWXXDao dwxxDao;
	@Autowired
	QXGLDao qxglDao;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
		
//	static HashMap 

	
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
	public List<String[]> getViewDataList(Date date, String comp, String bglx) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		List<String[]> list = new ArrayList<String[]>();
		List<JygkZzyDwReferBglxfl> zblist = zzyYclchDao.getDwReferBglxfl(Integer.parseInt(comp), Integer.parseInt("20019"));
		String[] val;
		JygkZzyFl fl;
		Object[] data1;
		Object[] data2;
		double ydkc1 = 0;
		double nckc1 = 0;
		double ydkc2 = 0;
		
		for(int i = 0; i < zblist.size(); i++){
			fl = zzyYclchDao.getZbfl(zblist.get(i).getJygkZzyFl().getId());
			val = new String[6];
			Date newDate = new Date(date.getYear() -1, date.getMonth(), date.getDate());
			
			val[0] = fl.getName();
			data1 = zzyYclchDao.getZb(Integer.parseInt(fl.getId()+""), date, Integer.parseInt(comp), Integer.parseInt("20019"));
			data2 = zzyYclchDao.getZb(Integer.parseInt(fl.getId()+""), newDate, Integer.parseInt(comp), Integer.parseInt("20019"));
			if(data1 != null){
				if(data1[0]==null || "".equals(data1[0])) {
					val[1] = "--";
				} else {
					ydkc1 = Double.parseDouble(data1[0].toString());
					val[1] = ydkc1 + "";
				}
				if(data1[1]==null || "".equals(data1[1])) {
					val[2] = "--";
				} else {
					nckc1 = Double.parseDouble(data1[1].toString());
					val[2] = nckc1 + "";
				}
			} else {
				val[1] = "--";
				val[2] = "--";
			}
			if(data2 != null) {
				if(data2[0]==null || "".equals(data2[0])) {
					val[4] = "--";
				} else {
					ydkc2 = Double.parseDouble(data2[0].toString());
					val[4] = ydkc2 + "";
				}
			} else {
				val[4] = "--";
			}
			if(nckc1 != 0 && !"--".equals(val[1])) {
				val[3] = ydkc1 / nckc1 -1 + "";
			} else {
				val[3] = "--";
			}
			if(ydkc2 != 0 && !"--".equals(val[1])) {
				val[5] = ydkc1 / ydkc2 -1 + "";
			} else {
				val[5] = "--";
			}
			list.add(val);
		}
		
		return list;
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

	//20019
	private boolean updateJygkZzyChYclch(Date date, String company, JSONArray data, String entryType) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		JSONArray row;
		Integer zbId;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(Calendar.getInstance().getTime());
		Timestamp ts = Timestamp.valueOf(time);
		for (int r = 0; r < data.size(); ++r) {
			row = data.getJSONArray(r);
			zbId = Integer.valueOf(row.getString(0));
			JygkZzyChYclch object = new JygkZzyChYclch();
			object = (JygkZzyChYclch)zzyYclchDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyChYclch", entryType);
			boolean isNew = false;
			if(object == null){
				isNew = true;
				object = new JygkZzyChYclch();
//					object.setId(zbId);
			}
			object.setDwid(Integer.parseInt(company));
			object.setZzyflId(zbId);
			object.setNf(cal.get(Calendar.YEAR));
			object.setYf(cal.get(Calendar.MONTH) + 1);
			object.setJzydkcje(getBigDecimal(row.get(1)));
			object.setNckcje(getBigDecimal(row.get(2)));
			object.setXgsj(ts);
			if(isNew){
				zzyYclchDao.create(object);
			} else {
				zzyYclchDao.merge(object);
			}
		}

		return true;
	}
	

}
