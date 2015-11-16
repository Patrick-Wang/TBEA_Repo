package com.tbea.ic.carrier.service.nacao;

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

import com.tbea.ic.carrier.model.dao.keywords.KeyWordsDao;
import com.tbea.ic.carrier.model.dao.org.OrganizationDao;
import com.tbea.ic.carrier.model.entity.KeyWords;
import com.tbea.ic.carrier.model.entity.Organization;

@Service
@Transactional("transactionManager")
public class NacaoServiceImpl implements NacaoService{	
	
	@Autowired
	OrganizationDao orgDao;
	
	@Autowired
	KeyWordsDao keywordsDao;
		

	private JSONArray downloadCompanyInfo(WebDriver driver, String companyName){
		companyName = companyName.replace("\"", "\\\\\\\"").replace("\'", "\\\\\\\'");
		String js = "function searchCompany(companyName){" +
			"var formMap = JSON.parse(\'{\"firststrfind\":\"jgmc=\\'\' + companyName + \'\\'  not ZYBZ=(\\'2\\') \",\"strfind\":\"jgmc=\\'\' + companyName + \'\\'  not ZYBZ=(\\'2\\') \",\"key\":\"\' + companyName + \'\",\"kind\":\"2\",\"tit1\":\"\' + companyName + \'\",\"selecttags\":\"鍏ㄥ浗\",\"xzqhName\":\"alll\",\"button\":\"\",\"jgdm\":false,\"jgmc\":true,\"jgdz\":false,\"zch\":false,\"strJgmc\":\"\",\"\":\"\",\"secondSelectFlag\":\"\"}\');"+
					"DWREngine._execute(\'/dwr\', \'ServiceForNum\', \'getData\', formMap, function(data){"+
						"$(\'#jsResultParent\').append(\"<div id=\'jsResult\'></div>\");" + 
						"$(\'#jsResult\').attr(\"result\", JSON.stringify(data));"+
					"});"+
				"}"+
				"if (document.getElementById(\"jsResultParent\") == null){" +
					"$(\'body\').append(\"<div id=\'jsResultParent\'></div>\");" +
				"}" +
				"$(\'#jsResultParent\').empty();"	+
				"searchCompany(\"" + companyName + "\");";
		JavascriptExecutor jse = (JavascriptExecutor)driver; 
		jse.executeScript(js);
		
		
		WebElement myResult = (new WebDriverWait(driver, 600)).until(
				new ExpectedCondition<WebElement>(){

					public WebElement apply(WebDriver d) {
						return d.findElement(By.id("jsResult"));  
					} 
				} 
		); 
		
		String ret = myResult.getAttribute("result");		
		return JSONArray.fromObject(ret).getJSONArray(1);
	}
	
	private void fetchCompany(WebDriver driver, List<KeyWords> keys){
		try{
			for (int i = 0; i < keys.size(); ++i ){
				KeyWords key = keys.get(i);
				String company = key.getText().trim();
				JSONArray jsonOrg = downloadCompanyInfo(driver, company);
				System.out.println(i + " " + company + " " + jsonOrg.size());
				for (int j = 0; j < jsonOrg.size(); ++j){
					Organization org = (Organization) JSONObject.toBean(jsonOrg.getJSONObject(j), Organization.class);
					orgDao.update(org);
				}
				keywordsDao.remove(key);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int fetchCompanyWithUnfixedKeywords(WebDriver driver, int start, int count) {
		List<KeyWords> keywords = keywordsDao.getUnfixedKeyWorks(start, count);
		if (!keywords.isEmpty()) {

			fetchCompany(driver, keywords);
		}
		return keywords.size();
	}

	public int fetchCompanyWithAllKeywords(WebDriver driver, int start, int count) {
		List<KeyWords> keywords = keywordsDao.getKeyWorks(start, count);
		if (!keywords.isEmpty()) {
			fetchCompany(driver, keywords);
		}
		return keywords.size();
	}

	public List<Organization> findByName(String name) {
		findByNameExactly(name);
		List<Organization> orgs = orgDao.getByName(name);
		return orgs;
	}

	public int getUnfixedKeywordsCount() {
		return keywordsDao.getUnfixedKeyWorksCount();
	}

	public int getKeywordsCount() {
		return keywordsDao.getKeyWorksCount();
	}

	public int getUnfoundKeywordsCount() {
		// TODO Auto-generated method stub
		return keywordsDao.getUnfoundKeyWorksCount();
	}

	public int fetchCompanyWithUnfoundKeywords(WebDriver driver, int start,
			int count) {
		List<KeyWords> keywords = keywordsDao.getUnfoundKeyWorks(start, count);
		if (!keywords.isEmpty()) {

			fetchCompany(driver, keywords);
		}
		return keywords.size();
	}

	public List<Organization> findByNameExactly(String compName) {		
		List<Organization> orgs = orgDao.getByNameExactly(compName);
		if (orgs.isEmpty()){
			KeyWords key = keywordsDao.getKeyWordsByKey(compName);
			if (null == key){
				key = new KeyWords();
				key.setText(compName);
				key.setFixed("N");
				keywordsDao.update(key);
			}
		}
		return orgs;
	}

}
