package com.tbea.ic.operation.controller.servlet.testTable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tbea.test.testWebProject.model.entity.TestTable;
import com.tbea.test.testWebProject.service.testTable.TestTableService;

@Controller
@RequestMapping(value = "TestTable")
public class TestTableController {

	@Autowired
	private TestTableService testTableService;

	private String view = "testTablePage";

	private String commandName = "testTable";

	@RequestMapping(value = "TestGet.do", method = RequestMethod.GET)
	public ModelAndView getTestTableById(HttpServletRequest request,
			HttpServletResponse response) {
		TestTable testTable = testTableService.getTestTableById(1);
		int id = testTable.getId();
		String name = testTable.getName();
		// System.out.println("id:" + id);
		// System.out.println("name:" + name);

		return new ModelAndView(view, commandName, testTable);
	}

}
