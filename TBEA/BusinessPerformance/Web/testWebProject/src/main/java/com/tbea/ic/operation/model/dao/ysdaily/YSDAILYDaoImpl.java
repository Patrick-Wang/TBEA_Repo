package com.tbea.ic.operation.model.dao.ysdaily;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cn.com.tbea.template.model.dao.AbstractReadWriteDaoImpl;
import com.tbea.ic.operation.model.entity.Yszkrb;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.jygk.DWXX;
import com.tbea.ic.operation.model.entity.jygk.YSDAILY;
import com.tbea.ic.operation.model.entity.jygk.YSDAILYPK;

@Repository
@Transactional("transactionManager")
public class YSDAILYDaoImpl extends AbstractReadWriteDaoImpl<Yszkrb> implements YSDAILYDao{

	@PersistenceContext(unitName = "localDB")
	EntityManager entityManager;

	@Override
	public YSDAILY getYsdaily(Date date, DWXX dwxx) {
		YSDAILYPK ysdaily = new YSDAILYPK();
		ysdaily.setDwxx(dwxx);
		ysdaily.setDate(date);
		Query q = entityManager.createQuery("from YSDAILY where key = :key");
		q.setParameter("key", ysdaily);
		List<YSDAILY> yspks = q.getResultList();
		if (!yspks.isEmpty()){
			return (YSDAILY)yspks.get(0);
		}
		return null;
	}

	/**
	 * 获取某一日中应该显示的日报信息(没有录入过的不算)
	 * @param date
	 * @param dwxx
	 * @return
	 */
	@Override
	public Yszkrb getYsdailyRBLy(Date date, DWXX dwxx) {
		Yszkrb ysdaily = new Yszkrb();
		String[] dateStrArr = date.toString().split("-");
		int lrnf = Integer.parseInt(dateStrArr[0]);
		int lryf = Integer.parseInt(dateStrArr[1]);
		int lrrq = Integer.parseInt(dateStrArr[2]);
		Query q = entityManager.createQuery("from Yszkrb where dwID = :dwID and lrnf = :lrnf and lryf = :lryf and lrrq = :lrrq");
		q.setParameter("dwID", dwxx.getId());
		q.setParameter("lrnf", lrnf);
		q.setParameter("lryf", lryf);
		q.setParameter("lrrq", lrrq);
		List<Yszkrb> yss = q.getResultList();
		if (!yss.isEmpty()){
			ysdaily = (Yszkrb)yss.get(0);
			return ysdaily;
		}else{
			Query q2 = entityManager.createQuery("from Yszkrb where dwID = :dwID and lrnf = :lrnf and lryf = :lryf");
			q2.setParameter("dwID", dwxx.getId());
			q2.setParameter("lrnf", lrnf);
			q2.setParameter("lryf", lryf);
			List<Yszkrb> yss2 = q2.getResultList();
			if(!yss2.isEmpty()){
				for(int i = 0; i < yss2.size(); i++){
					if(yss2.get(i).getYszkzb() != null){
						ysdaily.setYszkzb(yss2.get(i).getYszkzb());
					}
					if(yss2.get(i).getHkjh() != null){
						ysdaily.setHkjh(yss2.get(i).getHkjh());
					}
					if(yss2.get(i).getQbkx() != null){
						ysdaily.setQbkx(yss2.get(i).getQbkx());
					}
					if(yss2.get(i).getZqkx() != null){
						ysdaily.setZqkx(yss2.get(i).getZqkx());
					}
					if(yss2.get(i).getSyysye() != null){
						ysdaily.setSyysye(yss2.get(i).getSyysye());
					}
					break;
				}
				return ysdaily;
			}else{
				return null;
			}
		}
	}

