package com.tbea.ic.operation.common;

public interface FormulaClient<T>{

	Pair<Integer, T> onThis();

	Pair<Integer, T> onNull();
	
	Pair<Integer, T> onFormula(FormulaServer<T> server, Formula formula);
	
	Pair<Integer, T> onFormulaNoCache(FormulaServer<T> server, Formula formula);
	
}