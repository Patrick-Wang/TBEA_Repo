package com.tbea.ic.operation.service.jygk.zzy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.model.dao.jygk.dwxx.DWXXDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.CcKglyddcbqkDao;
import com.tbea.ic.operation.model.dao.qxgl.QXGLDao;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyCcKglyddcbqk;



@Service
@Transactional("transactionManager")
public class CcKglyddcbqkServiceImpl implements CcKglyddcbqkService{
	@Autowired
	CcKglyddcbqkDao zzyKglyddcbqkDao;

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
	public List<String[]> getViewDataList(Date date, String comp, String bglx) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		List<String[]> list = new ArrayList<String[]>();
		if("20015".equals(bglx)) {
			String[] val = new String[29];
			Object[] dw = zzyKglyddcbqkDao.getBgCompanie(comp);
//			val[0] = "0";
			val[0] = dw[1].toString();
			Object[] data = zzyKglyddcbqkDao.getZb(0, date, Integer.parseInt(comp), Integer.parseInt(bglx));
			if(data != null){
				int k = 0;
				for(int j = 0; j < data.length - 2; j++){
					val[k + 1] = data[j] + "";
					if(k==7 || k==10 || k==13 || k==16 || k==19 || k==22) {
						if(data[0] != null && !"".equals(data[0]) && Double.parseDouble(data[0]+"") != 0) {
							val[k+2] = getBigDecimal((Double.parseDouble(val[k+1]) / Double.parseDouble(data[0]+""))*100) + "%";
						} else {
							val[k+2] = "--";
						}
						k++;
					}
					k++;
				}
			}
			list.add(val);
		} else {
			String[] val = new String[16];
			Object[] dw = zzyKglyddcbqkDao.getBgCompanie(comp);
//			val[0] = "0";
			val[0] = dw[1].toString();
			Object[] data = zzyKglyddcbqkDao.getZb(0, date, Integer.parseInt(comp), Integer.parseInt(bglx));
			if(data != null){
				int k = 0;
				for(int j = 0; j < data.length; j++){
					val[k + 1] = data[j] + "";
					if(j==4 || j==6 || j==8) {
						if(data[0] != null && !"".equals(data[0]) && Double.parseDouble(data[0]+"") != 0) {
							val[k+2] = getBigDecimal((Double.parseDouble(data[j-1]+"") / Double.parseDouble(data[0]+"") )*100) + "%";
						} else {
							val[k+2] = "--";
						}
						k++;
					}
					k++;
				}
			}
			list.add(val);
		}
		
		return list;
	}
	
	//20015 20016
	private boolean updateJygkZzyCcKglyddcbqk(Date date, String company, JSONArray data, String entryType) {
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
			JygkZzyCcKglyddcbqk object = new JygkZzyCcKglyddcbqk();
			object = (JygkZzyCcKglyddcbqk)zzyKglyddcbqkDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyCcKglyddcbqk", entryType);
			boolean isNew = false;
			if(object == null){
				isNew = true;
				object = new JygkZzyCcKglyddcbqk();
//				object.setId(zbId);
			}
			object.setDwid(Integer.parseInt(company));
			object.setNf(cal.get(Calendar.YEAR));
			object.setYf(cal.get(Calendar.MONTH) + 1);
			object.setYccnlcz(getBigDecimal(row.get(1)));
			object.setYccnlcl(getBigDecimal(row.get(2)));
			object.setKglyddzcz(getBigDecimal(row.get(3)));
			object.setKglyddzcl(getBigDecimal(row.get(4)));
			object.setNdkglyddzcz(getBigDecimal(row.get(5)));
			object.setNdkglyddzcl(getBigDecimal(row.get(6)));
			object.setN1cz(getBigDecimal(row.get(7)));
			object.setN1czn(getBigDecimal(row.get(8)));
			object.setN1cl(getBigDecimal(row.get(9)));
			object.setN2cz(getBigDecimal(row.get(10)));
			object.setN2czn(getBigDecimal(row.get(11)));
			object.setN2cl(getBigDecimal(row.get(12)));
			object.setN3cz(getBigDecimal(row.get(13)));
			object.setN3czn(getBigDecimal(row.get(14)));
			object.setN3cl(getBigDecimal(row.get(15)));
			object.setN4cz(getBigDecimal(row.get(16)));
			object.setN4cl(getBigDecimal(row.get(17)));
			object.setN5cz(getBigDecimal(row.get(18)));
			object.setN5cl(getBigDecimal(row.get(19)));
			object.setN6cz(getBigDecimal(row.get(20)));
			object.setN6cl(getBigDecimal(row.get(21)));
			object.setN6hcz(getBigDecimal(row.get(22)));
			object.setN6hcl(getBigDecimal(row.get(23)));
			object.setN3hcz(getBigDecimal(row.get(24)));
			object.setDdcz(getBigDecimal(row.get(25)));
			object.setDdcl(getBigDecimal(row.get(26)));
			object.setWxcz(getBigDecimal(row.get(27)));
			object.setWxcn(getBigDecimal(row.get(28)));
			object.setXgsj(ts);
			if(isNew){
				zzyKglyddcbqkDao.create(object);
			} else {
				zzyKglyddcbqkDao.merge(object);
			}
		}

		return true;
	}

}
