package com.tbea.ic.carrier.controller.servlet.nacao;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.carrier.model.entity.Organization;
import com.tbea.ic.carrier.service.nacao.NacaoService;


@Controller
@RequestMapping(value = "/nacao")
public class NacaoServlet {

	private static String CHROME_DRIVER_PATH = null;
	static 
	{
		try {
			String basePath = new URI(NacaoServlet.class
					.getClassLoader().getResource("").getPath()).getPath();
			CHROME_DRIVER_PATH = basePath.substring(1) + "exe/chromedriver.exe";
			System.out.println(CHROME_DRIVER_PATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.setProperty("webdriver.chrome.driver",  CHROME_DRIVER_PATH);
	}
	
	private final String NACAO_URL = "https://s.nacao.org.cn/specialResult.html?x=8XJ1/pI26t/R86rKz7VspQ==&k=uvGb鈥�&s=4MIKGgKAicefN4zsiKPq6pMHFkYrvl5YLyz5/H5t4IdP&y=ORt7YhA+Tb9dFmARh/6Dqw==";
	
	@Autowired
	NacaoService nacaoService;
	
	
//	@Scheduled(cron="0 30 8 ? * 2-6")
	public void carryUnfixed(){
		WebDriver driver = new ChromeDriver();
		driver.get(NACAO_URL);
		int size = nacaoService.getUnfixedKeywordsCount();
		int fetchedLength = 1;
		for (int start = 0; start < size && fetchedLength > 0; ){
			System.out.println("Unfixed fetchedLength " + fetchedLength);
			System.out.println("Unfixed size " + size + " start : " + start);
			fetchedLength = nacaoService.fetchCompanyWithUnfixedKeywords(driver, start, 100);
			start += fetchedLength;
		}
		driver.quit();
	}
	
	@Scheduled(cron="0 0 9 ? * 6")
	public void carrryAll(){
		WebDriver driver = new ChromeDriver();
		driver.get(NACAO_URL);
		int size = nacaoService.getKeywordsCount();
		int fetchedLength = 1;
		for (int start = 0; start < size && fetchedLength > 0; ){
			System.out.println("All fetchedLength " + fetchedLength);
			System.out.println("All size " + size + " start : " + start);
			fetchedLength = nacaoService.fetchCompanyWithAllKeywords(driver, start, 100);
			start += fetchedLength;
		}
		driver.quit();
	}
	
	@RequestMapping(value = "/search.do")
	public ModelAndView getSearchView(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		return new ModelAndView("searchView");
	}
		
	@RequestMapping(value = "/query_by_name.do")
	public @ResponseBody byte[] queryByName(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String name = request.getParameter("companyName");
		List<Organization> orgs = nacaoService.findByName(name);
		return JSONArray.fromObject(orgs).toString().getBytes("utf-8");
			
//		else{
//			return "{\"result\":\"该关键字尚未被收录，请明日查询\"}".getBytes("utf-8");
//		}
	}
	
	@RequestMapping(value = "/carry_unfixed.do")
	public void carrryUnfixed(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		carryUnfixed();
	}
	
//	@RequestMapping(value = "/carry_unfound.do")
//	public void carrryUnfound(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
//		WebDriver driver = new ChromeDriver();
//		driver.get(NACAO_URL);
//		int size = nacaoService.getUnfoundKeywordsCount();
//		int fetchedLength = 1;
//		for (int start = 0; start < size && fetchedLength > 0; ){
//			System.out.println("Unfound fetchedLength " + fetchedLength);
//			System.out.println("Unfound size " + size + " start : " + start);
//			fetchedLength = nacaoService.fetchCompanyWithUnfoundKeywords(driver, start, 100);
//			start += fetchedLength;
//		}
//		driver.quit();
//	}
	
	@RequestMapping(value = "/carry_all.do")
	public  void carrryAll(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		carrryAll();
	}
}
 