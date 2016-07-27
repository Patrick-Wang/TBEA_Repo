package com.tbea.ic.operation.reportframe.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.w3c.dom.Element;

import com.tbea.ic.operation.common.Data;
import com.tbea.ic.operation.common.DataNode;
import com.tbea.ic.operation.reportframe.component.ComponentLoader.ComponentLoadedListener;

public class ComponentStructureBuilder implements ComponentLoadedListener {

	DataNode lastNode;
	Stack<DataNode> runStack = new Stack<DataNode>();
	
	DataNode getFolderNode(String path){
		int index = path.lastIndexOf("\\");
		DataNode dataNode = new DataNode();
		Data data = new Data();
		dataNode.setData(data);
		data.setId(1);
		data.setValue(path.substring(index + 1));
		return dataNode;
	}
	
	DataNode getFileNode(String path){
		int index = path.lastIndexOf("\\");
		DataNode dataNode = new DataNode();
		Data data = new Data();
		dataNode.setData(data);
		data.setId(2);
		data.setValue(path.substring(index + 1));
		return dataNode;
	}
	
	DataNode getServiceNode(String id){
		DataNode dataNode = new DataNode();
		Data data = new Data();
		dataNode.setData(data);
		data.setId(3);
		data.setValue(id);
		return dataNode;
	}
	
	DataNode getControllerNode(String id){
		DataNode dataNode = new DataNode();
		Data data = new Data();
		dataNode.setData(data);
		data.setId(4);
		data.setValue(id);
		return dataNode;
	}
	
	public DataNode getRootNode(){
		return lastNode;
	}
	
	private DataNode find(List<DataNode> nodes, String path){
		if (null != nodes){
			int index = path.lastIndexOf("\\");
			path = path.substring(index + 1);
			for (DataNode node : nodes){
				if (node.getData().getValue().equals(path)){
					return node;
				}
			}
		}
		return null;
	}
	
	@Override
	public void onEnterFolder(String filePath) {

		if (runStack.isEmpty()){
			if (lastNode != null){
				runStack.push(lastNode);
			}else{
				DataNode node = getFolderNode(filePath);
				node.getData().setId(0);
				runStack.push(node);
			}
		}else{
			DataNode node = find(runStack.peek().getSubNodes(), filePath);
			if (node == null){
				if (runStack.peek().getSubNodes() == null){
					runStack.peek().setSubNodes(new ArrayList<DataNode>());
				}
				node = getFolderNode(filePath);
				runStack.peek().getSubNodes().add(node);
			}
			runStack.push(node);
		}
	}

	@Override
	public void onExitFolder(String filePath) {
		lastNode = runStack.pop();
	}

	@Override
	public void onEnterFile(String filePath) {
		DataNode node = find(runStack.peek().getSubNodes(), filePath);
		if (node == null){
			if (runStack.peek().getSubNodes() == null){
				runStack.peek().setSubNodes(new ArrayList<DataNode>());
			}
			node = getFileNode(filePath);
			runStack.peek().getSubNodes().add(node);
		}
		runStack.push(node);
	}

	@Override
	public void onExitFile(String filePath) {
		runStack.pop();
	}

	@Override
	public void onService(String id, Element e, String filePath) {
		DataNode node = find(runStack.peek().getSubNodes(), id);
		if (node == null){
			if (runStack.peek().getSubNodes() == null){
				runStack.peek().setSubNodes(new ArrayList<DataNode>());
			}
			node = getServiceNode(id);
			runStack.peek().getSubNodes().add(node);
		}
	}

	@Override
	public void onController(String id, Element e, String filePath) {
		DataNode node = find(runStack.peek().getSubNodes(), id);
		if (node == null){
			if (runStack.peek().getSubNodes() == null){
				runStack.peek().setSubNodes(new ArrayList<DataNode>());
			}
			node = getControllerNode(id);
			runStack.peek().getSubNodes().add(node);
		}
	}

}
