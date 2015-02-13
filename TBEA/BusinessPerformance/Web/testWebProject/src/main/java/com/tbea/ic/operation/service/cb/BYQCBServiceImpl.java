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
import com.tbea.ic.operation.common.companys.Organization;
import com.tbea.ic.operation.common.companys.CompanyManager.CompanyType;
import com.tbea.ic.operation.model.dao.cb.BYQCBDao;
import com.tbea.ic.operation.model.dao.cb.XMXXDao;
import com.tbea.ic.operation.model.entity.CBBYQTBDD;
import com.tbea.ic.operation.model.entity.CBBYQWGDD;
import com.tbea.ic.operation.model.entity.CBBYQZXDD;
import com.tbea.ic.operation.model.entity.XMXX;

@Service
@Transactional("transactionManager")
public class BYQCBServiceImpl implements BYQCBService {

	@Autowired
	private BYQCBDao byqcbDao;

	@Autowired
	private XMXXDao xmxxDao;

	
	CompanyManager companyManager;
	
	private Organization org = null;
	
	@Resource(type=com.tbea.ic.operation.common.companys.CompanyManager.class)
	public void setCompanyManager(CompanyManager companyManager) {
		this.companyManager = companyManager;
		org = companyManager.getPzghOrganization();
	}



	private static Map<CompanyType, Integer> gsMap = new HashMap<CompanyType, Integer>();
	static {
		gsMap.put(CompanyType.SBGS, 0);
		gsMap.put(CompanyType.HBGS, 1);
		gsMap.put(CompanyType.XBC, 2);
	}

	private static Map<String, Integer> dydjMap = new HashMap<String, Integer>();
	private static Map<Integer, String> byqzxjdMap = new HashMap<Integer, String>();

	static {
		dydjMap.put("110", 0);
		dydjMap.put("220", 1);
		dydjMap.put("330", 2);
		dydjMap.put("500", 3);
		
		byqzxjdMap.put(101,"未设计");
		byqzxjdMap.put(102,"已设计");
		byqzxjdMap.put(103,"已开工前评审");
		byqzxjdMap.put(104,"已采购");
		byqzxjdMap.put(105,"已排产");

	}

	
	
//	private Double valueOf(String val) {
//		if (val != null) {
//			return Double.valueOf(val);
//		}
//		return 0.0;
//	}

	private void computeDj(String[][] tb, boolean hasDjtf) {

		int delta = 0;
		if (!hasDjtf) {
			delta = -1;
		}
		for (int i = 0; i < tb.length; ++i) {
			tb[i][3] = Util.toDouble(tb[i][4]) != 0 ?  Util.toDouble(tb[i][3])
					/ Util.toDouble(tb[i][4]) + "" : "0";
			tb[i][5] = Util.toDouble(tb[i][6]) != 0 ?  Util.toDouble(tb[i][5])	/ Util.toDouble(tb[i][6]) + "": "0";
			tb[i][8 + delta] = 
					Util.toDouble(tb[i][9 + delta]) != 0 ?  
							Util.toDouble(tb[i][8 + delta]) / Util.toDouble(tb[i][9 + delta]) + "":
							"0";
			tb[i][10 + delta] = Util.toDouble(tb[i][11 + delta]) != 0 ?  
					Util.toDouble(tb[i][10 + delta]) / Util.toDouble(tb[i][11 + delta])	+ "": 
						"0";
			tb[i][12 + delta] = Util.toDouble(tb[i][13 + delta]) != 0 ?  Util.toDouble(tb[i][12 + delta]) / Util.toDouble(tb[i][13 + delta])
					+ "": "0";
		}
	}

