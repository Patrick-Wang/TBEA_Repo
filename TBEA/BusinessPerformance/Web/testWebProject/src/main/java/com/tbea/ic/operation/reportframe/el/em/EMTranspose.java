package com.tbea.ic.operation.reportframe.el.em;

import java.util.List;

import com.tbea.ic.operation.reportframe.util.TypeUtil;


public class EMTranspose  extends NamedEM{

	public EMTranspose() {
		super("transpose");
	}


	@Override
	public int paramCount() {
		return 0;
	}

	
	private Object transposeArray(List<Object[]> matrix){
		Object retMatrix[][] = new Object[matrix.get(0).length][matrix.size()];
		for (int i = 0; i < matrix.size(); ++i){
			for (int j = 0; j < matrix.get(i).length; ++j){
				retMatrix[j][i] = matrix.get(i)[j];
			}
		}
		return retMatrix;
	}
	
	private Object transpose(List matrix) {
		if (matrix.isEmpty()){
			return matrix;
		}
		
		if (matrix.get(0).getClass().isArray()){
			return transposeArray(matrix);
		}
		if (matrix.get(0) instanceof List){
			return transposeList(matrix);
		}
		
		return matrix;
	}

	private Object transposeList(List<List> matrix) {
		Object retMatrix[][] = new Object[matrix.get(0).size()][matrix.size()];
		for (int i = 0; i < matrix.size(); ++i){
			for (int j = 0; j < matrix.get(i).size(); ++j){
				retMatrix[j][i] = matrix.get(i).get(j);
			}
		}
		return retMatrix;
	}
	
	
	
	@Override
	public Object invoke(Object stub, List<Object> args) {
		if (TypeUtil.instanceOf(stub, List.class)){
			return transpose((List)stub);
		}
		return stub;
	}
}
