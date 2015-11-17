package com.tbea.ic.operation.service.jygk.zzy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ChChjgjnhDao;
import com.tbea.ic.operation.model.dao.qxgl.QXGLDao;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyChChjgjnh;



@Service
@Transactional("transactionManager")
public class ChChjgjnhServiceImpl implements ChChjgjnhService{
	@Autowired
	ChChjgjnhDao zzyChjgjnhDao;

	@Autowired
	DWXXDao dwxxDao;
	@Autowired
	QXGLDao qxglDao;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
		
	static HashMap<String, Integer> entrysize;
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
		String[] val = new String[10];
		
//		val[0] = cal.get(Calendar.YEAR)-1 + "年" + (cal.get(Calendar.MONTH) + 1) + "月";
		val[0] = "去年同期";
	
		Date newDate = new Date(date.getYear() -1, date.getMonth(), 20);
		Object[] data = zzyChjgjnhDao.getZb(0, newDate, Integer.parseInt(comp), Integer.parseInt("20017"));
		if(data != null){
			for(int j = 0; j < data.length ; j++){
				if(data[j] == null || "".equals(data[j])) {
					val[j + 1] = "--";
				} else {
					val[j + 1] = data[j] + "";
				}
			}
		}
		list.add(val);
		val = new String[10];
//		val[0] = "1";
//		val[0] = cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月";
		val[0] = cal.get(Calendar.YEAR) + "年当期";
		
		Object[] data2 = zzyChjgjnhDao.getZb(0, date, Integer.parseInt(comp), Integer.parseInt("20017"));
		if(data2 != null){
			for(int j = 0; j < data2.length ; j++){
				if(data2[j] == null || "".equals(data2[j])) {
					val[j + 1] = "--";
				} else {
					val[j + 1] = data2[j] + "";
				}
			}
		}
		list.add(val);
		val = new String[10];
//		val[0] = "2";
		val[0] = "同比";
		double x = 0;
		double y = 0;
		for(int j = 0; j < 9 ; j++){
			if(list.get(1)[j+1]==null || "".equals(list.get(1)[j+1]) || "--".equals(list.get(1)[j+1])) {
				val[j + 1] = "--";
			} else {
				x = Double.parseDouble(list.get(1)[j+1]);
			}
			if(list.get(0)[j+1]==null || "".equals(list.get(0)[j+1]) || "--".equals(list.get(0)[j+1])) {
				val[j + 1] = "--";
			} else {
				y = Double.parseDouble(list.get(0)[j+1]);
			}
			if(!"--".equals(val[j+1])) {
				val[j + 1] = (y/x-1)*100 + "%";
			}
			
		}
		
		list.add(val);
		
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

	//20017
	private boolean updateJygkZzyChChjgjnh(Date date, String company, JSONArray data, String entryType) {
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
			JygkZzyChChjgjnh object = new JygkZzyChChjgjnh();
			object = (JygkZzyChChjgjnh)zzyChjgjnhDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyChChjgjnh", entryType);
			boolean isNew = false;
			if(object == null){
				isNew = true;
				object = new JygkZzyChChjgjnh();
//					object.setId(zbId);
			}
			object.setDwid(Integer.parseInt(company));
			object.setNf(cal.get(Calendar.YEAR));
			object.setYf(cal.get(Calendar.MONTH) + 1);
			object.setYcl(getBigDecimal(row.get(1)));
			object.setBcp(getBigDecimal(row.get(2)));
			object.setSjkc(getBigDecimal(row.get(3)));
			object.setYfhwkfp(getBigDecimal(row.get(4)));
			object.setQhfdy(getBigDecimal(row.get(5)));
			object.setQhfdk(getBigDecimal(row.get(6)));
			object.setWkhykp(getBigDecimal(row.get(7)));
			object.setQt(getBigDecimal(row.get(8)));
			object.setHj(getBigDecimal(row.get(9)));
			object.setXgsj(ts);
			if(isNew){
				zzyChjgjnhDao.create(object);
			} else {
				zzyChjgjnhDao.merge(object);
			}
		}

		return true;
	}

}
