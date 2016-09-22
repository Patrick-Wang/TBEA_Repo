package com.tbea.ic.carrier.service.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.carrier.model.dao.psn.PsnDao;
import com.tbea.ic.carrier.model.dao.psn.PsnJTDao;
import com.tbea.ic.carrier.model.dao.psn.PsnZHDao;
import com.tbea.ic.carrier.model.entity.Psn;

@Service
@Transactional("transactionManager_psn")
public class PersonServiceImpl implements PersonService{	
	
	@Autowired
	PsnDao psnDao;
	
	@Autowired
	PsnJTDao psnJTDao;
	
	@Autowired
	PsnZHDao psnZHDao;
	
	int m_pageCountPsn = 0;
	int m_pageCountPsnJT = 0;
	int m_pageCountPsnZH = 0;
		
	public int queryPersonInfoPagesCount(){
		this.m_pageCountPsn = psnDao.getPsnPagesCount();
		this.m_pageCountPsnJT = psnJTDao.getPsnPagesCount();
		this.m_pageCountPsnZH = psnZHDao.getPsnPagesCount();

		return (m_pageCountPsn + m_pageCountPsnJT + m_pageCountPsnZH);
	}

	public List<Psn> queryPersonInfo(int pageIndex) {

		List<Psn> result;
		if (pageIndex <= m_pageCountPsn) {
			result = psnDao.getPsns(pageIndex);
		} else if (pageIndex <= m_pageCountPsn + m_pageCountPsnJT) {
			result = psnJTDao.getPsns(pageIndex - m_pageCountPsn);
		} else if (pageIndex <= m_pageCountPsn + m_pageCountPsnJT + m_pageCountPsnZH) {
			result = psnZHDao.getPsns(pageIndex - m_pageCountPsn - m_pageCountPsnJT);
		} else {
			result = null;
		}

		return result;
	}

	public List<Psn> queryPersonInfoById(String id) {

		List<Psn> result;

		result = psnDao.getPsnsById(id);

		if (result == null || result.isEmpty()) {
			result = psnJTDao.getPsnsById(id);
			
			if (result == null || result.isEmpty()) {
				result = psnZHDao.getPsnsById(id);
			}
		}

		return result;
	}

	public String queryPersonNoById(String id) {

		String result = "";

		result = psnDao.getPsnNoByID(id);

		if (result == null || result.isEmpty()) {

			result = psnJTDao.getPsnNoByID(id);
			
			if (result == null || result.isEmpty()) {

				result = psnZHDao.getPsnNoByID(id);
			}
		}

		return result;
	}
	
	public String queryPersonSSOById(String id) {

		String result = "";

		result = psnDao.getPsnSSOByID(id);

		if (result == null ||result.isEmpty()) {

			result = psnJTDao.getPsnSSOByID(id);
			
			if (result == null ||result.isEmpty()) {

				result = psnZHDao.getPsnSSOByID(id);
			}
		}

		return result;
	}
}
