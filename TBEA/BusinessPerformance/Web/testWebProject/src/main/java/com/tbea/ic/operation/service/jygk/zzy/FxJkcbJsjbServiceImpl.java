package com.tbea.ic.operation.service.jygk.zzy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.jygk.zzy.FxJkcbJsjbDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ReferBglxDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ReferBglxflDao;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyDwReferBglxfl;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxJkcbJsjb;


@Service
@Transactional("transactionManager")
public class FxJkcbJsjbServiceImpl implements FxJkcbJsjbService{

	@Autowired
	ReferBglxDao referBglxDao;
	
	@Autowired
	ReferBglxflDao referBglxflDao;	
	
	@Autowired
	FxJkcbJsjbDao fxJkcbJsjbDao;	
			
	@Override
	public List<String[]> getViewDataListByq(String dwxxid,String nf,String yf) {
		List<JygkZzyDwReferBglxfl> bglxflList=referBglxflDao.getDataList(Integer.parseInt(dwxxid), 20005);
		List<JygkZzyFxJkcbJsjb> fxJkcbJsjbList=fxJkcbJsjbDao.getDataListByDwDate(Integer.parseInt(dwxxid),Integer.parseInt(nf), Integer.parseInt(yf));	
		BigDecimal sctssum=toBigDecimal("0");//生产台数合计
		BigDecimal yhtssum=toBigDecimal("0");//优化台数合计
		BigDecimal czsum=toBigDecimal("0");//产值合计
		BigDecimal jbsum=toBigDecimal("0");//降本合计
		BigDecimal jgcsyhjbsum=toBigDecimal("0");//结构参数优化降本合计
		BigDecimal cltdjbsum=toBigDecimal("0");//材料替代降本合计
		BigDecimal qtjbsum=toBigDecimal("0");//材料替代降本合计
		for (JygkZzyFxJkcbJsjb d : fxJkcbJsjbList){
			sctssum=d.getScts()==null?sctssum:sctssum.add(d.getScts());//如果收入是null那么不相加
			yhtssum=d.getYhts()==null?yhtssum:yhtssum.add(d.getYhts());//如果毛利额是null那么不相加
			jgcsyhjbsum=d.getJgcsyhjb()==null?jgcsyhjbsum:jgcsyhjbsum.add(d.getJgcsyhjb());//如果毛利额是null那么不相加
			cltdjbsum=d.getCltdjb()==null?cltdjbsum:cltdjbsum.add(d.getCltdjb());//如果毛利额是null那么不相加
			qtjbsum=d.getQtjb()==null?qtjbsum:qtjbsum.add(d.getQtjb());//如果毛利额是null那么不相加
		}
		jbsum=this.calJbHj(jgcsyhjbsum,cltdjbsum,qtjbsum);
		
		List<String[]> ret = new ArrayList<String[]>();
		for (JygkZzyDwReferBglxfl bglxfl : bglxflList){
			String[] row = new String[9];			
			row[0] = bglxfl.getJygkZzyFl().getViewname();
			JygkZzyFxJkcbJsjb jygkZzyFxJkcbJsjb=null;
			for (JygkZzyFxJkcbJsjb d : fxJkcbJsjbList){
				if(bglxfl.getJygkZzyFl().getId()==d.getZzyflId()){
					jygkZzyFxJkcbJsjb=d;				
				}					
			}
			
			if(jygkZzyFxJkcbJsjb!=null){
				row[1]=this.bigDecimalToString(jygkZzyFxJkcbJsjb.getScts());//生产台数
				row[2]=this.bigDecimalToString(jygkZzyFxJkcbJsjb.getYhts());//优化台数
				row[3]=this.bigDecimalToString(this.calZb(jygkZzyFxJkcbJsjb.getYhts(),jygkZzyFxJkcbJsjb.getScts()));//占比
				row[4]=null;//产值
				row[5]=this.bigDecimalToString(this.calJbHj(jygkZzyFxJkcbJsjb.getJgcsyhjb(), jygkZzyFxJkcbJsjb.getCltdjb(), jygkZzyFxJkcbJsjb.getQtjb()));//降本
				row[6]=this.bigDecimalToString(jygkZzyFxJkcbJsjb.getJgcsyhjb());//结构参数优化降本
				row[7]=this.bigDecimalToString(jygkZzyFxJkcbJsjb.getCltdjb());//材料替代降本计
				row[8]=this.bigDecimalToString(jygkZzyFxJkcbJsjb.getQtjb());//其他降本
			}
			ret.add(row);
		}
		
		
		String[] sumrow = new String[9];//合计
		sumrow[0]="合计";
		sumrow[1]=this.bigDecimalToString(sctssum);//生产台数
		sumrow[2]=this.bigDecimalToString(yhtssum);//优化台数
		sumrow[3]=this.bigDecimalToString(this.calZb(yhtssum,sctssum));//占比		
		sumrow[4]=this.bigDecimalToString(czsum);//产值
		sumrow[5]=this.bigDecimalToString(jbsum);//降本
		sumrow[6]=this.bigDecimalToString(jgcsyhjbsum);//结构参数优化降本
		sumrow[7]=this.bigDecimalToString(cltdjbsum);//材料替代降本计
		sumrow[8]=this.bigDecimalToString(qtjbsum);//其他降本
		ret.add(sumrow);
		return ret;
	}
	
