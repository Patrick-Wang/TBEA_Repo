package com.tbea.ic.operation.controller.servlet.pricelib.jcycljg;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.Data;
import com.tbea.ic.operation.common.DataNode;
import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.ErrorCode;
import com.tbea.ic.operation.common.Url;
import com.tbea.ic.operation.common.Util;
import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgService;
import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgServiceImpl;
import com.tbea.ic.operation.service.pricelib.jcycljg.JcycljgType;
import com.tbea.ic.operation.service.pricelib.jcycljg.storage.validation.ValidationException;

import net.sf.json.JSONArray;

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
		Map map = new HashMap();
		if (Url.isV2(request)){
			List<DataNode> itemNodes = new ArrayList<DataNode>();
			itemNodes.add(new DataNode(new Data(-1, "无")));
			itemNodes.add(new DataNode(new Data(2, "有色金属类")));
			itemNodes.add(new DataNode(new Data(3, "硅钢片")));
			itemNodes.add(new DataNode(new Data(4, "国际原油")));
			itemNodes.add(new DataNode(new Data(5, "铁矿石")));
			itemNodes.add(new DataNode(new Data(6, "焦炭")));
			itemNodes.add(new DataNode(new Data(7, "废钢材")));
			itemNodes.add(new DataNode(new Data(8, "冷轧薄板")));
			itemNodes.add(new DataNode(new Data(9, "中厚板（Q235 20mm）")));
			itemNodes.add(new DataNode(new Data(10, "高线（45-70# Φ6.5）")));
			itemNodes.add(new DataNode(new Data(11, "PVC树脂")));
			itemNodes.add(new DataNode(new Data(12, "低密度聚乙烯（LDPE）")));
			itemNodes.add(new DataNode(new Data(13, "EVA")));
			itemNodes.add(new DataNode(new Data(14, "进口纸浆")));
			itemNodes.add(new DataNode(new Data(15, "美元指数")));
			itemNodes.add(new DataNode(new Data(16, "螺纹钢")));
			itemNodes.add(new DataNode(new Data(17, "PMI、CPI、PPI")));
			itemNodes.add(new DataNode(new Data(18, "银行基准利率")));
			map.put("itemNodes", JSONArray.fromObject(itemNodes).toString());
			map.put("importUrl", "../import");
		}
		
		
		
		
		return new ModelAndView((Url.isV2(request) ? "ui2/pages/components/import_data" : "priceLib/jcycljg/jcycljg_import_data"), map);
	}
	
	@RequestMapping(value = {"import.do", "v2/import.do"})
	public @ResponseBody byte[] importExcel(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("upfile") CommonsMultipartFile file)
			throws IOException {
		
		String filetype = request.getParameter("filetype");
		if (filetype == null){
			filetype = request.getParameter("item");
		}
		int type = Integer.valueOf(filetype).intValue();
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
