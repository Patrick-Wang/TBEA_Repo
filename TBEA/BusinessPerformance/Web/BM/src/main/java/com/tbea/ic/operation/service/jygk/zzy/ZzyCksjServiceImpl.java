package com.tbea.ic.operation.service.jygk.zzy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DecimalFormat;
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
import com.tbea.ic.operation.model.dao.jygk.zzy.CcCcwcqkDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.CcCcwcqkGsDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.CcKglyddcbqkDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ChChjgjnhDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ChYclchDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ChZljjDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.FxCpylspDqddmlqkDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.FxCpylspHqlyddzlDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.FxJkcbCgjbDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.FxJkcbJsjbDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.FxJkcbScjbDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.FxJkcbXsjbDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.FxJkcbZbwcqkDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.FxJkcbZtnhqkDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ZzyCksjDao;
import com.tbea.ic.operation.model.dao.qxgl.QXGLDao;
import com.tbea.ic.operation.model.entity.jygk.zzy.*;



@Service
@Transactional("transactionManager")
public class ZzyCksjServiceImpl implements ZzyCksjService{
	@Autowired
	ZzyCksjDao zzyCksjDao;

	@Autowired
	FxCpylspDqddmlqkDao jygkZzyFxCpylspDqddmlqkDao;	//20001
	@Autowired
	FxCpylspHqlyddzlDao jygkZzyFxCpylspHqlyddzlDao;	//20002  20003
	@Autowired
	FxJkcbZbwcqkDao jygkZzyFxJkcbZbwcqkDao;			//20004
	@Autowired
	FxJkcbJsjbDao jygkZzyFxJkcbJsjbDao;				//20005 20006
	@Autowired
	FxJkcbCgjbDao jygkZzyFxJkcbCgjbDao;				//20007
	@Autowired
	FxJkcbScjbDao jygkZzyFxJkcbScjbDao;				//20008
	@Autowired
	FxJkcbZtnhqkDao jygkZzyFxJkcbZtnhqkDao;			//20009  20010
	@Autowired
	FxJkcbXsjbDao jygkZzyFxJkcbXsjbDao;				//20011
	@Autowired
	CcCcwcqkDao jygkZzyCcCcwcqkDao;					//20012  20013
	@Autowired
	CcCcwcqkGsDao jygkZzyCcCcwcqkGDao;				//20014
	@Autowired
	CcKglyddcbqkDao jygkZzyCcKglyddcbqkDao;			//20015  20016
	@Autowired
	ChChjgjnhDao jygkZzyChChjgjnhDao;				//20017
	@Autowired
	ChZljjDao jygkZzyChZljjDao;						//20018
	@Autowired
	ChYclchDao jygkZzyChYclchDao;					//20019
	@Autowired
	DWXXDao dwxxDao;
	@Autowired
	QXGLDao qxglDao;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
		
