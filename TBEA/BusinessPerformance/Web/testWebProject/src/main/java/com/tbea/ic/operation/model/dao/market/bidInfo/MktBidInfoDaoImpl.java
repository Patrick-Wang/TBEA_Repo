package com.tbea.ic.operation.model.dao.market.bidInfo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.MktBidInfo;
import com.tbea.ic.operation.service.market.pipe.MarketUnit;
import com.tbea.ic.operation.service.market.pipe.MarketUnit.Type;

@Repository
@Transactional("transactionManager")
public class MktBidInfoDaoImpl implements MktBidInfoDao {

	@PersistenceContext(unitName = "localDB")
	EntityManager manager;

	@Override
	public void update(MktBidInfo mktBidInfo) {
		manager.merge(mktBidInfo);
	}
	
	@Override
	public List<MktBidInfo> getData(String companyName) {
		Query q;
		if(companyName.equals("股份公司"))
		{
			q = manager.createQuery(
					"from MktBidInfo order by startdate desc");
		}else{
			q = manager.createQuery(
					"from MktBidInfo where company_name like:comp order by startdate desc");
			q.setParameter("comp", "%"+companyName+"%");
		}		
		
		return q.getResultList();
	}

	@Override
	public MktBidInfo getById(String bidNo) {
		Query q = manager.createQuery(
				"from MktBidInfo where bidNo = :bidNo");
		q.setParameter("bidNo", bidNo);
		List<MktBidInfo> mbis = q.getResultList();
		if (mbis.isEmpty()){
			return null;
		}
		return mbis.get(0);
	}

	@Override
	public List<MktBidInfo> getUndecidedBidInfo(Date dStart, Date dEnd) {
		Query q = manager.createQuery(
				"from MktBidInfo where enddate >= :start and enddate <= :end and bidStatus='未定标'");
		q.setParameter("start", dStart);
		q.setParameter("end", dEnd);
		return q.getResultList();
	}

	@Override
	public List<MktBidInfo> getData(String companyName, Integer year) {
		Query q;
		if(companyName.equals("股份公司"))
		{
			q = manager.createQuery(
					"from MktBidInfo where Year(startdate) <= :year and Year(enddate) >= :year "
					+ "order by bid_no desc");
		}else{
			q = manager.createQuery(
					"from MktBidInfo where Year(startdate) <= :year and Year(enddate) >= :year "
					+ "and company_name like:comp order by bid_no desc");
			q.setParameter("comp", "%"+companyName+"%");
		}		
		q.setParameter("year", year);
		
		return q.getResultList();
	}

	@Override
	public void remove(String key) {
		MktBidInfo info = this.getById(key);
		if (null != info){
			manager.remove(info);
		}
	}

	@Override
	public List<MktBidInfo> getIndustryData(Date start, Date end, MarketUnit company,
			List<MarketUnit> companies) {
		Query q = manager.createQuery(
				"from MktBidInfo where companyName=:compName and datediff(mm, :start, bidDate) >= 0 and datediff(mm, bidDate, :end) >= 0 and industryCategory in (" + Util.toNameString((List)companies) + ") ");
		q.setParameter("compName", company.getName());
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}

	@Override
	public List<MarketUnit> getIndustries(MarketUnit mu) {
		Query q = manager.createQuery(
				"select m.industryCategory from MktBidInfo m where m.companyName = :compName group by m.industryCategory");
		q.setParameter("compName", mu.getName());
		List<String> industries = q.getResultList();
		List<MarketUnit> mus = new ArrayList<MarketUnit>();
		for (String industry : industries){
			mus.add(new MarketUnit(industry, Type.INDUSTRY));
		}
		return mus;
	}

	@Override
	public List<MktBidInfo> getCompanyData(Date start, Date end,
			MarketUnit pMku, List companies) {
		Query q = manager.createQuery(
				"from MktBidInfo where companyName=:compName and datediff(mm, :start, bidDate) >= 0 and datediff(mm, bidDate, :end) >= 0 and office_name in (" + Util.toNameString((List)companies) + ") ");
		q.setParameter("compName", pMku.getName());
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}

	@Override
	public List<MarketUnit> getCompanies(MarketUnit mu) {
		Query q = manager.createQuery(
				"select m.officeName from MktBidInfo m where m.companyName = :compName group by m.officeName");
		q.setParameter("compName", mu.getName());
		List<String> industries = q.getResultList();
		List<MarketUnit> mus = new ArrayList<MarketUnit>();
		for (String industry : industries){
			mus.add(new MarketUnit(industry, Type.COMPANY));
		}
		return mus;
	}

}
