package com.tbea.ic.operation.service.cb;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.common.companys.CompanyManager;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.model.dao.cb.XLCBDao;
import com.tbea.ic.operation.model.dao.cb.XMXXDao;
import com.tbea.ic.operation.model.entity.CBBYQTBDD;
import com.tbea.ic.operation.model.entity.CBBYQWGDD;
import com.tbea.ic.operation.model.entity.CBBYQZXDD;
import com.tbea.ic.operation.model.entity.CBXLTBDD;
import com.tbea.ic.operation.model.entity.CBXLWGDD;
import com.tbea.ic.operation.model.entity.XMXX;

@Service
@Transactional("transactionManager")
public class XLCBServiceImpl implements XLCBService{

	
	@Autowired
	XLCBDao xlDao;
	
	@Autowired
	private XMXXDao xmxxDao;
	
	private static Map<CompanyType, Integer> gsMap = new HashMap<CompanyType, Integer>();
//	private static Map<Integer, String> xlzxjdMap = new HashMap<Integer, String>();
	static {
		gsMap.put(CompanyType.LL, 0);
		gsMap.put(CompanyType.XL, 1);
		gsMap.put(CompanyType.DL, 2);
		
//		xlzxjdMap.put(201,"已采购");
//		xlzxjdMap.put(202,"已排产");
//		xlzxjdMap.put(203,"已生产");
//		xlzxjdMap.put(204,"完工");

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
		Organization org = CompanyManager.getBMOrganization();
		tbmx[row][0] = org.getCompany(Integer.valueOf(xmxx.getDdszdw())).getName();//订单所在单位及项目公司//订单所在单位及项目公司
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
		tb[row][3] = valueOf(tb[row][3]) + valueOf(xltbcb.getDjtdj()) * valueOf(xltbcb.getDjtyl()) + "";

		tb[row][4] = valueOf(tb[row][4]) + valueOf(xltbcb.getDjtyl()) + "";
		tb[row][5] = valueOf(tb[row][5]) + valueOf(xltbcb.getLdj()) * valueOf(xltbcb.getLyl()) + "";
		tb[row][6] = valueOf(tb[row][6]) + valueOf(xltbcb.getLyl()) + "";
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
	
	private void fillWgmx(String[][] wg, int row, CBXLWGDD wgdd,
			Double zccb, Double sjzcb) {
		//wg[row][0] = CompanyType.XL.getValue();// 工作号
				wg[row][0] = CompanyType.XL.getValue();//订单所在单位及项目公司
				wg[row][1] = wgdd.getWgsj();//完工时间//完工时间
				//wg[row][2] = //投标报价时间
				//wg[row][3] = //合同中标时间
				//wg[row][4] = //合同号
				//wg[row][5] = //数量
				wg[row][6] = wgdd.getDwmc();
				//wg[row][7] = //产品大类
				wg[row][8] = wgdd.getCz() + "";//产值//产值
				wg[row][9] = wgdd.getDjtyl() + "";//实际电解铜用量//实际铜用量
				wg[row][10] = wgdd.getDjtdj() + "";//实际电解铜单价//实际铜单价
				wg[row][11] = wgdd.getTjgf() + "";//铜加工费//实际铜加工费
				wg[row][12] = wgdd.getLyl() + "";//实际铝用量//实际铝用量
				wg[row][13] = wgdd.getSjlvdj() + "";//实际铝单价//实际铝单价
				wg[row][14] = zccb + "";//主材成本
				wg[row][15] = wgdd.getQtcbhj() + "";//其他成本合计//实际其他材料成本合计
				wg[row][16] = zccb + wgdd.getQtcbhj() + "";//材料成本合计
				wg[row][17] = 0.0 + "";//人工制造费用
				wg[row][18] = sjzcb + "";//实际总成本
				wg[row][19] = wgdd.getYf() + "";//运费//运费
				wg[row][20] = (valueOf(wgdd.getCz()) - sjzcb) + "";//实际毛利额
				wg[row][21] = (valueOf(wgdd.getCz()) - sjzcb) / valueOf(wgdd.getCz()) + "";//实际毛利率
	}

	private void fillwgqk(String[][] jtzx, Integer row, CBXLWGDD xlcbwgdd,
			Double sczcb) {
		jtzx[row][0] = valueOf(jtzx[row][0]) + xlcbwgdd.getCz() + "";
		jtzx[row][1] = valueOf(jtzx[row][0]) + xlcbwgdd.getCz() - sczcb + "";
		jtzx[row][2] = valueOf(jtzx[row][1]) / valueOf(jtzx[row][0]) + "";
		
		jtzx[row][3] = valueOf(jtzx[row][3]) + xlcbwgdd.getDjtyl() * xlcbwgdd.getDjtdj()+ "";
		jtzx[row][4] = valueOf(jtzx[row][4]) + xlcbwgdd.getDjtyl() + "";
		
		jtzx[row][5] = valueOf(jtzx[row][5]) + xlcbwgdd.getSjlvdj()
				* xlcbwgdd.getLyl() + "";
		jtzx[row][6] = valueOf(jtzx[row][6]) + xlcbwgdd.getLyl() + "";
	}

	@Override
	public List<String[][]> getWgmx(Date date) {
		List<CBXLWGDD> xlcbwgdds = xlDao.getWgdd();
		List<String[][]> rets = new ArrayList<String[][]>();
		String[][] wgmx = new String[xlcbwgdds.size()][23];
		String[][] jtwg = new String[4 * 3][7];
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String[][] gswg = new String[(cal.get(Calendar.MONTH) + 1) * 3 * 3 + 3][7];
		String[][] dydjwg = new String[11 * 3][7];
		rets.add(wgmx);
		rets.add(jtwg);
		rets.add(gswg);
		rets.add(dydjwg);
		CBXLWGDD xlcbwgdd;

		Organization org = CompanyManager.getBMOrganization();
		Company comp;

		Calendar firstMonth = Calendar.getInstance();
		firstMonth.set(cal.get(Calendar.YEAR), 1, 1);

		Calendar tmpCal = Calendar.getInstance();

		for (int i = 0; i < xlcbwgdds.size(); ++i) {
			xlcbwgdd = xlcbwgdds.get(i);

			Double wg_zccb = xlcbwgdd.getLyl() * xlcbwgdd.getSjlvdj()
					+ xlcbwgdd.getDjtyl() * xlcbwgdd.getDjtdj();// 投标五大主材成本
			Double sjzcb = wg_zccb + xlcbwgdd.getQtcbhj() + 0.0;
			fillWgmx(wgmx, i, xlcbwgdd, wg_zccb, sjzcb);

			if (xlcbwgdd.getWgsj() != null) {
				tmpCal.setTime(Date.valueOf(xlcbwgdd.getWgsj() + "-1"));
				comp = org.getCompany(5);
				if (tmpCal.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
						&& comp != null) {

					if (tmpCal.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) {

						if (comp.getParentCompany() != null) {
							comp = comp.getParentCompany();
						}

						fillwgqk(jtwg, gsMap.get(comp.getType()) * 3 + 2,
								xlcbwgdd, sjzcb);
						fillwgqk(jtwg, jtwg.length - 1, xlcbwgdd, sjzcb);
					}

					if (tmpCal.get(Calendar.MONTH) <= cal.get(Calendar.MONTH)) {
						fillwgqk(
								gswg,
								(gsMap.get(comp.getType())
										* (cal.get(Calendar.MONTH) + 1) + tmpCal
										.get(Calendar.MONTH)) * 3 + 2,
								xlcbwgdd, sjzcb);
						fillwgqk(gswg, gswg.length - 1, xlcbwgdd, sjzcb);
					}

				}
			}
		}
		computeDj(jtwg);
		computeDj(gswg);
		return rets;
	}

	@Override
	public boolean IsTbCompanyExist(Company comp) {
		// TODO Auto-generated method stub
		return xlDao.containsTbCompany(comp);
	}

	@Override
	public List<String[]> getTbmx(Date date, Company comp) {
		List<CBXLTBDD> xlcbtbdds = xlDao.getTbdd();

		String[][] tbrow;
		List<String[]> tbmx = new ArrayList<String[]>();	
		XMXX xmxx;
		CBXLTBDD xltbcb;

		for (int i = 0; i < xlcbtbdds.size(); ++i) {
			xltbcb = xlcbtbdds.get(i);
			xmxx = xmxxDao.getXmxxByBh(xltbcb.getXmbh());

			Double zccb = valueOf(xltbcb.getLyl()) * valueOf(xltbcb.getLdj())
					+ valueOf(xltbcb.getDjtyl()) * valueOf(xltbcb.getDjtdj());// 投标五大主材成本
			Double tbcbzj = (zccb - valueOf(xltbcb.getQtcbhj())) / 1.17;
			if (comp.getId() == Integer.valueOf(xmxx.getDdszdw())){
				tbrow = new String[1][17];
				fillTbmx(tbrow, 0, xmxx, xltbcb, zccb, tbcbzj);
				tbmx.add(tbrow[0]);
			}
		}

	
		return tbmx;
	}

	@Override
	public Date getLatestWgDate() {
		CBXLWGDD wgdd = xlDao.getLatestWgdd();
		if (null != wgdd){
			return Date.valueOf(wgdd.getWgsj() + "-1");
		}
		return null;
	}


}