	static HashMap<String, Integer> entrysize;
//	static HashMap 
	static{
		entrysize = new HashMap<String, Integer>();
		entrysize.put("20001", 4);
		entrysize.put("20002", 6);
		entrysize.put("20003", 7);
		entrysize.put("20004", 5);
		entrysize.put("20005", 7);
		entrysize.put("20006", 7);
		entrysize.put("20007", 5);
		entrysize.put("20008", 8);
		entrysize.put("20009", 12);
		entrysize.put("20010", 12);
		entrysize.put("20011", 8);
		entrysize.put("20012", 8);
		entrysize.put("20013", 8);
		entrysize.put("20014", 5);
		entrysize.put("20015", 30);
		entrysize.put("20016", 30);
		entrysize.put("20017", 11);
		entrysize.put("20018", 27);
		entrysize.put("20019", 4);
	}
	
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
		if("20009".equals(entryType) || "20010".equals(entryType)){
			cal.add(Calendar.YEAR, -1);
			String[] val0 = new String[entrysize.get(entryType)];
			val0[0] = "0";
			val0[1] = "去年同期";
			Object[] data = zzyCksjDao.getZb(0, new java.sql.Date(cal.getTimeInMillis()), Integer.parseInt(comp), Integer.parseInt(entryType));
			if(data != null){
				for(int j = 0; j < val0.length - 4; j++){
					val0[j + 2] = data[j] + "";
				}
			}
			list.add(val0);
			String[] val = new String[entrysize.get(entryType)];
			val[0] = "0";
			val[1] = cal.get(Calendar.YEAR) + "年当月";
			data = zzyCksjDao.getZb(0, date, Integer.parseInt(comp), Integer.parseInt(entryType));
			if(data != null){
				for(int j = 0; j < val.length - 4; j++){
					val[j + 2] = data[j] + "";
				}
			}
			list.add(val);
			List<String[]> cclist = getZb(date, comp, entryType.equals("20009") ? "20012" : "20013");
			list.get(0)[val.length - 2] = cclist.get(cclist.size() - 1)[3];
			list.get(0)[val.length - 1] = cclist.get(cclist.size() - 1)[6];
			list.get(1)[val.length - 2] = cclist.get(cclist.size() - 1)[2];
			list.get(1)[val.length - 1] = cclist.get(cclist.size() - 1)[5];
		} else if("20011".equals(entryType)){
			String[] val0 = new String[entrysize.get(entryType)];
			val0[0] = "0";
			val0[1] = cal.get(Calendar.YEAR) + "年当月";
//			val0[1] = cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月";
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
			String[] val = new String[entrysize.get(entryType)];
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
		} else if("20017".equals(entryType)){
			String[] val = new String[entrysize.get(entryType)];
			val[0] = "0";
			val[1] = cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月";
			Object[] data = zzyCksjDao.getZb(0, date, Integer.parseInt(comp), Integer.parseInt(entryType));
			if(data != null){
				for(int j = 0; j < val.length - 2; j++){
					val[j + 2] = data[j] + "";
				}
			}
			list.add(val);
		} else if("20015".equals(entryType) || "20016".equals(entryType)){
			String[] val = new String[entrysize.get(entryType)];
			 Object[] dw = zzyCksjDao.getBgCompanie(comp);
			val[0] = "0";
			val[1] = dw[1].toString();
			Object[] data = zzyCksjDao.getZb(0, date, Integer.parseInt(comp), Integer.parseInt(entryType));
			if(data != null){
				for(int j = 0; j < val.length - 2; j++){
					val[j + 2] = data[j] + "";
				}
			}
			list.add(val);
		} else if("20018".equals(entryType)){
			String[] val = new String[entrysize.get(entryType)];
			val[0] = "0";
			val[1] = "";
			Object[] data = zzyCksjDao.getZb(0, date, Integer.parseInt(comp), Integer.parseInt(entryType));
			if(data != null){
				for(int j = 0; j < val.length - 2; j++){
					val[j + 2] = data[j] + "";
				}
			}
			list.add(val);
		} else if("20008".equals(entryType)) {
			List<JygkZzyDwReferBglxfl> zblist = zzyCksjDao.getDwReferBglxfl(Integer.parseInt(comp), Integer.parseInt(entryType));
			for(int i = 0; i < zblist.size(); i++){
				String[] val = new String[entrysize.get(entryType)];
				DecimalFormat df = new DecimalFormat("0.000");
				JygkZzyFl fl = zzyCksjDao.getZbfl(zblist.get(i).getJygkZzyFl().getId());
				val[0] = zblist.get(i).getJygkZzyFl().getId() + "";
				val[1] = zblist.get(i).getJygkZzyFl().getName();
				Object[] data = zzyCksjDao.getZb(Integer.parseInt(val[0]), date, Integer.parseInt(comp), Integer.parseInt(entryType));
				if(data != null){
					val[2] = data[0] == null ? null : data[0].toString();
					val[3] = data[1] == null ? null : data[1].toString();
					if(data[0] == null || Float.parseFloat(data[0].toString()) == 0){
						val[4] = null;
					} else {
						if(data[1] == null){
							val[4] = "1.00";
						} else {
							val[4] = df.format((Double.parseDouble(data[0].toString()) - Double.parseDouble(data[1].toString()))/ Double.parseDouble(data[0].toString()));
						}
					}
				}
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(date);
				cal1.add(Calendar.YEAR, -1);
				data = zzyCksjDao.getZb(Integer.parseInt(val[0]), new java.sql.Date(cal1.getTimeInMillis()), Integer.parseInt(comp), Integer.parseInt(entryType));
				if(data != null){
					val[5] = data[0] == null ? null : data[0].toString();
					val[6] = data[1] == null ? null : data[1].toString();
					if(data[0] == null || Float.parseFloat(data[0].toString()) == 0){
						val[7] = null;
					} else {
						if(data[1] == null){
							val[7] = "1.00";
						} else {
							val[7] = df.format((Double.parseDouble(data[0].toString()) - Double.parseDouble(data[1].toString()))/ Double.parseDouble(data[0].toString()));
						}
					}
				}
				list.add(val);
			}
		} else if("20012".equals(entryType) || "20013".equals(entryType)) {
			List<JygkZzyDwReferBglxfl> zblist = zzyCksjDao.getDwReferBglxfl(Integer.parseInt(comp), Integer.parseInt(entryType));
			String[] total = new String[entrysize.get(entryType)];
			total[0] = "total";
			total[1] = "合计";
			DecimalFormat df = new DecimalFormat("0.000");
			for(int i = 0; i < zblist.size(); i++){
				String[] val = new String[entrysize.get(entryType)];
				JygkZzyFl fl = zzyCksjDao.getZbfl(zblist.get(i).getJygkZzyFl().getId());
				val[0] = zblist.get(i).getJygkZzyFl().getId() + "";
				val[1] = zblist.get(i).getJygkZzyFl().getName();
				Object[] data = zzyCksjDao.getZb(Integer.parseInt(val[0]), date, Integer.parseInt(comp), Integer.parseInt(entryType));
				if(data != null){
					val[2] = data[0] == null ? null : data[0].toString();
					val[5] = data[1] == null ? null : data[1].toString();
					total[2] = total[2] == null ? (data[0] == null ? "0" : data[0].toString()) : (data[0] == null ? total[2] : String.valueOf(Float.parseFloat(total[2]) + Float.parseFloat(data[0].toString())));
					total[5] = total[5] == null ? (data[1] == null ? "0" : data[1].toString()) : (data[1] == null ? total[5] : String.valueOf(Float.parseFloat(total[5]) + Float.parseFloat(data[1].toString())));
				}
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(date);
				cal1.add(Calendar.YEAR, -1);
				data = zzyCksjDao.getZb(Integer.parseInt(val[0]), new java.sql.Date(cal1.getTimeInMillis()), Integer.parseInt(comp), Integer.parseInt(entryType));
				if(data != null){
					val[3] = data[0] == null ? null : data[0].toString();
					val[6] = data[1] == null ? null : data[1].toString();
					total[3] = total[3] == null ? (data[0] == null ? "0" : data[0].toString()) : (data[0] == null ? total[3] : String.valueOf(Float.parseFloat(total[3]) + Float.parseFloat(data[0].toString())));
					total[6] = total[6] == null ? (data[1] == null ? "0" : data[1].toString()) : (data[1] == null ? total[6] : String.valueOf(Float.parseFloat(total[6]) + Float.parseFloat(data[1].toString())));
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
			list.add(total);
		} else if("20014".equals(entryType)) {
			List<JygkZzyDwReferBglxfl> zblist = zzyCksjDao.getDwReferBglxfl(Integer.parseInt(comp), Integer.parseInt(entryType));
			String[] total = new String[entrysize.get(entryType)];
			total[0] = "total";
			total[1] = "合计";
			DecimalFormat df = new DecimalFormat("0.000");
			for(int i = 0; i < zblist.size(); i++){
				String[] val = new String[entrysize.get(entryType)];
				JygkZzyFl fl = zzyCksjDao.getZbfl(zblist.get(i).getJygkZzyFl().getId());
				val[0] = zblist.get(i).getJygkZzyFl().getId() + "";
				val[1] = zblist.get(i).getJygkZzyFl().getName();
				Object[] data = zzyCksjDao.getZb(Integer.parseInt(val[0]), date, Integer.parseInt(comp), Integer.parseInt(entryType));
				if(data != null){
					val[2] = data[0] == null ? null : data[0].toString();
					total[2] = total[2] == null ? (data[0] == null ? "0" : data[0].toString()) : (data[0] == null ? total[2] : String.valueOf(Float.parseFloat(total[2]) + Float.parseFloat(data[0].toString())));
				}
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(date);
				cal1.add(Calendar.YEAR, -1);
				data = zzyCksjDao.getZb(Integer.parseInt(val[0]), new java.sql.Date(cal1.getTimeInMillis()), Integer.parseInt(comp), Integer.parseInt(entryType));
				if(data != null){
					val[3] = data[0] == null ? null : data[0].toString();
					total[3] = total[3] == null ? (data[0] == null ? "0" : data[0].toString()) : (data[0] == null ? total[3] : String.valueOf(Float.parseFloat(total[3]) + Float.parseFloat(data[0].toString())));
					if(data[0] == null || Float.parseFloat(data[0].toString()) == 0){
						val[4] = null;
					} else {
						if(val[4] == null){
							val[4] = "1.00";
						} else {
							val[4] = df.format((Double.parseDouble(val[2].toString()) - Double.parseDouble(val[3].toString()))/ Double.parseDouble(val[2].toString()));
						}
					}
				}
				list.add(val);
			}
			if(total[2] == null || Float.parseFloat(total[2].toString()) == 0){
				total[4] = null;
			} else {
				if(total[4] == null){
					total[4] = "1.00";
				} else {
					total[4] = df.format((Double.parseDouble(total[2].toString()) - Double.parseDouble(total[3].toString()))/ Double.parseDouble(total[2].toString()));
				}
			}
			list.add(total);
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

	@Override
	public List<JygkZzyBglx> getCksjBgList() {
		// TODO Auto-generated method stub
		return zzyCksjDao.getCksjBgList();
	}

	@Override
	public boolean saveZb(Date date, String comp, String entryType, JSONArray data) {
		boolean bRet = false;
		switch(Integer.parseInt(entryType)){
		case 20001:
			bRet = updateJygkZzyFxCpylspDqddmlqk(date, comp, data, entryType);
			break;
		case 20002:
		case 20003:
			bRet = updateJygkZzyFxCpylspHqlyddzl(date, comp, data, entryType);
			break;
		case 20004:
			bRet = updateJygkZzyFxJkcbZbwcqk(date, comp, data, entryType);
			break;
		case 20005:
		case 20006:
			bRet = updateJygkZzyFxJkcbJsjb(date, comp, data, entryType);
			break;
		case 20007:
			bRet = updateJygkZzyFxJkcbCgjb(date, comp, data, entryType);
			break;
		case 20008:
			bRet = updateJygkZzyFxJkcbScjb(date, comp, data, entryType);
			break;
		case 20009:
		case 20010:
			bRet = updateJygkZzyFxJkcbZtnhqk(date, comp, data, entryType);
			break;
		case 20011:
			bRet = updateJygkZzyFxJkcbXsjb(date, comp, data, entryType);
			break;
		case 20012:
		case 20013:
			bRet = updateJygkZzyCcCcwcqk(date, comp, data, entryType);
			break;
		case 20014:
			bRet = updateJygkZzyCcCcwcqkGs(date, comp, data, entryType);
			break;
		case 20015:
		case 20016:
			bRet = updateJygkZzyCcKglyddcbqk(date, comp, data, entryType);
			break;
		case 20017:
			bRet = updateJygkZzyChChjgjnh(date, comp, data, entryType);
			break;
		case 20018:
			bRet = updateJygkZzyChZljj(date, comp, data, entryType);
			break;
		case 20019:
			bRet = updateJygkZzyChYclch(date, comp, data, entryType);
			break;
		}
		return bRet;
	}

	//20001
	private boolean updateJygkZzyFxCpylspDqddmlqk(Date date, String company, JSONArray data, String entryType) {
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
			JygkZzyFxCpylspDqddmlqk object = new JygkZzyFxCpylspDqddmlqk();
			object = (JygkZzyFxCpylspDqddmlqk)zzyCksjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyFxCpylspDqddmlqk", entryType);
			boolean isNew = false;
			if(object == null){
				isNew = true;
				object = new JygkZzyFxCpylspDqddmlqk();
//				object.setId(zbId);
			}
			object.setDwid(Integer.parseInt(company));
			object.setZzyflId(Integer.parseInt(row.get(0).toString()));
			object.setNf(cal.get(Calendar.YEAR));
			object.setYf(cal.get(Calendar.MONTH) + 1);
			object.setSr(getBigDecimal(row.get(1)));
			object.setMle(getBigDecimal(row.get(2)));
			object.setXgsj(ts);
			if(isNew){
				jygkZzyFxCpylspDqddmlqkDao.create(object);
			} else {
				jygkZzyFxCpylspDqddmlqkDao.merge(object);
			}
		}

		return true;
	}

	//20002 20003
	private boolean updateJygkZzyFxCpylspHqlyddzl(Date date, String company, JSONArray data, String entryType) {
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
			JygkZzyFxCpylspHqlyddzl object = new JygkZzyFxCpylspHqlyddzl();
			object = (JygkZzyFxCpylspHqlyddzl)zzyCksjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyFxCpylspHqlyddzl", entryType);
			boolean isNew = false;
			if(object == null){
				isNew = true;
				object = new JygkZzyFxCpylspHqlyddzl();
//				object.setId(zbId);
			}
			object.setDwid(Integer.parseInt(company));
			object.setZzyflId(Integer.parseInt(row.get(0).toString()));
			object.setNf(cal.get(Calendar.YEAR));
			object.setJd(cal.get(Calendar.MONTH) + 1);
			object.setCz(getBigDecimal(row.get(1)));
			object.setCl(getBigDecimal(row.get(2)));
			object.setZbmll(getBigDecimal(row.get(3)));
			object.setYjyhhmle(getBigDecimal(row.get(4)));
			object.setYjyhhmll(getBigDecimal(row.get(5)));
			object.setXgsj(ts);
			if(isNew){
				jygkZzyFxCpylspHqlyddzlDao.create(object);
			} else {
				jygkZzyFxCpylspHqlyddzlDao.merge(object);
			}
		}

		return true;
	}

	//20004
	private boolean updateJygkZzyFxJkcbZbwcqk(Date date, String company, JSONArray data, String entryType) {
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
			JygkZzyFxJkcbZbwcqk object = new JygkZzyFxJkcbZbwcqk();
			object = (JygkZzyFxJkcbZbwcqk)zzyCksjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyFxJkcbZbwcqk", entryType);
			boolean isNew = false;
			if(object == null){
				isNew = true;
				object = new JygkZzyFxJkcbZbwcqk();
//				object.setId(zbId);
			}
			object.setDwid(Integer.parseInt(company));
			object.setZzyflId(Integer.parseInt(row.get(0).toString()));
			object.setNf(cal.get(Calendar.YEAR));
			object.setYf(cal.get(Calendar.MONTH) + 1);
			object.setNdjh(getBigDecimal(row.get(1)));
			object.setYdjh(getBigDecimal(row.get(2)));
			object.setYdwc(getBigDecimal(row.get(3)));
			object.setXgsj(ts);
			if(isNew){
				jygkZzyFxJkcbZbwcqkDao.create(object);
			} else {
				jygkZzyFxJkcbZbwcqkDao.merge(object);
			}
		}

		return true;
	}

