package com.tbea.ic.operation.controller.servlet.entry;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.ic.operation.common.DateSelection;
import com.tbea.ic.operation.common.ZBType;
import com.tbea.ic.operation.model.entity.User;
import com.tbea.ic.operation.service.entry.EntryService;


@Controller
@RequestMapping(value = "entry")
public class EntryController {
	
	@Autowired
	private EntryService service;
	
	@RequestMapping(value = "index.do", method = RequestMethod.GET)
	public ModelAndView getIndexEntry(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Map<String, Object> map = new HashMap<String, Object>();
		User usr = new User();
		boolean hasPermission = service.hasEntryPlanPermission(usr);
		map.put("entryPlan", hasPermission);
		hasPermission = service.hasEntryPredictPermission(usr);
		map.put("entryPredict", hasPermission);
		return new ModelAndView("entry_index", map);
	}
	
	@RequestMapping(value = "zb.do", method = RequestMethod.GET)
	public ModelAndView getZBEntry(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		Calendar date = Calendar.getInstance();
		int month = date.get(Calendar.MONTH) + 1;
		int year = date.get(Calendar.YEAR);
		
		
		ZBType entryType = ZBType.valueOf(Integer.valueOf(request.getParameter("entryType")));
		Map<String, Object> map = new HashMap<String, Object>();
		if (entryType == ZBType.QNJH){
			DateSelection dateSel = new DateSelection(year);
			dateSel.select(map);
		}
		else{
			
			DateSelection dateSel = new DateSelection(year, month);
			dateSel.select(map);
		}
		
		map.put("entryType", entryType.ordinal());
//		User usr = new User();
//		Permission perm = service.getPermission(usr);
//		map.put("permission", perm.getSeasonPredict());
			
		return new ModelAndView("entry_template", map);
	}

	
	
	@RequestMapping(value = "zb_update.do", method = RequestMethod.GET)
	public @ResponseBody byte[] getZBEntryUpdate(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ZBType entryType = ZBType.valueOf(Integer.valueOf(request.getParameter("entryType")));
		Date date = DateSelection.getDate(request);
		User usr = new User();
		String[][] ret = null;
		ret =  service.getZb(date, usr, entryType);
//		switch (entryType){
//			case ZBType.BY20:
//				ret = service.get20Zb(date);
//				break;
//			case ZBType.BY28YJ:
//				ret = service.get28Zb(date, usr);
//				break;
//			case BYSJ:
//				ret = service.getBySJZb(date);
//				break;
//			case QNJH:
//				ret = service.getByQNZb(date);
//				break;
//			default:
//				break;
//		}
		
		String zb = JSONArray.fromObject(ret).toString().replace("null", "");
		return zb.getBytes("utf-8");
	}
	
	@RequestMapping(value = "zb_submit.do", method = RequestMethod.POST)
	public @ResponseBody byte[] getZBEntrySubmit(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		ZBType entryType = ZBType.valueOf(Integer.valueOf(request.getParameter("entryType")));
		Date date = DateSelection.getDate(request);
		String data = request.getParameter("data");
		boolean ret = false;
		User usr = new User();
		ret = service.updateZb(date, usr, JSONArray.fromObject(data), entryType);
		
		
//		switch (entryType){
//			case BY20:
//				ret = service.update20Zb(date, JSONArray.fromObject(data));
//				break;
//			case BY28:
//				ret = service.update28Zb(date, usr, JSONArray.fromObject(data));
//				break;
//			case BYSJ:
//				ret = service.updateBySJZb(date, JSONArray.fromObject(data));
//				break;
//			case QNJH:
//				ret = service.updateByQNZb(date, JSONArray.fromObject(data));
//				break;
//			default:
//				break;
//		}
		
		String result = "{\"result\":" + ret + "}";
		return result.getBytes("utf-8");
	}
}
