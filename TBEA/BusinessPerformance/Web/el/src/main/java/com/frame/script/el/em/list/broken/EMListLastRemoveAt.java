package com.frame.script.el.em.list.broken;

import java.util.List;

import com.frame.script.el.em.NamedEM;
import com.util.tools.ListUtil;


public class EMListLastRemoveAt  extends NamedEM{

	
	
	public EMListLastRemoveAt() {
		super("lastRm");
	}

	@Override
	public int paramCount() {
		return 1;
	}

	@Override
	public Object invoke(Object stub, List<Object> args) {
		if (stub != null) {
			Integer lastIndex = (Integer) args.get(0);
            return ListUtil.lastRmAt(stub, lastIndex);
		}
		return null;
	}

}