	//20005、20006
	private boolean updateJygkZzyFxJkcbJsjb(Date date, String company, JSONArray data, String entryType) {
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
			JygkZzyFxJkcbJsjb object = new JygkZzyFxJkcbJsjb();
			object = (JygkZzyFxJkcbJsjb)zzyCksjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyFxJkcbJsjb", entryType);
			boolean isNew = false;
			if(object == null){
				isNew = true;
				object = new JygkZzyFxJkcbJsjb();
//				object.setId(zbId);
			}
			object.setDwid(Integer.parseInt(company));
			object.setZzyflId(Integer.parseInt(row.get(0).toString()));
			object.setNf(cal.get(Calendar.YEAR));
			object.setYf(cal.get(Calendar.MONTH) + 1);
			object.setScts(getBigDecimal(row.get(1)));
			object.setYhts(getBigDecimal(row.get(2)));
			object.setJgcsyhjb(getBigDecimal(row.get(3)));
			object.setCltdjb(getBigDecimal(row.get(4)));
			object.setQtjb(getBigDecimal(row.get(5)));
			object.setXgsj(ts);
			if(isNew){
				jygkZzyFxJkcbJsjbDao.create(object);
			} else {
				jygkZzyFxJkcbJsjbDao.merge(object);
			}
		}