	@Override
	public List<String[]> getViewDataListXl(String dwxxid,String nf,String yf) {
		List<JygkZzyDwReferBglxfl> bglxflList=referBglxflDao.getDataList(Integer.parseInt(dwxxid), 20006);
		List<JygkZzyFxJkcbJsjb> fxJkcbJsjbList=fxJkcbJsjbDao.getDataListByDwDate(Integer.parseInt(dwxxid),Integer.parseInt(nf), Integer.parseInt(yf));	
		BigDecimal czsum=toBigDecimal("0");//产值合计
		BigDecimal jbsum=toBigDecimal("0");//降本合计
		BigDecimal jgcsyhjbsum=toBigDecimal("0");//结构参数优化降本合计
		BigDecimal cltdjbsum=toBigDecimal("0");//材料替代降本合计
		BigDecimal qtjbsum=toBigDecimal("0");//材料替代降本合计
		for (JygkZzyFxJkcbJsjb d : fxJkcbJsjbList){
			jgcsyhjbsum=d.getJgcsyhjb()==null?jgcsyhjbsum:jgcsyhjbsum.add(d.getJgcsyhjb());//如果毛利额是null那么不相加
			cltdjbsum=d.getCltdjb()==null?cltdjbsum:cltdjbsum.add(d.getCltdjb());//如果毛利额是null那么不相加
			qtjbsum=d.getQtjb()==null?qtjbsum:qtjbsum.add(d.getQtjb());//如果毛利额是null那么不相加
		}
		jbsum=this.calJbHj(jgcsyhjbsum,cltdjbsum,qtjbsum);
		
		List<String[]> ret = new ArrayList<String[]>();
		for (JygkZzyDwReferBglxfl bglxfl : bglxflList){
			String[] row = new String[6];			
			row[0] = bglxfl.getJygkZzyFl().getViewname();
			JygkZzyFxJkcbJsjb jygkZzyFxJkcbJsjb=null;
			for (JygkZzyFxJkcbJsjb d : fxJkcbJsjbList){
				if(bglxfl.getJygkZzyFl().getId()==d.getZzyflId()){
					jygkZzyFxJkcbJsjb=d;				
				}					
			}
			
			if(jygkZzyFxJkcbJsjb!=null){
				row[1]=null;//产值
				row[2]=this.bigDecimalToString(this.calJbHj(jygkZzyFxJkcbJsjb.getJgcsyhjb(), jygkZzyFxJkcbJsjb.getCltdjb(), jygkZzyFxJkcbJsjb.getQtjb()));//降本
				row[3]=this.bigDecimalToString(jygkZzyFxJkcbJsjb.getJgcsyhjb());//结构参数优化降本
				row[4]=this.bigDecimalToString(jygkZzyFxJkcbJsjb.getCltdjb());//材料替代降本计
				row[5]=this.bigDecimalToString(jygkZzyFxJkcbJsjb.getQtjb());//其他降本
			}
			ret.add(row);
		}
		
		
		String[] sumrow = new String[6];//合计
		sumrow[0]="合计";
		sumrow[1]=this.bigDecimalToString(czsum);//产值
		sumrow[2]=this.bigDecimalToString(jbsum);//降本
		sumrow[3]=this.bigDecimalToString(jgcsyhjbsum);//结构参数优化降本
		sumrow[4]=this.bigDecimalToString(cltdjbsum);//材料替代降本计
		sumrow[5]=this.bigDecimalToString(qtjbsum);//其他降本
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
	
	//降本合计计算
	private BigDecimal calJbHj(BigDecimal va1,BigDecimal va2,BigDecimal va3){
		BigDecimal bd=toBigDecimal("0");
		if(va1!=null){
			bd=bd.add(va1);
		}
		if(va2!=null){
			bd=bd.add(va2);
		}
		if(va3!=null){
			bd=bd.add(va3);
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
