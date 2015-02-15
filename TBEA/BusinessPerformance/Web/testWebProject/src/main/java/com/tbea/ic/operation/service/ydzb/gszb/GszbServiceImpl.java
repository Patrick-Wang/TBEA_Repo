package com.tbea.ic.operation.service.ydzb.gszb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.model.dao.jygk.qnjh.NDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.sjzb.SJZBDao;
import com.tbea.ic.operation.model.dao.jygk.ydjhzb.YDJHZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj20zb.YJ20ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yj28zb.YJ28ZBDao;
import com.tbea.ic.operation.model.dao.jygk.yjzbzt.YDZBZTDao;
import com.tbea.ic.operation.model.dao.jygk.zbxx.ZBXXDao;
import com.tbea.ic.operation.model.entity.jygk.ZBXX;

@Service
@Transactional("transactionManager")
public class GszbServiceImpl implements GszbService {

	@Autowired
	NDJHZBDao ndjhzbDao;

	@Autowired
	YDJHZBDao ydjhzbDao;

	@Autowired
	YDZBZTDao ydzbztDao;

	@Autowired
	SJZBDao sjzbDao;

	@Autowired
	YJ20ZBDao yj20zbDao;

	@Autowired
	YJ28ZBDao yj28zbDao;

	@Autowired
	ZBXXDao zbxxDao;

