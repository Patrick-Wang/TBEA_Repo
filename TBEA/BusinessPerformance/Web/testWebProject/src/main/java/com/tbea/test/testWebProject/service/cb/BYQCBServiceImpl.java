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
import com.tbea.test.testWebProject.common.CompanyManager.CompanyType;
import com.tbea.test.testWebProject.common.Organization;
import com.tbea.test.testWebProject.model.dao.cb.BYQCBDao;
import com.tbea.test.testWebProject.model.dao.cb.XMXXDao;
import com.tbea.test.testWebProject.model.entity.CBBYQTBDD;
import com.tbea.test.testWebProject.model.entity.CBBYQZXDD;
import com.tbea.test.testWebProject.model.entity.XMXX;

@Service
@Transactional("transactionManager")
public class BYQCBServiceImpl  implements BYQCBService{

	@Autowired
	private BYQCBDao byqcbDao;

	@Autowired
	private XMXXDao xmxxDao;

	private static Map<CompanyType, Integer> gsMap = new HashMap<CompanyType, Integer>();
	static{
		gsMap.put(CompanyType.SB, 0);
		gsMap.put(CompanyType.HB, 1);
		gsMap.put(CompanyType.XB, 2);
	}
	
	private void fillTbmx(String[][] tbmx, int row, 
			XMXX xmxx,
	CBBYQTBDD byqtbcb,
	Double zccb,
	Double tbclzcb,
	Double tbzccb){
		tbmx[row][0] = xmxx.getDdszdw();// 订单所在单位及项目公司//订单所在单位及项目公司
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
		Organization org = CompanyManager.getPzghOrganization();
		Company comp;
		
		Calendar firstMonth = Calendar.getInstance();
		firstMonth.set(cal.get(Calendar.YEAR), 1, 1);
		
		Calendar tmpCal = Calendar.getInstance();

		for (int i = 0; i < byqcbtbdds.size(); ++i) {
			byqtbcb = byqcbtbdds.get(i);
			xmxx = xmxxDao.getXmxxByBh(byqtbcb.getXmxx());

			Double zccb = byqtbcb.getGgyl() * byqtbcb.getGgdj()	+ 
					byqtbcb.getDjtyl() * byqtbcb.getDjtdj() + 
					byqtbcb.getByqyyl() * byqtbcb.getByqydj() + 
					byqtbcb.getGcyl() * byqtbcb.getGcdj() + 
					byqtbcb.getZbyl() * byqtbcb.getZbdj();// 投标五大主材成本
			Double tbclzcb = (zccb + byqtbcb.getQtclcb()) / 1.17;// 投标材料成本总计
			Double tbzccb = tbclzcb + byqtbcb.getRgjzzfy();// 投标制造成本
			fillTbmx(tbmx, i, xmxx, byqtbcb, zccb, tbclzcb, tbzccb);
			
			comp = org.getCompany(Integer.valueOf(xmxx.getDdszdw()));
			if (comp != null) {

				if (comp.getParentCompany() != null) {
					comp = comp.getParentCompany();
				}

				filltbqk(jttb, gsMap.get(comp.getType()), byqtbcb, tbzccb);

				if (null != byqtbcb.getTbbjsj()
						&& byqtbcb.getTbbjsj().before(date)
						&& byqtbcb.getTbbjsj().after(firstMonth.getTime())) {
					tmpCal.setTime(byqtbcb.getTbbjsj());
					filltbqk(gstb, tmpCal.get(Calendar.MONTH), byqtbcb, tbzccb);
				}
			}
		}

		return rets;
	}


	private Double valueOf(String val){
		if (val != null){
			return Double.valueOf(val);
		}
		return 0.0;
	}
	