	private void fillTbmx(String[][] tbmx, int row, XMXX xmxx,
			CBBYQTBDD byqtbcb, Double zccb, Double tbclzcb, Double tbzccb) {
		List<String> tbmxTmp = new ArrayList<String>();
		tbmxTmp.add(org.getCompany(byqtbcb.getQybh()).getName());
		tbmxTmp.add(xmxx != null ? org.getCompany(Integer.valueOf(xmxx.getDdszdw())).getName() : "");// 订单所在单位及项目公司//订单所在单位及项目公司
		tbmxTmp.add(byqtbcb.getTbbjsj() + "");// 投标报价时间//投标报价时间
		tbmxTmp.add(xmxx != null ? xmxx.getYhdwmc(): "");// 用户单位名称//用户单位名称
		tbmxTmp.add(xmxx != null ? xmxx.getXmmc(): "");// 项目名称//项目名称
		tbmxTmp.add(byqtbcb.getYjjhsj());// 预计交货时间//预计交货时间
		tbmxTmp.add(byqtbcb.getXh());// 型号//型号
		tbmxTmp.add(byqtbcb.getDy());// 电压//电压
		tbmxTmp.add(byqtbcb.getCl());// 产量//产量（万KVA）
		tbmxTmp.add(byqtbcb.getCz() + "");// 产值//产值
		tbmxTmp.add(byqtbcb.getYjkbsj());// 预计开标时间//预计开标时间
		tbmxTmp.add(byqtbcb.getYczbgl() + "");// 销售部门预测的中标概率//销售部门预测的中标概率
		tbmxTmp.add(byqtbcb.getGgph() + "");// 硅钢牌号//投标硅钢牌号
		tbmxTmp.add(byqtbcb.getGgyl() + "");// 硅钢用量（单台）//投标硅钢用量（单台）
		tbmxTmp.add(byqtbcb.getGgdj() + "");// 硅钢单价//投标硅钢单价
		tbmxTmp.add(byqtbcb.getDjtyl() + "");// 电解铜用量（单台）//投标电解铜用量（单台）
		tbmxTmp.add(byqtbcb.getDjtdj() + "");// 电解铜单价//投标电解铜单价
		tbmxTmp.add(byqtbcb.getByqyyl() + "");// 变压器油用量（单台）//投标变压器油用量（单台）
		tbmxTmp.add(byqtbcb.getByqydj() + "");// 变压器油单价//投标变压器油单价
		tbmxTmp.add(byqtbcb.getGcyl() + "");// 钢材用量（单台）//投标钢材用量（单台）
		tbmxTmp.add(byqtbcb.getGcdj() + "");// 钢材单价//投标钢材单价
		tbmxTmp.add(byqtbcb.getZbyl() + "");// 纸板用量（单台）//投标纸板用量（单台）
		tbmxTmp.add(byqtbcb.getZbdj() + "");// 纸板单价//投标纸板单价
		tbmxTmp.add(zccb + "");// 投标五大主材成本
		tbmxTmp.add(byqtbcb.getQtclcb() + "");// 投标其他材料成本//投标其他材料成本
		tbmxTmp.add(tbclzcb + "");// 投标材料成本总计
		tbmxTmp.add(byqtbcb.getRgjzzfy() + "");// 人工及制造费用//人工及制造费用
		tbmxTmp.add(tbzccb + "");// 投标制造成本
		tbmxTmp.add(byqtbcb.getYf() + "");// 运费//运费
		tbmxTmp.add(byqtbcb.getCz() - tbzccb + "");// 投标毛利（单台）
		tbmxTmp.add(byqtbcb.getCz() != 0 ? (byqtbcb.getCz() - tbzccb) / byqtbcb.getCz() + "" : "0"); // /投标毛利率
		tbmxTmp.toArray(tbmx[row]);
	}

	private void filltbqk(String[][] tb, int row, CBBYQTBDD byqtbcb,
			Double tbzccb, boolean hasDjtf) {

		if (null == byqtbcb){
			return ;
		}
		
		int delta = 0;
		if (!hasDjtf) {
			delta = -1;
		}
		tb[row][0] = Util.toDouble(tb[row][0]) + byqtbcb.getCz() + "";
		tb[row][1] = Util.toDouble(tb[row][1]) + byqtbcb.getCz() - tbzccb + "";
		tb[row][2] = Util.toDouble(tb[row][0]) != 0 ? Util.toDouble(tb[row][1]) / Util.toDouble(tb[row][0]) + "" : "0";
		
		tb[row][3] = Util.toDouble(tb[row][3]) + byqtbcb.getGgdj() * byqtbcb.getGgyl() + "";
		tb[row][4] = Util.toDouble(tb[row][4]) + byqtbcb.getGgyl() + "";
			
		tb[row][5] = Util.toDouble(tb[row][5]) + byqtbcb.getDjtdj()
				* byqtbcb.getDjtyl() + "";
		tb[row][6] = Util.toDouble(tb[row][6]) + byqtbcb.getDjtyl() + "";

		tb[row][7] = "0";// 电解铜费用
		
		tb[row][8 + delta] = Util.toDouble(tb[row][8 + delta]) + byqtbcb.getZbdj()
				* byqtbcb.getZbyl() + "";
		tb[row][9 + delta] = Util.toDouble(tb[row][9 + delta]) + byqtbcb.getZbyl()
				+ "";
		
		tb[row][10 + delta] = Util.toDouble(tb[row][10 + delta])
				+ byqtbcb.getByqydj() * byqtbcb.getByqyyl() + "";
		tb[row][11 + delta] = Util.toDouble(tb[row][11 + delta])
				+ byqtbcb.getByqyyl() + "";
		
		tb[row][12 + delta] = Util.toDouble(tb[row][12 + delta]) + byqtbcb.getGcdj()
				* byqtbcb.getGcyl() + "";
		tb[row][13 + delta] = Util.toDouble(tb[row][13 + delta]) + byqtbcb.getGcyl()
				+ "";


	}

