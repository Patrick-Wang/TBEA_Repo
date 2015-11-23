package com.tbea.ic.operation.service.jygk.zzy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.model.dao.jygk.zzy.FxSxfykzDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ZzyNdjhzbDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ZzySjzbDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ZzyYdjhzbDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ZzyZBXXDao;
import com.tbea.ic.operation.model.entity.jygk.NDJHZB;
import com.tbea.ic.operation.model.entity.jygk.SJZB;
import com.tbea.ic.operation.model.entity.jygk.YDJHZB;
import com.tbea.ic.operation.model.entity.jygk.ZBXX;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxSxfykz;

@Service
@Transactional("transactionManager")
public class FxSxfykzServiceImpl implements FxSxfykzService{
	
	@Autowired
	ZzyNdjhzbDao zzyNdjhzbDao;	
	@Autowired
	ZzySjzbDao zzySjzbDao;
	@Autowired
	ZzyYdjhzbDao ZzyYdjhzbDao;
	@Autowired
	ZzyZBXXDao zzyZBXXDao;
	@Autowired
	FxSxfykzDao fxSxfykzDao;
			
	@Override
	public List<String[]> getViewDataList(String dwxxid,String nf,String yf) {
		//管理费用(财务口径)	221
		//	其中：固定费用	222
	    // 	其中：变动费用	223
	    //销售费用(财务口径)	224
	    // 	其中：固定费用	225
	    // 	其中：变动费用	226
		//财务费用(财务口径)	227
		int xssrzbid=6;//销售收入指标id
		int[] zbxxid={221,222,223,224,225,226,227};//三项费用指标数组
		String zbidstrs="221,222,223,224,225,226,227";	//三项费用指标字符串
		List<ZBXX> ZBXXList=zzyZBXXDao.getZbs(zbidstrs);
		List<NDJHZB> NDJHZBList=zzyNdjhzbDao.getDataListByDwDate(Integer.parseInt(dwxxid),zbidstrs,Integer.parseInt(nf));	//各指标年度计划
		Map<Integer,BigDecimal> NDJHZBmap=new HashMap<Integer,BigDecimal>();//各指标年度计划map
		List<SJZB> SJZBList=zzySjzbDao.getDataListByDwDate(Integer.parseInt(dwxxid),zbidstrs,Integer.parseInt(nf), Integer.parseInt(yf));	//各指标月实际
		Map<Integer,BigDecimal> SJZBmap=new HashMap<Integer,BigDecimal>();//各指标月实际map
		List<YDJHZB> YDJHZBList=ZzyYdjhzbDao.getDataListByDwDate(Integer.parseInt(dwxxid),zbidstrs,Integer.parseInt(nf), Integer.parseInt(yf));	//各指标月计划
		Map<Integer,BigDecimal> YDJHZBmap=new HashMap<Integer,BigDecimal>();//各指标月计划map
		List<NDJHZB> NDJHZBListqn=zzyNdjhzbDao.getDataListByDwDate(Integer.parseInt(dwxxid),zbidstrs,Integer.parseInt(nf)-1);	//各指标去年年度计划
		Map<Integer,BigDecimal> NDJHZBmapqn=new HashMap<Integer,BigDecimal>();//各指标去年月计划map
		List<SJZB> SJZBListqn=zzySjzbDao.getDataListByDwDate(Integer.parseInt(dwxxid),zbidstrs,Integer.parseInt(nf)-1, Integer.parseInt(yf));	//各指标去年月实际
		Map<Integer,BigDecimal> SJZBmapqn=new HashMap<Integer,BigDecimal>();//各指标年月月实际map
		List<YDJHZB> YDJHZBListqn=ZzyYdjhzbDao.getDataListByDwDate(Integer.parseInt(dwxxid),zbidstrs,Integer.parseInt(nf)-1, Integer.parseInt(yf));	//各指标去年月计划
		Map<Integer,BigDecimal> YDJHZBmapqn=new HashMap<Integer,BigDecimal>();//各指标去年月计划map
		List<JygkZzyFxSxfykz> fxSxfykzList=fxSxfykzDao.getDataListByDwDate(Integer.parseInt(dwxxid), Integer.parseInt(nf), Integer.parseInt(yf));
		Map<Integer,BigDecimal> fxSxfykzmap=new HashMap<Integer,BigDecimal>();
		
		BigDecimal ndjhzsum=toBigDecimal("0");//各指标年度计划值合计
		BigDecimal ydjhzsum=toBigDecimal("0");//各指标月度计划值合计
		BigDecimal sjzsum=toBigDecimal("0");//各指标实际计划值合计
		BigDecimal ndjhzqnsum=toBigDecimal("0");//各指标去年年度计划值合计
		BigDecimal ydjhzqnsum=toBigDecimal("0");//各指标去年月度计划值合计
		BigDecimal sjzqnsum=toBigDecimal("0");//各指标去年实际计划值合计
		for (NDJHZB d : NDJHZBList){
			ndjhzsum=d.getNdjhz()==null?ndjhzsum:ndjhzsum.add(toBigDecimal(d.getNdjhz()));
			NDJHZBmap.put(Integer.valueOf(d.getZbxx().getId()), toBigDecimal(d.getNdjhz()));
		}
		for (YDJHZB d : YDJHZBList){
			ydjhzsum=d.getYdjhz()==null?ydjhzsum:ydjhzsum.add(toBigDecimal(d.getYdjhz()));
			YDJHZBmap.put(Integer.valueOf(d.getZbxx().getId()), toBigDecimal(d.getYdjhz()));
		}
		for (SJZB d : SJZBList){
			sjzsum=d.getSjz()==null?sjzsum:sjzsum.add(toBigDecimal(d.getSjz()));
			SJZBmap.put(Integer.valueOf(d.getZbxx().getId()), toBigDecimal(d.getSjz()));			
		}
		for (NDJHZB d : NDJHZBListqn){
			ndjhzqnsum=d.getNdjhz()==null?ndjhzqnsum:ndjhzqnsum.add(toBigDecimal(d.getNdjhz()));
			NDJHZBmapqn.put(Integer.valueOf(d.getZbxx().getId()), toBigDecimal(d.getNdjhz()));
		}
		for (YDJHZB d : YDJHZBListqn){
			ydjhzqnsum=d.getYdjhz()==null?ydjhzqnsum:ydjhzqnsum.add(toBigDecimal(d.getYdjhz()));
			YDJHZBmapqn.put(Integer.valueOf(d.getZbxx().getId()), toBigDecimal(d.getYdjhz()));
		}
		for (SJZB d : SJZBListqn){
			sjzqnsum=d.getSjz()==null?sjzqnsum:sjzqnsum.add(toBigDecimal(d.getSjz()));
			SJZBmapqn.put(Integer.valueOf(d.getZbxx().getId()), toBigDecimal(d.getSjz()));		
		}
		for (JygkZzyFxSxfykz d : fxSxfykzList){
			fxSxfykzmap.put(Integer.valueOf(d.getZbxxid()), d.getNdjhfyl());		
		}		
		
		Map<Integer,BigDecimal> sjljmap=new HashMap<Integer,BigDecimal>();//累计实际map
		for(int i=0;i<zbxxid.length;i++){
			List<SJZB> SJZBListlj=zzySjzbDao.readDataLjByDwFlData(Integer.parseInt(dwxxid),zbxxid[i],Integer.parseInt(nf), Integer.parseInt(yf));
			BigDecimal sjzbsumlj=toBigDecimal("0");
			for (SJZB d : SJZBListlj){
				sjzbsumlj=d.getSjz()==null?sjzbsumlj:sjzbsumlj.add(toBigDecimal(d.getSjz()));
			}
			sjljmap.put(Integer.valueOf(zbxxid[i]), sjzbsumlj);
		}
		Map<Integer,BigDecimal> sjljqnmap=new HashMap<Integer,BigDecimal>();//去年同期累计实际map
		for(int i=0;i<zbxxid.length;i++){
			List<SJZB> SJZBListqnlj=zzySjzbDao.readDataLjByDwFlData(Integer.parseInt(dwxxid),zbxxid[i],Integer.parseInt(nf)-1, Integer.parseInt(yf));
			BigDecimal sjzbsumqnlj=toBigDecimal("0");
			for (SJZB d : SJZBListqnlj){
				sjzbsumqnlj=d.getSjz()==null?sjzbsumqnlj:sjzbsumqnlj.add(toBigDecimal(d.getSjz()));
			}
			sjljqnmap.put(Integer.valueOf(zbxxid[i]), sjzbsumqnlj);
		}
		Map<Integer,BigDecimal> sjqbqnmap=new HashMap<Integer,BigDecimal>();//去年全年实际map
		for(int i=0;i<zbxxid.length;i++){
			List<SJZB> SJZBListqnqb=zzySjzbDao.readDataQnByDwFlData(Integer.parseInt(dwxxid),zbxxid[i],Integer.parseInt(nf)-1);
			BigDecimal sjzbsumqnqb=toBigDecimal("0");
			for (SJZB d : SJZBListqnqb){
				sjzbsumqnqb=d.getSjz()==null?sjzbsumqnqb:sjzbsumqnqb.add(toBigDecimal(d.getSjz()));
			}
			sjqbqnmap.put(Integer.valueOf(zbxxid[i]), sjzbsumqnqb);
		}
		
		BigDecimal sjljsum=toBigDecimal("0");  //累计实际合计
		for(Map.Entry<Integer, BigDecimal> entry:sjljmap.entrySet()){
			BigDecimal sjlj=entry.getValue();	
			sjljsum=sjlj==null?sjljsum:sjljsum.add(sjlj);
		}   
		
		BigDecimal sjljqnsum=toBigDecimal("0"); //去年同期累计实际合计
		for(Map.Entry<Integer, BigDecimal> entry:sjljmap.entrySet()){
			BigDecimal sjlj=entry.getValue();	
			sjljqnsum=sjlj==null?sjljqnsum:sjljqnsum.add(sjlj);
		} 
		
		BigDecimal sjqbqnsum=toBigDecimal("0"); //去年全年实际合计
		for(Map.Entry<Integer, BigDecimal> entry:sjljmap.entrySet()){
			BigDecimal sjlj=entry.getValue();	
			sjqbqnsum=sjlj==null?sjqbqnsum:sjqbqnsum.add(sjlj);
		}
		
		BigDecimal xssrsj=toBigDecimal("0");//月实际销售收入
		SJZB xssrSJZB=zzySjzbDao.readDataByDwFlData(Integer.parseInt(dwxxid),xssrzbid,Integer.parseInt(nf), Integer.parseInt(yf));	//销售收入月实际	
		if(xssrSJZB!=null){
			xssrsj=xssrSJZB.getSjz()==null?xssrsj:toBigDecimal(xssrSJZB.getSjz());//
		}		
		
		BigDecimal xssrsjqn=toBigDecimal("0");//去年月实际销售收入
		SJZB xssrSJZBqn=zzySjzbDao.readDataByDwFlData(Integer.parseInt(dwxxid),xssrzbid,Integer.parseInt(nf)-1, Integer.parseInt(yf));	//去年销售收入月实际		
		if(xssrSJZBqn!=null){
			xssrsjqn=xssrSJZBqn.getSjz()==null?xssrsjqn:toBigDecimal(xssrSJZBqn.getSjz());//
		}		
		
		BigDecimal xssrsjlj=toBigDecimal("0");//实际累计销售收入
		List<SJZB> xssrSJZBListlj=zzySjzbDao.readDataLjByDwFlData(Integer.parseInt(dwxxid),xssrzbid,Integer.parseInt(nf), Integer.parseInt(yf));
		for (SJZB d : xssrSJZBListlj){
			xssrsjlj=d.getSjz()==null?xssrsjlj:xssrsjlj.add(toBigDecimal(d.getSjz()));//如果是null那么不相加
		}
		
		BigDecimal xssrsjljqn=toBigDecimal("0");//去年同期实际累计销售收入
		List<SJZB> xssrSJZBListljqn=zzySjzbDao.readDataLjByDwFlData(Integer.parseInt(dwxxid),xssrzbid,Integer.parseInt(nf)-1, Integer.parseInt(yf));
		for (SJZB d : xssrSJZBListljqn){
			xssrsjljqn=d.getSjz()==null?xssrsjljqn:xssrsjljqn.add(toBigDecimal(d.getSjz()));//如果是null那么不相加
		}
		
		
		BigDecimal xssrsjqbqn=toBigDecimal("0");//去年全年实际销售收入
		List<SJZB> xssrSJZBListqbqn=zzySjzbDao.readDataQnByDwFlData(Integer.parseInt(dwxxid),xssrzbid,Integer.parseInt(nf)-1);
		for (SJZB d : xssrSJZBListqbqn){
			xssrsjqbqn=d.getSjz()==null?xssrsjqbqn:xssrsjqbqn.add(toBigDecimal(d.getSjz()));//如果是null那么不相加
		}
		
		List<String[]> ret = new ArrayList<String[]>();
		for (ZBXX zbxx : ZBXXList){
			Integer zbid=Integer.valueOf(zbxx.getId());
			String[] row = new String[19];
			row[0]=zbxx.getName();//指标名称
			row[1]=this.bigDecimalToString(NDJHZBmapqn.get(zbid));//去年年度计划;
			row[2]=this.bigDecimalToString(sjqbqnmap.get(zbid));//去年全年实际;
			row[3]=this.bigDecimalToString(this.calZb(sjqbqnmap.get(zbid),xssrsjqbqn));//占销售收入比例--去年全年实际/去年全年实际销售收入;
			row[4]=this.bigDecimalToString(NDJHZBmap.get(zbid));//年度计划;
			row[5]=this.bigDecimalToString(fxSxfykzmap.get(zbid));//年度计划费用率--无可用数据;
			row[6]=this.bigDecimalToString(this.calXj(NDJHZBmap.get(zbid),sjqbqnmap.get(zbid)));//年度计划对去年实际发生增减额--年度计划-去年全年实际;
			row[7]=this.bigDecimalToString(YDJHZBmap.get(zbid));//本月计划;
			row[8]=this.bigDecimalToString(SJZBmap.get(zbid));//本月实际;
			row[9]=this.bigDecimalToString(this.calZb(SJZBmap.get(zbid),YDJHZBmap.get(zbid)));//预算完成率--本月实际/本月计划;
			row[10]=this.bigDecimalToString(this.calZb(SJZBmap.get(zbid),xssrsj));//占销售收入比--本月实际/本月销售收入;
			row[11]=this.bigDecimalToString(sjljmap.get(zbid));//累计实际;
			row[12]=this.bigDecimalToString(this.calZb(sjljmap.get(zbid),NDJHZBmap.get(zbid)));//年度预算完成率--累计实际/年度计划;
			row[13]=this.bigDecimalToString(this.calZb(sjljmap.get(zbid),xssrsjlj));//占销售收入比--累计实际/累计销售收入;
			row[14]=this.bigDecimalToString(sjljqnmap.get(zbid));//去年累计实际;
			row[15]=this.bigDecimalToString(this.calZb(sjljqnmap.get(zbid),NDJHZBmapqn.get(zbid)));//去年占年度预算比例--去年累计实际/去年年度计划;
			row[16]=this.bigDecimalToString(this.calZb(sjljqnmap.get(zbid),xssrsjljqn));//占销售收入比例--去年累计实际/去年累计销售收入;
			row[17]=this.bigDecimalToString(this.calXj(sjljmap.get(zbid),sjljqnmap.get(zbid)));//增减额--累计实际-去年累计实际;
			row[18]=this.bigDecimalToString(this.calTb(sjljmap.get(zbid),sjljqnmap.get(zbid)));//增减率--累计实际/去年累计实际-1;
			ret.add(row);
		}		
		String[] sumrow = new String[19];//合计		
		sumrow[0]="合计";
		sumrow[1]=this.bigDecimalToString(ndjhzqnsum);//去年年度计划;
		sumrow[2]=this.bigDecimalToString(sjzqnsum);//去年全年实际;
		sumrow[3]=this.bigDecimalToString(this.calZb(sjzqnsum,xssrsjqbqn));//占销售收入比例--去年全年实际/去年全年实际销售收入;
		sumrow[4]=this.bigDecimalToString(ndjhzsum);//年度计划;
		sumrow[5]=null;//年度计划费用率--无可用数据;
		sumrow[6]=this.bigDecimalToString(this.calXj(ndjhzsum,sjzqnsum));//年度计划对去年实际发生增减额--年度计划-去年全年实际;
		sumrow[7]=this.bigDecimalToString(ydjhzsum);//本月计划;
		sumrow[8]=this.bigDecimalToString(sjzsum);//本月实际;
		sumrow[9]=this.bigDecimalToString(this.calZb(sjzsum,ydjhzsum));//预算完成率--本月实际/本月计划;
		sumrow[10]=this.bigDecimalToString(this.calZb(sjzsum,xssrsj));//占销售收入比--本月实际/本月销售收入;
		sumrow[11]=this.bigDecimalToString(sjljsum);//累计实际;
		sumrow[12]=this.bigDecimalToString(this.calZb(sjljsum,ndjhzsum));//年度预算完成率--累计实际/年度计划;
		sumrow[13]=this.bigDecimalToString(this.calZb(sjljsum,xssrsjlj));//占销售收入比--累计实际/累计销售收入;
		sumrow[14]=this.bigDecimalToString(sjljqnsum);//去年累计实际;
		sumrow[15]=this.bigDecimalToString(this.calZb(sjljqnsum,ndjhzqnsum));//去年占年度预算比例--去年累计实际/去年年度计划;
		sumrow[16]=this.bigDecimalToString(this.calZb(sjljqnsum,xssrsjljqn));//占销售收入比例--去年累计实际/去年累计销售收入;
		sumrow[17]=this.bigDecimalToString(this.calXj(sjljsum,sjljqnsum));//增减额--累计实际-去年累计实际;
		sumrow[18]=this.bigDecimalToString(this.calTb(sjljsum,sjljqnsum));//增减率--累计实际/去年累计实际-1;
		ret.add(sumrow);
		return ret;
	}
	
	private BigDecimal toBigDecimal(String val){
		BigDecimal bd=new BigDecimal(val);   
		//设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)    
		bd=bd.setScale(4, BigDecimal.ROUND_HALF_UP);   
		return bd;
	}
	
	private BigDecimal toBigDecimal(double val){
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
	
	//同比计算，参数1类别值，参数2类别去年值
	private BigDecimal calXj(BigDecimal va1,BigDecimal va2){
		BigDecimal bd=null;
		if(va1!=null&&va2!=null){
			bd=va1.subtract(va2);;
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
