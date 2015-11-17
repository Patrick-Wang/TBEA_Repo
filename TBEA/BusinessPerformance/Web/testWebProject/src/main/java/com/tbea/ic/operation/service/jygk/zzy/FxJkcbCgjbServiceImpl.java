package com.tbea.ic.operation.service.jygk.zzy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.jygk.zzy.FxJkcbCgjbDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ReferBglxDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ReferBglxflDao;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyDwReferBglxfl;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbCgjb;


@Service
@Transactional("transactionManager")
public class FxJkcbCgjbServiceImpl implements FxJkcbCgjbService{

	@Autowired
	ReferBglxDao referBglxDao;
	
	@Autowired
	ReferBglxflDao referBglxflDao;	
	
	@Autowired
	FxJkcbCgjbDao fxJkcbCgjbDao;	
			
	@Override
	public List<String[]> getViewDataList(String dwxxid,String nf,String yf) {
		List<JygkZzyDwReferBglxfl> bglxflList=referBglxflDao.getDataList(Integer.parseInt(dwxxid), 20007);
		List<JygkZzyFxJkcbCgjb> fxJkCgJsjbList=fxJkcbCgjbDao.getDataListByDwDate(Integer.parseInt(dwxxid),Integer.parseInt(nf), Integer.parseInt(yf));	
		List<JygkZzyFxJkcbCgjb> fxJkCgJsjbListqn=fxJkcbCgjbDao.getDataListByDwDate(Integer.parseInt(dwxxid),Integer.parseInt(nf)-1, Integer.parseInt(yf));	
		
		BigDecimal ndjhsum=toBigDecimal("0");//年度计划合计
		BigDecimal ydjhsum=toBigDecimal("0");//当月计划合计
		BigDecimal ydwcsum=toBigDecimal("0");//当月完成合计
		BigDecimal ydwcqnsum=toBigDecimal("0");//去年当月完成合计
		for (JygkZzyFxJkcbCgjb d : fxJkCgJsjbList){
			ndjhsum=d.getNdjh()==null?ndjhsum:ndjhsum.add(d.getNdjh());
			ydjhsum=d.getYdjh()==null?ydjhsum:ydjhsum.add(d.getYdjh());
			ydwcsum=d.getYdwc()==null?ydwcsum:ydwcsum.add(d.getYdwc());		
		}
		
		for (JygkZzyFxJkcbCgjb d : fxJkCgJsjbListqn){			
			ydwcqnsum=d.getYdwc()==null?ydwcqnsum:ydwcsum.add(d.getYdwc());		
		}
		
		List<String[]> ret = new ArrayList<String[]>();
		for (JygkZzyDwReferBglxfl bglxfl : bglxflList){
			String[] row = new String[7];
			row[0] = bglxfl.getJygkZzyFl().getViewname();
			JygkZzyFxJkcbCgjb jygkZzyFxJkcbCgjb=null;
			JygkZzyFxJkcbCgjb jygkZzyFxJkcbCgjbqn=null;
			for (JygkZzyFxJkcbCgjb d : fxJkCgJsjbList){
				if(bglxfl.getJygkZzyFl().getId()==d.getZzyflId()){
					jygkZzyFxJkcbCgjb=d;				
				}					
			}
			
			for (JygkZzyFxJkcbCgjb d : fxJkCgJsjbListqn){
				if(bglxfl.getJygkZzyFl().getId()==d.getZzyflId()){
					jygkZzyFxJkcbCgjbqn=d;				
				}					
			}
			
			if(jygkZzyFxJkcbCgjb==null&&jygkZzyFxJkcbCgjbqn!=null){
				row[1]=null;//年度计划
				row[2]=null;//月度计划
				row[3]=null;//月度完成
				row[4]=null;//月度计划完成率
				row[5]=this.bigDecimalToString(jygkZzyFxJkcbCgjbqn.getYdwc());//去年同期月度完成
				row[6]=null;//同比
			}else if(jygkZzyFxJkcbCgjb!=null&&jygkZzyFxJkcbCgjbqn==null){
				row[1]=this.bigDecimalToString(jygkZzyFxJkcbCgjb.getNdjh());//年度计划
				row[2]=this.bigDecimalToString(jygkZzyFxJkcbCgjb.getYdjh());//月度计划
				row[3]=this.bigDecimalToString(jygkZzyFxJkcbCgjb.getYdwc());//月度完成
				row[4]=this.bigDecimalToString(this.calZb(jygkZzyFxJkcbCgjb.getYdwc(),jygkZzyFxJkcbCgjb.getYdjh()));//月度计划完成率
				row[5]=null;//去年同期月度完成
				row[6]=null;//同比
			}else if(jygkZzyFxJkcbCgjb==null&&jygkZzyFxJkcbCgjbqn==null){
				row[1]=null;//年度计划
				row[2]=null;//月度计划
				row[3]=null;//月度完成
				row[4]=null;//月度计划完成率
				row[5]=null;//去年同期月度完成
				row[6]=null;//同比
			}else if(jygkZzyFxJkcbCgjb!=null&&jygkZzyFxJkcbCgjbqn!=null){
				row[1]=this.bigDecimalToString(jygkZzyFxJkcbCgjb.getNdjh());//年度计划
				row[2]=this.bigDecimalToString(jygkZzyFxJkcbCgjb.getYdjh());//月度计划
				row[3]=this.bigDecimalToString(jygkZzyFxJkcbCgjb.getYdwc());//月度完成
				row[4]=this.bigDecimalToString(this.calZb(jygkZzyFxJkcbCgjb.getYdwc(),jygkZzyFxJkcbCgjb.getYdjh()));//月度计划完成率
				row[5]=this.bigDecimalToString(jygkZzyFxJkcbCgjbqn.getYdwc());//去年同期月度完成
				row[6]=this.bigDecimalToString(this.calTb(jygkZzyFxJkcbCgjb.getYdwc(),jygkZzyFxJkcbCgjbqn.getYdwc()));//同比
			}			
			ret.add(row);
		}
		
		
		String[] sumrow = new String[7];//合计
		sumrow[0]="合计";
		sumrow[1]=this.bigDecimalToString(ndjhsum);//年度计划
		sumrow[2]=this.bigDecimalToString(ydjhsum);//月度计划
		sumrow[3]=this.bigDecimalToString(ydwcsum);//月度完成
		sumrow[4]=this.bigDecimalToString(this.calZb(ydwcsum,ydjhsum));//月度计划完成率
		sumrow[5]=this.bigDecimalToString(ydwcqnsum);//去年同期月度完成
		sumrow[6]=this.bigDecimalToString(this.calTb(ydwcsum,ydwcqnsum));//同比
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
	
	//同比计算，参数1类别值，参数2类别去年值
	private BigDecimal calTb(BigDecimal va,BigDecimal vaqn){
		BigDecimal bd=null;
		if(va!=null&&vaqn!=null){
			bd=(vaqn.compareTo(BigDecimal.valueOf(0))==0)?null:va.divide(vaqn,4,BigDecimal.ROUND_HALF_UP).subtract(toBigDecimal("1"));
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