	@Override
	public List<String[][]> getTbmx(Date date) {
		List<CBBYQTBDD> byqcbtbdds = byqcbDao.getTbdd();
		List<String[][]> rets = new ArrayList<String[][]>();
		String[][] tbmx = new String[byqcbtbdds.size()][31];
		String[][] jttb = new String[4][14];
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String[][] gstb = new String[cal.get(Calendar.MONTH) + 2][14];
		rets.add(tbmx);
		rets.add(jttb);
		rets.add(gstb);
		XMXX xmxx;
		CBBYQTBDD byqtbcb;

		Calendar firstMonth = Calendar.getInstance();
		firstMonth.set(cal.get(Calendar.YEAR), 1, 1);

		Calendar tmpCal = Calendar.getInstance();

		for (int i = 0; i < byqcbtbdds.size(); ++i) {
			byqtbcb = byqcbtbdds.get(i);
			xmxx = xmxxDao.getXmxxByBh(byqtbcb.getXmxx());

			Double zccb = byqtbcb.getGgyl() * byqtbcb.getGgdj()
					+ byqtbcb.getDjtyl() * byqtbcb.getDjtdj()
					+ byqtbcb.getByqyyl() * byqtbcb.getByqydj()
					+ byqtbcb.getGcyl() * byqtbcb.getGcdj() + byqtbcb.getZbyl()
					* byqtbcb.getZbdj();// 投标五大主材成本
			Double tbclzcb = (zccb + byqtbcb.getQtclcb()) / 1.17;// 投标材料成本总计
			Double tbzccb = tbclzcb + byqtbcb.getRgjzzfy();// 投标制造成本
			
			fillTbmx(tbmx, i, xmxx, byqtbcb, zccb, tbclzcb, tbzccb);

			Company comp = org.getCompany(Integer.valueOf(byqtbcb.getQybh()));
			if (comp != null) {

				if (comp.getParentCompany() != null) {
					comp = comp.getParentCompany();
				}
			
				filltbqk(jttb, gsMap.get(org.getCompany(byqtbcb.getQybh()).getType()), byqtbcb, tbzccb, true);
			}
			
			filltbqk(jttb, jttb.length - 1, byqtbcb, tbzccb, true);
			if (null != byqtbcb.getTbbjsj() && byqtbcb.getTbbjsj().before(date)
					&& byqtbcb.getTbbjsj().after(firstMonth.getTime())) {
				tmpCal.setTime(byqtbcb.getTbbjsj());
				filltbqk(gstb, tmpCal.get(Calendar.MONTH), byqtbcb, tbzccb,
						true);
				filltbqk(gstb, gstb.length - 1, byqtbcb, tbzccb, true);
			}
		}

		computeDj(gstb, true);
		computeDj(jttb, true);

		return rets;
	}

	private void fillZxmx(String[][] zxmx, int row, XMXX xmxx,
			CBBYQZXDD byqcbzxdd, CBBYQTBDD tbdd, Double zccb, Double clzcb,
			Double sczcb) {
		List<String> tbmxTmp = new ArrayList<String>();
		tbmxTmp.add(org.getCompany(byqcbzxdd.getQybh()).getName());
		tbmxTmp.add(xmxx != null ? org.getCompany(Integer.valueOf(xmxx.getDdszdw())).getName() : "");// 订单所在单位及项目公司订单所在单位及项目公司
		tbmxTmp.add(byqzxjdMap.get(Integer.valueOf(byqcbzxdd.getDdzxjd())));// 订单执行阶段订单执行阶段
		tbmxTmp.add(byqcbzxdd.getGzh());// 工作号工作号
		tbmxTmp.add(xmxx != null ? xmxx.getGb() == 0 ? "国内订单" : "国际订单" + "" : "");// 国别国别
		tbmxTmp.add(xmxx != null ? xmxx.getKhhylx() : "");// 客户行业类型客户行业类型
		tbmxTmp.add(byqcbzxdd.getHtzbsj() + "");// 合同中标时间合同中标时间
		tbmxTmp.add(tbdd != null ? tbdd.getXh() + "" : "");// 产品型号
		tbmxTmp.add(byqcbzxdd.getHth());// 合同号合同号
		tbmxTmp.add(xmxx != null ? xmxx.getYhdwmc() : "");// 用户单位名称订货单位
		tbmxTmp.add(byqcbzxdd.getJhsj() + "");// 交货时间交货时间
		tbmxTmp.add(byqcbzxdd.getCz() + "");// 产值产值
		tbmxTmp.add(byqcbzxdd.getGgph() + "");// 硅钢牌号硅钢牌号
		tbmxTmp.add(byqcbzxdd.getGgyl() + "");// 硅钢用量（单台）硅钢数量
		tbmxTmp.add(byqcbzxdd.getGgdj() + "");// 硅钢单价硅钢单价
		tbmxTmp.add(byqcbzxdd.getDjtyl() + "");// 电解铜用量（单台）铜用量
		tbmxTmp.add(byqcbzxdd.getDjtdj() + "");// 电解铜单价铜单价
		tbmxTmp.add(byqcbzxdd.getTjgf() + "");// 铜加工费铜加工费
		tbmxTmp.add(byqcbzxdd.getByqygg());// 变压器油规格变压器油规格
		tbmxTmp.add(byqcbzxdd.getByqyyl() + "");// 变压器油用量（单台）变压器油用量
		tbmxTmp.add(byqcbzxdd.getByqydj() + "");// 变压器油单价变压器油单价
		tbmxTmp.add(byqcbzxdd.getGcyl() + "");// 钢材用量（单台）钢材用量
		tbmxTmp.add(byqcbzxdd.getGcdj() + "");// 钢材单价钢材单价
		tbmxTmp.add(byqcbzxdd.getZbyl() + "");// 纸板用量（单台）纸板用量
		tbmxTmp.add(byqcbzxdd.getZbdj() + "");// 纸板单价纸板单价
		tbmxTmp.add(zccb + "");// 五大主材成本
		tbmxTmp.add(byqcbzxdd.getQtclcb() + "");// 其他材料成本其他材料成本
		tbmxTmp.add(clzcb + "");// 材料合计
		tbmxTmp.add(byqcbzxdd.getRgjzzfy() + "");// 人工及制造费用人工制造费用
		tbmxTmp.add(sczcb + "");// 生产总成本
		tbmxTmp.add(byqcbzxdd.getYf() + "");// 运费运费
		tbmxTmp.add(byqcbzxdd.getCz() - sczcb + "");// 产值测算毛利额
		tbmxTmp.add(byqcbzxdd.getCz() != 0 ? ((byqcbzxdd.getCz() - sczcb) / byqcbzxdd.getCz() + "") : "0"); // 产值测算毛利率
		tbmxTmp.toArray(zxmx[row]);
	}

