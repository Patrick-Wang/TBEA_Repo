package com.frame.script.el.em.list.broken;

import java.util.List;

import com.frame.script.el.em.NamedEM;
import com.frame.script.util.TypeUtil;


public class EM2Fixed extends NamedEM{


	
	public EM2Fixed() {
		super("toFixed");
	}

	String toFixed(Double val, Integer size) {
		return String.format("%." + size + "f", val);
	}
	
	@Override
	public Object invoke(Object stub, List<Object> args) {
		if (TypeUtil.instanceOf(stub, Double.class)){
			return toFixed((Double) stub, (Integer)args.get(0));
		}
		if (stub.getClass().isArray()) {
			Object[] ref = (Object[]) stub;
			for (int i = 0; i < ref.length; ++i) {
				if (null != ref[i] && TypeUtil.instanceOf(ref[i], Double.class)) {
					ref[i] = toFixed((Double) ref[i], (Integer)args.get(0));
				}
			}
			return ref;
		}
		
		if ( TypeUtil.instanceOf(stub, List.class)) {
			List ref = (List) stub;
			for (int i = 0; i < ref.size(); ++i) {
				if (null != ref.get(i) && TypeUtil.instanceOf(ref.get(i), Double.class)) {
					ref.set(i, toFixed((Double) ref.get(i), (Integer)args.get(0)));
				}
			}
			return ref;
		}
		return stub;
	}



	@Override
	public int paramCount() {
		return 1;
	}
}
