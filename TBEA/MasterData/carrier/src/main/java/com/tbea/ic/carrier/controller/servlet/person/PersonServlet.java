package com.tbea.ic.carrier.controller.servlet.person;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tbea.ic.carrier.model.entity.Psn;
import com.tbea.ic.carrier.service.person.PersonService;


@Controller
@RequestMapping(value = "/psn")
public class PersonServlet {

	@Autowired
	PersonService personService;
		
	@RequestMapping(value = "/queryAll.do")
	public @ResponseBody byte[] queryAll(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		String index = request.getParameter("pageindex");
		List<Psn> psns;
		String err = "error";
		try {
			psns = personService.queryPersonInfo(Integer.parseInt(index));
		} catch (NumberFormatException e) {
			return err.getBytes("utf-8");
		}
		return JSONArray.fromObject(psns).toString().getBytes("utf-8");
	}
	
	@RequestMapping(value = "/queryPageCount.do")
	public @ResponseBody byte[] getPersonPagesCount(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException {
		
		int i;
		i = personService.queryPersonInfoPagesCount();
		return String.valueOf(i).getBytes("utf-8");
	}
}
 