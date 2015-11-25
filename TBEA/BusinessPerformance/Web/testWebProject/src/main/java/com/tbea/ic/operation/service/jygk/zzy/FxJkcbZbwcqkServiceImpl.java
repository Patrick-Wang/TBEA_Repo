package com.tbea.ic.operation.service.jygk.zzy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.jygk.zzy.ReferBglxDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ReferBglxflDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.FxJkcbZbwcqkDao;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyDwReferBglxfl;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbZbwcqk;


@Service
@Transactional("transactionManager")
public class FxJkcbZbwcqkServiceImpl implements FxJkcbZbwcqkService{

	@Autowired
	ReferBglxDao referBglxDao;
	
	@Autowired
	ReferBglxflDao referBglxflDao;	
	
	@Autowired
	FxJkcbZbwcqkDao zbwcqkDao;	
			
	@Override
	public List<String[]> getViewDataList(String dwxxid,String nf,String yf) {
		String dwxxs="";
		if(dwxxid.equals("900000")){//变压器产业
			dwxxs="1,2,3";
		}else if(dwxxid.equals("800000")){//线缆产业
			dwxxs="4,5,6";
		}else{
			dwxxs=dwxxid;
		}
		List<JygkZzyDwReferBglxfl> bglxflList=referBglxflDao.getDataList(Integer.parseInt(dwxxid), 20004);
		List<JygkZzyFxJkcbZbwcqk> zbwcqkList=zbwcqkDao.getDataListByDwDate(dwxxs,Integer.parseInt(nf), Integer.parseInt(yf));	
		BigDecimal njhsum=toBigDecimal("0");//年计划入合计
		BigDecimal yjhsum=toBigDecimal("0");//月计划合计
		BigDecimal ywcsum=toBigDecimal("0");//月完成合计
		for (JygkZzyFxJkcbZbwcqk d : zbwcqkList){
			njhsum=d.getNdjh()==null?njhsum:njhsum.add(d.getNdjh());//如果收入是null那么不相加
			yjhsum=d.getYdjh()==null?yjhsum:yjhsum.add(d.getYdjh());//如果毛利额是null那么不相加
			ywcsum=d.getYdwc()==null?ywcsum:ywcsum.add(d.getYdwc());//如果毛利额是null那么不相加
		}
		
		List<String[]> ret = new ArrayList<String[]>();
		for (JygkZzyDwReferBglxfl bglxfl : bglxflList){
			String[] row = new String[5];			
			row[0] = bglxfl.getJygkZzyFl().getViewname();
			JygkZzyFxJkcbZbwcqk zbwcqk=null;
			for (JygkZzyFxJkcbZbwcqk d : zbwcqkList){
				if(bglxfl.getJygkZzyFl().getId()==d.getZzyflId()){
					zbwcqk=d;				
				}					
			}
			
			if(zbwcqk!=null){
				row[1]=this.bigDecimalToString(zbwcqk.getNdjh());//年度计划
				row[2]=this.bigDecimalToString(zbwcqk.getYdjh());//月度计划
				row[3]=this.bigDecimalToString(zbwcqk.getYdwc());//月完成
				row[4]=this.bigDecimalToString(this.calZb(zbwcqk.getYdwc(),zbwcqk.getYdjh()));//计划完成率--当月完成/当月计划				
			}
			ret.add(row);
		}
		
		
		String[] sumrow = new String[5];//合计		
		sumrow[0]="合计";
		sumrow[1]=this.bigDecimalToString(njhsum);//年计划总
		sumrow[2]=this.bigDecimalToString(yjhsum);//月计划总
		sumrow[3]=this.bigDecimalToString(ywcsum);//月完成总
		sumrow[4]=this.bigDecimalToString(this.calZb(ywcsum,yjhsum));
		ret.add(sumrow);
		return ret;
	}
	
	private BigDecimal toBigDecimal(String val){
		BigDecimal bd=new BigDecimal(val);   
		//设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)    
		bd=bd.setScale(4, BigDecimal.ROUND_HALF_UP);   
		return bd;
	}
	//占比计算，参数1类别值，参数2类别总值
	private BigDecimal calZb(BigDecimal va,BigDecimal vasum){
		BigDecimal bd=null;
		if(va!=null&&vasum!=null){
			bd=(vasum.compareTo(BigDecimal.valueOf(0))==0)?null:va.divide(vasum,4,BigDecimal.ROUND_HALF_UP);
		}
		return bd;
	}	
	
	private String bigDecimalToString(BigDecimal va){
		if(va==null||(va.compareTo(BigDecimal.valueOf(0))==0)){
			return null;
		}else{
			return va.toString();
		}
	}
}
