package com.xml.frame.report.component;

import java.util.ArrayList;
import java.util.Stack;

import org.w3c.dom.Element;

import com.xml.frame.report.util.Data;
import com.xml.frame.report.util.DataNode;

public class ComponentStructureBuilder implements ConfigLoadedListener {

	public final static int FOLDER = 1;
	public final static int FILE = 2;
	public final static int SERVICE = 3;
	public final static int CONTROLLER = 4;
	DataNode lastNode;
	Stack<DataNode> runStack = new Stack<DataNode>();
	String basePath;
	DataNode getFolderNode(String path){
		DataNode dataNode = new DataNode();
		Data data = new Data();
		dataNode.setData(data);
		data.setId(FOLDER);
		data.setValue(path.substring(basePath.length()));
		return dataNode;
	}
	
	String getBasePath(){
		return basePath;
	}
	
	DataNode getFileNode(String path){
		DataNode dataNode = new DataNode();
		Data data = new Data();
		dataNode.setData(data);
		data.setId(FILE);
		data.setValue(path.substring(basePath.length()));
		return dataNode;
	}
	
	DataNode getServiceNode(String id){
		DataNode dataNode = new DataNode();
		Data data = new Data();
		dataNode.setData(data);
		data.setId(SERVICE);
		data.setValue(id);
		return dataNode;
	}
	
	DataNode getControllerNode(String id){
		DataNode dataNode = new DataNode();
		Data data = new Data();
		dataNode.setData(data);
		data.setId(CONTROLLER);
		data.setValue(id);
		return dataNode;
	}
	
	public String nextPathSegment(String path, int start){
		if (start >= path.length()){
			return null;
		}
		int index = path.indexOf("\\", start + 1);
		if (index >= 0){
			return path.substring(start, index);
		}
		return path.substring(start);
	}
	
	
	public DataNode getParentNode(String path){
		if (lastNode != null){
			int end = path.lastIndexOf("\\");
			return getNode(path.substring(0, end));
		}
		return null;
	}
	
	public DataNode getNode(String path){
		if (lastNode != null){
			String subPath = path.substring(basePath.length());
			int start = 0;
			String pathSegment = nextPathSegment(subPath, start);
			DataNode node = lastNode;
			if (node.getData().getValue().equals(pathSegment)){
				start += pathSegment.length();
				while (null != node && null != (pathSegment = nextPathSegment(subPath, start))){
					start += pathSegment.length();
					node = node.findByValue(subPath.substring(0, start));
				}
				return node;
			}
		}
		return null;
	}
	
	public DataNode getRootNode(){
		return lastNode;
	}
	
	@Override
	public void onEnterFolder(String filePath) {
		if (runStack.isEmpty()){
			if (lastNode != null){
				runStack.push(lastNode);
			}else{
				basePath = filePath.substring(0, filePath.lastIndexOf("\\"));
				DataNode node = getFolderNode(filePath);
				node.getData().setId(0);
				runStack.push(node);
			}
		}else{
			DataNode node = runStack.peek().findByValue(filePath.substring(basePath.length()));
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
		DataNode node = runStack.peek().findByValue(filePath.substring(basePath.length()));
		if (node == null){
			if (runStack.peek().getSubNodes() == null){
				runStack.peek().setSubNodes(new ArrayList<DataNode>());
			}
			node = getFileNode(filePath);
			runStack.peek().getSubNodes().add(node);
		}else{
			if (null != node.getSubNodes()){
				node.getSubNodes().clear();
			}
		}
		runStack.push(node);
	}

	@Override
	public void onExitFile(String filePath) {
		runStack.pop();
	}

	@Override
	public void onService(String id, Element e, String filePath) {
		DataNode node = runStack.peek().findByValue(id);
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
		DataNode node = runStack.peek().findByValue(id);
		if (node == null){
			if (runStack.peek().getSubNodes() == null){
				runStack.peek().setSubNodes(new ArrayList<DataNode>());
			}
			node = getControllerNode(id);
			runStack.peek().getSubNodes().add(node);
		}
	}

	@Override
	public void onDeleteFile(String filePath) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDeleteFolder(String folderPath) {
		// TODO Auto-generated method stub
		
	}

}