	@Resource(type = com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	private static List<Integer> gsztzbs = new ArrayList<Integer>();
	static {
		gsztzbs.add(GSZB.LRZE.getValue());
		gsztzbs.add(GSZB.XSSR.getValue());
		gsztzbs.add(GSZB.JYXJXJL.getValue());
		gsztzbs.add(GSZB.YSZK.getValue());
		gsztzbs.add(GSZB.QZYQK.getValue());
		gsztzbs.add(GSZB.BL.getValue());
		gsztzbs.add(GSZB.CH.getValue());
		gsztzbs.add(GSZB.QZJYWY.getValue());
		gsztzbs.add(GSZB.HTQYE.getValue());
		gsztzbs.add(GSZB.ZJHL.getValue());
		gsztzbs.add(GSZB.BHSCZ.getValue());
		gsztzbs.add(GSZB.RS.getValue());
		gsztzbs.add(GSZB.RJLR.getValue());
		gsztzbs.add(GSZB.RJSR.getValue());
		gsztzbs.add(GSZB.SXFY.getValue());
		gsztzbs.add(GSZB.SXFYL.getValue());
		gsztzbs.add(GSZB.JZCSYL.getValue());
	}

	private static Map<Integer, ZBXX> zbxxMap = new HashMap<Integer, ZBXX>();

	private ZBXX getZbxx(Integer zbId) {
		if (zbxxMap.isEmpty()) {
			List<ZBXX> zbxxs = zbxxDao.getZbs(gsztzbs);
			for (ZBXX zbxx : zbxxs) {
				zbxxMap.put(zbxx.getId(), zbxx);
			}
		}
		return zbxxMap.get(zbId);
	}

	private List<Company> filterCompany(List<Company> companies) {
		List<Company> retComps = new ArrayList<Company>();
		for (Company comp : companies) {
			if (!comp.getSubCompanys().isEmpty()) {
				retComps.addAll(comp.getSubCompanys());
			}
		}
		return retComps;
	}

	private List<String[]> makeResult(List<Double[]> gszbs){

		List<String[]> result = new ArrayList<String[]>();
		for (int i = 0, len = gsztzbs.size(); i < len; ++i) {
			String[] zbRow = new String[16];
			Double[] gszbRow = gszbs.get(i);
			zbRow[0] = getZbxx(gsztzbs.get(i)).getName();
			for (int j = zbRow.length - 1; j > 0; --j) {
				if (null != gszbRow[j - 1]) {
					zbRow[j] = gszbRow[j - 1] + "";
				}
			}
			result.add(zbRow);
		}
		return result;
	}
	
	@Override
	public List<String[]> getGsztzb(Date date) {
		Organization org = companyManager.getBMDBOrganization();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -(cal.get(Calendar.MONTH) + 1) % 3 + 1);
		Date seasonStart = Util.toDate(cal);
		
		cal.setTime(seasonStart);
		cal.add(Calendar.YEAR, -1);
		Date qntqSeason = Util.toDate(cal);
		
		cal.setTime(date);
		cal.add(Calendar.YEAR, -1);
		Date qntq = Util.toDate(cal);
		
		cal.setTime(date);
		cal.set(Calendar.MONTH, 0);
		Date firstMonth = Util.toDate(cal);
		
		cal.setTime(qntq);
		cal.set(Calendar.MONTH, 0);
		Date qnfirstMonth = Util.toDate(cal);

		GszbPipe pipe = new GszbPipe(gsztzbs, 15, filterCompany(org.getCompany(
				CompanyType.GFGS).getSubCompanys()), date);
		
		SJZBAccumulator accumulator = new SJZBAccumulator(sjzbDao, yj20zbDao,
				yj28zbDao, ydzbztDao);
		
		pipe.add(new QnjhPipeFilter(ndjhzbDao, 0))// 全年计划
			.add(new YdjhWithoutChYsRsPipeFilter(ydjhzbDao, 1, date, date))// 当月计划
			.add(new YdsjSbdWithChYsPipeFilter(1, accumulator, companyManager, firstMonth, date))
			.add(new YdsjNonSbdWithChYsPipeFilter(ydjhzbDao, 1, accumulator, companyManager, firstMonth, date))
			.add(new YdjhRsPipeFilter(ydjhzbDao, 1))
			
			.add(new YdsjWithoutRsPipeFilter(2, accumulator, date, date))// 当月实际
			.add(new YdsjRsPipeFilter(2, accumulator, date))
			
			.add(new JhwclPipeFilter(3, 1, 2))// 计划完成率
			
			.add(new YdsjWithoutRsPipeFilter(4, accumulator, qntq, qntq))// 去年同期
			.add(new YdsjRsPipeFilter(2, accumulator, qntq))
			
			.add(new TbzzPipeFilter(5, 4, 2))// 同比增幅
			
			.add(new YdjhWithoutChYsRsPipeFilter(ydjhzbDao, 6, seasonStart, date))// 季度计划
			.add(new YdzbCopyWithChYsPipeFilter(6, 1))
			.add(new YdjhRsCopyPipeFilter(6, 1))
			
			.add(new YdsjWithoutChYsRsPipeFilter(7, accumulator, seasonStart, date))// 季度累计
			.add(new YdzbCopyWithChYsPipeFilter(7, 2))
			.add(new YdzbRsCopyPipeFilter(7, 1))
			
			.add(new JhwclPipeFilter(8, 6, 7))// 季度计划完成率
			
			.add(new YdsjWithoutChYsRsPipeFilter(9, accumulator, qntqSeason, qntq))// 去年同期
			.add(new YdsjWithChYsPipeFilter(accumulator, 9, qntq, qntq))
			.add(new YdsjRsPipeFilter(9, accumulator, qntq))
			
			.add(new TbzzPipeFilter(10, 9, 7))// 同比增幅
			
			.add(new YdsjWithoutChYsRsPipeFilter(11, accumulator, firstMonth, date))// 年度累计
			.add(new YdzbCopyWithChYsPipeFilter(11, 2))
			.add(new YdzbRsCopyPipeFilter(11, 2))
			
			.add(new JhwclPipeFilter(12, 0, 11))// 累计计划完成率
			
			.add(new YdsjWithoutChYsRsPipeFilter(13, accumulator, qnfirstMonth, qntq))// 去年同期
			.add(new YdzbCopyWithChYsPipeFilter(13, 9))
			.add(new YdzbRsCopyPipeFilter(13, 9))
			
			.add(new TbzzPipeFilter(14, 13, 11));// 同比增幅


		return makeResult(pipe.getGszb());
	}

}
