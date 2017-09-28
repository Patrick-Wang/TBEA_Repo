package com.tbea.ic.operation.service.cpzlqk.xladydjtjjg;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Formula;
import com.tbea.ic.operation.common.FormulaServer;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.model.dao.cpzlqk.xladwtjjg.XlAdwtjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDaoImpl;
import com.tbea.ic.operation.model.entity.cpzlqk.XlAdwtjjgEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.ZltjjgEntity;
import com.util.tools.MathUtil;
import com.util.tools.Pair;
import com.xml.frame.report.util.EasyCalendar;

@Service(XladydjtjjgServiceImpl.NAME)
@Transactional("transactionManager")
public class XladydjtjjgServiceImpl implements XladydjtjjgService {
	@Resource(name=ZltjjgDaoImpl.NAME)
	ZltjjgDao zltjjgDao;

	@Autowired
	XlAdwtjjgDao xladwtjjgDao;
	
	public final static String NAME = "XladydjtjjgServiceImpl";

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	private List<List<String>> getXladydjtjjg(Date d, YDJDType yjType, List<XlAdwtjjgEntity> entities, List<Integer> zts){
		List<Integer> comps = new ArrayList<Integer>();
		FormulaClientJd client = new FormulaClientJd(this, zltjjgDao, comps, d, yjType, zts);
		FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> fs = new FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>>(client);
		for (XlAdwtjjgEntity entity : entities){
			Formula formula = new Formula(entity.getFormul());
			Company comp = null;
			if (entity.getDwid() != null){
				comp = companyManager.getVirtualCYOrg().getCompanyById(entity.getDwid());
				comps.add(comp.getId());
			}
			client.add(formula, entity, comp);
			fs.addFormul(formula);
		}
		
		fs.run();
		return client.getResult();
	}
	
	@Override
	public List<List<String>> getXladydjtjjg(Date d, YDJDType yjType, List<Integer> zts) {
		List<XlAdwtjjgEntity> entities = xladwtjjgDao.getAll();
		return this.getXladydjtjjg(d, yjType, entities, zts);
	}
	
	private int setZltjjg(List<String> row, int start, ZltjjgEntity zltjjg){
		if (null != zltjjg){
			row.set(start++, "" + zltjjg.getBhgs());
			row.set(start++, "" + zltjjg.getZs());
			row.set(start++, "" + MathUtil.division(MathUtil.minus(zltjjg.getZs(), zltjjg.getBhgs()), zltjjg.getZs()));
		}else{
			start += 3;
		}
		return start;
	}
	

	void setRow(List<String> row, XlAdwtjjgEntity entity, ZltjjgEntity tj1, ZltjjgEntity tj2){
		int start = 0;
		if (null == entity.getDwmc()){
			row.set(start++, entity.getCpxl().getName());
		}else{
			row.set(start++, entity.getDwmc().getName());
		}
				 
		row.set(start++, entity.getCpdl().getName());
		start = setZltjjg(row, start, tj1);
		setZltjjg(row, start, tj2);
	}

	@Override
	public List<List<String>> getXladydjtjjg(Date d, YDJDType yjType,
			Company company, List<Integer> zts) {
		List<XlAdwtjjgEntity> entities = xladwtjjgDao.getByDw(company);
		return this.getXladydjtjjg(d, yjType, entities, zts);
	}

	private WaveItem getWaveItem(List<WaveItem> wis, String name){
		for (int i = 0; i < wis.size(); ++i){
			if (wis.get(i).getName().equals(name)){
				return wis.get(i);
			}
		}
		WaveItem item = new WaveItem(name, new ArrayList<String>());
		wis.add(item);
		return item;
	}
	
	private String toCtVal(String val){
		if (null == val || "".equals(val) || "null".equals(val)){
			val = "0";
		}else{
			val = Double.valueOf(val).doubleValue() * 100 + "";
		}
		return val;
	}
	@Override
	public List<WaveItem> getXladydjWaveItems(Date d, YDJDType yjType,
			Company company, List<Integer> zts) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		EasyCalendar cal = new EasyCalendar();
		cal.setTime(d);
		cal.setMonth(1);
		for (int j = 0; j < 12; ++j){
			List<List<String>> result = this.getXladydjtjjg(cal.getDate(), YDJDType.YD, xladwtjjgDao.getByDw(company), zts);
			for (int i = 0; i < result.size(); ++i){
				WaveItem item = getWaveItem(wis, result.get(i).get(1));
				item.getData().add(toCtVal(result.get(i).get(4)));
			}
			cal.addMonth(1);
		}
		return wis;
	}

}
