package com.tbea.ic.operation.service.jygk.zzy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.tbea.ic.operation.model.dao.jygk.zzy.FxCpylspHqlyddzlDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ReferBglxDao;
import com.tbea.ic.operation.model.dao.jygk.zzy.ReferBglxflDao;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyDwReferBglxfl;
import com.tbea.ic.operation.model.entity.jygk.zzy.JygkZzyFxCpylspHqlyddzl;


@Service
@Transactional("transactionManager")
public class FxCpylspHqlyddzlServiceImpl implements FxCpylspHqlyddzlService{

	@Autowired
	ReferBglxDao referBglxDao;
	
	@Autowired
	ReferBglxflDao referBglxflDao;	
	
	@Autowired
	FxCpylspHqlyddzlDao fxCpylspHqlyddzlDao;	
	
	@Override
	public boolean saveDataList(String dwxxid,String nf,String yf,JSONArray data) {
		JSONArray row;
		String fl;
		for (int i = 0; i < data.size(); ++i) {
			row = data.getJSONArray(i);			
			boolean newEntity = false;
			fl=row.getString(0);
			JygkZzyFxCpylspHqlyddzl  hqlyddzl  = fxCpylspHqlyddzlDao.readDataByDwFlDate(Integer.parseInt(dwxxid), Integer.parseInt(fl), Integer.parseInt(nf), Integer.parseInt(yf));
			if (null == hqlyddzl){
				newEntity = true;
				hqlyddzl = new JygkZzyFxCpylspHqlyddzl();				
				hqlyddzl.setDwid(Integer.parseInt(dwxxid));
				hqlyddzl.setZzyflId(Integer.parseInt(fl));
				hqlyddzl.setNf(Integer.parseInt(nf));
				hqlyddzl.setJd(Integer.parseInt(yf));		
			}		
			hqlyddzl.setCz(StringUtils.hasText(row.getString(1))?toBigDecimal(row.getString(1)):null);
			hqlyddzl.setCl(StringUtils.hasText(row.getString(2))?toBigDecimal(row.getString(2)):null);
			hqlyddzl.setZbmll(StringUtils.hasText(row.getString(3))?toBigDecimal(row.getString(3)):null);
			hqlyddzl.setYjyhhmle(StringUtils.hasText(row.getString(4))?toBigDecimal(row.getString(4)):null);
			hqlyddzl.setYjyhhmll(StringUtils.hasText(row.getString(5))?toBigDecimal(row.getString(5)):null);
			hqlyddzl.setXgsj(new java.sql.Timestamp(new java.util.Date().getTime()));
			if (newEntity) {
				fxCpylspHqlyddzlDao.create(hqlyddzl);
			} else {
				fxCpylspHqlyddzlDao.merge(hqlyddzl);
			}			
		}		
		return true;
	}
	
	@Override
	public List<String[]> getWriteDataList(String dwxxid,String nf,String yf) {
		List<JygkZzyDwReferBglxfl> bglxflList=referBglxflDao.getDataList(Integer.parseInt(dwxxid), 10002);
		List<JygkZzyFxCpylspHqlyddzl> hqlyddzlList=fxCpylspHqlyddzlDao.getDataListByDwDate(dwxxid,Integer.parseInt(nf), Integer.parseInt(yf));	
		List<String[]> ret = new ArrayList<String[]>();
		for (JygkZzyDwReferBglxfl bglxfl : bglxflList){	
			String[] row = new String[7];
			row[0] = bglxfl.getJygkZzyFl().getId() + "";
			row[1] = bglxfl.getJygkZzyFl().getViewname();		
			for (JygkZzyFxCpylspHqlyddzl hqlyddzl : hqlyddzlList){
				if(bglxfl.getJygkZzyFl().getId()==hqlyddzl.getZzyflId()){
					row[2]= this.bigDecimalToString(hqlyddzl.getCz());
					row[3]= this.bigDecimalToString(hqlyddzl.getCl());
					row[4]= this.bigDecimalToString(hqlyddzl.getZbmll());
					row[5]= this.bigDecimalToString(hqlyddzl.getYjyhhmle());
					row[6]= this.bigDecimalToString(hqlyddzl.getYjyhhmll());
				}					
			}
			ret.add(row);
		}		
		return ret;
	}
	
