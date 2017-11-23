package com.frame.script.el.em;

import java.util.List;

import com.frame.script.util.TypeUtil;


public class EMDistinct extends NamedEM{

	
	
	public EMDistinct() {
		super("distinct");
	}
	
	@Override
	public Object invoke(Object stub, List<Object> args) {
		if (TypeUtil.instanceOf(stub, List.class)){
			List pkg = (List) stub;
			for (int i = 0; i < pkg.size(); ++i){
				for (int j = pkg.size() - 1; j > i; --j){
					if ((pkg.get(i) == null && pkg.get(j) == null) || pkg.get(i).equals(pkg.get(j))){
						pkg.remove(j);
					}
				}
			}
			return pkg;
		}
		return stub;
	}




	@Override
	public int paramCount() {
		return 0;
	}
}
