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
import com.tbea.ic.operation.model.dao.jygk.zzy.ZzyLrsjDao;
import com.tbea.ic.operation.model.dao.qxgl.QXGLDao;
import com.tbea.ic.operation.model.entity.jygk.zzy.*;



@Service
@Transactional("transactionManager")
public class ZzyLrsjServiceImpl implements ZzyLrsjService{
	@Autowired
	ZzyLrsjDao zzyLrsjDao;

	@Autowired
	FxCpylspDqddmlqkDao jygkZzyFxCpylspDqddmlqkDao;	//10001
	@Autowired
	FxCpylspHqlyddzlDao jygkZzyFxCpylspHqlyddzlDao;	//10002  10003
	@Autowired
	FxJkcbZbwcqkDao jygkZzyFxJkcbZbwcqkDao;			//10004
	@Autowired
	FxJkcbJsjbDao jygkZzyFxJkcbJsjbDao;				//10005 10006
	@Autowired
	FxJkcbCgjbDao jygkZzyFxJkcbCgjbDao;				//10007
	@Autowired
	FxJkcbScjbDao jygkZzyFxJkcbScjbDao;				//10008
	@Autowired
	FxJkcbZtnhqkDao jygkZzyFxJkcbZtnhqkDao;			//10009  10010
	@Autowired
	FxJkcbXsjbDao jygkZzyFxJkcbXsjbDao;				//10011
	@Autowired
	CcCcwcqkDao jygkZzyCcCcwcqkDao;					//10012  10013
	@Autowired
	CcCcwcqkGsDao jygkZzyCcCcwcqkGDao;				//10014
	@Autowired
	CcKglyddcbqkDao jygkZzyCcKglyddcbqkDao;			//10015  10016
	@Autowired
	ChChjgjnhDao jygkZzyChChjgjnhDao;				//10017
	@Autowired
	ChZljjDao jygkZzyChZljjDao;						//10018
	@Autowired
	ChYclchDao jygkZzyChYclchDao;					//10019
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
		entrysize.put("10001", 4);
		entrysize.put("10002", 6);
		entrysize.put("10003", 7);
		entrysize.put("10004", 3);
		entrysize.put("10005", 7);
		entrysize.put("10006", 7);
		entrysize.put("10007", 5);
		entrysize.put("10008", 4);
		entrysize.put("10009", 10);
		entrysize.put("10010", 10);
		entrysize.put("10011", 7);
		entrysize.put("10012", 4);
		entrysize.put("10013", 4);
		entrysize.put("10014", 3);
		entrysize.put("10015", 24);
		entrysize.put("10016", 14);
		entrysize.put("10017", 11);
		entrysize.put("10018", 27);
		entrysize.put("10019", 4);
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
		List<Object[]> dwxxs = zzyLrsjDao.getBgCompanies(bglx);
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
		if("10009".equals(entryType) || "10010".equals(entryType) || "10011".equals(entryType) || "10017".equals(entryType)){
			String[] val = new String[entrysize.get(entryType)];
			val[0] = "0";
			val[1] = cal.get(Calendar.YEAR) + "年" + (cal.get(Calendar.MONTH) + 1) + "月";
			Object[] data = zzyLrsjDao.getZb(0, date, Integer.parseInt(comp), Integer.parseInt(entryType));
			if(data != null){
				for(int j = 0; j < val.length - 2; j++){
					val[j + 2] = data[j] + "";
				}
			}
			list.add(val);
		} else if("10015".equals(entryType) || "10016".equals(entryType)){
			String[] val = new String[entrysize.get(entryType)];
			 Object[] dw = zzyLrsjDao.getBgCompanie(comp);
			val[0] = "0";
			val[1] = dw[1].toString();
			Object[] data = zzyLrsjDao.getZb(0, date, Integer.parseInt(comp), Integer.parseInt(entryType));
			if(data != null){
				for(int j = 0; j < val.length - 2; j++){
					val[j + 2] = data[j] + "";
				}
			}
			list.add(val);
		} else if("10018".equals(entryType)){
			String[] val = new String[entrysize.get(entryType)];
			val[0] = "0";
			val[1] = "";
			Object[] data = zzyLrsjDao.getZb(0, date, Integer.parseInt(comp), Integer.parseInt(entryType));
			if(data != null){
				for(int j = 0; j < val.length - 2; j++){
					val[j + 2] = data[j] + "";
				}
			}
			list.add(val);
		} else {
			List<JygkZzyDwReferBglxfl> zblist = zzyLrsjDao.getDwReferBglxfl(Integer.parseInt(comp), Integer.parseInt(entryType));
			for(int i = 0; i < zblist.size(); i++){
				String[] val = new String[entrysize.get(entryType)];
				JygkZzyFl fl = zzyLrsjDao.getZbfl(zblist.get(i).getJygkZzyFl().getId());
				val[0] = zblist.get(i).getJygkZzyFl().getId() + "";
				val[1] = zblist.get(i).getJygkZzyFl().getName();
				Object[] data = zzyLrsjDao.getZb(Integer.parseInt(val[0]), date, Integer.parseInt(comp), Integer.parseInt(entryType));
				if(data != null){
					for(int j = 0; j < val.length - 2; j++){
						val[j + 2] = data[j] + "";
					}
				}
				list.add(val);
			}
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
	public List<JygkZzyBglx> getLrsjBgList() {
		// TODO Auto-generated method stub
		return zzyLrsjDao.getLrsjBgList();
	}

	@Override
	public boolean saveZb(Date date, String comp, String entryType, JSONArray data) {
		boolean bRet = false;
		switch(Integer.parseInt(entryType)){
		case 10001:
			bRet = updateJygkZzyFxCpylspDqddmlqk(date, comp, data, entryType);
			break;
		case 10002:
		case 10003:
			bRet = updateJygkZzyFxCpylspHqlyddzl(date, comp, data, entryType);
			break;
		case 10004:
			bRet = updateJygkZzyFxJkcbZbwcqk(date, comp, data, entryType);
			break;
		case 10005:
		case 10006:
			bRet = updateJygkZzyFxJkcbJsjb(date, comp, data, entryType);
			break;
		case 10007:
			bRet = updateJygkZzyFxJkcbCgjb(date, comp, data, entryType);
			break;
		case 10008:
			bRet = updateJygkZzyFxJkcbScjb(date, comp, data, entryType);
			break;
		case 10009:
		case 10010:
			bRet = updateJygkZzyFxJkcbZtnhqk(date, comp, data, entryType);
			break;
		case 10011:
			bRet = updateJygkZzyFxJkcbXsjb(date, comp, data, entryType);
			break;
		case 10012:
		case 10013:
			bRet = updateJygkZzyCcCcwcqk(date, comp, data, entryType);
			break;
		case 10014:
			bRet = updateJygkZzyCcCcwcqkGs(date, comp, data, entryType);
			break;
		case 10015:
			bRet = updateJygkZzyCcKglyddcbqk(date, comp, data, entryType);
			break;
		case 10016:
			bRet = updateJygkZzyCcKglyddcbqkXl(date, comp, data, entryType);
			break;
		case 10017:
			bRet = updateJygkZzyChChjgjnh(date, comp, data, entryType);
			break;
		case 10018:
			bRet = updateJygkZzyChZljj(date, comp, data, entryType);
			break;
		case 10019:
			bRet = updateJygkZzyChYclch(date, comp, data, entryType);
			break;
		}
		return bRet;
	}

	//10001
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
			object = (JygkZzyFxCpylspDqddmlqk)zzyLrsjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyFxCpylspDqddmlqk", entryType);
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

	//10002 10003
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
			object = (JygkZzyFxCpylspHqlyddzl)zzyLrsjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyFxCpylspHqlyddzl", entryType);
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

	//10004
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
			object = (JygkZzyFxJkcbZbwcqk)zzyLrsjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyFxJkcbZbwcqk", entryType);
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

	//10005、10006
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
			object = (JygkZzyFxJkcbJsjb)zzyLrsjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyFxJkcbJsjb", entryType);
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

	//10007
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
			object = (JygkZzyFxJkcbCgjb)zzyLrsjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyFxJkcbCgjb", entryType);
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

	//10008
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
			object = (JygkZzyFxJkcbScjb)zzyLrsjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyFxJkcbScjb", entryType);
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

	//10009、10010
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
			object = (JygkZzyFxJkcbZtnhqk)zzyLrsjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyFxJkcbZtnhqk", entryType);
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

	//10011
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
			object = (JygkZzyFxJkcbXsjb)zzyLrsjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyFxJkcbXsjb", entryType);
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

	//10012 10013
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
			object = (JygkZzyCcCcwcqk)zzyLrsjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyCcCcwcqk", entryType);
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

	//10014
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
			object = (JygkZzyCcCcwcqkGs)zzyLrsjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyCcCcwcqkGs", entryType);
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

	//10015
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
			object = (JygkZzyCcKglyddcbqk)zzyLrsjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyCcKglyddcbqk", entryType);
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
			object.setN1cl(getBigDecimal(row.get(8)));
			object.setN2cz(getBigDecimal(row.get(9)));
			object.setN2cl(getBigDecimal(row.get(10)));
			object.setN3cz(getBigDecimal(row.get(11)));
			object.setN3cl(getBigDecimal(row.get(12)));
			object.setN4cz(getBigDecimal(row.get(13)));
			object.setN4cl(getBigDecimal(row.get(14)));
			object.setN5cz(getBigDecimal(row.get(15)));
			object.setN5cl(getBigDecimal(row.get(16)));
			object.setN6cz(getBigDecimal(row.get(17)));
			object.setN6cl(getBigDecimal(row.get(18)));
			object.setN6hcz(getBigDecimal(row.get(19)));
			object.setN6hcl(getBigDecimal(row.get(20)));
			object.setDdcz(getBigDecimal(row.get(21)));
			object.setDdcl(getBigDecimal(row.get(22)));
			object.setXgsj(ts);
			if(isNew){
				jygkZzyCcKglyddcbqkDao.create(object);
			} else {
				jygkZzyCcKglyddcbqkDao.merge(object);
			}
		}