	private void filltbqk(String[][] tb, int row, CBBYQTBDD byqtbcb, Double tbzccb) {
		tb[row][0] = valueOf(tb[row][0]) + byqtbcb.getCz() + "";
		tb[row][1] = valueOf(tb[row][1]) + byqtbcb.getCz() - tbzccb + "";
		tb[row][2] = valueOf(tb[row][1]) / valueOf(tb[row][0]) + "";
		tb[row][3] = valueOf(tb[row][3]) + byqtbcb.getGgdj() + "";
		tb[row][4] = valueOf(tb[row][4]) + byqtbcb.getGgyl() + "";
		tb[row][5] = valueOf(tb[row][5]) + byqtbcb.getDjtdj() + "";
		tb[row][6] = valueOf(tb[row][6]) + byqtbcb.getDjtyl() + "";
		tb[row][7] = "0";//电解铜费用
		tb[row][8] = valueOf(tb[row][8]) + byqtbcb.getZbdj() + "";
		tb[row][9] = valueOf(tb[row][9]) + byqtbcb.getZbyl() + "";
		tb[row][10] = valueOf(tb[row][10]) + byqtbcb.getByqydj() + "";
		tb[row][11] = valueOf(tb[row][11]) + byqtbcb.getByqyyl() + "";
		tb[row][12] = valueOf(tb[row][12]) + byqtbcb.getGcdj() + "";
		tb[row][13] = valueOf(tb[row][13]) + byqtbcb.getGcyl() + "";
		
		tb[tb.length - 1][0] = valueOf(tb[tb.length - 1][0]) + byqtbcb.getCz() + "";
		tb[tb.length - 1][1] = valueOf(tb[tb.length - 1][1]) + byqtbcb.getCz() - tbzccb + "";
		tb[tb.length - 1][2] = valueOf(tb[tb.length - 1][1]) / valueOf(tb[tb.length - 1][0]) + "";
		tb[tb.length - 1][3] = valueOf(tb[tb.length - 1][3]) + byqtbcb.getGgdj() + "";
		tb[tb.length - 1][4] = valueOf(tb[tb.length - 1][4]) + byqtbcb.getGgyl() + "";
		tb[tb.length - 1][5] = valueOf(tb[tb.length - 1][5]) + byqtbcb.getDjtdj() + "";
		tb[tb.length - 1][6] = valueOf(tb[tb.length - 1][6]) + byqtbcb.getDjtyl() + "";
		tb[tb.length - 1][7] = "0";//电解铜费用
		tb[tb.length - 1][8] = valueOf(tb[tb.length - 1][8]) + byqtbcb.getZbdj() + "";
		tb[tb.length - 1][9] = valueOf(tb[tb.length - 1][9]) + byqtbcb.getZbyl() + "";
		tb[tb.length - 1][10] = valueOf(tb[tb.length - 1][10]) + byqtbcb.getByqydj() + "";
		tb[tb.length - 1][11] = valueOf(tb[tb.length - 1][11]) + byqtbcb.getByqyyl() + "";
		tb[tb.length - 1][12] = valueOf(tb[tb.length - 1][12]) + byqtbcb.getGcdj() + "";
		tb[tb.length - 1][13] = valueOf(tb[tb.length - 1][13]) + byqtbcb.getGcyl() + "";
	}

	
	private void fillZxmx(String[][] zxmx, int row, XMXX xmxx,
			CBBYQZXDD byqcbzxdd,
			CBBYQTBDD tbdd, Double zccb, Double clzcb, Double sczcb){
		zxmx[row][0] =xmxx.getDdszdw();//订单所在单位及项目公司订单所在单位及项目公司
		zxmx[row][1] =byqcbzxdd.getDdzxjd() + "";//订单执行阶段订单执行阶段
		zxmx[row][2] =byqcbzxdd.getGzh();//工作号工作号
		zxmx[row][3] =xmxx.getGb() + "";//国别国别
		zxmx[row][4] =xmxx.getKhhylx();//客户行业类型客户行业类型
		zxmx[row][5] =byqcbzxdd.getHtzbsj() + "";//合同中标时间合同中标时间 
		zxmx[row][6] =tbdd.getXh() + "";//产品型号
		zxmx[row][7] =byqcbzxdd.getHth();//合同号合同号
		zxmx[row][8] =xmxx.getYhdwmc();//用户单位名称订货单位
		zxmx[row][9] =byqcbzxdd.getJhsj() + "";//交货时间交货时间
		zxmx[row][10] =byqcbzxdd.getCz() + "";//产值产值
		zxmx[row][11] =byqcbzxdd.getGgph() + "";//硅钢牌号硅钢牌号
		zxmx[row][12] =byqcbzxdd.getGgyl() + "";//硅钢用量（单台）硅钢数量
		zxmx[row][13] =byqcbzxdd.getGgdj() + "";//硅钢单价硅钢单价
		zxmx[row][14] =byqcbzxdd.getDjtyl() + "";//电解铜用量（单台）铜用量
		zxmx[row][15] =byqcbzxdd.getDjtdj() + "";//电解铜单价铜单价
		zxmx[row][16] =byqcbzxdd.getTjgf() + "";//铜加工费铜加工费
		zxmx[row][17] =byqcbzxdd.getByqygg();//变压器油规格变压器油规格
		zxmx[row][18] =byqcbzxdd.getByqyyl() + "";//变压器油用量（单台）变压器油用量
		zxmx[row][19] =byqcbzxdd.getByqydj() + "";//变压器油单价变压器油单价
		zxmx[row][20] =byqcbzxdd.getGcyl() + "";//钢材用量（单台）钢材用量
		zxmx[row][21] =byqcbzxdd.getGcdj() + "";//钢材单价钢材单价
		zxmx[row][22] =byqcbzxdd.getZbyl() + "";//纸板用量（单台）纸板用量
		zxmx[row][23] =byqcbzxdd.getZbdj() + "";//纸板单价纸板单价
		zxmx[row][24] = zccb + "";//五大主材成本
		zxmx[row][25] =byqcbzxdd.getQtclcb() + "";//其他材料成本其他材料成本
		zxmx[row][26] = clzcb + "";//材料合计
		zxmx[row][27] =byqcbzxdd.getRgjzzfy() + "";//人工及制造费用人工制造费用
		zxmx[row][28] = sczcb + "";//生产总成本
		zxmx[row][29] =byqcbzxdd.getYf() + "";//运费运费
		zxmx[row][30] = byqcbzxdd.getCz() - sczcb + "";//产值测算毛利额
		zxmx[row][31] =(byqcbzxdd.getCz() - sczcb) / sczcb + ""; //产值测算毛利率
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
		Organization org = CompanyManager.getPzghOrganization();
		Company comp;
		
		Calendar firstMonth = Calendar.getInstance();
		firstMonth.set(cal.get(Calendar.YEAR), 1, 1);
		
		Calendar tmpCal = Calendar.getInstance();
		
		for (int i = 0; i < byqcbzxdds.size(); ++i) {
			byqcbzxdd = byqcbzxdds.get(i);
			tbdd = byqcbDao.getTbddById(byqcbzxdd.getTbcpbh());
			xmxx = xmxxDao.getXmxxByBh(tbdd.getXmxx());
			
			Double zccb = byqcbzxdd.getGgyl() * byqcbzxdd.getGgdj()	+ 
					byqcbzxdd.getDjtyl() * byqcbzxdd.getDjtdj() + 
					byqcbzxdd.getByqyyl() * byqcbzxdd.getByqydj() + 
					byqcbzxdd.getGcyl() * byqcbzxdd.getGcdj() + 
					byqcbzxdd.getZbyl() * byqcbzxdd.getZbdj();// 投标五大主材成本
			Double clzcb = (zccb + byqcbzxdd.getQtclcb()) / 1.17;// 材料合计
			Double sczcb = clzcb + byqcbzxdd.getRgjzzfy();// 生产总成本
			
			fillZxmx(zxmx, i, xmxx, byqcbzxdd, tbdd, zccb, clzcb, sczcb);
			
			
//			comp = org.getCompany(Integer.valueOf(xmxx.getDdszdw()));
//			if (comp != null) {
//
//				if (comp.getParentCompany() != null) {
//					comp = comp.getParentCompany();
//				}
//
//				fillzxqk(jtzx, gsMap.get(comp.getType()), byqcbzxdd, sczcb);
//
//				if (null != byqcbzxdd.getHtzbsj()
//						&& byqcbzxdd.getHtzbsj().before(date)
//						&& byqcbzxdd.getHtzbsj().after(firstMonth.getTime())) {
//					tmpCal.setTime(byqcbzxdd.getHtzbsj());
//					fillzxqk(jtzx, tmpCal.get(Calendar.MONTH), byqcbzxdd, sczcb);
//				}
//			}
			
		}
		return rets;
	}

