package com.tbea.ic.operation.service.jygk.zzy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.jygk.zzy.CcKglyddcbqkDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ZzyDWXXDao;

@Service
@Transactional("transactionManager")
public class CcKglyddcbqkServiceImpl implements CcKglyddcbqkService{
	@Autowired
	CcKglyddcbqkDao zzyKglyddcbqkDao;
	@Autowired
	ZzyDWXXDao zzyDwxxDao;
	
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
	public List<String[]> getViewDataList(String year, String month, String dwxxid, String bglx) {
		String dwxxs = "";
		if(dwxxid.equals("900000")){//变压器产业
			dwxxs="1,2,3";
		}else if(dwxxid.equals("800000")){//线缆产业
			dwxxs="4,5,6";
		}else{
			dwxxs=dwxxid;
		}
		List<String[]> list = new ArrayList<String[]>();
		String dwid = "";
		if("20015".equals(bglx)) {
			for(int x=0; x<dwxxs.split(",").length; x++) {
				dwid = dwxxs.split(",")[x];
				Object[] data = zzyKglyddcbqkDao.getViewDataByq(year, month, dwid);
				String[] val = new String[29];
				val[0] = zzyDwxxDao.getDwxx(Integer.parseInt(dwid)).getName();
				int k = 0;
				for(int j = 0; data !=null && j < data.length; j++){
					val[k + 1] = data[j] + "";
					if(k==7 || k==10 || k==13 || k==16 || k==19 || k==22) {
						if(data[0] != null && !"".equals(data[0]) && Double.parseDouble(data[0]+"") != 0) {
							val[k+2] = getBigDecimal(Double.parseDouble(val[k+1]) / Double.parseDouble(data[0]+"")) + "";
						} 
						k++;
					}
					k++;
				}
				list.add(val);
			}
		} else {
			for(int x=0; x<dwxxs.split(",").length; x++) {
				dwid = dwxxs.split(",")[x];
				Object[] data = zzyKglyddcbqkDao.getViewDataXl(year, month, dwid);
				String[] val = new String[16];
				val[0] = zzyDwxxDao.getDwxx(Integer.parseInt(dwid)).getName();
				int k = 0;
				for(int j = 0; data !=null && j < data.length; j++){
					val[k + 1] = data[j] + "";
					if(j==4 || j==6 || j==8) {
						if(data[0] != null && !"".equals(data[0]) && Double.parseDouble(data[0]+"") != 0) {
							val[k+2] = getBigDecimal(Double.parseDouble(data[j-1]+"") / Double.parseDouble(data[0]+""))+"";
						} 
						k++;
					}
					k++;
				}
				list.add(val);
			}
		}
		return list;
	}
}