	private void fillzxqk(String[][] jtzx, Integer row, CBBYQZXDD byqcbzxdd,
			Double sczcb, boolean hasDjtf) {
		if (null == byqcbzxdd){
			return;
		}
		
		int delta = 0;
		if (!hasDjtf) {
			delta = -1;
		}
		jtzx[row][0] = Util.toDouble(jtzx[row][0]) + byqcbzxdd.getCz() + "";
		jtzx[row][1] = Util.toDouble(jtzx[row][1]) + byqcbzxdd.getCz() - sczcb + "";
		jtzx[row][2] = Util.toDouble(jtzx[row][1]) / Util.toDouble(jtzx[row][0]) + "";
		
		jtzx[row][3] = Util.toDouble(jtzx[row][3]) + byqcbzxdd.getGgdj()
				* byqcbzxdd.getGgyl() + "";
		jtzx[row][4] = Util.toDouble(jtzx[row][4]) + byqcbzxdd.getGgyl() + "";
	
		jtzx[row][5] = Util.toDouble(jtzx[row][5]) + byqcbzxdd.getDjtdj()
				* byqcbzxdd.getDjtyl() + "";
		jtzx[row][6] = Util.toDouble(jtzx[row][6]) + byqcbzxdd.getDjtyl() + "";
	
		jtzx[row][7] = "0";// 电解铜费用

		jtzx[row][8 + delta] = Util.toDouble(jtzx[row][8 + delta])
				+ byqcbzxdd.getZbdj() * byqcbzxdd.getZbyl() + "";
		jtzx[row][9 + delta] = Util.toDouble(jtzx[row][9 + delta])
				+ byqcbzxdd.getZbyl() + "";
		
		jtzx[row][10 + delta] = Util.toDouble(jtzx[row][10 + delta])
				+ byqcbzxdd.getByqydj() * byqcbzxdd.getByqyyl() + "";
		jtzx[row][11 + delta] = Util.toDouble(jtzx[row][11 + delta])
				+ byqcbzxdd.getByqyyl() + "";

		jtzx[row][12 + delta] = Util.toDouble(jtzx[row][12 + delta])
				+ byqcbzxdd.getGcdj() * byqcbzxdd.getGcyl() + "";
		jtzx[row][13 + delta] = Util.toDouble(jtzx[row][13 + delta])
				+ byqcbzxdd.getGcyl() + "";
	}

	
	private Double getTbzzcb(CBBYQTBDD tbdd){
		Double tbzccb = 0.0;
		if (tbdd != null){
			Double tbwdzccb = tbdd.getGgyl() * tbdd.getGgdj() + tbdd.getDjtyl()
					* tbdd.getDjtdj() + tbdd.getByqyyl() * tbdd.getByqydj()
					+ tbdd.getGcyl() * tbdd.getGcdj() + tbdd.getZbyl()
					* tbdd.getZbdj();// 投标五大主材成本
			Double tbclzcb = (tbwdzccb + tbdd.getQtclcb()) / 1.17;// 投标材料成本总计
			tbzccb = tbclzcb + tbdd.getRgjzzfy();// 投标制造成本
		}
		return tbzccb;
	}
	
