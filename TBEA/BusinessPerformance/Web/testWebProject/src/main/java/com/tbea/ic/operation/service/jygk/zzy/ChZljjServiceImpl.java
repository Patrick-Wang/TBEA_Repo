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
import com.tbea.ic.operation.model.dao.jygk.zzy.ChZljjDao;
import com.tbea.ic.operation.model.dao.qxgl.QXGLDao;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyChZljj;



@Service
@Transactional("transactionManager")
public class ChZljjServiceImpl implements ChZljjService{
	@Autowired
	ChZljjDao zzyZljjDao;
	
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
		String[] val = new String[25];
		Object[] data = zzyZljjDao.getZb(0, date, Integer.parseInt(comp), Integer.parseInt("20018"));
		if(data != null){
			for(int j = 0; j < data.length; j++){
				if(data[j] == null || "".equals(data[j])) {
					val[j] = "--";
				} else {
					val[j] = data[j] + "";
				}
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

	//20018
	private boolean updateJygkZzyChZljj(Date date, String company, JSONArray data, String entryType) {
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
			JygkZzyChZljj object = new JygkZzyChZljj();
			object = (JygkZzyChZljj)zzyZljjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyChZljj", entryType);
			boolean isNew = false;
			if(object == null){
				isNew = true;
				object = new JygkZzyChZljj();
//					object.setId(zbId);
			}
			object.setDwid(Integer.parseInt(company));
			object.setNf(cal.get(Calendar.YEAR));
			object.setYf(cal.get(Calendar.MONTH) + 1);
			object.setN5sycl(getBigDecimal(row.get(1)));
			object.setN5sbcp(getBigDecimal(row.get(2)));
			object.setN5sccp(getBigDecimal(row.get(3)));
			object.setN5sqt(getBigDecimal(row.get(4)));
			object.setN4z5ycl(getBigDecimal(row.get(5)));
			object.setN4z5bcp(getBigDecimal(row.get(6)));
			object.setN4z5ccp(getBigDecimal(row.get(7)));
			object.setN4z5qt(getBigDecimal(row.get(8)));
			object.setN3z4ycl(getBigDecimal(row.get(9)));
			object.setN3z4bcp(getBigDecimal(row.get(10)));
			object.setN3z4ccp(getBigDecimal(row.get(11)));
			object.setN3z4qt(getBigDecimal(row.get(12)));
			object.setN2z3ycl(getBigDecimal(row.get(13)));
			object.setN2z3bcp(getBigDecimal(row.get(14)));
			object.setN2z3ccp(getBigDecimal(row.get(15)));
			object.setN2z3qt(getBigDecimal(row.get(16)));
			object.setN1z2ycl(getBigDecimal(row.get(17)));
			object.setN1z2bcp(getBigDecimal(row.get(18)));
			object.setN1z2ccp(getBigDecimal(row.get(19)));
			object.setN1z2qt(getBigDecimal(row.get(20)));
			object.setN1ycl(getBigDecimal(row.get(21)));
			object.setN1bcp(getBigDecimal(row.get(22)));
			object.setN1ccp(getBigDecimal(row.get(23)));
			object.setN1qt(getBigDecimal(row.get(24)));
			object.setHj(getBigDecimal(row.get(25)));
			object.setXgsj(ts);
			if(isNew){
				zzyZljjDao.create(object);
			} else {
				zzyZljjDao.merge(object);
			}
		}

		return true;
	}
	

}