	private void fillzxqk(String[][] jtzx, Integer row,
			CBBYQZXDD byqcbzxdd, Double sczcb) {

//		jtzx[row][0]	 = valueOf(jtzx[row][0]) + byqcbzxdd.getCz() + "";
//			jtzx[row][1]	 = valueOf(jtzx[row][0]) + byqcbzxdd.getCz() - sczcb + "";
//			jtzx[row][2]	 = valueOf(jtzx[row][1]) / valueOf(jtzx[row][0]) + "";
//			jtzx[row][3] = valueOf(jtzx[row][3]) + byqcbzxdd.getGgdj() + "";
//			jtzx[row][4] = valueOf(jtzx[row][4]) + byqcbzxdd.getGgyl() + "";
//			jtzx[row][5] = valueOf(jtzx[row][5]) + byqcbzxdd.getDjtdj() + "";
//			jtzx[row][6] = valueOf(jtzx[row][6]) + byqcbzxdd.getDjtyl() + "";
//			jtzx[row][7] = "0";//电解铜费用
//			jtzx[row][8] = valueOf(jtzx[row][8]) + byqcbzxdd.getZbdj() + "";
//			jtzx[row][9] = valueOf(jtzx[row][9]) + byqcbzxdd.getZbyl() + "";
//			jtzx[row][10] = valueOf(jtzx[row][10]) + byqcbzxdd.getByqydj() + "";
//			jtzx[row][11] = valueOf(jtzx[row][11]) + byqcbzxdd.getByqyyl() + "";
//			jtzx[row][12] = valueOf(jtzx[row][12]) + byqcbzxdd.getGcdj() + "";
//			jtzx[row][13] = valueOf(jtzx[row][13]) + byqcbzxdd.getGcyl() + "";


	}
	
//	@Override
//	public String[][] getJttb(Date date) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public String[][] getGstb(Date date) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