	@Override
	public List<String[][]> getZxmx(Date date) {
		List<CBBYQZXDD> byqcbzxdds = byqcbDao.getZxdd();
		List<String[][]> rets = new ArrayList<String[][]>();
		String[][] zxmx = new String[byqcbzxdds.size()][33];
		String[][] jtzx = new String[4 * 2][14];
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String[][] gszx = new String[(cal.get(Calendar.MONTH) + 2) * 2][14];
		rets.add(zxmx);
		rets.add(jtzx);
		rets.add(gszx);
		CBBYQZXDD byqcbzxdd;
		CBBYQTBDD tbdd;
		XMXX xmxx;

		Calendar firstMonth = Calendar.getInstance();
		firstMonth.set(cal.get(Calendar.YEAR), 1, 1);

		Calendar tmpCal = Calendar.getInstance();

		for (int i = 0; i < byqcbzxdds.size(); ++i) {
			byqcbzxdd = byqcbzxdds.get(i);
			tbdd = byqcbDao.getTbddById(byqcbzxdd.getTbcpbh());
			xmxx = null;
			if (null != tbdd){
				xmxx = xmxxDao.getXmxxByBh(tbdd.getXmxx());
			}

			Double zccb = byqcbzxdd.getGgyl() * byqcbzxdd.getGgdj()
					+ byqcbzxdd.getDjtyl() * byqcbzxdd.getDjtdj()
					+ byqcbzxdd.getByqyyl() * byqcbzxdd.getByqydj()
					+ byqcbzxdd.getGcyl() * byqcbzxdd.getGcdj()
					+ byqcbzxdd.getZbyl() * byqcbzxdd.getZbdj();// 投标五大主材成本
			Double clzcb = (zccb + byqcbzxdd.getQtclcb()) / 1.17;// 材料合计
			Double sczcb = clzcb + byqcbzxdd.getRgjzzfy();// 生产总成本

			Double tbzzcb = getTbzzcb(tbdd);

			fillZxmx(zxmx, i, xmxx, byqcbzxdd, tbdd, zccb, clzcb, sczcb);

			Company comp = org.getCompany(Integer.valueOf(byqcbzxdd.getQybh()));
			if (comp != null) {

				if (comp.getParentCompany() != null) {
					comp = comp.getParentCompany();
				}
			
				filltbqk(
						jtzx,
						gsMap.get(comp.getType()) * 2,
						tbdd, tbzzcb, true);
				fillzxqk(
						jtzx,
						gsMap.get(comp.getType()) * 2 + 1,
						byqcbzxdd, sczcb, true);
			}
			filltbqk(jtzx, jtzx.length - 2, tbdd, tbzzcb, true);
			fillzxqk(jtzx, jtzx.length - 1, byqcbzxdd, sczcb, true);
			
			if (null != byqcbzxdd.getJhsj() && byqcbzxdd.getJhsj().before(date)
					&& byqcbzxdd.getJhsj().after(firstMonth.getTime())) {
				tmpCal.setTime(byqcbzxdd.getJhsj());

				filltbqk(gszx, tmpCal.get(Calendar.MONTH) * 2, tbdd, tbzzcb,
						true);
				filltbqk(gszx, gszx.length - 2, tbdd, tbzzcb, true);

				fillzxqk(gszx, tmpCal.get(Calendar.MONTH) * 2 + 1, byqcbzxdd,
						sczcb, true);
				fillzxqk(gszx, gszx.length - 1, byqcbzxdd, sczcb, true);
			}

		}

		computeDj(jtzx, true);
		computeDj(gszx, true);

		return rets;
	}

	private void fillWgmx(String[][] wg, int row, CBBYQWGDD wgdd,
			CBBYQZXDD byqcbzxdd, XMXX xmxx, CBBYQTBDD tbdd, Double wdzccb,
			Double clzcb, Double sjzcb) {
		List<String> ret = new ArrayList<String>();
		ret.add(org.getCompany(wgdd.getQybh()).getName());
		ret.add(xmxx != null ? org.getCompany(Integer.valueOf(xmxx.getDdszdw())).getName() : "");// 订单所在单位及项目公司
		ret.add(byqcbzxdd != null ? byqcbzxdd != null ? byqcbzxdd.getGzh() : "" : "");// 工作号
		ret.add(wgdd.getWgsj());// 完工时间//完工时间
		ret.add(xmxx != null ? xmxx.getYhdwmc() : "");// 订货单位
		ret.add(tbdd != null ? tbdd.getXh() : ""); // 产品型号
		ret.add(tbdd != null ? tbdd.getDy() : ""); // 电压等级
		ret.add(tbdd != null ? tbdd.getCl() : ""); // 产量 （万KVA）
		ret.add(wgdd.getSjcz() + "");// 实际产值//产值
		ret.add(wgdd.getGgyl() + "");// 实际硅钢用量（单台）//实际硅钢片用量
		ret.add(wgdd.getGgdj() + "");// 实际硅钢单价//实际硅钢片单价
		ret.add(wgdd.getDjtyl() + "");// 实际电解铜用量（单台）//实际电解铜用量
		ret.add(wgdd.getDjtdj() + "");// 实际电解铜单价//实际电解铜单价（无税含加工费）
		ret.add(wgdd.getTjgf() + "");// 铜加工费//加工费(含税)
		ret.add(wgdd.getByqyyl() + "");// 实际变压器油用量（单台）//实际变压器油用量
		ret.add(wgdd.getByqydj() + "");// 实际变压器油单价//实际变压器油单价
		ret.add(wgdd.getGcyl() + "");// 实际钢材用量（单台）//实际钢材用量
		ret.add(wgdd.getGcdj() + "");// 实际钢材单价//实际钢材单价
		ret.add(wgdd.getZbyl() + "");// 实际纸板用量（单台）//实际绝缘纸板用量
		ret.add(wgdd.getZbdj() + "");// 实际纸板单价//实际绝缘纸板单价
		ret.add(wdzccb + "");// 实际五大主材成本
		ret.add(wgdd.getQtclcb() + "");// 其他材料成本//实际其他材料成本合计
		ret.add(clzcb + "");// 实际材料成本总计
		ret.add(wgdd.getRgjzzfy() + "");// 人工及制造费用//实际人工制造费用
		ret.add(sjzcb + "");// 实际总成本
		ret.add(wgdd.getYf() + "");// 运费//运费
		ret.add(wgdd.getSjcz() - sjzcb + "");// 实际毛利额
		ret.add(wgdd.getSjcz() != 0 ? (wgdd.getSjcz() - sjzcb) / wgdd.getSjcz() + "" : "");// 实际毛利率
		ret.toArray(wg[row]);
	}

