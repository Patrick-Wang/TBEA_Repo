package com.tbea.ic.carrier.service.person;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tbea.ic.carrier.model.dao.psn.PsnDao;
import com.tbea.ic.carrier.model.entity.Psn;

import com.tbea.ic.carrier.model.dao.psn.PsnJTDao;
import com.tbea.ic.carrier.model.entity.PsnJT;

@Service
@Transactional("transactionManager_psn")
public class PersonServiceImpl implements PersonService{	
	
	@Autowired
	PsnDao psnDao;
	
	@Autowired
	PsnJTDao psnJTDao;
	
	int m_pageCountPsn = 0;
	int m_pageCountPsnJT = 0;
		
	public int queryPersonInfoPagesCount(){
		this.m_pageCountPsn = psnDao.getPsnPagesCount();
		this.m_pageCountPsnJT = psnJTDao.getPsnPagesCount();

		return (m_pageCountPsn + m_pageCountPsnJT);
	}

	public List<Psn> queryPersonInfo(int pageIndex) {

		List<Psn> result;
		if (pageIndex <= m_pageCountPsn) {
			result = psnDao.getPsns(pageIndex);
		} else if (pageIndex <= m_pageCountPsn + m_pageCountPsnJT) {
			result = psnJTDao.getPsns(pageIndex - m_pageCountPsn);
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
		}

		return result;
	}

	public String queryPersonNoById(String id) {

		String result = "";

		result = psnDao.getPsnNoByID(id);

		if (result == null || result.isEmpty()) {

			result = psnJTDao.getPsnNoByID(id);
		}

		return result;
	}
	
	public String queryPersonSSOById(String id) {

		String result = "";

		result = psnDao.getPsnSSOByID(id);

		if (result == null ||result.isEmpty()) {

			result = psnJTDao.getPsnSSOByID(id);
		}

		return result;
	}
}
