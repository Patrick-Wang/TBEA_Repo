package com.tbea.ic.operation.service.cpzlqk.xkadwtjjg;

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
import com.tbea.ic.operation.model.dao.cpzlqk.xkadwtjjg.XkAdwtjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.xkadwtjjg.XkAdwtjjgDaoImpl;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDao;
import com.tbea.ic.operation.model.dao.cpzlqk.zltjjg.ZltjjgDaoImpl;
import com.tbea.ic.operation.model.entity.cpzlqk.XkAdwtjjgEntity;
import com.tbea.ic.operation.model.entity.cpzlqk.ZltjjgEntity;
import com.xml.frame.report.util.EasyCalendar;
import com.xml.frame.report.util.Pair;


@Service(XkadwtjjgServiceImpl.NAME)
@Transactional("transactionManager")
public class XkadwtjjgServiceImpl implements XkadwtjjgService {
	@Resource(name=XkAdwtjjgDaoImpl.NAME)
	XkAdwtjjgDao xkAdwtjjgDao;

	@Resource(name=ZltjjgDaoImpl.NAME)
	ZltjjgDao zltjjgDao;

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	public final static String NAME = "XkadwtjjgServiceImpl";

	@Override
	public List<List<String>> getXkadwtjjg(Date d,
			YDJDType yjType,  List<Integer> zts) {
		return getXkadwtjjg(d, yjType, xkAdwtjjgDao.getAll(), zts);
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
	

	void setRow(List<String> row, XkAdwtjjgEntity entity, ZltjjgEntity tj1, ZltjjgEntity tj2){
		int start = 0;
		row.set(start++, entity.getDwmc().getName());
		row.set(start++, entity.getCpxl().getName());
		start = setZltjjg(row, start, tj1);
		setZltjjg(row, start, tj2);
	}

	
	private List<List<String>> getXkadwtjjg(Date d, YDJDType yjType, List<XkAdwtjjgEntity> entities,  List<Integer> zts) {
		List<Integer> comps = new ArrayList<Integer>();
		FormulaClientJd client = new FormulaClientJd(this, zltjjgDao, comps, d, yjType, zts);
		FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>> fs = new FormulaServer<Pair<ZltjjgEntity, ZltjjgEntity>>(client);
		for (XkAdwtjjgEntity entity : entities){
			Formula formula = new Formula(entity.getFormul());
			Company comp = null;
			if (entity.getDwid() != null){
				comp = companyManager.getVirtualCYOrg().getCompany(entity.getDwid());
				comps.add(comp.getId());
			}
			client.add(formula, entity, comp);
			fs.addFormul(formula);
		}
		
		fs.run();
		return client.getResult();
	}
	
	@Override
	public List<List<String>> getXkadwtjjg(Date d, YDJDType yjType,
			Company company,  List<Integer> zts) {
		return this.getXkadwtjjg(d, yjType, xkAdwtjjgDao.getByDw(company), zts);
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
	public List<WaveItem> getXkYdAdwtjjgWaveItems(Date d, Company company,  List<Integer> zts) {
		
		List<WaveItem> wis = new ArrayList<WaveItem>();
		EasyCalendar cal = new EasyCalendar();
		cal.setTime(d);
		cal.setMonth(1);
		for (int j = 0; j < 12; ++j){
			List<List<String>> result = this.getXkadwtjjg(cal.getDate(), YDJDType.YD, xkAdwtjjgDao.getByDw(company), zts);
			//result.size() - 1 因为最后一行是合计
			for (int i = 0; i < result.size() - 1; ++i){
				if (result.get(i).get(1).indexOf("35") < 0){
					//不包含35千伏以下产品
					WaveItem item = getWaveItem(wis, result.get(i).get(1));
					item.getData().add(toCtVal(result.get(i).get(4)));
				}
			}
			cal.addMonth(1);
		}
		return wis;
	}

	@Override
	public List<WaveItem> getWaveItems(Date d,  List<Integer> zts) {
		List<WaveItem> wis = new ArrayList<WaveItem>();
		EasyCalendar cal = new EasyCalendar();
		cal.setTime(d);
		cal.setMonth(1);
		for (int j = 0; j < 12; ++j){
			List<List<String>> result = this.getXkadwtjjg(cal.getDate(), YDJDType.YD, zts);
			//result.size() - 1 因为最后一行是合计
			for (int i = 0; i < result.size() - 1; ++i){
				if ("合计".equals(result.get(i).get(0).replaceAll("\\s", "")) && result.get(i).get(1).indexOf("35") < 0){
					//不包含35千伏以下产品
					WaveItem item = getWaveItem(wis, result.get(i).get(1));
					item.getData().add(toCtVal(result.get(i).get(4)));
				}
			}
			cal.addMonth(1);
		}
		return wis;
	}
}
