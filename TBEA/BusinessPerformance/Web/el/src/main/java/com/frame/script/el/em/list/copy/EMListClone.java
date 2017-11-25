package com.frame.script.el.em.list.copy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.frame.script.el.em.NamedEM;
import com.util.tools.MathUtil;


public class EMListClone  extends NamedEM{

	public EMListClone() {
		super("clone");
	}

	@Override
	public int paramCount() {
		return 0;
	}


	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object invoke(Object stub, List<Object> args) {
		if (stub != null) {
			if (stub instanceof List) {
				List list = (List) stub;
				List lsNew = new ArrayList();
				lsNew.addAll(list);
				return lsNew;
			}
		}
		return null;
	}

}
