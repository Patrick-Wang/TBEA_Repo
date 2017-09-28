package com.tbea.ic.operation.service.cb;

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
import com.tbea.ic.operation.common.companys.CompanyType;
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.model.dao.cb.XLCBDao;
import com.tbea.ic.operation.model.dao.cb.XMXXDao;
import com.tbea.ic.operation.model.entity.CBXLTBDD;
import com.tbea.ic.operation.model.entity.CBXLWGDD;
import com.tbea.ic.operation.model.entity.XMXX;
import com.util.tools.DateUtil;

@Service
@Transactional("transactionManager")
public class XLCBServiceImpl implements XLCBService{

	
	@Autowired
	XLCBDao xlDao;
	
	@Autowired
	private XMXXDao xmxxDao;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	CompanyManager companyManager;
	
	private static Map<CompanyType, Integer> gsMap = new HashMap<CompanyType, Integer>();
//	private static Map<Integer, String> xlzxjdMap = new HashMap<Integer, String>();
	static {
		gsMap.put(CompanyType.LLGS, 0);
		gsMap.put(CompanyType.XLC, 1);
		gsMap.put(CompanyType.DLGS, 2);
		
//		xlzxjdMap.put(201,"已采购");
//		xlzxjdMap.put(202,"已排产");
//		xlzxjdMap.put(203,"已生产");
//		xlzxjdMap.put(204,"完工");

	}
	
	private Double valueOf(String val) {
		return Util.toDouble(val);
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
		Organization org = companyManager.getBMOrganization();
		List<String> ret = new ArrayList<String>();
		ret.add(org.getCompanyById(xltbcb.getQybh()).getName());
		//ret.add(null != xmxx ? org.getCompany(Integer.valueOf(xmxx.getDdszdw())).getName() : null);//订单所在单位及项目公司//订单所在单位及项目公司
		ret.add(xltbcb.getTbbjsj() + "");//投标报价时间//投标报价时间
		ret.add(null != xmxx ? xmxx.getYhdwmc() : "");//用户单位名称
		ret.add(xltbcb.getCpdl() + "");//产品大类//产品大类
		ret.add(xltbcb.getXlsl() + "");//线缆数量//数量
		ret.add(xltbcb.getCz() + "");//产值//产值
		ret.add(xltbcb.getYjkbsj());//预计开标时间//预计开标时间
		ret.add(xltbcb.getYczbgl() + "");//销售部门预测的中标概率//销售部门预测的中标概率
		ret.add(xltbcb.getDjtyl() + "");//投标电解铜用量//投标电解铜用量
		ret.add(xltbcb.getDjtdj() + "");//投标电解铜单价//投标电解铜单价
		ret.add(xltbcb.getLyl() + "");//投标铝用量//投标铝用量
		ret.add(xltbcb.getLdj() + "");//投标铝单价//投标铝单价
		ret.add(xltbcb.getQtcbhj() + "");//其他成本合计//投标其他成本合计
		ret.add(tbcbzj + "");////投标成本总计
		ret.add(xltbcb.getYf() + "");//运费//运费
		ret.add(xltbcb.getCz() - tbcbzj + "");////投标毛利额
		ret.add(xltbcb.getCz() != 0.0 ? valueOf(tbmx[row][15]) / xltbcb.getCz() + "" : "0");////投标毛利率
		ret.toArray(tbmx[row]);
	}

	private void filltbqk(String[][] tb, int row, CBXLTBDD xltbcb,
			Double tbcbzj) {
		tb[row][0] = valueOf(tb[row][0]) + Util.valueOf(xltbcb.getCz()) + "";
		tb[row][1] = valueOf(tb[row][1]) + Util.valueOf(xltbcb.getCz()) - tbcbzj + "";
		tb[row][2] = valueOf(tb[row][0]) != 0.0 ? valueOf(tb[row][1]) / valueOf(tb[row][0]) + "" : "";
		tb[row][3] = valueOf(tb[row][3]) + Util.valueOf(xltbcb.getDjtdj()) * Util.valueOf(xltbcb.getDjtyl()) + "";

		tb[row][4] = valueOf(tb[row][4]) + Util.valueOf(xltbcb.getDjtyl()) + "";
		tb[row][5] = valueOf(tb[row][5]) + Util.valueOf(xltbcb.getLdj()) * Util.valueOf(xltbcb.getLyl()) + "";
		tb[row][6] = valueOf(tb[row][6]) + Util.valueOf(xltbcb.getLyl()) + "";
	}
	