		return true;
	}

	//10016
	private boolean updateJygkZzyCcKglyddcbqkXl(Date date, String company, JSONArray data, String entryType) {
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
			object = (JygkZzyCcKglyddcbqk)zzyLrsjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyCcKglyddcbqk", entryType);
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
			object.setKglyddzcz(getBigDecimal(row.get(2)));
			object.setNdkglyddzcz(getBigDecimal(row.get(3)));
			object.setN1cz(getBigDecimal(row.get(4)));
			object.setN1czn(getBigDecimal(row.get(5)));
			object.setN2cz(getBigDecimal(row.get(6)));
			object.setN2czn(getBigDecimal(row.get(7)));
			object.setN3cz(getBigDecimal(row.get(8)));
			object.setN3czn(getBigDecimal(row.get(9)));
			object.setN3hcz(getBigDecimal(row.get(10)));
			object.setDdcz(getBigDecimal(row.get(11)));
			object.setWxcz(getBigDecimal(row.get(12)));
			object.setXgsj(ts);
			if(isNew){
				jygkZzyCcKglyddcbqkDao.create(object);
			} else {
				jygkZzyCcKglyddcbqkDao.merge(object);
			}
		}

		return true;
	}

	//10017
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
			object = (JygkZzyChChjgjnh)zzyLrsjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyChChjgjnh", entryType);
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

	//10018
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
			object = (JygkZzyChZljj)zzyLrsjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyChZljj", entryType);
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

	//10019
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
			object = (JygkZzyChYclch)zzyLrsjDao.getZbObject(Integer.parseInt(row.get(0).toString()), date, Integer.parseInt(company), "JygkZzyChYclch", entryType);
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
