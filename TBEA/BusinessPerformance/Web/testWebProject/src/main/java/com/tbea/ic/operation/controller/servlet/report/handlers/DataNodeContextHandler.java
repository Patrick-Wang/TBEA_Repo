package com.tbea.ic.operation.controller.servlet.report.handlers;

import java.util.ArrayList;
import java.util.List;

import com.tbea.ic.operation.common.ClosureMap;
import com.tbea.ic.operation.common.Data;
import com.tbea.ic.operation.common.DataNode;
import com.tbea.ic.operation.controller.servlet.report.ContextHandler;
import com.tbea.ic.operation.reportframe.component.entity.Context;

public class DataNodeContextHandler implements ContextHandler {


	@Override
	public void onHandle(Context context) {
		context.put("dataNodeFactory", new ClosureMap(){

			@Override
			protected boolean validate(List<Object> args) throws Exception {
				return args.size() == 2;
			}

			@Override
			protected Object onGetProp(List<Object> args) throws Exception {
				List<Integer> ids = (List<Integer>) args.get(0);
				List<String> vals = (List<String>) args.get(1);
				List<DataNode> nodes = new ArrayList<DataNode>(args.size());
				for (int i = 0; i < ids.size(); ++i){
					DataNode dn = new DataNode();
					Data d = new Data();
					d.setId(ids.get(i));
					d.setValue(vals.get(i));
					dn.setData(d);
					nodes.add(dn);
				}
				return nodes;
			}
		});
	}

}