	private void fillwgqk(String[][] jtzx, Integer row, CBBYQWGDD byqcbwgdd,
			Double sczcb) {
		
		if (null == byqcbwgdd){
			return;
		}
		
		jtzx[row][0] = Util.toDouble(jtzx[row][0]) + byqcbwgdd.getSjcz() + "";
		jtzx[row][1] = Util.toDouble(jtzx[row][1]) + byqcbwgdd.getSjcz() - sczcb + "";
		jtzx[row][2] = Util.toDouble(jtzx[row][1]) / Util.toDouble(jtzx[row][0]) + "";
		
		jtzx[row][3] = Util.toDouble(jtzx[row][3]) + byqcbwgdd.getGgdj()
				* byqcbwgdd.getGgyl() + "";
		jtzx[row][4] = Util.toDouble(jtzx[row][4]) + byqcbwgdd.getGgyl() + "";
		
		jtzx[row][5] = Util.toDouble(jtzx[row][5]) + byqcbwgdd.getDjtdj()
				* byqcbwgdd.getDjtyl() + "";
		jtzx[row][6] = Util.toDouble(jtzx[row][6]) + byqcbwgdd.getDjtyl() + "";

		
		jtzx[row][7] = Util.toDouble(jtzx[row][7]) + byqcbwgdd.getZbdj()
				* byqcbwgdd.getZbyl() + "";
		jtzx[row][8] = Util.toDouble(jtzx[row][8]) + byqcbwgdd.getZbyl() + "";
		
		jtzx[row][9] = Util.toDouble(jtzx[row][9]) + byqcbwgdd.getByqydj()
				* byqcbwgdd.getByqyyl() + "";
		jtzx[row][10] = Util.toDouble(jtzx[row][10]) + byqcbwgdd.getByqyyl()
				+ "";
		
		jtzx[row][11] = Util.toDouble(jtzx[row][11]) + byqcbwgdd.getGcdj()
				* byqcbwgdd.getGcyl() + "";
		jtzx[row][12] = Util.toDouble(jtzx[row][12]) + byqcbwgdd.getGcyl()
				+ "";		
	}
	
	private Double getZxsczcb(CBBYQZXDD byqcbzxdd){
		Double zx_sczcb = 0.0;
		if (byqcbzxdd != null){
			Double zx_zccb = byqcbzxdd.getGgyl() * byqcbzxdd.getGgdj()
					+ byqcbzxdd.getDjtyl() * byqcbzxdd.getDjtdj()
					+ byqcbzxdd.getByqyyl() * byqcbzxdd.getByqydj()
					+ byqcbzxdd.getGcyl() * byqcbzxdd.getGcdj()
					+ byqcbzxdd.getZbyl() * byqcbzxdd.getZbdj();// 投标五大主材成本
			Double zx_clzcb = (zx_zccb + byqcbzxdd.getQtclcb()) / 1.17;// 材料合计
			zx_sczcb = zx_clzcb + byqcbzxdd.getRgjzzfy();// 生产总成本
		}
		return zx_sczcb;
	}
	
