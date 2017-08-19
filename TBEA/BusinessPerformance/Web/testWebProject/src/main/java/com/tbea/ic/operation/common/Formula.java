package com.tbea.ic.operation.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.xml.frame.report.util.Pair;

public class Formula {

	// #13456
	final static Pattern paramNumber = Pattern.compile("#\\s*[0-9]+\\s*");

	final static Pattern patternNumber = Pattern.compile("[0-9]+");

	// #+(5 - 9)
	final static Pattern paramFormula = Pattern
			.compile("#\\s*[\\+|\\-|\\*|/|]{1}\\(\\s*[0-9]+\\s*\\-\\s*[0-9]+\\s*\\)\\s*");
	final static String THIS = "this";
	
	static ScriptEngine jse = new ScriptEngineManager()
			.getEngineByName("JavaScript");

	List<Integer> params = new ArrayList<Integer>();
	Map<Integer, List<Double>> values = new HashMap<Integer, List<Double>>();
	String script;
	
	boolean isThis = false;
	
	
	public Formula(String formula) {
		
		script = formula;
		
		if (THIS.equals(script)){
			isThis = true;
			return ;
		}
		
		if (null == script){
			return;
		}
		
		
		Matcher matcher = paramNumber.matcher(script);
		while (matcher.find()) {
			parserNumber(script.substring(matcher.start() + 1, matcher.end()));
		}

		matcher = paramFormula.matcher(script);
		if (matcher.find()){
			List<Pair<Integer, Integer>> regions = new ArrayList<Pair<Integer, Integer>>();
			List<String> extracts = new ArrayList<String>();
			matcher.reset();
			while (matcher.find()) {
				String extract = parserFormula(matcher);
				if (null != extracts) {
					regions.add(new Pair<Integer, Integer>(matcher.start(), matcher.end()));
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
	}

	public boolean isThis(){
		return isThis;
	}
	
	public boolean isNull(){
		return this.script == null;
	}
	
	public boolean isFormula(){
		return !isNull() && !isThis();
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
			builder.append("(");
			while (start < end){
				builder.append("#");
				builder.append(start);
				builder.append(formul.charAt(0));
				addParam(start);
				++start;
			}
			builder.append("#");
			builder.append(end);
			builder.append(")");
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

	public void setParameter(Integer param, Integer group, Double val) {
		if (null != val) {
			
			if (!values.containsKey(group)){
				values.put(group, Util.resize(new ArrayList<Double>(), params.size()));
			}

			int index = params.indexOf(param);
			if (index >= 0) {
				values.get(group).set(index, val);
			}
		}
	}

	public Double compute(Integer group) {
		if (!values.containsKey(group)) {
			return null;
		}
		try {
			Double val = null;
			List<Double> gp = this.values.get(group);
			String tmpScript = this.script;
			for (int i = 0; i < this.params.size(); ++i) {
				val = gp.get(i) == null ? 0 : gp.get(i);
				tmpScript = tmpScript.replace("#" + this.params.get(i), "" + val);
			}
			return (Double) jse.eval(tmpScript);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
