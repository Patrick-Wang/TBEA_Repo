package com.tbea.ic.operation.model.dao.market.projectInfo;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.operation.common.companys.Company;
import com.tbea.ic.operation.model.entity.MktBidInfo;
import com.tbea.ic.operation.model.entity.MktProjectInfo;
import com.tbea.ic.operation.model.entity.MktSignContract;

@Repository
@Transactional("transactionManager")
public class MktProjectInfoDaoImpl implements MktProjectInfoDao {

	@PersistenceContext(unitName = "localDB")
	EntityManager manager;

	@Override
	public void update(MktProjectInfo mktObject) {
		manager.merge(mktObject);
	}

	@Override
	public List<MktProjectInfo> getData(String companyName) {
		Query q;
		if (companyName.equals("股份公司")) {
			q = manager.createQuery("from MktProjectInfo order by startdate desc");
		} else {
			q = manager
					.createQuery("from MktProjectInfo where company_name like:comp order by startdate desc");
			q.setParameter("comp", "%" + companyName + "%");
		}
		return q.getResultList();
	}

	@Override
	public MktProjectInfo getById(String projectNo) {
		Query q = manager
				.createQuery("from MktProjectInfo where projectNo = :projectNo");
		q.setParameter("projectNo", projectNo);
		List<MktProjectInfo> mbis = q.getResultList();
		if (mbis.isEmpty()) {
			return null;
		}
		return mbis.get(0);
	}

	@Override
	public List<MktProjectInfo> getCarryDownProjectInfo(Date dStart, Date dEnd) {
		Query q = manager
				.createQuery("from MktProjectInfo where enddate >= :start and enddate <= :end and (bidRestrict is null or bidRestrict not in ('已投标', '已报价', '放弃跟踪', '弃标', '项目重复'))");
		q.setParameter("start", dStart);
		q.setParameter("end", dEnd);
		return q.getResultList();
	}

	@Override
	public List<MktProjectInfo> getData(String companyName, Integer year) {
		Query q;
		if (companyName.equals("股份公司")) {
			q = manager
					.createQuery("from MktProjectInfo where Year(startdate) <= :year and Year(enddate) >= :year "
							+ "order by project_no desc");
		} else {
			q = manager
					.createQuery("from MktProjectInfo where Year(startdate) <= :year and Year(enddate) >= :year "
							+ "and company_name like:comp order by project_no desc");
			q.setParameter("comp", "%" + companyName + "%");
		}
		q.setParameter("year", year);

		return q.getResultList();
	}

	@Override
	public void remove(String key) {
		MktProjectInfo info = this.getById(key);
		if (null != info) {
			manager.remove(info);
		}
	}
}
