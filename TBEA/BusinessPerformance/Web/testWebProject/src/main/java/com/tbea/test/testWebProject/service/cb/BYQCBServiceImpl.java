package com.tbea.test.testWebProject.service.cb;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.test.testWebProject.model.dao.cb.BYQCBDao;
import com.tbea.test.testWebProject.model.dao.cb.XMXXDao;
import com.tbea.test.testWebProject.model.entity.CBBYQTBDD;
import com.tbea.test.testWebProject.model.entity.XMXX;

@Service
@Transactional("transactionManager")
public class BYQCBServiceImpl  implements BYQCBService{

	@Autowired
	private BYQCBDao byqcbDao;

	@Autowired
	private XMXXDao xmxxDao;

	@Override
	public String[][] getTbmx(Date date) {
		List<CBBYQTBDD> byqcbtbdds = byqcbDao.getTbdd();
		String[][] ret = new String[byqcbtbdds.size()][30];
		XMXX xmxx;
		CBBYQTBDD byqtbcb;
		for (int i = 0; i < byqcbtbdds.size(); ++i){
			byqtbcb = byqcbtbdds.get(i);
			xmxx = xmxxDao.getXmxxByBh(byqtbcb.getXmxx());

			Double zccb = byqtbcb.getGgyl() * byqtbcb.getGgyl()
					+ byqtbcb.getDjtyl() * byqtbcb.getDjtdj()
					+ byqtbcb.getByqyyl() * byqtbcb.getByqydj()
					+ byqtbcb.getGcyl() * byqtbcb.getGcdj() + byqtbcb.getZbyl()
					* byqtbcb.getZbdj();
			Double tbclzcb = zccb / 1.17 + byqtbcb.getQtclcb();
			Double tbzccb = tbclzcb + byqtbcb.getRgjzzfy();
			ret[i][0] = xmxx.getDdszdw();// 订单所在单位及项目公司//订单所在单位及项目公司
			ret[i][1] = byqtbcb.getTbbjsj() + "";// 投标报价时间//投标报价时间
			ret[i][2] = xmxx.getYhdwmc();// 用户单位名称//用户单位名称
			ret[i][3] = xmxx.getXmmc();// 项目名称//项目名称
			ret[i][4] = byqtbcb.getYjjhsj();// 预计交货时间//预计交货时间
			ret[i][5] = byqtbcb.getXh();// 型号//型号
			ret[i][6] = byqtbcb.getDy();// 电压//电压
			ret[i][7] = byqtbcb.getCl();// 产量//产量（万KVA）
			ret[i][8] = byqtbcb.getCz() + "";// 产值//产值
			ret[i][9] = byqtbcb.getYjkbsj();// 预计开标时间//预计开标时间
			ret[i][10] = byqtbcb.getYczbgl() + "";// 销售部门预测的中标概率//销售部门预测的中标概率
			ret[i][11] = byqtbcb.getGgph() + "";// 硅钢牌号//投标硅钢牌号
			ret[i][12] = byqtbcb.getGgyl() + "";// 硅钢用量（单台）//投标硅钢用量（单台）
			ret[i][13] = byqtbcb.getGgdj() + "";// 硅钢单价//投标硅钢单价
			ret[i][14] = byqtbcb.getDjtyl() + "";// 电解铜用量（单台）//投标电解铜用量（单台）
			ret[i][15] = byqtbcb.getDjtdj() + "";// 电解铜单价//投标电解铜单价
			ret[i][16] = byqtbcb.getByqyyl() + "";// 变压器油用量（单台）//投标变压器油用量（单台）
			ret[i][17] = byqtbcb.getByqydj() + "";// 变压器油单价//投标变压器油单价
			ret[i][18] = byqtbcb.getGcyl() + "";// 钢材用量（单台）//投标钢材用量（单台）
			ret[i][19] = byqtbcb.getGcdj() + "";// 钢材单价//投标钢材单价
			ret[i][20] = byqtbcb.getZbyl() + "";// 纸板用量（单台）//投标纸板用量（单台）
			ret[i][21] = byqtbcb.getZbdj() + "";// 纸板单价//投标纸板单价
			ret[i][22] = zccb + "";// 投标五大主材成本
			ret[i][23] = byqtbcb.getQtclcb() + "";// 投标其他材料成本//投标其他材料成本
			ret[i][24] = tbclzcb + "";// 投标材料成本总计
			ret[i][25] = byqtbcb.getRgjzzfy() + "";// 人工及制造费用//人工及制造费用
			ret[i][26] = tbzccb + "";// 投标制造成本
			ret[i][27] = byqtbcb.getYf() + "";// 运费//运费
			ret[i][28] = byqtbcb.getCz() - tbzccb + "";// 投标毛利（单台）
			ret[i][29] = (byqtbcb.getCz() - tbzccb) / byqtbcb.getCz() + ""; // /投标毛利率
		}

		return ret;
	}

	@Override
	public String[][] getJttb(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[][] getGstb(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

}
