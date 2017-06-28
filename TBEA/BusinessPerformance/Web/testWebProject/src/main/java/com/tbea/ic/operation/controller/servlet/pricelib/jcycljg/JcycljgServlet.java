package com.tbea.ic.operation.controller.servlet.pricelib.jcycljg;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Url;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgService;
import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgServiceImpl;
import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgType;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.validation.ValidationException;

@Controller
@RequestMapping(value = "jcycljg")
public class JcycljgServlet {
	
	@Resource(name=JcycljgServiceImpl.NAME)
	JcycljgService jcycljgService;
	
	@RequestMapping(value = {"show.do", "v2/show.do"}, method = RequestMethod.GET)
	public ModelAndView show(HttpServletRequest request,
			HttpServletResponse response) {

		Map<String, Object> map = new HashMap<String, Object>();
		DateSelection dateSel = new DateSelection(Calendar.getInstance(),
				true, true);
		dateSel.select(map);
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "priceLib/jcycljg/jcycljg", map);
	}
	
	@RequestMapping(value = "update.do")
	public @ResponseBody byte[] ysjsUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		int type = Integer.valueOf(request.getParameter("type")).intValue();
		List<List<String>> result = jcycljgService.getValues(JcycljgType.valueOf(type), Date.valueOf(start), Date.valueOf(end));
		return JSONArray.fromObject(result).toString().getBytes("utf-8");
	}
	
	
	@RequestMapping(value = {"import/show.do", "v2/import/show.do"}, method = RequestMethod.GET)
	public ModelAndView importShow(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/" : "") + "priceLib/jcycljg/jcycljg_import_data");
	}
	
	@RequestMapping(value = "import.do")
	public @ResponseBody byte[] importExcel(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("upfile") CommonsMultipartFile file)
			throws IOException {

		int type = Integer.valueOf(request.getParameter("filetype")).intValue();
		String msg = "导入失败";
		ErrorCode ec = ErrorCode.PRICELIB_JCYCLJG_IMPORT_ERROR;
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
			jcycljgService.importExcel(JcycljgType.valueOf(type - 2), workbook);
			ec = ErrorCode.OK;
			msg = "导入成功!";
		} catch (ValidationException e) {
			msg = e.getMessage();
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}

		return Util.response(ec, msg);
	}
}
