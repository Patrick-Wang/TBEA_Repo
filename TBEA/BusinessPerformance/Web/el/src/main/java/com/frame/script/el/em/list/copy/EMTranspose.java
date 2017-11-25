package com.frame.script.el.em.list.copy;

import java.util.ArrayList;
import java.util.List;

import com.frame.script.el.em.NamedEM;
import com.frame.script.util.TypeUtil;


public class EMTranspose  extends NamedEM{
	
	public EMTranspose() {
		super("transpose");
	}


	@Override
	public int paramCount() {
		return 0;
	}

	
	private Object transposeArray(List<Object[]> matrix){
		List<List> retMatrix = new ArrayList<List>();
		for (int i = 0; i < matrix.get(i).length; ++i) {
			retMatrix.add(new ArrayList<Object>());
			for (int j = 0; j < matrix.size(); ++j) {
				retMatrix.get(i).add(null);
			}
		}
		
		for (int i = 0; i < matrix.size(); ++i){
			for (int j = 0; j < matrix.get(i).length; ++j){
				retMatrix.get(j).set(i,  matrix.get(i)[j]);
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
		List<List> retMatrix = new ArrayList<List>();
		for (int i = 0; i < matrix.get(0).size(); ++i) {
			retMatrix.add(new ArrayList<Object>());
			for (int j = 0; j < matrix.size(); ++j) {
				retMatrix.get(i).add(null);
			}
		}
		
		for (int i = 0; i < matrix.size(); ++i){
			for (int j = 0; j < matrix.get(i).size(); ++j){
				retMatrix.get(j).set(i, matrix.get(i).get(j));
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