		return true;
	}

	//20007
	private boolean updateJygkZzyFxJkcbCgjb(Date date, String company, JSONArray data, String entryType) {
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
			JygkZzyFxJkcbCgjb object = new JygkZzyFxJkcbCgjb();
			object = (JygkZzyFxJkcbCgjb)zzyCksjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyFxJkcbCgjb", entryType);
			boolean isNew = false;
			if(object == null){
				isNew = true;
				object = new JygkZzyFxJkcbCgjb();
//				object.setId(zbId);
			}
			object.setDwid(Integer.parseInt(company));
			object.setZzyflId(Integer.parseInt(row.get(0).toString()));
			object.setNf(cal.get(Calendar.YEAR));
			object.setYf(cal.get(Calendar.MONTH) + 1);
			object.setNdjh(getBigDecimal(row.get(1)));
			object.setYdjh(getBigDecimal(row.get(2)));
			object.setYdwc(getBigDecimal(row.get(3)));
			object.setXgsj(ts);
			if(isNew){
				jygkZzyFxJkcbCgjbDao.create(object);
			} else {
				jygkZzyFxJkcbCgjbDao.merge(object);
			}
		}

		return true;
	}

	//20008
	private boolean updateJygkZzyFxJkcbScjb(Date date, String company, JSONArray data, String entryType) {
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
			JygkZzyFxJkcbScjb object = new JygkZzyFxJkcbScjb();
			object = (JygkZzyFxJkcbScjb)zzyCksjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyFxJkcbScjb", entryType);
			boolean isNew = false;
			if(object == null){
				isNew = true;
				object = new JygkZzyFxJkcbScjb();
//				object.setId(zbId);
			}
			object.setDwid(Integer.parseInt(company));
			object.setZzyflId(Integer.parseInt(row.get(0).toString()));
			object.setNf(cal.get(Calendar.YEAR));
			object.setYf(cal.get(Calendar.MONTH) + 1);
			object.setSjlyl(getBigDecimal(row.get(1)));
			object.setFl(getBigDecimal(row.get(2)));
			object.setXgsj(ts);
			if(isNew){
				jygkZzyFxJkcbScjbDao.create(object);
			} else {
				jygkZzyFxJkcbScjbDao.merge(object);
			}
		}

		return true;
	}

	//20009、20010
	private boolean updateJygkZzyFxJkcbZtnhqk(Date date, String company, JSONArray data, String entryType) {
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
			JygkZzyFxJkcbZtnhqk object = new JygkZzyFxJkcbZtnhqk();
			object = (JygkZzyFxJkcbZtnhqk)zzyCksjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyFxJkcbZtnhqk", entryType);
			boolean isNew = false;
			if(object == null){
				isNew = true;
				object = new JygkZzyFxJkcbZtnhqk();
//				object.setId(zbId);
			}
			object.setDwid(Integer.parseInt(company));
			object.setNf(cal.get(Calendar.YEAR));
			object.setYf(cal.get(Calendar.MONTH) + 1);
			object.setSyl(getBigDecimal(row.get(1)));
			object.setSje(getBigDecimal(row.get(2)));
			object.setDyl(getBigDecimal(row.get(3)));
			object.setDje(getBigDecimal(row.get(4)));
			object.setZqyl(getBigDecimal(row.get(5)));
			object.setZqje(getBigDecimal(row.get(6)));
			object.setRqyl(getBigDecimal(row.get(7)));
			object.setRqje(getBigDecimal(row.get(8)));
			object.setXgsj(ts);
			if(isNew){
				jygkZzyFxJkcbZtnhqkDao.create(object);
			} else {
				jygkZzyFxJkcbZtnhqkDao.merge(object);
			}
		}

		return true;
	}

	//20011
	private boolean updateJygkZzyFxJkcbXsjb(Date date, String company, JSONArray data, String entryType) {
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
			JygkZzyFxJkcbXsjb object = new JygkZzyFxJkcbXsjb();
			object = (JygkZzyFxJkcbXsjb)zzyCksjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyFxJkcbXsjb", entryType);
			boolean isNew = false;
			if(object == null){
				isNew = true;
				object = new JygkZzyFxJkcbXsjb();
//				object.setId(zbId);
			}
			object.setDwid(Integer.parseInt(company));
			object.setNf(cal.get(Calendar.YEAR));
			object.setYf(cal.get(Calendar.MONTH) + 1);
			object.setTc(getBigDecimal(row.get(1)));
			object.setTpzbz(getBigDecimal(row.get(2)));
			object.setYhfkfs(getBigDecimal(row.get(3)));
			object.setQxkhzd(getBigDecimal(row.get(4)));
			object.setQt(getBigDecimal(row.get(5)));
			object.setXgsj(ts);
			if(isNew){
				jygkZzyFxJkcbXsjbDao.create(object);
			} else {
				jygkZzyFxJkcbXsjbDao.merge(object);
			}
		}

		return true;
	}

	//20012 20013
	private boolean updateJygkZzyCcCcwcqk(Date date, String company, JSONArray data, String entryType) {
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
			JygkZzyCcCcwcqk object = new JygkZzyCcCcwcqk();
			object = (JygkZzyCcCcwcqk)zzyCksjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyCcCcwcqk", entryType);
			boolean isNew = false;
			if(object == null){
				isNew = true;
				object = new JygkZzyCcCcwcqk();
//				object.setId(zbId);
			}
			object.setDwid(Integer.parseInt(company));
			object.setZzyflId(Integer.parseInt(row.get(0).toString()));
			object.setNf(cal.get(Calendar.YEAR));
			object.setYf(cal.get(Calendar.MONTH) + 1);
			object.setCl(getBigDecimal(row.get(1)));
			object.setCz(getBigDecimal(row.get(2)));
			object.setXgsj(ts);
			if(isNew){
				jygkZzyCcCcwcqkDao.create(object);
			} else {
				jygkZzyCcCcwcqkDao.merge(object);
			}
		}

		return true;
	}

	//20014
	private boolean updateJygkZzyCcCcwcqkGs(Date date, String company, JSONArray data, String entryType) {
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
			JygkZzyCcCcwcqkGs object = new JygkZzyCcCcwcqkGs();
			object = (JygkZzyCcCcwcqkGs)zzyCksjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyCcCcwcqkGs", entryType);
			boolean isNew = false;
			if(object == null){
				isNew = true;
				object = new JygkZzyCcCcwcqkGs();
//				object.setId(zbId);
			}
			object.setDwid(Integer.parseInt(company));
			object.setZzyflId(Integer.parseInt(row.get(0).toString()));
			object.setNf(cal.get(Calendar.YEAR));
			object.setYf(cal.get(Calendar.MONTH) + 1);
			object.setGs(getBigDecimal(row.get(1)));
			object.setXgsj(ts);
			if(isNew){
				jygkZzyCcCcwcqkGDao.create(object);
			} else {
				jygkZzyCcCcwcqkGDao.merge(object);
			}
		}

		return true;
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
			object = (JygkZzyCcKglyddcbqk)zzyCksjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyCcKglyddcbqk", entryType);
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
				jygkZzyCcKglyddcbqkDao.create(object);
			} else {
				jygkZzyCcKglyddcbqkDao.merge(object);
			}
		}

		return true;
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
			object = (JygkZzyChChjgjnh)zzyCksjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyChChjgjnh", entryType);
			boolean isNew = false;
			if(object == null){
				isNew = true;
				object = new JygkZzyChChjgjnh();
//				object.setId(zbId);
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
				jygkZzyChChjgjnhDao.create(object);
			} else {
				jygkZzyChChjgjnhDao.merge(object);
			}
		}

		return true;
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
			object = (JygkZzyChZljj)zzyCksjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyChZljj", entryType);
			boolean isNew = false;
			if(object == null){
				isNew = true;
				object = new JygkZzyChZljj();
//				object.setId(zbId);
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
				jygkZzyChZljjDao.create(object);
			} else {
				jygkZzyChZljjDao.merge(object);
			}
		}

		return true;
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
			object = (JygkZzyChYclch)zzyCksjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyChYclch", entryType);
			boolean isNew = false;
			if(object == null){
				isNew = true;
				object = new JygkZzyChYclch();
//				object.setId(zbId);
			}
			object.setDwid(Integer.parseInt(company));
			object.setZzyflId(Integer.parseInt(row.get(0).toString()));
			object.setNf(cal.get(Calendar.YEAR));
			object.setYf(cal.get(Calendar.MONTH) + 1);
			object.setJzydkcje(getBigDecimal(row.get(1)));
			object.setNckcje(getBigDecimal(row.get(2)));
			object.setXgsj(ts);
			if(isNew){
				jygkZzyChYclchDao.create(object);
			} else {
				jygkZzyChYclchDao.merge(object);
			}
		}

		return true;
	}
}