	/**
	 * 根据年份、月份、日期获取应收账款日报信息
	 * @param date
	 * @param dwxx
	 * @return
	 */
	@Override
	public Yszkrb getYsdailyRB(Date date, DWXX dwxx) {
		Yszkrb ysdaily = new Yszkrb();
		String[] dateStrArr = date.toString().split("-");
		int lrnf = Integer.parseInt(dateStrArr[0]);
		int lryf = Integer.parseInt(dateStrArr[1]);
		int lrrq = Integer.parseInt(dateStrArr[2]);
		Query q = entityManager.createQuery("from Yszkrb where dwID = :dwID and lrnf = :lrnf and lryf = :lryf and lrrq = :lrrq");
		q.setParameter("dwID", dwxx.getId());
		q.setParameter("lrnf", lrnf);
		q.setParameter("lryf", lryf);
		q.setParameter("lrrq", lrrq);
		List<Yszkrb> yss = q.getResultList();
		if (!yss.isEmpty()){
			ysdaily = (Yszkrb)yss.get(0);
			return ysdaily;
		}
		return null;
	}

	/**
	 * 得到在某个月份的所有日报信息
	 * @param date
	 * @param dwxx
	 * @return
	 */
	public List<Yszkrb> getYszkrbCountByNfYf(Date date, DWXX dwxx){
		String[] dateStrArr = date.toString().split("-");
		int lrnf = Integer.parseInt(dateStrArr[0]);
		int lryf = Integer.parseInt(dateStrArr[1]);
		int lrrq = Integer.parseInt(dateStrArr[2]);
		Query q = entityManager.createQuery("from Yszkrb where dwID = :dwID and lrnf = :lrnf and lryf = :lryf");
		q.setParameter("dwID", dwxx.getId());
		q.setParameter("lrnf", lrnf);
		q.setParameter("lryf", lryf);
		List<Yszkrb> yss = q.getResultList();
		if(!yss.isEmpty()){
			return yss;
		}else{
			return null;
		}
	}

	@Override
	public void update(YSDAILY daily) {
		entityManager.merge(daily);		
	}

