package com.tbea.ic.operation.controller.servlet.convertor;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.controller.servlet.dashboard.SessionManager;
import com.tbea.ic.operation.service.convertor.ConvertorSevice;


@Controller
@RequestMapping(value = "convertor")
public class Convertor {
	
	@Autowired
	ConvertorSevice service;
	
	private byte[] log;
	
	private static String pathData = null;
	static{
		try {
			pathData = new URI(Convertor.class
					.getClassLoader().getResource("").getPath()).getPath().substring(1) + "META-INF/convertor/data/";
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "jh.do", method = RequestMethod.GET)
	public ModelAndView Ndjh(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("convertor", map);
	}
	
	@RequestMapping(value = "checkFiles.do", method = RequestMethod.GET)
	public @ResponseBody byte[]  checkFiles(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		File dataDir = new File(pathData + SessionManager.getAccount(request.getSession()).getName());
		if (!dataDir.exists()){
			dataDir.mkdirs();
		}
		File[] files = dataDir.listFiles();
		List<String> fileNames = new ArrayList<String>();
		for (File f : files){
			String name = f.getName();
			if(f.isFile() && name.length() > 4 && ".xls".equals(name.substring(name.length() - 4))){
				fileNames.add(name);
			}
		}
		
		String[] arr = new String[fileNames.size()];
		fileNames.toArray(arr);
		JSONArray ja = JSONArray.fromObject(arr);
		return ja.toString().getBytes("utf-8");
	}
		
	@RequestMapping(value = "log.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getLog(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		return log;
	}
	
	@RequestMapping(value = "ydjhconvert.do", method = RequestMethod.POST)
	public @ResponseBody byte[] convertYdjh(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String path = pathData + SessionManager.getAccount(request.getSession()).getName()+ "/";
		return log = service.convertYdjh(request, response, path).getBytes("utf-8");
	}
	
	@RequestMapping(value = "ydsjconvert.do", method = RequestMethod.POST)
	public @ResponseBody byte[] convertYdsj(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String path = pathData + SessionManager.getAccount(request.getSession()).getName()+ "/";
		return log = service.convertYdsj(request, response, path).getBytes("utf-8");
	}
	
	@RequestMapping(value = "ndjhconvert.do", method = RequestMethod.POST)
	public @ResponseBody byte[] convertNdjh(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String path = pathData + SessionManager.getAccount(request.getSession()).getName()+ "/";
		return log = service.convertNdjh(request, response, path).getBytes("utf-8");
	
	}
	
	@RequestMapping(value = "deletefile.do")
	public @ResponseBody String deletefile(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String path = pathData + SessionManager.getAccount(request.getSession()).getName()+ "/";
		String fileName = request.getParameter("filename");
		File f= new File(path + fileName);
		f.delete();
		return "";
	
	}
	
	@RequestMapping(value = "upload.do")
	public ModelAndView upload(HttpServletRequest request,
			HttpServletResponse response,
			 @RequestParam("upfile") CommonsMultipartFile file) throws IOException {
		File dataDir = new File(pathData + SessionManager.getAccount(request.getSession()).getName());
		if (!dataDir.exists()){
			dataDir.mkdirs();
		}
		
		if (!file.isEmpty()) {
			File f = new File(dataDir.getAbsoluteFile() + "/"
					+ file.getFileItem().getName());
			try {
				// FileUtils.copyInputStreamToFile()这个方法里对IO进行了自动操作，不需要额外的再去关闭IO流
				FileUtils.copyInputStreamToFile(file.getInputStream(), f);// 复制临时文件到指定目录下
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return new ModelAndView("redirect:/convertor/jh.do");
	}
}
