package com.frame.script.el.em.list.copy;

import java.util.ArrayList;
import java.util.List;

import com.frame.script.el.em.NamedEM;
import com.frame.script.util.TypeUtil;
import com.util.tools.StringUtil;


public class EMListPick extends NamedEM {

	
	
    public EMListPick() {
        super("pick");
    }


    @Override
    public int paramCount() {
        return 1;
    }



   
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Object invoke(Object stub, List<Object> args) {
        if (stub instanceof List) {
            List list = (List) stub;
            List ret = new ArrayList();
            if (TypeUtil.instanceOf(args.get(0), String.class)) {
	            String sIndexs = (String) args.get(0);
	            sIndexs = StringUtil.shrink(sIndexs);
	            int start = 0;
	            int end = sIndexs.indexOf(",");
	            Integer i;
	            while(end >= 0) {
	            	i = Integer.valueOf(sIndexs.substring(start, end));
	            	ret.add(list.get(i));
	            	start = end + 1;
	            	end = sIndexs.indexOf(',', start);
	            }
	            i = Integer.valueOf(sIndexs.substring(start));
	        	ret.add(list.get(i));
	        }
            else if (TypeUtil.instanceOf(args.get(0), List.class)){
            	for (Integer i : (List<Integer>)args.get(0)) {
            		ret.add(list.get(i));
            	}
            }else if (TypeUtil.instanceOf(args.get(0), Integer.class)){
            	ret.add(list.get((Integer) args.get(0)));
            }
        	return ret;
        	
        }
        return null;
    }
}
