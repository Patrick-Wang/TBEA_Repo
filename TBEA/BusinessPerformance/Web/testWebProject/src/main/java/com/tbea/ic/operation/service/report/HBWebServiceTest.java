package com.tbea.ic.operation.service.report;

import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import net.sf.json.JSONArray;

public class HBWebServiceTest {

	@Test
	public void testGetHBData() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHBNbzlqk() {
		HBWebService hbService = new HBWebService();
		List<String> ls = new ArrayList<String>();
		ls.add("company_name");
		ls.add("issue_happen_date");
		ls.add("product_type");
		ls.add("production_num");
		ls.add("production_model");
		ls.add("issue_type");
		ls.add("sub_issue_type");
		ls.add("category_code");
		ls.add("material_quality_phenomenon");
		ls.add("detail");
		ls.add("material_happen_phase");
		ls.add("material_count");
		ls.add("measurement_units");
		ls.add("suppier_id");
		ls.add("suppier");
		ls.add("issue_process");
		ls.add("responsibility_department");
		ls.add("material_treatment_measure");
		ls.add("onsite_treatmen_measure");
		ls.add("onsite_treatment_result");
		ls.add("causa_analysis");
		ls.add("assessment");
		ls.add("filling_personnel");
		List ret = hbService.getHBNbzlqk(ls);
		System.out.println(JSONArray.fromObject(ret).toString());
	}

	@Test
	public void testGetHBWbzlqk() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHBSjzbListOfString() {
		HBWebService hbws = new HBWebService();
		List<String> cols = new ArrayList<String>();
		cols.add("company_name");
		cols.add("index_name");
		cols.add("sjs");
		List<Object[]> result = hbws.getHBSjzb(cols, Date.valueOf("2017-10-1"));
		System.out.println(JSONArray.fromObject(result).toString());
	}

	@Test
	public void testGetHBSjzbListOfStringDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHBClwcqkListOfString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHBClwcqkListOfStringDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHBCpqyListOfString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHBCpqyListOfStringDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHBScqyListOfString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetHBScqyListOfStringDate() {
		fail("Not yet implemented");
	}

}
