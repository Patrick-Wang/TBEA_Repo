package com.tbea.ic.operation.common;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Formula {

	// #13456
	Pattern paramNumber = Pattern.compile("#\\s*[0-9]+\\s*");

	Pattern patternNumber = Pattern.compile("[0-9]+");

	// #+(5 - 9)
	Pattern paramFormula = Pattern
			.compile("#\\s*[\\+|\\-|\\*|/|]{1}\\(\\s*[0-9]+\\s*\\-\\s*[0-9]+\\s*\\)\\s*");

	static ScriptEngine jse = new ScriptEngineManager()
			.getEngineByName("JavaScript");

	List<Integer> params = new ArrayList<Integer>();
	List<Double> values = new ArrayList<Double>();
	String script;

	public Formula(String formula) {
		script = formula;
		Matcher matcher = paramNumber.matcher(script);
		while (matcher.find()) {
			parserNumber(script.substring(matcher.start() + 1, matcher.end()));
		}

		matcher = paramFormula.matcher(script);
		List<Pair<Integer, Integer>> regions = new ArrayList<Pair<Integer, Integer>>();
		List<String> extracts = new ArrayList<String>();
		while (matcher.find()) {
			String extract = parserFormula(matcher);
			if (null != extracts) {
				regions.add(new Pair(matcher.start(), matcher.end()));
				extracts.add(extract);
			}
		}

		StringBuilder builder = new StringBuilder();
		int start = 0;
		for (int i = 0; i < regions.size(); ++i) {
			builder.append(this.script.substring(start, regions.get(i)
					.getFirst()));
			builder.append(extracts.get(i));
			start = regions.get(i).getSecond();
		}
		this.script = builder.toString();
	}

	private String parserFormula(Matcher matcher) {
		String formul = matcher.group().replaceAll("#|\\s", "");
		Matcher numberMatcher = patternNumber.matcher(formul);
		Integer start = null;
		Integer end = null;
		if (numberMatcher.find()){
			start = Integer.valueOf(formul.substring(numberMatcher.start(), numberMatcher.end())); 
		}
		if (numberMatcher.find()){
			end = Integer.valueOf(formul.substring(numberMatcher.start(), numberMatcher.end())); 
		}
		if (start != null && end != null){
			start = Math.min(start, end);
			end = Math.max(start, end);
			StringBuilder builder = new StringBuilder();
			while (start < end){
				builder.append("#");
				builder.append(start);
				builder.append(formul.charAt(0));
				addParam(start);
				++start;
			}
			builder.append("#");
			builder.append(end);
			addParam(end);
			return builder.toString();
		}
		return null;
	}

	private void addParam(Integer val) {
		if (params.indexOf(val) < 0) {
			params.add(val);
		}
	}

	private void parserNumber(String number) {
		addParam(Integer.valueOf(number.trim()));
	}

	public List<Integer> getParameters() {
		return this.params;
	}

	public void setParameter(Integer param, Double val) {
		if (null != val) {
			if (values.isEmpty()) {
				Util.resize(values, params.size());
			}
			int index = params.indexOf(param);
			if (index >= 0) {
				values.set(index, val);
			}
		}
	}

	public Double compute() {
		if (values.isEmpty()) {
			return null;
		}
		try {
			Double val;
			for (int i = 0; i < this.params.size(); ++i) {
				val = this.values.get(i) == null ? 0 : this.values.get(i);
				this.script = this.script.replace("#" + this.params.get(i), ""
						+ val);
			}

			return (Double) jse.eval(this.script);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
