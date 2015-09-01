package com.tbea.ic.carrier.service.nacao;

import java.net.URI;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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

	private static String CHROME_DRIVER_PATH = null;
	static 
	{
		try {
			String basePath = new URI(NacaoServiceImpl.class
					.getClassLoader().getResource("").getPath()).getPath();
			CHROME_DRIVER_PATH = basePath.substring(1) + "exe/chromedriver.exe";
			System.out.println(CHROME_DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.setProperty("webdriver.chrome.driver",  CHROME_DRIVER_PATH);
	}
	
	
	
	@Autowired
	OrganizationDao orgDao;
	
	@Autowired
	KeyWordsDao keywordsDao;
	
	private final String NACAO_URL = "https://s.nacao.org.cn/specialResult.html?x=8XJ1/pI26t/R86rKz7VspQ==&k=uvGb鈥�&s=4MIKGgKAicefN4zsiKPq6pMHFkYrvl5YLyz5/H5t4IdP&y=ORt7YhA+Tb9dFmARh/6Dqw==";
	

	private JSONArray downloadCompanyInfo(WebDriver driver, String companyName){
		WebElement element =driver.findElement(By.tagName("body"));
		String js = "function searchCompany(companyName){" +
			"var formMap = JSON.parse(\'{\"firststrfind\":\"jgmc=\\'\' + companyName + \'\\'  not ZYBZ=(\\'2\\') \",\"strfind\":\"jgmc=\\'\' + companyName + \'\\'  not ZYBZ=(\\'2\\') \",\"key\":\"\' + companyName + \'\",\"kind\":\"2\",\"tit1\":\"\' + companyName + \'\",\"selecttags\":\"鍏ㄥ浗\",\"xzqhName\":\"alll\",\"button\":\"\",\"jgdm\":false,\"jgmc\":true,\"jgdz\":false,\"zch\":false,\"strJgmc\":\"\",\"\":\"\",\"secondSelectFlag\":\"\"}\');"+
					"DWREngine._execute(\'/dwr\', \'ServiceForNum\', \'getData\', formMap, function(data){"+
						"$(\'body\').append(\"<div id=\'jsResultParent\'><div id=\'jsResult\'></div></div>\");" + 
						"$(\'#jsResult\').attr(\"result\", JSON.stringify(data));"+
					"});"+
				"}"+
				"$(\'#jsResultParent\').empty()"	+
				"searchCompany(\"" + companyName + "\");";
		JavascriptExecutor jse = (JavascriptExecutor)driver; 
		jse.executeScript(js);
		
		
		WebElement myResult = (new WebDriverWait(driver, 10)).until(
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
			for (KeyWords key : keys){
				JSONArray jsonOrg = downloadCompanyInfo(driver, key.getText());
				System.out.println(key.getText());
				for (int i = 0; i < jsonOrg.size(); ++i){
					Organization org = (Organization) JSONObject.toBean(jsonOrg.getJSONObject(i), Organization.class);
					orgDao.update(org);
				}
				
				if (!"Y".equals(key.getFixed())){
					key.setFixed("Y");
				}
				key.setCount(jsonOrg.size());
				keywordsDao.update(key);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void fetchCompanyWithUnfixedKeywords() {
		List<KeyWords> keywords = keywordsDao.getUnfixedKeyWorks();
		if (!keywords.isEmpty()) {
			WebDriver driver = new ChromeDriver();
			driver.get(NACAO_URL);
			fetchCompany(driver, keywords);
			driver.quit();
		}
	}

	public void fetchCompanyWithAllKeywords() {
		List<KeyWords> keywords = keywordsDao.getKeyWorks();
		if (!keywords.isEmpty()) {
			WebDriver driver = new ChromeDriver();
			driver.get(NACAO_URL);
			fetchCompany(driver, keywords);
			driver.quit();
		}
	}

	public JSONArray findByName(String name) {
		KeyWords key = keywordsDao.getKeyWordsByKey(name);
		if (null == key){
			key = new KeyWords();
			key.setText(name);
			key.setFixed("N");
			keywordsDao.update(key);
		}
		
		List<Organization> orgs = orgDao.getByName(name);
		return JSONArray.fromObject(orgs);
	}

}
