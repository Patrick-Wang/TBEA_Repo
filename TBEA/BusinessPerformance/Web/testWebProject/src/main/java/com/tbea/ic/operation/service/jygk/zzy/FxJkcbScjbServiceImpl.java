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
import com.tbea.ic.operation.model.dao.jygk.zzy.ReferBglxflDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ZzyCksjDao;
import com.tbea.ic.operation.model.dao.qxgl.QXGLDao;
import com.tbea.ic.operation.model.entity.jygk.zzy.*;



@Service
@Transactional("transactionManager")
public class FxJkcbScjbServiceImpl implements FxJkcbScjbService{
	@Autowired
	FxJkcbScjbDao jygkZzyFxJkcbScjbDao;
	@Autowired
	ReferBglxflDao referBglxflDao;
	
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
	public List<String[]> getViewDataList(Date date, String comp, String entryType) {
		String dwxxs = "";
		if(comp.equals("900000")){//变压器产业
			dwxxs="1,2,3";
		}else if(comp.equals("800000")){//线缆产业
			dwxxs="4,5,6";
		}else{
			dwxxs=comp;
		}
		
		List<JygkZzyDwReferBglxfl> zblist = referBglxflDao.getDataList(Integer.parseInt(comp), Integer.parseInt(entryType));
		List<String[]> list = new ArrayList<String[]>();
		for(int i = 0; i < zblist.size(); i++){
			String[] val = new String[8];
			val[0] = zblist.get(i).getJygkZzyFl().getId() + "";
			val[1] = zblist.get(i).getJygkZzyFl().getName();
			JygkZzyFxJkcbScjb data = jygkZzyFxJkcbScjbDao.getViewData(Integer.parseInt(val[0]), date, dwxxs);
			if(data != null){
				val[2] = data.getSjlyl() == null ? null : data.getSjlyl().toString();
				val[3] = data.getFl() == null ? null : data.getFl().toString();
				if(data.getSjlyl() == null || data.getSjlyl().compareTo(BigDecimal.valueOf(0)) == 0){
					val[4] = null;
				} else {
					if(data.getFl() == null){
						val[4] = "1.00";
					} else {
						val[4] = getBigDecimal((Double.parseDouble(data.getSjlyl().toString()) - Double.parseDouble(data.getFl().toString()))/ Double.parseDouble(data.getSjlyl().toString())) + "";
					}
				}
			}
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date);
			cal1.add(Calendar.YEAR, -1);
			data = jygkZzyFxJkcbScjbDao.getViewData(Integer.parseInt(val[0]), new java.sql.Date(cal1.getTimeInMillis()), dwxxs);
			if(data != null){
				val[5] = data.getSjlyl() == null ? null : data.getSjlyl().toString();
				val[6] = data.getFl() == null ? null : data.getFl().toString();
				if(data.getSjlyl() == null || data.getSjlyl().compareTo(BigDecimal.valueOf(0)) == 0){
					val[7] = null;
				} else {
					if(data.getFl() == null){
						val[7] = "1.00";
					} else {
						val[7] = getBigDecimal((Double.parseDouble(data.getSjlyl().toString()) - Double.parseDouble(data.getFl().toString()))/ Double.parseDouble(data.getSjlyl().toString())) + "";;
					}
				}
			}
			list.add(val);
		}
		return list;
	}
}