	@Override
	public List<String[][]> getWgmx(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		List<CBBYQWGDD> byqcbwgdds = byqcbDao.getWgdd();
		List<String[][]> rets = new ArrayList<String[][]>();
		String[][] wgmx = new String[byqcbwgdds.size()][28];
		String[][] jtwg = new String[4 * 3][13];
		String[][] gswg = new String[(cal.get(Calendar.MONTH)) * 3 * 3 + 3][13];
		String[][] dydjwg = new String[7 * 3][13];
		rets.add(wgmx);
		rets.add(jtwg);
		rets.add(gswg);
		rets.add(dydjwg);
		CBBYQZXDD byqcbzxdd;
		CBBYQWGDD byqcbwgdd;
		CBBYQTBDD tbdd;
		XMXX xmxx;
		Company comp;

		Calendar tmpCal = Calendar.getInstance();
		for (int i = 0; i < byqcbwgdds.size(); ++i) {
			byqcbwgdd = byqcbwgdds.get(i);
			byqcbzxdd = byqcbDao.getZxddById(byqcbwgdd.getZxcpbh());
			tbdd = null;
			xmxx = null;

			if (null != byqcbzxdd) {
				tbdd = byqcbDao.getTbddById(byqcbzxdd.getTbcpbh());
			}

			if (null != tbdd) {
				xmxx = xmxxDao.getXmxxByBh(tbdd.getXmxx());
			}

			Double wg_zccb = byqcbwgdd.getGgyl() * byqcbwgdd.getGgdj()
					+ byqcbwgdd.getDjtyl() * byqcbwgdd.getDjtdj()
					+ byqcbwgdd.getByqyyl() * byqcbwgdd.getByqydj()
					+ byqcbwgdd.getGcyl() * byqcbwgdd.getGcdj()
					+ byqcbwgdd.getZbyl() * byqcbwgdd.getZbdj();// 投标五大主材成本
			Double wg_clzcb = (wg_zccb + byqcbwgdd.getQtclcb()) / 1.17;// 材料合计
			Double wg_sczcb = wg_clzcb + byqcbwgdd.getRgjzzfy();// 生产总成本

			fillWgmx(wgmx, i, byqcbwgdd, byqcbzxdd, xmxx, tbdd, wg_sczcb,
					wg_clzcb, wg_sczcb);

			if (byqcbwgdd.getWgsj() == null) {
				continue;
			}
			try {
				tmpCal.setTime(Util.toDate(byqcbwgdd.getWgsj()));
				if (tmpCal.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
						&& tmpCal.get(Calendar.MONTH) == cal
								.get(Calendar.MONTH)) {
					Double tbzccb = getTbzzcb(tbdd);

					Double zx_sczcb = getZxsczcb(byqcbzxdd);
					comp = org.getCompany(Integer.valueOf(byqcbwgdd.getQybh()));
					if (comp != null) {

						if (comp.getParentCompany() != null) {
							comp = comp.getParentCompany();
						}

						filltbqk(jtwg, gsMap.get(comp.getType()) * 3, tbdd,
								tbzccb, false);
						filltbqk(jtwg, jtwg.length - 3, tbdd, tbzccb, false);

						fillzxqk(jtwg, gsMap.get(comp.getType()) * 3 + 1,
								byqcbzxdd, zx_sczcb, false);
						fillzxqk(jtwg, jtwg.length - 2, byqcbzxdd, zx_sczcb,
								false);

						fillwgqk(jtwg, gsMap.get(comp.getType()) * 3 + 2,
								byqcbwgdd, wg_sczcb);
						fillwgqk(jtwg, jtwg.length - 1, byqcbwgdd, wg_sczcb);
					}

					if (tbdd != null && tbdd.getDy() != null
							&& dydjMap.containsKey(tbdd.getDy())) {

						filltbqk(dydjwg, dydjMap.get(tbdd.getDy()) * 3, tbdd,
								tbzccb, false);
						filltbqk(dydjwg, dydjwg.length - 3, tbdd, tbzccb, false);

						fillzxqk(dydjwg, dydjMap.get(tbdd.getDy()) * 3 + 1,
								byqcbzxdd, zx_sczcb, false);
						fillzxqk(dydjwg, dydjwg.length - 2, byqcbzxdd,
								zx_sczcb, false);

						fillwgqk(dydjwg, dydjMap.get(tbdd.getDy()) * 3 + 2,
								byqcbwgdd, wg_sczcb);
						fillwgqk(dydjwg, dydjwg.length - 1, byqcbwgdd, wg_sczcb);

					}
				}
			} catch (Exception e) {
				System.out.println(byqcbwgdd.getWgsj());
				e.printStackTrace();
			}
		}

		computeDj(jtwg, false);
		computeDj(dydjwg, false);
		return rets;
	}

	@Override
	public boolean IsTbCompanyExist(Company company) {
		return this.byqcbDao.containsTbCompany(company);
	}
	

	@Override
	public List<Integer> getZxCompany() {
		return this.byqcbDao.getZxCompany();
	}
	
	@Override
	public String[][] getTbmx(Company comp) {
		List<CBBYQTBDD> byqcbtbdds = byqcbDao.getTbdd();
		String[][] tbmx = new String[byqcbtbdds.size()][31];
		List<String[]> tbmxs = new ArrayList<String[]>();
		XMXX xmxx;
		CBBYQTBDD byqtbcb;
		Company tmpComp;

		for (int i = 0; i < byqcbtbdds.size(); ++i) {
			byqtbcb = byqcbtbdds.get(i);
			xmxx = xmxxDao.getXmxxByBh(byqtbcb.getXmxx());

			Double zccb = byqtbcb.getGgyl() * byqtbcb.getGgdj()
					+ byqtbcb.getDjtyl() * byqtbcb.getDjtdj()
					+ byqtbcb.getByqyyl() * byqtbcb.getByqydj()
					+ byqtbcb.getGcyl() * byqtbcb.getGcdj() + byqtbcb.getZbyl()
					* byqtbcb.getZbdj();// 投标五大主材成本
			Double tbclzcb = (zccb + byqtbcb.getQtclcb()) / 1.17;// 投标材料成本总计
			Double tbzccb = tbclzcb + byqtbcb.getRgjzzfy();// 投标制造成本
			if (null != xmxx) {
				tmpComp = org.getCompany(Integer.valueOf(xmxx.getDdszdw()));
				if (comp.getId() == tmpComp.getId()) {
					fillTbmx(tbmx, i, xmxx, byqtbcb, zccb, tbclzcb, tbzccb);
					tbmxs.add(tbmx[i]);
				}
			} else{
				fillTbmx(tbmx, i, xmxx, byqtbcb, zccb, tbclzcb, tbzccb);
				tbmxs.add(tbmx[i]);
			}

		}
		tbmx = new String[tbmxs.size()][31];
		tbmxs.toArray(tbmx);
		return tbmx;
	}

