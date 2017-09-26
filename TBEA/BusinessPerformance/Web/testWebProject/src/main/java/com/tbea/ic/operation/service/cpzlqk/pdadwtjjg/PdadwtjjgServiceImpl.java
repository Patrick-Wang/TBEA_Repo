package com.tbea.ic.operation.service.cpzlqk.pdadwtjjg;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Formula;
import com.tbea.ic.operation.common.FormulaServer;
import com.tbea.ic.operation.common.MathUtil;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.controller.servlet.cpzlqk.WaveItem;
import com.tbea.ic.operation.controller.servlet.cpzlqk.YDJDType;
import com.tbea.ic.operation.model.dao.cpzlqk.pdadwtjjg.PdAdwtjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.pdadwtjjg.PdAdwtjjgDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDaoImpl;
import com.tbea.ic.operation.model.entity.cpzlqk.PdAdwtjjgEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.ZltjjgEntity;
import com.util.tools.Pair;
import com.xml.frame.report.util.EasyCalendar;


@Service(PdadwtjjgServiceImpl.NAME)
@Transactional("transactionManager")
public class PdadwtjjgServiceImpl implements PdadwtjjgService {
	@Resource(name=PdAdwtjjgDaoImpl.NAME)
	PdAdwtjjgDao pdAdwtjjgDao;

	@Resource(name=ZltjjgDaoImpl.NAME)
	ZltjjgDao zltjjgDao;

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	public final static String NAME = "PdadwtjjgServiceImpl";

	@Override
	public List<List<String>> getPdadwtjjg(Date d,
			YDJDType yjType, List<Integer> zts) {
		return getPdadwtjjg(d, yjType, pdAdwtjjgDao.getAll(), zts);
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
	

	void setRow(List<String> row, PdAdwtjjgEntity entity, ZltjjgEntity tj1, ZltjjgEntity tj2){
		int start = 0;
		row.set(start++, entity.getDwmc().getName());
		row.set(start++, entity.getCpxl().getName());
		start = setZltjjg(row, start, tj1);
		setZltjjg(row, start, tj2);
	}

	
	private List<List<String>> getPdadwtjjg(Date d, YDJDType yjType, List<PdAdwtjjgEntity> entities, List<Integer> zts) {
		List<Integer> comps = new ArrayList<Integer>();
		FormulaClientJd client = new FormulaClientJd(this, zltjjgDao, comps, d, yjType, zts);
		FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> fs = new FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>>(client);
		for (PdAdwtjjgEntity entity : entities){
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
	public List<List<String>> getPdadwtjjg(Date d, YDJDType yjType,
			Company company, List<Integer> zts) {
		return this.getPdadwtjjg(d, yjType, pdAdwtjjgDao.getByDw(company), zts);
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
	public List<WaveItem> getPdYdAdwtjjgWaveItems(Date d, Company company, List<Integer> zts) {
		
		List<WaveItem> wis = new ArrayList<WaveItem>();
		EasyCalendar cal = new EasyCalendar();
		cal.setTime(d);
		cal.setMonth(1);
		for (int j = 0; j < 12; ++j){
			List<List<String>> result = this.getPdadwtjjg(cal.getDate(), YDJDType.YD, pdAdwtjjgDao.getByDw(company), zts);
			for (int i = 0; i < result.size(); ++i){
				WaveItem item = getWaveItem(wis, result.get(i).get(0));
				item.getData().add(toCtVal(result.get(i).get(4)));
			}
			cal.addMonth(1);
		}
		return wis;
	}

	@Override
	public List<WaveItem> getPdYdAdwtjjgWaveItems(Date d, List<Integer> zts) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		EasyCalendar cal = new EasyCalendar();
		cal.setTime(d);
		cal.setMonth(1);
		for (int j = 0; j < 12; ++j){
			List<List<String>> result = this.getPdadwtjjg(cal.getDate(), YDJDType.YD, zts);
			for (int i = 0; i < result.size(); ++i){
				WaveItem item = getWaveItem(wis, result.get(i).get(0));
				item.getData().add(toCtVal(result.get(i).get(4)));
			}
			cal.addMonth(1);
		}
		return wis;
	}

	@Override
	public List<WaveItem> getWaveItems(Date d, List<Integer> zts) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		EasyCalendar cal = new EasyCalendar();
		cal.setTime(d);
		cal.setMonth(1);
		for (int j = 0; j < 12; ++j){
			List<List<String>> result = this.getPdadwtjjg(cal.getDate(), YDJDType.YD, zts);
			for (int i = 0; i < result.size(); ++i){
				WaveItem item = getWaveItem(wis, result.get(i).get(0));
				item.getData().add(toCtVal(result.get(i).get(4)));
			}
			cal.addMonth(1);
		}
		return wis;
	}
}
