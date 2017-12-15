package com.frame.script.el.em.list.broken;

import java.util.List;

import com.frame.script.el.em.NamedEM;
import com.util.tools.ListUtil;


public class EMListRemoveAt  extends NamedEM{

	
	
	public EMListRemoveAt() {
		super("rm");
	}

	@Override
	public int paramCount() {
		return 1;
	}

	@Override
	public Object invoke(Object stub, List<Object> args) {
		if (stub != null) {
			Integer index = (Integer) args.get(0);
            return ListUtil.rmAt(stub, index);
		}
		return null;
	}

}