	@Override
	public String[][] getZxmx(Company comp) {
		List<CBBYQZXDD> byqcbzxdds = byqcbDao.getZxdd();
		String[][] zxmx = new String[byqcbzxdds.size()][33];
		CBBYQZXDD byqcbzxdd;
		CBBYQTBDD tbdd;
		XMXX xmxx = null;

		for (int i = 0; i < byqcbzxdds.size(); ++i) {
			byqcbzxdd = byqcbzxdds.get(i);
			tbdd = byqcbDao.getTbddById(byqcbzxdd.getTbcpbh());
			xmxx = null;
			if (null != tbdd && tbdd.getXmxx() != null){
				xmxx = xmxxDao.getXmxxByBh(tbdd.getXmxx());
			}

			Double zccb = byqcbzxdd.getGgyl() * byqcbzxdd.getGgdj()
					+ byqcbzxdd.getDjtyl() * byqcbzxdd.getDjtdj()
					+ byqcbzxdd.getByqyyl() * byqcbzxdd.getByqydj()
					+ byqcbzxdd.getGcyl() * byqcbzxdd.getGcdj()
					+ byqcbzxdd.getZbyl() * byqcbzxdd.getZbdj();// 投标五大主材成本
			Double clzcb = (zccb + byqcbzxdd.getQtclcb()) / 1.17;// 材料合计
			Double sczcb = clzcb + byqcbzxdd.getRgjzzfy();// 生产总成本

			if ((null != xmxx && Integer.valueOf(xmxx.getDdszdw()) == comp.getId()) || byqcbzxdd.getQybh() == comp.getId()){
				fillZxmx(zxmx, i, xmxx, byqcbzxdd, tbdd, zccb, clzcb, sczcb);
			}
		}

		return zxmx;
	}

	@Override
	public String[][] getWgmx(Company comp) {
		List<CBBYQWGDD> byqcbwgdds = byqcbDao.getWgdd(comp);
		String[][] wgmx = new String[byqcbwgdds.size()][28];

		CBBYQZXDD byqcbzxdd;
		CBBYQWGDD byqcbwgdd;
		CBBYQTBDD tbdd;
		XMXX xmxx;

		for (int i = 0; i < byqcbwgdds.size(); ++i) {
			byqcbwgdd = byqcbwgdds.get(i);

			byqcbzxdd = byqcbDao.getZxddById(byqcbwgdd.getZxcpbh());
			tbdd = null;
			xmxx = null;
			if (null != byqcbzxdd) {
				tbdd = byqcbDao.getTbddById(byqcbzxdd.getTbcpbh());
			}
			if (null != tbdd) {
				xmxx = xmxxDao.getXmxxByBh(tbdd.getXmxx());
			}

			Double wg_zccb = byqcbwgdd.getGgyl() * byqcbwgdd.getGgdj()
					+ byqcbwgdd.getDjtyl() * byqcbwgdd.getDjtdj()
					+ byqcbwgdd.getByqyyl() * byqcbwgdd.getByqydj()
					+ byqcbwgdd.getGcyl() * byqcbwgdd.getGcdj()
					+ byqcbwgdd.getZbyl() * byqcbwgdd.getZbdj();// 投标五大主材成本
			Double wg_clzcb = (wg_zccb + byqcbwgdd.getQtclcb()) / 1.17;// 材料合计
			Double wg_sczcb = wg_clzcb + byqcbwgdd.getRgjzzfy();// 生产总成本

			fillWgmx(wgmx, i, byqcbwgdd, byqcbzxdd, xmxx, tbdd, wg_zccb,
					wg_clzcb, wg_sczcb);

		}

		return wgmx;
	}

	@Override
	public List<String[][]> getJtwg(Date date) {
		List<String[][]> ret = getWgmx(date);
		ret.remove(2);
		ret.remove(0);
		return ret;
	}

	@Override
	public Date getLatestWgDate() {
		CBBYQWGDD wgdd = byqcbDao.getLatestWgdd();
		if (null != wgdd){
			return Util.toDate(wgdd.getWgsj());
		}
		return null;
	}

	@Override
	public List<Integer> getWgCompany() {
		return this.byqcbDao.getWgCompany();
	}

	@Override
	public List<Integer> getTbCompany() {
		return this.byqcbDao.getTbCompany();
	}

}