	@Override
	public List<String[][]> getTbmx(Date date, Company comp) {
		List<CBXLTBDD> xlcbtbdds = xlDao.getTbdd(date);
		List<String[][]> rets = new ArrayList<String[][]>();
		String[][] tbmx = new String[xlcbtbdds.size()][17];
		String[][] jttb = new String[4][7];
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String[][] gstb = new String[cal.get(Calendar.MONTH) + 2][7];
		List<String[]> tbmxTmp = new ArrayList<String[]>();
		XMXX xmxx;
		CBXLTBDD xltbcb;
		Organization org = companyManager.getBMOrganization();
	

		Calendar firstMonth = Calendar.getInstance();
		firstMonth.set(cal.get(Calendar.YEAR), 1, 1);

				
		Calendar tmpCal = Calendar.getInstance();
		Map<String, XMXX> bhXXMap = new HashMap<String, XMXX>();
		String xmbh;
		for (int i = 0; i < xlcbtbdds.size(); ++i) {
			xltbcb = xlcbtbdds.get(i);
			xmbh = xltbcb.getXmbh();
			xmxx = null;
			if (null != xmbh && !xmbh.isEmpty()){
				xmxx = bhXXMap.get(xmbh);
				if (null == xmxx){
					xmxx = xmxxDao.getXmxxByBh(xltbcb.getXmbh());
					if (null != xmxx){
						bhXXMap.put(xmbh, xmxx);
					}
				}
			}
			

			Double zccb = Util.valueOf(xltbcb.getLyl()) * Util.valueOf(xltbcb.getLdj())
					+ Util.valueOf(xltbcb.getDjtyl()) * Util.valueOf(xltbcb.getDjtdj());// 投标五大主材成本
			Double tbcbzj = (zccb - Util.valueOf(xltbcb.getQtcbhj())) / 1.17;
			Company compTmp = org.getCompanyById(xltbcb.getQybh());

			if (null != xltbcb.getTbbjsj()) {
				tmpCal.setTime(xltbcb.getTbbjsj());
				if (tmpCal.get(Calendar.MONTH) == cal.get(Calendar.MONTH)) {
					if (comp.getType() == compTmp.getType()) {

						fillTbmx(tbmx, i, xmxx, xltbcb, zccb, tbcbzj);
						tbmxTmp.add(tbmx[i]);

					} else if (xmxx != null && xmxx.getDdszdw() != null
							&& !xmxx.getDdszdw().isEmpty()) {
						compTmp = org.getCompanyById(Integer.valueOf(xmxx
								.getDdszdw()));
						if (null != compTmp
								&& compTmp.getType() == comp.getType()) {
							fillTbmx(tbmx, i, xmxx, xltbcb, zccb, tbcbzj);
							tbmxTmp.add(tbmx[i]);
						}
					}
				}
			}
			
			if (compTmp != null) {

				if (compTmp.getParentCompany() != null) {
					compTmp = compTmp.getParentCompany();
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
		tbmx = new String[tbmxTmp.size()][17];
		tbmxTmp.toArray(tbmx);
		rets.add(tbmx);
		rets.add(jttb);
		rets.add(gstb);
		return rets;
	}
	
	private void fillWgmx(String[][] wg, int row, CBXLWGDD wgdd,
			Double zccb, Double sjzcb, Company comp) {
			List<String> ret = new ArrayList<String>();
			ret.add(comp.getName());
			//wg[row][0] = CompanyType.XL.getValue();// 工作号
			//ret.add(CompanyType.XL.getValue();//订单所在单位及项目公司
			ret.add(wgdd.getWgsj());//完工时间//完工时间
			ret.add(null);//wg[row][2] = //投标报价时间
			ret.add(null);//wg[row][3] = //合同中标时间
			ret.add(null);//wg[row][4] = //合同号
			ret.add(null);//wg[row][5] = //数量
			ret.add(wg[row][6] = wgdd.getDwmc());
			ret.add(null);//wg[row][7] = //产品大类
			ret.add(wgdd.getCz() + "");//产值//产值
			ret.add(wgdd.getDjtyl() + "");//实际电解铜用量//实际铜用量
			ret.add(wgdd.getDjtdj() + "");//实际电解铜单价//实际铜单价
			ret.add(wgdd.getTjgf() + "");//铜加工费//实际铜加工费
			ret.add(wgdd.getLyl() + "");//实际铝用量//实际铝用量
			ret.add(wgdd.getSjlvdj() + "");//实际铝单价//实际铝单价
			ret.add(zccb + "");//主材成本
			ret.add(wgdd.getQtcbhj() + "");//其他成本合计//实际其他材料成本合计
			ret.add(zccb + wgdd.getQtcbhj() + "");//材料成本合计
			ret.add(0.0 + "");//人工制造费用
			ret.add(sjzcb + "");//实际总成本
			ret.add(wgdd.getYf() + "");//运费//运费
			ret.add((Util.valueOf(wgdd.getCz()) - sjzcb) + "");//实际毛利额
			ret.add(Util.valueOf(wgdd.getCz()) != 0.0 ? (Util.valueOf(wgdd.getCz()) - sjzcb) / Util.valueOf(wgdd.getCz()) + "" : "");//实际毛利率
			ret.toArray(wg[row]);
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
	public List<String[][]> getWgmx(Date date, Company comp) {
		List<CBXLWGDD> xlcbwgdds = xlDao.getWgdd(date);
		List<String[][]> rets = new ArrayList<String[][]>();
		String[][] wgmx = new String[xlcbwgdds.size()][23];
		String[][] jtwg = new String[4 * 3][7];
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String[][] gswg = new String[(cal.get(Calendar.MONTH) + 1) * 3 * 3 + 3][7];
		String[][] dydjwg = new String[11 * 3][7];
	
		CBXLWGDD xlcbwgdd;

		Organization org = companyManager.getBMOrganization();
		List<String[]> wgmxTmp = new ArrayList<String[]>();
		Calendar tmpCal = Calendar.getInstance();
		Company compTmp;
		for (int i = 0; i < xlcbwgdds.size(); ++i) {
			xlcbwgdd = xlcbwgdds.get(i);
			if (xlcbwgdd.getWgsj() != null) {
				try {
					tmpCal.setTime(DateUtil.toDate(xlcbwgdd.getWgsj()));
					Double wg_zccb = xlcbwgdd.getLyl() * xlcbwgdd.getSjlvdj()
							+ xlcbwgdd.getDjtyl() * xlcbwgdd.getDjtdj();// 投标五大主材成本
					Double sjzcb = wg_zccb + xlcbwgdd.getQtcbhj() + 0.0;
					compTmp = org.getCompanyById(xlcbwgdd.getQybh());
					if (tmpCal.get(Calendar.MONTH) == cal.get(Calendar.MONTH) &&
							comp.getType() == compTmp.getType()) {
						fillWgmx(wgmx, i, xlcbwgdd, wg_zccb, sjzcb, comp);
						wgmxTmp.add(wgmx[i]);
					}

					
					if (compTmp != null) {
						if (compTmp.getParentCompany() != null) {
							compTmp = compTmp.getParentCompany();
						}
						
						if (tmpCal.get(Calendar.MONTH) == cal
								.get(Calendar.MONTH)) {

							fillwgqk(jtwg, gsMap.get(comp.getType()) * 3 + 2,
									xlcbwgdd, sjzcb);
							fillwgqk(jtwg, jtwg.length - 1, xlcbwgdd, sjzcb);
						}

						if (tmpCal.get(Calendar.MONTH) <= cal
								.get(Calendar.MONTH)) {
							fillwgqk(
									gswg,
									(gsMap.get(comp.getType())
											* (cal.get(Calendar.MONTH) + 1) + tmpCal
											.get(Calendar.MONTH)) * 3 + 2,
									xlcbwgdd, sjzcb);
							fillwgqk(gswg, gswg.length - 1, xlcbwgdd, sjzcb);
						}

					}
				} catch (Exception e) {
					System.out.println(xlcbwgdd.getWgsj());
					e.printStackTrace();
				}
			}
		}
		computeDj(jtwg);
		computeDj(gswg);
		wgmx = new String[wgmxTmp.size()][23];
		wgmxTmp.toArray(wgmx);
		
		rets.add(wgmx);
		rets.add(jtwg);
		rets.add(gswg);
		rets.add(dydjwg);
		return rets;
	}

	@Override
	public boolean IsTbCompanyExist(Company comp) {
		// TODO Auto-generated method stub
		return xlDao.containsTbCompany(comp);
	}

//	@Override
//	public List<String[]> getTbmx(Date date, Company comp) {
//		List<CBXLTBDD> xlcbtbdds = xlDao.getTbdd(date);
//
//		String[][] tbrow;
//		List<String[]> tbmx = new ArrayList<String[]>();	
//		XMXX xmxx;
//		CBXLTBDD xltbcb;
//
//		for (int i = 0; i < xlcbtbdds.size(); ++i) {
//			xltbcb = xlcbtbdds.get(i);
//			xmxx = xmxxDao.getXmxxByBh(xltbcb.getXmbh());
//
//			Double zccb = Util.valueOf(xltbcb.getLyl()) * Util.valueOf(xltbcb.getLdj())
//					+ Util.valueOf(xltbcb.getDjtyl()) * Util.valueOf(xltbcb.getDjtdj());// 投标五大主材成本
//			Double tbcbzj = (zccb - Util.valueOf(xltbcb.getQtcbhj())) / 1.17;
//			
//			if (comp.getId() == Integer.valueOf(xltbcb.getQybh())){
//				tbrow = new String[1][17];
//				fillTbmx(tbrow, 0, xmxx, xltbcb, zccb, tbcbzj);
//				tbmx.add(tbrow[0]);
//			}
//		}
//
//		return tbmx;
//	}

	@Override
	public Date getLatestWgDate() {
		CBXLWGDD wgdd = xlDao.getLatestWgdd();
		if (null != wgdd){
			return DateUtil.toDate(wgdd.getWgsj());
		}
		return null;
	}

	@Override
	public List<Integer> getWgCompany() {
		// TODO Auto-generated method stub
		return xlDao.getWgCompany();
	}

	@Override
	public List<Integer> getTbCompany() {
		// TODO Auto-generated method stub
		return xlDao.getTbCompany();
	}

	@Override
	public Date getLatestTbDate() {
		CBXLTBDD tbdd = xlDao.getLatestTbdd();
		if (null != tbdd){
			return tbdd.getTbbjsj();
		}
		return null;
	}


}
