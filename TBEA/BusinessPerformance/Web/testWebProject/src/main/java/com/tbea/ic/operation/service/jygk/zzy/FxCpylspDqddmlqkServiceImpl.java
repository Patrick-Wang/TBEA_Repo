package com.tbea.ic.operation.service.jygk.zzy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.jygk.zzy.FxCpylspDqddmlqkDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ReferBglxDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ReferBglxflDao;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyDwReferBglxfl;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxCpylspDqddmlqk;


@Service
@Transactional("transactionManager")
public class FxCpylspDqddmlqkServiceImpl implements FxCpylspDqddmlqkService{

	@Autowired
	ReferBglxDao referBglxDao;
	
	@Autowired
	ReferBglxflDao referBglxflDao;	
	
	@Autowired
	FxCpylspDqddmlqkDao fxCpylspDqddmlqkDao;	
			
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
		List<JygkZzyDwReferBglxfl> bglxflList=referBglxflDao.getDataList(Integer.parseInt(dwxxid), 20001);
		List<JygkZzyFxCpylspDqddmlqk> dqddmlqkList=fxCpylspDqddmlqkDao.getDataListByDwDate(dwxxs,Integer.parseInt(nf), Integer.parseInt(yf));	
		List<JygkZzyFxCpylspDqddmlqk> dqddmlqkListQn=fxCpylspDqddmlqkDao.getDataListByDwDate(dwxxs,Integer.parseInt(nf)-1, Integer.parseInt(yf));	
		BigDecimal srsum=toBigDecimal("0");//当月收入合计
		BigDecimal mlesum=toBigDecimal("0");//当月毛利额合计
		BigDecimal srqnsum=toBigDecimal("0");//去年收入同期合计
		BigDecimal mleqnsum=toBigDecimal("0");//去年同期毛利额合计
		for (JygkZzyFxCpylspDqddmlqk d : dqddmlqkList){
			srsum=d.getSr()==null?srsum:srsum.add(d.getSr());//如果收入是null那么不相加
			mlesum=d.getMle()==null?mlesum:mlesum.add(d.getMle());//如果毛利额是null那么不相加
		}
		for (JygkZzyFxCpylspDqddmlqk d : dqddmlqkListQn){
			srqnsum=d.getSr()==null?srqnsum:srqnsum.add(d.getSr());//如果收入是null那么不相加
			mleqnsum=d.getMle()==null?mleqnsum:mleqnsum.add(d.getMle());//如果毛利额是null那么不相加	
		}
		List<String[]> ret = new ArrayList<String[]>();
		for (JygkZzyDwReferBglxfl bglxfl : bglxflList){
			String[] row = new String[13];
			row[0] = bglxfl.getJygkZzyFl().getViewname();
			JygkZzyFxCpylspDqddmlqk dqddmlqk=null;
			JygkZzyFxCpylspDqddmlqk dqddmlqkqn=null;
			for (JygkZzyFxCpylspDqddmlqk d : dqddmlqkList){
				if(bglxfl.getJygkZzyFl().getId()==d.getZzyflId()){
					dqddmlqk=d;				
				}					
			}
			for (JygkZzyFxCpylspDqddmlqk d : dqddmlqkListQn){
				if(bglxfl.getJygkZzyFl().getId()==d.getZzyflId()){
					dqddmlqkqn=d;
				}					
			}
			if(dqddmlqk==null&&dqddmlqkqn!=null){
				row[1]=null;//收入
				row[2]=null;//收入占比--当月产品大类收入/当月产品大类合计数
				row[3]=this.bigDecimalToString(dqddmlqkqn.getSr());//收入去年同期
				row[4]=this.bigDecimalToString(this.calZb(dqddmlqkqn.getSr(),srqnsum));//收入去年同期占比--去年同期产品大类收入/去年同期产品大类合计数
				row[5]=null;//收入同比--当月收入/去年同期收入数-1
				row[6]=null;//收入占比变化--当月收入占比-去年同期收入占比
				row[7]=null;//毛利额
				row[8]=this.bigDecimalToString(dqddmlqkqn.getMle());//毛利额去年同期
				row[9]=null;//毛利额同比--当月毛利额/去年同期毛利额-1
				row[10]=null;//毛利率--当月毛利额/当月收入
				row[11]=this.bigDecimalToString(this.calZb(dqddmlqkqn.getMle(),mleqnsum));//毛利率去年同期--去年同期毛利额/去年同期收入
				row[12]=null;//毛利率变化百分点--当月毛利率-去年同期毛利率
			}else if(dqddmlqk!=null&&dqddmlqkqn==null){
				row[1]=this.bigDecimalToString(dqddmlqk.getSr());//收入
				row[2]=this.bigDecimalToString(this.calZb(dqddmlqk.getSr(),srsum));//收入占比--当月产品大类收入/当月产品大类合计数
				row[3]=null;//收入去年同期
				row[4]=null;//收入去年同期占比--去年同期产品大类收入/去年同期产品大类合计数
				row[5]=null;//收入同比--当月收入/去年同期收入数-1
				row[6]=null;//收入占比变化--当月收入占比-去年同期收入占比
				row[7]=this.bigDecimalToString(dqddmlqk.getMle());//毛利额
				row[8]=null;//毛利额去年同期
				row[9]=null;//毛利额同比--当月毛利额/去年同期毛利额-1
				row[10]=this.bigDecimalToString(this.calZb(dqddmlqk.getMle(),dqddmlqk.getSr()));//毛利率--当月毛利额/当月收入
				row[11]=null;//毛利率去年同期--去年同期毛利额/去年同期收入
				row[12]=null;//毛利率变化百分点--当月毛利率-去年同期毛利率
			}else if(dqddmlqk==null&&dqddmlqkqn==null){
				row[1]=null;//收入
				row[2]=null;//收入占比--当月产品大类收入/当月产品大类合计数
				row[3]=null;//收入去年同期
				row[4]=null;//收入去年同期占比--去年同期产品大类收入/去年同期产品大类合计数
				row[5]=null;//收入同比--当月收入/去年同期收入数-1
				row[6]=null;//收入占比变化--当月收入占比-去年同期收入占比
				row[7]=null;//毛利额
				row[8]=null;//毛利额去年同期
				row[9]=null;//毛利额同比--当月毛利额/去年同期毛利额-1
				row[10]=null;//毛利率--当月毛利额/当月收入
				row[11]=null;//毛利率去年同期--去年同期毛利额/去年同期收入
				row[12]=null;//毛利率变化百分点--当月毛利率-去年同期毛利率
			}else{
				row[1]=this.bigDecimalToString(dqddmlqk.getSr());//收入
				row[2]=this.bigDecimalToString(this.calZb(dqddmlqk.getSr(),srsum));//收入占比--当月产品大类收入/当月产品大类合计数
				row[3]=this.bigDecimalToString(dqddmlqkqn.getSr());//收入去年同期
				row[4]=this.bigDecimalToString(this.calZb(dqddmlqkqn.getSr(),srqnsum));//收入去年同期占比--去年同期产品大类收入/去年同期产品大类合计数
				row[5]=this.bigDecimalToString(this.calTb(dqddmlqk.getSr(),dqddmlqkqn.getSr()));//收入同比--当月收入/去年同期收入数-1
				row[6]=this.bigDecimalToString(this.calZbbh(dqddmlqk.getSr(),srsum,dqddmlqkqn.getSr(),srqnsum));//收入占比变化--当月收入占比-去年同期收入占比
				row[7]=this.bigDecimalToString(dqddmlqk.getMle());//毛利额
				row[8]=this.bigDecimalToString(dqddmlqkqn.getMle());//毛利额去年同期
				row[9]=this.bigDecimalToString(this.calTb(dqddmlqk.getMle(),dqddmlqkqn.getMle()));//毛利额同比--当月毛利额/去年同期毛利额-1
				row[10]=this.bigDecimalToString(this.calZb(dqddmlqk.getMle(),dqddmlqk.getSr()));//毛利率--当月毛利额/当月收入
				row[11]=this.bigDecimalToString(this.calZb(dqddmlqkqn.getMle(),dqddmlqkqn.getSr()));//毛利率去年同期--去年同期毛利额/去年同期收入
				row[12]=this.bigDecimalToString(this.calZbbh(dqddmlqk.getMle(),dqddmlqk.getSr(),dqddmlqkqn.getMle(),dqddmlqkqn.getSr()));//毛利率变化百分点--当月毛利率-去年同期毛利率
			}
			ret.add(row);
		}
		
		
		String[] sumrow = new String[13];//合计		
		sumrow[0]="合计";
		sumrow[1]=this.bigDecimalToString(srsum);//收入
		sumrow[3]=this.bigDecimalToString(srqnsum);//收入去年同期
		sumrow[5]=this.bigDecimalToString(this.calTb(srsum,srqnsum));//收入同比
		sumrow[7]=this.bigDecimalToString(mlesum);//毛利额
		sumrow[8]=this.bigDecimalToString(mleqnsum);//毛利额去年同期
		sumrow[9]=this.bigDecimalToString(this.calTb(mlesum,mleqnsum));//毛利额同比--当月毛利额/去年同期毛利额-1
		sumrow[10]=this.bigDecimalToString(this.calZb(mlesum,srsum));//毛利率--当月毛利额/当月收入
		sumrow[11]=this.bigDecimalToString(this.calZb(mleqnsum,srqnsum));//毛利率--当月毛利额/当月收入
		sumrow[12]=this.bigDecimalToString(this.calZbbh(mlesum,srsum,mleqnsum,srqnsum));
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
	
	//占比变化，参数1类别值，参数2类别总值,参数3去年类别值，参数4去年类别总值,
	private BigDecimal calZbbh(BigDecimal va,BigDecimal vasum,BigDecimal vaqn,BigDecimal vaqnsum){
		BigDecimal bd=null;
		BigDecimal zb=null;
		BigDecimal zbqn=null;
		if(va!=null&&vasum!=null){
			zb=(vasum.compareTo(BigDecimal.valueOf(0))==0)?null:va.divide(vasum,4,BigDecimal.ROUND_HALF_UP);
		}
		if(va!=null&&vasum!=null){
			zbqn=(vaqnsum.compareTo(BigDecimal.valueOf(0))==0)?null:vaqn.divide(vaqnsum,4,BigDecimal.ROUND_HALF_UP);
		}
		
		if(zb!=null&&zbqn!=null){
			bd=zb.subtract(zbqn);
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
		if(va==null){
			return null;
		}else{
			return va.toString();
		}
	}
}