	@Override
	public Double getJzydyszkzmye(Date start, Date end, List<Company> companies) {
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(start);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);
		Query q = entityManager.createQuery("select sum(balanceAccount) from YSDAILY where " + 
		"key.date >= :dStart and key.date <= :dEnd and " +
		"key.dwxx.id in ("+ Util.toBMString(companies) +")");
		q.setParameter("dStart",start);
		q.setParameter("dEnd", end);
		return (Double) q.getSingleResult();
	}

	@Override
	public Double getZqbc(Date start, Date end, List<Company> companies) {
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(start);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);
		Query q = entityManager.createQuery("select sum(zqbcMoney) from YSDAILY where " + 
		"key.date >= :dStart and key.date <= :dEnd and " +
		"key.dwxx.id in ("+ Util.toBMString(companies) +")");
		q.setParameter("dStart",start);
		q.setParameter("dEnd", end);
		return (Double) q.getSingleResult();
	}

	@Override
	public Double getQbbc(Date start, Date end, List<Company> companies) {
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(start);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);
		Query q = entityManager.createQuery("select sum(qbbcMoney) from YSDAILY where " + 
		"key.date >= :dStart and key.date <= :dEnd and " +
		"key.dwxx.id in ("+ Util.toBMString(companies) +")");
		q.setParameter("dStart",start);
		q.setParameter("dEnd", end);
		return (Double) q.getSingleResult();
	}

	@Override
	public Double getYhkzkjyshkje(Date start, Date end, List<Company> companies) {
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(start);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);
		Query q = entityManager.createQuery("select sum(kjysWithdrawalToday) from YSDAILY where " + 
		"key.date >= :dStart and key.date <= :dEnd and " +
		"key.dwxx.id in ("+ Util.toBMString(companies) +")");
		q.setParameter("dStart",start);
		q.setParameter("dEnd", end);
		return (Double) q.getSingleResult();
	}

	@Override
	public Double getJrhk(Date start, Date end, List<Company> companies) {
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(start);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);
		Query q = entityManager.createQuery("select sum(withdrawalToday) from YSDAILY where " + 
		"key.date >= :dStart and key.date <= :dEnd and " +
		"key.dwxx.id in ("+ Util.toBMString(companies) +")");
		q.setParameter("dStart",start);
		q.setParameter("dEnd", end);
		return (Double) q.getSingleResult();
	}

	@Override
	public Double getGdwzxzdhkjh(Date start, Date end, List<Company> companies) {
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(start);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);
		Query q = entityManager.createQuery("select sum(withdrawalPlan) from YSDAILY where " + 
		"key.date >= :dStart and key.date <= :dEnd and " +
		"key.dwxx.id in ("+ Util.toBMString(companies) +")");
		q.setParameter("dStart",start);
		q.setParameter("dEnd", end);
		return (Double) q.getSingleResult();
	}

	@Override
	public Double getJtxdydzjhlzb(Date start, Date end, List<Company> companies) {
		Calendar calStart = Calendar.getInstance();
		calStart.setTime(start);
		Calendar calEnd = Calendar.getInstance();
		calEnd.setTime(end);
		Query q = entityManager.createQuery("select sum(withdrawalFundsTargetMonth) from YSDAILY where " +
		"key.date >= :dStart and key.date <= :dEnd and " +
		"key.dwxx.id in ("+ Util.toBMString(companies) +")");
		q.setParameter("dStart",start);
		q.setParameter("dEnd", end);
		return (Double) q.getSingleResult();
	}
	/*************************************以*****下*****是*****新*****的******************************************************/

	/**
	 * 获取应收账款指标 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	@Override
	public Double getYszkzb(Date lrDate, List<Company> companies) {
		List<Yszkrb> yss = getYszkrbMonthLrInfo(lrDate,companies);
		if(yss != null){
			if(!yss.isEmpty()) {
				Yszkrb ys = (Yszkrb) yss.get(0);
				return ys.getYszkzb();
			}
		}
		return null;
	}

	/**
	 * 获取回款计划 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	@Override
	public Double getHkjh(Date lrDate, List<Company> companies) {
		List<Yszkrb> yss = getYszkrbMonthLrInfo(lrDate,companies);
		if(yss != null){
			if(!yss.isEmpty()) {
				Yszkrb ys = (Yszkrb) yss.get(0);
				return ys.getHkjh();
			}
		}
		return null;
	}

	/**
	 * 获取确保款项 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	@Override
	public Double getQbkx(Date lrDate, List<Company> companies) {
		List<Yszkrb> yss = getYszkrbMonthLrInfo(lrDate,companies);
		if(yss != null){
			if(!yss.isEmpty()) {
				Yszkrb ys = (Yszkrb) yss.get(0);
				return ys.getQbkx();
			}
		}
		return null;
	}

	/**
	 * 获取争取款项 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	@Override
	public Double getZqkx(Date lrDate, List<Company> companies) {
		List<Yszkrb> yss = getYszkrbMonthLrInfo(lrDate,companies);
		if(yss != null){
			if(!yss.isEmpty()) {
				Yszkrb ys = (Yszkrb) yss.get(0);
				return ys.getZqkx();
			}
		}
		return null;
	}

	/**
	 * 获取上月应收余额 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	@Override
	public Double getSyysye(Date lrDate, List<Company> companies) {
		List<Yszkrb> yss = getYszkrbMonthLrInfo(lrDate,companies);
		if(yss != null){
			if(!yss.isEmpty()) {
				Yszkrb ys = (Yszkrb) yss.get(0);
				return ys.getSyysye();
			}
		}
		return null;
	}

	/**
	 * 获取今日新增应收账款 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	@Override
	public Double getJrxzyszk(Date lrDate, List<Company> companies) {
		List<Yszkrb> yss = getYszkrbDayLrInfo(lrDate,companies);
		if(yss != null){
			if(!yss.isEmpty()) {
				Yszkrb ys = (Yszkrb) yss.get(0);
				return ys.getJrxzyszk();
			}
		}
		return null;
	}

	/**
	 * 获取今日新增应收账款 ————新增私有，用来计算合计
	 * @param lrnf
	 * @param lryf
	 * @param lrrq
	 * @param companies
	 * @return
	 */
	private Double getJrxzyszkPrivate(int lrnf,int lryf,int lrrq,List<Company> companies) {
		String dwidArr = "(";
		if(companies.size() == 1){
			dwidArr = dwidArr + companies.get(0).getId() + ")";
		}else{
			for(int i = 0; i < companies.size(); i++){
				dwidArr = dwidArr + companies.get(i).getId() + ",";
				if(i == companies.size()-1){
					dwidArr = dwidArr + companies.get(i).getId() + ")";
				}
			}
		}
		Query q = entityManager.createQuery("from Yszkrb where lrnf = :lrnf and lryf = :lryf and lrrq = :lrrq and dwID in "+dwidArr+" group by dwID,id,lrnf,lryf,lrrq,yszkzb,hkjh,qbkx,zqkx,syysye,jrxzyszk,jrhk,ljkjyshk");
		q.setParameter("lrnf", lrnf);
		q.setParameter("lryf", lryf);
		q.setParameter("lrrq", lrrq);
		List<Yszkrb> yss = q.getResultList();
		if(yss != null){
			if(!yss.isEmpty()) {
				Yszkrb ys = (Yszkrb) yss.get(0);
				return ys.getJrxzyszk();
			}
		}
		return null;
	}

	/**
	 * 获取本月累计新增应收 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	@Override
	public Double getByljxzys(Date lrDate, List<Company> companies) {
		String[] dateStrArr = lrDate.toString().split("-");
		int lrnf = Integer.parseInt(dateStrArr[0]);
		int lryf = Integer.parseInt(dateStrArr[1]);
		int lrrq = Integer.parseInt(dateStrArr[2]);
		Double sumD = 0.0;
		for(int i = 1; i <= lrrq; i++){
			if(getJrxzyszkPrivate(lrnf, lryf, i, companies) != null){
				sumD = sumD + getJrxzyszkPrivate(lrnf, lryf, i, companies);
			}
		}
		if(sumD == 0.0){
			return null;
		}
		return sumD;
	}

	/**
	 * 获取今日回款 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	@Override
	public Double getJrhkNew(Date lrDate, List<Company> companies) {
		List<Yszkrb> yss = getYszkrbDayLrInfo(lrDate,companies);
		if(yss != null){
			if(!yss.isEmpty()){
				Yszkrb ys = (Yszkrb) yss.get(0);
				return ys.getJrhk();
			}
		}
		return null;
	}

	/**
	 * 获取今日回款 ————新增私有，用来计算合计
	 * @param lrnf
	 * @param lryf
	 * @param lrrq
	 * @param companies
	 * @return
	 */
	private Double getJrhkNewPrivate(int lrnf,int lryf,int lrrq,List<Company> companies) {
		String dwidArr = "(";
		if(companies.size() == 1){
			dwidArr = dwidArr + companies.get(0).getId() + ")";
		}else{
			for(int i = 0; i < companies.size(); i++){
				dwidArr = dwidArr + companies.get(i).getId() + ",";
				if(i == companies.size()-1){
					dwidArr = dwidArr + companies.get(i).getId() + ")";
				}
			}
		}
		Query q = entityManager.createQuery("from Yszkrb where lrnf = :lrnf and lryf = :lryf and lrrq = :lrrq and dwID in "+dwidArr+" group by dwID,id,lrnf,lryf,lrrq,yszkzb,hkjh,qbkx,zqkx,syysye,jrxzyszk,jrhk,ljkjyshk");
		q.setParameter("lrnf", lrnf);
		q.setParameter("lryf", lryf);
		q.setParameter("lrrq", lrrq);
		List<Yszkrb> yss = q.getResultList();
		if(yss != null){
			if(!yss.isEmpty()) {
				Yszkrb ys = (Yszkrb) yss.get(0);
				return ys.getJrhk();
			}
		}
		return null;
	}

	/**
	 * 获取累计回款 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	@Override
	public Double getLjhk(Date lrDate, List<Company> companies) {
		String[] dateStrArr = lrDate.toString().split("-");
		int lrnf = Integer.parseInt(dateStrArr[0]);
		int lryf = Integer.parseInt(dateStrArr[1]);
		int lrrq = Integer.parseInt(dateStrArr[2]);
		Double sumD = 0.0;
		for(int i = 1; i <= lrrq; i++){
			if(getJrhkNewPrivate(lrnf, lryf, i, companies) != null){
				sumD = sumD + getJrhkNewPrivate(lrnf, lryf, i, companies);
			}
		}
		if(sumD == 0.0){
			return null;
		}
		return sumD;
	}

	/**
	 * 获取回款完成率 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	@Override
	public Double getHkwcl(Date lrDate, List<Company> companies) {
		Double ljhk = getLjhk(lrDate, companies);
		Double hkjh = getHkjh(lrDate, companies);
		if(ljhk != null && hkjh != null){
			return ljhk/hkjh;
		}
		return null;
	}

	/**
	 * 获取累计可降应收回款 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	@Override
	public Double getLjkjyshk(Date lrDate, List<Company> companies) {
		List<Yszkrb> yss = getYszkrbDayLrInfo(lrDate,companies);
		if(yss != null){
			if(!yss.isEmpty()) {
				Yszkrb ys = (Yszkrb) yss.get(0);
				return ys.getLjkjyshk();
			}
		}
		return null;
	}

	/**
	 * 获取今日应收账款余额 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	@Override
	public Double getJryszkye(Date lrDate, List<Company> companies) {
		Double f = getSyysye(lrDate, companies)==null?0.0:getSyysye(lrDate, companies);
		Double h = getByljxzys(lrDate, companies)==null?0.0:getByljxzys(lrDate, companies);
		Double l = getLjkjyshk(lrDate, companies)==null?0.0:getLjkjyshk(lrDate, companies);
		if(f+h-l == 0.0){
			return null;
		}
		return f+h-l;
	}

	/**
	 * 获取应收账款完成率 ————新增
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	@Override
	public Double getYszkwcl(Date lrDate, List<Company> companies) {
		Double m = getJryszkye(lrDate, companies);
		Double c = getYszkzb(lrDate, companies);
		if(c == null){
			return null;
		}
		return m/c;
	}

	/**
	 * 得到应收账款日报中的月录入项集合
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	private List<Yszkrb> getYszkrbMonthLrInfo(Date lrDate, List<Company> companies){
		String[] dateStrArr = lrDate.toString().split("-");
		int lrnf = Integer.parseInt(dateStrArr[0]);
		int lryf = Integer.parseInt(dateStrArr[1]);
		String dwidArr = "(";
		if(companies.size() == 1){
			dwidArr = dwidArr + companies.get(0).getId() + ")";
		}else{
			for(int i = 0; i < companies.size(); i++){
				dwidArr = dwidArr + companies.get(i).getId() + ",";
				if(i == companies.size()-1){
					dwidArr = dwidArr + companies.get(i).getId() + ")";
				}
			}
		}
		Query q = entityManager.createQuery("from Yszkrb where lrnf = :lrnf and lryf = :lryf and dwID in "+dwidArr+" group by dwID,id,lrnf,lryf,lrrq,yszkzb,hkjh,qbkx,zqkx,syysye,jrxzyszk,jrhk,ljkjyshk");
		q.setParameter("lrnf", lrnf);
		q.setParameter("lryf", lryf);
		List<Yszkrb> yss = q.getResultList();
		if(yss != null){
			return yss;
		}
		return null;
	}

	/**
	 * 得到应收账款日报中的日录入项集合
	 * @param lrDate
	 * @param companies
	 * @return
	 */
	private List<Yszkrb> getYszkrbDayLrInfo(Date lrDate, List<Company> companies){
		String[] dateStrArr = lrDate.toString().split("-");
		int lrnf = Integer.parseInt(dateStrArr[0]);
		int lryf = Integer.parseInt(dateStrArr[1]);
		int lrrq = Integer.parseInt(dateStrArr[2]);
		String dwidArr = "(";
		if(companies.size() == 1){
			dwidArr = dwidArr + companies.get(0).getId() + ")";
		}else{
			for(int i = 0; i < companies.size(); i++){
				dwidArr = dwidArr + companies.get(i).getId() + ",";
				if(i == companies.size()-1){
					dwidArr = dwidArr + companies.get(i).getId() + ")";
				}
			}
		}
		Query q = entityManager.createQuery("from Yszkrb where lrnf = :lrnf and lryf = :lryf and lrrq = :lrrq and dwID in "+dwidArr+" group by dwID,id,lrnf,lryf,lrrq,yszkzb,hkjh,qbkx,zqkx,syysye,jrxzyszk,jrhk,ljkjyshk");
		q.setParameter("lrnf", lrnf);
		q.setParameter("lryf", lryf);
		q.setParameter("lrrq", lrrq);
		List<Yszkrb> yss = q.getResultList();
		if(yss != null){
			return yss;
		}
		return null;
	}
}
