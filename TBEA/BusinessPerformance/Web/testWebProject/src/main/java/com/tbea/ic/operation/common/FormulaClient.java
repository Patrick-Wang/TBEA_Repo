package com.tbea.ic.operation.common;

import com.xml.frame.report.util.Pair;

public interface FormulaClient<T>{

	Pair<Integer, T> onThis(FormulaServer<T> server, Formula formula);

	Pair<Integer, T> onNull(FormulaServer<T> server, Formula formula);
	
	Pair<Integer, T> onFormula(FormulaServer<T> server, Formula formula);
	
	Pair<Integer, T> onFormulaNoCache(FormulaServer<T> server, Formula formula);
	
	void onStart(FormulaServer<T> server, Formula formula);
	
	void onComplete(FormulaServer<T> server, Formula formula);
}