	@Override
	public List<String[]> getViewDataListByq(String dwxxid,String nf,String yf) {
		String dwxxs="";
		if(dwxxid.equals("900000")){//变压器产业
			dwxxs="1,2,3";
		}else if(dwxxid.equals("800000")){//线缆产业
			dwxxs="4,5,6";
		}else{
			dwxxs=dwxxid;
		}
		List<JygkZzyDwReferBglxfl> bglxflList=referBglxflDao.getDataList(Integer.parseInt(dwxxid), 20002);
		List<JygkZzyFxCpylspHqlyddzl> hqlyddzlList=fxCpylspHqlyddzlDao.getDataListByDwDate(dwxxs,Integer.parseInt(nf), Integer.parseInt(yf));
		BigDecimal czsum=toBigDecimal("0");//产值合计
		BigDecimal clsum=toBigDecimal("0");//产量合计
		BigDecimal yjyhhmlesum=toBigDecimal("0");//预计优化后毛利额
		for (JygkZzyFxCpylspHqlyddzl d : hqlyddzlList){
			czsum=d.getCz()==null?czsum:czsum.add(d.getCz());
			clsum=d.getCl()==null?clsum:clsum.add(d.getCl());
			yjyhhmlesum=d.getYjyhhmle()==null?yjyhhmlesum:yjyhhmlesum.add(d.getYjyhhmle());			
		}
		List<String[]> ret = new ArrayList<String[]>();
		for (JygkZzyDwReferBglxfl bglxfl : bglxflList){	
			String[] row = new String[6];
			row[0] = bglxfl.getJygkZzyFl().getViewname();
			for (JygkZzyFxCpylspHqlyddzl d : hqlyddzlList){
				if(bglxfl.getJygkZzyFl().getId()==d.getZzyflId()){
					row[1]= this.bigDecimalToString(d.getCz());
					row[2]= this.bigDecimalToString(d.getCl());
					row[3]= this.bigDecimalToString(d.getZbmll());
					row[4]= this.bigDecimalToString(d.getYjyhhmle());
					row[5]= this.bigDecimalToString(d.getYjyhhmll());	
				}
			}
			ret.add(row);
		}
		
		String[] sumrow = new String[6];//合计
		sumrow[0] = "合计";
		sumrow[1]= this.bigDecimalToString(czsum);
		sumrow[2]= this.bigDecimalToString(clsum);
		sumrow[3]= null;
		sumrow[4]= null;
		sumrow[5]= this.bigDecimalToString(yjyhhmlesum);
		ret.add(sumrow);
		return ret;
	}
	
	@Override
	public List<String[]> getViewDataListXl(String dwxxid,String nf,String yf) {
		String dwxxs="";
		if(dwxxid.equals("900000")){//变压器产业
			dwxxs="1,2,3";
		}else if(dwxxid.equals("800000")){//线缆产业
			dwxxs="4,5,6";
		}else{
			dwxxs=dwxxid;
		}
		List<JygkZzyDwReferBglxfl> bglxflList=referBglxflDao.getDataList(Integer.parseInt(dwxxid), 20003);
		List<JygkZzyFxCpylspHqlyddzl> hqlyddzlList=fxCpylspHqlyddzlDao.getDataListByDwDate(dwxxs,Integer.parseInt(nf), Integer.parseInt(yf));	
		BigDecimal czsum=toBigDecimal("0");//产值合计
		BigDecimal yjyhhmlesum=toBigDecimal("0");//预计优化后毛利额
		for (JygkZzyFxCpylspHqlyddzl d : hqlyddzlList){
			czsum=d.getCz()==null?czsum:czsum.add(d.getCz());
			yjyhhmlesum=d.getYjyhhmle()==null?yjyhhmlesum:yjyhhmlesum.add(d.getYjyhhmle());		
		}
		List<String[]> ret = new ArrayList<String[]>();
		for (JygkZzyDwReferBglxfl bglxfl : bglxflList){	
			String[] row = new String[5];
			row[0] = bglxfl.getJygkZzyFl().getViewname();		
			for (JygkZzyFxCpylspHqlyddzl hqlyddzl : hqlyddzlList){
				if(bglxfl.getJygkZzyFl().getId()==hqlyddzl.getZzyflId()){
					row[1]= this.bigDecimalToString(hqlyddzl.getCz());
					row[2]= this.bigDecimalToString(hqlyddzl.getZbmll());
					row[3]= this.bigDecimalToString(hqlyddzl.getYjyhhmle());
					row[4]= this.bigDecimalToString(hqlyddzl.getYjyhhmll());
				}					
			}
			ret.add(row);
		}
		
		String[] sumrow = new String[5];//合计
		sumrow[0] = "合计";
		sumrow[1]= this.bigDecimalToString(czsum);
		sumrow[2]= null;
		sumrow[3]= null;
		sumrow[4]= this.bigDecimalToString(yjyhhmlesum);
		ret.add(sumrow);
		return ret;
	}
	
	private BigDecimal toBigDecimal(String val){
		BigDecimal bd=new BigDecimal(val);   
		//设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)    
		bd=bd.setScale(4, BigDecimal.ROUND_HALF_UP);   
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
