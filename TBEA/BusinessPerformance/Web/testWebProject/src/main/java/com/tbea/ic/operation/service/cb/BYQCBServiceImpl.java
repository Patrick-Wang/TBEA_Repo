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

	private static Map<CompanyType, Integer> gsMap = new HashMap<CompanyType, Integer>();
	static {
		gsMap.put(CompanyType.SB, 0);
		gsMap.put(CompanyType.HB, 1);
		gsMap.put(CompanyType.XB, 2);
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

	private Organization org = CompanyManager.getPzghOrganization();
	
	private Double valueOf(String val) {
		if (val != null) {
			return Double.valueOf(val);
		}
		return 0.0;
	}

	private void computeDj(String[][] tb, boolean hasDjtf) {

		int delta = 0;
		if (!hasDjtf) {
			delta = -1;
		}
		for (int i = 0; i < tb.length; ++i) {
			tb[i][3] = (valueOf(tb[i][4]) == 0 ? 0.0 : valueOf(tb[i][3])
					/ valueOf(tb[i][4]))
					+ "";
			tb[i][5] = (valueOf(tb[i][6]) == 0 ? 0.0 : valueOf(tb[i][5])
					/ valueOf(tb[i][6]))
					+ "";
			tb[i][8 + delta] = (valueOf(tb[i][9 + delta]) == 0 ? 0.0
					: valueOf(tb[i][8 + delta]) / valueOf(tb[i][9 + delta]))
					+ "";
			tb[i][10 + delta] = (valueOf(tb[i][11 + delta]) == 0 ? 0.0
					: valueOf(tb[i][10 + delta]) / valueOf(tb[i][11 + delta]))
					+ "";
			tb[i][12 + delta] = (valueOf(tb[i][13 + delta]) == 0 ? 0.0
					: valueOf(tb[i][12 + delta]) / valueOf(tb[i][13 + delta]))
					+ "";
		}
	}

	private void fillTbmx(String[][] tbmx, int row, XMXX xmxx,
			CBBYQTBDD byqtbcb, Double zccb, Double tbclzcb, Double tbzccb) {
		tbmx[row][0] = org.getCompany(Integer.valueOf(xmxx.getDdszdw())).getName();// 订单所在单位及项目公司//订单所在单位及项目公司
		tbmx[row][1] = byqtbcb.getTbbjsj() + "";// 投标报价时间//投标报价时间
		tbmx[row][2] = xmxx.getYhdwmc();// 用户单位名称//用户单位名称
		tbmx[row][3] = xmxx.getXmmc();// 项目名称//项目名称
		tbmx[row][4] = byqtbcb.getYjjhsj();// 预计交货时间//预计交货时间
		tbmx[row][5] = byqtbcb.getXh();// 型号//型号
		tbmx[row][6] = byqtbcb.getDy();// 电压//电压
		tbmx[row][7] = byqtbcb.getCl();// 产量//产量（万KVA）
		tbmx[row][8] = byqtbcb.getCz() + "";// 产值//产值
		tbmx[row][9] = byqtbcb.getYjkbsj();// 预计开标时间//预计开标时间
		tbmx[row][10] = byqtbcb.getYczbgl() + "";// 销售部门预测的中标概率//销售部门预测的中标概率
		tbmx[row][11] = byqtbcb.getGgph() + "";// 硅钢牌号//投标硅钢牌号
		tbmx[row][12] = byqtbcb.getGgyl() + "";// 硅钢用量（单台）//投标硅钢用量（单台）
		tbmx[row][13] = byqtbcb.getGgdj() + "";// 硅钢单价//投标硅钢单价
		tbmx[row][14] = byqtbcb.getDjtyl() + "";// 电解铜用量（单台）//投标电解铜用量（单台）
		tbmx[row][15] = byqtbcb.getDjtdj() + "";// 电解铜单价//投标电解铜单价
		tbmx[row][16] = byqtbcb.getByqyyl() + "";// 变压器油用量（单台）//投标变压器油用量（单台）
		tbmx[row][17] = byqtbcb.getByqydj() + "";// 变压器油单价//投标变压器油单价
		tbmx[row][18] = byqtbcb.getGcyl() + "";// 钢材用量（单台）//投标钢材用量（单台）
		tbmx[row][19] = byqtbcb.getGcdj() + "";// 钢材单价//投标钢材单价
		tbmx[row][20] = byqtbcb.getZbyl() + "";// 纸板用量（单台）//投标纸板用量（单台）
		tbmx[row][21] = byqtbcb.getZbdj() + "";// 纸板单价//投标纸板单价
		tbmx[row][22] = zccb + "";// 投标五大主材成本
		tbmx[row][23] = byqtbcb.getQtclcb() + "";// 投标其他材料成本//投标其他材料成本
		tbmx[row][24] = tbclzcb + "";// 投标材料成本总计
		tbmx[row][25] = byqtbcb.getRgjzzfy() + "";// 人工及制造费用//人工及制造费用
		tbmx[row][26] = tbzccb + "";// 投标制造成本
		tbmx[row][27] = byqtbcb.getYf() + "";// 运费//运费
		tbmx[row][28] = byqtbcb.getCz() - tbzccb + "";// 投标毛利（单台）
		tbmx[row][29] = (byqtbcb.getCz() - tbzccb) / byqtbcb.getCz() + ""; // /投标毛利率
	}

	private void filltbqk(String[][] tb, int row, CBBYQTBDD byqtbcb,
			Double tbzccb, boolean hasDjtf) {

		int delta = 0;
		if (!hasDjtf) {
			delta = -1;
		}
		tb[row][0] = valueOf(tb[row][0]) + byqtbcb.getCz() + "";
		tb[row][1] = valueOf(tb[row][1]) + byqtbcb.getCz() - tbzccb + "";
		tb[row][2] = valueOf(tb[row][1]) / valueOf(tb[row][0]) + "";
		
		tb[row][3] = valueOf(tb[row][3]) + byqtbcb.getGgdj() * byqtbcb.getGgyl() + "";
		tb[row][4] = valueOf(tb[row][4]) + byqtbcb.getGgyl() + "";
			
		tb[row][5] = valueOf(tb[row][5]) + byqtbcb.getDjtdj()
				* byqtbcb.getDjtyl() + "";
		tb[row][6] = valueOf(tb[row][6]) + byqtbcb.getDjtyl() + "";

		tb[row][7] = "0";// 电解铜费用
		
		tb[row][8 + delta] = valueOf(tb[row][8 + delta]) + byqtbcb.getZbdj()
				* byqtbcb.getZbyl() + "";
		tb[row][9 + delta] = valueOf(tb[row][9 + delta]) + byqtbcb.getZbyl()
				+ "";
		
		tb[row][10 + delta] = valueOf(tb[row][10 + delta])
				+ byqtbcb.getByqydj() * byqtbcb.getByqyyl() + "";
		tb[row][11 + delta] = valueOf(tb[row][11 + delta])
				+ byqtbcb.getByqyyl() + "";
		
		tb[row][12 + delta] = valueOf(tb[row][12 + delta]) + byqtbcb.getGcdj()
				* byqtbcb.getGcyl() + "";
		tb[row][13 + delta] = valueOf(tb[row][13 + delta]) + byqtbcb.getGcyl()
				+ "";


	}

	@Override
	public List<String[][]> getTbmx(Date date) {
		List<CBBYQTBDD> byqcbtbdds = byqcbDao.getTbdd();
		List<String[][]> rets = new ArrayList<String[][]>();
		String[][] tbmx = new String[byqcbtbdds.size()][30];
		String[][] jttb = new String[4][14];
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String[][] gstb = new String[cal.get(Calendar.MONTH) + 2][14];
		rets.add(tbmx);
		rets.add(jttb);
		rets.add(gstb);
		XMXX xmxx;
		CBBYQTBDD byqtbcb;

		Company comp;

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

			comp = org.getCompany(Integer.valueOf(xmxx.getDdszdw()));
			if (comp != null) {

				if (comp.getParentCompany() != null) {
					comp = comp.getParentCompany();
				}

				filltbqk(jttb, gsMap.get(comp.getType()), byqtbcb, tbzccb, true);
				filltbqk(jttb, jttb.length - 1, byqtbcb, tbzccb, true);
				if (null != byqtbcb.getTbbjsj()
						&& byqtbcb.getTbbjsj().before(date)
						&& byqtbcb.getTbbjsj().after(firstMonth.getTime())) {
					tmpCal.setTime(byqtbcb.getTbbjsj());
					filltbqk(gstb, tmpCal.get(Calendar.MONTH), byqtbcb, tbzccb,
							true);
					filltbqk(gstb, gstb.length - 1, byqtbcb, tbzccb, true);
				}
			}
		}

		computeDj(gstb, true);
		computeDj(jttb, true);

		return rets;
	}

	private void fillZxmx(String[][] zxmx, int row, XMXX xmxx,
			CBBYQZXDD byqcbzxdd, CBBYQTBDD tbdd, Double zccb, Double clzcb,
			Double sczcb) {
		zxmx[row][0] = org.getCompany(Integer.valueOf(xmxx.getDdszdw())).getName();;// 订单所在单位及项目公司订单所在单位及项目公司
		zxmx[row][1] = byqzxjdMap.get(Integer.valueOf(byqcbzxdd.getDdzxjd()));// 订单执行阶段订单执行阶段
		zxmx[row][2] = byqcbzxdd.getGzh();// 工作号工作号
		zxmx[row][3] = xmxx.getGb() == 0 ? "国内订单" : "国际订单" + "";// 国别国别
		zxmx[row][4] = xmxx.getKhhylx();// 客户行业类型客户行业类型
		zxmx[row][5] = byqcbzxdd.getHtzbsj() + "";// 合同中标时间合同中标时间
		zxmx[row][6] = tbdd.getXh() + "";// 产品型号
		zxmx[row][7] = byqcbzxdd.getHth();// 合同号合同号
		zxmx[row][8] = xmxx.getYhdwmc();// 用户单位名称订货单位
		zxmx[row][9] = byqcbzxdd.getJhsj() + "";// 交货时间交货时间
		zxmx[row][10] = byqcbzxdd.getCz() + "";// 产值产值
		zxmx[row][11] = byqcbzxdd.getGgph() + "";// 硅钢牌号硅钢牌号
		zxmx[row][12] = byqcbzxdd.getGgyl() + "";// 硅钢用量（单台）硅钢数量
		zxmx[row][13] = byqcbzxdd.getGgdj() + "";// 硅钢单价硅钢单价
		zxmx[row][14] = byqcbzxdd.getDjtyl() + "";// 电解铜用量（单台）铜用量
		zxmx[row][15] = byqcbzxdd.getDjtdj() + "";// 电解铜单价铜单价
		zxmx[row][16] = byqcbzxdd.getTjgf() + "";// 铜加工费铜加工费
		zxmx[row][17] = byqcbzxdd.getByqygg();// 变压器油规格变压器油规格
		zxmx[row][18] = byqcbzxdd.getByqyyl() + "";// 变压器油用量（单台）变压器油用量
		zxmx[row][19] = byqcbzxdd.getByqydj() + "";// 变压器油单价变压器油单价
		zxmx[row][20] = byqcbzxdd.getGcyl() + "";// 钢材用量（单台）钢材用量
		zxmx[row][21] = byqcbzxdd.getGcdj() + "";// 钢材单价钢材单价
		zxmx[row][22] = byqcbzxdd.getZbyl() + "";// 纸板用量（单台）纸板用量
		zxmx[row][23] = byqcbzxdd.getZbdj() + "";// 纸板单价纸板单价
		zxmx[row][24] = zccb + "";// 五大主材成本
		zxmx[row][25] = byqcbzxdd.getQtclcb() + "";// 其他材料成本其他材料成本
		zxmx[row][26] = clzcb + "";// 材料合计
		zxmx[row][27] = byqcbzxdd.getRgjzzfy() + "";// 人工及制造费用人工制造费用
		zxmx[row][28] = sczcb + "";// 生产总成本
		zxmx[row][29] = byqcbzxdd.getYf() + "";// 运费运费
		zxmx[row][30] = byqcbzxdd.getCz() - sczcb + "";// 产值测算毛利额
		zxmx[row][31] = (byqcbzxdd.getCz() - sczcb) / byqcbzxdd.getCz() + ""; // 产值测算毛利率
	}

	private void fillzxqk(String[][] jtzx, Integer row, CBBYQZXDD byqcbzxdd,
			Double sczcb, boolean hasDjtf) {
		int delta = 0;
		if (!hasDjtf) {
			delta = -1;
		}
		jtzx[row][0] = valueOf(jtzx[row][0]) + byqcbzxdd.getCz() + "";
		jtzx[row][1] = valueOf(jtzx[row][0]) + byqcbzxdd.getCz() - sczcb + "";
		jtzx[row][2] = valueOf(jtzx[row][1]) / valueOf(jtzx[row][0]) + "";
		
		jtzx[row][3] = valueOf(jtzx[row][3]) + byqcbzxdd.getGgdj()
				* byqcbzxdd.getGgyl() + "";
		jtzx[row][4] = valueOf(jtzx[row][4]) + byqcbzxdd.getGgyl() + "";
	
		jtzx[row][5] = valueOf(jtzx[row][5]) + byqcbzxdd.getDjtdj()
				* byqcbzxdd.getDjtyl() + "";
		jtzx[row][6] = valueOf(jtzx[row][6]) + byqcbzxdd.getDjtyl() + "";
	
		jtzx[row][7] = "0";// 电解铜费用

		jtzx[row][8 + delta] = valueOf(jtzx[row][8 + delta])
				+ byqcbzxdd.getZbdj() * byqcbzxdd.getZbyl() + "";
		jtzx[row][9 + delta] = valueOf(jtzx[row][9 + delta])
				+ byqcbzxdd.getZbyl() + "";
		
		jtzx[row][10 + delta] = valueOf(jtzx[row][10 + delta])
				+ byqcbzxdd.getByqydj() * byqcbzxdd.getByqyyl() + "";
		jtzx[row][11 + delta] = valueOf(jtzx[row][11 + delta])
				+ byqcbzxdd.getByqyyl() + "";

		jtzx[row][12 + delta] = valueOf(jtzx[row][12 + delta])
				+ byqcbzxdd.getGcdj() * byqcbzxdd.getGcyl() + "";
		jtzx[row][13 + delta] = valueOf(jtzx[row][13 + delta])
				+ byqcbzxdd.getGcyl() + "";
	}

	@Override
	public List<String[][]> getZxmx(Date date) {
		List<CBBYQZXDD> byqcbzxdds = byqcbDao.getZxdd();
		List<String[][]> rets = new ArrayList<String[][]>();
		String[][] zxmx = new String[byqcbzxdds.size()][32];
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
		
		Company comp;

		Calendar firstMonth = Calendar.getInstance();
		firstMonth.set(cal.get(Calendar.YEAR), 1, 1);

		Calendar tmpCal = Calendar.getInstance();

		for (int i = 0; i < byqcbzxdds.size(); ++i) {
			byqcbzxdd = byqcbzxdds.get(i);
			tbdd = byqcbDao.getTbddById(byqcbzxdd.getTbcpbh());
			xmxx = xmxxDao.getXmxxByBh(tbdd.getXmxx());

			Double zccb = byqcbzxdd.getGgyl() * byqcbzxdd.getGgdj()
					+ byqcbzxdd.getDjtyl() * byqcbzxdd.getDjtdj()
					+ byqcbzxdd.getByqyyl() * byqcbzxdd.getByqydj()
					+ byqcbzxdd.getGcyl() * byqcbzxdd.getGcdj()
					+ byqcbzxdd.getZbyl() * byqcbzxdd.getZbdj();// 投标五大主材成本
			Double clzcb = (zccb + byqcbzxdd.getQtclcb()) / 1.17;// 材料合计
			Double sczcb = clzcb + byqcbzxdd.getRgjzzfy();// 生产总成本

			Double tbwdzccb = tbdd.getGgyl() * tbdd.getGgdj() + tbdd.getDjtyl()
					* tbdd.getDjtdj() + tbdd.getByqyyl() * tbdd.getByqydj()
					+ tbdd.getGcyl() * tbdd.getGcdj() + tbdd.getZbyl()
					* tbdd.getZbdj();// 投标五大主材成本
			Double tbclzcb = (tbwdzccb + tbdd.getQtclcb()) / 1.17;// 投标材料成本总计
			Double tbzccb = tbclzcb + tbdd.getRgjzzfy();// 投标制造成本

			fillZxmx(zxmx, i, xmxx, byqcbzxdd, tbdd, zccb, clzcb, sczcb);

			comp = org.getCompany(Integer.valueOf(xmxx.getDdszdw()));
			if (comp != null) {

				if (comp.getParentCompany() != null) {
					comp = comp.getParentCompany();
				}

				filltbqk(jtzx, gsMap.get(comp.getType()) * 2, tbdd, tbzccb,
						true);
				filltbqk(jtzx, jtzx.length - 2, tbdd, tbzccb, true);

				fillzxqk(jtzx, gsMap.get(comp.getType()) * 2 + 1, byqcbzxdd,
						sczcb, true);
				fillzxqk(jtzx, jtzx.length - 1, byqcbzxdd, sczcb, true);

				if (null != byqcbzxdd.getJhsj()
						&& byqcbzxdd.getJhsj().before(date)
						&& byqcbzxdd.getJhsj().after(firstMonth.getTime())) {
					tmpCal.setTime(byqcbzxdd.getJhsj());

					filltbqk(gszx, tmpCal.get(Calendar.MONTH) * 2, tbdd,
							tbzccb, true);
					filltbqk(gszx, gszx.length - 2, tbdd, tbzccb, true);

					fillzxqk(gszx, tmpCal.get(Calendar.MONTH) * 2 + 1,
							byqcbzxdd, sczcb, true);
					fillzxqk(gszx, gszx.length - 1, byqcbzxdd, sczcb, true);
				}
			}

		}

		computeDj(jtzx, true);
		computeDj(gszx, true);

		return rets;
	}

	private void fillWgmx(String[][] wg, int row, CBBYQWGDD wgdd,
			CBBYQZXDD byqcbzxdd, XMXX xmxx, CBBYQTBDD tbdd, Double wdzccb,
			Double clzcb, Double sjzcb) {
		wg[row][0] =  org.getCompany(Integer.valueOf(xmxx.getDdszdw())).getName();// 订单所在单位及项目公司
		wg[row][1] = byqcbzxdd != null ? byqcbzxdd.getGzh() : "";// 工作号
		wg[row][2] = wgdd.getWgsj();// 完工时间//完工时间
		wg[row][3] = xmxx.getYhdwmc();// 订货单位
		wg[row][4] = tbdd.getXh(); // 产品型号
		wg[row][5] = tbdd.getDy(); // 电压等级
		wg[row][6] = tbdd.getCl(); // 产量 （万KVA）
		wg[row][7] = wgdd.getSjcz() + "";// 实际产值//产值
		wg[row][8] = wgdd.getGgyl() + "";// 实际硅钢用量（单台）//实际硅钢片用量
		wg[row][9] = wgdd.getGgdj() + "";// 实际硅钢单价//实际硅钢片单价
		wg[row][10] = wgdd.getDjtyl() + "";// 实际电解铜用量（单台）//实际电解铜用量
		wg[row][11] = wgdd.getDjtdj() + "";// 实际电解铜单价//实际电解铜单价（无税含加工费）
		wg[row][12] = wgdd.getTjgf() + "";// 铜加工费//加工费(含税)
		wg[row][13] = wgdd.getByqyyl() + "";// 实际变压器油用量（单台）//实际变压器油用量
		wg[row][14] = wgdd.getByqydj() + "";// 实际变压器油单价//实际变压器油单价
		wg[row][15] = wgdd.getGcyl() + "";// 实际钢材用量（单台）//实际钢材用量
		wg[row][16] = wgdd.getGcdj() + "";// 实际钢材单价//实际钢材单价
		wg[row][17] = wgdd.getZbyl() + "";// 实际纸板用量（单台）//实际绝缘纸板用量
		wg[row][18] = wgdd.getZbdj() + "";// 实际纸板单价//实际绝缘纸板单价
		wg[row][19] = wdzccb + "";// 实际五大主材成本
		wg[row][20] = wgdd.getQtclcb() + "";// 其他材料成本//实际其他材料成本合计
		wg[row][21] = clzcb + "";// 实际材料成本总计
		wg[row][22] = wgdd.getRgjzzfy() + "";// 人工及制造费用//实际人工制造费用
		wg[row][23] = sjzcb + "";// 实际总成本
		wg[row][24] = wgdd.getYf() + "";// 运费//运费
		wg[row][25] = wgdd.getSjcz() - sjzcb + "";// 实际毛利额
		wg[row][26] = (wgdd.getSjcz() - sjzcb) / wgdd.getSjcz() + "";// 实际毛利率
	}

	private void fillwgqk(String[][] jtzx, Integer row, CBBYQWGDD byqcbwgdd,
			Double sczcb) {
		jtzx[row][0] = valueOf(jtzx[row][0]) + byqcbwgdd.getSjcz() + "";
		jtzx[row][1] = valueOf(jtzx[row][0]) + byqcbwgdd.getSjcz() - sczcb + "";
		jtzx[row][2] = valueOf(jtzx[row][1]) / valueOf(jtzx[row][0]) + "";
		
		jtzx[row][3] = valueOf(jtzx[row][3]) + byqcbwgdd.getGgdj()
				* byqcbwgdd.getGgyl() + "";
		jtzx[row][4] = valueOf(jtzx[row][4]) + byqcbwgdd.getGgyl() + "";
		
		jtzx[row][5] = valueOf(jtzx[row][5]) + byqcbwgdd.getDjtdj()
				* byqcbwgdd.getDjtyl() + "";
		jtzx[row][6] = valueOf(jtzx[row][6]) + byqcbwgdd.getDjtyl() + "";

		
		jtzx[row][7] = valueOf(jtzx[row][7]) + byqcbwgdd.getZbdj()
				* byqcbwgdd.getZbyl() + "";
		jtzx[row][8] = valueOf(jtzx[row][8]) + byqcbwgdd.getZbyl() + "";
		
		jtzx[row][9] = valueOf(jtzx[row][9]) + byqcbwgdd.getByqydj()
				* byqcbwgdd.getByqyyl() + "";
		jtzx[row][10] = valueOf(jtzx[row][10]) + byqcbwgdd.getByqyyl()
				+ "";
		
		jtzx[row][11] = valueOf(jtzx[row][11]) + byqcbwgdd.getGcdj()
				* byqcbwgdd.getGcyl() + "";
		jtzx[row][12] = valueOf(jtzx[row][12]) + byqcbwgdd.getGcyl()
				+ "";		
	}

	@Override
	public List<String[][]> getWgmx(Date date) {
		List<CBBYQWGDD> byqcbwgdds = byqcbDao.getWgdd();
		List<String[][]> rets = new ArrayList<String[][]>();
		String[][] wgmx = new String[byqcbwgdds.size()][27];
		String[][] jtwg = new String[4 * 3][13];
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String[][] gswg = new String[(cal.get(Calendar.MONTH)) * 3 * 3 + 3][13];
		String[][] dydjwg = new String[7 * 3][13];
		
		CBBYQZXDD byqcbzxdd;
		CBBYQWGDD byqcbwgdd;
		CBBYQTBDD tbdd;
		XMXX xmxx;
		
		Company comp;

		Calendar firstMonth = Calendar.getInstance();
		firstMonth.set(cal.get(Calendar.YEAR), 1, 1);

		Calendar tmpCal = Calendar.getInstance();
		int len = 0;
		for (int i = 0; i < byqcbwgdds.size(); ++i) {
			byqcbwgdd = byqcbwgdds.get(i);
			byqcbzxdd = byqcbDao.getZxddById(byqcbwgdd.getZxcpbh());
			if (null == byqcbzxdd)
				continue;
			tbdd = byqcbDao.getTbddById(byqcbzxdd.getTbcpbh());
			if (null == tbdd)
				continue;
			xmxx = xmxxDao.getXmxxByBh(tbdd.getXmxx());
			if (null == xmxx)
				continue;

			Double wg_zccb = byqcbwgdd.getGgyl() * byqcbwgdd.getGgdj()
					+ byqcbwgdd.getDjtyl() * byqcbwgdd.getDjtdj()
					+ byqcbwgdd.getByqyyl() * byqcbwgdd.getByqydj()
					+ byqcbwgdd.getGcyl() * byqcbwgdd.getGcdj()
					+ byqcbwgdd.getZbyl() * byqcbwgdd.getZbdj();// 投标五大主材成本
			Double wg_clzcb = (wg_zccb + byqcbwgdd.getQtclcb()) / 1.17;// 材料合计
			Double wg_sczcb = wg_clzcb + byqcbwgdd.getRgjzzfy();// 生产总成本
			if (xmxx.getYhdwmc() != null){
				//System.out.println(xmxx.getYhdwmc());
				++len;
				fillWgmx(wgmx, i, byqcbwgdd, byqcbzxdd, xmxx, tbdd, wg_zccb, wg_clzcb, wg_sczcb);
			
			Double zx_zccb = byqcbzxdd.getGgyl() * byqcbzxdd.getGgdj()
					+ byqcbzxdd.getDjtyl() * byqcbzxdd.getDjtdj()
					+ byqcbzxdd.getByqyyl() * byqcbzxdd.getByqydj()
					+ byqcbzxdd.getGcyl() * byqcbzxdd.getGcdj()
					+ byqcbzxdd.getZbyl() * byqcbzxdd.getZbdj();// 投标五大主材成本
			Double zx_clzcb = (zx_zccb + byqcbzxdd.getQtclcb()) / 1.17;// 材料合计
			Double zx_sczcb = zx_clzcb + byqcbzxdd.getRgjzzfy();// 生产总成本

			Double tbwdzccb = tbdd.getGgyl() * tbdd.getGgdj() + tbdd.getDjtyl()
					* tbdd.getDjtdj() + tbdd.getByqyyl() * tbdd.getByqydj()
					+ tbdd.getGcyl() * tbdd.getGcdj() + tbdd.getZbyl()
					* tbdd.getZbdj();// 投标五大主材成本
			Double tbclzcb = (tbwdzccb + tbdd.getQtclcb()) / 1.17;// 投标材料成本总计
			Double tbzccb = tbclzcb + tbdd.getRgjzzfy();// 投标制造成本
			if (byqcbwgdd.getWgsj() != null) {
				tmpCal.setTime(Date.valueOf(byqcbwgdd.getWgsj() + "-1"));
				if (tmpCal.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
						&& tmpCal.get(Calendar.MONTH) == cal
								.get(Calendar.MONTH)) {
					comp = org.getCompany(Integer.valueOf(xmxx.getDdszdw()));
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

					if (tbdd.getDy() != null
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
			}
			}

			//
		}
		//
		computeDj(jtwg, false);
		computeDj(dydjwg, false);

		String[][] newWgmx = new String[len][27];
		for (int i = 0; i < len; ++i){
			for (int j = 0; j < 27; ++j){
				newWgmx[i][j] = wgmx[i][j];;
			}
		}
		
		rets.add(newWgmx);
		rets.add(jtwg);
		rets.add(gswg);
		rets.add(dydjwg);
		
		return rets;
	}

	@Override
	public boolean IsTbCompanyExist(Company company) {
		return this.byqcbDao.containsTbCompany(company);
	}
	

	@Override
	public boolean IsZxCompanyExist(Company company) {
		return this.byqcbDao.containsZxCompany(company);
	}
	

	@Override
	public boolean IsWgCompanyExist(Company company) {
		return this.byqcbDao.containsWgCompany(company);
	}

	@Override
	public String[][] getTbmx(Company comp) {
		List<CBBYQTBDD> byqcbtbdds = byqcbDao.getTbdd();
		String[][] tbmx = new String[byqcbtbdds.size()][30];
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
			tmpComp = org.getCompany(Integer.valueOf(xmxx.getDdszdw()));
			if (comp.getId() == tmpComp.getId()){
				fillTbmx(tbmx, i, xmxx, byqtbcb, zccb, tbclzcb, tbzccb);
				tbmxs.add(tbmx[i]);
			}
			

		}
		tbmx = new String[tbmxs.size()][30];
		tbmxs.toArray(tbmx);
		return tbmx;
	}

	@Override
	public String[][] getZxmx(Company comp) {
		List<CBBYQZXDD> byqcbzxdds = byqcbDao.getZxdd();
		String[][] zxmx = new String[byqcbzxdds.size()][32];
		CBBYQZXDD byqcbzxdd;
		CBBYQTBDD tbdd;
		XMXX xmxx;

		for (int i = 0; i < byqcbzxdds.size(); ++i) {
			byqcbzxdd = byqcbzxdds.get(i);
			tbdd = byqcbDao.getTbddById(byqcbzxdd.getTbcpbh());
			xmxx = xmxxDao.getXmxxByBh(tbdd.getXmxx());

			Double zccb = byqcbzxdd.getGgyl() * byqcbzxdd.getGgdj()
					+ byqcbzxdd.getDjtyl() * byqcbzxdd.getDjtdj()
					+ byqcbzxdd.getByqyyl() * byqcbzxdd.getByqydj()
					+ byqcbzxdd.getGcyl() * byqcbzxdd.getGcdj()
					+ byqcbzxdd.getZbyl() * byqcbzxdd.getZbdj();// 投标五大主材成本
			Double clzcb = (zccb + byqcbzxdd.getQtclcb()) / 1.17;// 材料合计
			Double sczcb = clzcb + byqcbzxdd.getRgjzzfy();// 生产总成本

			if (Integer.valueOf(xmxx.getDdszdw()) == comp.getId()){
				fillZxmx(zxmx, i, xmxx, byqcbzxdd, tbdd, zccb, clzcb, sczcb);
			}
		}

		return zxmx;
	}

	@Override
	public String[][] getWgmx(Company comp) {
		List<CBBYQWGDD> byqcbwgdds = byqcbDao.getWgdd();
		String[][] wgmx = new String[byqcbwgdds.size()][27];

		CBBYQZXDD byqcbzxdd;
		CBBYQWGDD byqcbwgdd;
		CBBYQTBDD tbdd;
		XMXX xmxx;
		
		int len = 0;
		for (int i = 0; i < byqcbwgdds.size(); ++i) {
			byqcbwgdd = byqcbwgdds.get(i);
			byqcbzxdd = byqcbDao.getZxddById(byqcbwgdd.getZxcpbh());
			if (null == byqcbzxdd)
				continue;
			tbdd = byqcbDao.getTbddById(byqcbzxdd.getTbcpbh());
			if (null == tbdd)
				continue;
			xmxx = xmxxDao.getXmxxByBh(tbdd.getXmxx());
			if (null == xmxx)
				continue;
			Double wg_zccb = byqcbwgdd.getGgyl() * byqcbwgdd.getGgdj()
					+ byqcbwgdd.getDjtyl() * byqcbwgdd.getDjtdj()
					+ byqcbwgdd.getByqyyl() * byqcbwgdd.getByqydj()
					+ byqcbwgdd.getGcyl() * byqcbwgdd.getGcdj()
					+ byqcbwgdd.getZbyl() * byqcbwgdd.getZbdj();// 投标五大主材成本
			Double wg_clzcb = (wg_zccb + byqcbwgdd.getQtclcb()) / 1.17;// 材料合计
			Double wg_sczcb = wg_clzcb + byqcbwgdd.getRgjzzfy();// 生产总成本
			if (xmxx.getYhdwmc() != null){
				++len;
				if (Integer.valueOf(xmxx.getDdszdw()) == comp.getId()){
					fillWgmx(wgmx, i, byqcbwgdd, byqcbzxdd, xmxx, tbdd, wg_zccb, wg_clzcb, wg_sczcb);
				}
			}
		}

		String[][] newWgmx = new String[len][27];
		for (int i = 0; i < len; ++i){
			for (int j = 0; j < 27; ++j){
				newWgmx[i][j] = wgmx[i][j];;
			}
		}
		return newWgmx;
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
			return Date.valueOf(wgdd.getWgsj() + "-1");
		}
		return null;
	}

}
