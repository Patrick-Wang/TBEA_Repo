package com.tbea.ic.operation.controller.servlet.convertor;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.service.convertor.ConvertorSevice;


@Controller
@RequestMapping(value = "convertor")
public class Convertor {
	
	@Autowired
	ConvertorSevice service;
	
	private byte[] log;
	
	@RequestMapping(value = "jh.do", method = RequestMethod.GET)
	public ModelAndView Ndjh(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		return new ModelAndView("convertor", map);
	
	}
		
	@RequestMapping(value = "log.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getLog(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		return log;
	}
	
	@RequestMapping(value = "ydjhconvert.do", method = RequestMethod.POST)
	public @ResponseBody byte[] convertYdjh(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		return log = service.convertYdjh(request, response).getBytes("utf-8");
	}
	
	@RequestMapping(value = "ydsjconvert.do", method = RequestMethod.POST)
	public @ResponseBody byte[] convertYdsj(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		return log = service.convertYdsj(request, response).getBytes("utf-8");
	}
	
	@RequestMapping(value = "ndjhconvert.do", method = RequestMethod.POST)
	public @ResponseBody byte[] convertNdjh(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
//		//定义上载文件的最大字节
//		int MAX_SIZE = 102400 * 102400;
//		// 创建根路径的保存变量
//		String rootPath;
//		//声明文件读入类
//		DataInputStream in = null;
//		//取得客户端的网络地址
//		String remoteAddr = request.getRemoteAddr();
//		//获得服务器的名字
//		String serverName = request.getServerName();
//
//		//取得互联网程序的绝对地址
////		String realPath = request.getRealPath(serverName);
////		realPath = realPath.substring(0,realPath.lastIndexOf("\\"));
////		//创建文件的保存目录
////		rootPath = realPath + "\\upload\\";
//		//取得客户端上传的数据类型
//		String contentType = request.getContentType();
//
//		//读入上传的数据
//		in = new DataInputStream(request.getInputStream());
//		int formDataLength = request.getContentLength();
//		//保存上传文件的数据
//		byte dataBytes[] = new byte[formDataLength];
//		int byteRead = 0;
//		int totalBytesRead = 0;
//		//上传的数据保存在byte数组
//		while(totalBytesRead < formDataLength){
//			byteRead = in.read(dataBytes,totalBytesRead,formDataLength);
//			totalBytesRead += byteRead;
//		}
//		//根据byte数组创建字符串
//		String file = new String(dataBytes);
//		//out.println(file);
//		//取得上传的数据的文件名
//		String saveFile = file.substring(file.indexOf("filename=\"") + 10);
//		saveFile = saveFile.substring(0,saveFile.indexOf("\n"));
//		saveFile = saveFile.substring(saveFile.lastIndexOf("\\") + 1,saveFile.indexOf("\""));
//		int lastIndex = contentType.lastIndexOf("=");
//		//取得数据的分隔字符串
//		String boundary = contentType.substring(lastIndex + 1,contentType.length());
//		//创建保存路径的文件名
//		//String fileName = rootPath + saveFile;
//		//out.print(fileName);
//		int pos;
//		pos = file.indexOf("filename=\"");
//		pos = file.indexOf("\n",pos) + 1;
//		pos = file.indexOf("\n",pos) + 1;
//		pos = file.indexOf("\n",pos) + 1;
//		int boundaryLocation = file.indexOf(boundary,pos) - 4;
		return log = service.convertNdjh(request, response).getBytes("utf-8");
	
	}
}
