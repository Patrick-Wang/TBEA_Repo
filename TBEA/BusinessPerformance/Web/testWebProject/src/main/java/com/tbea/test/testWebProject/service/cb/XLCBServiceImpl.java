package com.tbea.test.testWebProject.service.cb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.common.Company;
import com.tbea.test.testWebProject.common.CompanyManager;
import com.tbea.test.testWebProject.common.Organization;
import com.tbea.test.testWebProject.common.CompanyManager.CompanyType;
import com.tbea.test.testWebProject.model.dao.cb.XLCBDao;
import com.tbea.test.testWebProject.model.dao.cb.XMXXDao;
import com.tbea.test.testWebProject.model.entity.CBBYQTBDD;
import com.tbea.test.testWebProject.model.entity.CBXLTBDD;
import com.tbea.test.testWebProject.model.entity.XMXX;

@Service
@Transactional("transactionManager")
public class XLCBServiceImpl implements XLCBService{

	
	@Autowired
	XLCBDao xlDao;
	
	@Autowired
	private XMXXDao xmxxDao;
	
	private static Map<CompanyType, Integer> gsMap = new HashMap<CompanyType, Integer>();
	static {
		gsMap.put(CompanyType.LL, 0);
		gsMap.put(CompanyType.XL, 1);
		gsMap.put(CompanyType.DL, 2);
	}
	
	private Double valueOf(String val) {
		if (val != null) {
			return Double.valueOf(val);
		}
		return 0.0;
	}

	private void computeDj(String[][] tb) {

		for (int i = 0; i < tb.length; ++i) {
			tb[i][3] = (valueOf(tb[i][4]) == 0 ? 0.0 : valueOf(tb[i][3])
					/ valueOf(tb[i][4]))
					+ "";
			tb[i][5] = (valueOf(tb[i][6]) == 0 ? 0.0 : valueOf(tb[i][5])
					/ valueOf(tb[i][6]))
					+ "";
		}
	}

	private void fillTbmx(String[][] tbmx, int row, XMXX xmxx,
			CBXLTBDD xltbcb, Double zccb, Double tbcbzj) {
		tbmx[row][0] = xmxx.getDdszdw();//订单所在单位及项目公司//订单所在单位及项目公司
		tbmx[row][1] = xltbcb.getTbbjsj() + "";//投标报价时间//投标报价时间
		tbmx[row][2] = xmxx.getYhdwmc();//用户单位名称
		tbmx[row][3] = xltbcb.getCpdl() + "";//产品大类//产品大类
		tbmx[row][4] = xltbcb.getXlsl() + "";//线缆数量//数量
		tbmx[row][5] = xltbcb.getCz() + "";//产值//产值
		tbmx[row][6] = xltbcb.getYjkbsj();//预计开标时间//预计开标时间
		tbmx[row][7] = xltbcb.getYczbgl() + "";//销售部门预测的中标概率//销售部门预测的中标概率
		tbmx[row][8] = xltbcb.getDjtyl() + "";//投标电解铜用量//投标电解铜用量
		tbmx[row][9] = xltbcb.getDjtdj() + "";//投标电解铜单价//投标电解铜单价
		tbmx[row][10] = xltbcb.getLyl() + "";//投标铝用量//投标铝用量
		tbmx[row][11] = xltbcb.getLdj() + "";//投标铝单价//投标铝单价
		tbmx[row][12] = xltbcb.getQtcbhj() + "";//其他成本合计//投标其他成本合计
		tbmx[row][13] = tbcbzj + "";////投标成本总计
		tbmx[row][14] = xltbcb.getYf() + "";//运费//运费
		tbmx[row][15] = xltbcb.getCz() - tbcbzj + "";////投标毛利额
		tbmx[row][16] = valueOf(tbmx[row][15]) / xltbcb.getCz() + "";////投标毛利率
	}

	private void filltbqk(String[][] tb, int row, CBXLTBDD xltbcb,
			Double tbcbzj) {
		tb[row][0] = valueOf(tb[row][0]) + valueOf(xltbcb.getCz()) + "";
		tb[row][1] = valueOf(tb[row][1]) + valueOf(xltbcb.getCz()) - tbcbzj + "";
		tb[row][2] = valueOf(tb[row][1]) / valueOf(tb[row][0]) + "";
		tb[row][3] = valueOf(tb[row][3]) + valueOf(xltbcb.getDjtdj()) + "";
		tb[row][4] = valueOf(tb[row][4]) + valueOf(xltbcb.getDjtdj()) * valueOf(xltbcb.getDjtyl()) + "";
		tb[row][5] = valueOf(tb[row][5]) + valueOf(xltbcb.getLdj()) + "";
		tb[row][6] = valueOf(tb[row][6]) + valueOf(xltbcb.getLdj()) * valueOf(xltbcb.getLyl()) + "";
	}
	
	private double valueOf(Double d){
		if (null == d){
			return 0.0;
		}
		return d.doubleValue();
	}

	@Override
	public List<String[][]> getTbmx(Date date) {
		List<CBXLTBDD> xlcbtbdds = xlDao.getTbdd();
		List<String[][]> rets = new ArrayList<String[][]>();
		String[][] tbmx = new String[xlcbtbdds.size()][17];
		String[][] jttb = new String[4][7];
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String[][] gstb = new String[cal.get(Calendar.MONTH) + 2][7];
		rets.add(tbmx);
		rets.add(jttb);
		rets.add(gstb);
		XMXX xmxx;
		CBXLTBDD xltbcb;
		Organization org = CompanyManager.getBMOrganization();
		Company comp;

		Calendar firstMonth = Calendar.getInstance();
		firstMonth.set(cal.get(Calendar.YEAR), 1, 1);

		Calendar tmpCal = Calendar.getInstance();

		for (int i = 0; i < xlcbtbdds.size(); ++i) {
			xltbcb = xlcbtbdds.get(i);
			xmxx = xmxxDao.getXmxxByBh(xltbcb.getXmbh());

			Double zccb = valueOf(xltbcb.getLyl()) * valueOf(xltbcb.getLdj())
					+ valueOf(xltbcb.getDjtyl()) * valueOf(xltbcb.getDjtdj());// 投标五大主材成本
			Double tbcbzj = (zccb - valueOf(xltbcb.getQtcbhj())) / 1.17;
			fillTbmx(tbmx, i, xmxx, xltbcb, zccb, tbcbzj);

			comp = org.getCompany(Integer.valueOf(xmxx.getDdszdw()));
			if (comp != null) {

				if (comp.getParentCompany() != null) {
					comp = comp.getParentCompany();
				}

				filltbqk(jttb, gsMap.get(comp.getType()), xltbcb, tbcbzj);
				filltbqk(jttb, jttb.length - 1, xltbcb, tbcbzj);
				if (null != xltbcb.getTbbjsj()
						&& xltbcb.getTbbjsj().before(date)
						&& xltbcb.getTbbjsj().after(firstMonth.getTime())) {
					tmpCal.setTime(xltbcb.getTbbjsj());
					
					filltbqk(gstb, tmpCal.get(Calendar.MONTH), xltbcb, tbcbzj);
					filltbqk(gstb, gstb.length - 1, xltbcb, tbcbzj);
				}
			}
		}

		computeDj(gstb);
		computeDj(jttb);

		return rets;
	}

}
