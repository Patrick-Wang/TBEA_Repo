package com.tbea.ic.operation.model.dao.market.signContract;

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
import com.tbea.ic.operation.model.dao.market.signContract.MktSignContractDao;
import com.tbea.ic.operation.model.entity.MktBidInfo;
import com.tbea.ic.operation.model.entity.MktProjectInfo;
import com.tbea.ic.operation.model.entity.MktSignContract;
import com.tbea.ic.operation.service.market.pipe.MarketUnit;
import com.tbea.ic.operation.service.market.pipe.MarketUnit.Type;

@Repository
@Transactional("transactionManager")
public class MktSignContractDaoImpl implements MktSignContractDao {

	@PersistenceContext(unitName = "localDB")
	EntityManager manager;

	@Override
	public void update(MktSignContract mktObject) {
		manager.merge(mktObject);	
	}
	
	@Override
	public List<MktSignContract> getData(String companyName) {
		Query q;
		if(companyName.equals("股份公司"))
		{
			q = manager.createQuery(
					"from MktSignContract order by startdate desc");
		}else{
			q = manager.createQuery(
					"from MktSignContract where company_name like :comp order by startdate desc");
			q.setParameter("comp", "%"+companyName+"%");
		}		
		return q.getResultList();
	}

	@Override
	public MktSignContract getById(String contractNo) {
		Query q = manager.createQuery(
				"from MktSignContract where contractNo = :contractNo");
		q.setParameter("contractNo", contractNo);
		List<MktSignContract> mbis = q.getResultList();
		if (mbis.isEmpty()){
			return null;
		}
		return mbis.get(0);
	}

	@Override
	public void remove(String key) {
		MktSignContract info = this.getById(key);
		if (null != info){
			manager.remove(info);
		}
	}

	@Override
	public List<MktSignContract> getData(Date start, Date end,
			List<MarketUnit> companies) {
		Query q = manager.createQuery(
				"from MktSignContract where enddate >= :start and enddate <= :end and companyName in (" + Util.toNameString((List)companies) + ") ");
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}

	@Override
	public List<MktSignContract> getIndustryData(Date start, Date end,
			MarketUnit mu, List<MarketUnit> mus) {
		Query q = manager.createQuery(
				"from MktSignContract where companyName=:compName and datediff(mm, :start, signDate) >= 0 and datediff(mm, signDate, :end) >= 0 and industryCategory in (" + Util.toNameString((List)mus) + ") ");
		q.setParameter("compName", mu.getName());
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}

	@Override
	public List<MktSignContract> getCompanyData(Date start, Date end,
			MarketUnit mu, List<MarketUnit> mus) {
		Query q = manager.createQuery(
				"from MktSignContract where companyName=:compName and datediff(mm, :start, signDate) >= 0 and datediff(mm, signDate, :end) >= 0 and officeName in (" + Util.toNameString((List)mus) + ") ");
		q.setParameter("compName", mu.getName());
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}
	
	@Override
	public List<MarketUnit> getCompanies(MarketUnit mu) {
		Query q = manager.createQuery(
				"select m.officeName from MktSignContract m where m.companyName = :compName group by m.officeName");
		q.setParameter("compName", mu.getName());
		List<String> industries = q.getResultList();
		List<MarketUnit> mus = new ArrayList<MarketUnit>();
		for (String industry : industries){
			mus.add(new MarketUnit(industry, Type.COMPANY));
		}
		return mus;
	}
	
	@Override
	public List<MarketUnit> getIndustries(MarketUnit mu) {
		Query q = manager.createQuery(
				"select m.industryCategory from MktSignContract m where m.companyName = :compName group by m.industryCategory");
		q.setParameter("compName", mu.getName());
		List<String> industries = q.getResultList();
		List<MarketUnit> mus = new ArrayList<MarketUnit>();
		for (String industry : industries){
			mus.add(new MarketUnit(industry, Type.INDUSTRY));
		}
		return mus;
	}

	@Override
	public List<MarketUnit> getAreas(MarketUnit mu) {
		Query q = manager.createQuery(
				"select m.projectArea from MktSignContract m where m.companyName = :compName group by m.projectArea");
		q.setParameter("compName", mu.getName());
		List<String> industries = q.getResultList();
		List<MarketUnit> mus = new ArrayList<MarketUnit>();
		for (String industry : industries){
			mus.add(new MarketUnit(industry, Type.AREA));
		}
		return mus;
	}

	@Override
	public List<MktSignContract> getAreaData(Date start, Date end,
			MarketUnit mu, List<MarketUnit> mus) {
		Query q = manager.createQuery(
				"from MktSignContract where companyName=:compName and datediff(mm, :start, signDate) >= 0 and datediff(mm, signDate, :end) >= 0 and projectArea in (" + Util.toNameString((List)mus) + ") ");
		q.setParameter("compName", mu.getName());
		q.setParameter("start", start);
		q.setParameter("end", end);
		return q.getResultList();
	}
}
