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

@Service
@Transactional("transactionManager_psn")
public class PersonServiceImpl implements PersonService{	
	
	@Autowired
	PsnDao psnDao;
		
	public int queryPersonInfoPagesCount(){
		return psnDao.getPsnPagesCount();
	}

	public List<Psn> queryPersonInfo(int pageIndex){
		return psnDao.getPsns(pageIndex);
	}

	public List<Psn> queryPersonInfoById(String id){
		return psnDao.getPsnsById(id);
	}

	public String queryPersonNoById(String id){
		return psnDao.getPsnNoByID(id);
	}
}
