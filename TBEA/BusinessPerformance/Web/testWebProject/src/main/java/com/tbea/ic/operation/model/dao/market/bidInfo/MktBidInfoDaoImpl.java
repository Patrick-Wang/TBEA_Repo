package com.tbea.ic.operation.model.dao.market.bidInfo;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.MktBidInfo;

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
		if(companyName.equals("全部公司"))
		{
			q = manager.createQuery(
					"from MktBidInfo");
		}else{
			q = manager.createQuery(
					"from MktBidInfo where company_name like:comp order by bid_no desc");
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
		if(companyName.equals("全部公司"))
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

}
